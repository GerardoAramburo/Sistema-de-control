/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.de.control.DAO;

import sistema.de.control.IDAO.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistema.de.control.modelos.Producto;
/**
 *
 * @author Gerardo
 */
public class ProductosDaoImpl implements IProductosDao{

    private String dbHost = "jdbc:mysql://sql5.freemysqlhosting.net/";
    private String dbNombre = "sql5453807";
    private String dbUsuario = "sql5453807";
    private String dbContra = "9ZqJVmpiKt";
    private Connection conexion;
    private boolean conectado;
    private Statement statement;
    
    public ProductosDaoImpl() {
        try
        {
          Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        conectarDB();
    }
    
    private void conectarDB() {
        try {
            conexion = DriverManager.getConnection(dbHost + dbNombre, dbUsuario, dbContra);
            statement = conexion.createStatement();
            System.out.println("#Conexion establecida con la base de datos");
        } catch (SQLException ex) {
            System.out.println("Error al conectar con la base de datos");
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Producto> getProductos() {
        try {
            ArrayList<Producto> productos = new ArrayList<>();
            
            String consulta = "SELECT * FROM Productos";
            
            ResultSet respuesta = statement.executeQuery(consulta);
            
            while(respuesta.next()) {
                int id = respuesta.getInt("ID");
                String nombre = respuesta.getString("nombre");
                String descripcion = respuesta.getString("descripcion");
                int cantidad = respuesta.getInt("cantidad");
                Producto producto = new Producto(nombre, descripcion, cantidad);
                
                productos.add(producto);
            }
            return productos;
        } catch (SQLException ex) {
            System.out.println("No se pueden obtener los productos de la base de datos");
            Logger.getLogger(ProductosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Producto getProducto(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizarProducto(Producto producto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarProducto(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
