package Controller;

import View.TelaCadastroCliente;
import View.TelaCadastroFuncionario;
import View.TelaDeAgendamento;
import View.TelaMenu;
import View.TelaServiços;

public class MenuController {

    private final TelaMenu view;

    public MenuController(TelaMenu view) {
        this.view = view;
    }

    public void irParaAgendamento() {
        TelaDeAgendamento telaAgendamento = new TelaDeAgendamento();
        telaAgendamento.setVisible(true);
        this.view.dispose();
    }

    public void irParaCadastroCliente() {
        TelaCadastroCliente telaCliente = new TelaCadastroCliente();
        telaCliente.setVisible(true);
        this.view.dispose();
    }

    public void IrParaCadastroFuncionario() {
        TelaCadastroFuncionario telaFuncionario = new TelaCadastroFuncionario();
        telaFuncionario.setVisible(true);
         this.view.dispose();
    }

    public void IrParaServicos() {
        TelaServiços telaServico = new TelaServiços();
        telaServico.setVisible(true);
        this.view.dispose();
    }
    
}
