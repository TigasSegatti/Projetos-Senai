
package DAO;


    import java.sql.*;

public class ClasseConexao {
    public static Connection conectorBD () {
         //metodo responsavel pela conexao com o banco
        Connection conexao = null;

        //"Chama" o Driver
        String driver = "com.mysql.cj.jdbc.Driver";

        //Armazena infos no banco
        String url = "jdbc:mysql://localhost:3306/esteticaautomotiva";
        String user = "root";
        String password = "";

        //Estabelecendo conexao com o banco
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);

            return conexao;

        } catch (Exception e) {
            return null;
        }
    }
}
