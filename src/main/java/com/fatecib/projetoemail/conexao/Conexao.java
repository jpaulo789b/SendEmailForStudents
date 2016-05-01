/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fatecib.projetoemail.conexao;
import java.sql.Connection; // biblioteca de conexao
import java.sql.DriverManager; // gerenciador do drive
import java.sql.SQLException;// validacao de erros do sql
/**
 *
 * @author eugeniojulio
 */
public class Conexao {
    private static Connection conexao = null;
    public static Connection getConexao(){
        if (conexao != null){
            return conexao;
        }
        else{
            try{
                // Comando do MYSQL
                // O drive do banco deve ser anexado as bibliotecas do projeto
                String driver = "com.mysql.jdbc.Driver";
                String url = "jdbc:mysql://localhost/mail";
                String user = "root";
                String password = "123456";
                
                Class.forName(driver);
                conexao = DriverManager.getConnection(url, user, password);
            }
            catch(ClassNotFoundException e){
                e.printStackTrace();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            return conexao;
        }
    }      
}
