/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema.de.control.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Daniel
 */
public class ConexionDAO {
    
    private String dbHost = "jdbc:mysql://sql5.freemysqlhosting.net/";
    private String dbNombre = "sql5453807";
    private String dbUsuario = "sql5453807";
    private String dbContra = "9ZqJVmpiKt";
    private boolean conectado = false;
    public Connection conexion;
    public Statement statement;
    
    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }
    
    public ConexionDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void conectarDB() {
        try {
            conexion = DriverManager.getConnection(dbHost + dbNombre, dbUsuario, dbContra);
            statement = conexion.createStatement();
            conectado = true;
            System.out.println("#Conexion establecida con la base de datos");
        } catch (SQLException ex) {
            System.out.println("Error al conectar con la base de datos");
            ex.printStackTrace();
        }
    }
}
