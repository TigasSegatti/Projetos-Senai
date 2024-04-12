package Model;

import java.util.Date;

public class Funcionario extends Pessoa {

    protected String nivelAcesso;
    protected String senha;

    public Funcionario(int id, String nome, String senha) {
        super(id, nome);
        this.senha = senha;
    }

    public Funcionario(String nivelAcesso, String senha, int id, String nome,String email,String cpf,String telefone, char sexo, String dataNascimento ) {
        super(id, nome, sexo, dataNascimento, telefone, email, cpf);
        this.nivelAcesso = nivelAcesso;
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
