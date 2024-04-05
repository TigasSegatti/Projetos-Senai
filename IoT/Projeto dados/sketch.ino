#include <Arduino.h> //Inclusão das bibliotecas
#include <Wire.h>
#include <LiquidCrystal_I2C.h>

LiquidCrystal_I2C lcd(0x27, 16, 2); // Endereço I2C e tamanho do display
const int buttonPin = 4; // Em que entrada o botão está inserido
bool pressionado; //Variavel para verificar se o botão está pressionado

void setup() {
  Serial.begin(9600);
  pinMode(buttonPin, INPUT_PULLUP); // Configura o pino do botão como entrada com pull-up interno
  lcd.init();                      // Inicializa o LCD
  lcd.backlight();                 // Liga a luz de fundo do LCD
  randomSeed(analogRead(0));      // Inicializa a semente para a geração de números aleatórios
}

void loop() {
  pressionado = false; // Reseta a variável para indicar se o botão foi pressionado

  if (digitalRead(buttonPin) == HIGH) { //Comando de SE para quando o botão não estiver pressionado
    lcd.setCursor(0, 0);  //Seta para aparecer emcima
    lcd.print("  Clique para");//O que deve ser escrito isso na parte de cima
    lcd.setCursor(0, 1); //Seta para aparecer em baixo
    lcd.print("     jogar");//O que deve ser escrito isso na parte de baixo
  }

  if (digitalRead(buttonPin) == LOW) { // Verificar se o botão foi pressionado
    lcd.clear();
    long num1 = random(1, 7); //Número aleatório para soma
    int num2 = random(1, 7); //Número aleatório para soma
    int soma = num1 + num2; //Soma de ambos os números

    lcd.clear(); //Limpa a tela
    lcd.setCursor(0, 0); //Seta para ser escrito em cima
    lcd.print("Soma:"); //O que deve ser escrito
    lcd.print(num1);
    lcd.print("+");
    lcd.print(num2);
    lcd.print("=");
    lcd.print(soma);
    delay(2000); //Delay para conseguir ler o que está escrito

    if (soma == 7 || soma == 11) { // Verifica se a soma vai dar 7 ou 11
      lcd.setCursor(0, 1);
      lcd.print("Vencedor!"); // caso dê, será vencedor
      num1 = 0; //Redefinindo váriaveis para zero afim de evitar erros
      num2 = 0;
      soma = 0;
    } else if (soma == 2 || soma == 3 || soma == 12) { //Verifica se a soma da 2 ou 3 ou 12
      lcd.setCursor(0, 1);
      lcd.print("Perdedor!"); // caso dê, será perdedor
    } else {
      while (true) { 
        if (digitalRead(buttonPin) == HIGH) { //Após ser feito o armazenamento do ponto, a tela fica esperando ser apertada novamente
          lcd.setCursor(0, 0);
          lcd.print(" Clique de novo para");
          lcd.setCursor(0, 1);
          lcd.print("     jogar");
          delay(1000);
        }
        if (digitalRead(buttonPin) == LOW && !pressionado) { // Após o jogador apertar o botão, a variavel fica positiva, assim ocorre o segundo lançamento dos dados
          pressionado = true;
        }
        if (pressionado == true && digitalRead(buttonPin) == HIGH ) { 
          lcd.clear(); //Verifica se a variável esta positiva para prosseguir e se o botão não está apertadado para multiplas leituras 
          long novoNum1 = random(1, 7); // Novo Número aleatório para soma
          long novoNum2 = random(1, 7); // Novo Número aleatório para soma
          long novaSoma = novoNum1 + novoNum2; //Nova soma
          lcd.clear();
          lcd.setCursor(0, 1);
          lcd.print("Ponto: ");
          lcd.print(soma); ////Define qual o ponto para ser guardado
          lcd.setCursor(0, 0);
          lcd.print("Sorteio: "); //O que deve ser escrito na parte de cima
          lcd.print(novoNum1);
          lcd.print("+");
          lcd.print(novoNum2);
          lcd.print("=");
          lcd.print(novaSoma);
          delay(1000);
          pressionado = false; //Após a soma, se torna negativa novamente, para que repita o processo acima se nescessário
          if (novaSoma == soma) { //Verifica se a soma atual é igual a soma antiga
            lcd.clear();
            lcd.setCursor(0, 0); //Parte de cima escrita
            lcd.print("    Voce");
            lcd.setCursor(0, 1); //Parte debaixo escrita
            lcd.print("    Venceu! :)"); //Caso seja, você venceu
            novaSoma = 0;
            delay(1000);
            lcd.clear();
            break; //Comando de quebra após a vitória
          } else if ( novaSoma == 7) { //Verifica se a nova soma é igual a 7
            lcd.clear();
            lcd.setCursor(0, 0);
            lcd.print("    Voce");
            lcd.setCursor(0, 1);
            lcd.print("    Perdeu! :("); //Caso seja, será perdedor
            novaSoma = 0;
            delay(1000);
            lcd.clear();
            break; //Comando de quebra após a derrota
          }
        }
      }
    }
    delay(1500);
  }
}