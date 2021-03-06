/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import DAO.ProductosDaoImpl;
import DAO.ClientesDaoImpl;
import DAO.VentasDaoImpl;
import modelo.Cliente;
import modelo.Venta;
import modelo.Producto;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.google.gson.Gson;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import modelo.Seccion;

/**
 *
 * @author Gerardo
 */
public class MenuPrincipal extends javax.swing.JFrame {

    Seccion seccionSeleccionada = Seccion.PRODUCTOS;
    ArrayList<Producto> productos = new ArrayList<>();
    ArrayList<Cliente> clientes = new ArrayList<>();
    ArrayList<Venta> ventas = new ArrayList<>();
    String tema = "Claro";
    String[] columnasProducto = {"Nombre", "Descripción", "Precio", "Cantidad"};
    String[] columnasCliente = {"Nombre", "Apellidos", "Domicilio", "Email"};
    String[] columnasVenta = {"Producto", "Comprador", "Fecha", "Hora", "Cantidad", "Precio", "Tipo de Pago"};
    VentasDaoImpl accesoVentas;
    ProductosDaoImpl accesoProductos;
    ClientesDaoImpl accesoClientes;

    DefaultTableModel model;

    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal(String tema) {
        this.tema = tema;
        initComponents();
        temaCb.setSelectedItem(tema);

        configurarTabla();

        System.out.println("#Menu principal inicializado correctamente");
    }

    public MenuPrincipal(String tema, ArrayList<Venta> ventas, ArrayList<Producto> productos, ArrayList<Cliente> clientes, Seccion seccionSeleccionada) {
        this.tema = tema;
        this.seccionSeleccionada = seccionSeleccionada;
        this.ventas = ventas;
        this.productos = productos;
        this.clientes = clientes;
        initComponents();

        configurarTabla();

        temaCb.setSelectedItem(tema);

        System.out.println("#Menu principal inicializado correctamente");
    }

    private void configurarTabla() {
        mainTable.getSelectionModel().addListSelectionListener((ListSelectionEvent event) -> {
            eliminarBtn.setEnabled(mainTable.getSelectedRowCount() > 0);
        });

        Point centroPantalla = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        Dimension tamanoVentana = this.getSize();
        this.setLocation(centroPantalla.x - (tamanoVentana.width / 2), centroPantalla.y - (tamanoVentana.height / 2));

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }

        };
    }

    private void cambiarSeccion(Seccion seccionSeleccionada, boolean actualizar) {
        this.seccionSeleccionada = seccionSeleccionada;
        System.out.println("#Seccion cambiada a: " + this.seccionSeleccionada);

        switch (seccionSeleccionada) {
            case VENTAS: {
                ventasBtn.setBackground(new Color(74, 78, 105));
                productosBtn.setBackground(new Color(34, 34, 59));
                clientesBtn.setBackground(new Color(34, 34, 59));
                jLabel1.setText("Ventas");
                break;
            }

            case PRODUCTOS: {
                productosBtn.setBackground(new Color(74, 78, 105));
                ventasBtn.setBackground(new Color(34, 34, 59));
                clientesBtn.setBackground(new Color(34, 34, 59));
                jLabel1.setText("Productos");
                break;
            }

            case CLIENTES: {
                clientesBtn.setBackground(new Color(74, 78, 105));
                ventasBtn.setBackground(new Color(34, 34, 59));
                productosBtn.setBackground(new Color(34, 34, 59));
                jLabel1.setText("Clientes");
                break;
            }
        }
        if (actualizar) {
            actualizarSeccion(seccionSeleccionada);
        }
    }

    private void actualizarSeccion(Seccion seccion) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        importarBtn.setEnabled(false);
        exportarBtn.setEnabled(false);
        model.setRowCount(0);

        switch (seccion) {
            case VENTAS:
                accesoVentas = new VentasDaoImpl();
                ventas = accesoVentas.getVentas();
                break;
            case PRODUCTOS:
                accesoProductos = new ProductosDaoImpl();
                productos = accesoProductos.getProductos();
                break;
            case CLIENTES:
                accesoClientes = new ClientesDaoImpl();
                clientes = accesoClientes.getClientes();
                break;
        }

        rellenarTabla(seccion, ventas, productos, clientes);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        importarBtn.setEnabled(true);
        exportarBtn.setEnabled(true);
    }

    private void rellenarTabla(Seccion seccion, ArrayList<Venta> ventas, ArrayList<Producto> productos, ArrayList<Cliente> clientes) {
        mainTable.setModel(model);
        model.setColumnCount(0);
        model.setRowCount(0);
        switch (seccion) {
            case VENTAS: {
                for (String nombreColumna : columnasVenta) {
                    model.addColumn(nombreColumna);
                }
                for (Venta venta : ventas) {
                    model.addRow(new Object[]{venta.getProducto(), venta.getComprador(), venta.getFecha(), venta.getHora(), venta.getPrecio(), venta.getCantidad(), venta.getTipoPago()});
                }
                break;
            }
            case PRODUCTOS: {
                for (String nombreColumna : columnasProducto) {
                    model.addColumn(nombreColumna);

                }
                for (Producto producto : productos) {
                    model.addRow(new Object[]{producto.getNombre(), producto.getDescripcion(), producto.getPrecio(), producto.getCantidad()});
                }
                break;
            }
            case CLIENTES: {
                for (String nombreColumna : columnasCliente) {
                    model.addColumn(nombreColumna);
                }
                for (Cliente cliente : clientes) {
                    model.addRow(new Object[]{cliente.getNombre(), cliente.getApellidos(), cliente.getDomicilio(), cliente.getEmail()});
                }
                break;
            }
            default: {
                break;
            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ventasBtn = new javax.swing.JLabel();
        productosBtn = new javax.swing.JLabel();
        clientesBtn = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        mainTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        temaCb = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        importarBtn = new javax.swing.JButton();
        exportarBtn = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        eliminarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 600));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        ventasBtn.setBackground(new java.awt.Color(74, 78, 105));
        ventasBtn.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        ventasBtn.setForeground(new java.awt.Color(243, 242, 228));
        ventasBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ventasBtn.setText("Ventas");
        ventasBtn.setToolTipText("");
        ventasBtn.setMaximumSize(new java.awt.Dimension(53, 200));
        ventasBtn.setMinimumSize(new java.awt.Dimension(53, 200));
        ventasBtn.setOpaque(true);
        ventasBtn.setPreferredSize(new java.awt.Dimension(53, 200));
        ventasBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ventasBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ventasBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ventasBtnMouseExited(evt);
            }
        });

        productosBtn.setBackground(new java.awt.Color(34, 34, 59));
        productosBtn.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        productosBtn.setForeground(new java.awt.Color(243, 242, 228));
        productosBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productosBtn.setText("Productos");
        productosBtn.setMaximumSize(new java.awt.Dimension(53, 200));
        productosBtn.setMinimumSize(new java.awt.Dimension(53, 200));
        productosBtn.setOpaque(true);
        productosBtn.setPreferredSize(new java.awt.Dimension(53, 200));
        productosBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                productosBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                productosBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                productosBtnMouseExited(evt);
            }
        });

        clientesBtn.setBackground(new java.awt.Color(34, 34, 59));
        clientesBtn.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        clientesBtn.setForeground(new java.awt.Color(243, 242, 228));
        clientesBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clientesBtn.setText("Clientes");
        clientesBtn.setOpaque(true);
        clientesBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientesBtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                clientesBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                clientesBtnMouseExited(evt);
            }
        });

        mainTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(mainTable);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Productos");

        temaCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Claro", "Oscuro" }));
        temaCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                temaCbActionPerformed(evt);
            }
        });

        jLabel2.setText("Tema: ");

        importarBtn.setText("Importar");
        importarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importarBtnActionPerformed(evt);
            }
        });

        exportarBtn.setText("Exportar");
        exportarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportarBtnActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jButton3.setText("+");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        eliminarBtn.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        eliminarBtn.setText("-");
        eliminarBtn.setEnabled(false);
        eliminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ventasBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productosBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clientesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 760, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(640, 640, 640)
                        .addComponent(temaCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(600, 600, 600)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(eliminarBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(importarBtn)
                        .addGap(31, 31, 31)
                        .addComponent(exportarBtn)
                        .addGap(38, 38, 38))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(ventasBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(productosBtn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(clientesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(temaCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton3)
                        .addComponent(eliminarBtn))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(importarBtn)
                        .addComponent(exportarBtn))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ventasBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ventasBtnMouseClicked
        seccionSeleccionada = Seccion.VENTAS;
        cambiarSeccion(seccionSeleccionada, true);
    }//GEN-LAST:event_ventasBtnMouseClicked

    private void ventasBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ventasBtnMouseEntered
        ventasBtn.setBackground(new Color(74, 78, 105));
    }//GEN-LAST:event_ventasBtnMouseEntered

    private void ventasBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ventasBtnMouseExited
        if (seccionSeleccionada != Seccion.VENTAS) {
            ventasBtn.setBackground(new Color(34, 34, 59));
        }
    }//GEN-LAST:event_ventasBtnMouseExited

    private void productosBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productosBtnMouseClicked
        seccionSeleccionada = Seccion.PRODUCTOS;
        cambiarSeccion(seccionSeleccionada, true);
    }//GEN-LAST:event_productosBtnMouseClicked

    private void productosBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productosBtnMouseEntered
        productosBtn.setBackground(new Color(74, 78, 105));
    }//GEN-LAST:event_productosBtnMouseEntered

    private void productosBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productosBtnMouseExited
        if (seccionSeleccionada != Seccion.PRODUCTOS) {
            productosBtn.setBackground(new Color(34, 34, 59));
        }
    }//GEN-LAST:event_productosBtnMouseExited

    private void clientesBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesBtnMouseClicked
        seccionSeleccionada = Seccion.CLIENTES;
        cambiarSeccion(seccionSeleccionada, true);
    }//GEN-LAST:event_clientesBtnMouseClicked

    private void clientesBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesBtnMouseEntered
        clientesBtn.setBackground(new Color(74, 78, 105));
    }//GEN-LAST:event_clientesBtnMouseEntered

    private void clientesBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesBtnMouseExited
        if (seccionSeleccionada != Seccion.CLIENTES) {
            clientesBtn.setBackground(new Color(34, 34, 59));
        }
    }//GEN-LAST:event_clientesBtnMouseExited

    private void importarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importarBtnActionPerformed
        switch (seccionSeleccionada) {
            case VENTAS:
                JFileChooser seleccionadorDeArchivo = new JFileChooser();

                FileFilter JSONFilefilter = new FileFilter() {
                    public boolean accept(File file) {
                        String path = file.getPath();
                        return file.isDirectory() || path.endsWith(".json") || path.endsWith("JSON");
                    }

                    @Override
                    public String getDescription() {
                        return "Archivo JSON";
                    }
                };

                seleccionadorDeArchivo.setFileFilter(JSONFilefilter);

                if (seleccionadorDeArchivo.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    String rutaCompleta = seleccionadorDeArchivo.getSelectedFile().getAbsolutePath();
                    String texto = leerArchivo(rutaCompleta);
                    Venta[] nuevasVentasArr = new Gson().fromJson(texto, Venta[].class);
                    if (JOptionPane.showConfirmDialog(this, "Se añadiran " + nuevasVentasArr.length + " ventas, ¿Estas seguro?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                        VentasDaoImpl accesoVentas = new VentasDaoImpl();
                        if (accesoVentas.insertarVentas(nuevasVentasArr)) {
                            for (Venta nuevoProducto : nuevasVentasArr) {
                                ventas.add(nuevoProducto);
                            }

                        } else {
                            JOptionPane.showMessageDialog(this, "Error al importar ventas, asegurese de que los productos y clientes asociados a las ventas no se hayan eliminado", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                }
                break;
            case PRODUCTOS:
                seleccionadorDeArchivo = new JFileChooser();

                JSONFilefilter = new FileFilter() {
                    public boolean accept(File file) {
                        String path = file.getPath();
                        return file.isDirectory() || path.endsWith(".json") || path.endsWith("JSON");
                    }

                    @Override
                    public String getDescription() {
                        return "Archivo JSON";
                    }
                };

                seleccionadorDeArchivo.setFileFilter(JSONFilefilter);

                if (seleccionadorDeArchivo.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    String rutaCompleta = seleccionadorDeArchivo.getSelectedFile().getAbsolutePath();
                    String texto = leerArchivo(rutaCompleta);
                    Producto[] nuevosProductosArr = new Gson().fromJson(texto, Producto[].class);

                    if (JOptionPane.showConfirmDialog(this, "Se añadiran " + nuevosProductosArr.length + " productos, ¿Estas seguro?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                        for (Producto nuevoProducto : nuevosProductosArr) {
                            productos.add(nuevoProducto);
                        }
                        ProductosDaoImpl accesoProductos = new ProductosDaoImpl();
                        accesoProductos.insertarProductos(nuevosProductosArr);
                    }
                }
                break;
            case CLIENTES:
                seleccionadorDeArchivo = new JFileChooser();

                JSONFilefilter = new FileFilter() {
                    public boolean accept(File file) {
                        String path = file.getPath();
                        return file.isDirectory() || path.endsWith(".json") || path.endsWith("JSON");
                    }

                    @Override
                    public String getDescription() {
                        return "Archivo JSON";
                    }
                };

                seleccionadorDeArchivo.setFileFilter(JSONFilefilter);

                if (seleccionadorDeArchivo.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    String rutaCompleta = seleccionadorDeArchivo.getSelectedFile().getAbsolutePath();
                    String texto = leerArchivo(rutaCompleta);
                    Cliente[] nuevosClientesArr = new Gson().fromJson(texto, Cliente[].class);
                    if (JOptionPane.showConfirmDialog(this, "Se añadiran " + nuevosClientesArr.length + " clientes, ¿Estas seguro?", "Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                        for (Cliente nuevoProducto : nuevosClientesArr) {
                            clientes.add(nuevoProducto);
                        }
                        ClientesDaoImpl accesoClientes = new ClientesDaoImpl();
                        accesoClientes.insertarClientes(nuevosClientesArr);
                    }
                }
                break;
            default:
                break;
        }

        rellenarTabla(seccionSeleccionada, ventas, productos, clientes);
    }//GEN-LAST:event_importarBtnActionPerformed

    private String leerArchivo(String rutaCompleta) {
        try {
            File myObj = new File(rutaCompleta);
            Scanner reader = new Scanner(myObj);
            String text = "";
            while (reader.hasNextLine()) {
                text += reader.nextLine();
            }
            reader.close();
            return text;
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo encontrar el archivo");
            e.printStackTrace();
        }
        return "";
    }
    private void temaCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_temaCbActionPerformed
        String temaSeleccionado = ((JComboBox) evt.getSource()).getSelectedItem().toString();
        if (!(tema == temaSeleccionado)) {
            if (temaSeleccionado == "Claro") {
                FlatLightLaf.setup();
            } else {
                FlatDarkLaf.setup();
            }

            this.dispose();
            System.out.println(seccionSeleccionada);
            new MenuPrincipal(temaSeleccionado, ventas, productos, clientes, seccionSeleccionada).setVisible(true);
        }
    }//GEN-LAST:event_temaCbActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        cambiarSeccion(seccionSeleccionada, false);
        if (productos.isEmpty() && ventas.isEmpty() && clientes.isEmpty()) {
            actualizarSeccion(seccionSeleccionada);
        } else {
            rellenarTabla(seccionSeleccionada, ventas, productos, clientes);
        }
    }//GEN-LAST:event_formWindowOpened

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        switch (seccionSeleccionada) {
            case CLIENTES:
                new AddCliente(this).setVisible(true);
                break;
            case PRODUCTOS:
                new AddProducto(this).setVisible(true);
                break;
            case VENTAS:
                new AddVenta(this, ventas).setVisible(true);
                break;
            default:
                break;
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void reproducirSonido(String nombre) {
        String dir = System.getProperty("user.dir");
        String soundName = dir + "/recursos" + nombre;

        try {
            SoundPlayer.thePath = soundName;
            SoundPlayer soundPlayer = new SoundPlayer();
            soundPlayer.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void eliminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarBtnActionPerformed
        // Los arratList tienen que estar en el mismo orden que en la tabla, de lo contrario va a borrar los que no son  
        int[] filasAEliminar = mainTable.getSelectedRows();
        switch (seccionSeleccionada) {
            case CLIENTES:
                ArrayList<Cliente> clientesAEliminar = new ArrayList<>();
                for (int filaAEliminar : filasAEliminar) {
                    clientesAEliminar.add(clientes.get(filaAEliminar));
                }
                for (Cliente clienteAEliminar : clientesAEliminar) {
                    clientes.remove(clienteAEliminar);
                }
                if (!clientesAEliminar.isEmpty()) {
                    reproducirSonido("/370849__cabled-mess__clack-minimal-ui-sounds.wav");
                    ClientesDaoImpl accesoClientes = new ClientesDaoImpl();
                    accesoClientes.eliminarClientes(clientesAEliminar);
                }
                break;
            case PRODUCTOS:
                ArrayList<Producto> productosAEliminar = new ArrayList<>();
                for (int filaAEliminar : filasAEliminar) {
                    productosAEliminar.add(productos.get(filaAEliminar));
                }
                for (Producto productoAEliminar : productosAEliminar) {
                    productos.remove(productoAEliminar);
                }
                if (!productosAEliminar.isEmpty()) {
                    reproducirSonido("/370849__cabled-mess__clack-minimal-ui-sounds.wav");
                    ProductosDaoImpl accesoProductos = new ProductosDaoImpl();
                    accesoProductos.eliminarProductos(productosAEliminar);
                }
                break;
            case VENTAS:
                ArrayList<Venta> ventasAEliminar = new ArrayList<>();
                for (int filaAEliminar : filasAEliminar) {
                    ventasAEliminar.add(ventas.get(filaAEliminar));
                }

                for (Venta ventaAEliminar : ventasAEliminar) {
                    ventas.remove(ventaAEliminar);
                }
                if (!ventasAEliminar.isEmpty()) {
                    reproducirSonido("/370849__cabled-mess__clack-minimal-ui-sounds.wav");

                    VentasDaoImpl accesoVentas = new VentasDaoImpl();
                    accesoVentas.eliminarVentas(ventasAEliminar);
                }

                break;
            default:
                break;
        }
        rellenarTabla(seccionSeleccionada, ventas, productos, clientes);
    }//GEN-LAST:event_eliminarBtnActionPerformed

    private void exportarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportarBtnActionPerformed
        switch (seccionSeleccionada) {
            case VENTAS:
                String jsonStr = new Gson().toJson(ventas);

                JFileChooser seleccionadorDeArchivo = new JFileChooser();

                FileFilter JSONFilefilter = new FileFilter() {
                    public boolean accept(File file) {
                        String path = file.getPath();
                        return file.isDirectory() || path.endsWith(".json") || path.endsWith("JSON");
                    }

                    @Override
                    public String getDescription() {
                        return "Archivo JSON";
                    }
                };

                seleccionadorDeArchivo.setFileFilter(JSONFilefilter);
                if (seleccionadorDeArchivo.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                    String rutaCompleta = seleccionadorDeArchivo.getSelectedFile().getAbsolutePath();
                    if (!(rutaCompleta.endsWith(".json") || rutaCompleta.endsWith(".JSON"))) {
                        rutaCompleta += ".json";
                    }
                    if (rutaCompleta.length() > 5) {
                        escribirArchivo(rutaCompleta, jsonStr);
                    } else {
                        JOptionPane.showMessageDialog(this, "Nombre invalido", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case PRODUCTOS:
                jsonStr = new Gson().toJson(productos);

                seleccionadorDeArchivo = new JFileChooser();

                JSONFilefilter = new FileFilter() {
                    public boolean accept(File file) {
                        String path = file.getPath();
                        return file.isDirectory() || path.endsWith(".json") || path.endsWith("JSON");
                    }

                    @Override
                    public String getDescription() {
                        return "Archivo JSON";
                    }
                };

                seleccionadorDeArchivo.setFileFilter(JSONFilefilter);
                if (seleccionadorDeArchivo.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                    String rutaCompleta = seleccionadorDeArchivo.getSelectedFile().getAbsolutePath();
                    if (!(rutaCompleta.endsWith(".json") || rutaCompleta.endsWith(".JSON"))) {
                        rutaCompleta += ".json";
                    }
                    if (rutaCompleta.length() > 5) {
                        escribirArchivo(rutaCompleta, jsonStr);
                    } else {
                        JOptionPane.showMessageDialog(this, "Nombre invalido", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            case CLIENTES:
                jsonStr = new Gson().toJson(clientes);

                seleccionadorDeArchivo = new JFileChooser();

                JSONFilefilter = new FileFilter() {
                    public boolean accept(File file) {
                        String path = file.getPath();
                        return file.isDirectory() || path.endsWith(".json") || path.endsWith("JSON");
                    }

                    @Override
                    public String getDescription() {
                        return "Archivo JSON";
                    }
                };

                seleccionadorDeArchivo.setFileFilter(JSONFilefilter);
                if (seleccionadorDeArchivo.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                    String rutaCompleta = seleccionadorDeArchivo.getSelectedFile().getAbsolutePath();
                    if (!(rutaCompleta.endsWith(".json") || rutaCompleta.endsWith(".JSON"))) {
                        rutaCompleta += ".json";
                    }
                    if (rutaCompleta.length() > 5) {
                        escribirArchivo(rutaCompleta, jsonStr);
                    } else {
                        JOptionPane.showMessageDialog(this, "Nombre invalido", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                break;
            default:
                break;
        }
    }//GEN-LAST:event_exportarBtnActionPerformed

    private void escribirArchivo(String rutaCompleta, String contenido) {
        try {
            FileWriter archivo = new FileWriter(rutaCompleta);
            archivo.write(contenido);
            archivo.close();
            System.out.println("#Se guardo el archivo: " + rutaCompleta);
            JOptionPane.showMessageDialog(this, "Archivo creado correctamente", "Correcto", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudo crear el archivo", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public void recargarTabla(Producto producto, Venta venta, Cliente cliente) {
        if (producto != null) {
            productos.add(producto);
        }
        if (venta != null) {
            ventas.add(venta);
        }
        if (cliente != null) {
            clientes.add(cliente);
        }

        rellenarTabla(seccionSeleccionada, ventas, productos, clientes);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel clientesBtn;
    private javax.swing.JButton eliminarBtn;
    private javax.swing.JButton exportarBtn;
    private javax.swing.JButton importarBtn;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable mainTable;
    private javax.swing.JLabel productosBtn;
    private javax.swing.JComboBox<String> temaCb;
    private javax.swing.JLabel ventasBtn;
    // End of variables declaration//GEN-END:variables
}
