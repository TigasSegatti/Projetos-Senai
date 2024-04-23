//Definindo os leds por define
#define ledVermelho_carro 13
#define ledAmarelo_carro 12
#define ledVerde_carro 11
#define ledVermelho_pedestre 3
#define ledVerde_pedestre 2
#define botao_pedestre 4
#define buzzer 5
long contador = 0 ;
void setup() {
  pinMode(ledVermelho_carro, OUTPUT);
  pinMode(ledAmarelo_carro, OUTPUT);
  pinMode(ledVerde_carro, OUTPUT);
  pinMode(ledVermelho_pedestre, OUTPUT);
  pinMode(ledVerde_pedestre, OUTPUT);
  pinMode(botao_pedestre, INPUT);
  pinMode(buzzer, OUTPUT);
}

void loop() {
  digitalWrite(ledVerde_carro, HIGH);
  digitalWrite(ledVermelho_pedestre, HIGH);
  digitalWrite(ledAmarelo_carro, LOW);
  digitalWrite(ledVermelho_carro, LOW);
  digitalWrite(ledVerde_pedestre, LOW);
  
if(millis()>=contador + 70000){
//evitar mais apertos
  delay(2000);

  //Após 90 segundos, ocorre a mundaça de led. Acendendo o Amarelo 
  digitalWrite(ledVerde_carro, LOW);
  digitalWrite(ledAmarelo_carro, HIGH);

  delay(6000); //Duração de 6 segundos para o Amarelo

  digitalWrite(ledVerde_pedestre, HIGH);
  digitalWrite(ledVermelho_pedestre, LOW);
  digitalWrite(ledVerde_carro, LOW);
  digitalWrite(ledAmarelo_carro, LOW);
  digitalWrite(ledVermelho_carro, HIGH);
  delay(80000); 
  //Definindo oito segundos para os pedestres passarem
  //Agora, em 10 segundos, acionará o sinal sonoro
  for(int x = 0;x<4;x++){
    tone(buzzer,329);
    delay(1000);
    noTone(buzzer);
    delay(1500);
  }
  delay(1500);

  digitalWrite(ledVermelho_carro, LOW);//Vermelho para carros apaga
  digitalWrite(ledVerde_pedestre, LOW);//Verde para pedestres apaga
contador= millis(); //Define a variável contador para o tempo atual
}
//Após botão ser pressionado
  else if (digitalRead(botao_pedestre)==LOW){

    delay(20000);

    digitalWrite(ledVerde_carro, LOW);
    digitalWrite(ledAmarelo_carro, HIGH);
    delay(6000);

    digitalWrite(ledVerde_pedestre, HIGH);
    digitalWrite(ledVermelho_pedestre, LOW);
    digitalWrite(ledVerde_carro, LOW);
    digitalWrite(ledAmarelo_carro, LOW);
    digitalWrite(ledVermelho_carro, HIGH);
  	delay(25000);
  for(int x = 0;x<4;x++){
    tone(buzzer,329);
    delay(1000);
    noTone(buzzer);
    delay(1500);
  }
  delay(1500);
     digitalWrite(ledVermelho_carro, LOW);//Vermelho para carros apaga
  digitalWrite(ledVerde_pedestre, LOW);//Verde para pedestres apaga
    contador= millis(); //Define a variável contador para o tempo atual
  }
}