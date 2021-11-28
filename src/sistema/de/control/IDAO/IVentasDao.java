/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.de.control.IDAO;

import java.util.ArrayList;
import sistema.de.control.modelos.Venta;

/**
 *
 * @author Gerardo
 */
public interface IVentasDao {

    public ArrayList<Venta> getVentas();

    public Venta getVenta(int id);

    public void actualizarVenta(Venta venta);

    public void eliminarVenta(int id);
}
