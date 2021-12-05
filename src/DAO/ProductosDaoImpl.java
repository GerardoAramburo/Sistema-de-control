/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import IDAO.IProductosDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Producto;

/**
 *
 * @author Gerardo
 */
public class ProductosDaoImpl extends ConexionDAO implements IProductosDao {
    
    @Override
    public ArrayList<Producto> getProductos() {
        if (!isConectado()) {
            conectarDB();
        }
        try {
            ArrayList<Producto> productos = new ArrayList<>();

            String consulta = "SELECT * FROM Productos";

            ResultSet respuesta = statement.executeQuery(consulta);

            while (respuesta.next()) {
                int id = respuesta.getInt("ID");
                String nombre = respuesta.getString("nombre");
                String descripcion = respuesta.getString("descripcion");
                float precio = respuesta.getFloat("precio");
                int cantidad = respuesta.getInt("cantidad");
                Producto producto = new Producto(id, nombre, descripcion, precio, cantidad);

                productos.add(producto);
            }
            conexion.close();
            return productos;
        } catch (SQLException ex) {
            System.out.println("No se pueden obtener los productos de la base de datos");
            Logger.getLogger(ProductosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Producto getProducto(int id) {
        if (!isConectado()) {
            conectarDB();
        }

        try {
            String consulta = "SELECT * FROM Productos WHERE ID=" + id;

            ResultSet respuesta = statement.executeQuery(consulta);

            if (!respuesta.next()) {
                return null;
            }

            String nombre = respuesta.getString("nombre");
            String descripcion = respuesta.getString("descripcion");
            float precio = respuesta.getFloat("precio");
            int cantidad = respuesta.getInt("cantidad");

            Producto producto = new Producto(id, nombre, descripcion, precio, cantidad);
            
            conexion.close();
            return producto;
        } catch (SQLException ex) {
            System.out.println("No se pueden obtener los productos de la base de datos");
            Logger.getLogger(ProductosDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void actualizarProducto(Producto producto, int id) {
        if (!isConectado()) {
            conectarDB();
        }
        
        try {
            String consulta = "UPDATE Productos SET Nombre = '" + producto.getNombre() + "', Descripcion='" + producto.getDescripcion()+"', Precio='"+producto.getPrecio()+"', Cantidad="+producto.getCantidad()+" WHERE ID="+id;
            statement.executeUpdate(consulta);
            System.out.println("Producto actualizado correctamente");
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar el producto");
            ex.printStackTrace();
        }
    }

    @Override
    public void eliminarProducto(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarProductos(ArrayList<Producto> productos) {
        if (productos.isEmpty()) {
            return;
        }
        if (!isConectado()) {
            conectarDB();
        }
        try {
            Producto producto1 = productos.get(0);
            String consulta = "DELETE FROM Productos Where ID=" + producto1.getId();
            productos.remove(0);
            
            for (Producto producto : productos) {
                consulta += " OR ID=" + producto.getId();
            }
            
            
            statement.executeUpdate(consulta);
            conexion.close();
            System.out.println("#Productos eliminados correctamente");
        } catch (SQLException ex) {
            System.out.println("Error al eliminar los productos");
            ex.printStackTrace();
        }
    }

    @Override
    public void insertarProducto(Producto producto) {
        if (!isConectado()) {
            conectarDB();
        }
        
        try {
            String consulta = "INSERT INTO Productos(Nombre, Descripcion, Precio, Cantidad) VALUES('"+ producto.getNombre() +"', '"
                    + producto.getDescripcion() +"',"+ producto.getPrecio() +","+ producto.getCantidad() +")";
            statement.executeUpdate(consulta);
            System.out.println("#Producto insertado correctamente");
            conexion.close();
        } catch (SQLException ex) {
            System.out.println("Error al insertar el producto");
            ex.printStackTrace();
        }
    }
    
    public void insertarProductos(Producto[] productos) {
        if (!isConectado()) {
            conectarDB();
        }
        
        try {
            for (Producto producto: productos) {
                String consulta = "INSERT INTO Productos(Nombre, Descripcion, Precio, Cantidad) VALUES('"+ producto.getNombre() +"', '"
                        + producto.getDescripcion() +"',"+ producto.getPrecio() +","+ producto.getCantidad() +");\n";
                statement.addBatch(consulta);
            }
            statement.executeLargeBatch();
            conexion.close();
            System.out.println("#Productos insertadas correctamente");
            
        } catch (SQLException ex) {
            System.out.println("Error al insertar el producto");
            ex.printStackTrace();
        }
    }
}
