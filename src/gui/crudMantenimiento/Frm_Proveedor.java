
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
import logic.dao.ProveedorMethod;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Frm_Proveedor extends javax.swing.JFrame {
    DefaultTableModel modeloTablaProveedor = new DefaultTableModel();
    //Objeto conexión a la base de datos
    ProveedorMethod PM = new ProveedorMethod();
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Frm_Proveedor.class.getName());
    
   
    public Frm_Proveedor() {
        FlatLightLaf.setup();
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Mantenimiento de las mesa");
        
        this.PM = new ProveedorMethod();
        

        String[] header = {"ID","RUC","Razon social","Teléfono del proveedor","Correo del proveedor","Dirección del proveedor","Observaciones","Estado"};

        modeloTablaProveedor.setColumnIdentifiers(header);
        JTABLE_Mant_Proveedor.setModel(modeloTablaProveedor);
        
        //Ocultar ID
        JTABLE_Mant_Proveedor.getColumnModel().getColumn(0).setMinWidth(0);
        JTABLE_Mant_Proveedor.getColumnModel().getColumn(0).setMaxWidth(0);
        JTABLE_Mant_Proveedor.getColumnModel().getColumn(0).setWidth(0);

        //Desactivar button
        BTN_Guardar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        BTN_Activar.setEnabled(false);
        BTN_Activar.setVisible(false);
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
        txtrazonSocial = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtdireccionProveedor = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        BTN_VerProveedor = new javax.swing.JButton();
        BTN_VerProveedorInactivo = new javax.swing.JButton();
        txtRuc = new javax.swing.JTextField();
        txttelefonoProveedor = new javax.swing.JTextField();
        txtcorreoProveedor = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtcodigoProveedor = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtobservaciones = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        BTN_Desactivar = new javax.swing.JButton();
        BTN_Activar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_Proveedor = new javax.swing.JTable();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXT_BuscarProveedor = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        BTN_Cerrar1 = new javax.swing.JButton();
        BTN_PDF = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("MANTENIMIENTO DE PROVEEDORES");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 0, 240, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("correo del proveedor");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("direccion del proveedor");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, -1, -1));

        txtrazonSocial.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtrazonSocial.setForeground(new java.awt.Color(0, 0, 204));
        txtrazonSocial.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtrazonSocial.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtrazonSocial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrazonSocialKeyTyped(evt);
            }
        });
        jPanel1.add(txtrazonSocial, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 30, 290, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Observaciones");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, -1, -1));

        txtdireccionProveedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtdireccionProveedor.setForeground(new java.awt.Color(0, 0, 204));
        txtdireccionProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtdireccionProveedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtdireccionProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdireccionProveedorKeyTyped(evt);
            }
        });
        jPanel1.add(txtdireccionProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 100, 250, 30));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BTN_VerProveedor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_VerProveedor.setText("VER PROVEEDORES");
        BTN_VerProveedor.addActionListener(this::BTN_VerProveedorActionPerformed);
        jPanel3.add(BTN_VerProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 180, 50));

        BTN_VerProveedorInactivo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_VerProveedorInactivo.setText("VER PROVEEDORES INACTIVOS");
        BTN_VerProveedorInactivo.addActionListener(this::BTN_VerProveedorInactivoActionPerformed);
        jPanel3.add(BTN_VerProveedorInactivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 180, 50));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, 210, 120));

        txtRuc.setEditable(false);
        txtRuc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtRuc.setForeground(new java.awt.Color(0, 0, 204));
        txtRuc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtRuc.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtRuc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRucKeyTyped(evt);
            }
        });
        jPanel1.add(txtRuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 250, 30));

        txttelefonoProveedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txttelefonoProveedor.setForeground(new java.awt.Color(0, 0, 204));
        txttelefonoProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttelefonoProveedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txttelefonoProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonoProveedorKeyTyped(evt);
            }
        });
        jPanel1.add(txttelefonoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 250, 30));

        txtcorreoProveedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcorreoProveedor.setForeground(new java.awt.Color(0, 0, 204));
        txtcorreoProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcorreoProveedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtcorreoProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcorreoProveedorKeyTyped(evt);
            }
        });
        jPanel1.add(txtcorreoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 100, 250, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("telefono del proveedor");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("razon social");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, -1, -1));

        txtcodigoProveedor.setEditable(false);
        txtcodigoProveedor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcodigoProveedor.setForeground(new java.awt.Color(0, 0, 204));
        txtcodigoProveedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcodigoProveedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtcodigoProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcodigoProveedorKeyTyped(evt);
            }
        });
        jPanel1.add(txtcodigoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 170, 30));

        txtobservaciones.setColumns(20);
        txtobservaciones.setRows(5);
        jScrollPane3.setViewportView(txtobservaciones);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 330, 60));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("ruc");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("ID");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, -1, -1));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BTN_Desactivar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Desactivar.setText("DESACTIVAR");
        BTN_Desactivar.addActionListener(this::BTN_DesactivarActionPerformed);
        jPanel4.add(BTN_Desactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 170, 50));

        BTN_Activar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Activar.setText("ACTIVAR");
        BTN_Activar.addActionListener(this::BTN_ActivarActionPerformed);
        jPanel4.add(BTN_Activar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 170, 50));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 140, 200, 120));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 1020, 270));

        JTABLE_Mant_Proveedor.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_Proveedor.setForeground(new java.awt.Color(0, 0, 204));
        JTABLE_Mant_Proveedor.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Mant_Proveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_ProveedorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Mant_Proveedor);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 1040, 220));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setText("      NUEVO");
        BTN_Nuevo.addActionListener(this::BTN_NuevoActionPerformed);
        getContentPane().add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 190, 50));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setText("     GUARDAR");
        BTN_Guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_GuardarMouseClicked(evt);
            }
        });
        BTN_Guardar.addActionListener(this::BTN_GuardarActionPerformed);
        getContentPane().add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 330, 190, 50));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setText("    MODIFICAR");
        BTN_Modificar.addActionListener(this::BTN_ModificarActionPerformed);
        getContentPane().add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 330, 200, 50));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setText("     Exportar XLSX");
        BTN_EXCEL.addActionListener(this::BTN_EXCELActionPerformed);
        getContentPane().add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 680, 170, 40));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingresar el Nombre de la Facultad");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, 30));

        TXT_BuscarProveedor.addActionListener(this::TXT_BuscarProveedorActionPerformed);
        TXT_BuscarProveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BuscarProveedorKeyReleased(evt);
            }
        });
        jPanel2.add(TXT_BuscarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 290, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("BUSCAR");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 120, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, 1040, 50));

        BTN_Cerrar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar1.setText("     Cerrar");
        BTN_Cerrar1.addActionListener(this::BTN_Cerrar1ActionPerformed);
        getContentPane().add(BTN_Cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 680, 130, 40));

        BTN_PDF.setText("     Exportar PDF");
        BTN_PDF.addActionListener(this::BTN_PDFActionPerformed);
        getContentPane().add(BTN_PDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 680, 170, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtrazonSocialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrazonSocialKeyTyped

    }//GEN-LAST:event_txtrazonSocialKeyTyped

    private void BTN_VerProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerProveedorActionPerformed
        this.MostrarProveedores();
        this.BTN_Guardar.setEnabled(false);
        this.BTN_Modificar.setEnabled(false);
        this.BTN_VerProveedor.setEnabled(false);
        this.BTN_VerProveedorInactivo.setVisible(true);
        this.BTN_VerProveedorInactivo.setEnabled(true);
        this.BTN_VerProveedor.setVisible(false);
    }//GEN-LAST:event_BTN_VerProveedorActionPerformed

    private void txtdireccionProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionProveedorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccionProveedorKeyTyped

    private void JTABLE_Mant_ProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_ProveedorMouseClicked
        int selectRow = JTABLE_Mant_Proveedor.getSelectedRow();
        if (selectRow >= 0) {
            String id = JTABLE_Mant_Proveedor.getValueAt(selectRow, 0).toString();
            String ruc = JTABLE_Mant_Proveedor.getValueAt(selectRow, 1).toString();
            String razonSocial = JTABLE_Mant_Proveedor.getValueAt(selectRow, 2).toString();
            String telefonoProveedor = JTABLE_Mant_Proveedor.getValueAt(selectRow, 3).toString();
            String CorreoProveedor = JTABLE_Mant_Proveedor.getValueAt(selectRow, 4).toString();
            String direccionProveedor = JTABLE_Mant_Proveedor.getValueAt(selectRow, 5).toString();
            
            Object celdaObservacion = JTABLE_Mant_Proveedor.getValueAt(selectRow, 6);
            String observacionProveedor = (celdaObservacion != null) ? celdaObservacion.toString().trim() : "";
            
            Object estadoObj = JTABLE_Mant_Proveedor.getValueAt(selectRow, 7);
            String estado = (estadoObj != null) ? estadoObj.toString() : "";
            
            // Lógica de botones Activar/Desactivar
            if (estado.equalsIgnoreCase("Activo")) { // Si está ACTIVO (1)
                BTN_Activar.setEnabled(false);
                BTN_Desactivar.setEnabled(true);
                BTN_Activar.setVisible(false);
                BTN_Desactivar.setVisible(true);
            } else { // Si está DESACTIVADO (0)
                BTN_Activar.setEnabled(true);
                BTN_Desactivar.setEnabled(false);
                BTN_Activar.setVisible(true);
                BTN_Desactivar.setVisible(false);
            }
            
            txtcodigoProveedor.setText(id);
            txtRuc.setText(ruc);
            txtrazonSocial.setText(razonSocial);
            txttelefonoProveedor.setText(telefonoProveedor);
            txtcorreoProveedor.setText(CorreoProveedor);
            txtdireccionProveedor.setText(direccionProveedor);
            txtobservaciones.setText(observacionProveedor);
        }
        BTN_Guardar.setEnabled(true);
        BTN_Modificar.setEnabled(true);

    }//GEN-LAST:event_JTABLE_Mant_ProveedorMouseClicked

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
        txtcodigoProveedor.setText("");
        txtRuc.setText("");
        txtrazonSocial.setText("");
        txttelefonoProveedor.setText("");
        txtcorreoProveedor.setText("");
        txtdireccionProveedor.setText("");
        txtobservaciones.setText("");

        txtRuc.setEnabled(true);
        txtRuc.requestFocus();
        txtrazonSocial.setEnabled(true);
        txttelefonoProveedor.setEnabled(true);
        txtcorreoProveedor.setEnabled(true);
        txtdireccionProveedor.setEnabled(true);

        BTN_Guardar.setEnabled(true);
        BTN_Modificar.setEnabled(false);
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed

        // 1. Validar que el campo no esté vacío
        String ruc = txtRuc.getText().trim();
        String razonSocial = txtrazonSocial.getText().trim();
        String telefono = txttelefonoProveedor.getText().trim();
        String correo = txtcorreoProveedor.getText().trim();
        String direccion = txtdireccionProveedor.getText().trim();
        String Observacion = txtobservaciones.getText().trim();

        if (ruc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el ruc del proveedor", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            txtrazonSocial.requestFocus();
            return;
        }
        if (razonSocial.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la razon social", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            txtrazonSocial.requestFocus();
            return;
        }
        if (telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el telefono del proveedor", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            txttelefonoProveedor.requestFocus();
            return;
        }
        if (correo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el correo del proveedor", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            txtcorreoProveedor.requestFocus();
            return;
        }
        if (direccion.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la dirección del proveedor", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            txtdireccionProveedor.requestFocus();
            return;
        }
        if (Observacion.length() > 500) {
            JOptionPane.showMessageDialog(this, "La observación no puede exceder los 500 caracteres.", "Validación", JOptionPane.WARNING_MESSAGE);
            txtobservaciones.requestFocus();
            return;
        }

        // 2. Confirmar si el usuario desea guardar
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea guardar el registro de proveedo?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {

            try {
                // 3. Llamar al método para insertar
                this.PM.insertarProveedor(ruc, razonSocial, telefono, correo, direccion, Observacion);

                // 4. Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(this, "Proveedor registrado(a) correctamente", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);

                // 5. Actualizar tabla y limpiar campos
                this.MostrarProveedores();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar proveedor:\n" + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
    // Validar que se haya seleccionado un registro
    String codigoStr = txtcodigoProveedor.getText().trim();
    String ruc = txtRuc.getText().trim();
    String nuevarazonSocial = txtrazonSocial.getText().trim();
    String nuevotelefono = txttelefonoProveedor.getText().trim();
    String nuevocorreo = txtcorreoProveedor.getText().trim();
    String nuevodireccion = txtdireccionProveedor.getText().trim();
    String nuevaObservacion = txtobservaciones.getText().trim();
    

    
    if (nuevarazonSocial.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Ingrese la razon social", "Campo requerido", JOptionPane.WARNING_MESSAGE);
      txtrazonSocial.requestFocus();
      return;
    }
    if (nuevotelefono.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Ingrese el telefono del proveedor", "Campo requerido", JOptionPane.WARNING_MESSAGE);
      txttelefonoProveedor.requestFocus();
      return;
    }
    if (nuevocorreo.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Ingrese el correo del proveedor", "Campo requerido", JOptionPane.WARNING_MESSAGE);
      txtcorreoProveedor.requestFocus();
      return;
    }
    if (nuevodireccion.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Ingrese la dirección del proveedor", "Campo requerido", JOptionPane.WARNING_MESSAGE);
      txtdireccionProveedor.requestFocus();
      return;
    }
    // Validar la longitud de la observacion
    if (nuevaObservacion.length() > 500) {
        JOptionPane.showMessageDialog(this, "La observación no puede exceder los 500 caracteres.", "Validación", JOptionPane.WARNING_MESSAGE);
        txtobservaciones.requestFocus();
        return;
    }

    //Convertir Id_proveedor en int
    int codigoProveedor = Integer.parseInt(codigoStr);
    
    // Confirmación del usuario
    int respuesta = JOptionPane.showConfirmDialog(this,"¿Desea modificar esta Proveedor?", "Confirmación",JOptionPane.YES_NO_OPTION);
    if (respuesta == JOptionPane.YES_OPTION) {
      try {
        this.PM.modificarProveedor(codigoProveedor, ruc, nuevarazonSocial, nuevotelefono, nuevocorreo, nuevodireccion, nuevaObservacion);

        JOptionPane.showMessageDialog(this, "Proveedor modificada correctamente", "Modificación exitosa", JOptionPane.INFORMATION_MESSAGE);

        this.MostrarProveedores();
        // Limpia los campos de texto
        limpiarCamposProveedor();
      } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al modificar Proveedor:\n" + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
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
            for (int i = 0; i < JTABLE_Mant_Proveedor.getColumnCount(); i++) {
                headerRow.createCell(i).setCellValue(JTABLE_Mant_Proveedor.getColumnName(i));
            }

            // 4. Escribir los datos fila por fila desde el JTable
            for (int i = 0; i < JTABLE_Mant_Proveedor.getRowCount(); i++) {
                XSSFRow dataRow = sheet.createRow(i + 1); // Fila 1 en adelante
                for (int j = 0; j < JTABLE_Mant_Proveedor.getColumnCount(); j++) {
                    Object valor = JTABLE_Mant_Proveedor.getValueAt(i, j);
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

    private void TXT_BuscarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_BuscarProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_BuscarProveedorActionPerformed

    private void TXT_BuscarProveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarProveedorKeyReleased
        this.BuscarProveedorPorNombre();
    }//GEN-LAST:event_TXT_BuscarProveedorKeyReleased

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
            PdfPTable pdfTable = new PdfPTable(JTABLE_Mant_Proveedor.getColumnCount());
            pdfTable.setWidthPercentage(100);

            // Encabezados
            for (int i = 0; i < JTABLE_Mant_Proveedor.getColumnCount(); i++) {
                PdfPCell cell = new PdfPCell(new Phrase(JTABLE_Mant_Proveedor.getColumnName(i)));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                pdfTable.addCell(cell);
            }

            // Filas de datos
            for (int row = 0; row < JTABLE_Mant_Proveedor.getRowCount(); row++) {
                for (int col = 0; col < JTABLE_Mant_Proveedor.getColumnCount(); col++) {
                    Object value = JTABLE_Mant_Proveedor.getValueAt(row, col);
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

    private void txtRucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRucKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRucKeyTyped

    private void txttelefonoProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoProveedorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelefonoProveedorKeyTyped

    private void txtcorreoProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcorreoProveedorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcorreoProveedorKeyTyped

    private void txtcodigoProveedorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoProveedorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcodigoProveedorKeyTyped

    private void BTN_DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DesactivarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_DesactivarActionPerformed

    private void BTN_ActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ActivarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_ActivarActionPerformed

    private void BTN_VerProveedorInactivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerProveedorInactivoActionPerformed
        this.MostrarProveedoresInactivos();
        this.BTN_Guardar.setEnabled(false);
        this.BTN_Modificar.setEnabled(false);
        this.BTN_VerProveedor.setEnabled(false);
        this.BTN_VerProveedor.setVisible(true);
        this.BTN_VerProveedor.setEnabled(true);
        this.BTN_VerProveedorInactivo.setVisible(false);
    }//GEN-LAST:event_BTN_VerProveedorInactivoActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Frm_Proveedor().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Activar;
    private javax.swing.JButton BTN_Cerrar1;
    private javax.swing.JButton BTN_Desactivar;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_PDF;
    private javax.swing.JButton BTN_VerProveedor;
    private javax.swing.JButton BTN_VerProveedorInactivo;
    private javax.swing.JTable JTABLE_Mant_Proveedor;
    private javax.swing.JTextField TXT_BuscarProveedor;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtcodigoProveedor;
    private javax.swing.JTextField txtcorreoProveedor;
    private javax.swing.JTextField txtdireccionProveedor;
    private javax.swing.JTextArea txtobservaciones;
    private javax.swing.JTextField txtrazonSocial;
    private javax.swing.JTextField txttelefonoProveedor;
    // End of variables declaration//GEN-END:variables
    //Método para mostrar las Mesaes
    public void MostrarProveedores() {
        //Ordenar ASC, DESC
        JTABLE_Mant_Proveedor.setAutoCreateRowSorter(true);
        //Limpiar la tabla antes de mostrar nuevos datos
        modeloTablaProveedor.setRowCount(0);
        try {
            //Llama al método que retorna los datos de Mesaes
            ResultSet rs = this.PM.listarProveedor();
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("ID"),
                    rs.getString("RUC"),
                    rs.getString("Razón Social (Nombre del Proveedor)"),
                    rs.getString("Teléfono de contacto"),
                    rs.getString("Correo de contacto"),
                    rs.getString("Dirección"),
                    rs.getString("Observaciones"),
                    rs.getInt("Estado") == 1 ? "Activo" : "Inactivo"
                };
                modeloTablaProveedor.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los resultados:\n" + e.getMessage(),
                    "ErrorDeConsulta", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void MostrarProveedoresInactivos() {
        //Ordenar ASC, DESC
        JTABLE_Mant_Proveedor.setAutoCreateRowSorter(true);
        //Limpiar la tabla antes de mostrar nuevos datos
        modeloTablaProveedor.setRowCount(0);
        try {
            //Llama al método que retorna los datos de Mesaes
            ResultSet rs = this.PM.listarProveedorInactivo();
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("ID"),
                    rs.getString("RUC"),
                    rs.getString("Razón Social (Nombre del Proveedor)"),
                    rs.getString("Teléfono de contacto"),
                    rs.getString("Correo de contacto"),
                    rs.getString("Dirección"),
                    rs.getString("Observaciones"),
                    rs.getInt("Estado") == 1 ? "Activo" : "Inactivo"
                };
                modeloTablaProveedor.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los resultados:\n" + e.getMessage(),
                    "ErrorDeConsulta", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void limpiarCamposProveedor() {
            txtcodigoProveedor.setText("");
            txtRuc.setText("");
            txtrazonSocial.setText("");
            txttelefonoProveedor.setText("");
            txtcorreoProveedor.setText("");
            txtdireccionProveedor.setText("");
            BTN_Guardar.setEnabled(true);
            BTN_Nuevo.setEnabled(false);
            BTN_Modificar.setEnabled(false);
            //BTN_Desactivar.setEnabled(false);
            BTN_VerProveedor.setEnabled(false);
        }
    
    public void BuscarProveedorPorNombre() {
        modeloTablaProveedor.setRowCount(0);
        String nombre = TXT_BuscarProveedor.getText().trim();
        try {
            //Llama al método que retorna los datos de Mesaes
            ResultSet rs = this.PM.buscarProovedor(nombre);
            while (rs.next()) {
                Object[] fila = {
                    rs.getString("RUC"),
                    rs.getString("Razón Social (Nombre del Proveedor)"),
                    rs.getString("Teléfono de contacto"),
                    rs.getString("Correo de contacto"),
                    rs.getString("Dirección"),
                    rs.getString("Observaciones")
                };
                modeloTablaProveedor.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar Mesaes:\n" + e.getMessage(),
                    "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        }
    }
}
