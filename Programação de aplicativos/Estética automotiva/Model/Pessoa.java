package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class Pessoa {
 //classe abstrata oois não pode ser instanciada, assim só pode ser funcionario ou cliente   
    protected int id;
    protected String nome;
    protected char sexo;
    protected Date dataNascimento;
    protected String telefone;
    protected String email;
    protected String cpf;

    public Pessoa(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Pessoa(int id, String nome, char sexo, String dataNascimento, String telefone, String email, String cpf) {
        this.id = id;
        this.nome = nome;
        this.sexo = sexo;
        try {
            this.dataNascimento = new SimpleDateFormat("dd/MM/yyyy").parse(dataNascimento);
        } catch (ParseException ex) {
            Logger.getLogger(Pessoa.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
    }

    
}
