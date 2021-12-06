/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import IDAO.IVentasDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Venta;

/**
 *
 * @author Gerardo
 */
public class VentasDaoImpl extends ConexionDAO implements IVentasDao {

    @Override
    public ArrayList<Venta> getVentas() {
        if (!isConectado()) {
            conectarDB();
        }
        try {
            ArrayList<Venta> ventas = new ArrayList<>();

            String consulta = "SELECT Ventas.ID, Productos.nombre AS Producto, CONCAT(cliente.Nombre,' ',cliente.Apellidos) AS Comprador, Ventas.Fecha, Ventas.Hora, Ventas.Cantidad, Ventas.Precio, Ventas.TipoPago, Ventas.IDProducto, Ventas.IDCliente FROM Ventas INNER JOIN Productos ON Ventas.IDProducto = Productos.ID INNER JOIN cliente ON Ventas.IDCliente = cliente.ID";

            ResultSet respuesta = statement.executeQuery(consulta);

            while (respuesta.next()) {
                int id = respuesta.getInt("ID");
                String producto = respuesta.getString("Producto");
                String comprador = respuesta.getString("Comprador");
                String fecha = respuesta.getString("Fecha");
                String hora = respuesta.getString("Hora");
                int cantidad = respuesta.getInt("Cantidad");
                float precio = respuesta.getFloat("Precio");
                String tipoPago = respuesta.getString("TipoPago");
                int idProducto = respuesta.getInt("IDProducto");
                int idCliente = respuesta.getInt("IDCliente");
                Venta venta = new Venta(id, producto, comprador, fecha, hora, cantidad, precio, tipoPago, idProducto, idCliente);
                ventas.add(venta);
            }
            conexion.close();
            return ventas;
        } catch (SQLException ex) {
            System.out.println("No se pueden obtener los productos de la base de datos");
            Logger.getLogger(VentasDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Venta getVenta(int id) {
        if (!isConectado()) {
            conectarDB();
        }

        try {
            String consulta = "SELECT Ventas.ID, Productos.nombre AS Producto, CONCAT(cliente.Nombre,' ',cliente.Apellidos) AS Comprador, Ventas.Fecha, Ventas.Hora, Ventas.Cantidad, Ventas.Precio, Ventas.TipoPago, Ventas.IDProducto, Ventas.IDCliente FROM Ventas INNER JOIN Productos ON Ventas.IDProducto = Productos.ID INNER JOIN cliente ON Ventas.IDCliente = cliente.ID WHERE Ventas.ID=" + id;
 
            ResultSet respuesta = statement.executeQuery(consulta);

            if (!respuesta.next()) {
                return null;
            }

                String producto = respuesta.getString("Producto");
                String comprador = respuesta.getString("Comprador");
                String fecha = respuesta.getString("Fecha");
                String hora = respuesta.getString("Hora");
                int cantidad = respuesta.getInt("Cantidad");
                float precio = respuesta.getFloat("Precio");
                String tipoPago = respuesta.getString("TipoPago");
                int idProducto = respuesta.getInt("IDProducto");
                int idCliente = respuesta.getInt("IDCliente");
            Venta venta = new Venta(id, producto ,comprador, fecha, hora, cantidad, precio, tipoPago, idProducto, idCliente);
            conexion.close();
            return venta;
        } catch (SQLException ex) {
            System.out.println("No se pueden obtener los productos de la base de datos");
            Logger.getLogger(VentasDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void actualizarVenta(Venta venta) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void insertarVenta(Venta venta,int idProducto,int idCliente) {
       if (!isConectado()) {
            conectarDB();
        }
        
        try {
            String consulta = "INSERT INTO Ventas(Fecha, Hora, Cantidad, Precio, TipoPago, IDProducto, IDCliente) VALUES('"+venta.getFecha()+"', '"+venta.getHora()+"',"+venta.getPrecio()+", "+venta.getCantidad()+", '"+venta.getTipoPago()+"',"+idProducto+","+idCliente+")";
            statement.executeUpdate(consulta);
            conexion.close();
            System.out.println("Venta insertada correctamente");
        } catch (SQLException ex) {
            System.out.println("Error al insertar la Venta");
            ex.printStackTrace();
        }
    }
    @Override
    public void eliminarVenta(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void eliminarVentas(ArrayList<Venta> ventas) {
        if (ventas.isEmpty()) {
            return;
        }
        if (!isConectado()) {
            conectarDB();
        }
        try {
            Venta producto1 = ventas.get(0);
            String consulta = "DELETE FROM Ventas Where ID=" + producto1.getId();
            ventas.remove(0);
            
            for (Venta producto : ventas) {
                consulta += " OR ID=" + producto.getId();
            }
            
            statement.executeUpdate(consulta);
            conexion.close();
            System.out.println("#Ventas eliminados correctamente");
        } catch (SQLException ex) {
            System.out.println("Error al eliminar los productos");
            ex.printStackTrace();
        }
    }
    
    public void insertarVentas(Venta[] ventas) {
        if (!isConectado()) {
            conectarDB();
        }
        
        try {
            for (Venta venta: ventas) {
                String consulta = "INSERT INTO Ventas(Fecha, Hora, Cantidad, Precio, TipoPago, IDProducto, IDCliente) VALUES('"+venta.getFecha()+"', '"+venta.getHora()+"',"+venta.getCantidad()+", "+venta.getPrecio()+", '"+venta.getTipoPago()+"',"+venta.getIdProducto()+","+venta.getIdCliente()+")";

                statement.addBatch(consulta);
            }
            statement.executeLargeBatch();
            conexion.close();

            System.out.println("#Ventas insertadas correctamente");

        } catch (SQLException ex) {
            System.out.println("Error al insertar el producto");
            ex.printStackTrace();
        }
    }
}
