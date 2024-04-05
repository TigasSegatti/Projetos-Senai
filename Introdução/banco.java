import javax.swing.JOptionPane;

public class banco {
    public banco() {
        String nome = JOptionPane.showInputDialog("Olá \nInforme seu nome: ");
        int conta = Integer.parseInt(JOptionPane.showInputDialog("Número da conta: "));
        float depositoInicial = Float.parseFloat(JOptionPane.showInputDialog("Depósito Inicial: "));
        float saldo, saque = 0;
        saldo = depositoInicial;
        String op = "";
        Object[] opcao = { "Deposito", "Saque", "Sair", "Extrato" };
        do {
            Object opcaoEscolhida = JOptionPane.showInputDialog(null, "Escolha uma opção", "Banco",
                    JOptionPane.INFORMATION_MESSAGE, null, opcao, opcao[0]);
            String escolha = (String) opcaoEscolhida;

            switch (escolha) {
                case "Deposito":
                    saldo += Float.parseFloat(JOptionPane.showInputDialog("Digite um valor de depósito: "));
                    break;
                case "Saque":
                    saque = Float.parseFloat(JOptionPane.showInputDialog("Valor do saque: "));
                    if (saque > saldo) {
                        saque = 0;
                        JOptionPane.showMessageDialog(null, "Não tem saldo suficiente!");
                    } else {
                        saldo -= saque;
                    }
                    break;
                case "Extrato":
                    JOptionPane.showMessageDialog(null, "-----New Bank-----"
                            + "\n----------------------------"
                            + "\nConta: " + conta
                            + "\nNome Cliente: " + nome
                            + "\nDepósito Inicial: " + depositoInicial
                            + "\nDepósito Atual: " + saldo
                            + "\nValor do saque " + saque);
                    break;

                case "Sair":

                    JOptionPane.showMessageDialog(null, "Obrigado " + nome + ". Volte sempre!");
                    op = "Sair";
                    break;
            }
        } while (!op.equals("Sair"));
        System.exit(0);
    }

    public static void main(String[] args) {
        new banco();
    }
}