
package Controller;

import View.TelaCadastroCliente;
import View.TelaMenu;

public class ClienteController {
 private final TelaCadastroCliente view; 
  
  public ClienteController (TelaCadastroCliente view){
          this.view =view;          
  }
    public void voltarMenu(){
        TelaMenu telaVoltar= new TelaMenu();
        telaVoltar.setVisible(true);
        this.view.dispose(); 
    }
}
