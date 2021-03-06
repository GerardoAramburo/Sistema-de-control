/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import IDAO.IClientesDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cliente;

/**
 *
 * @author Gerardo
 */
public class ClientesDaoImpl extends ConexionDAO implements IClientesDao {

    @Override
    public ArrayList<Cliente> getClientes() {
        if (!isConectado()) {
            conectarDB();
        }
        try {
            ArrayList<Cliente> clientes = new ArrayList<>();

            String consulta = "SELECT * FROM cliente";

            ResultSet respuesta = statement.executeQuery(consulta);

            while (respuesta.next()) {
                int id = respuesta.getInt("ID");
                String nombre = respuesta.getString("Nombre");
                String apellidos = respuesta.getString("Apellidos");
                String domicilio = respuesta.getString("Domicilio");
                String email = respuesta.getString("Email");
                Cliente cliente = new Cliente(id, nombre, apellidos, domicilio, email);

                clientes.add(cliente);
            }
            conexion.close();
            return clientes;
        } catch (SQLException ex) {
            System.out.println("No se pueden obtener la informacionde los clientes de la base de datos");
            Logger.getLogger(ClientesDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public Cliente getCliente(int id) {
        if (!isConectado()) {
            conectarDB();
        }

        try {
            String consulta = "SELECT * FROM cliente WHERE ID=" + id;

            ResultSet respuesta = statement.executeQuery(consulta);

            if (!respuesta.next()) {
                return null;
            }

            String nombre = respuesta.getString("Nombre");
            String apellidos = respuesta.getString("Apellidos");
            String domicilio = respuesta.getString("Domicilio");
            String email = respuesta.getString("Email");
            Cliente cliente = new Cliente(id, nombre, apellidos, domicilio, email);

            return cliente;
        } catch (SQLException ex) {
            System.out.println("No se pueden obtener la informacion del cliente la base de datos");
            Logger.getLogger(ClientesDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
       
    @Override
    public void actualizarCliente(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarCliente(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
    @Override
    public void insertarCliente(Cliente cliente) {
        if (!isConectado()) {
            conectarDB();
        }
        
        try {
            String consulta = "INSERT INTO cliente(Nombre, Apellidos, Domicilio, Email) VALUES('"+ cliente.getNombre() +"', '"
                    + cliente.getApellidos() +"',"+ "'" + cliente.getDomicilio() + "'" + ","+ "'" +cliente.getEmail() +"')";
            statement.executeUpdate(consulta);
            conexion.close();
            System.out.println("#Cliente insertado correctamente");
        } catch (SQLException ex) {
            System.out.println("Error al insertar el cliente");
            ex.printStackTrace();
        }
    }
    
    public void insertarClientes(Cliente[] clientes) {
        if (!isConectado()) {
            conectarDB();
        }
        
        try {
            for (Cliente cliente: clientes) {
                String consulta = "INSERT INTO cliente(Nombre, Apellidos, Domicilio, Email) VALUES('"+ cliente.getNombre() +"', '"
                    + cliente.getApellidos() +"',"+ "'" + cliente.getDomicilio() + "'" + ","+ "'" +cliente.getEmail() +"')";
                statement.addBatch(consulta);
            }
            statement.executeLargeBatch();
            conexion.close();
            System.out.println("#Clientes insertadas correctamente");

        } catch (SQLException ex) {
            System.out.println("Error al insertar el cliente");
            ex.printStackTrace();
        }
    }
    
    public void eliminarClientes(ArrayList<Cliente> clientes) {
        if (clientes.isEmpty()) {
            return;
        }
        if (!isConectado()) {
            conectarDB();
        }
        try {
            Cliente producto1 = clientes.get(0);
            String consulta = "DELETE FROM cliente Where ID=" + producto1.getId();
            clientes.remove(0);
            
            for (Cliente cliente : clientes) {
                consulta += " OR ID=" + cliente.getId();
            }
            
            
            statement.executeUpdate(consulta);
            conexion.close();
            System.out.println("#Clientes eliminados correctamente");
        } catch (SQLException ex) {
            System.out.println("Error al eliminar los productos");
            ex.printStackTrace();
        }
    }
}

