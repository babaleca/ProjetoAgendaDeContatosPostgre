/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Barbara
 */
public class ConexaoPostgre {
    private static final String URL  = "jdbc:postgresql://localhost:5432/trabalho";
    private static final String USER = "postgres";
    private static final String PASS = "****";

    public static Connection getConexao() throws Exception {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
