package gui.menu;

import javax.swing.JOptionPane;
import connection.ConnectionDB;
import com.formdev.flatlaf.FlatLightLaf;

public class Frm_LoginAcceso extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Frm_LoginAcceso.class.getName());

    ConnectionDB conexionBD = new ConnectionDB();
    
    public Frm_LoginAcceso() {
        this.setUndecorated(true);        
        FlatLightLaf.setup();
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        ComboBox_Cargos = new javax.swing.JComboBox<>();
        txtcodigousuario = new javax.swing.JTextField();
        txtpassword = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnsalir = new javax.swing.JButton();
        btningresar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        panelImage1 = new org.edisoncor.gui.panel.PanelImage();

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 255, 255));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Solo se permite el ingreso a personal autorizado, por el DBA , Admin ");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 13, 730, 20));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 70));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "      Acceso al sistema..!      ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 16))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ComboBox_Cargos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ComboBox_Cargos.setForeground(new java.awt.Color(0, 0, 204));
        ComboBox_Cargos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar cargo", "Gerente General ", "Jefe de Cocina ", "Mesero ", "Cajero", "Ayudante de Cocina" }));
        jPanel1.add(ComboBox_Cargos, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 290, 30));

        txtcodigousuario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtcodigousuario.setForeground(new java.awt.Color(0, 0, 204));
        txtcodigousuario.setText("somos diseño grafico??????");
        txtcodigousuario.addActionListener(this::txtcodigousuarioActionPerformed);
        jPanel1.add(txtcodigousuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 290, 30));

        txtpassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtpassword.setForeground(new java.awt.Color(0, 0, 204));
        jPanel1.add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 290, 30));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Ingrese Usuario:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 130, -1));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Ingrese contraseña:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 130, -1));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Seleccione Cargo:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 130, -1));

        btnsalir.setBackground(new java.awt.Color(0, 0, 204));
        btnsalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnsalir.setForeground(new java.awt.Color(255, 255, 255));
        btnsalir.setText("SALIR");
        btnsalir.addActionListener(this::btnsalirActionPerformed);
        jPanel1.add(btnsalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 160, 130, 30));

        btningresar.setBackground(new java.awt.Color(0, 0, 204));
        btningresar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btningresar.setForeground(new java.awt.Color(255, 255, 255));
        btningresar.setText("INGRESAR");
        btningresar.addActionListener(this::btningresarActionPerformed);
        jPanel1.add(btningresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 130, 30));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 470, 220));

        jPanel4.setBackground(new java.awt.Color(0, 0, 204));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Sistema de Gestión para Restaurante");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 330, 20));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 790, 70));

        panelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/login_icon.png"))); // NOI18N

        javax.swing.GroupLayout panelImage1Layout = new javax.swing.GroupLayout(panelImage1);
        panelImage1.setLayout(panelImage1Layout);
        panelImage1Layout.setHorizontalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        panelImage1Layout.setVerticalGroup(
            panelImage1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );

        jPanel3.add(panelImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 200, 210));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btningresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btningresarActionPerformed
        /* try {
            String codigo = txtcodigousuario.getText().trim();
            String password = String.valueOf(txtpassword.getPassword()).trim();
            String nombreCargo = ComboBox_Cargos.getSelectedItem().toString();

            // Validaciones de campos
            if (nombreCargo.equals("<<Seleccionar>>")) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un cargo", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!codigo.matches("\\d{5,15}")) {
                JOptionPane.showMessageDialog(this, "El código debe tener entre 5 y 15 dígitos numéricos.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (password.length() < 6) {
                JOptionPane.showMessageDialog(this, "La contraseña debe tener al menos 6 caracteres.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Encriptar contraseña
            String passwordEncriptada = EncriptadorSHA256.encriptar(password);

            // Validar acceso con método del DAO
            boolean accesoValido = conexionBD.validarLogin(codigo, passwordEncriptada, nombreCargo);

            if (accesoValido) {
                JOptionPane.showMessageDialog(this, "Bienvenido al sistema.", "Acceso concedido", JOptionPane.INFORMATION_MESSAGE);
                intentosFallidos = 0;

                // Redirigir según el cargo
                switch (nombreCargo.toUpperCase()) {
                    case "Administrador":
                    new Frm_MantUsuarios().setVisible(true);
                    break;
                    case "Coordinador Académico":
                    new Frm_MenuPrincipal().setVisible(true);
                    break;
                    default:
                    new Frm_PlanEstudios().setVisible(true);
                    break;
                }

                this.dispose(); // Cierra login

            } else {
                intentosFallidos++;

                JOptionPane.showMessageDialog(this,
                    "Credenciales incorrectas o usuario inactivo.\nIntento " + intentosFallidos + " de 3.",
                    "Acceso denegado",
                    JOptionPane.ERROR_MESSAGE);

                if (intentosFallidos >= 3) {
                    JOptionPane.showMessageDialog(this,
                        "Ha superado el número máximo de intentos.\nEl sistema se cerrará por seguridad.",
                        "Demasiados intentos",
                        JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al validar el acceso:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        */
    }//GEN-LAST:event_btningresarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed

        int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Estás seguro de que deseas cerrar el formulario?",
            "Confirmar salida",
            JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                // Cerrar conexión si tienes un método cerrarConexion()
                conexionBD.closeConnection();
            } catch (Exception e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }

            // Cierra el formulario actual
            dispose();  // o this.dispose() si estás dentro del formulario
        }
    }//GEN-LAST:event_btnsalirActionPerformed

    private void txtcodigousuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodigousuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodigousuarioActionPerformed


    
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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Frm_LoginAcceso().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBox_Cargos;
    private javax.swing.JButton btningresar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField jTextField2;
    private org.edisoncor.gui.panel.PanelImage panelImage1;
    private javax.swing.JTextField txtcodigousuario;
    private javax.swing.JPasswordField txtpassword;
    // End of variables declaration//GEN-END:variables
}
