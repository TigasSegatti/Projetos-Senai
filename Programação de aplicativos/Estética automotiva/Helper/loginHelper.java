package Helper;
//importações

import Model.Funcionario;
import View.TelaLogin;

public class LoginHelper {

    private final TelaLogin view; //propriedade tipo view

    public LoginHelper(TelaLogin view) {
        this.view = view;

    }

    //metodo que retorna um usuario em formato de funcionário.
    public Funcionario obterModelo() {
        String nome = view.getIdFuncionarioLogin().getText();
        String senha = view.getSenhaFuncionarioLogin().getText();
        Funcionario modelo = new Funcionario(0, nome, senha);
        return modelo; //retorna
    }

    //funcão que deve receber e escreve tipo Funcionário.
    public void setModelo(Funcionario modelo) {
        String nome = modelo.getNome(); //coleta nome de usuario inserido
        String senha = modelo.getSenha(); //coleta a senha inserida
        view.getIdFuncionarioLogin().setText(nome); //Ele recebe um parametro e 'seta' no lugar corrretamente 
        view.getSenhaFuncionarioLogin().setText(senha);
    }
    //função que limpa a tela
    public void CleanScreen() {
        view.getIdFuncionarioLogin().setText("");
        view.getSenhaFuncionarioLogin().setText("");
    }
}
