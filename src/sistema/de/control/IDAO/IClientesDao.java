package sistema.de.control.IDAO;

import java.util.ArrayList;
import sistema.de.control.modelos.Cliente;

public interface IClientesDao {

    public ArrayList<Cliente> getClientes();

    public Cliente getCliente(int id);

    public void actualizarCliente(Cliente cliente);

    public void eliminarCliente(int id);
}
