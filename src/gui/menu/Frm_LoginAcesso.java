package gui.menu;

import javax.swing.JOptionPane;

public class Frm_LoginAcesso extends javax.swing.JFrame {
    
   private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Frm_LoginAcesso.class.getName());
    
    
   private final String usuario = "USER-001";
   private final String password = "mySQL-123";
    
    
public Frm_LoginAcesso() {
    // 1. Primero quitamos la barra (X, -, cuadrado)
    this.setUndecorated(true); 
    
    // 2. Luego inicializamos los componentes
    initComponents();
    
    // 3. Finalmente centramos la ventana en la pantalla
    this.setLocationRelativeTo(null); 
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtusuario = new javax.swing.JTextField();
        BTN_Ingresar = new javax.swing.JButton();
        BTN_Salir = new javax.swing.JButton();
        passpassword = new javax.swing.JPasswordField();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 270, 140));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 310, 180));

        jLabel2.setFont(new java.awt.Font("Agency FB", 1, 12)); // NOI18N
        jLabel2.setText("USUARIO");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 70, 30));

        jLabel3.setFont(new java.awt.Font("Agency FB", 1, 12)); // NOI18N
        jLabel3.setText("CONTRASEÑA");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 70, 30));

        txtusuario.addActionListener(this::txtusuarioActionPerformed);
        jPanel1.add(txtusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 210, 35));

        BTN_Ingresar.setBackground(new java.awt.Color(0, 0, 255));
        BTN_Ingresar.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 12)); // NOI18N
        BTN_Ingresar.setForeground(new java.awt.Color(255, 255, 255));
        BTN_Ingresar.setText("INGRESAR");
        BTN_Ingresar.addActionListener(this::BTN_IngresarActionPerformed);
        jPanel1.add(BTN_Ingresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 90, 50));

        BTN_Salir.setFont(new java.awt.Font("Franklin Gothic Medium Cond", 1, 12)); // NOI18N
        BTN_Salir.setText("SALIR");
        BTN_Salir.addActionListener(this::BTN_SalirActionPerformed);
        jPanel1.add(BTN_Salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 370, 100, 50));

        passpassword.addActionListener(this::passpasswordActionPerformed);
        jPanel1.add(passpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, 210, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 360, 440));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusuarioActionPerformed

    private void BTN_IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_IngresarActionPerformed
        
        String user = new String(txtusuario.getText());
        String pass = new String(passpassword.getPassword());
        
        
        if (user.equals(usuario)&&pass.equals(password)) {
            JOptionPane.showMessageDialog(this, "Bienvenido al sistema académico\nAcceso concedido correctamente","Acceso permitido",JOptionPane.INFORMATION_MESSAGE);
            
            Frm_MenuPrincipal menu = new Frm_MenuPrincipal();
            menu.setVisible(true);
            this.dispose();
            
        } else {
            JOptionPane.showMessageDialog(this, "Correo electrónico o contraseña incorrectos.\n Inténtelo de nuevo","Inicio de Sesión denegado",JOptionPane.WARNING_MESSAGE);
            txtusuario.setText("");
            passpassword.setText("");
        }
      
    }//GEN-LAST:event_BTN_IngresarActionPerformed

    private void BTN_SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SalirActionPerformed
        int respuesta = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "¿Estás seguro de que quieres cerrar la aplicación?",
            "Confirmar salida",
            javax.swing.JOptionPane.YES_NO_OPTION,
            javax.swing.JOptionPane.QUESTION_MESSAGE
        );

        if (respuesta == javax.swing.JOptionPane.YES_OPTION) {
            System.exit(0); // Cierra todo el programa
        }

    }//GEN-LAST:event_BTN_SalirActionPerformed

    private void passpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passpasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passpasswordActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Frm_LoginAcesso().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Ingresar;
    private javax.swing.JButton BTN_Salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField passpassword;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables

    }


