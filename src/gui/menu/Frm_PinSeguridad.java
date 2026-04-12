/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.menu;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JOptionPane;
import gui.menu.Frm_LoginAcceso;



public class Frm_PinSeguridad extends javax.swing.JFrame {


    //Variable para contar el número de intentos
    private byte intentos_validos = 0;
    private final byte intentos_permitidos = 4;


    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Frm_PinSeguridad.class.getName());
    public Frm_PinSeguridad() {
        this.setUndecorated(true);
        FlatLightLaf.setup();
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        btningresar.setEnabled(false);
        btn_delete.setText("");


        TXTPASS.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(javax.swing.event.DocumentEvent e) { validarLongitud(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { validarLongitud(); }
            public void insertUpdate(javax.swing.event.DocumentEvent e) { validarLongitud(); }
        });


    }

    private void validarLongitud() {
    String pass = new String(TXTPASS.getPassword());
    btningresar.setEnabled(pass.length() >= 4);
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        TXTPASS = new javax.swing.JPasswordField();
        jRadioButtonView = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btn1 = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btningresar = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        btnclose = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_delete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Ingrese pin de seguridad !.");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 380, 20));

        TXTPASS.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        TXTPASS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TXTPASS.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        TXTPASS.addActionListener(this::TXTPASSActionPerformed);
        TXTPASS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TXTPASSKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXTPASSKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXTPASSKeyTyped(evt);
            }
        });
        jPanel3.add(TXTPASS, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 122, 270, 25));

        jRadioButtonView.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jRadioButtonView.addActionListener(this::jRadioButtonViewActionPerformed);
        jPanel3.add(jRadioButtonView, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 125, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Olvide el pin ?.");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 380, -1));

        jPanel4.setLayout(new java.awt.GridLayout(4, 3, 15, 12));

        btn1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn1.setText("1");
        btn1.addActionListener(this::btn1ActionPerformed);
        jPanel4.add(btn1);

        btn2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn2.setText("2");
        btn2.addActionListener(this::btn2ActionPerformed);
        jPanel4.add(btn2);

        btn3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn3.setText("3");
        btn3.addActionListener(this::btn3ActionPerformed);
        jPanel4.add(btn3);

        btn4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn4.setText("4");
        btn4.addActionListener(this::btn4ActionPerformed);
        jPanel4.add(btn4);

        btn5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn5.setText("5");
        btn5.addActionListener(this::btn5ActionPerformed);
        jPanel4.add(btn5);

        btn6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn6.setText("6");
        btn6.addActionListener(this::btn6ActionPerformed);
        jPanel4.add(btn6);

        btn7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn7.setText("7");
        btn7.addActionListener(this::btn7ActionPerformed);
        jPanel4.add(btn7);

        btn8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn8.setText("8");
        btn8.addActionListener(this::btn8ActionPerformed);
        jPanel4.add(btn8);

        btn9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn9.setText("9");
        btn9.addActionListener(this::btn9ActionPerformed);
        jPanel4.add(btn9);

        btningresar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btningresar.setText("Ingresar");
        btningresar.addActionListener(this::btningresarActionPerformed);
        jPanel4.add(btningresar);

        btn0.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btn0.setText("0");
        btn0.addActionListener(this::btn0ActionPerformed);
        jPanel4.add(btn0);

        btnclose.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnclose.setText("Salir");
        btnclose.addActionListener(this::btncloseActionPerformed);
        jPanel4.add(btnclose);

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 190, 360, 290));

        jPanel2.setBackground(new java.awt.Color(0, 0, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("INICIAR SESION...!");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("BIENVENIDO USUARIO");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 380, -1));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, 0, 380, 70));

        btn_delete.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        btn_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/delete_text.png"))); // NOI18N
        btn_delete.addActionListener(this::btn_deleteActionPerformed);
        jPanel3.add(btn_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 122, 50, 25));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 380, 490));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, -4, 380, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TXTPASSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXTPASSActionPerformed

    }//GEN-LAST:event_TXTPASSActionPerformed

    private void TXTPASSKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTPASSKeyPressed

    }//GEN-LAST:event_TXTPASSKeyPressed

    private void TXTPASSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTPASSKeyReleased

    }//GEN-LAST:event_TXTPASSKeyReleased

    private void TXTPASSKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXTPASSKeyTyped

    }//GEN-LAST:event_TXTPASSKeyTyped

    private void jRadioButtonViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonViewActionPerformed
        if (jRadioButtonView.isSelected()){
            //Mostrar contraseña
            TXTPASS.setEchoChar((char)0);

        }else{
            //Ocultar la conrtaseña
            TXTPASS.setEchoChar('*');
        }
    }//GEN-LAST:event_jRadioButtonViewActionPerformed

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        String PassActual = String.valueOf(TXTPASS.getPassword());
            TXTPASS.setText(PassActual+"1" );
    }//GEN-LAST:event_btn1ActionPerformed

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        String PassActual = String.valueOf(TXTPASS.getPassword());
            TXTPASS.setText(PassActual+"2" );
    }//GEN-LAST:event_btn2ActionPerformed

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        String PassActual = String.valueOf(TXTPASS.getPassword());
            TXTPASS.setText(PassActual+"3" );
    }//GEN-LAST:event_btn3ActionPerformed

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        String PassActual = String.valueOf(TXTPASS.getPassword());
            TXTPASS.setText(PassActual+"4" );
    }//GEN-LAST:event_btn4ActionPerformed

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        String PassActual = String.valueOf(TXTPASS.getPassword());
            TXTPASS.setText(PassActual+"5" );
    }//GEN-LAST:event_btn5ActionPerformed

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn6ActionPerformed
        String PassActual = String.valueOf(TXTPASS.getPassword());
            TXTPASS.setText(PassActual+"6" );
    }//GEN-LAST:event_btn6ActionPerformed

    private void btn7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn7ActionPerformed
        String PassActual = String.valueOf(TXTPASS.getPassword());
            TXTPASS.setText(PassActual+"7" );
    }//GEN-LAST:event_btn7ActionPerformed

    private void btn8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn8ActionPerformed
        String PassActual = String.valueOf(TXTPASS.getPassword());
            TXTPASS.setText(PassActual+"8" );
    }//GEN-LAST:event_btn8ActionPerformed

    private void btn9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn9ActionPerformed
        String PassActual = String.valueOf(TXTPASS.getPassword());
            TXTPASS.setText(PassActual+"9" );
    }//GEN-LAST:event_btn9ActionPerformed

    private void btningresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btningresarActionPerformed
        String pass = new String(TXTPASS.getPassword());


        byte intentos_restantes = (byte) (intentos_permitidos-intentos_validos);

        if (pass.equals(pinCorrecto)) {
            JOptionPane.showMessageDialog(this, "Bienvenido al sistema académico\nAcceso concedido correctamente","Acceso permitido",JOptionPane.INFORMATION_MESSAGE);

            Frm_LoginAcceso menu = new Frm_LoginAcceso();
            menu.setVisible(true);
            this.dispose();

        } else {
            intentos_validos++;
            JOptionPane.showMessageDialog(this, "PIN incorrecto, tiene "+intentos_restantes+" intentos restantes.","Acceso denegado",JOptionPane.WARNING_MESSAGE);
            TXTPASS.setText("");
            if(intentos_restantes<=0){
                JOptionPane.showMessageDialog(this, "Ha superado el número de intentos permitidos","Acceso denegado",JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }


        }
    }//GEN-LAST:event_btningresarActionPerformed

    private void btn0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn0ActionPerformed
        String PassActual = String.valueOf(TXTPASS.getPassword());
            TXTPASS.setText(PassActual+"0" );
    }//GEN-LAST:event_btn0ActionPerformed

    private void btncloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncloseActionPerformed
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
    }//GEN-LAST:event_btncloseActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        char[] passwordArray = TXTPASS.getPassword();
        String passwordStr = new String(passwordArray);

        if (passwordStr.length() > 0) {
            // Creamos una nueva cadena sin el último carácter
            String nuevoPassword = passwordStr.substring(0, passwordStr.length() - 1);
            TXTPASS.setText(nuevoPassword);
    }
    }//GEN-LAST:event_btn_deleteActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Frm_PinSeguridad().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField TXTPASS;
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btnclose;
    private javax.swing.JButton btningresar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButtonView;
    // End of variables declaration//GEN-END:variables
    private final String pinCorrecto = "123456";

}

