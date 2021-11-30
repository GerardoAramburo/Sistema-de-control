/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.de.control;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import sistema.de.control.SoundPlayer;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import sistema.de.control.DAO.*;
import sistema.de.control.modelos.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

/**
 *
 * @author Gerardo
 */
public class MenuPrincipal extends javax.swing.JFrame {

    public enum Seccion {
        VENTAS,
        PRODUCTOS,
        CLIENTES
    }

    Seccion seccionSeleccionada = Seccion.PRODUCTOS;
    ArrayList<Producto> productos = new ArrayList<>();
    ArrayList<Cliente> clientes = new ArrayList<>();
    ArrayList<Venta> ventas = new ArrayList<>();
    String tema = "Claro";
    String[] columnasProducto = {"Nombre", "Descripción", "Precio", "Cantidad"};
    String[] columnasCliente = {"Nombre", "Apellidos", "Domicilio", "Email"};
    String[] columnasVenta = {"Producto", "Comprador", "Fecha", "Hora", "Precio", "Tipo de Pago"};

    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal(String tema) {
        this.tema = tema;
        initComponents();
        
        centrarVentana();

        temaCb.setSelectedItem(tema);
        
        addSeleccionListener();

        System.out.println("#Menu principal inicializado correctamente");
    }

    public MenuPrincipal(String tema, ArrayList<Venta> ventas, ArrayList<Producto> productos, ArrayList<Cliente> clientes, Seccion seccionSeleccionada) {
        this.tema = tema;
        this.seccionSeleccionada = seccionSeleccionada;
        this.ventas = ventas;
        this.productos = productos;
        this.clientes = clientes;
        initComponents();

        centrarVentana();
        
        addSeleccionListener();
        
        temaCb.setSelectedItem(tema);

        System.out.println("#Menu principal inicializado correctamente");
    }

    private void addSeleccionListener() {
        mainTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                eliminarBtn.setEnabled(mainTable.getSelectedRowCount() > 0);
            }
        });
    }
    private void centrarVentana() {
        Point centroPantalla = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        Dimension tamanoVentana = this.getSize();
        this.setLocation(centroPantalla.x - (tamanoVentana.width / 2), centroPantalla.y - (tamanoVentana.height / 2));
    }
    private void centrarColumna(int index) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        mainTable.getColumnModel().getColumn(index).setCellRenderer(centerRenderer);
    }

    private void cambiarSeccionSinRecargarDB() {
        switch (seccionSeleccionada) {
            case VENTAS: {
                ventasBtn.setBackground(new Color(74, 78, 105));
                productosBtn.setBackground(new Color(34, 34, 59));
                clientesBtn.setBackground(new Color(34, 34, 59));
                break;
            }

            case PRODUCTOS: {
                productosBtn.setBackground(new Color(74, 78, 105));
                ventasBtn.setBackground(new Color(34, 34, 59));
                clientesBtn.setBackground(new Color(34, 34, 59));
                break;
            }

            case CLIENTES: {
                clientesBtn.setBackground(new Color(74, 78, 105));
                ventasBtn.setBackground(new Color(34, 34, 59));
                productosBtn.setBackground(new Color(34, 34, 59));
                break;
            }

            default:
                break;
        }
    }

    private void cambiarSeleccion(Seccion seccionSeleccionada) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        this.seccionSeleccionada = seccionSeleccionada;
        System.out.println("#Seccion cambiada a: " + this.seccionSeleccionada);
        switch (seccionSeleccionada) {
            case VENTAS: {
                ventasBtn.setBackground(new Color(74, 78, 105));
                productosBtn.setBackground(new Color(34, 34, 59));
                clientesBtn.setBackground(new Color(34, 34, 59));
                actualizarSeccion(seccionSeleccionada);
                break;
            }

            case PRODUCTOS: {
                productosBtn.setBackground(new Color(74, 78, 105));
                ventasBtn.setBackground(new Color(34, 34, 59));
                clientesBtn.setBackground(new Color(34, 34, 59));
                actualizarSeccion(seccionSeleccionada);
                break;
            }

            case CLIENTES: {
                clientesBtn.setBackground(new Color(74, 78, 105));
                ventasBtn.setBackground(new Color(34, 34, 59));
                productosBtn.setBackground(new Color(34, 34, 59));
                actualizarSeccion(seccionSeleccionada);
                break;
            }

            default:
                break;
        }
         this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    private void actualizarSeccion(Seccion seccion) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }

        };
        switch (seccion) {
            case VENTAS:
                VentasDaoImpl accesoVentas = new VentasDaoImpl();
                ventas = null;
                ventas = accesoVentas.getVentas();

                //model = (DefaultTableModel) mainTable.getModel();
                model.setRowCount(0);
                rellenarTabla(seccion, ventas, productos, clientes, model);
                break;
            case PRODUCTOS:
                ProductosDaoImpl accesoProductos = new ProductosDaoImpl();
                productos = null;
                productos = accesoProductos.getProductos();

                //model = (DefaultTableModel) mainTable.getModel();
                model.setRowCount(0);
                rellenarTabla(seccion, ventas, productos, clientes, model);
                break;
            case CLIENTES:
                ClientesDaoImpl accesoClientes = new ClientesDaoImpl();
                clientes = null;
                clientes = accesoClientes.getClientes();

                //model = (DefaultTableModel) mainTable.getModel();
                model.setRowCount(0);
                rellenarTabla(seccion, ventas, productos, clientes, model);
                break;
            default:
                break;
        }

    }

    private void rellenarTabla(Seccion seccion, ArrayList<Venta> ventas, ArrayList<Producto> productos, ArrayList<Cliente> clientes, DefaultTableModel model) {
        mainTable.setModel(model);
        model.setColumnCount(0);
        switch (seccion) {
            case VENTAS: {
                for (String nombreColumna : columnasVenta) {
                    model.addColumn(nombreColumna);
                }
                for (Venta venta : ventas) {
                    model.addRow(new Object[]{venta.getProducto(), venta.getComprador(), venta.getFecha(), venta.getHora(), venta.getPrecio(), venta.getTipoPago()});
                }
                break;
            }
            case PRODUCTOS: {
                for (String nombreColumna : columnasProducto) {
                    model.addColumn(nombreColumna);

                }
                centrarColumna(3);
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
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

        jButton1.setText("Importar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Exportar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
                        .addComponent(jButton1)
                        .addGap(31, 31, 31)
                        .addComponent(jButton2)
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
                        .addComponent(jButton1)
                        .addComponent(jButton2))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ventasBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ventasBtnMouseClicked
        jLabel1.setText("Ventas");

        seccionSeleccionada = Seccion.VENTAS;
        
        cambiarSeleccion(seccionSeleccionada);
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
        jLabel1.setText("Productos");

        seccionSeleccionada = Seccion.PRODUCTOS;
        cambiarSeleccion(seccionSeleccionada);
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
        jLabel1.setText("Clientes");

        seccionSeleccionada = Seccion.CLIENTES;
        cambiarSeleccion(seccionSeleccionada);

    }//GEN-LAST:event_clientesBtnMouseClicked

    private void clientesBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesBtnMouseEntered
        clientesBtn.setBackground(new Color(74, 78, 105));
    }//GEN-LAST:event_clientesBtnMouseEntered

    private void clientesBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesBtnMouseExited
        if (seccionSeleccionada != Seccion.CLIENTES) {
            clientesBtn.setBackground(new Color(34, 34, 59));
        }
    }//GEN-LAST:event_clientesBtnMouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JOptionPane.showMessageDialog(this, "Funcion no implementada", "Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void temaCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_temaCbActionPerformed
        String temaSeleccionado = ((JComboBox) evt.getSource()).getSelectedItem().toString();
        if (!(tema == temaSeleccionado)) {
            if (temaSeleccionado == "Claro") {
                FlatLightLaf.setup();
            } else {
                FlatDarkLaf.setup();
            }

            this.dispose();
            new MenuPrincipal(temaSeleccionado, ventas, productos, clientes, seccionSeleccionada).setVisible(true);
        }
    }//GEN-LAST:event_temaCbActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        cambiarSeccionSinRecargarDB();
        if (productos.size() == 0 && ventas.size() == 0 && clientes.size() == 0) {
            actualizarSeccion(seccionSeleccionada);
        } else {
            DefaultTableModel model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int col) {
                    return false;
                }

            };
            rellenarTabla(seccionSeleccionada, ventas, productos, clientes, model);
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
                 JOptionPane.showMessageDialog(this, "Funcion no implementada", "Error", JOptionPane.ERROR_MESSAGE);
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
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(MenuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void eliminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarBtnActionPerformed
        // Los arratList tienen que estar en el mismo orden que en la tabla, de lo contrario va a borrar los que no son  
        int[] filasAEliminar = mainTable.getSelectedRows();
        switch (seccionSeleccionada) {
            case CLIENTES:
                JOptionPane.showMessageDialog(this, "Funcion no implementada", "Error", JOptionPane.ERROR_MESSAGE);
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
                }
                ProductosDaoImpl accesoProductos = new ProductosDaoImpl();
                accesoProductos.eliminarProductos(productosAEliminar);
                break;
            case VENTAS:
                JOptionPane.showMessageDialog(this, "Funcion no implementada", "Error", JOptionPane.ERROR_MESSAGE);
                break;
            default:
                break;
        }
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }
        };
        rellenarTabla(seccionSeleccionada, ventas, productos, clientes, model);
    }//GEN-LAST:event_eliminarBtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        JOptionPane.showMessageDialog(this, "Funcion no implementada", "Error", JOptionPane.ERROR_MESSAGE);
    }//GEN-LAST:event_jButton2ActionPerformed
    
    public void recargarTabla(Producto producto, Venta venta, Cliente cliente) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false;
            }

        };
        
        if (producto != null) {
            productos.add(producto);
        }
        if (venta != null) {
            ventas.add(venta);
        }
        if (producto != null) {
            clientes.add(cliente);
        }
        
        rellenarTabla(seccionSeleccionada, ventas, productos, clientes, model);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel clientesBtn;
    private javax.swing.JButton eliminarBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
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
