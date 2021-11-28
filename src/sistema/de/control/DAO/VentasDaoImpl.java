/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.de.control.DAO;

import sistema.de.control.IDAO.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistema.de.control.modelos.Venta;

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

            String consulta = "SELECT Ventas.ID, Productos.nombre AS Producto, CONCAT(cliente.Nombre,' ',cliente.Apellidos) AS Comprador, Ventas.Fecha, Ventas.Hora, Ventas.Precio, Ventas.TipoPago FROM Ventas INNER JOIN Productos ON Ventas.IDProducto = Productos.ID INNER JOIN cliente ON Ventas.IDCliente = cliente.ID";

            ResultSet respuesta = statement.executeQuery(consulta);

            while (respuesta.next()) {
                int id = respuesta.getInt("ID");
                String producto = respuesta.getString("Producto");
                String comprador = respuesta.getString("Comprador");
                String fecha = respuesta.getString("Fecha");
                String hora = respuesta.getString("Hora");
                float precio = respuesta.getFloat("Precio");
                String tipoPago = respuesta.getString("TipoPago");
                Venta venta = new Venta(id, producto, comprador, fecha, hora, precio, tipoPago);

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
        if (isConectado()) {
            conectarDB();
        }

        try {
            String consulta = "SELECT Ventas.ID,Ventas.Hora,Ventas.Precio,Ventas.TipoPago,cliente.Nombre FROM Ventas INNER JOIN cliente ON Ventas.IDCliente = cliente.ID WHERE Ventas.ID=" + id;

            ResultSet respuesta = statement.executeQuery(consulta);

            if (!respuesta.next()) {
                return null;
            }

            String producto = respuesta.getString("Producto");
            String comprador = respuesta.getString("Comprador");
            String fecha = respuesta.getString("Fecha");
            String hora = respuesta.getString("Hora");
            float precio = respuesta.getFloat("Precio");
            String tipoPago = respuesta.getString("TipoPago");
            Venta venta = new Venta(id, producto, comprador, fecha, hora, precio, tipoPago);
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

    @Override
    public void eliminarVenta(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
