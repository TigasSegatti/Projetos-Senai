package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Agendamento {

    private int id;
    private Cliente cliente;
    private Servico servico;
    private float valor;
    private Date data;
    private String Observacao;

    public Agendamento(int id, Cliente cliente, Servico serviço, float valor, String data) {
        this.id = id;
        this.cliente = cliente;
        this.servico = servico;
        this.valor = valor;
        try {
            this.data = new SimpleDateFormat("dd/MM/yyyy").parse(data);
        } catch (ParseException ex) {
            Logger.getLogger(Agendamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String Observacao) {
        this.Observacao = Observacao;
    }
    
}
