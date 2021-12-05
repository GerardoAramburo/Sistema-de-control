/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDAO;

import java.util.ArrayList;
import modelo.Producto;

/**
 *
 * @author Gerardo
 */
public interface IProductosDao {

    public ArrayList<Producto> getProductos();

    public Producto getProducto(int id);

    public void actualizarProducto(Producto producto, int id);

    public void eliminarProducto(int id);
    
    public void eliminarProductos(ArrayList<Producto> productos);
    
    public void insertarProducto(Producto producto);
}
