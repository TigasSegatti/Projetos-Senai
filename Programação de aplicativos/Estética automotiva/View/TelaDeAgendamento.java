
package View;

import DAO.ClasseConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class TelaDeAgendamento extends javax.swing.JFrame {
Connection conexao = null;
PreparedStatement pst = null;
ResultSet rs = null;

    public TelaDeAgendamento() {
        initComponents();
}
    
    private void cadastrarClientes() {
        String sql = "insert into registro(nomeCliente,servico,dataHora,valorServico,observacaoServico) values(?,?,?,?,?)";
        conexao = new ClasseConexao().conectorBD();
        
        try {
            //prepara e pega os dados do cadastro
            pst = conexao.prepareStatement(sql);
            pst.setString(1, clienteAgendamento.getText());//nome
            pst.setString(2, clienteValor.getSelectedItem().toString());//sexo
            
            //converter data dd/MM/yyyy para yyyy/MM/dd
            String dia = dataEhoraAgendamento.getText().substring(0, 2);
            String mes = dataEhoraAgendamento.getText().substring(3, 5);
            String ano = dataEhoraAgendamento.getText().substring(6);
            String hora = dataEhoraAgendamento.getText().substring(7);
            String dataNascConv = ano + "-" + mes + "-" + dia + " - "+hora;

            pst.setString(3, dataNascConv);//dataNascimento
            pst.setString(4, valorAgendamento.getText());//endereco
            pst.setString(5, observacaoAgendamento.getText());//email

            //validacao dos campos obrigatorios
            if (clienteAgendamento.getText().isEmpty() || clienteValor.getSelectedItem().toString().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha Todos os Campos Obrigatorios");
            } else {

                //atualiza a tabela cliente com os dados do formulario
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Serviço Cadastrado Com Sucesso");

                    clienteAgendamento.setText(null);//nome
                    clienteValor.setSelectedItem(null);//cpf
                    dataEhoraAgendamento.setText(null);//dataNascimento
                    valorAgendamento.setText(null);//endereco
                    observacaoAgendamento.setText(null);//email
                    TelaServiços tabela = new TelaServiços();
                    tabela.setVisible(true);
                    this.dispose();
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dataEhoraAgendamento = new javax.swing.JTextField();
        valorAgendamento = new javax.swing.JTextField();
        confirmarAgendamento = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        observacaoAgendamento = new javax.swing.JTextArea();
        clienteValor = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        clienteAgendamento = new javax.swing.JTextField();
        fundoAgendamento = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dataEhoraAgendamento.setBackground(new java.awt.Color(204, 204, 204));
        getContentPane().add(dataEhoraAgendamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, 210, 30));

        valorAgendamento.setBackground(new java.awt.Color(204, 204, 204));
        getContentPane().add(valorAgendamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, 210, 30));

        confirmarAgendamento.setBackground(new java.awt.Color(0, 0, 0));
        confirmarAgendamento.setFont(new java.awt.Font("Artifakt Element Light", 0, 24)); // NOI18N
        confirmarAgendamento.setForeground(new java.awt.Color(255, 255, 255));
        confirmarAgendamento.setText("Confirmar");
        confirmarAgendamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmarAgendamentoActionPerformed(evt);
            }
        });
        getContentPane().add(confirmarAgendamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 800, 270, 40));

        observacaoAgendamento.setBackground(new java.awt.Color(204, 204, 204));
        observacaoAgendamento.setColumns(20);
        observacaoAgendamento.setRows(5);
        jScrollPane1.setViewportView(observacaoAgendamento);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 490, 230, 130));

        clienteValor.setBackground(new java.awt.Color(204, 204, 204));
        clienteValor.setForeground(new java.awt.Color(204, 204, 204));
        clienteValor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-------", "Lavagem Completa", "Limpeza Interna", "Troca de Pneus", "Limpeza Externa", "Polimento c/ Cera" }));
        clienteValor.setToolTipText("");
        clienteValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteValorActionPerformed(evt);
            }
        });
        getContentPane().add(clienteValor, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 220, 20));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Imagens/botão voltar.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 50, 50));
        getContentPane().add(clienteAgendamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 160, 230, 20));

        fundoAgendamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/Imagens/agendamentoNova.jpg"))); // NOI18N
        fundoAgendamento.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(fundoAgendamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void clienteValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienteValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clienteValorActionPerformed

    private void confirmarAgendamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmarAgendamentoActionPerformed
        this.cadastrarClientes();
    }//GEN-LAST:event_confirmarAgendamentoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        TelaMenu menu = new TelaMenu();
        menu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaDeAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaDeAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaDeAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaDeAgendamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaDeAgendamento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField clienteAgendamento;
    private javax.swing.JComboBox<String> clienteValor;
    private javax.swing.JButton confirmarAgendamento;
    private javax.swing.JTextField dataEhoraAgendamento;
    private javax.swing.JLabel fundoAgendamento;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea observacaoAgendamento;
    private javax.swing.JTextField valorAgendamento;
    // End of variables declaration//GEN-END:variables
}
