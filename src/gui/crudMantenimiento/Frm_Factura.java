package gui.crudMantenimiento;
import com.formdev.flatlaf.FlatLightLaf;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

//Factura
import logic.dao.FacturaMethod;
import connection.ConnectionDB;
 /*excel*/
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;

/*pdf*/
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import logic.dao.FacturaMethod.ComboItem;



public class Frm_Factura extends javax.swing.JFrame {
//Modelo para mostrar datos en ta tabla
DefaultTableModel modeloTablaFactura = new DefaultTableModel();

//Objeto Conexion
FacturaMethod FC = new FacturaMethod();
ConnectionDB CN = new ConnectionDB();

//VAriable para comprobar cambios en mdoificar
// Variables para comprobar cambios al momento de MODIFICAR
// (Usaremos el Comprobante y el ID del pedido para validar si cambiaron)
private Date fechaPagoOriginal;
private Date fechaPedidoOriginal;
private String totalOriginal;
private String tipoPagoOriginal;

    
    
    public Frm_Factura() {
    FlatLightLaf.setup();
    initComponents();
    this.setTitle("Mantenimiento de Facturas");
    this.setLocationRelativeTo(null);
    this.cargarClientes();
    this.cargarEmpleado();
    this.cargarMetodoPago();
    
    // Definir los encabezados para la tabla de Facturas
    String titulos[] = {
        "ID Factura",      // Columna 0 (Generalmente se oculta)
        "ID Pedido",       // Columna 1
        "N° Comprobante",  // Columna 2
        "F. Pago",         // Columna 3
        "F. Registro",     // Columna 4
        "F. Pedido",       // Columna 5
        "Cliente",         // Columna 6
        "Empleado",        // Columna 7
        "Total",           // Columna 8
        "Método de Pago"   // Columna 9
    };
    modeloTablaFactura.setColumnIdentifiers(titulos);
    
    JTABLE_Mant_Factura.setModel(modeloTablaFactura);
    
    // pero tú puedas usarlas para modificar/eliminar
    JTABLE_Mant_Factura.getColumnModel().getColumn(0).setMaxWidth(0);
    JTABLE_Mant_Factura.getColumnModel().getColumn(0).setMinWidth(0);
    JTABLE_Mant_Factura.getColumnModel().getColumn(0).setPreferredWidth(0);
   
    JTABLE_Mant_Factura.getColumnModel().getColumn(1).setMaxWidth(0);
    JTABLE_Mant_Factura.getColumnModel().getColumn(1).setMinWidth(0);
    JTABLE_Mant_Factura.getColumnModel().getColumn(1).setPreferredWidth(0);
    
  
    BTN_Guardar.setEnabled(false);
    BTN_Modificar.setEnabled(false);
    
    this.jComboBox_Cliente.setEnabled(false);
    this.jComboBox_Empleado.setEnabled(false);
    this.TXT_idPedido.setEnabled(false);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TXT_numeroComprobante = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jComboBox_metodoPago = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jComboBox_Cliente = new javax.swing.JComboBox<>();
        jDateChooserFechaPago = new com.toedter.calendar.JDateChooser();
        jLabel17 = new javax.swing.JLabel();
        TXT_codigoFactura = new javax.swing.JTextField();
        TXT_Total = new javax.swing.JTextField();
        jDateChooserFechaPedido = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        TXT_idPedido = new javax.swing.JTextField();
        jComboBox_Empleado = new javax.swing.JComboBox<>();
        jButtonelegirPedido = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_Factura = new javax.swing.JTable();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXT_BuscarFacturas = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        BTN_Cerrar1 = new javax.swing.JButton();
        BTN_PDF = new javax.swing.JButton();
        BTN_ListarFacturas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("MANTENIMIENTO DE FACTURA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 240, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Codigo del pedido");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Metodo de pago*");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("numero de comprobante*");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        TXT_numeroComprobante.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TXT_numeroComprobante.setForeground(new java.awt.Color(0, 0, 204));
        TXT_numeroComprobante.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TXT_numeroComprobante.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        TXT_numeroComprobante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_numeroComprobanteKeyTyped(evt);
            }
        });
        jPanel1.add(TXT_numeroComprobante, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 110, 270, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Fecha de pago*");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        jComboBox_metodoPago.setEditable(true);
        jComboBox_metodoPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_metodoPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_metodoPagoActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox_metodoPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 150, 280, 20));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Empleado*");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 70, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Total*");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, -1, -1));

        jComboBox_Cliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_ClienteActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox_Cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, 280, 20));
        jPanel1.add(jDateChooserFechaPago, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 270, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Cliente*");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, -1, -1));

        TXT_codigoFactura.setEditable(false);
        TXT_codigoFactura.setBackground(new java.awt.Color(255, 255, 255));
        TXT_codigoFactura.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TXT_codigoFactura.setForeground(new java.awt.Color(0, 0, 204));
        TXT_codigoFactura.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TXT_codigoFactura.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(TXT_codigoFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 270, 20));

        TXT_Total.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TXT_Total.setForeground(new java.awt.Color(0, 0, 204));
        TXT_Total.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TXT_Total.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(TXT_Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 110, 280, 20));
        jPanel1.add(jDateChooserFechaPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 190, 270, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Fecha de pedido*");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Codigo de la factura");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));

        TXT_idPedido.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TXT_idPedido.setForeground(new java.awt.Color(0, 0, 204));
        TXT_idPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TXT_idPedido.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        TXT_idPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_idPedidoActionPerformed(evt);
            }
        });
        jPanel1.add(TXT_idPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, 270, 20));

        jComboBox_Empleado.setEditable(true);
        jComboBox_Empleado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox_Empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_EmpleadoActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox_Empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 70, 280, 20));

        jButtonelegirPedido.setText("Elegir pedido");
        jButtonelegirPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonelegirPedidoActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonelegirPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 80, 110, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 1040, 290));

        JTABLE_Mant_Factura.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_Factura.setForeground(new java.awt.Color(0, 0, 204));
        JTABLE_Mant_Factura.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Mant_Factura.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_FacturaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Mant_Factura);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 450, 1120, 220));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setText("NUEVO");
        BTN_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_NuevoActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 190, 50));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setText("GUARDAR");
        BTN_Guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_GuardarMouseClicked(evt);
            }
        });
        BTN_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_GuardarActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 330, 190, 50));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setText("MODIFICAR");
        BTN_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ModificarActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 330, 200, 50));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setText("Exportar");
        BTN_EXCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_EXCELActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 690, 140, 50));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingresar el Nombre de la Escuela Profecional");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, 30));

        TXT_BuscarFacturas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BuscarFacturasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_BuscarFacturasKeyTyped(evt);
            }
        });
        jPanel2.add(TXT_BuscarFacturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 360, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("BUSCAR");
        jLabel5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jLabel5KeyReleased(evt);
            }
        });
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 10, 120, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 1120, 50));

        BTN_Cerrar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar1.setText("Cerrar");
        BTN_Cerrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_Cerrar1ActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_Cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 690, 140, 50));

        BTN_PDF.setText("Exportar");
        BTN_PDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_PDFActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_PDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 690, 140, 50));

        BTN_ListarFacturas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_ListarFacturas.setText("LISTAR FACTURAS");
        BTN_ListarFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ListarFacturasActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_ListarFacturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 330, 180, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JTABLE_Mant_FacturaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_FacturaMouseClicked
    int filaSeleccionada = JTABLE_Mant_Factura.getSelectedRow();

    // 1. Configuración de botones
    BTN_Modificar.setEnabled(filaSeleccionada != -1);
    BTN_Guardar.setEnabled(false);

    if (filaSeleccionada != -1) {
        // 2. Extracción de datos (Índices basados en tus títulos)
        String idFactura   = JTABLE_Mant_Factura.getValueAt(filaSeleccionada, 0).toString();
        String idPedido    = JTABLE_Mant_Factura.getValueAt(filaSeleccionada, 1).toString();
        String comprobante = JTABLE_Mant_Factura.getValueAt(filaSeleccionada, 2).toString();
        String fPagoStr    = JTABLE_Mant_Factura.getValueAt(filaSeleccionada, 3).toString();
        String fPedidoStr  = JTABLE_Mant_Factura.getValueAt(filaSeleccionada, 5).toString();
        String cliente     = JTABLE_Mant_Factura.getValueAt(filaSeleccionada, 6).toString();
        String empleado    = JTABLE_Mant_Factura.getValueAt(filaSeleccionada, 7).toString();
        String total       = JTABLE_Mant_Factura.getValueAt(filaSeleccionada, 8).toString();
        String tipoPago    = JTABLE_Mant_Factura.getValueAt(filaSeleccionada, 9).toString();

        // 3. Llenar campos de texto
        TXT_codigoFactura.setText(idFactura);
        TXT_idPedido.setText(idPedido);
        TXT_numeroComprobante.setText(comprobante);
        TXT_Total.setText(total);

        // 4. Llenar Fechas
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            jDateChooserFechaPago.setDate(formato.parse(fPagoStr));
        } catch (Exception e) { jDateChooserFechaPago.setDate(null); }

        try {
            jDateChooserFechaPedido.setDate(formato.parse(fPedidoStr));
        } catch (Exception e) { jDateChooserFechaPedido.setDate(null); }

        // 5. Seleccionar en Combos usando el método auxiliar
        seleccionarEnCombo(jComboBox_Cliente, cliente);
        seleccionarEnCombo(jComboBox_Empleado, empleado);
        seleccionarEnCombo(jComboBox_metodoPago, tipoPago);
        BTN_Nuevo.setEnabled(true);
    }

    }//GEN-LAST:event_JTABLE_Mant_FacturaMouseClicked

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
        this.limpiarCamposFactura();
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
    try {
        // 1. VALIDACIÓN DE COMBOS (Evita el ClassCastException)
        // Verificamos que lo seleccionado sea un objeto ComboItem y no un String
        if (!(jComboBox_Cliente.getSelectedItem() instanceof FacturaMethod.ComboItem) ||
            !(jComboBox_Empleado.getSelectedItem() instanceof FacturaMethod.ComboItem) ||
            !(jComboBox_metodoPago.getSelectedItem() instanceof FacturaMethod.ComboItem)) {
            
            JOptionPane.showMessageDialog(this, "Por favor, seleccione elementos válidos de las listas.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. EXTRACCIÓN DE OBJETOS COMBOITEM
        FacturaMethod.ComboItem cliente = (FacturaMethod.ComboItem) jComboBox_Cliente.getSelectedItem();
        FacturaMethod.ComboItem empleado = (FacturaMethod.ComboItem) jComboBox_Empleado.getSelectedItem();
        FacturaMethod.ComboItem pago = (FacturaMethod.ComboItem) jComboBox_metodoPago.getSelectedItem();

        // 3. VALIDACIÓN DE SELECCIÓN (ID 0 es "<<Seleccionar>>")
        if (cliente.getId() == 0 || empleado.getId() == 0 || pago.getId() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un Cliente, Empleado y Método de Pago real.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 4. CAPTURA DE DATOS DE TEXTO Y NÚMEROS
        String numComprobante = TXT_numeroComprobante.getText().trim();
        String totalStr = TXT_Total.getText().trim();
        String idPedidoStr = TXT_idPedido.getText().trim();

        if (numComprobante.isEmpty() || totalStr.isEmpty() || idPedidoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos los campos de texto son obligatorios.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double total = Double.parseDouble(totalStr);
        int idPedido = Integer.parseInt(idPedidoStr);

        // 5. MANEJO DE FECHA (JDateChooser)
        if (jDateChooserFechaPago.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha de pago válida.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Convertimos java.util.Date a java.sql.Date para la base de datos
        java.sql.Date fechaSql = new java.sql.Date(jDateChooserFechaPago.getDate().getTime());
        
        

        // 6. EJECUCIÓN EN LA BASE DE DATOS
        // Suponiendo que tienes un método registrarFactura en tu clase FC
        // El orden debe coincidir con tu Procedimiento Almacenado de inserción
        FC.insertarFactura(numComprobante, fechaSql, total, idPedido, pago.getId());

        // 7. FINALIZACIÓN
        JOptionPane.showMessageDialog(this, "Factura guardada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        
        listarFacturas(); // Refrescar la tabla
        // limpiarCampos(); // Método opcional para vaciar el formulario

    }catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Error: El Total y el ID Pedido deben ser números.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
    }
    // Aquí se capturan los SIGNAL SQLSTATE de tu base de datos
     catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
   try {
    // 1. Captura de datos (ID's y Comprobante se capturan pero no se comparan para cambios)
    int idFactura = Integer.parseInt(TXT_codigoFactura.getText());
    int idPedido = Integer.parseInt(TXT_idPedido.getText());
    String comprobante = TXT_numeroComprobante.getText().trim();
    
    // Datos que SÍ puede modificar
    String totalStr = TXT_Total.getText().trim();
    double total = Double.parseDouble(totalStr);
    
    if (jDateChooserFechaPago.getDate() == null) {
        JOptionPane.showMessageDialog(this, "Debe ingresar una fecha de pago.");
        return;
    }
    java.sql.Date fechaSql = new java.sql.Date(jDateChooserFechaPago.getDate().getTime());

    // El JDateChooser del pedido (si es que también lo puede editar)
    java.sql.Date fechaPedidoSql = new java.sql.Date(jDateChooserFechaPedido.getDate().getTime());

    FacturaMethod.ComboItem pagoItem = (FacturaMethod.ComboItem) jComboBox_metodoPago.getSelectedItem();

    // ============================================================
    // VALIDACIÓN LIMITADA A CAMPOS EDITABLES
    // ============================================================
    boolean huboCambios = !totalStr.equals(totalOriginal) ||
                          !pagoItem.toString().equals(tipoPagoOriginal) ||
                          !fechaSql.toString().equals(fechaPagoOriginal.toString()) ||
                          !fechaPedidoSql.toString().equals(fechaPedidoOriginal.toString());

    if (!huboCambios) {
        JOptionPane.showMessageDialog(this, "No has modificado la fecha, el total o el método de pago.", "Sin cambios", JOptionPane.INFORMATION_MESSAGE);
        return; 
    }
    // ============================================================

    // 4. Ejecución
    FC.modificarFactura(idFactura, comprobante, fechaSql, total, idPedido, pagoItem.getId());

    JOptionPane.showMessageDialog(this, "Factura actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    
    guardarEstadoOriginal(); 
    listarFacturas();

} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(this, "Error en el formato del total.", "Error", JOptionPane.ERROR_MESSAGE);
} catch (SQLException e) {
    JOptionPane.showMessageDialog(this, e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
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
            for (int i = 0; i < JTABLE_Mant_Factura.getColumnCount(); i++) {
                headerRow.createCell(i).setCellValue(JTABLE_Mant_Factura.getColumnName(i));
            }

            // 4. Escribir los datos fila por fila desde el JTable
            for (int i = 0; i < JTABLE_Mant_Factura.getRowCount(); i++) {
                XSSFRow dataRow = sheet.createRow(i + 1); // Fila 1 en adelante
                for (int j = 0; j < JTABLE_Mant_Factura.getColumnCount(); j++) {
                    Object valor = JTABLE_Mant_Factura.getValueAt(i, j);
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

    private void TXT_BuscarFacturasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarFacturasKeyReleased
        //XX
        
    }//GEN-LAST:event_TXT_BuscarFacturasKeyReleased

    private void TXT_BuscarFacturasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarFacturasKeyTyped
        this.buscarFacturaPorNombre();
    }//GEN-LAST:event_TXT_BuscarFacturasKeyTyped

    private void BTN_Cerrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_Cerrar1ActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(this, 
        "¿Estás seguro de que deseas cerrar el formulario?", 
        "Confirmar salida", 
        JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
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
            PdfPTable pdfTable = new PdfPTable(JTABLE_Mant_Factura.getColumnCount());
            pdfTable.setWidthPercentage(100);

            // Encabezados
            for (int i = 0; i < JTABLE_Mant_Factura.getColumnCount(); i++) {
                PdfPCell cell = new PdfPCell(new Phrase(JTABLE_Mant_Factura.getColumnName(i)));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                pdfTable.addCell(cell);
            }

            // Filas de datos
            for (int row = 0; row < JTABLE_Mant_Factura.getRowCount(); row++) {
                for (int col = 0; col < JTABLE_Mant_Factura.getColumnCount(); col++) {
                    Object value = JTABLE_Mant_Factura.getValueAt(row, col);
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

    private void BTN_ListarFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ListarFacturasActionPerformed
        this.listarFacturas();
        this.BTN_Nuevo.setEnabled(true);
        this.BTN_Guardar.setEnabled(false);
        this.BTN_Modificar.setEnabled(false);
        this.BTN_ListarFacturas.setEnabled(false);
        //jPanel3.requestFocus();
    }//GEN-LAST:event_BTN_ListarFacturasActionPerformed

    private void jLabel5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel5KeyReleased
       //
    }//GEN-LAST:event_jLabel5KeyReleased

    private void jComboBox_EmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_EmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_EmpleadoActionPerformed

    private void jComboBox_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_ClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_ClienteActionPerformed

    private void jComboBox_metodoPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_metodoPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_metodoPagoActionPerformed

    private void TXT_numeroComprobanteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_numeroComprobanteKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_numeroComprobanteKeyTyped

    private void jButtonelegirPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonelegirPedidoActionPerformed
                                                  
    // 1. Instanciamos el diálogo (this es el JFrame principal, true es para que sea modal)
    JD_BuscarPedido buscar = new JD_BuscarPedido(this, true);
    
    // 2. Centrar el diálogo respecto a la ventana principal
    buscar.setLocationRelativeTo(this);
    
    // 3. Mostrar la ventana
    buscar.setVisible(true);

    }//GEN-LAST:event_jButtonelegirPedidoActionPerformed

    private void TXT_idPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_idPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_idPedidoActionPerformed

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
            java.util.logging.Logger.getLogger(Frm_Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_Factura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_Factura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Cerrar1;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_ListarFacturas;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_PDF;
    private javax.swing.JTable JTABLE_Mant_Factura;
    private javax.swing.JTextField TXT_BuscarFacturas;
    private javax.swing.JTextField TXT_Total;
    private javax.swing.JTextField TXT_codigoFactura;
    public javax.swing.JTextField TXT_idPedido;
    private javax.swing.JTextField TXT_numeroComprobante;
    private javax.swing.JButton jButtonelegirPedido;
    public javax.swing.JComboBox<Object> jComboBox_Cliente;
    public javax.swing.JComboBox<Object> jComboBox_Empleado;
    private javax.swing.JComboBox<Object> jComboBox_metodoPago;
    private com.toedter.calendar.JDateChooser jDateChooserFechaPago;
    private com.toedter.calendar.JDateChooser jDateChooserFechaPedido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    //Listar facturas
    //llenar elementos en el jcombobox el metodo viene de la clase
    //y la consulta de la vista
    private void cargarClientes() {
        try {
            jComboBox_Cliente.removeAllItems();

            // En lugar de un String puro, usamos un objeto con ID 0 o -1
            jComboBox_Cliente.addItem(new FacturaMethod.ComboItem(0, "<<Seleccionar>>"));

            ResultSet rs = FC.combobox_listarCliente();
            while (rs.next()) {
                jComboBox_Cliente.addItem(new FacturaMethod.ComboItem(
                        rs.getInt("id_cliente"),
                        rs.getString("Info_Cliente")
                ));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    private void cargarEmpleado() {
        try {
            jComboBox_Empleado.removeAllItems();
            jComboBox_Empleado.addItem(new FacturaMethod.ComboItem(0, "<<Seleccionar>>"));

            ResultSet rs = FC.combobox_listarEmpleado();
            while (rs.next()) {
                // Juntamos las piezas manualmente para que coincida con el JDialog
                String dni = rs.getString("DNI");
                String nombre = rs.getString("Nombre de Empleado");
                String apellido = rs.getString("Apellido de Empleado");

                // Este formato debe ser IDENTICO al del JDialog: "DNI - Nombre Apellido"
                String formatoCompleto = dni + " - " + nombre + " " + apellido;

                // Suponiendo que tu vista tiene el id_empleado, si no, usa el DNI como ID
                jComboBox_Empleado.addItem(new FacturaMethod.ComboItem(
                        rs.getInt("DNI"), // O el id_empleado si lo agregas a la vista
                        formatoCompleto
                ));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    private void cargarMetodoPago() {
        try {
            jComboBox_metodoPago.removeAllItems();
            // USAR ComboItem en lugar de String
            jComboBox_metodoPago.addItem(new FacturaMethod.ComboItem(0, "<<Seleccionar>>"));

            ResultSet rs = FC.combobox_listartipoPago();
            while (rs.next()) {
                jComboBox_metodoPago.addItem(new FacturaMethod.ComboItem(
                        rs.getInt("id_tipo_pago"), // Revisa si el ID se llama así en tu RS
                        rs.getString("nombre_tipo_pago")
                ));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar los tipos de pago: " + e.getMessage());
        }
    }
   
    
    //método para listar productos
    private void listarFacturas() {
        try {
            // 1. Configuración de la tabla
            JTABLE_Mant_Factura.setAutoCreateRowSorter(true);
            modeloTablaFactura.setRowCount(0);

            // 2. Obtener datos
            ResultSet rs = FC.listarFacturas();

           while (rs.next()) {
                Object fila[] = {
                    rs.getInt("ID"),
                    rs.getInt("ID Pedido"),
                    rs.getString("Comprobante"),
                    // Usamos getString porque en el SQL usaste DATE_FORMAT
                    rs.getString("Fecha de Pago"),
                    rs.getString("Fecha de Registro"),
                    rs.getString("Fecha de Pedido"),
                    // Cambiamos a getString porque es un CONCAT (nombre completo)
                    rs.getString("Cliente"),
                    rs.getString("Empleado"),
                    // Usamos getDouble para valores monetarios
                    rs.getDouble("Total"),
                    // El nombre debe coincidir EXACTAMENTE con el alias de la vista
                    rs.getString("Tipo de Pago")
                };
                modeloTablaFactura.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar la tabla de facturas: " + e.getMessage(), "Error de Datos", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void limpiarCamposFactura() {
            TXT_codigoFactura.setText("");
            TXT_Total.setText("");
            TXT_idPedido.setText("");
            TXT_numeroComprobante.setText("");
            jComboBox_Cliente.setSelectedIndex(0); // O -1 si quieres dejarlo vacío
            jComboBox_Empleado.setSelectedIndex(0);
            jComboBox_metodoPago.setSelectedIndex(0);
            jDateChooserFechaPago.setDate(new java.util.Date());
            jDateChooserFechaPedido.setDate(new java.util.Date());
            BTN_Guardar.setEnabled(true);
            BTN_Nuevo.setEnabled(false);
            BTN_Modificar.setEnabled(false);
            BTN_ListarFacturas.setEnabled(false);

        }
    //Método para mostrar las facultades
    public void buscarFacturaPorNombre() {
        String nombreBusqueda = TXT_BuscarFacturas.getText().trim();

        // 1. Si el buscador está vacío, regresamos a la lista completa
        if (nombreBusqueda.isEmpty()) {
            this.listarFacturas();
            return;
        }

        // 2. Limpiar la tabla antes de mostrar los resultados de la búsqueda
        modeloTablaFactura.setRowCount(0);

        try {
            boolean hayResultados = false;

            // Llamamos al método que acabas de mostrar (FC es tu objeto FacturaMethod)
            ResultSet rs = this.FC.buscarFactura(nombreBusqueda);

            while (rs != null && rs.next()) {
                hayResultados = true;

                // IMPORTANTE: Los nombres dentro de rs.getString deben ser 
                // EXACTAMENTE los que devuelve tu procedimiento "buscar_factura"
                Object[] fila = {
                    rs.getString(1), // ID Factura
                    rs.getString(2), // ID Pedido
                    rs.getString(3), // N° Comprobante
                    rs.getString(4), // Fecha Pago
                    rs.getString(5), // Fecha Registro
                    rs.getString(6), // Fecha Pedido
                    rs.getString(7), // Cliente
                    rs.getString(8), // Empleado
                    rs.getString(9), // Total
                    rs.getString(10) // Método Pago
                };
                modeloTablaFactura.addRow(fila);
            }

            // Si no hay resultados, no lanzamos un JOptionPane (para no interrumpir la escritura)
            // Pero podrías imprimir en consola o en un JLabel de estado.
            if (!hayResultados) {
                JOptionPane.showMessageDialog(this, "No se encontraron facturas para: " + nombreBusqueda, "Sin resultados", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al ejecutar la búsqueda:\n" + e.getMessage(),
                    "Error de Base de Datos", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Permite verificar si el nombre buscado esta en el jcombobox
    void seleccionarEnCombo(javax.swing.JComboBox combo, String nombreTabla) {
        if (nombreTabla == null || nombreTabla.trim().isEmpty()) {
            combo.setSelectedIndex(0);
            return;
        }

        String nombreBuscado = nombreTabla.trim().toUpperCase();

        for (int i = 0; i < combo.getItemCount(); i++) {
            Object item = combo.getItemAt(i);
            if (item != null) {
                String textoCombo = item.toString().toUpperCase();

                // Usamos contains porque el combo tiene "DNI - NOMBRE" 
                // y la tabla solo tiene "NOMBRE"
                if (textoCombo.contains(nombreBuscado)) {
                    combo.setSelectedIndex(i);
                    return;
                }
            }
        }
        // Si no hay coincidencia, vuelve al inicio
        combo.setSelectedIndex(0);
    }
    
    private void guardarEstadoOriginal() {
        // Capturamos lo que hay actualmente en los campos editables
        totalOriginal = TXT_Total.getText().trim();
        tipoPagoOriginal = jComboBox_metodoPago.getSelectedItem().toString();

        // Guardamos las fechas (manejando posibles nulos para evitar errores)
        if (jDateChooserFechaPago.getDate() != null) {
            fechaPagoOriginal = new java.sql.Date(jDateChooserFechaPago.getDate().getTime());
        }

        if (jDateChooserFechaPedido.getDate() != null) {
            fechaPedidoOriginal = new java.sql.Date(jDateChooserFechaPedido.getDate().getTime());
        }
    }
}
