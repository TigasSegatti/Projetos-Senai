/* Projeto SA – MEDIDOR DE QUALIDADE DA ÁGUA
13.05.2024 - V2.0 
 Mudanças
 Foi incluido o arrendondamento da a variavel distanciaAtual e cast Integer
 Agora é chamada por distanciaAgora (inteiro)
 Mudança das variaveis distanciaAtual para distanciaAgora nos if/else
 Mudança nas variaveis do TagoIo
 Foi colocado retirado um subscribe 
 O String Json dentro da delayFunction()    
*/

// Bibliotecas
#include <EspMQTTClient.h> // EspMQTTClient
#include <ArduinoJson.h> // ArduinoJson
#include <math.h>
#include <OneWire.h>
#include <DallasTemperature.h>

// Definição das constantes do pinos que serão utilizados para o sensor ultrassônico.
// HC-SR04. O pino de LED foi implementado para sinalizar os limites inferior e superior
// que no caso do sensor HC-SR04 são de 10cm (limite inferior) e 399cm (limite superior)
#define PINO_TRIG 4
#define PINO_ECHO 2
#define PINO_LED 5
//Definindo pino de medidor de temperatura
// GPIO where the DS18B20 is connected to
const int oneWireBus = 32;  


float distanciaAnterior = 0.0;
float temperaturaAnterior=0.0;
bool mudouValorDoSensor = false;
float temperaturaAgora =0.0;
/* 
  Conexões WI-FI e MQTT
 Construtor que passa 6 parametros
*/

EspMQTTClient client(
"Wokwi-GUEST", //Rede wi-fi
"", // Senha wi-fi
"mqtt.tago.io", // Broker
"Default", // Usuário (qq string)
"b4e26df0-3d36-4442-aa33-df14089f84d1", // Token da plataforma Tago.io
"SA_IOT_2024" // Identificação única (qq string)
);

/*****************************************************************************
Função: delayedFunction
Parâmetros de entrada: nenhum
Retorno: void
Esta função é responsável por publicar os dados dos sensores no tópico 
definido na plataforma TAGO.IO. Ele é executada via chamada de callback a cada 
5 segundos. Importante a função deve ser registrada novamente no método
client.executeDelayed(), caso contrário será executada  somente uma vez

*****************************************************************************/

//PARTE DA TEMPERATURA 
// Setup a oneWire instance to communicate with any OneWire devices
OneWire oneWire(oneWireBus);
// Pass our oneWire reference to Dallas Temperature sensor 
DallasTemperature sensors(&oneWire);
void delayedFunction() {

  DynamicJsonDocument dados(1024);
  String dadosJson;

  mudouValorDoSensor = false;

  // processa informações do sensor HC-SR04, medindo o tamanho (em microssegundos) do pulso
  // da entrada ECHO
  digitalWrite(PINO_TRIG, LOW);
  delayMicroseconds(2);
  digitalWrite(PINO_TRIG, HIGH);
  delayMicroseconds(10);
  digitalWrite(PINO_TRIG, LOW);
  long duracao = pulseIn(PINO_ECHO, HIGH); // Mede o tempo de resposta do ECHO
  float distanciaAtual = (duracao * 0.0343) / 2;// Calcula a distância usando a velocidade do som (aproximadamente 343 m/s)
  int distanciaAgora = round(distanciaAtual);
  sensors.requestTemperatures(); 
   temperaturaAgora = sensors.getTempCByIndex(0);
  
  //Testar conexão
  // Serial.println(distanciaAtual);
  // Serial.println(distanciaAgora);
  
  if (distanciaAgora <= 10 || distanciaAgora > 399 ) // quando estiver menor ou igual a 10 cm enviará um alerta. ** Valor editável para a distância preferir.
  {
    digitalWrite(PINO_LED, HIGH); // Acende o LED se a distância for menor ou igual a 10 cm
  } else {
    digitalWrite(PINO_LED, LOW);  // Desliga o LED caso contrário
  }
  
  // questiona se o sensor HC-SR04 apresentou mudança de valor de distância
  // se sim, ira atualizar o flag que indica mudança de valor para TRUE, de forma 
  // a publicar nova informação na função de callback registrada no método client.executeDelayed()

  if(distanciaAgora != distanciaAnterior){
    distanciaAnterior = distanciaAgora;
    mudouValorDoSensor = true;
    // Insere os dados do sensor HC-SR04 na estrutura Json (variável dados)
    dados[0]["variable"] = "distanciaAgora";
    dados[0]["unit"] = "cm";
    dados[0]["value"] = distanciaAgora  ;
  }
  if(temperaturaAgora != temperaturaAnterior){
    temperaturaAnterior= temperaturaAgora;
         mudouValorDoSensor = true;
    dados[0]["variable"] = "temperatura";
    dados[0]["unit"] = "c";
    dados[0]["value"] = temperaturaAgora ;
  }



   // MQTT (Publish) e controle (alteração de valores)
   Serial.println("Publicando a leitura dos sensores de qualidade da água...");
   Serial.println(dadosJson);

   if(mudouValorDoSensor){
    // Conversão da estrutura de dados (JSON) para uma 
    // String (serialização), que será enviada como payload de um pacote MQTT
    serializeJson(dados, dadosJson);
    client.publish("le_dados_qualidade_agua", dadosJson);
   }

   // chama novamente função de callback
   client.executeDelayed(5000, delayedFunction);

}

// Setup
void setup() {

  // alteração dos parâmetros default por causa das desconexões frequentes
  // do wifi dummy do wokwi
  client.enableMQTTPersistence();
  client.setMqttReconnectionAttemptDelay(2000);
  client.setWifiReconnectionAttemptDelay(3000);
  client.setKeepAlive(20);

  //habilita msgs de debug
  client.enableDebuggingMessages(true);
  client.setMaxPacketSize(256);

  // delay com callback, não posso usar delay(x ms)
  // devido a conexão wifi
  client.executeDelayed(5000, delayedFunction);

  // define os modos de operação dos pinos associados ao sensor HC-SR04
  pinMode(PINO_LED, OUTPUT);  // configura o pino de LED como saída
  pinMode(PINO_TRIG, OUTPUT); // Configura o pino TRIG como saída
  pinMode(PINO_ECHO, INPUT);  // Configura o pino ECHO como entrada 

  Serial.begin(115200);
  sensors.begin();
 }
//Sensor temperatura começa
// Loop
void loop() {
  client.loop();

  //Parte sensores
    
 // float temperaturaAgora = sensors.getTempCByIndex(0);
  Serial.print(temperaturaAgora);
  Serial.println("ºC");
  delay(5000);
}


/*****************************************************************************
Função: onConnectionEstablished
Parâmetros de entrada: nenhum
Retorno: void
Na conexão com o broker (no caso utilizamos o TAGO.IO) bem sucedida,
deveremos efetuar a subscrição (subscribe) dos tópicos que deverão ser
atualizados, no projeto em questão atualizaremos somente um tópico
chamado de "le_dados_qualidade_agua"
*****************************************************************************/
void onConnectionEstablished() {

  Serial.println("Conexao com tago.io estabelecida, assinando topicos...");
    
  Serial.println("Assinando o topico que atualiza os dados de qualidade da água....");
  client.subscribe("le_dados_qualidade_agua", [] (const String &payload)  {
    });

}