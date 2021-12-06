/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.text.SimpleDateFormat;
import java.util.Date;
import DAO.ClientesDaoImpl;
import DAO.ProductosDaoImpl;
import DAO.VentasDaoImpl;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;
import modelo.Venta;
import modelo.Cliente;

/**
 *
 * @author Luis
 */
public class Factura extends javax.swing.JFrame {

    Venta venta;
    Producto producto;
    Cliente cliente;
    int id;
    DefaultTableModel model;
    /**
     * Creates new form AddVenta
     */
    JFrame parent;

    public Factura(JFrame parent, Venta venta, Producto producto, Cliente cliente) {

        this.parent = parent;
        this.venta = venta;
        this.producto = producto;
        this.cliente = cliente;
        this.setAlwaysOnTop(true);

        parent.setEnabled(false);
        initComponents();
        centrarVentana();

        model = (DefaultTableModel) tblProducto.getModel();
        ProductosDaoImpl accesoProductos = new ProductosDaoImpl();
        accesoProductos.actualizarProducto(producto, producto.getId());
        GenerarFactura();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTicket = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblEmpresa = new javax.swing.JLabel();
        lblDatosCliente = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblDomicilio = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblFechaTitulo = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducto = new javax.swing.JTable();
        lblProductoComprado = new javax.swing.JLabel();
        lblMetodoPago = new javax.swing.JLabel();
        lblTipoPago = new javax.swing.JLabel();
        lblGracias = new javax.swing.JLabel();
        lblNumero = new javax.swing.JLabel();
        lblContactar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Factura");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lblTicket.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        lblTicket.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTicket.setText("FACTURA");

        lblEmpresa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblEmpresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEmpresa.setText("Tienda de Videojuegos");

        lblDatosCliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblDatosCliente.setText("Datos cliente");

        lblNombre.setText("Nombre");

        lblDomicilio.setText("Domicilio");

        lblEmail.setText("Email");

        lblFechaTitulo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblFechaTitulo.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblFechaTitulo.setText("Fecha");

        lblFecha.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblFecha.setText("fecha");

        tblProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Cantidad", "Precio", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblProducto);
        if (tblProducto.getColumnModel().getColumnCount() > 0) {
            tblProducto.getColumnModel().getColumn(0).setPreferredWidth(200);
        }

        lblProductoComprado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblProductoComprado.setText("Producto Comprado");

        lblMetodoPago.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblMetodoPago.setText("Método de Pago");

        lblTipoPago.setText("TipoPago");

        lblGracias.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblGracias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblGracias.setText("Gracias  por su Preferencia");

        lblNumero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNumero.setText("+52 622 469 2692");

        lblContactar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblContactar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContactar.setText("Cualquier duda o queja contactar a");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNumero, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblDatosCliente)
                            .addComponent(lblNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblDomicilio, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(lblEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(lblFechaTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblProductoComprado)
                            .addComponent(lblMetodoPago)
                            .addComponent(lblTipoPago, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblGracias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblContactar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEmpresa)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDatosCliente)
                    .addComponent(lblFechaTitulo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(lblFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDomicilio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblProductoComprado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblMetodoPago)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTipoPago)
                .addGap(18, 18, 18)
                .addComponent(lblContactar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNumero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblGracias)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTicket, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTicket)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.setAlwaysOnTop(false);
        parent.setEnabled(true);
        parent.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.setAlwaysOnTop(false);
        parent.setEnabled(true);
        parent.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void centrarVentana() {
        Dimension tamanoVentanaParent = parent.getSize();
        Dimension tamanoVentana = this.getSize();
        Point posicionParent = parent.getLocation();
        this.setLocation(posicionParent.x + (tamanoVentanaParent.width / 2) - (tamanoVentana.width / 2), posicionParent.y + (tamanoVentanaParent.height / 2) - (tamanoVentana.height / 2));
    }

    private void GenerarFactura() {
        VentasDaoImpl accesoVenta = new VentasDaoImpl();
        lblDomicilio.setText(cliente.getDomicilio());
        lblEmail.setText(cliente.getEmail());
        lblFecha.setText(venta.getFecha() + " : " + venta.getHora());
        lblNombre.setText(cliente.getNombre() + " " + cliente.getApellidos());
        lblTipoPago.setText(venta.getTipoPago());
        tblProducto.setModel(model);
        model.addRow(new Object[]{producto.getNombre(), venta.getCantidad(), venta.getPrecio(), venta.getPrecio() * venta.getCantidad()});
    }
    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblContactar;
    private javax.swing.JLabel lblDatosCliente;
    private javax.swing.JLabel lblDomicilio;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmpresa;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblFechaTitulo;
    private javax.swing.JLabel lblGracias;
    private javax.swing.JLabel lblMetodoPago;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNumero;
    private javax.swing.JLabel lblProductoComprado;
    private javax.swing.JLabel lblTicket;
    private javax.swing.JLabel lblTipoPago;
    private javax.swing.JTable tblProducto;
    // End of variables declaration//GEN-END:variables
}
