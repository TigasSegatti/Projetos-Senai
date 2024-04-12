
package Model;

public class Servico {
    
    private int id;
    private String observacao;
    private float valor;
    
    public Servico (int id, String observacao, float valor) {
        this.id = id;
        this.observacao = observacao;
        this.valor = valor;
    }
    
    @Override
        public String toString(){
        return getObservacao();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    
}
