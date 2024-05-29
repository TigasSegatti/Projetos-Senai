/* Projeto SA – MEDIDOR DE QUALIDADE DA ÁGUA
27.05.2024 - V3.5
Atualizações: 
Nesta atualização foi inserido a calibragem do sensor de turbidez através de uma função quadrática
Foi inserido o valor de medida NTU para o Tagio 
*/
// Bibliotecas
#include <EspMQTTClient.h> 
#include <ArduinoJson.h> 
#include <OneWire.h>
#include <DallasTemperature.h>


#define PINO_TRIG 4 
#define PINO_ECHO 2
//Localiza aonde esta o leitor analógico do leitor de turbidez
#define PORTA_ANALOGICA 35
// Localização da esp32 onde DS18B20 está conectado 
const int oneWireBus = 32;
//  
float distanciaAnterior = 0.0;
float temperaturaAnterior=0.0;
bool mudouValorDoSensor = false;

//PARTE DA TEMPERATURA 
// Setup a oneWire instance to communicate with any OneWire devices
OneWire oneWire(oneWireBus);
// Pass our oneWire reference to Dallas Temperature sensor 
DallasTemperature sensors(&oneWire);
/* 
  Conexões WI-FI e MQTT
 Construtor que passa 6 parametros
*/

EspMQTTClient client(
"Tiago's iPhone", //Rede wi-fi
"Dolls1234", // Senha wi-fi
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
void delayedFunction() {

  DynamicJsonDocument dados(1024);
  String dadosJson;  //Json passado para distância
  String dadosJson2; //Json passado para Temperatura
  String dadosJson3; //Json para turbidez
  mudouValorDoSensor = false; //Variável booleana para ver se houve alteração
  // processa informações do sensor HC-SR04, medindo o tamanho (em microssegundos) do pulso
  // da entrada ECHO
  digitalWrite(PINO_TRIG, LOW);
  delayMicroseconds(2);
  digitalWrite(PINO_TRIG, HIGH);
  delayMicroseconds(10);
  digitalWrite(PINO_TRIG, LOW);
  long duracao = pulseIn(PINO_ECHO, HIGH); // Mede o tempo de resposta do ECHO
  float distanciaAtual = (duracao * 0.0343) / 2;// Calcula a distância usando a velocidade do som (aproximadamente 343 m/s)
  //Arredondamento da distância com o método round
  int distanciaAgora = round(distanciaAtual);
  sensors.requestTemperatures(); // Função para o sensor captar temperatura
  float temperaturaAtual = sensors.getTempCByIndex(0); //Definindo que variável ira receber a temperatura
  
  //Testar variáveis caso nescessário
  // Serial.println(distanciaAtual);
  // Serial.println(distanciaAgora);
  // Serial.println("Valor Anterior Temperatura....");
  //Serial.println(temperaturaAnterior);
  //Serial.println("Valor Atual Temperatura....");
  //Serial.println(temperaturaAtual);
  
  // questiona se o sensor HC-SR04 apresentou mudança de valor de distância
  if(distanciaAgora != distanciaAnterior){
    mudouValorDoSensor = true;
    distanciaAnterior = distanciaAgora;

  }
  // Questiona se o sensor DS18B20 apresentou alguma mudança de valor de temperatura
  if(temperaturaAtual != temperaturaAnterior){
    mudouValorDoSensor = true;
    temperaturaAnterior= temperaturaAtual;
  }
   // MQTT (Publish) e controle (alteração de valores)
   Serial.println("Publicando a leitura dos sensores de qualidade da água...");
   Serial.println(dadosJson);
  
  //Parte do sensor de turbidez
  int analogValue=0.0;
  int i=0;
  int somaAnalogValue=0;
  for (i = 0; i < 99; i++)
  {
  // Lê o valor analógico da saida GPIO 35
  analogValue = analogRead(PORTA_ANALOGICA);
  somaAnalogValue += analogValue; // soma valores
  }

  analogValue = somaAnalogValue/100;  // valor médio
  Serial.print("Analog Value MEDIO (MEDIA DE 100 VALORES): ");
  Serial.println(analogValue);
    // Add a delay to slow down the output in the Serial Monitor
  delay(1000);
 
  //Calculo de função para calibragem de turbidez 
 
 int NTU = -0.012144*analogValue*analogValue + 0.90066*analogValue + 2995.8;

 if(NTU <0)
 {
  NTU=0;
 }

 Serial.println("NTU : ");
 Serial.print(NTU);
 Serial.println();

   if(mudouValorDoSensor){
    // Conversão da estrutura de dados (JSON) para uma 
    // String (serialização), que será enviada como payload de um pacote MQTT
    // Converte float para string com uma casa decimal
    char buff_t[7];
        // Insere os dados do sensor HC-SR04 na estrutura Json (variável dados)
    dados[0]["variable"] = "distanciaAgora";
    dados[0]["unit"] = "cm";
    dados[0]["value"] = distanciaAgora  ;

        // Insere os dados do sensor DS18B20 na estrutura Json (váriavel dados)
    dados[1]["variable"] = "temperatura";
    dados[1]["unit"] = "ºC";
    snprintf(buff_t, sizeof(buff_t),"%.1f",temperaturaAtual);
    dados[1]["value"] = buff_t ;
    
        // Insere os dados do sensor de turbidzer st100 na estrutura Json (váriavel dados)
    dados[2]["variable"] = "turbidez";
    dados[2]["unit"] = "NTU";
    dados[2]["value"] = NTU;
;

    serializeJson(dados, dadosJson);
    serializeJson(dados, dadosJson2);
    serializeJson(dados, dadosJson3);
    client.publish("le_dados_qualidade_agua", dadosJson);
    //Publica a leitura da temperatura da água
    Serial.println("Publicando a leitura dos sensores de temperatura da água....");
    Serial.println(dadosJson2);
    client.publish("temperatura", dadosJson2);
    //Publica a leitura da turbidez da água
    Serial.println("Publicando a leitura de turbidez da água....");
    Serial.println(dadosJson3);
    client.publish("turbidez", dadosJson3);
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
  pinMode(PINO_TRIG, OUTPUT); // Configura o pino TRIG como saída
  pinMode(PINO_ECHO, INPUT);  // Configura o pino ECHO como entrada 

  Serial.begin(115200);
  sensors.begin();
  //Definindo tamanho da leitura analógica em 10 bits 
  analogReadResolution(10);
 }
//Sensor temperatura começa
// Loop
void loop() {
client.loop();
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
  client.subscribe("le_dados_qualidade_agua", [] (const String &payload) {});
    client.subscribe("le_temperatura_agua", [](const String &payload) {});
}