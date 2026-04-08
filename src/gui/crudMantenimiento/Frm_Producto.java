package gui.crudMantenimiento;

import com.formdev.flatlaf.FlatLightLaf;
import java.sql.ResultSet;
import connection.ConnectionDB;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//Metodo de producto
import logic.dao.ProductoMethod;
//Metodo de unidad medida para el jcombobox
import logic.dao.UnidadMedidaMethod;
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
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.UIManager;

public class Frm_Producto extends javax.swing.JFrame {
    //Modelo para mostrar datos en ta tabla
    DefaultTableModel modeloTablaProducto = new DefaultTableModel();
    
    //Objeto conexión a la base de datos
    ProductoMethod PR = new ProductoMethod();
    UnidadMedidaMethod UM = new UnidadMedidaMethod();
    ConnectionDB CBD = new ConnectionDB();

    //VAriable para comprobar cambios en mdoificar
    private String nombreOriginal;
    private String precioOriginal;
    private String stockActualOriginal;
    private String stockMinimoOriginal;
    private String ObservacionOriginal;
    private String unidadOriginal = "";
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Frm_Producto.class.getName());

    public Frm_Producto() {
        FlatLightLaf.setup();
        initComponents();
        
        // --- CONFIGURACIÓN GLOBAL ---
    UIManager.put("Button.arc", 15);           // Redondeo de botones
    UIManager.put("TextComponent.arc", 10);    // Redondeo de campos de texto
    this.setResizable(false);                  // Bloquea redimensionado
    this.setLocationRelativeTo(null);          // Centra la ventana
    this.setTitle("Mantenimiento de Productos - Bistro System");

    // --- APLICACIÓN DE COLORES (ESTILO BISTRO) ---
    this.getContentPane().setBackground(new Color(0xFFFFFF));
    
    // Panel de datos (Blanco con borde fino)
    jPanel1.setBackground(new Color(0xFFFFFF)); 
    jPanel1.setBorder(BorderFactory.createLineBorder(new Color(0xE5E5D1), 1));
    
    // Paneles de diseño (Café oscuro para contraste)
    jPanel2.setBackground(new Color(0x3E2723)); // Panel de búsqueda
    jPanel3.setBackground(new Color(0xFDFCF0)); // Panel lateral/fondo
    
    // --- COLORES DE BOTONES ---
    BTN_Guardar.setBackground(new Color(0x274C5B)); // Azul Petróleo
    BTN_Guardar.setForeground(Color.WHITE);
    
    BTN_Modificar.setBackground(new Color(0xE67E22)); // Naranja desaturado
    BTN_Modificar.setForeground(Color.WHITE);
    
    BTN_PDF.setBackground(new Color(0xE74C3C)); // Rojo Alizarin
    BTN_PDF.setForeground(Color.WHITE);
    
    BTN_EXCEL.setBackground(new Color(0x27AE60)); // Verde Esmeralda
    BTN_EXCEL.setForeground(Color.WHITE);
    
    BTN_Nuevo.setBackground(new Color(0x7EB693)); // Verde Sage
    BTN_Nuevo.setForeground(Color.WHITE);
    
    BTN_Cerrar1.setBackground(new Color(0x95A5A6)); // Gris Asfalto
    BTN_Cerrar1.setForeground(Color.WHITE);
    
    BTN_VerProductos.setBackground(new Color(0x546E7A)); // Azul Grisáceo
    BTN_VerProductos.setForeground(Color.WHITE);
    
    BTN_Cancel.setBackground(new Color(0xBDC3C7)); // Gris claro
    BTN_Cancel.setForeground(Color.BLACK);
        
        
        //Posicion
        this.setLocationRelativeTo(null);
       
        //Metodo para cargar el combobox
        cargarUnidadMedida();
        
        //definir los encabezados de la tabla
        String titulos[]={"ID","Nombre del producto","unidad de medida","Abreviatura","Precio del producto", "Stock Minimo","Stock Actual","Observaciones"};
        
        //Asignar los tiutlos al modelo
        modeloTablaProducto.setColumnIdentifiers(titulos);
        
        //Establecer el modelo a la JTable
        JTABLE_Mant_Producto.setModel(modeloTablaProducto);
        
        //ocultar columnas sesibles o internas: solo visual, noafecta al modelo, ocultar columna "id_producto y observacion_producto"
        JTABLE_Mant_Producto.getColumnModel().getColumn(0).setMinWidth(0);
        JTABLE_Mant_Producto.getColumnModel().getColumn(0).setMaxWidth(0);
        JTABLE_Mant_Producto.getColumnModel().getColumn(0).setWidth(0);
        
        //JTABLE_Mant_Producto.getColumnModel().getColumn(7).setMinWidth(0);
        //JTABLE_Mant_Producto.getColumnModel().getColumn(7).setMaxWidth(0);
        //JTABLE_Mant_Producto.getColumnModel().getColumn(7).setWidth(0);
        
        //Desabilitar campo de codigo (solo se mostrara no se escribe)
        txtcodigoproducto.setEnabled(false);
        BTN_Cancel.setEnabled(false);
        BTN_Nuevo.setEnabled(false);
        BTN_Guardar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        //BTN_Desactivar.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtcodigoproducto = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtstockActual = new javax.swing.JTextField();
        jComboBox_unidad_medida = new javax.swing.JComboBox<>();
        txtNombreProducto = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtstockMinimo = new javax.swing.JTextField();
        txtPreciounitario = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtobservaciones = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_Producto = new javax.swing.JTable();
        BTN_EXCEL = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXT_BuscarProductos = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        BTN_Cerrar1 = new javax.swing.JButton();
        BTN_PDF = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        BTN_VerProductos = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Cancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MANTENIMIENTO DE PRODUCTO");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 830, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel2.setText("Codigo del  Producto");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Stock actual");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 80, -1));

        txtcodigoproducto.setEditable(false);
        txtcodigoproducto.setBackground(new java.awt.Color(255, 255, 255));
        txtcodigoproducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcodigoproducto.setForeground(new java.awt.Color(0, 0, 204));
        txtcodigoproducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcodigoproducto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtcodigoproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 160, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Nombre del Producto*");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 140, -1));

        txtstockActual.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtstockActual.setForeground(new java.awt.Color(0, 0, 204));
        txtstockActual.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtstockActual.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtstockActual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtstockActualKeyTyped(evt);
            }
        });
        jPanel1.add(txtstockActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 140, 30));

        jComboBox_unidad_medida.addActionListener(this::jComboBox_unidad_medidaActionPerformed);
        jPanel1.add(jComboBox_unidad_medida, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 330, 30));

        txtNombreProducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNombreProducto.setForeground(new java.awt.Color(0, 0, 204));
        txtNombreProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreProducto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtNombreProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreProductoKeyTyped(evt);
            }
        });
        jPanel1.add(txtNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 330, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Precio unitario*");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 20, 100, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Observacion");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, 100, -1));

        txtstockMinimo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtstockMinimo.setForeground(new java.awt.Color(0, 0, 204));
        txtstockMinimo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtstockMinimo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtstockMinimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtstockMinimoKeyTyped(evt);
            }
        });
        jPanel1.add(txtstockMinimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 140, 140, 30));

        txtPreciounitario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPreciounitario.setForeground(new java.awt.Color(0, 0, 204));
        txtPreciounitario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPreciounitario.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtPreciounitario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPreciounitarioKeyTyped(evt);
            }
        });
        jPanel1.add(txtPreciounitario, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 40, 140, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Stock mínimo*");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 90, -1));

        txtobservaciones.setColumns(20);
        txtobservaciones.setRows(5);
        jScrollPane3.setViewportView(txtobservaciones);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 220, 330, 60));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("unidad de medida");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, 100, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 730, 300));

        JTABLE_Mant_Producto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_Producto.setForeground(new java.awt.Color(0, 0, 204));
        JTABLE_Mant_Producto.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Mant_Producto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_ProductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Mant_Producto);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 920, 220));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/excel.png"))); // NOI18N
        BTN_EXCEL.setText("     Exportar XLSX");
        BTN_EXCEL.addActionListener(this::BTN_EXCELActionPerformed);
        getContentPane().add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 650, 170, 50));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingresar el Nombre del Producto");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, 30));

        TXT_BuscarProductos.addActionListener(this::TXT_BuscarProductosActionPerformed);
        TXT_BuscarProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BuscarProductosKeyReleased(evt);
            }
        });
        jPanel2.add(TXT_BuscarProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 13, 290, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_search_white.png"))); // NOI18N
        jLabel5.setText("BUSCAR");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 120, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 920, 50));

        BTN_Cerrar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_cancel.png"))); // NOI18N
        BTN_Cerrar1.setText("     Cerrar");
        BTN_Cerrar1.addActionListener(this::BTN_Cerrar1ActionPerformed);
        getContentPane().add(BTN_Cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 650, 165, 50));

        BTN_PDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pdf.png"))); // NOI18N
        BTN_PDF.setText("     Exportar PDF");
        BTN_PDF.addActionListener(this::BTN_PDFActionPerformed);
        getContentPane().add(BTN_PDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 650, 170, 50));

        jPanel3.setBackground(new java.awt.Color(253, 252, 240));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BTN_VerProductos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_VerProductos.setText("VER PRODUCTOS");
        BTN_VerProductos.addActionListener(this::BTN_VerProductosActionPerformed);
        jPanel3.add(BTN_VerProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, 165, 50));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_cancel_cogs.png"))); // NOI18N
        BTN_Modificar.setText("    MODIFICAR");
        BTN_Modificar.addActionListener(this::BTN_ModificarActionPerformed);
        jPanel3.add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 280, 165, 48));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_save.png"))); // NOI18N
        BTN_Guardar.setText("     GUARDAR");
        BTN_Guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_GuardarMouseClicked(evt);
            }
        });
        BTN_Guardar.addActionListener(this::BTN_GuardarActionPerformed);
        jPanel3.add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 200, 165, 48));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_add.png"))); // NOI18N
        BTN_Nuevo.setText("      NUEVO");
        BTN_Nuevo.addActionListener(this::BTN_NuevoActionPerformed);
        jPanel3.add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 110, 165, 48));

        BTN_Cancel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_delete.png"))); // NOI18N
        BTN_Cancel.setText("     CANCELAR");
        BTN_Cancel.addActionListener(this::BTN_CancelActionPerformed);
        jPanel3.add(BTN_Cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 110, 165, 48));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtstockActualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstockActualKeyTyped
 
    }//GEN-LAST:event_txtstockActualKeyTyped

    private void txtNombreProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProductoKeyTyped
 
    }//GEN-LAST:event_txtNombreProductoKeyTyped

    private void JTABLE_Mant_ProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_ProductoMouseClicked
        int filaSeleccionada = JTABLE_Mant_Producto.getSelectedRow();
        BTN_Modificar.setEnabled(true);
        //BTN_Desactivar.setEnabled(true);
        BTN_Guardar.setEnabled(false);
        BTN_Cancel.setVisible(false);
        BTN_Cancel.setEnabled(false);
        BTN_VerProductos.setEnabled(false);
        BTN_Nuevo.setVisible(true);
        BTN_Nuevo.setEnabled(true);


        if (filaSeleccionada != -1) {
            // Obtener los datos de la fila seleccionada
            String codigo = JTABLE_Mant_Producto.getValueAt(filaSeleccionada, 0).toString().trim();
            String nombreProducto = JTABLE_Mant_Producto.getValueAt(filaSeleccionada, 1).toString().trim();
            String precioUnitario = JTABLE_Mant_Producto.getValueAt(filaSeleccionada, 4).toString().trim();
            String stockActual = JTABLE_Mant_Producto.getValueAt(filaSeleccionada, 6).toString().trim();
            String stockMinimo = JTABLE_Mant_Producto.getValueAt(filaSeleccionada, 5).toString().trim();
            String unidadDeMedida = JTABLE_Mant_Producto.getValueAt(filaSeleccionada, 2).toString().trim();
            
            //Para observacion
            Object celdaObservacion = JTABLE_Mant_Producto.getValueAt(filaSeleccionada, 7);
            String observacionProducto = (celdaObservacion != null) ? celdaObservacion.toString().trim() : "";
            
            
            // Mostrar en los controles
            txtcodigoproducto.setText(codigo);
            txtNombreProducto.setText(nombreProducto);
            txtPreciounitario.setText(precioUnitario);
            txtstockActual.setText(stockActual);
            txtstockMinimo.setText(stockMinimo);
            txtobservaciones.setText(observacionProducto);

            // Guardar valores originales para comparación
            nombreOriginal = nombreProducto;
            precioOriginal = precioUnitario;
            stockActualOriginal = stockActual;
            stockMinimoOriginal = stockMinimo;
            unidadOriginal = unidadDeMedida;
            ObservacionOriginal = observacionProducto;

            // Buscar coincidencia en el ComboBox ignorando mayúsculas
            boolean encontrado = false;
            for (int i = 0; i < jComboBox_unidad_medida.getItemCount(); i++) {
                String item = jComboBox_unidad_medida.getItemAt(i).trim();
                if (item.equalsIgnoreCase(unidadDeMedida)) {
                    jComboBox_unidad_medida.setSelectedIndex(i);
                    encontrado = true;
                    break;
                }
            }

            // Si no se encontró, mostrar alerta opcional
            if (!encontrado) {
                JOptionPane.showMessageDialog(this,
                    "No se encontró la unidad de medida en la lista del combo.",
                    "Facultad no encontrada", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_JTABLE_Mant_ProductoMouseClicked

    private void BTN_EXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EXCELActionPerformed
        try {
            // 1. Crear un nuevo libro de Excel (.xlsx)
            XSSFWorkbook workbook = new XSSFWorkbook();

            // 2. Crear una hoja dentro del libro
            XSSFSheet sheet = workbook.createSheet("Facultades");

            // 3. Escribir la fila de encabezados desde el JTable
            XSSFRow headerRow = sheet.createRow(0); // Fila 0 para encabezados
            for (int i = 0; i < JTABLE_Mant_Producto.getColumnCount(); i++) {
                headerRow.createCell(i).setCellValue(JTABLE_Mant_Producto.getColumnName(i));
            }

            // 4. Escribir los datos fila por fila desde el JTable
            for (int i = 0; i < JTABLE_Mant_Producto.getRowCount(); i++) {
                XSSFRow dataRow = sheet.createRow(i + 1); // Fila 1 en adelante
                for (int j = 0; j < JTABLE_Mant_Producto.getColumnCount(); j++) {
                    Object valor = JTABLE_Mant_Producto.getValueAt(i, j);
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

    private void TXT_BuscarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_BuscarProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_BuscarProductosActionPerformed

    private void TXT_BuscarProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarProductosKeyReleased
        this.buscarProductosPorNombre();
    }//GEN-LAST:event_TXT_BuscarProductosKeyReleased

    private void BTN_Cerrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_Cerrar1ActionPerformed
        int confirmacion = JOptionPane.showConfirmDialog(this, 
        "¿Estás seguro de que deseas cerrar el formulario?", 
        "Confirmar salida", 
        JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
            // Cerrar conexión si tienes un método cerrarConexion()
            CBD.closeConnection();
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
            PdfPTable pdfTable = new PdfPTable(JTABLE_Mant_Producto.getColumnCount());
            pdfTable.setWidthPercentage(100);

            // Encabezados
            for (int i = 0; i < JTABLE_Mant_Producto.getColumnCount(); i++) {
                PdfPCell cell = new PdfPCell(new Phrase(JTABLE_Mant_Producto.getColumnName(i)));
                cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                pdfTable.addCell(cell);
            }

            // Filas de datos
            for (int row = 0; row < JTABLE_Mant_Producto.getRowCount(); row++) {
                for (int col = 0; col < JTABLE_Mant_Producto.getColumnCount(); col++) {
                    Object value = JTABLE_Mant_Producto.getValueAt(row, col);
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

    private void BTN_VerProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerProductosActionPerformed
        this.listarProductos();
        this.BTN_Nuevo.setEnabled(true);
        this.BTN_Guardar.setEnabled(false);
        //this.BTN_Desactivar.setEnabled(false);
        this.BTN_Modificar.setEnabled(false);
    }//GEN-LAST:event_BTN_VerProductosActionPerformed

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
        try {
            // Obtener datos del formulario
            String codigoStr = txtcodigoproducto.getText().trim();
            String nuevoNombreProducto = txtNombreProducto.getText().trim();
            String nuevoPrecioUnitario = txtPreciounitario.getText().trim();
            String nuevoStockActual = txtstockActual.getText().trim();
            String nuevoStockMinimo = txtstockMinimo.getText().trim();
            String nuevaObservacion = txtobservaciones.getText().trim();
            String nombreUnidadMedida = (String) jComboBox_unidad_medida.getSelectedItem();
    

            // Validar campos obligatorios
            if (codigoStr.isEmpty() || nuevoNombreProducto.isEmpty() || nuevoPrecioUnitario.isEmpty() || nuevoStockActual.isEmpty() 
                    || nuevoStockMinimo.isEmpty() || nombreUnidadMedida == null || nombreUnidadMedida.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos obligatorios.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Validar la longitud de la observacion
            if (nuevaObservacion.length() > 500) {
                JOptionPane.showMessageDialog(this, "La observación no puede exceder los 500 caracteres.", "Validación", JOptionPane.WARNING_MESSAGE);
                txtobservaciones.requestFocus();
                return;
            }
            
            // Validar longitud del nombre
            if (nuevoNombreProducto.length() > 85) {
                JOptionPane.showMessageDialog(this, "El nombre del producto no debe exceder 85 caracteres.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Validar si hubo cambios
            if (nuevoNombreProducto.equalsIgnoreCase(nombreOriginal) && nuevoPrecioUnitario.equalsIgnoreCase(precioOriginal) 
                    && nuevoStockActual.equalsIgnoreCase(stockActualOriginal) && nuevoStockMinimo.equalsIgnoreCase(stockMinimoOriginal) 
                    && nombreUnidadMedida.equalsIgnoreCase(unidadOriginal) && nuevaObservacion.equalsIgnoreCase(ObservacionOriginal)) {
                JOptionPane.showMessageDialog(this, "No se detectaron cambios en los datos.", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Convertir código a entero
            int codigoProducto = Integer.parseInt(codigoStr);
            // Convertir precio a double
            // En BTN_ModificarActionPerformed
            String precioLimpio = nuevoPrecioUnitario.replace("S/", "").replace(",", "").trim(); 
            // Nota: Si el formato usa coma para miles, elimínala. Si usa punto para decimales, asegúrate de mantenerlo.
            double precio = Double.parseDouble(precioLimpio);
            // Convertir stock actual a entero
            int stockActual = Integer.parseInt(nuevoStockActual);
            // Convertir stock minimo a entero
            int stockMinimo = Integer.parseInt(nuevoStockMinimo);

            // Obtener código de la unidad de medida (FK)
            int codigoUnidadMedida = UM.obtenerCodigoUnidad(nombreUnidadMedida);
            if (codigoUnidadMedida == -1) {
                JOptionPane.showMessageDialog(this, "La unidad de medida seleccionada no es válida.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Verificar duplicidad de nombre con otras escuelas
            if (PR.existeProductoConNombre(nuevoNombreProducto, codigoProducto)) {
                JOptionPane.showMessageDialog(this, "Ya existe otra escuela con el mismo nombre.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

             // Llamar al método de modificación
            PR.modificarProducto(codigoProducto, nuevoNombreProducto, precio, stockMinimo, stockActual, nuevaObservacion, codigoUnidadMedida);

            JOptionPane.showMessageDialog(this, "Escuela profesional actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // Refrescar tabla y limpiar
            listarProductos();
            limpiarCamposProducto();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Código de producto no válido.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al modificar: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
        try {  
            // 1. Obtener datos del formulario
            //Variable para el jcombobox y el nombre (solo para Strings y jcombox)
            String nombreProducto = txtNombreProducto.getText().trim();
            String nombreUnidad = (String) jComboBox_unidad_medida.getSelectedItem();
            String Observacion = txtobservaciones.getText().trim();
            
            if (nombreProducto.isEmpty()){
                JOptionPane.showMessageDialog(this, "El nombre del producto no puede estar vacio.", "Validación", JOptionPane.WARNING_MESSAGE);
                txtNombreProducto.requestFocus();
                return;
            }
            if (Observacion.length() > 500) {
                JOptionPane.showMessageDialog(this, "La observación no puede exceder los 500 caracteres.", "Validación", JOptionPane.WARNING_MESSAGE);
                txtobservaciones.requestFocus();
                return;
            }
            if (txtPreciounitario.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(this, "El precio del producto no puede estar vacio.", "Validación", JOptionPane.WARNING_MESSAGE);
                txtPreciounitario.requestFocus();
                return;
            }
            if (txtstockActual.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(this, "El stock actual del producto no puede estar vacio.", "Validación", JOptionPane.WARNING_MESSAGE);
                txtstockActual.requestFocus();
                return;
            }
            if (txtstockMinimo.getText().trim().isEmpty()){
                JOptionPane.showMessageDialog(this, "El stock minimo del producto no puede estar vacio.", "Validación", JOptionPane.WARNING_MESSAGE);
                txtstockMinimo.requestFocus();
                return;
            }
            if (nombreUnidad == null || nombreUnidad.trim().isEmpty()){
                JOptionPane.showMessageDialog(this, "Seleccione una unidad de medida valida.", "Validación", JOptionPane.WARNING_MESSAGE);
                txtNombreProducto.requestFocus();
                return;
            }
            
            
            //Proceso de transformación de String a int o Double (Solo para los campos que tienen numeros)
            double precioUnitario;
            int stockActual;
            int stockMinimo; 
            try {
                precioUnitario = Double.parseDouble(txtPreciounitario.getText().trim().replace(",", "."));
                stockActual = Integer.parseInt(txtstockActual.getText().trim());
                stockMinimo = Integer.parseInt(txtstockMinimo.getText().trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, """
                                                    Error: Uno de los campos num\u00e9ricos tiene un formato incorrecto.
                                                    Aseg\u00farese de usar solo n\u00fameros (y punto para decimales en el precio).""", 
                        "Error de entrada", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
          
            
            // 3. Obtener el código de la facultad desde la vista
            int codigoUnidad = UM.obtenerCodigoUnidad(nombreUnidad);
            if (codigoUnidad == -1) {
                JOptionPane.showMessageDialog(this, "No se encontró la unidad de medida seleccionada.", "Error", JOptionPane.ERROR_MESSAGE);
             return;
            }
            
            // 4. Insertar usando método de clase (que valida duplicados)
            PR.insertarProducto(nombreProducto, precioUnitario, stockMinimo, stockActual, Observacion, codigoUnidad);

            // 5. Confirmar éxito
            JOptionPane.showMessageDialog(this, "Producto registrado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // 6. Refrescar tabla
            listarProductos();

             // 7. Limpiar campos
            this.limpiarCamposProducto();

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
        limpiarCamposProducto();
        BTN_Cancel.setVisible(true);
        BTN_Nuevo.setVisible(false);
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CancelActionPerformed
        listarProductos();

        BTN_Guardar.setEnabled(true);
        //BTN_Desactivar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        BTN_Nuevo.setVisible(true);
        BTN_Nuevo.setEnabled(true);
        BTN_Cancel.setVisible(false);
        JTABLE_Mant_Producto.setEnabled(true);
    }//GEN-LAST:event_BTN_CancelActionPerformed

    private void txtstockMinimoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstockMinimoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstockMinimoKeyTyped

    private void txtPreciounitarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPreciounitarioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPreciounitarioKeyTyped

    private void jComboBox_unidad_medidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_unidad_medidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_unidad_medidaActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Frm_Producto().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Cancel;
    private javax.swing.JButton BTN_Cerrar1;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_PDF;
    private javax.swing.JButton BTN_VerProductos;
    private javax.swing.JTable JTABLE_Mant_Producto;
    private javax.swing.JTextField TXT_BuscarProductos;
    private javax.swing.JComboBox<String> jComboBox_unidad_medida;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField txtNombreProducto;
    private javax.swing.JTextField txtPreciounitario;
    private javax.swing.JTextField txtcodigoproducto;
    private javax.swing.JTextArea txtobservaciones;
    private javax.swing.JTextField txtstockActual;
    private javax.swing.JTextField txtstockMinimo;
    // End of variables declaration//GEN-END:variables
    
    //llenar elementos en el jcombobox el metodo viene de la clase
    //y la consulta de la vista
    private void cargarUnidadMedida() {
        try {
            jComboBox_unidad_medida.removeAllItems();
            jComboBox_unidad_medida.addItem("<<Seleccionar>>"); // Opción por defecto

            ResultSet rs = UM.listarUnidadMedida();
            while (rs.next()) {
                jComboBox_unidad_medida.addItem(rs.getString("Unidad de Medida"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las unidades de medidas: " + e.getMessage());
        }
    }
    
    //método para mostrar registros en el jtable
    private void listarProductos() {
        try {
            // 1. Configuración de la tabla
            JTABLE_Mant_Producto.setAutoCreateRowSorter(true);
            modeloTablaProducto.setRowCount(0);

            // 2. Obtener datos
            ResultSet rs = PR.listarProductos();

            while (rs.next()) {
            Object fila[] = {
                rs.getInt("ID"), 
                rs.getString("Nombre del Producto"),
                rs.getString("Unidad de Medida"), 
                rs.getString("Abreviatura"), 
                rs.getString("Precio"),    
                rs.getInt("Stock Mínimo"),
                rs.getInt("Stock Actual"),
                rs.getString("Observaciones")
            };
                modeloTablaProducto.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar la tabla de productos: " + e.getMessage(), "Error de Datos", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void limpiarCamposProducto() {
            txtcodigoproducto.setText("");
            txtNombreProducto.setText("");
            txtPreciounitario.setText("");
            txtstockActual.setText("");
            txtstockMinimo.setText("");
            jComboBox_unidad_medida.setSelectedIndex(0); // O -1 si quieres dejarlo vacío
            BTN_Guardar.setEnabled(true);
            BTN_Cancel.setEnabled(true);
            BTN_Nuevo.setEnabled(false);
            BTN_Modificar.setEnabled(false);
            //BTN_Desactivar.setEnabled(false);
            BTN_VerProductos.setEnabled(false);
        }
    //Método para mostrar las facultades
    public void buscarProductosPorNombre() {
        modeloTablaProducto.setRowCount(0);
        String nombre = TXT_BuscarProductos.getText().trim();
        try {
            ResultSet rs = PR.buscarProducto(nombre);
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("ID"),
                    rs.getString("Nombre del Producto"),
                    rs.getString("Unidad de Medida"),
                    rs.getString("Abreviatura"),
                    rs.getString("Precio"),
                    rs.getInt("Stock Mínimo"),
                    rs.getInt("Stock Actual"),
                    rs.getString("Observaciones")
                };
                modeloTablaProducto.addRow(fila);      
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar: " + e.getMessage());
        }
    }   
}
