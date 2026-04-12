
package gui.crudMantenimiento;
import com.formdev.flatlaf.FlatLightLaf;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
//Metodo de proveedor
import logic.dao.ClienteMethod;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Frm_Cliente extends javax.swing.JFrame {
    DefaultTableModel modeloTablaCliente = new DefaultTableModel();
    //Objeto conexión a la base de datos
    ClienteMethod CM = new ClienteMethod();
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Frm_Cliente.class.getName());
    
   
    public Frm_Cliente() {
        FlatLightLaf.setup();
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Mantenimiento de clientes");
        
        this.CM = new ClienteMethod();

        String[] header = {"ID","DNI","Nombre","Apellido","Correo","Telefono","Observaciones","Estado"};

        modeloTablaCliente.setColumnIdentifiers(header);
        JTABLE_Mant_Cliente.setModel(modeloTablaCliente);
        
        //Ocultar ID
        JTABLE_Mant_Cliente.getColumnModel().getColumn(0).setMinWidth(0);
        JTABLE_Mant_Cliente.getColumnModel().getColumn(0).setMaxWidth(0);
        JTABLE_Mant_Cliente.getColumnModel().getColumn(0).setWidth(0);
        
        //Ocultar Observacion y estado
        JTABLE_Mant_Cliente.getColumnModel().getColumn(6).setMinWidth(0);
        JTABLE_Mant_Cliente.getColumnModel().getColumn(6).setMaxWidth(0);
        JTABLE_Mant_Cliente.getColumnModel().getColumn(6).setWidth(0);
        
        JTABLE_Mant_Cliente.getColumnModel().getColumn(7).setMinWidth(0);
        JTABLE_Mant_Cliente.getColumnModel().getColumn(7).setMaxWidth(0);
        JTABLE_Mant_Cliente.getColumnModel().getColumn(7).setWidth(0);

        //Desactivar button
        BTN_Guardar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        BTN_Desactivar.setEnabled(false);
        BTN_VerProveedor.setEnabled(true);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtnombreCliente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txttelefonoCliente = new javax.swing.JTextField();
        txtDniCliente = new javax.swing.JTextField();
        txtApellidoCliente = new javax.swing.JTextField();
        txtcorreoCliente = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtcodigoCliente = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtobservaciones = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_Cliente = new javax.swing.JTable();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXT_BuscarCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        BTN_Cerrar1 = new javax.swing.JButton();
        BTN_PDF = new javax.swing.JButton();
        BTN_Desactivar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jCheckBoxListarInactivos = new javax.swing.JCheckBox();
        jCheckBoxActivar = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        BTN_VerProveedor = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("MANTENIMIENTO DE CLIENTES");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, 240, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("CORREO");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("TELEFONO");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, -1, -1));

        txtnombreCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtnombreCliente.setForeground(new java.awt.Color(0, 0, 204));
        txtnombreCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnombreCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtnombreCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreClienteKeyTyped(evt);
            }
        });
        jPanel1.add(txtnombreCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 250, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Observaciones");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, -1, -1));

        txttelefonoCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txttelefonoCliente.setForeground(new java.awt.Color(0, 0, 204));
        txttelefonoCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttelefonoCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txttelefonoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonoClienteKeyTyped(evt);
            }
        });
        jPanel1.add(txttelefonoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, 250, -1));

        txtDniCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtDniCliente.setForeground(new java.awt.Color(0, 0, 204));
        txtDniCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDniCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtDniCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDniClienteKeyTyped(evt);
            }
        });
        jPanel1.add(txtDniCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 250, 20));

        txtApellidoCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtApellidoCliente.setForeground(new java.awt.Color(0, 0, 204));
        txtApellidoCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtApellidoCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtApellidoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoClienteKeyTyped(evt);
            }
        });
        jPanel1.add(txtApellidoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 250, -1));

        txtcorreoCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcorreoCliente.setForeground(new java.awt.Color(0, 0, 204));
        txtcorreoCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcorreoCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtcorreoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcorreoClienteKeyTyped(evt);
            }
        });
        jPanel1.add(txtcorreoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 40, 250, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("APELLIDO");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("NOMBRE");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        txtcodigoCliente.setEditable(false);
        txtcodigoCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcodigoCliente.setForeground(new java.awt.Color(0, 0, 204));
        txtcodigoCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcodigoCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtcodigoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigoClienteKeyTyped(evt);
            }
        });
        jPanel1.add(txtcodigoCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 250, 20));

        txtobservaciones.setColumns(20);
        txtobservaciones.setRows(5);
        jScrollPane3.setViewportView(txtobservaciones);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 140, 350, 80));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("DNI");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Codigo");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 780, 240));

        JTABLE_Mant_Cliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_Cliente.setForeground(new java.awt.Color(0, 0, 204));
        JTABLE_Mant_Cliente.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Mant_Cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_ClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Mant_Cliente);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 1060, 220));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_add.png"))); // NOI18N
        BTN_Nuevo.setText("      NUEVO");
        BTN_Nuevo.addActionListener(this::BTN_NuevoActionPerformed);
        getContentPane().add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 190, 50));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_cancel_cogs.png"))); // NOI18N
        BTN_Modificar.setText("    MODIFICAR");
        BTN_Modificar.addActionListener(this::BTN_ModificarActionPerformed);
        getContentPane().add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 290, 200, 50));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/excel.png"))); // NOI18N
        BTN_EXCEL.setText("     Exportar XLSX");
        BTN_EXCEL.addActionListener(this::BTN_EXCELActionPerformed);
        getContentPane().add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 650, 170, 50));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingresar el Nombre del Cliente");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, 30));

        TXT_BuscarCliente.addActionListener(this::TXT_BuscarClienteActionPerformed);
        TXT_BuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TXT_BuscarClienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BuscarClienteKeyReleased(evt);
            }
        });
        jPanel2.add(TXT_BuscarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 290, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("BUSCAR");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 120, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 1060, 50));

        BTN_Cerrar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_cancel.png"))); // NOI18N
        BTN_Cerrar1.setText("     Cerrar");
        BTN_Cerrar1.addActionListener(this::BTN_Cerrar1ActionPerformed);
        getContentPane().add(BTN_Cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 650, 170, 50));

        BTN_PDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pdf.png"))); // NOI18N
        BTN_PDF.setText("     Exportar PDF");
        BTN_PDF.addActionListener(this::BTN_PDFActionPerformed);
        getContentPane().add(BTN_PDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 650, 170, 50));

        BTN_Desactivar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Desactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_delete.png"))); // NOI18N
        BTN_Desactivar.setText("DESACTIVAR");
        BTN_Desactivar.addActionListener(this::BTN_DesactivarActionPerformed);
        getContentPane().add(BTN_Desactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 290, 200, 50));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCheckBoxListarInactivos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBoxListarInactivos.setText("LISTAR CLIENTES DESACTIVADOS");
        jCheckBoxListarInactivos.addActionListener(this::jCheckBoxListarInactivosActionPerformed);
        jPanel5.add(jCheckBoxListarInactivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, -1));

        jCheckBoxActivar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBoxActivar.setText("REACTIVACION");
        jCheckBoxActivar.addActionListener(this::jCheckBoxActivarActionPerformed);
        jPanel5.add(jCheckBoxActivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, -1, -1));

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 140, 260, 120));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BTN_VerProveedor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_VerProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/information.png"))); // NOI18N
        BTN_VerProveedor.setText("LISTAR CLIENTES");
        BTN_VerProveedor.addActionListener(this::BTN_VerProveedorActionPerformed);
        jPanel3.add(BTN_VerProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 40, 230, 50));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_save.png"))); // NOI18N
        BTN_Guardar.setText("     GUARDAR");
        BTN_Guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_GuardarMouseClicked(evt);
            }
        });
        BTN_Guardar.addActionListener(this::BTN_GuardarActionPerformed);
        jPanel3.add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 290, 190, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1070, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtnombreClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreClienteKeyTyped

    }//GEN-LAST:event_txtnombreClienteKeyTyped

    private void BTN_VerProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerProveedorActionPerformed
        this.listarClientes();
        this.BTN_Guardar.setEnabled(false);
        this.BTN_Modificar.setEnabled(false);
        this.BTN_VerProveedor.setEnabled(false);
        this.jCheckBoxListarInactivos.setSelected(false);
        this.jPanel3.requestFocus(true);
    }//GEN-LAST:event_BTN_VerProveedorActionPerformed

    private void txttelefonoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoClienteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelefonoClienteKeyTyped

    private void JTABLE_Mant_ClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_ClienteMouseClicked
        int selectRow = JTABLE_Mant_Cliente.getSelectedRow();
        if (selectRow >= 0) {
            String id = JTABLE_Mant_Cliente.getValueAt(selectRow, 0).toString();
            String dniCliente = JTABLE_Mant_Cliente.getValueAt(selectRow, 1).toString();
            String nombreCliente = JTABLE_Mant_Cliente.getValueAt(selectRow, 2).toString();
            String apellidoCliente = JTABLE_Mant_Cliente.getValueAt(selectRow, 3).toString();
            String correoCliente = JTABLE_Mant_Cliente.getValueAt(selectRow, 4).toString();
            String telefonoCliente = JTABLE_Mant_Cliente.getValueAt(selectRow, 5).toString();

            Object celdaObservacion = JTABLE_Mant_Cliente.getValueAt(selectRow, 6);
            String observacionCliente = (celdaObservacion != null) ? celdaObservacion.toString().trim() : "";
            
          
            txtcodigoCliente.setText(id);
            txtDniCliente.setText(dniCliente);
            txtnombreCliente.setText(nombreCliente);
            txtApellidoCliente.setText(apellidoCliente);
            txtcorreoCliente.setText(correoCliente);
            txttelefonoCliente.setText(telefonoCliente);
            txtobservaciones.setText(observacionCliente);
            
        }
        if (jCheckBoxListarInactivos.isSelected()) {
            BTN_Desactivar.setEnabled(false);
        } else {
            BTN_Desactivar.setEnabled(true);
        }
        BTN_Guardar.setEnabled(true);
        BTN_Modificar.setEnabled(true);

    }//GEN-LAST:event_JTABLE_Mant_ClienteMouseClicked

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
        limpiarCamposClientes();
        this.BTN_Nuevo.setEnabled(false);
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
    // 1. Capturar y limpiar datos
        String dni = txtDniCliente.getText().trim();
        String nombre = txtnombreCliente.getText().trim();
        String apellido = txtApellidoCliente.getText().trim();
        String correo = txtcorreoCliente.getText().trim();
        String telefono = txttelefonoCliente.getText().trim();
        String Observacion = txtobservaciones.getText().trim();

    // --- VALIDACIONES DE CAMPOS VACÍOS ---
        if (campoVacio(txtDniCliente, "DNI"))
            return;
        if (campoVacio(txtnombreCliente, "Razón Social")) {
            return;
        }
        if (campoVacio(txtApellidoCliente, "Teléfono")) {
            return;
        }
        if (campoVacio(txtcorreoCliente, "Correo")) {
            return;
        }
        if (campoVacio(txttelefonoCliente, "Dirección"))
            return;

    // --- NUEVAS VALIDACIONES DE FORMATO (Lógica de Negocio en Java) ---
    // Validar que el RUC tenga 11 dígitos y sea numérico
        if (dni.length() != 8 || !dni.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "El DNI debe tener exactamente 8 dígitos numéricos.", "Validación de RUC", JOptionPane.WARNING_MESSAGE);
            txtDniCliente.requestFocus();
            return;
        }

    // Validar formato de correo (opcional pero recomendado en Java)
        if (!correo.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            JOptionPane.showMessageDialog(this, "El formato del correo electrónico no es válido.", "Validación", JOptionPane.WARNING_MESSAGE);
            txtcorreoCliente.requestFocus();
            return;
        }

        if (Observacion.length() > 500) {
            JOptionPane.showMessageDialog(this, "La observación no puede exceder los 500 caracteres.", "Validación", JOptionPane.WARNING_MESSAGE);
            txtobservaciones.requestFocus();
            return;
        }

    // 2. Confirmar si el usuario desea guardar
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea guardar el registro de cliente?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {

            try {
                // 3. Llamar al método para insertar
                this.CM.insertarCliente(dni, nombre, apellido, correo, telefono, Observacion);

                // 4. Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(this, "Cliente registrado correctamente", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);

                // 5. Actualizar tabla y limpiar campos
                this.listarClientes();// Tu método para refrescar la tabla
                this.limpiarCamposClientes();// Tu método para limpiar TXTs

            } catch (SQLException ex) {
                // Capturamos los códigos de error personalizados definidos en el SQL (MYSQL_ERRNO)
                int errorCode = ex.getErrorCode();

                switch (errorCode) {
                    case 1034 -> JOptionPane.showMessageDialog(this, "Error: Ya existe un cliente activo con ese RUC.", "Duplicado", JOptionPane.ERROR_MESSAGE);
                    case 1035 -> JOptionPane.showMessageDialog(this, "Error: El correo electrónico ya está registrado.", "Duplicado", JOptionPane.ERROR_MESSAGE);
                    case 1043 -> JOptionPane.showMessageDialog(this, "Aviso: Este cliente ya existe en el sistema pero está INACTIVO.\nPor favor, búsquelo en la sección de inactivos para reactivarlo.", "Proveedor Inactivo", JOptionPane.INFORMATION_MESSAGE);
                    default -> JOptionPane.showMessageDialog(this, "Error de base de datos (" + errorCode + "):\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }catch(IllegalArgumentException e){
                // Aquí capturamos el mensaje que escribiste en el 'throw' del DAO
                JOptionPane.showMessageDialog(this, e.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
        // 1. Capturar datos y limpiar espacios
        String codigoStr = txtcodigoCliente.getText().trim();
        String dni = txtDniCliente.getText().trim();
        String nuevoNombre = txtnombreCliente.getText().trim();
        String nuevoApellido = txtApellidoCliente.getText().trim();
        String nuevocorreo = txtcorreoCliente.getText().trim();
        String nuevotelefono = txttelefonoCliente.getText().trim();
        String nuevaObservacion = txtobservaciones.getText().trim();

        // --- VALIDACIONES DE CAMPOS VACÍOS (Java) ---
        if (codigoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un cliente de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validaciones en cascada
        if (campoVacio(txtDniCliente, "RUC")) {
            return;
        }
        if (campoVacio(txtnombreCliente, "Nombre")) {
            return;
        }
        if (campoVacio(txtApellidoCliente, "Apellido")) {
            return;
        }
        if (campoVacio(txtcorreoCliente, "Correo")) {
            return;
        }
        if (campoVacio(txttelefonoCliente, "Telefono"))
            return;

        // --- VALIDACIONES DE FORMATO (Java) ---
        // Validar longitud de RUC
        if (dni.length() != 8 || !dni.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "El DNI debe tener exactamente 8 dígitos numéricos.", "Validación", JOptionPane.WARNING_MESSAGE);
            txtDniCliente.requestFocus();
            return;
        }

        // Validar formato de correo básico
        if (!nuevocorreo.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            JOptionPane.showMessageDialog(this, "El formato del correo electrónico no es válido.", "Validación", JOptionPane.WARNING_MESSAGE);
            txtcorreoCliente.requestFocus();
            return;
        }

        // Validar longitud de observación
        if (nuevaObservacion.length() > 500) {
            JOptionPane.showMessageDialog(this, "La observación no puede exceder los 500 caracteres.", "Validación", JOptionPane.WARNING_MESSAGE);
            txtobservaciones.requestFocus();
            return;
        }

        // Convertir Id_proveedor
        int idCliente = Integer.parseInt(codigoStr);

        // 2. Confirmación del usuario
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea modificar este Cliente?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                // 3. Llamar al método del DAO
                this.CM.modificarCliente(idCliente, dni, nuevoNombre, nuevoApellido, nuevocorreo, nuevotelefono, nuevaObservacion);

                // 4. Éxito
                JOptionPane.showMessageDialog(this, "Cliente modificado correctamente", "Modificación exitosa", JOptionPane.INFORMATION_MESSAGE);

                // 5. Refrescar interfaz
                this.listarClientes();
                limpiarCamposClientes();

            } catch (SQLException ex) {
                // --- CAPTURA DE ERRORES SEGÚN TU SQL (MYSQL_ERRNO) ---
                int errorCode = ex.getErrorCode();

                switch (errorCode) {
                    case 20169 -> JOptionPane.showMessageDialog(this, "Error: El DNI ingresado ya pertenece a otro cliente.", "Duplicado", JOptionPane.ERROR_MESSAGE);
                    case 20164 -> JOptionPane.showMessageDialog(this, "Error: El formato de correo es rechazado por el servidor.", "Validación SQL", JOptionPane.ERROR_MESSAGE);
                    case 20161 -> JOptionPane.showMessageDialog(this, "Error: El Cliente que intenta editar ya no existe en la base de datos.", "Error de ID", JOptionPane.ERROR_MESSAGE);
                    default -> JOptionPane.showMessageDialog(this, "Error crítico (" + errorCode + "):\n" + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void BTN_EXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EXCELActionPerformed
         try {
            // 1. Crear un nuevo libro de Excel (.xlsx)
            XSSFWorkbook workbook = new XSSFWorkbook();

            // 2. Crear una hoja dentro del libro
            XSSFSheet sheet = workbook.createSheet("Facultades");

            // 3. Escribir la fila de encabezados desde el JTable
            XSSFRow headerRow = sheet.createRow(0); // Fila 0 para encabezados
            for (int i = 0; i < JTABLE_Mant_Cliente.getColumnCount(); i++) {
                headerRow.createCell(i).setCellValue(JTABLE_Mant_Cliente.getColumnName(i));
            }

            // 4. Escribir los datos fila por fila desde el JTable
            for (int i = 0; i < JTABLE_Mant_Cliente.getRowCount(); i++) {
                XSSFRow dataRow = sheet.createRow(i + 1); // Fila 1 en adelante
                for (int j = 0; j < JTABLE_Mant_Cliente.getColumnCount(); j++) {
                    Object valor = JTABLE_Mant_Cliente.getValueAt(i, j);
                    dataRow.createCell(j).setCellValue(valor != null ? valor.toString() : "");
                }
            }

            // 5. Mostrar cuadro de diálogo para elegir ruta de guardado
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar como Excel");
            int seleccion = fileChooser.showSaveDialog(this);

            // 6. Si el usuario acepta guardar, crear archivo
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                
                File archivo = fileChooser.getSelectedFile();

                // 7. Asegurar extensión .xlsx
                if (!archivo.getAbsolutePath().endsWith(".xlsx")) {
                    archivo = new File(archivo.getAbsolutePath() + ".xlsx");
                }

                // 8. Guardar el archivo
                FileOutputStream fos = new FileOutputStream(archivo);
                workbook.write(fos);
                fos.close(); // Cerrar el flujo de salida
                workbook.close(); // Cerrar el libro de Excel

                // 9. Confirmación al usuario
                JOptionPane.showMessageDialog(this, "✅ Datos exportados correctamente a:\n" + archivo.getAbsolutePath());
            }

        } catch (Exception e) {
            // Manejo de errores en caso de fallo
            JOptionPane.showMessageDialog(this, "❌ Error al exportar a Excel:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BTN_EXCELActionPerformed

    private void TXT_BuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_BuscarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_BuscarClienteActionPerformed

    private void TXT_BuscarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarClienteKeyReleased
        this.BuscarClientesPorNombre();
    }//GEN-LAST:event_TXT_BuscarClienteKeyReleased

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

    private void BTN_PDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_PDFActionPerformed
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar como PDF");
            int seleccion = fileChooser.showSaveDialog(this);

            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File archivo = fileChooser.getSelectedFile();
                if (!archivo.getAbsolutePath().endsWith(".pdf")) {
                archivo = new File(archivo.getAbsolutePath() + ".pdf");
            }

            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(archivo));
            document.open();

            // 1. Agregar LOGO (ajusta la ruta según tu proyecto)
            String logoPath = "src/Icons/Fondo Universidad.jpg"; // Asegúrate que la imagen exista
            try {
                Image logo = Image.getInstance(logoPath);
                 logo.scaleToFit(100, 100);
                logo.setAlignment(Image.ALIGN_LEFT);
                document.add(logo);
            } catch (Exception e) {
                System.out.println("⚠️ No se pudo cargar el logo: " + e.getMessage());
            }

            //2. Agregar FECHA y HORA
            String fechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
            Paragraph fecha = new Paragraph("Fecha de Exportación: " + fechaHora,
            FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.DARK_GRAY));
            fecha.setAlignment(Element.ALIGN_RIGHT);
            document.add(fecha);

            // TÍTULO
            Paragraph titulo = new Paragraph("LISTADO DE FACULTADES",
                FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLUE));
            titulo.setAlignment(Element.ALIGN_CENTER);
            document.add(titulo);
            document.add(new Paragraph(" ")); // espacio

            // TABLA DE DATOS
            PdfPTable pdfTable = new PdfPTable(JTABLE_Mant_Cliente.getColumnCount());
            pdfTable.setWidthPercentage(100);

            // Encabezados
            for (int i = 0; i < JTABLE_Mant_Cliente.getColumnCount(); i++) {
                PdfPCell cell = new PdfPCell(new Phrase(JTABLE_Mant_Cliente.getColumnName(i)));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                pdfTable.addCell(cell);
            }

            // Filas de datos
            for (int row = 0; row < JTABLE_Mant_Cliente.getRowCount(); row++) {
                for (int col = 0; col < JTABLE_Mant_Cliente.getColumnCount(); col++) {
                    Object value = JTABLE_Mant_Cliente.getValueAt(row, col);
                    pdfTable.addCell(value != null ? value.toString() : "");
                }
            }

            document.add(pdfTable);

            // Pie de página con nombre del usuario
            document.add(new Paragraph(" "));
            String usuario = "Wilmer Vera Ostios"; // Puedes hacerlo dinámico si lo obtienes de sesión
            Paragraph pie = new Paragraph("Exportado por: " + usuario,
                FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10, BaseColor.GRAY));
            pie.setAlignment(Element.ALIGN_RIGHT);
            document.add(pie);

            document.close();
            writer.close();

            JOptionPane.showMessageDialog(this, "✅ PDF exportado correctamente:\n" + archivo.getAbsolutePath());
        } 

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Error al exportar a PDF:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BTN_PDFActionPerformed

    private void txtDniClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDniClienteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDniClienteKeyTyped

    private void txtApellidoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoClienteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoClienteKeyTyped

    private void txtcorreoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcorreoClienteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcorreoClienteKeyTyped

    private void txtcodigoClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoClienteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodigoClienteKeyTyped

    private void BTN_DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DesactivarActionPerformed
        // Obtener la fila seleccionada
        int filaSeleccionada = JTABLE_Mant_Cliente.getSelectedRow();

        // Validación inicial: ¿Seleccionó algo?
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 1. Obtener datos de la fila de forma segura
        // Usamos .toString() y trim() para evitar errores de puntero nulo o espacios
        int idCliente = Integer.parseInt(JTABLE_Mant_Cliente.getValueAt(filaSeleccionada, 0).toString());
        String nombreClie = JTABLE_Mant_Cliente.getValueAt(filaSeleccionada, 2).toString().trim();
        String estadoActual = JTABLE_Mant_Cliente.getValueAt(filaSeleccionada, 7).toString().trim();

        // 2. Validar si ya está desactivado para no trabajar en vano
        if (estadoActual.equalsIgnoreCase("Inactivo")) {
            JOptionPane.showMessageDialog(this, "El cliente '" + nombreClie + "' ya se encuentra desactivado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // 3. Confirmación personalizada (Avisando que el historial se mantiene)
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de que desea desactivar al cliente: " + nombreClie + "?\n",
                "Confirmar Desactivación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                // 4. Llamada al método del DAO/Manager
                CM.desactivarCliente(idCliente);

                JOptionPane.showMessageDialog(this, "Cliente desactivado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // 5. Actualizar interfaz respetando el filtro que el usuario tenga puesto
                if (jCheckBoxListarInactivos.isSelected()) {
                   listarClientesInactivos();
                } else {
                    listarClientes(); // El registro debería desaparecer de la lista de activos
                }

                // 6. Limpiar el formulario para evitar confusiones
                limpiarCamposClientes();

            } catch (SQLException ex) {
                String mensajeError = (ex.getErrorCode() == 20170) ? "El cliente no existe en la base de datos." : ex.getMessage();

                JOptionPane.showMessageDialog(this,
                        "No se pudo cambiar el estado del cliente.\nDetalle: " + mensajeError,
                        "Error de Base de Datos",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTN_DesactivarActionPerformed

    private void jCheckBoxActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxActivarActionPerformed
        // Obtener fila seleccionada
        int filaSeleccionada = JTABLE_Mant_Cliente.getSelectedRow();

        // 1. Validar selección inicial
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente para reactivarlo.", "Aviso", JOptionPane.WARNING_MESSAGE);
            if (jCheckBoxActivar.isSelected()) {
                jCheckBoxActivar.setSelected(false);
            }
            return;
        }

        // 2. Obtener datos de la fila de forma segura
        int idCliente = Integer.parseInt(JTABLE_Mant_Cliente.getValueAt(filaSeleccionada, 0).toString());
        String nombreClie = JTABLE_Mant_Cliente.getValueAt(filaSeleccionada, 2).toString().trim();
        String estadoActual = JTABLE_Mant_Cliente.getValueAt(filaSeleccionada, 7).toString().trim();

        // 3. Validar si ya está reactivado (Mensaje mejorado)
        if (estadoActual.equalsIgnoreCase("Activo")) {
            JOptionPane.showMessageDialog(this,
                    "El cliente '" + nombreClie + "' ya está reactivado.",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            jCheckBoxActivar.setSelected(false);
            return;
        }

        // 4. Confirmación con nombre y diseño profesional
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Desea reactivar al cliente: " + nombreClie + "?",
                "Confirmar Reactivación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                // 5. Ejecutar la reactivación en la Base de Datos
                CM.reactivarCliente(idCliente);

                JOptionPane.showMessageDialog(this,
                        "¡Listo! El cliente '" + nombreClie + "' ha sido reactivado con éxito.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // 6. Sincronizar Interfaz
                // Regresamos a la vista de activos para ver el cambio reflejado
                jCheckBoxListarInactivos.setSelected(false);
                jCheckBoxActivar.setSelected(false);

                // Refrescar tabla y limpiar formulario
                listarClientes();
                limpiarCamposClientes();

            } catch (SQLException ex) {
                // Manejo de errores específicos (20170: El registro desapareció de la DB)
                String msj = (ex.getErrorCode() == 20170) ? "El cliente no existe en el sistema." : ex.getMessage();

                JOptionPane.showMessageDialog(this,
                        "Error al intentar reactivar:\n" + msj,
                        "Error de Base de Datos",
                        JOptionPane.ERROR_MESSAGE);

                jCheckBoxActivar.setSelected(false);
            }
        } else {
            // Si el usuario cancela la acción
            jCheckBoxActivar.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxActivarActionPerformed

    private void TXT_BuscarClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarClienteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_BuscarClienteKeyPressed

    private void jCheckBoxListarInactivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxListarInactivosActionPerformed
        this.listarClientesInactivos();

        // Bloquear el botón de desactivar porque ya están inactivos
        this.BTN_Desactivar.setEnabled(false);

        this.BTN_Guardar.setEnabled(false);
        this.BTN_Modificar.setEnabled(false);
        this.BTN_VerProveedor.setEnabled(true);
    }//GEN-LAST:event_jCheckBoxListarInactivosActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Frm_Cliente().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Cerrar1;
    private javax.swing.JButton BTN_Desactivar;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_PDF;
    private javax.swing.JButton BTN_VerProveedor;
    private javax.swing.JTable JTABLE_Mant_Cliente;
    private javax.swing.JTextField TXT_BuscarCliente;
    private javax.swing.JCheckBox jCheckBoxActivar;
    private javax.swing.JCheckBox jCheckBoxListarInactivos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txtApellidoCliente;
    private javax.swing.JTextField txtDniCliente;
    private javax.swing.JTextField txtcodigoCliente;
    private javax.swing.JTextField txtcorreoCliente;
    private javax.swing.JTextField txtnombreCliente;
    private javax.swing.JTextArea txtobservaciones;
    private javax.swing.JTextField txttelefonoCliente;
    // End of variables declaration//GEN-END:variables
    //Validar campos vacios
    private boolean campoVacio(javax.swing.JTextField txt, String nombreCampo) {
        if (txt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo " + nombreCampo + " es obligatorio.", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            txt.requestFocus();
            return true; // Sí está vacío
        }
        return false; // No está vacío
    }

    //Método para listar Proveedores
    public void listarClientes() {
        //Ordenar ASC, DESC
        JTABLE_Mant_Cliente.setAutoCreateRowSorter(true);
        //Limpiar la tabla antes de mostrar nuevos datos
        modeloTablaCliente.setRowCount(0);
        try {
            //Llama al método que retorna los datos de Mesaes
            ResultSet rs = this.CM.listarCliente();
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("ID"),
                    rs.getString("DNI"),
                    rs.getString("Nombre de Cliente"),
                    rs.getString("Apellido de Cliente"),
                    rs.getString("Correo Personal"),
                    rs.getString("Telefono Personal"),
                    rs.getString("Observaciones"),
                    rs.getInt("Estado") == 1 ? "Activo" : "Inactivo"
                };
                modeloTablaCliente.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los resultados:\n" + e.getMessage(),
                    "ErrorDeConsulta", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void listarClientesInactivos() {
        //Ordenar ASC, DESC
        JTABLE_Mant_Cliente.setAutoCreateRowSorter(true);
        //Limpiar la tabla antes de mostrar nuevos datos
        modeloTablaCliente.setRowCount(0);
        try {
            //Llama al método que retorna los datos de Mesaes
            ResultSet rs = this.CM.listarClienteInactivo();
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("ID"),
                    rs.getString("DNI"),
                    rs.getString("Nombre de Cliente"),
                    rs.getString("Apellido de Cliente"),
                    rs.getString("Correo Personal"),
                    rs.getString("Telefono Personal"),
                    rs.getString("Observaciones"),
                    rs.getInt("Estado") == 1 ? "Activo" : "Inactivo"
                };
                modeloTablaCliente.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los resultados:\n" + e.getMessage(),
                    "ErrorDeConsulta", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarCamposClientes() {
            txtcodigoCliente.setText("");
            txtDniCliente.setText("");
            txtnombreCliente.setText("");
            txtApellidoCliente.setText("");
            txtcorreoCliente.setText("");
            txttelefonoCliente.setText("");
            txtobservaciones.setText("");
            BTN_Guardar.setEnabled(true);
            BTN_Nuevo.setEnabled(false);
            BTN_Modificar.setEnabled(false);
            BTN_VerProveedor.setEnabled(false);
            this.txtDniCliente.requestFocus();
        }
    
    public void BuscarClientesPorNombre() {
        modeloTablaCliente.setRowCount(0);
        String nombre = TXT_BuscarCliente.getText().trim();
        try {
            boolean hayResultados = false;
            //Llama al método que retorna los datos de Mesaes
            ResultSet rs = this.CM.buscarCliente(nombre);
            while (rs.next()) {
                hayResultados = true;
                Object[] fila = {
                    rs.getInt("ID"),
                    rs.getString("DNI"),
                    rs.getString("Nombre de Cliente"),
                    rs.getString("Apellido de Cliente"),
                    rs.getString("Correo Personal"),
                    rs.getString("Telefono Personal")
                };
                modeloTablaCliente.addRow(fila);
            }
            if(!hayResultados){
                JOptionPane.showMessageDialog(this, "No se encontraron clientes ",
                    "Sin resultados", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar Mensajes:\n" + e.getMessage(),
                    "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        }
    }
}
