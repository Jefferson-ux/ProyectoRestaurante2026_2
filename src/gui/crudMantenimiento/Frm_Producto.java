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
//Date
import java.text.SimpleDateFormat;
import java.util.Date;


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
        
        //Posicion
        this.setLocationRelativeTo(null);
       
        //Metodo para cargar el combobox
        cargarUnidadMedida();
        
        //definir los encabezados de la tabla
        String titulos[]={"ID","Nombre del producto","unidad de medida","Precio del producto", "Stock Minimo","Stock Actual","Observaciones","Estado"};
        
        //Asignar los tiutlos al modelo
        modeloTablaProducto.setColumnIdentifiers(titulos);
        
        //Establecer el modelo a la JTable
        JTABLE_Mant_Producto.setModel(modeloTablaProducto);
        
        //ocultar columnas sesibles o internas: solo visual, noafecta al modelo, ocultar columna "id_producto, observacion_producto y estado"
        JTABLE_Mant_Producto.getColumnModel().getColumn(0).setMinWidth(0);
        JTABLE_Mant_Producto.getColumnModel().getColumn(0).setMaxWidth(0);
        JTABLE_Mant_Producto.getColumnModel().getColumn(0).setWidth(0);
        
        
        JTABLE_Mant_Producto.getColumnModel().getColumn(6).setMinWidth(0);
        JTABLE_Mant_Producto.getColumnModel().getColumn(6).setMaxWidth(0);
        JTABLE_Mant_Producto.getColumnModel().getColumn(6).setWidth(0);
        
        JTABLE_Mant_Producto.getColumnModel().getColumn(7).setMinWidth(0);
        JTABLE_Mant_Producto.getColumnModel().getColumn(7).setMaxWidth(0);
        JTABLE_Mant_Producto.getColumnModel().getColumn(7).setWidth(0);
        
        //Desabilitar campo de codigo (solo se mostrara no se escribe)
        txtcodigoproducto.setEnabled(false);
        BTN_Nuevo.setEnabled(false);
        BTN_Guardar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        BTN_ListarProductos = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jCheckBoxListarInactivos = new javax.swing.JCheckBox();
        jCheckBoxActivar = new javax.swing.JCheckBox();
        BTN_Desactivar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_Producto = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXT_BuscarProductos = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        BTN_Cerrar1 = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();
        BTN_PDF = new javax.swing.JButton();
        BTN_Nuevo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        BTN_Modificar = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Codigo");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Stock actual");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 80, 80, -1));

        txtcodigoproducto.setEditable(false);
        txtcodigoproducto.setBackground(new java.awt.Color(255, 255, 255));
        txtcodigoproducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcodigoproducto.setForeground(new java.awt.Color(0, 0, 204));
        txtcodigoproducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcodigoproducto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtcodigoproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 290, 20));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Nombre*");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 60, -1));

        txtstockActual.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtstockActual.setForeground(new java.awt.Color(0, 0, 204));
        txtstockActual.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtstockActual.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtstockActual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtstockActualKeyTyped(evt);
            }
        });
        jPanel1.add(txtstockActual, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 230, 20));

        jComboBox_unidad_medida.addActionListener(this::jComboBox_unidad_medidaActionPerformed);
        jPanel1.add(jComboBox_unidad_medida, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 290, 20));

        txtNombreProducto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNombreProducto.setForeground(new java.awt.Color(0, 0, 204));
        txtNombreProducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreProducto.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtNombreProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreProductoKeyTyped(evt);
            }
        });
        jPanel1.add(txtNombreProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 290, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Precio unitario*");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 90, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Observacion");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 100, -1));

        txtstockMinimo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtstockMinimo.setForeground(new java.awt.Color(0, 0, 204));
        txtstockMinimo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtstockMinimo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtstockMinimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtstockMinimoKeyTyped(evt);
            }
        });
        jPanel1.add(txtstockMinimo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 230, 20));

        txtPreciounitario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPreciounitario.setForeground(new java.awt.Color(0, 0, 204));
        txtPreciounitario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPreciounitario.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtPreciounitario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPreciounitarioKeyTyped(evt);
            }
        });
        jPanel1.add(txtPreciounitario, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 230, 20));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Stock mínimo*");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 90, -1));

        txtobservaciones.setColumns(20);
        txtobservaciones.setRows(5);
        jScrollPane3.setViewportView(txtobservaciones);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 430, 80));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("unidad de medida*");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 110, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 770, 230));

        BTN_ListarProductos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_ListarProductos.setText("LISTAR PRODUCTOS");
        BTN_ListarProductos.addActionListener(this::BTN_ListarProductosActionPerformed);
        getContentPane().add(BTN_ListarProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 30, 165, 50));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCheckBoxListarInactivos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBoxListarInactivos.setText("Listar Productos desactivados");
        jCheckBoxListarInactivos.addActionListener(this::jCheckBoxListarInactivosActionPerformed);
        jPanel4.add(jCheckBoxListarInactivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 190, 30));

        jCheckBoxActivar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBoxActivar.setText("Reactivar docentes");
        jCheckBoxActivar.addActionListener(this::jCheckBoxActivarActionPerformed);
        jPanel4.add(jCheckBoxActivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 140, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 120, 210, 120));

        BTN_Desactivar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Desactivar.setText("DESACTIVAR");
        BTN_Desactivar.addActionListener(this::BTN_DesactivarActionPerformed);
        getContentPane().add(BTN_Desactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 280, 170, 50));

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 1010, 220));

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

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 1010, 50));

        BTN_Cerrar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_cancel.png"))); // NOI18N
        BTN_Cerrar1.setText("     Cerrar");
        BTN_Cerrar1.addActionListener(this::BTN_Cerrar1ActionPerformed);
        getContentPane().add(BTN_Cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 640, 165, 50));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/excel.png"))); // NOI18N
        BTN_EXCEL.setText("     Exportar XLSX");
        BTN_EXCEL.addActionListener(this::BTN_EXCELActionPerformed);
        getContentPane().add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 640, 170, 50));

        BTN_PDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pdf.png"))); // NOI18N
        BTN_PDF.setText("     Exportar PDF");
        BTN_PDF.addActionListener(this::BTN_PDFActionPerformed);
        getContentPane().add(BTN_PDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 640, 170, 50));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_add.png"))); // NOI18N
        BTN_Nuevo.setText("      NUEVO");
        BTN_Nuevo.addActionListener(this::BTN_NuevoActionPerformed);
        getContentPane().add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 165, 48));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MANTENIMIENTO DE PRODUCTO");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 830, 30));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_cancel_cogs.png"))); // NOI18N
        BTN_Modificar.setText("    MODIFICAR");
        BTN_Modificar.addActionListener(this::BTN_ModificarActionPerformed);
        jPanel3.add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 280, 165, 48));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_save.png"))); // NOI18N
        BTN_Guardar.setText("     GUARDAR");
        BTN_Guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_GuardarMouseClicked(evt);
            }
        });
        BTN_Guardar.addActionListener(this::BTN_GuardarActionPerformed);
        jPanel3.add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 280, 165, 48));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtstockActualKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstockActualKeyTyped
 
    }//GEN-LAST:event_txtstockActualKeyTyped

    private void txtNombreProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreProductoKeyTyped
 
    }//GEN-LAST:event_txtNombreProductoKeyTyped

    private void txtstockMinimoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtstockMinimoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtstockMinimoKeyTyped

    private void txtPreciounitarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPreciounitarioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPreciounitarioKeyTyped

    private void jComboBox_unidad_medidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_unidad_medidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_unidad_medidaActionPerformed

    private void BTN_ListarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ListarProductosActionPerformed
        this.listarProductos();
        this.BTN_Nuevo.setEnabled(true);
        this.BTN_Guardar.setEnabled(false);
        this.BTN_Modificar.setEnabled(false);
        this.BTN_ListarProductos.setEnabled(false);
        this.jCheckBoxListarInactivos.setSelected(false);
        txtNombreProducto.requestFocus();

    }//GEN-LAST:event_BTN_ListarProductosActionPerformed

    private void jCheckBoxActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxActivarActionPerformed
        // 1. Obtener fila seleccionada de la tabla de productos
        int filaSeleccionada = JTABLE_Mant_Producto.getSelectedRow();

        // 2. Validar selección inicial
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto de la tabla para reactivarlo.", "Aviso", JOptionPane.WARNING_MESSAGE);
            if (jCheckBoxActivar.isSelected()) {
                jCheckBoxActivar.setSelected(false);
            }
            return;
        }

        // 3. Obtener datos de la fila de forma segura
        int codigoProducto = Integer.parseInt(JTABLE_Mant_Producto.getValueAt(filaSeleccionada, 0).toString());
        String nombreProd = JTABLE_Mant_Producto.getValueAt(filaSeleccionada, 1).toString().trim();
        String estadoActual = JTABLE_Mant_Producto.getValueAt(filaSeleccionada, 8).toString().trim();

        // 4. Validar si ya está reactivado para evitar llamadas innecesarias
        if (estadoActual.equalsIgnoreCase("Activo")) {
            JOptionPane.showMessageDialog(this,
                "El producto '" + nombreProd + "' ya se encuentra activo.",
                "Información", JOptionPane.INFORMATION_MESSAGE);
            jCheckBoxActivar.setSelected(false);
            return;
        }

        // 5. Confirmación con diseño profesional
        int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Desea reactivar el producto: " + nombreProd + "?\n"
            + "Esto permitirá volver a utilizarlo en el sistema de ventas.",
            "Confirmar Reactivación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                // 6. Ejecutar la reactivación en la Base de Datos
                // Usamos el método reactivarProducto
                PR.reactivarProducto(codigoProducto);

                JOptionPane.showMessageDialog(this,
                    "¡Listo! El producto '" + nombreProd + "' ha sido reactivado con éxito.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // 7. Sincronizar Interfaz
                jCheckBoxListarInactivos.setSelected(false);
                jCheckBoxActivar.setSelected(false);

                // Refrescar tabla y limpiar formulario
                listarProductos();
                limpiarCamposProducto();

            } catch (SQLException ex) {
                // Manejo de errores específicos basado en los Procedures de Producto
                // 20071 es el código que definimos para "ID no encontrado" en el mantenimiento de productos
                int errorCode = ex.getErrorCode();
                String msj = (errorCode == 20071) ? "El producto ya no existe en la base de datos." : ex.getMessage();

                JOptionPane.showMessageDialog(this,
                    "Error al intentar reactivar:\n" + msj,
                    "Error de Base de Datos",
                    JOptionPane.ERROR_MESSAGE);

                jCheckBoxActivar.setSelected(false);
            }
        } else {
            // Si el usuario cancela la confirmación, desmarcamos el checkbox
            jCheckBoxActivar.setSelected(false);
        }
    }//GEN-LAST:event_jCheckBoxActivarActionPerformed

    private void jCheckBoxListarInactivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxListarInactivosActionPerformed
        this.listarProductosInactivos();
        this.BTN_ListarProductos.setEnabled(true);
    }//GEN-LAST:event_jCheckBoxListarInactivosActionPerformed

    private void BTN_DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DesactivarActionPerformed
        // 1. Obtener la fila seleccionada de la tabla de productos
        int filaSeleccionada = JTABLE_Mant_Producto.getSelectedRow(); // Ajusta el nombre de tu JTable si es distinto

        // Validación inicial: ¿Seleccionó algo?
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un producto de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Obtener datos de la fila de forma segura
        // Nota: Verifica que el índice de la columna del 'ID' sea 0 y el del 'Estado' sea el correcto en tu vista
        int codigoProducto = Integer.parseInt(JTABLE_Mant_Producto.getValueAt(filaSeleccionada, 0).toString());
        String nombreProd = JTABLE_Mant_Producto.getValueAt(filaSeleccionada, 1).toString().trim();

        // El estado suele estar en la última columna o según tu vista_producto
        // Si en tu tabla no se muestra el texto "Activo/Inactivo", puedes omitir esta validación previa
        String estadoActual = JTABLE_Mant_Producto.getValueAt(filaSeleccionada, 7).toString().trim();

        // 3. Validar si ya está desactivado para no trabajar en vano
        if (estadoActual.equalsIgnoreCase("Inactivo")) {
            JOptionPane.showMessageDialog(this, "El producto '" + nombreProd + "' ya se encuentra desactivado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // 4. Confirmación personalizada
        int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Está seguro de que desea desactivar el producto: " + nombreProd + "?\n"
            + "Esto lo ocultará de las ventas pero mantendrá su historial.",
            "Confirmar Desactivación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                // 5. Llamada al método del DAO de Producto
                // Según tu archivo ProductoMethod.java el método es darDeBajaProducto
                PR.desactivarProducto(codigoProducto);

                JOptionPane.showMessageDialog(this, "Producto desactivado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // 6. Actualizar interfaz respetando el filtro de inactivos
                if (jCheckBoxListarInactivos.isSelected()) {
                    // Reemplaza por tu método para listar inactivos
                    listarProductosInactivos();
                } else {
                    listarProductos(); // El registro desaparece de la lista de activos
                }

                // 7. Limpiar el formulario
                limpiarCamposProducto();

            } catch (SQLException ex) {
                // Manejo de errores basado en el código de error que definimos en el Procedure
                String mensajeError = (ex.getErrorCode() == 20071) ? "El producto no existe en la base de datos." : ex.getMessage();

                JOptionPane.showMessageDialog(this,
                    "No se pudo cambiar el estado del producto.\nDetalle: " + mensajeError,
                    "Error de Base de Datos",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTN_DesactivarActionPerformed

    private void TXT_BuscarProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarProductosKeyReleased
        this.buscarProductosPorNombre();
    }//GEN-LAST:event_TXT_BuscarProductosKeyReleased

    private void TXT_BuscarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_BuscarProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_BuscarProductosActionPerformed

    private void JTABLE_Mant_ProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_ProductoMouseClicked
        int filaSeleccionada = JTABLE_Mant_Producto.getSelectedRow();

        // 1. Configuración de botones generales
        BTN_Modificar.setEnabled(filaSeleccionada != -1); // Habilitar solo si hay selección
        BTN_Guardar.setEnabled(false);
        BTN_Nuevo.setVisible(true);
        BTN_Nuevo.setEnabled(true);

        // 2. Lógica de habilitación para BTN_Desactivar según el filtro de inactivos
        if (jCheckBoxListarInactivos.isSelected()) {
            BTN_Desactivar.setEnabled(false); // Si ya están inactivos, no se pueden desactivar de nuevo
        } else {
            BTN_Desactivar.setEnabled(filaSeleccionada != -1); // Solo habilitar si hay selección
        }

        // 3. Extracción de datos si hay una fila seleccionada
        if (filaSeleccionada != -1) {
            // Obtener los datos de la fila de forma segura
            String codigo = JTABLE_Mant_Producto.getValueAt(filaSeleccionada, 0).toString().trim();
            String nombreProducto = JTABLE_Mant_Producto.getValueAt(filaSeleccionada, 1).toString().trim();
            String unidadDeMedida = JTABLE_Mant_Producto.getValueAt(filaSeleccionada, 2).toString().trim();
            String precioUnitario = JTABLE_Mant_Producto.getValueAt(filaSeleccionada, 4).toString().trim();
            String stockMinimo = JTABLE_Mant_Producto.getValueAt(filaSeleccionada, 5).toString().trim();
            String stockActual = JTABLE_Mant_Producto.getValueAt(filaSeleccionada, 6).toString().trim();

            // Tratamiento especial para observaciones (evitar NullPointerException)
            Object celdaObservacion = JTABLE_Mant_Producto.getValueAt(filaSeleccionada, 7);
            String observacionProducto = (celdaObservacion != null) ? celdaObservacion.toString().trim() : "";

            // 4. Mostrar en los controles (TextFields)
            txtcodigoproducto.setText(codigo);
            txtNombreProducto.setText(nombreProducto);
            txtPreciounitario.setText(precioUnitario);
            txtstockActual.setText(stockActual);
            txtstockMinimo.setText(stockMinimo);
            txtobservaciones.setText(observacionProducto);

            // 5. Guardar valores originales para control de cambios (Manejo de estados)
            nombreOriginal = nombreProducto;
            precioOriginal = precioUnitario;
            stockActualOriginal = stockActual;
            stockMinimoOriginal = stockMinimo;
            unidadOriginal = unidadDeMedida;
            ObservacionOriginal = observacionProducto;

            // 6. Sincronizar el JComboBox de Unidad de Medida
            boolean encontrado = false;
            for (int i = 0; i < jComboBox_unidad_medida.getItemCount(); i++) {
                String item = jComboBox_unidad_medida.getItemAt(i).trim();
                if (item.equalsIgnoreCase(unidadDeMedida)) {
                    jComboBox_unidad_medida.setSelectedIndex(i);
                    encontrado = true;
                    break;
                }
            }

            // Alerta si la unidad de medida no coincide con el catálogo del Combo
            if (!encontrado) {
                JOptionPane.showMessageDialog(this,
                    "La unidad de medida '" + unidadDeMedida + "' no está en la lista actual.",
                    "Aviso de Consistencia", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_JTABLE_Mant_ProductoMouseClicked

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

    private void BTN_EXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EXCELActionPerformed
        try {
            // 1. Crear un nuevo libro de Excel (.xlsx)
            XSSFWorkbook workbook = new XSSFWorkbook();

            // 2. Crear una hoja dentro del libro
            XSSFSheet sheet = workbook.createSheet("Proveedores");

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

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
        limpiarCamposProducto();
        BTN_Nuevo.setVisible(false);
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
        // 1. Capturar y limpiar datos
        String nombre = txtNombreProducto.getText().trim();
        String precioStr = txtPreciounitario.getText().trim();
        String stockActualStr = txtstockActual.getText().trim();
        String stockMinimoStr = txtstockMinimo.getText().trim();
        String observacion = txtobservaciones.getText().trim();
        String nombreUnidad = (String) jComboBox_unidad_medida.getSelectedItem();

        // --- VALIDACIONES DE CAMPOS VACÍOS ---
        if (campoVacio(txtNombreProducto, "Nombre del Producto")) {
            return;
        }
        if (campoVacio(txtPreciounitario, "Precio Unitario")) {
            return;
        }
        if (campoVacio(txtstockActual, "Stock Actual")) {
            return;
        }
        if (campoVacio(txtstockMinimo, "Stock Mínimo")) {
            return;
        }

        if (nombreUnidad == null || nombreUnidad.equals("Seleccione...")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una Unidad de Medida.", "Validación", JOptionPane.WARNING_MESSAGE);
            jComboBox_unidad_medida.requestFocus();
            return;
        }

        // --- VALIDACIONES DE FORMATO Y LÓGICA ---
        double precioUnitario;
        int stockActual, stockMinimo;

        try {
            precioUnitario = Double.parseDouble(precioStr.replace(",", "."));
            stockActual = Integer.parseInt(stockActualStr);
            stockMinimo = Integer.parseInt(stockMinimoStr);

            if (precioUnitario < 0) {
                JOptionPane.showMessageDialog(this, "El precio no puede ser negativo.", "Validación", JOptionPane.WARNING_MESSAGE);
                txtPreciounitario.requestFocus();
                return;
            }
            if (stockActual < 0 || stockMinimo < 0) {
                JOptionPane.showMessageDialog(this, "Los valores de stock no pueden ser negativos.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Asegúrese de ingresar solo números en los campos de Precio y Stock.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (observacion.length() > 500) {
            JOptionPane.showMessageDialog(this, "La observación no puede exceder los 500 caracteres.", "Validación", JOptionPane.WARNING_MESSAGE);
            txtobservaciones.requestFocus();
            return;
        }

        // 2. Confirmar si el usuario desea guardar
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea registrar este nuevo producto?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {

            try {
                // 3. Obtener el ID de la unidad y llamar al método para insertar
                int codigoUnidad = UM.obtenerCodigoUnidad(nombreUnidad);

                this.PR.insertarProducto(nombre, precioUnitario, stockMinimo, stockActual, observacion, codigoUnidad);

                // 4. Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(this, "Producto registrado correctamente", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);

                // 5. Actualizar tabla y limpiar campos
                this.listarProductos();
                this.limpiarCamposProducto();

            } catch (SQLException ex) {
                // Capturamos los códigos de error definidos en tu Procedure 'insertar_producto'
                int errorCode = ex.getErrorCode();

                switch (errorCode) {
                    case 1034 ->
                    JOptionPane.showMessageDialog(this, "Error: Ya existe un producto ACTIVO con ese nombre.", "Duplicado", JOptionPane.ERROR_MESSAGE);
                    case 1043 ->
                    JOptionPane.showMessageDialog(this, "Aviso: Este producto ya existe pero está INACTIVO.\nDebe reactivarlo desde el panel de gestión.", "Producto Inactivo", JOptionPane.WARNING_MESSAGE);
                    case 1033 ->
                    JOptionPane.showMessageDialog(this, "Error: La unidad de medida seleccionada no es válida.", "Error UM", JOptionPane.ERROR_MESSAGE);
                    default ->
                    JOptionPane.showMessageDialog(this, "Error de base de datos (" + errorCode + "):\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
        try {
            // 1. Capturar datos y limpiar espacios
            String codigoStr = txtcodigoproducto.getText().trim();
            String nuevoNombre = txtNombreProducto.getText().trim();
            String nuevoPrecioStr = txtPreciounitario.getText().trim();
            String nuevoStockActualStr = txtstockActual.getText().trim();
            String nuevoStockMinimoStr = txtstockMinimo.getText().trim();
            String nuevaObservacion = txtobservaciones.getText().trim();
            String nombreUnidad = (String) jComboBox_unidad_medida.getSelectedItem();

            // --- VALIDACIONES DE CAMPOS VACÍOS (Java) ---
            if (codigoStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un producto de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Usando tu método campoVacio para mantener el estilo de Proveedor
            if (campoVacio(txtNombreProducto, "Nombre del Producto")) {
                return;
            }
            if (campoVacio(txtPreciounitario, "Precio Unitario")) {
                return;
            }
            if (campoVacio(txtstockActual, "Stock Actual")) {
                return;
            }
            if (campoVacio(txtstockMinimo, "Stock Mínimo")) {
                return;
            }

            if (nombreUnidad == null || nombreUnidad.equals("Seleccione...")) {
                JOptionPane.showMessageDialog(this, "Seleccione una unidad de medida válida.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // --- VALIDACIÓN DE CAMBIOS ---
            if (nuevoNombre.equalsIgnoreCase(nombreOriginal)
                && nuevoPrecioStr.equalsIgnoreCase(precioOriginal)
                && nuevoStockActualStr.equalsIgnoreCase(stockActualOriginal)
                && nuevoStockMinimoStr.equalsIgnoreCase(stockMinimoOriginal)
                && nombreUnidad.equalsIgnoreCase(unidadOriginal)
                && nuevaObservacion.equalsIgnoreCase(ObservacionOriginal)) {

                JOptionPane.showMessageDialog(this, "No se detectaron cambios en los datos del producto.", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // --- CONVERSIÓN Y LÓGICA DE NEGOCIO ---
            int codigoProducto = Integer.parseInt(codigoStr);

            // Limpieza de formato de moneda S/ y miles
            double precio = Double.parseDouble(nuevoPrecioStr.replace("S/", "").replace(",", "").trim());
            int stockActual = Integer.parseInt(nuevoStockActualStr);
            int stockMinimo = Integer.parseInt(nuevoStockMinimoStr);

            if (precio < 0 || stockActual < 0 || stockMinimo < 0) {
                JOptionPane.showMessageDialog(this, "Los valores numéricos no pueden ser negativos.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (nuevaObservacion.length() > 500) {
                JOptionPane.showMessageDialog(this, "La observación no puede exceder los 500 caracteres.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 2. Confirmación del usuario
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea modificar los datos del producto \"" + nuevoNombre + "\"?", "Confirmación", JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                try {
                    // 3. Obtener ID de Unidad y llamar al DAO
                    int codigoUnidad = UM.obtenerCodigoUnidad(nombreUnidad);

                    this.PR.modificarProducto(codigoProducto, nuevoNombre, precio, stockMinimo, stockActual, nuevaObservacion, codigoUnidad);

                    // 4. Éxito
                    JOptionPane.showMessageDialog(this, "Producto actualizado correctamente", "Modificación exitosa", JOptionPane.INFORMATION_MESSAGE);

                    // 5. Refrescar interfaz
                    this.listarProductos();
                    this.limpiarCamposProducto();

                } catch (SQLException ex) {
                    // --- CAPTURA DE ERRORES SEGÚN TU SQL (MYSQL_ERRNO) ---
                    int errorCode = ex.getErrorCode();

                    switch (errorCode) {
                        case 20151 ->
                        JOptionPane.showMessageDialog(this, "Error: La unidad de medida es inválida o está inactiva.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
                        case 1062, 20074 ->
                        JOptionPane.showMessageDialog(this, "Error: Ya existe otro producto activo con el nombre \"" + nuevoNombre + "\".", "Nombre Duplicado", JOptionPane.ERROR_MESSAGE);
                        case 20071 ->
                        JOptionPane.showMessageDialog(this, "Error: El producto ya no existe en la base de datos.", "Error de ID", JOptionPane.ERROR_MESSAGE);
                        default ->
                        JOptionPane.showMessageDialog(this, "Error crítico (" + errorCode + "):\n" + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Asegúrese de ingresar valores numéricos válidos en Precio y Stock.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BTN_ModificarActionPerformed

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
    private javax.swing.JButton BTN_Cerrar1;
    private javax.swing.JButton BTN_Desactivar;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_ListarProductos;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_PDF;
    private javax.swing.JTable JTABLE_Mant_Producto;
    private javax.swing.JTextField TXT_BuscarProductos;
    private javax.swing.JCheckBox jCheckBoxActivar;
    private javax.swing.JCheckBox jCheckBoxListarInactivos;
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
    private javax.swing.JPanel jPanel4;
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
    
    //Validara campos vacios
    private boolean campoVacio(javax.swing.JTextField txt, String nombreCampo) {
        if (txt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El campo " + nombreCampo + " es obligatorio.", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            txt.requestFocus();
            return true; // Sí está vacío
        }
        return false; // No está vacío
    }
    
    //método para listar productos
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
                rs.getString("Precio"),    
                rs.getInt("Stock Mínimo"),
                rs.getInt("Stock Actual"),
                rs.getString("Observaciones"),
                rs.getInt("Estado") == 1 ? "Activo" : "Inactivo"
            };
                modeloTablaProducto.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar la tabla de productos: " + e.getMessage(), "Error de Datos", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Metodo para listar productos incativos
    private void listarProductosInactivos() {
        try {
            // 1. Configuración de la tabla
            JTABLE_Mant_Producto.setAutoCreateRowSorter(true);
            modeloTablaProducto.setRowCount(0);

            // 2. Obtener datos
            ResultSet rs = PR.listarProductosDesactivados();

            while (rs.next()) {
            Object fila[] = {
                rs.getInt("ID"), 
                rs.getString("Nombre del Producto"),
                rs.getString("Unidad de Medida"),  
                rs.getString("Precio"),    
                rs.getInt("Stock Mínimo"),
                rs.getInt("Stock Actual"),
                rs.getString("Observaciones"),
                rs.getInt("Estado") == 1 ? "Activo" : "Inactivo"
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
            txtobservaciones.setText("");
            jComboBox_unidad_medida.setSelectedIndex(0); // O -1 si quieres dejarlo vacío
            BTN_Guardar.setEnabled(true);
            BTN_Nuevo.setEnabled(false);
            BTN_Modificar.setEnabled(false);
            BTN_ListarProductos.setEnabled(false);

        }
    //Método para mostrar las facultades
    public void buscarProductosPorNombre() {
        modeloTablaProducto.setRowCount(0);
        String nombre = TXT_BuscarProductos.getText().trim();
        try {
            boolean hayResultados = false;
            String texto = TXT_BuscarProductos.getText().trim();
            //Si el campo esta vacio, listar todos
            if(texto.isEmpty() && jCheckBoxListarInactivos.isSelected()){
                this.listarProductosInactivos();//Reutilizamos el metodo para q la tabla no se quede vacia
                return;
            }else if(texto.isEmpty()){
                this.listarProductos();
                return;
            }
            // Llama al método que retorna los datos de Productos
            ResultSet rs = this.PR.buscarProducto(nombre);
            while (rs.next()) {
                hayResultados = true;
                Object[] fila = {
                    rs.getString("ID"),
                    rs.getString("Nombre del Producto"),
                    rs.getString("Unidad de Medida"),
                    rs.getString("Precio"),
                    rs.getString("Stock Minimo"),
                    rs.getString("Stock Actual"),
                    rs.getString("Observaciones")
                };
                modeloTablaProducto.addRow(fila);
            }
            if(!hayResultados){
                JOptionPane.showMessageDialog(this, "No se encontraron proveedores ",
                    "Sin resultados", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar Productos:\n" + e.getMessage(),
                    "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        }
    }
}

