package View;

import Controller.LoginController;
import DAO.ClasseConexao;
import java.awt.event.KeyEvent;
//import com.sun.jdi.connect.spi.Connection;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

/**
 *
 * @author evelin_oliarski
 */
public class TelaLogin extends javax.swing.JFrame {

    private final LoginController controller;
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form Login
     */
    public TelaLogin() {
        initComponents();
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        controller = new LoginController(this);
        conexao = ClasseConexao.conectorBD();
    }

    public void Logar() {

        String sql = "select * from funcionarios where id=? and senha=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, idFuncionarioLogin.getText());
            pst.setString(2, senhaFuncionarioLogin.getText());

            rs = pst.executeQuery();

            if (rs.next()) {

                
                
                //verifica se e admin ou funcionario
                String lvlAcesso = rs.getString(9);

                //define em qual tela vai logar
                if (lvlAcesso.equals("administrador")) {

                    new TelaMenu().setVisible(true);
                    dispose();

                }
                if (lvlAcesso.equals("funcionario")) {
                    new TelaMenu().setVisible(true);
                    dispose();
                }

            } else {
                JOptionPane.showMessageDialog(null, "Usuario ou Senha Invalidos");
            }

        } catch (Exception e) {
            System.out.println(e);

            //JOptionPane.showMessageDialog(null, "Nao Conectado Com o Banco de Dados", "ERRO DE CONEXAO", JOptionPane.ERROR_MESSAGE);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        idFuncionarioLogin = new javax.swing.JTextField();
        confirmarLogin = new javax.swing.JButton();
        senhaFuncionarioLogin = new javax.swing.JPasswordField();
        fundoLogin = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        idFuncionarioLogin.setBackground(new java.awt.Color(204, 204, 204));
        idFuncionarioLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idFuncionarioLoginActionPerformed(evt);
            }
        });
        idFuncionarioLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                idFuncionarioLoginKeyPressed(evt);
            }
        });
        getContentPane().add(idFuncionarioLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 512, 270, 40));

        confirmarLogin.setBackground(new java.awt.Color(0, 0, 0));
        confirmarLogin.setFont(new java.awt.Font("Artifakt Element Light", 0, 24)); // NOI18N
        confirmarLogin.setForeground(new java.awt.Color(255, 255, 255));
        confirmarLogin.setText("Confirmar");
        confirmarLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarLoginActionPerformed(evt);
            }
        });
        getContentPane().add(confirmarLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 730, 270, 30));

        senhaFuncionarioLogin.setBackground(new java.awt.Color(204, 204, 204));
        senhaFuncionarioLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                senhaFuncionarioLoginActionPerformed(evt);
            }
        });
        senhaFuncionarioLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                senhaFuncionarioLoginKeyPressed(evt);
            }
        });
        getContentPane().add(senhaFuncionarioLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 620, 270, 30));

        fundoLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Imagens/LoginTela.png"))); // NOI18N
        getContentPane().add(fundoLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -4, 400, 900));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void idFuncionarioLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idFuncionarioLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idFuncionarioLoginActionPerformed

    private void confirmarLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarLoginActionPerformed
        Logar();
    }//GEN-LAST:event_confirmarLoginActionPerformed

    private void idFuncionarioLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idFuncionarioLoginKeyPressed

    }//GEN-LAST:event_idFuncionarioLoginKeyPressed

    private void senhaFuncionarioLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_senhaFuncionarioLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_senhaFuncionarioLoginActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int opcao = JOptionPane.showConfirmDialog(null, "Deseja sair do sistema?", "Atenção!", JOptionPane.YES_OPTION);
        if (opcao == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Sistema finalizado!");
            System.exit(0);
        }
    }//GEN-LAST:event_formWindowClosing

    private void senhaFuncionarioLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_senhaFuncionarioLoginKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            Logar();
        }
    }//GEN-LAST:event_senhaFuncionarioLoginKeyPressed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton confirmarLogin;
    private javax.swing.JLabel fundoLogin;
    private javax.swing.JTextField idFuncionarioLogin;
    private javax.swing.JPasswordField senhaFuncionarioLogin;
    // End of variables declaration//GEN-END:variables

//Aqui pegamos os getters e setters para usuario e senha 
    public JTextField getIdFuncionarioLogin() {
        return idFuncionarioLogin;
    }

    public void setIdFuncionarioLogin(JTextField idFuncionarioLogin) {
        this.idFuncionarioLogin = idFuncionarioLogin;
    }

    public JTextField getSenhaFuncionarioLogin() {
        return senhaFuncionarioLogin;
    }

    public void setSenhaFuncionarioLogin(JTextField senhaFuncionarioLogin) {
        this.senhaFuncionarioLogin = (JPasswordField) senhaFuncionarioLogin;
    }
}
