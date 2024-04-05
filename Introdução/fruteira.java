
import javax.swing.JOptionPane;

//Pode comprar frutas até escolher sair do loop.
public class fruteira {
    public fruteira() {
        float preco = 0, pagMo = 0, pagMa = 0, qtdMa = 0, qtdMo = 0, pagT = 0, precotot = 0, valorMa = 0, valorMo = 0;
        String op = "";
        Object[] opcao = { "Morango", "Maçã", "Sair" };

        do {
            Object opcaoEscolhida = JOptionPane.showInputDialog(null, "Escolha uma opção", "Fruteira",
                    JOptionPane.INFORMATION_MESSAGE, null, opcao, opcao[0]);
            String escolha = (String) opcaoEscolhida;
            switch (escolha) {
                case "Morango":
                    JOptionPane.showMessageDialog(null,
                            "Preço Morango\n Até 5 Kilos, R$2,50.\n Mais que 5 kilos, R$ 2,20");
                    qtdMo += Float
                            .parseFloat(JOptionPane.showInputDialog("Informe a quantidade em kilos de Morangos: "));
                    break;
                case "Maçã":
                    JOptionPane.showMessageDialog(null,
                            "Preço Maçã\n Até 5 Kilos, R$1,80.\n Mais que 5 kilos, R$ 1,50");
                    qtdMa += Float.parseFloat(JOptionPane.showInputDialog("Informe a quantidade em kilos de Maçã: "));
                    break;
                case "Sair":
                    // Morango
                    if (qtdMo > 5) {
                        valorMo = (qtdMo * 2.20f);
                        pagMo = valorMo;
                    } else {
                        valorMo = (qtdMo * 2.50f);
                        pagMo += valorMo;
                    }
                    // Maçã
                    if (qtdMa > 5) {
                        valorMa = (qtdMa * 1.50f);
                        pagMa = valorMa;
                    } else {
                        valorMa = (qtdMa * 1.80f);
                        pagMa = valorMa;
                    }

                    pagT = pagMa + pagMo;
                    if (qtdMa + qtdMo > 8 || pagT > 25) {
                        precotot = pagT * 0.9f;
                        pagT = precotot;
                        if (qtdMo > 8 || pagT > 25 && qtdMa == 0) {
                            precotot = pagT * 0.85f;
                            pagT = precotot;
                        } else if (qtdMa > 8 || pagT > 25 && qtdMo == 0) {
                            precotot = pagT * 0.85f;
                            pagT = precotot;
                        } else {
                            precotot = pagT;
                        }
                    }
                    op = "Sair";
                    break;
            }
        } while (!op.equals(
                "Sair"));
        JOptionPane.showMessageDialog(
                null,
                "Total a pagar: " + precotot + ".\n Total de Morangos : " + qtdMo + ".\n Total de Maçãs: " + qtdMa);
        JOptionPane.showMessageDialog(
                null, "Obrigado. Volte sempre!");
    }

    public static void main(String[] args) {
        new fruteira();
    }
}
