package Model;

public class EsteticaAutomotiva {

    public static void main(String[] args) {
        Servico servico = new Servico(1,"barba",30);
        Cliente cliente = new Cliente(1, "NomedoCliente", "rua teste", "99999999");
        System.out.println(cliente.getNome());
        Funcionario tiago = new Funcionario(1, "Tiago", "senha1234");
        System.out.println(tiago);
        Agendamento agendamento= new Agendamento ( 1, cliente,servico,30,"26/07/2018");
        System.out.println(agendamento.getCliente().getNome());
    }

}
