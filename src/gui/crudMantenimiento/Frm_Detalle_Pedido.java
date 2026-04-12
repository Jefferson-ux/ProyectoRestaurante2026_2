package gui.crudMantenimiento;

import com.formdev.flatlaf.FlatLightLaf;
import gui.menu.Frm_MenuPrincipal;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import gui.crudMantenimiento.JD_AgregarPlato;

   /*Método de Mesa*/
import logic.dao.DetallePedidoMethod;

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
import gui.crudMantenimiento.JD_AgregarPlato;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;

//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFRow;

/*pdf*/
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.*;
//import java.text.SimpleDateFormat;
//import java.util.Date;

public class Frm_Detalle_Pedido extends javax.swing.JFrame {
    DefaultTableModel modeloTabla = new DefaultTableModel(
            new Object[]{"ID detalle","ID Pedido", "Platillo", "Cantidad", "Precio", "Subtotal", "Observaciones","DNI E","Empleado","DNI C","Cliente","Fecha"}, 0
    )
    {
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: return Integer.class; // ID Detalle como Int
            case 1: return Integer.class; // ID Pedido como Int
            case 2: return Integer.class; // Cantidad como Int
            default: return String.class; // Lo demás es texto
        }
    }
    
    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Aprovecha para que nadie edite la tabla escribiendo encima
    }
};
    

            

    
    
    //Objeto conexión a la base de datos
    DetallePedidoMethod methods;
    PedidoMethod methodPedido;
    JD_AgregarPlato dialogo;
    ResultSet pedidos;

    public Frm_Detalle_Pedido() throws SQLException {
        FlatLightLaf.setup();
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Mantenimiento de los detalles de los pedidos");

        ImageIcon icono = new ImageIcon(getClass().getResource("/assets/icon_user.png"));
        // 2. Extraemos la imagen del objeto ImageIcon
        Image imagen = icono.getImage();
        // 3. Lo asignamos a la ventana
        this.setIconImage(imagen);

        this.methods = new DetallePedidoMethod();
        this.methodPedido = new PedidoMethod();
        this.pedidos =methodPedido.listarPedido();

        // Títulos que verá el usuario
            // | DNI | Nombres y Apellidos | Nro Mesa | Inicio | Fin | PAX Nro Personas | Registro |
        
                         
         //=>>==>>=>>==>>==>>==>>=>>=>> Subtotal = cantidad x precio_unitario <<==<<==<<==<<==<<==<<==<<==<<==//
         // Mostrar todo eso en TEXTFIELD + Fecha + Observaciones

        JTABLE_Mant_DetallePedido.setModel(modeloTabla);

        TableColumnModel colModel = JTABLE_Mant_DetallePedido.getColumnModel();
       
        // 1. Activa el salto de línea automático
        txtObservaciones.setLineWrap(true);

        // 2. Asegura que el salto ocurra entre palabras y no corte letras a la mitad
        txtObservaciones.setWrapStyleWord(true);
        
        
// 1. Definimos el Renderer con el Fix del color azul
DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
    @Override
    public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        return this;
    }
}; // <--- ¡AQUÍ faltaba cerrar la llave y el punto y coma!

// 2. Aplicamos a las columnas
for (int i = 0; i < JTABLE_Mant_DetallePedido.getColumnCount(); i++) {
    // Saltamos Platillo (2) y Observaciones (6) para que se lean a la izquierda
    if (i != 2 && i != 6) { 
        JTABLE_Mant_DetallePedido.getColumnModel().getColumn(i).setCellRenderer(tcr);
    }
}    
        
        
        
        
        
        
        
        
        tcr.setHorizontalAlignment(SwingConstants.CENTER);

for (int i = 0; i < JTABLE_Mant_DetallePedido.getColumnCount(); i++) {
    // Si quieres saltarte la columna de "Platillo" o "Observaciones", usa un IF
    if (i != 1 && i != 5) { 
        JTABLE_Mant_DetallePedido.getColumnModel().getColumn(i).setCellRenderer(tcr);
    }
}
        
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

    // ID: Lo ocultamos
    colModel.getColumn(1).setPreferredWidth(75);
    colModel.getColumn(1).setMaxWidth(80);

    // Plato : Normal
    colModel.getColumn(2).setPreferredWidth(120);

    // Cantidad : Bajo
    colModel.getColumn(3).setPreferredWidth(75);
    colModel.getColumn(3).setMaxWidth(80);

    // Precio
    colModel.getColumn(4).setPreferredWidth(75);

    // Subtotal
    colModel.getColumn(5).setPreferredWidth(75);
    
    colModel.getColumn(6).setPreferredWidth(250);
    colModel.getColumn(6).setMinWidth(150);
    
    colModel.getColumn(7).setPreferredWidth(0);
    colModel.getColumn(7).setMinWidth(0);
    colModel.getColumn(7).setMaxWidth(0);
    
    colModel.getColumn(8).setPreferredWidth(0);
    colModel.getColumn(8).setMinWidth(0);
    colModel.getColumn(8).setMaxWidth(0);
    
    colModel.getColumn(9).setPreferredWidth(0);
    colModel.getColumn(9).setMinWidth(0);
    colModel.getColumn(9).setMaxWidth(0);
    
    colModel.getColumn(10).setPreferredWidth(0);
    colModel.getColumn(10).setMinWidth(0);
    colModel.getColumn(10).setMaxWidth(0);
    
    colModel.getColumn(11).setPreferredWidth(0);
    colModel.getColumn(11).setMinWidth(0);
    colModel.getColumn(11).setMaxWidth(0);
    

    

    // 3. Extras de la Tabla
    JTABLE_Mant_DetallePedido.getTableHeader().setReorderingAllowed(false); // No mover columnas
    JTABLE_Mant_DetallePedido.setRowHeight(25); // Filas más altas para que respire el diseño


        //Desactivar button
/*        BTN_Nuevo.setEnabled(false);
        BTN_Quitar.setEnabled(false);
        BTN_Guardar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        txtFechaRegistro.setEnabled(false);

        */
        
        
        //JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spnPrecio, "0.00");
        //spnPrecio.setEditor(editor);
        
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        BTN_Back = new javax.swing.JButton();
        scrollTabla = new javax.swing.JScrollPane();
        JTABLE_Mant_DetallePedido = new javax.swing.JTable();
        BTN_PDF = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();
        BTN_Cerrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXT_BuscarPorPedido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtFechaRegistro = new javax.swing.JTextField();
        txtIdPedido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtIdDetalle = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtPlato = new javax.swing.JTextField();
        txtSubtotal = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtPrecio = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtDniCliente = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtNombresCliente = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtDniEmpleado = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtNombresEmpleado = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        BTN_Filtrar1 = new javax.swing.JButton();
        BTN_Filtrar = new javax.swing.JButton();
        BTN_VerDetalles = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MANTENIMIENTO - DETALLE DEL PEDIDO");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 830, 30));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel3.add(scrollTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 910, 230));

        BTN_PDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pdf.png"))); // NOI18N
        BTN_PDF.setText("     Exportar PDF");
        BTN_PDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_PDFActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_PDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 650, 170, 50));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/excel.png"))); // NOI18N
        BTN_EXCEL.setText("     Exportar XLSX");
        BTN_EXCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_EXCELActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 650, 170, 50));

        BTN_Cerrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_close.png"))); // NOI18N
        BTN_Cerrar.setText("     Cerrar");
        BTN_Cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CerrarActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Cerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 650, 165, 50));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Buscar Pedido:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        TXT_BuscarPorPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_BuscarPorPedidoActionPerformed(evt);
            }
        });
        TXT_BuscarPorPedido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BuscarPorPedidoKeyReleased(evt);
            }
        });
        jPanel2.add(TXT_BuscarPorPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 15, 130, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_search_white.png"))); // NOI18N
        jLabel5.setText("BUSCAR");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 120, 30));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 920, 50));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel2.setText("Observaciones");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 150, 140, -1));

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
        jPanel1.add(txtFechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 170, 30));

        txtIdPedido.setEditable(false);
        txtIdPedido.setBackground(new java.awt.Color(255, 255, 255));
        txtIdPedido.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIdPedido.setForeground(new java.awt.Color(0, 0, 204));
        txtIdPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdPedido.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtIdPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 90, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel3.setText("Codigo de Detalle");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 130, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel6.setText("Fecha de Registro");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 130, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel7.setText("Cantidad");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 70, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel8.setText("Precio Unitario");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 100, 20));

        txtIdDetalle.setEditable(false);
        txtIdDetalle.setBackground(new java.awt.Color(255, 255, 255));
        txtIdDetalle.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIdDetalle.setForeground(new java.awt.Color(0, 0, 204));
        txtIdDetalle.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdDetalle.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtIdDetalle, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 90, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel11.setText("Codigo de Pedido");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 100, -1));

        txtPlato.setEditable(false);
        txtPlato.setBackground(new java.awt.Color(255, 255, 255));
        txtPlato.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtPlato.setForeground(new java.awt.Color(0, 0, 204));
        txtPlato.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlato.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtPlato, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, 200, 30));

        txtSubtotal.setEditable(false);
        txtSubtotal.setBackground(new java.awt.Color(255, 255, 255));
        txtSubtotal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtSubtotal.setForeground(new java.awt.Color(0, 0, 204));
        txtSubtotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSubtotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 90, 30));

        txtCantidad.setEditable(false);
        txtCantidad.setBackground(new java.awt.Color(255, 255, 255));
        txtCantidad.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCantidad.setForeground(new java.awt.Color(0, 0, 204));
        txtCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCantidad.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 90, 30));

        txtPrecio.setEditable(false);
        txtPrecio.setBackground(new java.awt.Color(255, 255, 255));
        txtPrecio.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtPrecio.setForeground(new java.awt.Color(0, 0, 204));
        txtPrecio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecio.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 90, 30));

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane1.setViewportView(txtObservaciones);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, 270, 70));

        jLabel9.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel9.setText("Subtotal");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 90, 20));

        jLabel13.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel13.setText("Platillo solicitado:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 130, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "     Cliente     ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDniCliente.setEditable(false);
        txtDniCliente.setBackground(new java.awt.Color(255, 255, 255));
        txtDniCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtDniCliente.setForeground(new java.awt.Color(0, 0, 204));
        txtDniCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDniCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.add(txtDniCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 200, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel14.setText("DNI ");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 130, -1));

        txtNombresCliente.setEditable(false);
        txtNombresCliente.setBackground(new java.awt.Color(255, 255, 255));
        txtNombresCliente.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNombresCliente.setForeground(new java.awt.Color(0, 0, 204));
        txtNombresCliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombresCliente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtNombresCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresClienteActionPerformed(evt);
            }
        });
        jPanel4.add(txtNombresCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 250, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel12.setText("Apellidos y Nombres:");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 130, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 310, 140));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "     Empleado     ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDniEmpleado.setEditable(false);
        txtDniEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        txtDniEmpleado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtDniEmpleado.setForeground(new java.awt.Color(0, 0, 204));
        txtDniEmpleado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDniEmpleado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.add(txtDniEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 220, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel15.setText("DNI ");
        jPanel6.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 130, -1));

        txtNombresEmpleado.setEditable(false);
        txtNombresEmpleado.setBackground(new java.awt.Color(255, 255, 255));
        txtNombresEmpleado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNombresEmpleado.setForeground(new java.awt.Color(0, 0, 204));
        txtNombresEmpleado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombresEmpleado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.add(txtNombresEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 260, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel16.setText("Apellidos y Nombres:");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 130, -1));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 310, 140));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 870, 260));

        BTN_Filtrar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Filtrar1.setText("Filtrar por pedido");
        BTN_Filtrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_Filtrar1ActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Filtrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, 140, 50));

        BTN_Filtrar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Filtrar.setText("Filtrar por pedido");
        BTN_Filtrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_FiltrarActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Filtrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 140, 50));

        BTN_VerDetalles.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_VerDetalles.setText("VER DETALLES");
        BTN_VerDetalles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_VerDetallesActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_VerDetalles, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 140, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents



  


    private void limpiarCamposPlatoMenu () {
        txtFechaRegistro.setText("");

        
        
        BTN_VerDetalles.setEnabled(false);

    }



    
    ////    CLICKEO EN LA TABLA --> -->
    private void JTABLE_Mant_DetallePedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_DetallePedidoMouseClicked
        if (!JTABLE_Mant_DetallePedido.isEnabled()) {
            return;
        }

        int selectRow = JTABLE_Mant_DetallePedido.getSelectedRow();
        if (selectRow >= 0) {
            String idDetalle = JTABLE_Mant_DetallePedido.getValueAt(selectRow, 0).toString().trim();
            String idPedido = JTABLE_Mant_DetallePedido.getValueAt(selectRow, 1).toString().trim();
            String plato = JTABLE_Mant_DetallePedido.getValueAt(selectRow, 2).toString().trim();
            String cantidad = JTABLE_Mant_DetallePedido.getValueAt(selectRow, 3).toString().trim();
            String precioUnitario = JTABLE_Mant_DetallePedido.getValueAt(selectRow, 4).toString().trim();
            String subtotal = JTABLE_Mant_DetallePedido.getValueAt(selectRow, 5).toString().trim();
            String observaciones = JTABLE_Mant_DetallePedido.getValueAt(selectRow, 6).toString().trim();
            String DNIempleado = JTABLE_Mant_DetallePedido.getValueAt(selectRow, 7).toString().trim();
            String empleado = JTABLE_Mant_DetallePedido.getValueAt(selectRow, 8).toString().trim();
            String DNIcliente = JTABLE_Mant_DetallePedido.getValueAt(selectRow, 9).toString().trim();
            String cliente = JTABLE_Mant_DetallePedido.getValueAt(selectRow, 10).toString().trim();
            String fecha = JTABLE_Mant_DetallePedido.getValueAt(selectRow, 11).toString().trim();
            
            // "Observaciones","DNI E","Empleado","DNI C","Cliente","Fecha"
                txtIdDetalle.setText(idDetalle);
                txtIdPedido.setText(idPedido);
                txtPlato.setText(plato);
                txtCantidad.setText(cantidad);
                txtPrecio.setText(precioUnitario);
                txtSubtotal.setText(subtotal);
                txtObservaciones.setText(observaciones);
                
                txtNombresCliente.setText(cliente);
                txtNombresEmpleado.setText(empleado);
                txtDniCliente.setText(DNIcliente);
                txtDniEmpleado.setText(DNIempleado);
                txtFechaRegistro.setText(fecha);
                
        }
        
        
        BTN_VerDetalles.setEnabled(false);

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

    private void TXT_BuscarPorPedidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarPorPedidoKeyReleased
        // 1. Capturamos lo que el usuario escribió
    String texto = TXT_BuscarPorPedido.getText().trim();
    
    // 2. Obtenemos el Sorter que ya configuramos en el constructor
    // Importante: Casteamos a TableRowSorter para tener acceso a los filtros
    TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) JTABLE_Mant_DetallePedido.getRowSorter();
    
    // 3. Si el campo está vacío, quitamos cualquier filtro previo
    if (texto.isEmpty()) {
        sorter.setRowFilter(null);
    } else {
        try {
            /* 4. Aplicamos el filtro con Regex:
               "(?i)" -> Ignora mayúsculas y minúsculas (Case Insensitive)
               Índices: 1 (ID Pedido), 2 (Platillo), 8 (Empleado), 10 (Cliente)
            */
            sorter.setRowFilter(javax.swing.RowFilter.regexFilter("(?i)" + texto, 1, 2, 8, 10));
            
        } catch (java.util.regex.PatternSyntaxException e) {
            // Esto evita que el programa "explote" si el usuario escribe caracteres raros como [ o *
            System.out.println("Error en el patrón de búsqueda: " + e.getMessage());
        }
    }
    }//GEN-LAST:event_TXT_BuscarPorPedidoKeyReleased

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




    private void TXT_BuscarPorPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_BuscarPorPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_BuscarPorPedidoActionPerformed

    private void BTN_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_BackActionPerformed
        Frm_MenuPrincipal mainMenu = new Frm_MenuPrincipal();
        mainMenu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BTN_BackActionPerformed

    private void BTN_VerDetallesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerDetallesActionPerformed
        this.MostrarDetalles();
        var sorter = new javax.swing.table.TableRowSorter<>(modeloTabla);
        JTABLE_Mant_DetallePedido.setRowSorter(sorter);
    
        
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        int indiceColumna = 1; // Columna 0 es ID_Pedido en tu vista anterior
        sortKeys.add(new RowSorter.SortKey(indiceColumna, SortOrder.ASCENDING));

// 3. Aplicar el orden
        sorter.setSortKeys(sortKeys);
        sorter.sort();
    }//GEN-LAST:event_BTN_VerDetallesActionPerformed

    private void txtFechaRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaRegistroActionPerformed

    private void BTN_FiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_FiltrarActionPerformed
        
    }//GEN-LAST:event_BTN_FiltrarActionPerformed

    private void txtNombresClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresClienteActionPerformed

    private void BTN_Filtrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_Filtrar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_Filtrar1ActionPerformed

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
                try {
                    new Frm_Detalle_Pedido().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Frm_Detalle_Pedido.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Back;
    private javax.swing.JButton BTN_Cerrar;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_Filtrar;
    private javax.swing.JButton BTN_Filtrar1;
    private javax.swing.JButton BTN_PDF;
    private javax.swing.JButton BTN_VerDetalles;
    private javax.swing.JTable JTABLE_Mant_DetallePedido;
    private javax.swing.JTextField TXT_BuscarPorPedido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtDniCliente;
    private javax.swing.JTextField txtDniEmpleado;
    private javax.swing.JTextField txtFechaRegistro;
    private javax.swing.JTextField txtIdDetalle;
    private javax.swing.JTextField txtIdPedido;
    private javax.swing.JTextField txtNombresCliente;
    private javax.swing.JTextField txtNombresEmpleado;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtPlato;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtSubtotal;
    // End of variables declaration//GEN-END:variables

    
 
    
    
    
  
    
    public void MostrarDetalles() {
        //Ordenar ASC, DESC
        JTABLE_Mant_DetallePedido.setAutoCreateRowSorter(true);
        //Limpiar la tabla antes de mostrar nuevos datos
        modeloTabla.setRowCount(0);
        try {
            //Llama al método que retorna los datos de plato menues
            ResultSet rs = this.methods.listarDetalle();
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("ID detalle"),
                    rs.getInt("ID pedido"),
                    rs.getString("Nombre de Platillo"),
                    rs.getString("Cantidad Pedida"),
                    rs.getString("Precio Formateado"),
                    rs.getString("Subtotal Formateado"),                    
                    rs.getString("Observaciones"),
                    rs.getString("DNI Empleado"),
                    rs.getString("Nombre Empleado"),
                    rs.getString("DNI Cliente"),
                    rs.getString("Nombre Cliente"),
                    rs.getString("Fecha del Pedido")
                };
                modeloTabla.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los resultados:\n" + e.getMessage(),
                    "ErrorDeConsulta", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    
    
    public void actualizarTablaPrincipal() {
    try {
        // 1. Limpiamos las filas actuales para no duplicar datos
        modeloTabla.setRowCount(0);
        
        // 2. Llamamos al ResultSet de tu DAO
        // Asegúrate de que el método en DetallePedidoMethod se llame así o cámbialo
        ResultSet rs = methods.listarDetalle(); 

        while (rs.next()) {
            // 3. Creamos el array con los datos exactos de tu Object[]
            Object[] fila = {
                    rs.getInt("ID detalle"),
                    rs.getInt("ID pedido"),
                    rs.getString("Nombre de Platillo"),
                    rs.getString("Cantidad Pedida"),
                    rs.getString("Precio Formateado"),
                    rs.getString("Subtotal Formateado"),                    
                    rs.getString("Observaciones"),
                    rs.getString("DNI Empleado"),
                    rs.getString("Nombre Empleado"),
                    rs.getString("DNI Cliente"),
                    rs.getString("Nombre Cliente"),
                    rs.getString("Fecha del Pedido")
            };
            
            // 4. Agregamos la fila al modelo
            modeloTabla.addRow(fila);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al recargar la tabla: " + e.getMessage());
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
