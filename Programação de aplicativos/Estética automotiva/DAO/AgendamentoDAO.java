package DAO;

import Helper.Helper;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.*;
import javax.swing.JOptionPane;


public class AgendamentoDAO {
    
    private Connection conexao;
    ArrayList<Helper> lista = new ArrayList<>();
    
    public ArrayList<Helper> PesquisarAgendamento(){
        PreparedStatement pstm;
        String sql = "Select * from registro";
        conexao = new ClasseConexao().conectorBD();
        ResultSet rs;
        try {
            
            pstm = conexao.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while (rs.next()){
                Helper help = new Helper(); 
                
                
                help.setIdAgendamento(rs.getInt("idAgendamento"));
                help.setNomeCliente(rs.getString("nomeCliente"));
                help.setServico(rs.getString("servico"));
                help.setDataHora(rs.getString("dataHora"));
                help.setValorServico(rs.getString("valorServico"));
                help.setObservacaoServico(rs.getString("observacaoServico"));
                
                lista.add(help);
            }
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, erro);
        }
        return lista;
    }
}
