
package gui.crudMantenimiento;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class JD_AgregarPlato extends javax.swing.JDialog {

    DefaultTableModel modeloTabla = new DefaultTableModel();
    public JD_AgregarPlato(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        FlatLightLaf.setup();
        initComponents();
        this.setLocationRelativeTo(this);
        this.setTitle("Pedidos");
        this.setResizable(false);
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spnPrecio, "0.00");
        spnPrecio.setEditor(editor);
        
        String[] header = {"ID","Plato","Cantidad","Precio","Subtotal","Observaciones"};
        modeloTabla.setColumnIdentifiers(header);
        JTABLE_Dialog.setModel(modeloTabla);
        Table_Size();
        
        
    }
    
    
    
    private void Table_Size() {
        // ID: Lo ocultamos
        TableColumnModel colModel = JTABLE_Dialog.getColumnModel();
 
        colModel.getColumn(0).setMinWidth(20);
        colModel.getColumn(0).setPreferredWidth(40);
        colModel.getColumn(0).setMaxWidth(40);

        colModel.getColumn(1).setMinWidth(120);
        colModel.getColumn(1).setPreferredWidth(100);

        colModel.getColumn(0).setMinWidth(120);
        colModel.getColumn(2).setPreferredWidth(100);

        colModel.getColumn(0).setMinWidth(90);
        colModel.getColumn(3).setPreferredWidth(80);

        colModel.getColumn(0).setMinWidth(90);
        colModel.getColumn(4).setPreferredWidth(80);

        colModel.getColumn(0).setMinWidth(100);
        colModel.getColumn(5).setPreferredWidth(200);

        
        JTABLE_Dialog.getTableHeader().setReorderingAllowed(false); // No mover columnas
        JTABLE_Dialog.setRowHeight(25); // Filas más altas para que respire el diseño
        
    }
    
    
    
    
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtFechaRegistro = new javax.swing.JTextField();
        cmbPlato = new javax.swing.JComboBox<>();
        spnCantidad = new javax.swing.JSpinner();
        spnPrecio = new javax.swing.JSpinner();
        scrollObs = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        txtSubtotal = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIdPedido = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Dialog = new javax.swing.JTable();
        btn_guardar = new javax.swing.JButton();
        btn_añadir = new javax.swing.JButton();
        btn_actualizar = new javax.swing.JButton();
        btn_quitar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtFechaRegistro.setEditable(false);
        txtFechaRegistro.setBackground(new java.awt.Color(255, 255, 255));
        txtFechaRegistro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtFechaRegistro.setForeground(new java.awt.Color(0, 0, 204));
        txtFechaRegistro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaRegistro.setText("13/12/2026");
        txtFechaRegistro.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtFechaRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaRegistroActionPerformed(evt);
            }
        });
        jPanel1.add(txtFechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 150, 30));

        cmbPlato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cmbPlato, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 180, 30));

        spnCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, 50, 1));
        jPanel1.add(spnCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 50, 120, 30));

        spnPrecio.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, 500.0d, 0.1d));
        jPanel1.add(spnPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, 120, 30));

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        scrollObs.setViewportView(txtObservaciones);

        jPanel1.add(scrollObs, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 450, 60));

        txtSubtotal.setEditable(false);
        txtSubtotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtSubtotal.setFocusable(false);
        jPanel1.add(txtSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 150, 120, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel12.setText("Platillo:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 60, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel7.setText("Cantidad");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 50, 80, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel6.setText("Observaciones (Opcional):");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 160, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel8.setText("Precio Unitario");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 90, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel2.setText("Subtotal");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, 90, -1));

        txtIdPedido.setEditable(false);
        txtIdPedido.setBackground(new java.awt.Color(255, 255, 255));
        txtIdPedido.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIdPedido.setForeground(new java.awt.Color(0, 0, 204));
        txtIdPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdPedido.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtIdPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 110, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Codigo de Pedido Actual");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 690, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel9.setText("Fecha de Registro");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 130, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel13.setText("Codigo de Pedido Actual");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 170, -1));

        JTABLE_Dialog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(JTABLE_Dialog);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 610, 210));

        btn_guardar.setText("Guardar");
        jPanel1.add(btn_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 510, 100, 40));

        btn_añadir.setText("Añadir");
        jPanel1.add(btn_añadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, 100, 40));

        btn_actualizar.setText("Actualizar");
        jPanel1.add(btn_actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 510, 100, 40));

        btn_quitar.setText("Quitar");
        jPanel1.add(btn_quitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 510, 100, 40));

        btn_salir.setText("Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        jPanel1.add(btn_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 510, 100, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 580));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFechaRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaRegistroActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
         int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que deseas cerrar el formulario?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            dispose(); // o this.dispose() si estás dentro del formulario
        }

    }//GEN-LAST:event_btn_salirActionPerformed

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
            java.util.logging.Logger.getLogger(JD_AgregarPlato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JD_AgregarPlato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JD_AgregarPlato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JD_AgregarPlato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JD_AgregarPlato dialog = new JD_AgregarPlato(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTABLE_Dialog;
    private javax.swing.JButton btn_actualizar;
    private javax.swing.JButton btn_añadir;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_quitar;
    private javax.swing.JButton btn_salir;
    private javax.swing.JComboBox<String> cmbPlato;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane scrollObs;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JSpinner spnPrecio;
    private javax.swing.JTextField txtFechaRegistro;
    private javax.swing.JTextField txtIdPedido;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JFormattedTextField txtSubtotal;
    // End of variables declaration//GEN-END:variables
}
