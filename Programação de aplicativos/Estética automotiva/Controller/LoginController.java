
package Controller;

import Helper.LoginHelper;
import Model.Funcionario;
import View.TelaLogin;
import View.TelaMenu;

public class LoginController {
    
    private final TelaLogin view;
    private LoginHelper helper;
    
    public LoginController (TelaLogin view){
        this.view = view;
        this.helper= new LoginHelper(view);
    }
    
    public void entrarNoSistema(){ 
        Funcionario funcioanrio = helper.obterModelo();//chamamos o help e pedimos que pegue o usuario da view.
         TelaMenu telamenuzinho= new TelaMenu();
        telamenuzinho.setVisible(true);
        this.view.dispose();
       
        
    }
}
