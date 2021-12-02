package IDAO;

import java.util.ArrayList;
import modelo.Cliente;

public interface IClientesDao {

    public ArrayList<Cliente> getClientes();

    public Cliente getCliente(int id);

    public void actualizarCliente(Cliente cliente);

    public void eliminarCliente(int id);
    
    public void insertarCliente(Cliente producto);
}
