package gui.crudMantenimiento;

import com.formdev.flatlaf.FlatLightLaf;
import gui.menu.Frm_MenuPrincipal;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

   /*Método de Mesa*/
import logic.dao.ReservaMethod;


  /*excel*/
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.TableColumnModel;
import logic.dao.DetallePedidoMethod;
import logic.dao.PedidoMethod;

//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFRow;

/*pdf*/
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.*;
//import java.text.SimpleDateFormat;
//import java.util.Date;

public class Frm_Detalle_Pedido extends javax.swing.JFrame {

    DefaultTableModel modeloTablaReserva = new DefaultTableModel();
    //Objeto conexión a la base de datos
    ReservaMethod methods;

    public Frm_Detalle_Pedido() {
        FlatLightLaf.setup();
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Mantenimiento de los detalles de los pedidos");

        ImageIcon icono = new ImageIcon(getClass().getResource("/assets/icon_user.png"));
        // 2. Extraemos la imagen del objeto ImageIcon
        Image imagen = icono.getImage();
        // 3. Lo asignamos a la ventana
        this.setIconImage(imagen);

        this.methods = new ReservaMethod();

        // Títulos que verá el usuario
            // | DNI | Nombres y Apellidos | Nro Mesa | Inicio | Fin | PAX Nro Personas | Registro |
        String[] header = {"ID","Plato","Cantidad","Precio","Subtotal",};
                         
         //=>>==>>=>>==>>==>>==>>=>>=>> Subtotal = cantidad x precio_unitario <<==<<==<<==<<==<<==<<==<<==<<==//
         // Mostrar todo eso en TEXTFIELD + Fecha + Observaciones


        modeloTablaReserva.setColumnIdentifiers(header);
        JTABLE_Mant_DetallePedido.setModel(modeloTablaReserva);

        TableColumnModel colModel = JTABLE_Mant_DetallePedido.getColumnModel();
        
        txtNombrePlato.setEditable(false);
        txtPrecio.setEditable(false);
        jTextAreaObservaciones.setEditable(false);
        jComboBoxCategoria.setEnabled(false);

        
        //
        /*
        Cálculo Automático: Como tienes un campo Subtotal, haz que cuando 
            cambien el valor del JSpinner de Cantidad, el Subtotal se actualice
            solito en la pantalla antes de darle a "Guardar".

        Buscador Inteligente: Tu vista tiene el ID pedido. Podrías hacer que el 
            buscador filtre todos los platos de un solo pedido. Así el mesero 
            pone "Pedido 105" y abajo sale toda la lista de lo que consumieron.
        */

    // ID: Lo ocultamos
    colModel.getColumn(0).setPreferredWidth(0);
    colModel.getColumn(0).setMinWidth(0);
    colModel.getColumn(0).setMaxWidth(0);

    // Plato : Normal
    colModel.getColumn(1).setPreferredWidth(100);

    // Cantidad : Bajo
    colModel.getColumn(2).setPreferredWidth(80);
    colModel.getColumn(2).setMaxWidth(80);

    // Precio
    colModel.getColumn(3).setPreferredWidth(80);

    // Cantidad
    colModel.getColumn(4).setPreferredWidth(100);
    
    // Subtotal
    colModel.getColumn(4).setPreferredWidth(80);
    
    

    // 3. Extras de la Tabla
    JTABLE_Mant_DetallePedido.getTableHeader().setReorderingAllowed(false); // No mover columnas
    JTABLE_Mant_DetallePedido.setRowHeight(25); // Filas más altas para que respire el diseño


        //Desactivar button
        BTN_Nuevo.setEnabled(false);
        BTN_Quitar.setEnabled(false);
        BTN_Guardar.setEnabled(false);
        BTN_Cancel.setVisible(false);
        BTN_Modificar.setEnabled(false);
        txtFechaRegistro.setEnabled(false);

        
        
        
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spnPrecio, "0.00");
        spnPrecio.setEditor(editor);
        
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFechaRegistro = new javax.swing.JTextField();
        txtIdPedido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtIdDetalle = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        cmbPlato = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        spnCantidad = new javax.swing.JSpinner();
        spnPrecio = new javax.swing.JSpinner();
        txtSubtotal = new javax.swing.JFormattedTextField();
        scrollObs = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        BTN_VerDetalles = new javax.swing.JButton();
        BTN_Quitar = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Cancel = new javax.swing.JButton();
        BTN_Back = new javax.swing.JButton();
        scrollTabla = new javax.swing.JScrollPane();
        JTABLE_Mant_DetallePedido = new javax.swing.JTable();
        BTN_PDF = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();
        BTN_Cerrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXT_BuscarDetalles = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MANTENIMIENTO - DETALLE DEL PEDIDO");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 830, 30));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel2.setText("Subtotal");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, 130, -1));

        txtFechaRegistro.setEditable(false);
        txtFechaRegistro.setBackground(new java.awt.Color(255, 255, 255));
        txtFechaRegistro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtFechaRegistro.setForeground(new java.awt.Color(0, 0, 204));
        txtFechaRegistro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaRegistro.setText("13/12/2026");
        txtFechaRegistro.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtFechaRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaRegistroActionPerformed(evt);
            }
        });
        jPanel1.add(txtFechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 150, 30));

        txtIdPedido.setEditable(false);
        txtIdPedido.setBackground(new java.awt.Color(255, 255, 255));
        txtIdPedido.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIdPedido.setForeground(new java.awt.Color(0, 0, 204));
        txtIdPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdPedido.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtIdPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, 90, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel3.setText("Codigo de Detalle");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 130, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel6.setText("Fecha de Registro");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 130, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel7.setText("Cantidad");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 130, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel8.setText("Precio Unitario");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 130, -1));

        txtIdDetalle.setEditable(false);
        txtIdDetalle.setBackground(new java.awt.Color(255, 255, 255));
        txtIdDetalle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIdDetalle.setForeground(new java.awt.Color(0, 0, 204));
        txtIdDetalle.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdDetalle.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtIdDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 90, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel10.setText("Observaciones");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 130, -1));

        cmbPlato.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(cmbPlato, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 180, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel11.setText("Codigo de Pedido");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 130, -1));

        spnCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, 50, 1));
        jPanel1.add(spnCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 150, 30));

        spnPrecio.setModel(new javax.swing.SpinnerNumberModel(0.0d, 0.0d, 500.0d, 0.1d));
        jPanel1.add(spnPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 170, 150, 30));

        txtSubtotal.setEditable(false);
        txtSubtotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtSubtotal.setFocusable(false);
        jPanel1.add(txtSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 240, 150, 30));

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        scrollObs.setViewportView(txtObservaciones);

        jPanel1.add(scrollObs, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 226, 310, 70));

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel12.setText("Platillo:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 130, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 720, 330));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BTN_VerDetalles.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_VerDetalles.setText("VER DETALLES");
        BTN_VerDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_VerDetallesActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_VerDetalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 20, 150, 50));

        BTN_Quitar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Quitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_delete.png"))); // NOI18N
        BTN_Quitar.setText("     QUITAR");
        BTN_Quitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_QuitarActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Quitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 280, 150, 48));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_update.png"))); // NOI18N
        BTN_Modificar.setText("    MODIFICAR");
        BTN_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ModificarActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 219, 150, 48));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_save.png"))); // NOI18N
        BTN_Guardar.setText("     GUARDAR");
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
        jPanel3.add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 157, 150, 48));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_add.png"))); // NOI18N
        BTN_Nuevo.setText("      NUEVO");
        BTN_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_NuevoActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 90, 150, 48));

        BTN_Cancel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_cancel.png"))); // NOI18N
        BTN_Cancel.setText("     CANCELAR");
        BTN_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CancelActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 94, 150, 48));

        BTN_Back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_back.png"))); // NOI18N
        BTN_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_BackActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Back, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 650, 50, 50));

        JTABLE_Mant_DetallePedido.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_DetallePedido.setForeground(new java.awt.Color(0, 0, 204));
        JTABLE_Mant_DetallePedido.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Mant_DetallePedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_DetallePedidoMouseClicked(evt);
            }
        });
        scrollTabla.setViewportView(JTABLE_Mant_DetallePedido);

        jPanel3.add(scrollTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 920, 220));

        BTN_PDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pdf.png"))); // NOI18N
        BTN_PDF.setText("     Exportar PDF");
        BTN_PDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_PDFActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_PDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 660, 170, 50));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/excel.png"))); // NOI18N
        BTN_EXCEL.setText("     Exportar XLSX");
        BTN_EXCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_EXCELActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 660, 170, 50));

        BTN_Cerrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_close.png"))); // NOI18N
        BTN_Cerrar.setText("     Cerrar");
        BTN_Cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CerrarActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 660, 165, 50));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Buscar Detalles:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, 30));

        TXT_BuscarDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_BuscarDetallesActionPerformed(evt);
            }
        });
        TXT_BuscarDetalles.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BuscarDetallesKeyReleased(evt);
            }
        });
        jPanel2.add(TXT_BuscarDetalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 13, 290, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_search_white.png"))); // NOI18N
        jLabel5.setText("BUSCAR");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 120, 30));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 920, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Rellenar el ComboBox
    private void cargarComboBoxCategoria() {
        try {
          jComboBoxCategoria.removeAllItems();
          jComboBoxCategoria.addItem("<<Seleccionar>>"); // Opción por defecto
          ResultSet rs = methods.comboListarCategorias();
          while (rs.next()) {
            jComboBoxCategoria.addItem(rs.getString("Nombre de Categoría"));
          }
        } catch (SQLException e) {
          JOptionPane.showMessageDialog(this, "Error al cargar las Categorías: " + e.getMessage());
        }
      }


    private void limpiarCamposPlatoMenu () {
        txtFechaRegistro.setText("");
        txtNombrePlato.setText("");
        jComboBoxCategoria.setSelectedIndex(0);
        BTN_Guardar.setEnabled(true);
        BTN_Modificar.setEnabled(false);
        BTN_Quitar.setEnabled(false);
        BTN_VerDetalles.setEnabled(false);

    }



    ////    CLICKEO EN LA TABLA --> -->
    private void JTABLE_Mant_DetallePedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_DetallePedidoMouseClicked
        if (!JTABLE_Mant_DetallePedido.isEnabled()) {
            return;
        }

        int selectRow = JTABLE_Mant_DetallePedido.getSelectedRow();
        if (selectRow >= 0) {
            String codigo = JTABLE_Mant_DetallePedido.getValueAt(selectRow, 0).toString().trim();
            String nombre_plato = JTABLE_Mant_DetallePedido.getValueAt(selectRow, 1).toString().trim();
            String precio = JTABLE_Mant_DetallePedido.getValueAt(selectRow, 2).toString().trim();
            String categoria = JTABLE_Mant_DetallePedido.getValueAt(selectRow, 3).toString().trim();
            String descripcion = JTABLE_Mant_DetallePedido.getValueAt(selectRow, 4).toString().trim();

            txtFechaRegistro.setText(codigo);
            txtNombrePlato.setText(nombre_plato);
            txtPrecio.setText(precio);
            jTextAreaObservaciones.setText(descripcion);

            String categoriaOriginal = categoria;
            boolean find = false;
            for (int i=0;i<jComboBoxCategoria.getItemCount();i++){
                String item = jComboBoxCategoria.getItemAt(i).trim();
                if (item.equalsIgnoreCase(categoria)){
                    jComboBoxCategoria.setSelectedIndex(i);
                    find=true;
                    break;
                }

            }


        }

        txtNombrePlato.setEditable(true);
        txtPrecio.setEditable(true);
        jComboBoxCategoria.setEnabled(true);
        jTextAreaObservaciones.setEditable(true);

        BTN_Guardar.setEnabled(false);
        BTN_VerDetalles.setEnabled(false);
        BTN_Modificar.setEnabled(true);
        BTN_Quitar.setEnabled(true);

    }//GEN-LAST:event_JTABLE_Mant_DetallePedidoMouseClicked





    private void BTN_EXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EXCELActionPerformed
    /*     try {
      // 1. Crear un nuevo libro de Excel (.xlsx)
      XSSFWorkbook workbook = new XSSFWorkbook();
      // 2. Crear una hoja dentro del libro
      XSSFSheet sheet = workbook.createSheet("Mesaes");
      // 3. Escribir la fila de encabezados desde el JTable
      XSSFRow headerRow = sheet.createRow(0); // Fila 0 para encabezados
      for (int i = 0; i < JTABLE_Mant_Mesa.getColumnCount(); i++) {
        headerRow.createCell(i).setCellValue(JTABLE_Mant_Mesa.getColumnName(i));
      }
      // 4. Escribir los datos fila por fila desde el JTable
      for (int i = 0; i < JTABLE_Mant_Mesa.getRowCount(); i++) {
        XSSFRow dataRow = sheet.createRow(i + 1); // Fila 1 en adelante
        for (int j = 0; j < JTABLE_Mant_Mesa.getColumnCount(); j++) {
          Object valor = JTABLE_Mant_Mesa.getValueAt(i, j);
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
*/
    }//GEN-LAST:event_BTN_EXCELActionPerformed

    private void TXT_BuscarDetallesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarDetallesKeyReleased
        this.BuscarMesaPorNombre();
    }//GEN-LAST:event_TXT_BuscarDetallesKeyReleased

    private void BTN_CerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CerrarActionPerformed

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
    }//GEN-LAST:event_BTN_CerrarActionPerformed




    private void BTN_PDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_PDFActionPerformed
  /*
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
        PdfPTable pdfTable = new PdfPTable(JTABLE_Mant_Mesa.getColumnCount());
        pdfTable.setWidthPercentage(100);
        // Encabezados
        for (int i = 0; i < JTABLE_Mant_Mesa.getColumnCount(); i++) {
          PdfPCell cell = new PdfPCell(new Phrase(JTABLE_Mant_Mesa.getColumnName(i)));
          cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
          pdfTable.addCell(cell);
        }

        // Filas de datos
        for (int row = 0; row < JTABLE_Mant_Mesa.getRowCount(); row++) {
          for (int col = 0; col < JTABLE_Mant_Mesa.getColumnCount(); col++) {
            Object value = JTABLE_Mant_Mesa.getValueAt(row, col);
            pdfTable.addCell(value != null ? value.toString() : "");
          }
        }
        document.add(pdfTable);
        // Pie de página con nombre del usuario
        document.add(new Paragraph(" "));
        String usuario = "Ñiquen Maqui Jefferson"; // Puedes hacerlo dinámico si lo obtienes de sesión
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

       */
    }//GEN-LAST:event_BTN_PDFActionPerformed




    private void TXT_BuscarDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_BuscarDetallesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_BuscarDetallesActionPerformed

    private void BTN_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_BackActionPerformed
        Frm_MenuPrincipal mainMenu = new Frm_MenuPrincipal();
        mainMenu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BTN_BackActionPerformed

    private void BTN_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CancelActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea cancelar la operación?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {

            BTN_Guardar.setEnabled(false);
            BTN_Quitar.setEnabled(false);
            BTN_Modificar.setEnabled(false);
            BTN_Nuevo.setVisible(true);
            BTN_Cancel.setVisible(false);

            txtNombrePlato.setEditable(false);
            txtPrecio.setEditable(false);
            jTextAreaObservaciones.setEditable(false);
            jComboBoxCategoria.setEnabled(false);

            JTABLE_Mant_DetallePedido.setEnabled(true);

        }
    }//GEN-LAST:event_BTN_CancelActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed

        txtFechaRegistro.setText("");
        txtNombrePlato.setText("");
        txtPrecio.setText("");
        txtNombrePlato.setText("");
        jTextAreaObservaciones.setText("");

        txtNombrePlato.requestFocus();
        txtNombrePlato.setEditable(true);
        txtPrecio.setEditable(true);
        jComboBoxCategoria.setEnabled(false);
        jTextAreaObservaciones.setEditable(true);

        // 1. Limpiar la selección actual (Que no quede nada pintado de azul)
        JTABLE_Mant_DetallePedido.clearSelection();

        // 2. Deshabilitar la tabla para que no se pueda hacer clic
        JTABLE_Mant_DetallePedido.setEnabled(false);

        txtNombrePlato.setEditable(true);
        txtPrecio.setEditable(true);
        jTextAreaObservaciones.setEditable(true);
        jComboBoxCategoria.setEnabled(true);

        BTN_Guardar.setEnabled(true);
        BTN_Quitar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        BTN_Nuevo.setVisible(false);
        BTN_Cancel.setVisible(true);
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed

        // 1. Validar que el campo no esté vacío
        String nombre = txtNombrePlato.getText().trim();
        String precio = txtPrecio.getText().trim();
        String categoria = (String) jComboBoxCategoria.getSelectedItem();
        String descripcion = jTextAreaObservaciones.getText().trim();

        if (nombre==null || nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre del Plato", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            txtNombrePlato.requestFocus();
            return;
        }
        if (precio==null || precio.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el precio del Plato", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            txtPrecio.requestFocus();
            return;
        }
        int codigoCategoria=-1;
        try {
            codigoCategoria = methods.comboSeleccionarID(categoria);
        } catch (SQLException ex) {
            Logger.getLogger(Frm_Detalle_Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (codigoCategoria==-1){
            JOptionPane.showMessageDialog(null, "No se encontró la categoría seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            if (this.methods.existePlatoConNombre(nombre, 0)) {
                JOptionPane.showMessageDialog(this, "Ya existe otra categoría con el mismo nombre.",
                    "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }       } catch (SQLException ex) {
                Logger.getLogger(Frm_Categoria.class.getName()).log(Level.SEVERE, null, ex);
            }

            // 2. Confirmar si el usuario desea guardar
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea guardar el registro?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {

                try {
                    // 3. Llamar al método para insertar
                    this.methods.insertarPlatoMenu(nombre,descripcion,Double.parseDouble(precio),codigoCategoria);

                    // 4. Mostrar mensaje de éxito
                    JOptionPane.showMessageDialog(this, "Plato registrado correctamente", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);

                    // 5. Actualizar tabla y limpiar campos
                    this.MostrarPlatosMenu();
                    this.limpiarCamposPlatoMenu();

                    txtNombrePlato.setEditable(false);
                    txtPrecio.setEditable(false);
                    jTextAreaObservaciones.setEditable(false);
                    jComboBoxCategoria.setEnabled(false);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error al registrar el plato:\n" + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
                } finally {
                    txtNombrePlato.setEditable(false);
                    txtPrecio.setEditable(false);
                    jComboBoxCategoria.setEditable(false);
                    jTextAreaObservaciones.setEditable(false);

                    BTN_Guardar.setEnabled(false);
                    BTN_VerDetalles.setEnabled(false);
                    BTN_Modificar.setEnabled(false);
                    BTN_Quitar.setEnabled(false);
                    BTN_Nuevo.setVisible(true);
                    BTN_Cancel.setVisible(false);
                    JTABLE_Mant_DetallePedido.setEnabled(true);
                }

            }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
        try {
            // 1. Obtener datos del formulario
            String codigoStr = txtFechaRegistro.getText().trim();
            String nuevoNombre = txtNombrePlato.getText().trim();
            String nuevoPrecioRaw = txtPrecio.getText().trim();
            String nombreCategoria = (String) jComboBoxCategoria.getSelectedItem();
            String nuevaDescripcion = jTextAreaObservaciones.getText().trim();

            // 2. Validar campos obligatorios (Siguiendo tu lógica de validación)
            if (codigoStr.isEmpty() || nuevoNombre.isEmpty() || nuevoPrecioRaw.isEmpty() ||
                nombreCategoria == null || nombreCategoria.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos obligatorios.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 3. Validar longitud del nombre (Como en tu imagen de Escuelas)
            if (nuevoNombre.length() > 85) {
                JOptionPane.showMessageDialog(this, "El nombre del plato no debe exceder 85 caracteres.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 4. Conversiones y Limpieza de datos
            int idPlato = Integer.parseInt(codigoStr);
            // Quitamos el "S/ " por si acaso viene de la tabla
            String precioLimpio = nuevoPrecioRaw.replace("S/", "").replace("S/ ", "").trim();
            double precio = Double.parseDouble(precioLimpio);

            // 5. Obtener ID de la categoría
            int idCategoria = this.methods.comboSeleccionarID(nombreCategoria);
            if (idCategoria == -1) {
                JOptionPane.showMessageDialog(this, "La categoría seleccionada no es válida.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                if (this.methods.existePlatoConNombre(nuevoNombre, idPlato)) {
                    JOptionPane.showMessageDialog(this, "Ya existe otro plato con el mismo nombre.",
                        "Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }       } catch (SQLException ex) {
                    Logger.getLogger(Frm_Categoria.class.getName()).log(Level.SEVERE, null, ex);
                }

                // 6. Confirmación del usuario
                int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea modificar este plato?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    // 7. Llamada al método que adaptaste (modificarPlatoMenu)
                    // El método ya incluye la validación de existePlatoConNombre internamente
                    this.methods.modificarPlatoMenu(idPlato, nuevoNombre, nuevaDescripcion, precio, idCategoria);

                    JOptionPane.showMessageDialog(this, "Plato actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                    // 8. Refrescar interfaz
                    this.MostrarPlatosMenu();
                    this.limpiarCamposPlatoMenu();

                    txtNombrePlato.setEditable(false);
                    txtPrecio.setEditable(false);
                    jTextAreaObservaciones.setEditable(false);
                    jComboBoxCategoria.setEnabled(false);

                    BTN_Modificar.setEnabled(false);
                    BTN_Quitar.setEnabled(false);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El código o el precio no son válidos.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                // Captura el error de "Ya existe un plato con ese nombre" que lanza tu método
                JOptionPane.showMessageDialog(this, e.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al modificar: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void BTN_QuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_QuitarActionPerformed
        // 1. Validar que se haya seleccionado una plato menu
        String codStr = txtFechaRegistro.getText().trim();
        if (codStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Seleccione una plato menu en la tabla para desactivar.","Campo requerido",JOptionPane.WARNING_MESSAGE);
            return;
        }
        int codigo = Integer.parseInt(codStr); // Convertir a entero
        // 2. Confirmar la acción con el usuario
        int opcion = JOptionPane.showConfirmDialog(this,"¿Está seguro de que desea desactivar esta plato menu?","Confirmar desactivación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            try {
                // 3. Llamar al método que ejecuta el procedure de desactivación
                this.methods.desactivarPlatoMenu(codigo);
                // 4. Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(this,"Mesa desactivada correctamente.","Operación exitosa",JOptionPane.INFORMATION_MESSAGE);
                // 5. Actualizar tabla y limpiar campos
                this.MostrarPlatosMenu();
                // Limpia los campos de texto
                txtFechaRegistro.setText("");
                //txtprecio.setText("");
                BTN_Quitar.setEnabled(false);
                BTN_Modificar.setEnabled(false);
            } catch (SQLException ex) {
                // 6. Captura cualquier error lanzado por el procedure (por SIGNAL)
                JOptionPane.showMessageDialog(this,"Error al desactivar plato menu:\n" + ex.getMessage(),"Error de base de datos",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTN_QuitarActionPerformed

    private void BTN_VerDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerDetallesActionPerformed
        this.MostrarPlatosMenu();

        cargarComboBoxCategoria();
        this.BTN_Nuevo.setEnabled(true);
        this.BTN_Guardar.setEnabled(false);
        this.BTN_Quitar.setEnabled(false);
        this.BTN_Modificar.setEnabled(false);
        this.BTN_VerDetalles.setEnabled(false);
    }//GEN-LAST:event_BTN_VerDetallesActionPerformed

    private void txtFechaRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaRegistroActionPerformed

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
            java.util.logging.Logger.getLogger(Frm_Detalle_Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_Detalle_Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_Detalle_Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_Detalle_Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Frm_Detalle_Pedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Back;
    private javax.swing.JButton BTN_Cancel;
    private javax.swing.JButton BTN_Cerrar;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_PDF;
    private javax.swing.JButton BTN_Quitar;
    private javax.swing.JButton BTN_VerDetalles;
    private javax.swing.JTable JTABLE_Mant_DetallePedido;
    private javax.swing.JTextField TXT_BuscarDetalles;
    private javax.swing.JComboBox<String> cmbPlato;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane scrollObs;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JSpinner spnPrecio;
    private javax.swing.JTextField txtFechaRegistro;
    private javax.swing.JTextField txtIdDetalle;
    private javax.swing.JTextField txtIdPedido;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JFormattedTextField txtSubtotal;
    // End of variables declaration//GEN-END:variables

    
 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
