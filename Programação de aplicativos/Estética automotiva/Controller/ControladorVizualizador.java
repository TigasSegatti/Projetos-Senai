package Controller;

import DAO.ClasseConexao;
import View.TelaServiços;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.*;
import javax.swing.JOptionPane;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
public class ControladorVizualizador {
    
    private Connection conexao;
    
    private final TelaServiços view;
    
    public ControladorVizualizador(TelaServiços view){
        this.view=view;
    } 
    public void preencherTabela(){
        
        conexao = new ClasseConexao().conectorBD();
        DefaultTableModel tablemodel = (DefaultTableModel) view.getTabela().getModel();
        tablemodel.setNumRows(0);
        ResultSet rs =null;
        PreparedStatement pst = null;
        try {
            pst =  conexao.prepareStatement("SELECT * FROM agendamentos ORDER BY idagendamentos");
            rs =  pst.executeQuery();
            
            if(rs.next()){
            tablemodel.addRow(new Object[]{
                
            });
            }
        } catch (SQLException ex) {
            Logger.Level.valueOf(ControladorVizualizador.class.getName()).getSeverity();
        }
        
    }
}
