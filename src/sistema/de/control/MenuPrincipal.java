/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.de.control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;

/**
 *
 * @author Gerardo
 */
public class MenuPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form MenuPrincipal
     */
    
    public enum SeccionSeleccionada {
        VENTAS,
        PRODUCTOS,
        CLIENTES
    }
    
    SeccionSeleccionada seccionSeleccionada = SeccionSeleccionada.VENTAS;
    
    public MenuPrincipal() {
        initComponents();
        this.setResizable(false);
        
        Point centroPantalla = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        Dimension tamanoVentana = this.getSize();
        this.setLocation(centroPantalla.x - (tamanoVentana.width / 2), centroPantalla.y - (tamanoVentana.height / 2));
        
        
        System.out.println("#Menu principal inicializado correctamente");
        
    }
    
    private void cambiarSeleccion(SeccionSeleccionada seccionSeleccionada) {
        this.seccionSeleccionada = seccionSeleccionada;
        System.out.println("#Seccion cambiada a: " + this.seccionSeleccionada);
        switch(seccionSeleccionada) {
            case VENTAS: {
                ventasBtn.setBackground(new Color(74, 78, 105));
                productosBtn.setBackground(new Color(34,34,59));
                clientesBtn.setBackground(new Color(34,34,59));
                break;
            }
            
            case PRODUCTOS: {
                productosBtn.setBackground(new Color(74, 78, 105));
                ventasBtn.setBackground(new Color(34,34,59));
                clientesBtn.setBackground(new Color(34,34,59));
                break;
            }
            
            case CLIENTES: {
                clientesBtn.setBackground(new Color(74, 78, 105));
                ventasBtn.setBackground(new Color(34,34,59));
                productosBtn.setBackground(new Color(34,34,59));
                break;
            }
            
            default: break;
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

        jPanel1 = new javax.swing.JPanel();
        ventasBtn = new javax.swing.JLabel();
        productosBtn = new javax.swing.JLabel();
        clientesBtn = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(154, 140, 152));
        setBounds(new java.awt.Rectangle(1, 1, 1, 1));
        setSize(new java.awt.Dimension(900, 600));

        jPanel1.setBackground(new java.awt.Color(34, 34, 59));

        ventasBtn.setBackground(new java.awt.Color(74, 78, 105));
        ventasBtn.setFont(new java.awt.Font("Ebrima", 0, 18)); // NOI18N
        ventasBtn.setForeground(new java.awt.Color(243, 242, 228));
        ventasBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ventasBtn.setText("Ventas");
        ventasBtn.setToolTipText("");
        ventasBtn.setOpaque(true);
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
        productosBtn.setOpaque(true);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(productosBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
            .addComponent(ventasBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(clientesBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(ventasBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(productosBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(clientesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2.setBackground(new java.awt.Color(242, 233, 228));
        jPanel2.setForeground(new java.awt.Color(154, 140, 152));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Ventas");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(286, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(242, 242, 242)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ventasBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ventasBtnMouseEntered

        ventasBtn.setBackground(new Color(74, 78, 105));
    }//GEN-LAST:event_ventasBtnMouseEntered

    private void ventasBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ventasBtnMouseExited
        if(seccionSeleccionada != SeccionSeleccionada.VENTAS) {
            ventasBtn.setBackground(new Color(34,34,59));
        }
    }//GEN-LAST:event_ventasBtnMouseExited

    private void productosBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productosBtnMouseEntered
        productosBtn.setBackground(new Color(74, 78, 105));
    }//GEN-LAST:event_productosBtnMouseEntered

    private void productosBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productosBtnMouseExited
        if(seccionSeleccionada != SeccionSeleccionada.PRODUCTOS) {
            productosBtn.setBackground(new Color(34,34,59));
        }
    }//GEN-LAST:event_productosBtnMouseExited

    private void clientesBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesBtnMouseEntered
        clientesBtn.setBackground(new Color(74, 78, 105));
    }//GEN-LAST:event_clientesBtnMouseEntered

    private void clientesBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesBtnMouseExited
        if(seccionSeleccionada != SeccionSeleccionada.CLIENTES) {
            clientesBtn.setBackground(new Color(34,34,59));
        }
    }//GEN-LAST:event_clientesBtnMouseExited

    private void ventasBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ventasBtnMouseClicked
       jLabel1.setText("Ventas");
       
       seccionSeleccionada = SeccionSeleccionada.VENTAS;
       
       cambiarSeleccion(seccionSeleccionada);
    }//GEN-LAST:event_ventasBtnMouseClicked

    private void productosBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productosBtnMouseClicked
        jLabel1.setText("Productos");
        
        seccionSeleccionada = SeccionSeleccionada.PRODUCTOS;
        cambiarSeleccion(seccionSeleccionada);

    }//GEN-LAST:event_productosBtnMouseClicked

    private void clientesBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesBtnMouseClicked
        jLabel1.setText("Clientes");
        
        seccionSeleccionada = SeccionSeleccionada.CLIENTES;
        cambiarSeleccion(seccionSeleccionada);

    }//GEN-LAST:event_clientesBtnMouseClicked

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel clientesBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel productosBtn;
    private javax.swing.JLabel ventasBtn;
    // End of variables declaration//GEN-END:variables
}
