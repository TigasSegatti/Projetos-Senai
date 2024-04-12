package Model;

import java.util.Date;

public class Cliente extends Pessoa {
    private String endereco;
    private String cep;

    public Cliente( String nome, String endereco, int id, String cep, String dataNascimento, String telefone, String email, char sexo, String cpf) {
        super(id, nome, sexo, dataNascimento, telefone, email, cpf);
        this.endereco = endereco;
        this.cep = cep;
    }

    public Cliente(int id, String nome, String endereco, String cep) {
        super(id, nome);
        this.endereco = endereco;
        this.cep = cep;
    }

    
    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }



}