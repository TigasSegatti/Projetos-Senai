#include <EspMQTTClient.h>
#include <ArduinoJson.h>
#include <math.h>
#include <OneWire.h>
#include <DallasTemperature.h>

#define PINO_TRIG 4
#define PINO_ECHO 2
#define PINO_LED 5
const int oneWireBus = 12;

float distanciaAnterior = 0.0;
float temperaturaAnterior = 0.0;
bool mudouValorDoSensor = false;

EspMQTTClient client(
  "batman",
  "gabi1801",
  "mqtt.tago.io",
  "Default",
  "b4e26df0-3d36-4442-aa33-df14089f84d1",
  "SA_IOT_2024"
);

OneWire oneWire(oneWireBus);
DallasTemperature sensors(&oneWire);

void delayedFunction() {
  DynamicJsonDocument distanciaDados(1024);
  DynamicJsonDocument temperaturaDados(1024);
  String distanciaJson;
  String temperaturaJson;

  mudouValorDoSensor = false;

  digitalWrite(PINO_TRIG, LOW);
  delayMicroseconds(2);
  digitalWrite(PINO_TRIG, HIGH);
  delayMicroseconds(10);
  digitalWrite(PINO_TRIG, LOW);
  long duracao = pulseIn(PINO_ECHO, HIGH);
  float distanciaAtual = (duracao * 0.0343) / 2;
  int distanciaAgora = round(distanciaAtual);

  if (distanciaAgora <= 10 || distanciaAgora > 399 ) {
    digitalWrite(PINO_LED, HIGH);
  } else {
    digitalWrite(PINO_LED, LOW);
  }

  if (distanciaAgora != distanciaAnterior) {
    distanciaAnterior = distanciaAgora;
    mudouValorDoSensor = true;
    distanciaDados["variable"] = "distanciaAgora";
    distanciaDados["unit"] = "cm";
    distanciaDados["value"] = distanciaAgora;
  }

  sensors.requestTemperatures();
  float temperaturaC = sensors.getTempCByIndex(0);

  if (isnan(temperaturaC)) {
    Serial.println("Erro ao ler temperatura!");
  } else {
    if (temperaturaC != temperaturaAnterior) {
      temperaturaAnterior = temperaturaC;
      mudouValorDoSensor = true;
      temperaturaDados["variable"] = "temperatura";
      temperaturaDados["unit"] = "ºC";
      temperaturaDados["value"] = temperaturaC;
    }
  }

  serializeJson(distanciaDados, distanciaJson);
  serializeJson(temperaturaDados, temperaturaJson);

  if (mudouValorDoSensor) {
    client.publish("le_dados_distancia", distanciaJson);
    client.publish("le_dados_temperatura", temperaturaJson);
  }

  client.executeDelayed(5000, delayedFunction);
}

void setup() {
  client.enableMQTTPersistence();
  client.setMqttReconnectionAttemptDelay(2000);
  client.setWifiReconnectionAttemptDelay(3000);
  client.setKeepAlive(20);
  client.enableDebuggingMessages(true);
  client.setMaxPacketSize(256);
  client.executeDelayed(5000, delayedFunction);

  pinMode(PINO_LED, OUTPUT);
  pinMode(PINO_TRIG, OUTPUT);
  pinMode(PINO_ECHO, INPUT);

  Serial.begin(115200);
  sensors.begin();
}

void loop() {
  client.loop();
}

void onConnectionEstablished() {
  Serial.println("Conexao com tago.io estabelecida, assinando topicos...");
  Serial.println("Assinando o topico que atualiza os dados de qualidade da agua....");
  client.subscribe("le_dados_temperatura", [] (const String &payload)  {
    });
  client.subscribe("le_dados_distancia", [] (const String &payload)  {
    });
}