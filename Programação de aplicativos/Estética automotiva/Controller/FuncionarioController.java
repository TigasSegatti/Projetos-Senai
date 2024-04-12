
package Controller;

import View.TelaCadastroFuncionario;
import View.TelaMenu;

public class FuncionarioController {
  private final TelaCadastroFuncionario view;   
  //private FuncionarioHelper helper;
  public FuncionarioController( TelaCadastroFuncionario view){
      this.view= view;
      //this.helper= new FuncionarioHelper(view);
  }
   public void FuncionarioVoltaMenu(){
        TelaMenu telaVolta= new TelaMenu();
        telaVolta.setVisible(true);
        this.view.dispose();
    }
}
