package modelo;

public class Venta {

    private int id;
    private String producto;
    private String comprador;
    private String fecha;
    private String hora;
    private int cantidad;
    private float precio;
    private String tipoPago;
    private int idProducto;
    private int idCliente;

    public Venta(int id, String producto, String comprador, String fecha, String hora, int cantidad, float precio, String tipoPago, int idProducto, int idCliente) {
        this.id = id;
        this.producto = producto;
        this.comprador = comprador;
        this.fecha = fecha;
        this.hora = hora;
        this.cantidad = cantidad;
        this.precio = precio;
        this.tipoPago = tipoPago;
        this.idProducto = idProducto;
        this.idCliente = idCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
}