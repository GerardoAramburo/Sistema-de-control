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
import java.util.logging.Level;
import java.util.logging.Logger;
import sistema.de.control.modelos.Cliente;

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
            return clientes;
        } catch (SQLException ex) {
            System.out.println("No se pueden obtener los productos de la base de datos");
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
            System.out.println("No se pueden obtener los productos de la base de datos");
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

}
