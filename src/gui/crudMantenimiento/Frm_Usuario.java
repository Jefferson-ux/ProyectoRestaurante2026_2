
package gui.crudMantenimiento;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
//Metodo de proveedor
import logic.dao.UsuarioMethod;

public class Frm_Usuario extends javax.swing.JFrame {
    DefaultTableModel modeloTablaUsuario = new DefaultTableModel();
    //Objeto conexión a la base de datos
    UsuarioMethod UM = new UsuarioMethod();
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Frm_Usuario.class.getName());
    
   
    public Frm_Usuario() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Mantenimiento de Usuario");
        
        this.UM = new UsuarioMethod();
        llenarComboCargos();
        String[] header = {"IdUsuario","Codigo Usuario","Password","Cargo","Estado"};

        modeloTablaUsuario.setColumnIdentifiers(header);
        JTABLE_Mant_Usuarios.setModel(modeloTablaUsuario);
        
        desactivarIniciar();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtpasswordUsuario = new javax.swing.JTextField();
        txtCodigoUsuario = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtestadoUsuario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBox_cargo = new javax.swing.JComboBox<>();
        txtIDUsuario1 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        BTN_listarUsuarios = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jCheckBoxDesactivarUsuario = new javax.swing.JCheckBox();
        jCheckBoxActivar = new javax.swing.JCheckBox();
        BTN_Reset = new javax.swing.JButton();
        BTN_Nuevo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXT_BuscarUsuario = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_Usuarios = new javax.swing.JTable();
        BTN_Cerrar1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("MANTENIMIENTO DE USUARIOS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, 240, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("ESTADO/USUARIO");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, -1));

        txtpasswordUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtpasswordUsuario.setForeground(new java.awt.Color(0, 0, 204));
        txtpasswordUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtpasswordUsuario.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtpasswordUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtpasswordUsuarioKeyTyped(evt);
            }
        });
        jPanel1.add(txtpasswordUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 250, -1));

        txtCodigoUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCodigoUsuario.setForeground(new java.awt.Color(0, 0, 204));
        txtCodigoUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCodigoUsuario.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtCodigoUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCodigoUsuarioKeyTyped(evt);
            }
        });
        jPanel1.add(txtCodigoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 250, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("CARGO");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("PASSWORD");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        txtestadoUsuario.setEditable(false);
        txtestadoUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtestadoUsuario.setForeground(new java.awt.Color(0, 0, 204));
        txtestadoUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtestadoUsuario.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtestadoUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtestadoUsuarioKeyTyped(evt);
            }
        });
        jPanel1.add(txtestadoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 150, 250, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("CODIGO");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("IDUSUARIO");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jComboBox_cargo.addActionListener(this::jComboBox_cargoActionPerformed);
        jComboBox_cargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox_cargoKeyReleased(evt);
            }
        });
        jPanel1.add(jComboBox_cargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 260, 30));

        txtIDUsuario1.setEditable(false);
        txtIDUsuario1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIDUsuario1.setForeground(new java.awt.Color(0, 0, 204));
        txtIDUsuario1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIDUsuario1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtIDUsuario1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIDUsuario1KeyTyped(evt);
            }
        });
        jPanel1.add(txtIDUsuario1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 250, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 520, 200));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BTN_listarUsuarios.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_listarUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/information.png"))); // NOI18N
        BTN_listarUsuarios.setText("LISTAR USUARIOS");
        BTN_listarUsuarios.addActionListener(this::BTN_listarUsuariosActionPerformed);
        jPanel3.add(BTN_listarUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, 230, 50));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_save.png"))); // NOI18N
        BTN_Guardar.setText("     GUARDAR");
        BTN_Guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_GuardarMouseClicked(evt);
            }
        });
        BTN_Guardar.addActionListener(this::BTN_GuardarActionPerformed);
        jPanel3.add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 140, 50));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCheckBoxDesactivarUsuario.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBoxDesactivarUsuario.setText(" DESACTIVAR USUARIO");
        jCheckBoxDesactivarUsuario.addActionListener(this::jCheckBoxDesactivarUsuarioActionPerformed);
        jPanel5.add(jCheckBoxDesactivarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jCheckBoxActivar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBoxActivar.setText("ACTIVAR USUARIO");
        jCheckBoxActivar.addActionListener(this::jCheckBoxActivarActionPerformed);
        jPanel5.add(jCheckBoxActivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 120, 260, 120));

        BTN_Reset.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Reset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_delete.png"))); // NOI18N
        BTN_Reset.setText("RESET");
        BTN_Reset.addActionListener(this::BTN_ResetActionPerformed);
        jPanel3.add(BTN_Reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, 150, 50));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_add.png"))); // NOI18N
        BTN_Nuevo.setText("      NUEVO");
        BTN_Nuevo.addActionListener(this::BTN_NuevoActionPerformed);
        jPanel3.add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 150, 50));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingresar el Nombre DE USUARIO");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, 30));

        TXT_BuscarUsuario.addActionListener(this::TXT_BuscarUsuarioActionPerformed);
        TXT_BuscarUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TXT_BuscarUsuarioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BuscarUsuarioKeyReleased(evt);
            }
        });
        jPanel2.add(TXT_BuscarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 290, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("BUSCAR");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 120, 30));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, -1, 50));

        JTABLE_Mant_Usuarios.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_Usuarios.setForeground(new java.awt.Color(0, 0, 204));
        JTABLE_Mant_Usuarios.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Mant_Usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_UsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Mant_Usuarios);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 850, 220));

        BTN_Cerrar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_cancel.png"))); // NOI18N
        BTN_Cerrar1.setText("     Cerrar");
        BTN_Cerrar1.addActionListener(this::BTN_Cerrar1ActionPerformed);
        jPanel3.add(BTN_Cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 600, 170, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtpasswordUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtpasswordUsuarioKeyTyped

    }//GEN-LAST:event_txtpasswordUsuarioKeyTyped

    private void BTN_listarUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_listarUsuariosActionPerformed
        this.listarUsuariosTabla();
        this.BTN_listarUsuarios.setEnabled(false);
    }//GEN-LAST:event_BTN_listarUsuariosActionPerformed

    private void JTABLE_Mant_UsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_UsuariosMouseClicked

    }//GEN-LAST:event_JTABLE_Mant_UsuariosMouseClicked

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
        this.limpiarFormulario();
        BTN_Guardar.setEnabled(true);
        jCheckBoxDesactivarUsuario.setEnabled(false);
        jCheckBoxActivar.setEnabled(false);
        BTN_Reset.setEnabled(false);
        BTN_listarUsuarios.setEnabled(true);
        
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
        String codigo = txtCodigoUsuario.getText().trim();
        String password = txtpasswordUsuario.getText().trim();
        String nombreCargo = jComboBox_cargo.getSelectedItem() != null ? 
                         jComboBox_cargo.getSelectedItem().toString() : "";

    // Validar campos del formulario
    if (codigo.isEmpty() || password.isEmpty() || nombreCargo.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Debe completar todos los "
                + "campos obligatorios.", "Validación", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Validar formato del código (solo números, entre 5 y 15 dígitos)
    if (!codigo.matches("\\d{5,15}")) {
        JOptionPane.showMessageDialog(this, "El código debe tener entre 5 y 15"
                + " dígitos numéricos.", "Validación", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Validar longitud mínima de contraseña (mínimo 6 caracteres)
    if (password.length() < 6) {
        JOptionPane.showMessageDialog(this, "La contraseña debe tener al menos 6 caracteres.",
                "Validación", JOptionPane.WARNING_MESSAGE);
        return;
    }
    try {
    // Obtener el código del cargo
    int codigoCargo = UM.obtenerCodigoCargo(nombreCargo);
    if (codigoCargo == -1) {
        JOptionPane.showMessageDialog(this, "El cargo seleccionado no es válido.",
                "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Encriptar la contraseña
    String passwordEncriptada = UsuarioMethod.encriptarSHA256(password);
    
    // Registrar el usuario
    String mensaje = UM.registrarUsuario(codigo, passwordEncriptada, codigoCargo);
    
    // Mostrar mensaje
    JOptionPane.showMessageDialog(this, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
    
    // Refrescar tabla y limpiar campos
    listarUsuariosTabla();

} catch (SQLException e) {
    JOptionPane.showMessageDialog(this, "Error al guardar usuario:\n" + e.getMessage(),
            "Error", JOptionPane.ERROR_MESSAGE);
}
    
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void TXT_BuscarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_BuscarUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_BuscarUsuarioActionPerformed

    private void TXT_BuscarUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarUsuarioKeyReleased
        String filtro = TXT_BuscarUsuario.getText().trim();
    try {
        modeloTablaUsuario.setRowCount(0); // Limpiar tabla
        ResultSet rs;
        
        if (filtro.isEmpty()) {
            // Si está vacío, listar todos los usuarios
            rs = UM.listarUsuario();
        } else {
            // Si hay texto, buscar por filtro
            rs = UM.buscarUsuario(filtro);
        }

        boolean hayResultados = false;
        while (rs.next()) {
            // Si devuelve solo un mensaje (validación del procedimiento)
            if (rs.getMetaData().getColumnLabel(1).equalsIgnoreCase("Mensaje")) {
                break;
            }

            Object fila[] = {
                rs.getInt("IdUsuario"),
                rs.getString("Codigo"),
                rs.getString("Password"),
                rs.getString("NombreCargo"),
                rs.getInt("EstadoUsuario") == 1 ? "Activo" : "Inactivo"
            };
            
            modeloTablaUsuario.addRow(fila);
            hayResultados = true;
        }

        rs.close();
        
        if (!hayResultados && !filtro.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron resultados para: "
                    + "\"" + filtro + "\"", "Sin coincidencias", JOptionPane.INFORMATION_MESSAGE);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al buscar usuario:\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }
        
        
        
        
        
        
        
        
        
        
    }//GEN-LAST:event_TXT_BuscarUsuarioKeyReleased

    private void BTN_Cerrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_Cerrar1ActionPerformed

            int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que deseas cerrar el formulario?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                // Cerrar conexión si tienes un método cerrarConexion()
                //TODO --> conexion.cerrarConexion();
            } catch (Exception e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
            // Cierra el formulario actual
            dispose(); // o this.dispose() si estás dentro del formulario
        }
    }//GEN-LAST:event_BTN_Cerrar1ActionPerformed

    private void txtCodigoUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoUsuarioKeyTyped

    char c = evt.getKeyChar();

    // Solo permitir dígitos
    if (!Character.isDigit(c)) {
        txtCodigoUsuario.setToolTipText("El codigo de usuario acepta como maximo 15 caracteres");
        evt.consume(); // Ignora el caracter
        return;
    }

    // Limitar a 15 caracteres
    if (txtCodigoUsuario.getText().length() >= 15) {
        evt.consume();
    }
    
    }//GEN-LAST:event_txtCodigoUsuarioKeyTyped

    private void txtestadoUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtestadoUsuarioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtestadoUsuarioKeyTyped

    private void BTN_ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ResetActionPerformed

    int fila = JTABLE_Mant_Usuarios.getSelectedRow();
    if (fila == -1) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar un usuario para "
                + "resetear su contraseña.", "Atención", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Obtener el código del usuario desde la tabla
    String codigoUsuario = modeloTablaUsuario.getValueAt(fila, 1).toString();
    int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro que "
            + "desea resetear la contraseña del usuario: " + codigoUsuario + "?",
            "Confirmar", JOptionPane.YES_NO_OPTION);

    if (confirmacion == JOptionPane.YES_OPTION) {
        try {
            String mensaje = UM.resetearPassword(codigoUsuario);
            JOptionPane.showMessageDialog(this, mensaje, "Resultado",
                    JOptionPane.INFORMATION_MESSAGE);

            listarUsuariosTabla(); // Refrescar tabla por si hay cambios
            limpiarFormulario();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al resetear contraseña:\n"
                    + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_BTN_ResetActionPerformed

    private void jCheckBoxActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxActivarActionPerformed
        
        if (jCheckBoxActivar.isSelected()) {
        
        int fila = JTABLE_Mant_Usuarios.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para reactivar.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            jCheckBoxActivar.setSelected(false);
            return;
        }
        
        // Leer el texto del estado ("Activo" o "Inactivo")
        String estadoTexto = JTABLE_Mant_Usuarios.getValueAt(fila, 4).toString().trim();
        
        if (estadoTexto.equalsIgnoreCase("Activo")) {
            JOptionPane.showMessageDialog(this, "El usuario ya está activo.", 
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            
            jCheckBoxActivar.setSelected(false);
            return;
        }
        
        // Obtener IdUsuario de la columna 0
int idUsuario = Integer.parseInt(JTABLE_Mant_Usuarios.getValueAt(fila, 0).toString());

int confirm = JOptionPane.showConfirmDialog(this, "¿Desea reactivar al usuario seleccionado?",
        "Confirmar", JOptionPane.YES_NO_OPTION);

if (confirm == JOptionPane.YES_OPTION) {
    try {
        String mensaje = UM.reactivarUsuario(idUsuario);
        JOptionPane.showMessageDialog(this, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);

        listarUsuariosTabla();      // Refresca la tabla

        jCheckBoxActivar.setSelected(false); // Desmarca
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al reactivar:\n" + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
        
        jCheckBoxActivar.setSelected(false);
    }
} else {
    jCheckBoxActivar.setSelected(false); // Canceló
}

    }
        
    }//GEN-LAST:event_jCheckBoxActivarActionPerformed

    private void TXT_BuscarUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarUsuarioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_BuscarUsuarioKeyPressed

    private void jCheckBoxDesactivarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxDesactivarUsuarioActionPerformed
        // Verificar si fue marcado
    if (jCheckBoxDesactivarUsuario.isSelected()) {
        int fila = JTABLE_Mant_Usuarios.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para desactivar.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            jCheckBoxDesactivarUsuario.setSelected(false); // Volver a desmarcar
            return;
        }

        // Obtener IdUsuario de la tabla (columna 0)
        int idUsuario = Integer.parseInt(modeloTablaUsuario.getValueAt(fila, 0).toString());
        int confirm = JOptionPane.showConfirmDialog(this, "¿Desea dar de baja al usuario "
                + "seleccionado?", "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                String mensaje = UM.darBajaUsuario(idUsuario);
                JOptionPane.showMessageDialog(this, mensaje, "Resultado",
                        JOptionPane.INFORMATION_MESSAGE);
                
                listarUsuariosTabla(); // Refresca la tabla
                jCheckBoxDesactivarUsuario.setSelected(false); // Desmarca el checkbox manualmente
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al dar de baja:\n" + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            jCheckBoxDesactivarUsuario.setSelected(false); // Canceló
        }
    }
    }//GEN-LAST:event_jCheckBoxDesactivarUsuarioActionPerformed

    private void jComboBox_cargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_cargoActionPerformed

    }//GEN-LAST:event_jComboBox_cargoActionPerformed

    private void jComboBox_cargoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox_cargoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_cargoKeyReleased

    private void txtIDUsuario1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDUsuario1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDUsuario1KeyTyped

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
        java.awt.EventQueue.invokeLater(() -> new Frm_Usuario().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Cerrar1;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_Reset;
    private javax.swing.JButton BTN_listarUsuarios;
    private javax.swing.JTable JTABLE_Mant_Usuarios;
    private javax.swing.JTextField TXT_BuscarUsuario;
    private javax.swing.JCheckBox jCheckBoxActivar;
    private javax.swing.JCheckBox jCheckBoxDesactivarUsuario;
    private javax.swing.JComboBox<String> jComboBox_cargo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCodigoUsuario;
    private javax.swing.JTextField txtIDUsuario1;
    private javax.swing.JTextField txtestadoUsuario;
    private javax.swing.JTextField txtpasswordUsuario;
    // End of variables declaration//GEN-END:variables
    /**
 * Llena el ComboBox de cargos con los valores activos desde la base de datos
 */
    
    
    private void llenarComboCargos() {
    try {
        // Acceder al método DAO para obtener los cargos activos
        ResultSet rs = UM.combobox_listarCargos();
        
        jComboBox_cargo.removeAllItems();
        jComboBox_cargo.addItem("<<Seleccionar>>"); // opción por defecto
        
        // Agregar cada NombreCargo al combo
        while (rs.next()) {
            jComboBox_cargo.addItem(rs.getString("nombre_cargo"));
        }
        
        rs.close(); // Cerrar ResultSet
        
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al llenar el ComboBox de cargos:\n" + 
                e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void listarUsuariosTabla() {
    try {
        // Limpiar el modelo de la tabla antes de llenarla nuevamente
        modeloTablaUsuario.setRowCount(0);

        JTABLE_Mant_Usuarios.setAutoCreateRowSorter(true);

        // Llamar al método DAO para obtener los usuarios
        ResultSet rs = UM.listarUsuario();

        // Recorrer el ResultSet y agregar filas a la tabla
        while (rs.next()) {
            Object fila[] = {
                rs.getInt("ID"),
                rs.getString("Código de Usuario"),
                rs.getString("Contraseña"),
                rs.getString("Nombre de Cargo"),
                rs.getInt("Estado") == 1 ? "Activo" : "Inactivo"
            };
            modeloTablaUsuario.addRow(fila);
        }

        rs.close(); // Cerrar ResultSet

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al listar los usuarios:\n" + 
                e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    
    /**
 * Limpia los campos del formulario después de guardar.
 */
    private void limpiarFormulario() {
    txtestadoUsuario.setText("");
    txtCodigoUsuario.setText("");
    txtpasswordUsuario.setText("");
    jComboBox_cargo.setSelectedIndex(0);
    txtestadoUsuario.setText("");
    TXT_BuscarUsuario.setText("");
    modeloTablaUsuario.setRowCount(0);
}

    private void desactivarIniciar() {
    jCheckBoxDesactivarUsuario.setEnabled(true);
    jCheckBoxActivar.setEnabled(true);
    BTN_Reset.setEnabled(true);
    BTN_Guardar.setEnabled(false);
}
}
