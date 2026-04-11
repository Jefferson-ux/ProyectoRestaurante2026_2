package gui.crudMantenimiento;

import com.formdev.flatlaf.FlatLightLaf;
import java.sql.ResultSet;
import connection.ConnectionDB;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//Metodo de proveedor_producto
import logic.dao.ProveedorProductoMethod;
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


public class Frm_Proveedor_Producto extends javax.swing.JFrame {
    //Modelo para mostrar datos en ta tabla
    DefaultTableModel modeloTablaProducto = new DefaultTableModel();
    
    //Instanciar metodo
    ProveedorProductoMethod PP = new ProveedorProductoMethod();
    //Objeto conexión a la base de datos
    ConnectionDB CBD = new ConnectionDB();

    //VAriable para comprobar cambios en modificar
    private double precioCompraOriginal;
    private String tiempoOriginal;
    private Date fechaOriginal;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Frm_Proveedor_Producto.class.getName());

    public Frm_Proveedor_Producto() {
        FlatLightLaf.setup();
        initComponents();
        //titulo
        this.setTitle("Mantenimiento de la relacion entre  Proveedor y Producto");
        
        //Posicion
        this.setLocationRelativeTo(null);
       
        //Metodo para cargar el combobox
        cargarDatosCombos();
        
        //definir los encabezados de la tabla
        String titulos[]={"ID Proveedor","ID Producto","Nombre del proveedor","Nombre del producto","Precio de compra","Tiempo de entrega(Dias)","Fecha de registro","Estado"};
        
        //Asignar los tiutlos al modelo
        modeloTablaProducto.setColumnIdentifiers(titulos);
        
        //Establecer el modelo a la JTable
        JTABLE_Mant_Proveedor_Producto.setModel(modeloTablaProducto);
        
        //ocultar columnas sesibles o internas: solo visual, noafecta al modelo, ocultar columna "id_producto, observacion_producto y estado"
        JTABLE_Mant_Proveedor_Producto.getColumnModel().getColumn(0).setMinWidth(0);
        JTABLE_Mant_Proveedor_Producto.getColumnModel().getColumn(0).setMaxWidth(0);
        JTABLE_Mant_Proveedor_Producto.getColumnModel().getColumn(0).setWidth(0);
        
        
        JTABLE_Mant_Proveedor_Producto.getColumnModel().getColumn(1).setMinWidth(0);
        JTABLE_Mant_Proveedor_Producto.getColumnModel().getColumn(1).setMaxWidth(0);
        JTABLE_Mant_Proveedor_Producto.getColumnModel().getColumn(1).setWidth(0);
        
        JTABLE_Mant_Proveedor_Producto.getColumnModel().getColumn(7).setMinWidth(0);
        JTABLE_Mant_Proveedor_Producto.getColumnModel().getColumn(7).setMaxWidth(0);
        JTABLE_Mant_Proveedor_Producto.getColumnModel().getColumn(7).setWidth(0);
        
        //Desabilitar campo de codigo (solo se mostrara no se escribe)
        BTN_Nuevo.setEnabled(false);
        BTN_Guardar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jComboBox_Proveedor = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        TXT_PrecioCompra = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox_Producto = new javax.swing.JComboBox<>();
        TXT_TiempoEntrega = new javax.swing.JTextField();
        jDateChooserFechaRegistro = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_Proveedor_Producto = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXT_BuscarProveedor_producto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        BTN_Cerrar1 = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();
        BTN_PDF = new javax.swing.JButton();
        BTN_Nuevo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        BTN_Modificar = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jCheckBoxListarInactivos = new javax.swing.JCheckBox();
        jCheckBoxActivar = new javax.swing.JCheckBox();
        BTN_ListarProductos = new javax.swing.JButton();
        BTN_Desactivar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Producto");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Precio de compra*");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, 110, -1));

        jComboBox_Proveedor.setEditable(true);
        jComboBox_Proveedor.addActionListener(this::jComboBox_ProveedorActionPerformed);
        jPanel1.add(jComboBox_Proveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 230, 20));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Tiempo de entrega*");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 120, -1));

        TXT_PrecioCompra.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TXT_PrecioCompra.setForeground(new java.awt.Color(0, 0, 204));
        TXT_PrecioCompra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TXT_PrecioCompra.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        TXT_PrecioCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_PrecioCompraKeyTyped(evt);
            }
        });
        jPanel1.add(TXT_PrecioCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, 250, 20));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Fecha de registro*");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, 110, 20));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Proveedor");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, -1, -1));

        jComboBox_Producto.setEditable(true);
        jComboBox_Producto.addActionListener(this::jComboBox_ProductoActionPerformed);
        jPanel1.add(jComboBox_Producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 240, 20));

        TXT_TiempoEntrega.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TXT_TiempoEntrega.setForeground(new java.awt.Color(0, 0, 204));
        TXT_TiempoEntrega.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TXT_TiempoEntrega.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        TXT_TiempoEntrega.addActionListener(this::TXT_TiempoEntregaActionPerformed);
        TXT_TiempoEntrega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TXT_TiempoEntregaKeyTyped(evt);
            }
        });
        jPanel1.add(TXT_TiempoEntrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 260, -1));
        jPanel1.add(jDateChooserFechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 110, 250, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 770, 230));

        JTABLE_Mant_Proveedor_Producto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_Proveedor_Producto.setForeground(new java.awt.Color(0, 0, 204));
        JTABLE_Mant_Proveedor_Producto.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Mant_Proveedor_Producto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_Proveedor_ProductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Mant_Proveedor_Producto);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 1010, 220));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingresar el Nombre del Producto");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, 30));

        TXT_BuscarProveedor_producto.addActionListener(this::TXT_BuscarProveedor_productoActionPerformed);
        TXT_BuscarProveedor_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BuscarProveedor_productoKeyReleased(evt);
            }
        });
        jPanel2.add(TXT_BuscarProveedor_producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 13, 290, -1));

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
        jLabel1.setText("MANTENIMIENTO DE CATALO POR PROVEEDOR");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 830, 30));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_cancel_cogs.png"))); // NOI18N
        BTN_Modificar.setText("    MODIFICAR");
        BTN_Modificar.addActionListener(this::BTN_ModificarActionPerformed);
        jPanel3.add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 280, 165, 48));

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

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCheckBoxListarInactivos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBoxListarInactivos.setText("Listar Catalogo Desactivado");
        jCheckBoxListarInactivos.addActionListener(this::jCheckBoxListarInactivosActionPerformed);
        jPanel4.add(jCheckBoxListarInactivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 190, 30));

        jCheckBoxActivar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBoxActivar.setText("Reactivar Catalogo");
        jCheckBoxActivar.addActionListener(this::jCheckBoxActivarActionPerformed);
        jPanel4.add(jCheckBoxActivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 140, -1));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 130, 210, 120));

        BTN_ListarProductos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_ListarProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/information.png"))); // NOI18N
        BTN_ListarProductos.setText("LISTAR CATALOGO");
        BTN_ListarProductos.addActionListener(this::BTN_ListarProductosActionPerformed);
        jPanel3.add(BTN_ListarProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 40, 200, 50));

        BTN_Desactivar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Desactivar.setText("DESACTIVAR");
        BTN_Desactivar.addActionListener(this::BTN_DesactivarActionPerformed);
        jPanel3.add(BTN_Desactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 280, 170, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1010, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TXT_PrecioCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_PrecioCompraKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_PrecioCompraKeyTyped

    private void jComboBox_ProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_ProveedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_ProveedorActionPerformed

    private void BTN_ListarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ListarProductosActionPerformed

        // 1. Recargar los datos desde la base de datos
        this.listarProveedoresyProductos();

        // 2. Limpiar campos de texto (basado en tus archivos)
        this.TXT_PrecioCompra.setText("");
        this.TXT_TiempoEntrega.setText("");
        this.TXT_BuscarProveedor_producto.setText("");

        // 3. Resetear Selección de Combos (opcional, vuelve al primer item)
        if (jComboBox_Proveedor.getItemCount() > 0) {
            jComboBox_Proveedor.setSelectedIndex(0);
        }
        if (jComboBox_Producto.getItemCount() > 0) {
            jComboBox_Producto.setSelectedIndex(0);
        }

        // 4. Gestión de estados de botones que ya tenías
        this.BTN_Nuevo.setEnabled(true);
        this.BTN_Guardar.setEnabled(false);
        this.BTN_Modificar.setEnabled(false);
        this.BTN_ListarProductos.setEnabled(false); // Se auto-desactiva porque ya estás listando
        this.jCheckBoxListarInactivos.setSelected(false);

        jPanel3.requestFocus();


    }//GEN-LAST:event_BTN_ListarProductosActionPerformed

    private void jCheckBoxActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxActivarActionPerformed
  
        // 1. Obtener fila seleccionada
        int filaSeleccionada = JTABLE_Mant_Proveedor_Producto.getSelectedRow();

        // 2. Validar selección inicial
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una vinculación de la tabla para reactivarla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            if (jCheckBoxActivar.isSelected()) {
                jCheckBoxActivar.setSelected(false);
            }
            return;
        }

        // 3. Obtener IDs de la vinculación (Pareja única)
        int idProv = Integer.parseInt(JTABLE_Mant_Proveedor_Producto.getValueAt(filaSeleccionada, 0).toString());
        int idProd = Integer.parseInt(JTABLE_Mant_Proveedor_Producto.getValueAt(filaSeleccionada, 1).toString());

        String nombreProv = JTABLE_Mant_Proveedor_Producto.getValueAt(filaSeleccionada, 2).toString();
        String nombreProd = JTABLE_Mant_Proveedor_Producto.getValueAt(filaSeleccionada, 3).toString();
        String estadoActual = JTABLE_Mant_Proveedor_Producto.getValueAt(filaSeleccionada, 7).toString();

        // 4. Validar si ya está activo
        if (estadoActual.equalsIgnoreCase("Activo")) {
            JOptionPane.showMessageDialog(this,
                    "La relación entre '" + nombreProv + "' y '" + nombreProd + "' ya se encuentra activa.",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            jCheckBoxActivar.setSelected(false);
            return;
        }

        // 5. Confirmación
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Desea reactivar la vinculación comercial de:\n" + nombreProd + " (Proveedor: " + nombreProv + ")?",
                "Confirmar Reactivación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                // 6. Ejecutar en el DAO de ProveedorProducto (PP)
                // IMPORTANTE: El método debe recibir AMBOS IDs para identificar el registro
                PP.reactivarProveedorProducto(idProv, idProd);

                JOptionPane.showMessageDialog(this,
                        "¡Éxito! La vinculación ha sido reactivada.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // 7. Sincronizar Interfaz
                jCheckBoxListarInactivos.setSelected(false);
                jCheckBoxActivar.setSelected(false);

                // Refrescar tabla (vuelve a listar los activos) y limpiar
                listarProveedoresyProductos();
                limpiarCamposProducto();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                        "Error al intentar reactivar:\n" + ex.getMessage(),
                        "Error de Base de Datos",
                        JOptionPane.ERROR_MESSAGE);

                jCheckBoxActivar.setSelected(false);
            }
        } else {
            jCheckBoxActivar.setSelected(false);
        }

    }//GEN-LAST:event_jCheckBoxActivarActionPerformed

    private void jCheckBoxListarInactivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxListarInactivosActionPerformed
        this.listarProveedoresYProductosInactivos();
        this.BTN_ListarProductos.setEnabled(true);
    }//GEN-LAST:event_jCheckBoxListarInactivosActionPerformed

    private void BTN_DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DesactivarActionPerformed
                                              
        // 1. Obtener la fila seleccionada
        int filaSeleccionada = JTABLE_Mant_Proveedor_Producto.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una vinculación de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Obtener los IDs de la relación (Columnas 0 y 1 de tu Vista)
        int idProv = Integer.parseInt(JTABLE_Mant_Proveedor_Producto.getValueAt(filaSeleccionada, 0).toString());
        int idProd = Integer.parseInt(JTABLE_Mant_Proveedor_Producto.getValueAt(filaSeleccionada, 1).toString());

        // Nombres para el mensaje de confirmación
        String nombreProv = JTABLE_Mant_Proveedor_Producto.getValueAt(filaSeleccionada, 2).toString();
        String nombreProd = JTABLE_Mant_Proveedor_Producto.getValueAt(filaSeleccionada, 3).toString();
        String estadoActual = JTABLE_Mant_Proveedor_Producto.getValueAt(filaSeleccionada, 7).toString();

        // 3. Validar si ya está desactivado
        if (estadoActual.equalsIgnoreCase("Inactivo")) {
            JOptionPane.showMessageDialog(this, "Esta relación ya se encuentra inactiva.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // 4. Confirmación
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Desea desactivar el suministro de '" + nombreProd + "' por parte de '" + nombreProv + "'?",
                "Confirmar Desactivación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            try {
                // 5. Llamada al DAO de ProveedorProducto (PP)
                // IMPORTANTE: Tu método en el DAO debe recibir AMBOS IDs
                PP.desactivarProveedorProducto(idProv, idProd);

                JOptionPane.showMessageDialog(this, "Vinculación desactivada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // 6. Actualizar interfaz según el CheckBox
                if (jCheckBoxListarInactivos.isSelected()) {
                    listarProveedoresYProductosInactivos();
                } else {
                    listarProveedoresyProductos();
                }

                // 7. Limpiar
                limpiarCamposProducto();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al desactivar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_BTN_DesactivarActionPerformed

    private void TXT_BuscarProveedor_productoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarProveedor_productoKeyReleased
        this.buscarProductosPorNombre();
    }//GEN-LAST:event_TXT_BuscarProveedor_productoKeyReleased

    private void TXT_BuscarProveedor_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_BuscarProveedor_productoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_BuscarProveedor_productoActionPerformed

    private void JTABLE_Mant_Proveedor_ProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_Proveedor_ProductoMouseClicked
       
        int filaSeleccionada = JTABLE_Mant_Proveedor_Producto.getSelectedRow();

        // Si se aplicó un filtro/ordenamiento a la tabla, convertimos el índice
        if (filaSeleccionada != -1) {
            filaSeleccionada = JTABLE_Mant_Proveedor_Producto.convertRowIndexToModel(filaSeleccionada);
        }

        // 1. Configuración de botones
        BTN_Modificar.setEnabled(filaSeleccionada != -1);
        BTN_Guardar.setEnabled(false);
        BTN_Nuevo.setEnabled(true);

        // Lógica para BTN_Desactivar (Solo habilitar si no estamos viendo inactivos)
        BTN_Desactivar.setEnabled(filaSeleccionada != -1 && !jCheckBoxListarInactivos.isSelected());

        // 2. Extracción de datos según el orden de tu VISTA SQL
if (filaSeleccionada != -1) {
    try {
        // IDs y textos
        int idProveedor = Integer.parseInt(JTABLE_Mant_Proveedor_Producto.getValueAt(filaSeleccionada, 0).toString());
        int idProducto = Integer.parseInt(JTABLE_Mant_Proveedor_Producto.getValueAt(filaSeleccionada, 1).toString());
        String precio = JTABLE_Mant_Proveedor_Producto.getValueAt(filaSeleccionada, 4).toString();
        String tiempo = JTABLE_Mant_Proveedor_Producto.getValueAt(filaSeleccionada, 5).toString();
        
                // --- CORRECCIÓN DE FECHA ---
                // Extraemos el objeto tal cual viene de la tabla (que debería ser un java.util.Date)
                Object valorFecha = JTABLE_Mant_Proveedor_Producto.getValueAt(filaSeleccionada, 6);

                // 3. Mostrar en los controles
                TXT_PrecioCompra.setText(precio);
                TXT_TiempoEntrega.setText(tiempo);

                // Seteamos la fecha en el JDateChooser
                if (valorFecha instanceof java.util.Date) {
                    jDateChooserFechaRegistro.setDate((java.util.Date) valorFecha);
                }

                // 4 y 5. SELECCIONAR EN COMBOS (Tu lógica de los for está perfecta)
                for (int i = 0; i < jComboBox_Proveedor.getItemCount(); i++) {
                    ProveedorProductoMethod.ComboItem item = jComboBox_Proveedor.getItemAt(i);
                    if (item.getId() == idProveedor) {
                        jComboBox_Proveedor.setSelectedIndex(i);
                        break;
                    }
                }

                for (int i = 0; i < jComboBox_Producto.getItemCount(); i++) {
                    ProveedorProductoMethod.ComboItem item = jComboBox_Producto.getItemAt(i);
                    if (item.getId() == idProducto) {
                        jComboBox_Producto.setSelectedIndex(i);
                        break;
                    }
                }

                // 6. Guardar originales
                this.precioCompraOriginal = Double.parseDouble(precio);
                this.tiempoOriginal = tiempo;
                // Opcional: guardar la fecha original para comparar cambios
                this.fechaOriginal = jDateChooserFechaRegistro.getDate();

                jComboBox_Proveedor.setEnabled(false);
                jComboBox_Producto.setEnabled(false);

            } catch (Exception e) {
                System.err.println("Error al recuperar datos de la tabla: " + e.getMessage());
            }
        }

    }//GEN-LAST:event_JTABLE_Mant_Proveedor_ProductoMouseClicked

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
                PdfPTable pdfTable = new PdfPTable(JTABLE_Mant_Proveedor_Producto.getColumnCount());
                pdfTable.setWidthPercentage(100);

                // Encabezados
                for (int i = 0; i < JTABLE_Mant_Proveedor_Producto.getColumnCount(); i++) {
                    PdfPCell cell = new PdfPCell(new Phrase(JTABLE_Mant_Proveedor_Producto.getColumnName(i)));
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    pdfTable.addCell(cell);
                }

                // Filas de datos
                for (int row = 0; row < JTABLE_Mant_Proveedor_Producto.getRowCount(); row++) {
                    for (int col = 0; col < JTABLE_Mant_Proveedor_Producto.getColumnCount(); col++) {
                        Object value = JTABLE_Mant_Proveedor_Producto.getValueAt(row, col);
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
            for (int i = 0; i < JTABLE_Mant_Proveedor_Producto.getColumnCount(); i++) {
                headerRow.createCell(i).setCellValue(JTABLE_Mant_Proveedor_Producto.getColumnName(i));
            }

            // 4. Escribir los datos fila por fila desde el JTable
            for (int i = 0; i < JTABLE_Mant_Proveedor_Producto.getRowCount(); i++) {
                XSSFRow dataRow = sheet.createRow(i + 1); // Fila 1 en adelante
                for (int j = 0; j < JTABLE_Mant_Proveedor_Producto.getColumnCount(); j++) {
                    Object valor = JTABLE_Mant_Proveedor_Producto.getValueAt(i, j);
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

                try ( // 8. Guardar el archivo
                        FileOutputStream fos = new FileOutputStream(archivo)) {
                    workbook.write(fos);
                    // Cerrar el flujo de salida
                }
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
        this.jComboBox_Producto.setEnabled(true);
        this.jComboBox_Proveedor.setEnabled(true);
        this.BTN_Desactivar.setEnabled(false);
        this.jDateChooserFechaRegistro.setEnabled(false);
        this.jPanel3.requestFocus();
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
      
        // 1. CAPTURAR OBJETOS DE LOS COMBOS
        ProveedorProductoMethod.ComboItem prov = (ProveedorProductoMethod.ComboItem) jComboBox_Proveedor.getSelectedItem();
        ProveedorProductoMethod.ComboItem prod = (ProveedorProductoMethod.ComboItem) jComboBox_Producto.getSelectedItem();

        String precioStr = TXT_PrecioCompra.getText().trim();
        String tiempoStr = TXT_TiempoEntrega.getText().trim();

        // --- VALIDACIONES DE SELECCIÓN ---
        if (prov == null || prov.getId() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un Proveedor.", "Validación", JOptionPane.WARNING_MESSAGE);
            jComboBox_Proveedor.requestFocus();
            return;
        }
        if (prod == null || prod.getId() == 0) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un Producto.", "Validación", JOptionPane.WARNING_MESSAGE);
            jComboBox_Producto.requestFocus();
            return;
        }

        // --- VALIDACIONES DE CAMPOS VACÍOS ---
        if (campoVacio(TXT_PrecioCompra, "Precio de Compra")) {
            return;
        }
        if (campoVacio(TXT_TiempoEntrega, "Tiempo de Entrega")) {
            return;
        }

        // --- VALIDACIONES DE FORMATO ---
        double precioCompra;
        int tiempoEntrega;

        try {
            precioCompra = Double.parseDouble(precioStr.replace(",", "."));
            tiempoEntrega = Integer.parseInt(tiempoStr);

            if (precioCompra <= 0) {
                JOptionPane.showMessageDialog(this, "El precio debe ser mayor a 0.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (tiempoEntrega < 0) {
                JOptionPane.showMessageDialog(this, "El tiempo de entrega no puede ser negativo.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos en Precio y Tiempo.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. CONFIRMACIÓN
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea vincular este proveedor con el producto?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                // 3. INSERTAR USANDO LOS IDS DE LOS COMBOS
                // Usamos PP (tu instancia de ProveedorProductoMethod)
                this.PP.insertarProveedorProducto(prov.getId(), prod.getId(), precioCompra, tiempoEntrega);

                // 4. ÉXITO
                JOptionPane.showMessageDialog(this, "Vinculación registrada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // 5. ACTUALIZAR Y LIMPIAR
                this.listarProveedoresyProductos();
                this.limpiarCamposProducto();

            } catch (SQLException ex) {
                int errorCode = ex.getErrorCode();
                // Ajusta estos códigos según los que definiste en tu nuevo Procedure de vinculación
                switch (errorCode) {
                    case 1062 -> // Código común para duplicados (Primary Key compuesta)
                        JOptionPane.showMessageDialog(this, "Error: Este proveedor ya está vinculado a este producto.", "Duplicado", JOptionPane.ERROR_MESSAGE);
                    default ->
                        JOptionPane.showMessageDialog(this, "Error de base de datos (" + errorCode + "):\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }catch(IllegalArgumentException e){
                // Aquí capturamos el mensaje que escribiste en el 'throw' del DAO
                JOptionPane.showMessageDialog(this, e.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
            }
        }

    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
 
        try {
            // 1. CAPTURAR OBJETOS DE LOS COMBOS (IDs)
            ProveedorProductoMethod.ComboItem prov = (ProveedorProductoMethod.ComboItem) jComboBox_Proveedor.getSelectedItem();
            ProveedorProductoMethod.ComboItem prod = (ProveedorProductoMethod.ComboItem) jComboBox_Producto.getSelectedItem();

            String nuevoPrecioStr = TXT_PrecioCompra.getText().trim();
            String nuevoTiempoStr = TXT_TiempoEntrega.getText().trim();
            java.util.Date nuevaFecha = jDateChooserFechaRegistro.getDate();

            // --- VALIDACIONES DE SELECCIÓN ---
            if (prov == null || prov.getId() == 0 || prod == null || prod.getId() == 0) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una fila de la tabla para modificar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // --- VALIDACIONES DE CAMPOS VACÍOS ---
            if (campoVacio(TXT_PrecioCompra, "Precio de Compra")) {
                return;
            }
            if (campoVacio(TXT_TiempoEntrega, "Tiempo de Entrega")) {
                return;
            }
            if (nuevaFecha == null) {
                JOptionPane.showMessageDialog(this, "La fecha es obligatoria.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // --- CONVERSIÓN Y LÓGICA ---
            double precio = Double.parseDouble(nuevoPrecioStr.replace("S/", "").replace(",", "").trim());
            int tiempo = Integer.parseInt(nuevoTiempoStr);
            java.sql.Date fechaSQL = new java.sql.Date(nuevaFecha.getTime());

            // --- VALIDACIÓN DE CAMBIOS (Comparando con los originales guardados en MouseClicked) ---
            // Asumiendo que guardaste estas variables en el evento MouseClicked
            if (precio == precioCompraOriginal && tiempo == Integer.parseInt(tiempoOriginal)) {
                // Nota: Aquí podrías comparar también la fecha si la guardaste
                JOptionPane.showMessageDialog(this, "No se detectaron cambios para modificar.", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // 2. CONFIRMACIÓN
            int respuesta = JOptionPane.showConfirmDialog(this,
                    "¿Desea actualizar los términos de este proveedor para el producto?",
                    "Confirmación", JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                try {
                    // 3. LLAMAR AL DAO (Usando PP)
                    // Enviamos ID Prov e ID Prod para encontrar el registro, y el resto para actualizar
                    this.PP.modificarProveedorProducto(prov.getId(), prod.getId(), precio, tiempo, fechaSQL);

                    // 4. ÉXITO
                    JOptionPane.showMessageDialog(this, "Registro actualizado correctamente", "Modificación exitosa", JOptionPane.INFORMATION_MESSAGE);

                    // 5. REFRESCAR
                    this.listarProveedoresyProductos();
                    this.limpiarCamposProducto();

                } catch (SQLException ex) {
                    int errorCode = ex.getErrorCode();
                    // Ajusta los códigos según tu procedimiento de MySQL
                    switch (errorCode) {
                        case 20071 ->
                            JOptionPane.showMessageDialog(this, "Error: La relación ya no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                        default ->
                            JOptionPane.showMessageDialog(this, "Error crítico (" + errorCode + "):\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void jComboBox_ProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_ProductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_ProductoActionPerformed

    private void TXT_TiempoEntregaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_TiempoEntregaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_TiempoEntregaKeyTyped

    private void TXT_TiempoEntregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_TiempoEntregaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_TiempoEntregaActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Frm_Proveedor_Producto().setVisible(true));
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
    private javax.swing.JTable JTABLE_Mant_Proveedor_Producto;
    private javax.swing.JTextField TXT_BuscarProveedor_producto;
    private javax.swing.JTextField TXT_PrecioCompra;
    private javax.swing.JTextField TXT_TiempoEntrega;
    private javax.swing.JCheckBox jCheckBoxActivar;
    private javax.swing.JCheckBox jCheckBoxListarInactivos;
    private javax.swing.JComboBox<ProveedorProductoMethod.ComboItem> jComboBox_Producto;
    private javax.swing.JComboBox<ProveedorProductoMethod.ComboItem> jComboBox_Proveedor;
    private com.toedter.calendar.JDateChooser jDateChooserFechaRegistro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
    
    private void cargarDatosCombos() {
        try {
            // 1. CARGAR PROVEEDORES
            // Obtenemos el ResultSet desde el DAO
            ResultSet rsProv = PP.jcombobox_listarProveedores();

            // Limpiamos el combo antes de llenar
            jComboBox_Proveedor.removeAllItems();
            jComboBox_Proveedor.addItem(new ProveedorProductoMethod.ComboItem(0, "<< Seleccionar >>"));
            

            while (rsProv.next()) {
                // Extraemos los datos del ResultSet fila por fila
                int id = rsProv.getInt("ID");
                // Nota: Verifica que en tu vista de proveedor la columna se llame exactamente así:
                String nombre = rsProv.getString("Razón Social (Nombre del Proveedor)");

                // AGREGAMOS UN OBJETO ComboItem AL COMBO
                jComboBox_Proveedor.addItem(new ProveedorProductoMethod.ComboItem(id, nombre));
            }

            // 2. CARGAR PRODUCTOS
            ResultSet rsProd = PP.jcombobox_listarProductos();
            jComboBox_Producto.removeAllItems();
            jComboBox_Producto.addItem(new ProveedorProductoMethod.ComboItem(0, "<< Seleccionar >>"));

            while (rsProd.next()) {
                int id = rsProd.getInt("ID");
                String nombre = rsProd.getString("Nombre del Producto");

                jComboBox_Producto.addItem(new ProveedorProductoMethod.ComboItem(id, nombre));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al llenar los combos: " + e.getMessage());
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
   
    public void listarProveedoresyProductos() {
        // 1. Limpiar la tabla
        JTABLE_Mant_Proveedor_Producto.setAutoCreateRowSorter(true);
        modeloTablaProducto.setRowCount(0);

        try {
            ResultSet rs = PP.listarProveedoresYProductos();

            // 3. Llenar la tabla
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("ID Proveedor"),
                    rs.getInt("ID Producto"),
                    rs.getString("Proveedor"),
                    rs.getString("Producto"),
                    rs.getDouble("Precio de Compra"),
                    rs.getInt("Tiempo de Entrega (días)"),
                    rs.getDate("Fecha de Registro"),
                    // Usamos tu lógica abreviada para el estado
                    rs.getInt("Estado") == 1 ? "Activo" : "Inactivo"
                };
                modeloTablaProducto.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al listar: " + e.getMessage());
        }
    }
    
    //Metodo para listar productos incativos
    private void listarProveedoresYProductosInactivos() {
        // 1. Limpiar la tabla
        JTABLE_Mant_Proveedor_Producto.setAutoCreateRowSorter(true);
        modeloTablaProducto.setRowCount(0);

        try {
            ResultSet rs = PP.listarProveedoresYProductosDesactivados();

            // 3. Llenar la tabla
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("ID Proveedor"),
                    rs.getInt("ID Producto"),
                    rs.getString("Proveedor"),
                    rs.getString("Producto"),
                    rs.getDouble("Precio de Compra"),
                    rs.getInt("Tiempo de Entrega (días)"),
                    rs.getDate("Fecha de Registro"),
                    // Usamos tu lógica abreviada para el estado
                    rs.getInt("Estado") == 1 ? "Activo" : "Inactivo"
                };
                modeloTablaProducto.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al listar: " + e.getMessage());
        }
    }
    private void limpiarCamposProducto() {
        TXT_PrecioCompra.setText("");
        TXT_TiempoEntrega.setText("");
        jDateChooserFechaRegistro.setDate(new java.util.Date());
        jComboBox_Producto.setSelectedIndex(0);
        jComboBox_Proveedor.setSelectedIndex(0); // O -1 si quieres dejarlo vacío
        BTN_Guardar.setEnabled(true);
        BTN_Nuevo.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        BTN_ListarProductos.setEnabled(false);

    }
    
    
    
    //Método para mostrar la relacion entre producto y proveedor
    public void buscarProductosPorNombre() {
        modeloTablaProducto.setRowCount(0);
        String texto = TXT_BuscarProveedor_producto.getText().trim();

        //Si el campo esta vacio, listar todos
        if (texto.isEmpty() && jCheckBoxListarInactivos.isSelected()) {
            this.listarProveedoresYProductosInactivos();//Reutilizamos el metodo para q la tabla no se quede vacia
            return;
        } else if (texto.isEmpty()) {
            this.listarProveedoresyProductos();
            return;
        }

        try {
            boolean hayResultados = false;

            // 2. Llamamos al buscador de la relación Proveedor-Producto (usando PP)
            // Debes asegurarte de tener este método en tu DAO (te lo paso abajo si no lo tienes)
            ResultSet rs = PP.buscarProveedorYProducto(texto);

            while (rs.next()) {
                hayResultados = true;
                Object[] fila = {
                    rs.getInt("ID Proveedor"),
                    rs.getInt("ID Producto"),
                    rs.getString("Proveedor"),
                    rs.getString("Producto"),
                    rs.getDouble("Precio de Compra"),
                    rs.getInt("Tiempo de Entrega (días)"),
                    rs.getDate("Fecha de Registro"),
                    // Usamos tu lógica abreviada para el estado
                    rs.getInt("Estado") == 1 ? "Activo" : "Inactivo"
                };
                modeloTablaProducto.addRow(fila);
            }

            if (!hayResultados) {
                JOptionPane.showMessageDialog(this, "No se encontraron coincidencias para: " + texto,
                        "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al buscar:\n" + e.getMessage(),
                    "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        }
    }
}

