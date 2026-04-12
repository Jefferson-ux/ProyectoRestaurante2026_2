package gui.crudMantenimiento;

import com.formdev.flatlaf.FlatLightLaf;
import gui.menu.Frm_MenuPrincipal;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

   /*Método de Mesa*/
import logic.dao.PedidoMethod;

  /*excel*/
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.table.TableColumnModel;
import gui.crudMantenimiento.JD_Empleado;
import gui.crudMantenimiento.JD_Cliente;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import logic.dao.DetallePedidoMethod.DetallePedido;

//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFRow;

/*pdf*/
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.*;
//import java.text.SimpleDateFormat;
//import java.util.Date;

public class Frm_Pedido extends javax.swing.JFrame {

    DefaultTableModel modeloTablaReserva = new DefaultTableModel();
    JD_Empleado modalEmpleado;
    JD_Cliente modalCliente;
    JD_AgregarPlato dialogo;
    LocalDate current_date; 
// Estas variables guardan los IDs de los buscadores que ya tienes
private int idClienteSeleccionado = -1;
private int idEmpleadoSeleccionado = -1;
private int idTipoPedidoSeleccionado = 1; // Por defecto 1 o el que elijas
    
    //Objeto conexión a la base de datos
    PedidoMethod methods;
    DateTimeFormatter formato;

    public Frm_Pedido() {
        FlatLightLaf.setup();
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Mantenimiento de los Pedidos");
        this.setResizable(false);
        
        

        ImageIcon icono = new ImageIcon(getClass().getResource("/assets/icon_user.png"));
        // 2. Extraemos la imagen del objeto ImageIcon
        Image imagen = icono.getImage();
        // 3. Lo asignamos a la ventana
        this.setIconImage(imagen);
        current_date = LocalDate.now(); 
        
        
        this.methods = new PedidoMethod();

        // Títulos que verá el usuario
        String[] header = {"ID","DNI Cliente","Cliente","DNI Empleado","Empleado","Tipo de Pedido","Fecha de Pedido","Cantidad de Platos","Nombre C","Apellido C","Nombre E","Apellidos E"};

        modeloTablaReserva.setColumnIdentifiers(header);
        JTABLE_Mant_Pedido.setModel(modeloTablaReserva);

        TableColumnModel colModel = JTABLE_Mant_Pedido.getColumnModel();
        
    // ID: Lo ocultamos
    colModel.getColumn(0).setPreferredWidth(0);
    colModel.getColumn(0).setMinWidth(0);
    colModel.getColumn(0).setMaxWidth(0);

    // DNI Cliente: Pequeño
    colModel.getColumn(1).setPreferredWidth(90);
    colModel.getColumn(1).setMaxWidth(90);

    // Cliente: Largo (Cubre Apellidos y Nombres)
    colModel.getColumn(2).setPreferredWidth(160);
    colModel.getColumn(2).setMaxWidth(180);
    
        // DNIEmpleado: Pequeño
    colModel.getColumn(3).setPreferredWidth(90);
    colModel.getColumn(3).setMaxWidth(90);

    // Empleado: Largo (Cubre Apellidos y Nombres)
    colModel.getColumn(4).setPreferredWidth(160);
    colModel.getColumn(4).setMaxWidth(180);

    // Tipo de Pedido
    colModel.getColumn(5).setPreferredWidth(90);
    
    
    // Fecha de Pedido
    colModel.getColumn(6).setPreferredWidth(90);
    
    // Fecha de Pedido
    colModel.getColumn(7).setPreferredWidth(80);
    colModel.getColumn(7).setPreferredWidth(80);
    
    colModel.getColumn(8).setPreferredWidth(0);
    colModel.getColumn(8).setMinWidth(0);
    colModel.getColumn(8).setMaxWidth(0);
    colModel.getColumn(9).setMinWidth(0);
    colModel.getColumn(9).setMaxWidth(0);
    colModel.getColumn(9).setPreferredWidth(0);
    colModel.getColumn(10).setPreferredWidth(0);
    colModel.getColumn(10).setMinWidth(0);
    colModel.getColumn(10).setMaxWidth(0);
    colModel.getColumn(11).setPreferredWidth(0);
    colModel.getColumn(11).setMinWidth(0);
    colModel.getColumn(11).setMaxWidth(0);
    
    CreateDetailTable();

    // 3. Extras de la Tabla
    JTABLE_Mant_Pedido.getTableHeader().setReorderingAllowed(false); // No mover columnas
    JTABLE_Mant_Pedido.setRowHeight(25); // Filas más altas para que respire el diseño


        //Desactivar button
        BTN_Nuevo.setEnabled(false);
        BTN_Desactivar.setEnabled(false);
        BTN_Guardar.setEnabled(false);
        //BTN_Cancel.setVisible(false);
        BTN_Modificar.setEnabled(false);
        txtIdPedido.setEnabled(false);
        formato = DateTimeFormatter.ofPattern("dd/MM/yy");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        BTN_VerPlatos = new javax.swing.JButton();
        BTN_Desactivar = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Back = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_Pedido = new javax.swing.JTable();
        BTN_PDF = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();
        BTN_Cerrar1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXT_BuscarMesas = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        BTN_Desactivar1 = new javax.swing.JButton();
        panel = new javax.swing.JPanel();
        txtIdPedido = new javax.swing.JTextField();
        jPanelCliente = new javax.swing.JPanel();
        txtDNICliente = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtApellidosCliente = new javax.swing.JTextField();
        txtNombresCliente = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        btn_searchCliente = new javax.swing.JButton();
        jPanelEmpleado = new javax.swing.JPanel();
        txtDNIEmpleado = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtApellidosEmpleado = new javax.swing.JTextField();
        txtNombresEmpleado = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        btn_searchEmpleado = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        comboTipoPedido = new javax.swing.JComboBox<>();
        txtFechaPedido = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        btnViewDetails = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        Table_Details = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MANTENIMIENTO DE PEDIDOS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 830, 30));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BTN_VerPlatos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_VerPlatos.setText("VER PEDIDOS");
        BTN_VerPlatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_VerPlatosActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_VerPlatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, 140, 50));

        BTN_Desactivar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Desactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_delete.png"))); // NOI18N
        BTN_Desactivar.setText("     QUITAR");
        BTN_Desactivar.setEnabled(false);
        BTN_Desactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_DesactivarActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Desactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 300, 140, 48));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_update.png"))); // NOI18N
        BTN_Modificar.setText("    MODIFICAR");
        BTN_Modificar.setEnabled(false);
        BTN_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ModificarActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 300, 140, 48));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_save.png"))); // NOI18N
        BTN_Guardar.setText("     GUARDAR");
        BTN_Guardar.setEnabled(false);
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
        jPanel3.add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 300, 140, 48));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_add.png"))); // NOI18N
        BTN_Nuevo.setText("      NUEVO");
        BTN_Nuevo.setEnabled(false);
        BTN_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_NuevoActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, 140, 48));

        BTN_Back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_back.png"))); // NOI18N
        BTN_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_BackActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Back, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 650, 50, 50));

        JTABLE_Mant_Pedido.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_Pedido.setForeground(new java.awt.Color(0, 0, 204));
        JTABLE_Mant_Pedido.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Mant_Pedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_PedidoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Mant_Pedido);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 920, 230));

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

        BTN_Cerrar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_close.png"))); // NOI18N
        BTN_Cerrar1.setText("     Cerrar");
        BTN_Cerrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_Cerrar1ActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 660, 165, 50));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Buscar Pedidos :");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, 30));

        TXT_BuscarMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_BuscarMesasActionPerformed(evt);
            }
        });
        TXT_BuscarMesas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BuscarMesasKeyReleased(evt);
            }
        });
        jPanel2.add(TXT_BuscarMesas, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 13, 290, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_search_white.png"))); // NOI18N
        jLabel5.setText("BUSCAR");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 120, 30));

        BTN_Desactivar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Desactivar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_delete.png"))); // NOI18N
        BTN_Desactivar1.setText("     QUITAR");
        BTN_Desactivar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_Desactivar1ActionPerformed(evt);
            }
        });
        jPanel2.add(BTN_Desactivar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 280, 165, 48));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 920, 50));

        panel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIdPedido.setEditable(false);
        txtIdPedido.setBackground(new java.awt.Color(255, 255, 255));
        txtIdPedido.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIdPedido.setForeground(new java.awt.Color(0, 0, 204));
        txtIdPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdPedido.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel.add(txtIdPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 80, 20));

        jPanelCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Cliente Atendido   ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanelCliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDNICliente.setEditable(false);
        txtDNICliente.setFocusable(false);
        txtDNICliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNIClienteActionPerformed(evt);
            }
        });
        jPanelCliente.add(txtDNICliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 110, 22));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Nombres:*");
        jPanelCliente.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 90, -1));

        txtApellidosCliente.setEditable(false);
        txtApellidosCliente.setFocusable(false);
        jPanelCliente.add(txtApellidosCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 180, 22));

        txtNombresCliente.setEditable(false);
        txtNombresCliente.setFocusable(false);
        jPanelCliente.add(txtNombresCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 180, 22));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Apellidos:*");
        jPanelCliente.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 90, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("DNI:*");
        jPanelCliente.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 90, -1));

        btn_searchCliente.setText("Buscar");
        btn_searchCliente.setEnabled(false);
        btn_searchCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchClienteActionPerformed(evt);
            }
        });
        jPanelCliente.add(btn_searchCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 70, -1));

        panel.add(jPanelCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 60, 230, 190));

        jPanelEmpleado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Empleado Encargado   ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanelEmpleado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDNIEmpleado.setEditable(false);
        txtDNIEmpleado.setFocusable(false);
        txtDNIEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNIEmpleadoActionPerformed(evt);
            }
        });
        jPanelEmpleado.add(txtDNIEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 110, 22));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("DNI:*");
        jPanelEmpleado.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 90, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Nombres:*");
        jPanelEmpleado.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 90, -1));

        txtApellidosEmpleado.setEditable(false);
        txtApellidosEmpleado.setFocusable(false);
        jPanelEmpleado.add(txtApellidosEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 180, 22));

        txtNombresEmpleado.setEditable(false);
        txtNombresEmpleado.setFocusable(false);
        jPanelEmpleado.add(txtNombresEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 180, 22));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Apellidos:*");
        jPanelEmpleado.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 90, -1));

        btn_searchEmpleado.setText("Buscar");
        btn_searchEmpleado.setEnabled(false);
        btn_searchEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchEmpleadoActionPerformed(evt);
            }
        });
        jPanelEmpleado.add(btn_searchEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 70, -1));

        panel.add(jPanelEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 220, 190));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("→");
        panel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 50, 60));

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel6.setText("ID:");
        panel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 30, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Fecha de Pedido:");
        panel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 130, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Detalles del Pedido");
        panel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 120, -1));

        comboTipoPedido.setEnabled(false);
        panel.add(comboTipoPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 30, 170, -1));

        txtFechaPedido.setEditable(false);
        txtFechaPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaPedido.setFocusable(false);
        txtFechaPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaPedidoActionPerformed(evt);
            }
        });
        panel.add(txtFechaPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 150, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Tipo de Pedido:*");
        panel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, 130, -1));

        btnViewDetails.setText("Añadir o Ver");
        btnViewDetails.setEnabled(false);
        btnViewDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDetailsActionPerformed(evt);
            }
        });
        panel.add(btnViewDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 30, 120, -1));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        Table_Details.setModel(new javax.swing.table.DefaultTableModel(
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
        Table_Details.setEnabled(false);
        Table_Details.setFocusable(false);
        jScrollPane2.setViewportView(Table_Details);

        panel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 310, 180));

        jPanel3.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 870, 260));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Rellenar el ComboBox
    private void cargarComboBoxTipoPedido() {
       try {
          comboTipoPedido.removeAllItems();
          comboTipoPedido.addItem("<<Seleccionar>>"); // Opción por defecto
          ResultSet rs = methods.comboListarTipoPedido();
          while (rs.next()) {
            comboTipoPedido.addItem(rs.getString("Tipo de Pedido"));
          }
        } catch (SQLException e) {
          JOptionPane.showMessageDialog(this, "Error al cargar los Tipos de Pedido: " + e.getMessage());
        }
      }


 
    
    
    
    
    

    ////    CLICKEO EN LA TABLA --> -->
    private void JTABLE_Mant_PedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_PedidoMouseClicked
// Dentro de JTABLE_Mant_PedidoMouseClicked...
int selectRow = JTABLE_Mant_Pedido.getSelectedRow();
if (selectRow >= 0) {
    int idPedido = Integer.parseInt(JTABLE_Mant_Pedido.getValueAt(selectRow, 0).toString());
                String id = JTABLE_Mant_Pedido.getValueAt(selectRow, 0).toString().trim();
            String dni_cliente = JTABLE_Mant_Pedido.getValueAt(selectRow, 1).toString().trim();
            String dni_empleado = JTABLE_Mant_Pedido.getValueAt(selectRow, 3).toString().trim();
            String tipo_pedido = JTABLE_Mant_Pedido.getValueAt(selectRow, 5).toString().trim();
            String fecha_pedido = JTABLE_Mant_Pedido.getValueAt(selectRow, 6).toString().trim();
            txtIdPedido.setText(id);
            txtDNICliente.setText(dni_cliente);
            txtDNIEmpleado.setText(dni_empleado);
            //String categoriaOriginal = categoria;
            boolean find = false;
            for (int i=0;i<comboTipoPedido.getItemCount();i++){
                String item = comboTipoPedido.getItemAt(i).trim();
                if (item.equalsIgnoreCase(tipo_pedido)){
                    comboTipoPedido.setSelectedIndex(i);
                    find=true;
                    break;
                }

            }
            txtFechaPedido.setText(fecha_pedido);
            
            String nombresC = JTABLE_Mant_Pedido.getValueAt(selectRow, 8).toString().trim();
            String apellidosC = JTABLE_Mant_Pedido.getValueAt(selectRow, 9).toString().trim();
            String nombresE = JTABLE_Mant_Pedido.getValueAt(selectRow, 10).toString().trim();
            String apellidosE = JTABLE_Mant_Pedido.getValueAt(selectRow, 11).toString().trim();
            
            txtNombresCliente.setText(nombresC);
            txtNombresEmpleado.setText(nombresE);
            txtApellidosCliente.setText(apellidosC);
            txtApellidosEmpleado.setText(apellidosE);
        
        
        this.BTN_Nuevo.setEnabled(true);
        this.BTN_Guardar.setEnabled(false);
        this.BTN_Desactivar.setEnabled(true);
        this.BTN_Modificar.setEnabled(true);
        this.btn_searchCliente.setEnabled(true);
        this.btn_searchEmpleado.setEnabled(true);
        this.btnViewDetails.setEnabled(true);
        
        comboTipoPedido.setEnabled(true);

    
    DefaultTableModel modeloDetalle = (DefaultTableModel) Table_Details.getModel();
    modeloDetalle.setRowCount(0);

    try {
        // Llamamos al nuevo método que acabamos de crear
        ResultSet rs = this.methods.listarDetallesPorId(idPedido);
        
        while (rs.next()) {
            String precio = String.format("%.2f", (rs.getDouble("Precio Unitario")));
            String subtotal = String.format("%.2f", (rs.getDouble("Subtotal")));
            Object[] fila = {
                rs.getInt("ID plato"),
                rs.getString("Nombre de Platillo"),
                rs.getInt("Cantidad Pedida"),
                "S/. " + precio,
                "S/. " + subtotal,
                rs.getString("Observaciones")
            };
            modeloDetalle.addRow(fila);
            
            
            
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar detalles: " + e.getMessage());
    }
}


    }//GEN-LAST:event_JTABLE_Mant_PedidoMouseClicked
    




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

    private void TXT_BuscarMesasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarMesasKeyReleased
        this.BuscarMesaPorNombre();
    }//GEN-LAST:event_TXT_BuscarMesasKeyReleased

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




    private void TXT_BuscarMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_BuscarMesasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_BuscarMesasActionPerformed

    private void BTN_Desactivar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_Desactivar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_Desactivar1ActionPerformed

    private void BTN_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_BackActionPerformed
        Frm_MenuPrincipal mainMenu = new Frm_MenuPrincipal();
        mainMenu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BTN_BackActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed

        String string_current = String.valueOf(current_date); 
        this.txtFechaPedido.setText(string_current);
        
        txtApellidosCliente.setText("");
        txtApellidosEmpleado.setText("");
        txtDNICliente.setText("");
        txtDNIEmpleado.setText("");
        txtIdPedido.setText("");
        txtNombresCliente.setText("");
        txtNombresEmpleado.setText("");
        
        
        if (dialogo!=null){
            dialogo.detalleEmpty();
        }
        
        comboTipoPedido.setSelectedIndex(0);
        
        comboTipoPedido.setEnabled(true);

        // 1. Limpiar la selección actual (Que no quede nada pintado de azul)
        JTABLE_Mant_Pedido.clearSelection();
        
        
        
        this.BTN_Nuevo.setEnabled(false);
        this.BTN_Guardar.setEnabled(true);
        this.BTN_Desactivar.setEnabled(false);
        this.BTN_Modificar.setEnabled(false);
        this.btnViewDetails.setEnabled(true);
        this.btn_searchCliente.setEnabled(true);
        this.btn_searchEmpleado.setEnabled(true);
        comboTipoPedido.setEnabled(true);
        
       // BTN_Nuevo.setVisible(false);
        //BTN_Cancel.setVisible(true);

    // 1. Limpiar la JTable (Memoria visual)
    DefaultTableModel modelo = (DefaultTableModel) Table_Details.getModel();
    modelo.setRowCount(0);
    
    // 2. Resetear IDs de selección
    this.idClienteSeleccionado = -1;
    this.idEmpleadoSeleccionado = -1;
    
    // 3. Limpiar campos de texto
    txtDNICliente.setText("");
    txtNombresCliente.setText("");
    txtDNIEmpleado.setText("");
    txtNombresEmpleado.setText("");
    
    // 4. Opcional: Mensaje de confirmación
    System.out.println("Nuevo pedido iniciado. Memoria limpia.");

        
        
        
        
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
 

    if (JTABLE_Mant_Pedido.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "Agregue platos antes de guardar.");
        return;
    }
    
    // Aquí llamas a tu ejecutarGuardado() que ya tenías programado

        int guardado = ejecutarGuardado();
        
        if (guardado==1||guardado==0){
            this.BTN_Nuevo.setEnabled(true);
            this.BTN_Guardar.setEnabled(false);
            this.BTN_Desactivar.setEnabled(false);
            this.BTN_Modificar.setEnabled(false);
            this.btnViewDetails.setEnabled(false);
            this.btn_searchCliente.setEnabled(false);
            this.btn_searchEmpleado.setEnabled(false);
            comboTipoPedido.setEnabled(false);
        }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
 
    int filaSeleccionada = JTABLE_Mant_Pedido.getSelectedRow();
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione un pedido primero.");
        return;
    }

    try {
        int idPedido = Integer.parseInt(JTABLE_Mant_Pedido.getValueAt(filaSeleccionada, 0).toString());
        int idCli = methods.obtenerIdClientePorDNI(txtDNICliente.getText().trim());
        int idEmp = methods.obtenerIdEmpleadoPorDNI(txtDNIEmpleado.getText().trim());
        int idTipo = comboTipoPedido.getSelectedIndex();

        if (idCli == -1 || idEmp == -1) {
            JOptionPane.showMessageDialog(this, "DNI no encontrado.");
            return;
        }
        
        int aceptar = JOptionPane.showConfirmDialog(null, "¿Desea actualizar el pedido y sus platos?","Confirmar",JOptionPane.YES_NO_OPTION);
        
        if (aceptar == JOptionPane.YES_OPTION){
            // 1. Modificar Cabecera
            methods.modificarPedido(idPedido, idCli, idEmp, idTipo);

            // 2. Modificar Detalles (Usando el modelo de la tabla de abajo)
            DefaultTableModel modeloDetalle = (DefaultTableModel) Table_Details.getModel();
            this.methods.actualizarDetallesDesdeTabla(idPedido,modeloDetalle);

            JOptionPane.showMessageDialog(this, "¡Pedido y Detalles actualizados!");
            limpiarTodo();
            // listarPedidos();
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        ex.printStackTrace();
    }

    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void BTN_DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DesactivarActionPerformed
    // 1. Obtener la fila seleccionada
    int filaSeleccionada = JTABLE_Mant_Pedido.getSelectedRow();

    // 2. Validar que se haya seleccionado algo
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Por favor, seleccione el pedido que desea desactivar.");
        return;
    }

    // 3. Confirmación del usuario (Importante antes de "borrar" algo)
    int confirmar = JOptionPane.showConfirmDialog(this, 
            "¿Estás seguro de que deseas quitar este pedido?", 
            "Confirmar Acción", 
            JOptionPane.YES_NO_OPTION);

    if (confirmar == JOptionPane.YES_OPTION) {
        try {
            // 4. Obtener el ID del pedido (Columna 0)
            int idPedido = Integer.parseInt(JTABLE_Mant_Pedido.getValueAt(filaSeleccionada, 0).toString());

            // 5. Llamar al método de tu DAO
            methods.desactivarPedido(idPedido);

            // 6. Mensaje de éxito y refrescar la tabla
            JOptionPane.showMessageDialog(this, "Pedido quitado correctamente.");
            
            // listarPedidos(); 
            limpiarTodo();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al desactivar en la BD: " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + ex.getMessage());
        }
    }

        
    }//GEN-LAST:event_BTN_DesactivarActionPerformed

    private void BTN_VerPlatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerPlatosActionPerformed
        this.MostrarPedidos();
        cargarComboBoxTipoPedido();
        this.BTN_VerPlatos.setEnabled(false);
        
        
        this.BTN_Nuevo.setEnabled(true);
        this.BTN_Guardar.setEnabled(false);
        this.BTN_Desactivar.setEnabled(false);
        this.BTN_Modificar.setEnabled(false);
        this.btnViewDetails.setEnabled(false);
        this.btn_searchCliente.setEnabled(false);
        this.btn_searchEmpleado.setEnabled(false);
        comboTipoPedido.setEnabled(false);
        
        
        String string_current = String.valueOf(current_date); 
        this.txtFechaPedido.setText(string_current);
        
        
        
        String fechaActual = LocalDate.now().format(formato);
        txtFechaPedido.setText(fechaActual);
    }//GEN-LAST:event_BTN_VerPlatosActionPerformed

    private void txtDNIClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDNIClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDNIClienteActionPerformed

    private void txtDNIEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDNIEmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDNIEmpleadoActionPerformed

    private void btn_searchEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchEmpleadoActionPerformed
        
        
        JD_Empleado buscador = new JD_Empleado(this, true);
    
        // 2. Lo muestras (El programa se detiene aquí hasta que cierres el buscador)
        buscador.setVisible(true);
    
    // 3. Cuando el código vuelve aquí, el buscador ya se cerró pero sus datos siguen en memoria
        if (buscador.seleccionado) {
        // 4. LLENAS TUS TEXTFIELDS
        txtDNIEmpleado.setText(buscador.dniProp);
        txtNombresEmpleado.setText(buscador.nombreProp);
        txtApellidosEmpleado.setText(buscador.apellidoProp);
    }
        
        
    }//GEN-LAST:event_btn_searchEmpleadoActionPerformed

    private void btn_searchClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchClienteActionPerformed
        
        
        JD_Cliente buscador = new JD_Cliente(this, true);
    
        // 2. Lo muestras (El programa se detiene aquí hasta que cierres el buscador)
        buscador.setVisible(true);
    
    // 3. Cuando el código vuelve aquí, el buscador ya se cerró pero sus datos siguen en memoria
        if (buscador.seleccionado) {
        // 4. LLENAS TUS TEXTFIELDS
        txtDNICliente.setText(buscador.dniProp);
        txtNombresCliente.setText(buscador.nombreProp);
        txtApellidosCliente.setText(buscador.apellidoProp);
    }
    }//GEN-LAST:event_btn_searchClienteActionPerformed

    private void btnViewDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewDetailsActionPerformed
        String id_pedido = txtIdPedido.getText();
        String fecha = txtFechaPedido.getText();
        
                int id_pedido_int;
        if (id_pedido.isEmpty()||id_pedido==null){
            id_pedido_int = -1;
        } else {
            id_pedido_int = Integer.parseInt(id_pedido);
        }
        dialogo = new JD_AgregarPlato(this, true,(id_pedido_int),fecha);
        // 1. Instanciamos el JDialog
    // 2. Obtenemos los modelos de ambas tablas
    DefaultTableModel modeloOrigen = (DefaultTableModel) Table_Details.getModel();
    DefaultTableModel modeloDestino = (DefaultTableModel) dialogo.modeloTabla;
    
    // 3. Limpiamos la tabla del Dialog antes de pasar los datos
    modeloDestino.setRowCount(0);
    
    // 4. Verificamos si hay datos para pasar
    if (modeloOrigen.getRowCount() > 0) {
        // Recorremos fila por fila
        for (int i = 0; i < modeloOrigen.getRowCount(); i++) {
            Object[] fila = new Object[modeloOrigen.getColumnCount()];
            
            // Recorremos cada columna de la fila actual
            for (int j = 0; j < modeloOrigen.getColumnCount(); j++) {
                fila[j] = modeloOrigen.getValueAt(i, j);
            }
            
            // Agregamos la fila clonada al modelo del JDialog
            modeloDestino.addRow(fila);
        }
    }
    
    // 5. Centramos y mostramos el Dialog
    dialogo.setLocationRelativeTo(this);
    
        dialogo.setVisible(true);
    }//GEN-LAST:event_btnViewDetailsActionPerformed

    private void txtFechaPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaPedidoActionPerformed

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
            java.util.logging.Logger.getLogger(Frm_Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Frm_Pedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Back;
    private javax.swing.JButton BTN_Cerrar1;
    private javax.swing.JButton BTN_Desactivar;
    private javax.swing.JButton BTN_Desactivar1;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_PDF;
    private javax.swing.JButton BTN_VerPlatos;
    public javax.swing.JTable JTABLE_Mant_Pedido;
    private javax.swing.JTextField TXT_BuscarMesas;
    public javax.swing.JTable Table_Details;
    private javax.swing.JButton btnViewDetails;
    private javax.swing.JButton btn_searchCliente;
    private javax.swing.JButton btn_searchEmpleado;
    private javax.swing.JComboBox<String> comboTipoPedido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelCliente;
    private javax.swing.JPanel jPanelEmpleado;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panel;
    private javax.swing.JTextField txtApellidosCliente;
    private javax.swing.JTextField txtApellidosEmpleado;
    private javax.swing.JTextField txtDNICliente;
    private javax.swing.JTextField txtDNIEmpleado;
    private javax.swing.JTextField txtFechaPedido;
    private javax.swing.JTextField txtIdPedido;
    private javax.swing.JTextField txtNombresCliente;
    private javax.swing.JTextField txtNombresEmpleado;
    // End of variables declaration//GEN-END:variables

//Método para mostrar las plato menues
    public void MostrarPedidos() {
        //Ordenar ASC, DESC
        JTABLE_Mant_Pedido.setAutoCreateRowSorter(true);
        //Limpiar la tabla antes de mostrar nuevos datos
        modeloTablaReserva.setRowCount(0);
        try {
            //Llama al método que retorna los datos de plato menues
            ResultSet rs = this.methods.listarPedido();
            while (rs.next()) {
                String cliente = rs.getString("Nombre del Cliente")+" "+rs.getString("Apellidos del Cliente");
                String empleado = rs.getString("Nombre del Empleado")+" "+rs.getString("Apellidos del Empleado");
                Object[] fila = {
                    rs.getInt("ID"),
                    rs.getString("DNI del Cliente"),
                    cliente,
                    rs.getString("DNI del Empleado"),
                    empleado,
                    rs.getString("Tipo de Pedido"),
                    rs.getString("Fecha del Pedido"),
                    rs.getInt("Cantidad de Platos"),
                    rs.getString("Nombre del Cliente"),
                    rs.getString("Apellidos del Cliente"),
                    rs.getString("Nombre del Empleado"),
                    rs.getString("Apellidos del Empleado")
                    
                };
                modeloTablaReserva.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los resultados:\n" + e.getMessage(),
                    "ErrorDeConsulta", JOptionPane.ERROR_MESSAGE);
        }
    }

//Método para mostrar las plato menues
    public void BuscarMesaPorNombre() {
        String parametro = TXT_BuscarMesas.getText().trim();
        if (parametro.isEmpty()) {
            parametro = "";
        }
        try {
            //Llama al método que retorna los datos de plato menues
            ResultSet rs = this.methods.buscarPedido(parametro);

            try (rs) {
                modeloTablaReserva.setRowCount(0);
            while (rs.next()) {
                String descripcion = rs.getString("Descripciones");
                if (descripcion==null||descripcion.isEmpty()){
                    descripcion = "Sin descripción";
                }

                Object[] fila = {
                    rs.getInt("ID"),
                    rs.getString("Nombre del Plato"),
                    rs.getString("Precio"),
                    rs.getString("Categoría"),
                    descripcion
                };
                modeloTablaReserva.addRow(fila);
            }
            }


        } catch (SQLException e) {
            System.err.println("Error silencioso en búsqueda: " + e.getMessage());
        }
    }


    
    
    
  private int ejecutarGuardado() {
    try {
        // ACTUALIZACIÓN: Antes de validar, buscamos los IDs reales por el DNI que está en pantalla
        String dniC = txtDNICliente.getText().trim();
        String dniE = txtDNIEmpleado.getText().trim();
        
        // Debug para ver qué está leyendo Java de las cajas de texto
        System.out.println("DNI Cliente capturado: [" + dniC + "]");
        System.out.println("DNI Empleado capturado: [" + dniE + "]");

        this.idClienteSeleccionado = methods.obtenerIdClientePorDNI(dniC);
        this.idEmpleadoSeleccionado = methods.obtenerIdEmpleadoPorDNI(dniE);
        // Ahora la validación ya no fallará si el DNI existe
        if (idClienteSeleccionado == -1 || idEmpleadoSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "DNI de Cliente o Empleado no válido o no encontrado.");
            return -1;
        }

        // Validar que la tabla de detalles (Table_Details) tenga platos
        DefaultTableModel modeloDetalle = (DefaultTableModel) Table_Details.getModel();
        if (modeloDetalle.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "El pedido no tiene platos agregados en la tabla de detalles.");
            return -1;
        }

        // Resto de tu código de guardado...
        String fechaSQL = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        
        
            int aceptar = JOptionPane.showConfirmDialog(null, "¿Desea guardar lo seleccionado?","Guardar",JOptionPane.INFORMATION_MESSAGE);
    if (aceptar==JOptionPane.YES_OPTION){
        
        if (methods.guardarPedidoCompleto(fechaSQL, idClienteSeleccionado, idEmpleadoSeleccionado, idTipoPedidoSeleccionado, modeloDetalle)) {
            JOptionPane.showMessageDialog(this, "¡Pedido registrado con éxito!");
            limpiarTodo();
            return 1;
        }
        
    }
        
 
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error de base de datos: " + e.getMessage());
    }
        return 0;
}
 
  
  
  
  
  
  
  
  
    private void limpiarTodo() {
    // 1. Limpiar campos de texto de Cabecera
    txtIdPedido.setText("");
    txtFechaPedido.setText(String.valueOf(current_date)); // O podrías poner la fecha actual: LocalDate.now().toString()
    
    // 2. Limpiar datos de Cliente
    txtDNICliente.setText("");
    txtNombresCliente.setText("");
    txtApellidosCliente.setText("");
    idClienteSeleccionado = -1; // Resetear el ID
    
    // 3. Limpiar datos de Empleado
    txtDNIEmpleado.setText("");
    txtNombresEmpleado.setText("");
    txtApellidosEmpleado.setText("");
    idEmpleadoSeleccionado = -1; // Resetear el ID
    
    // 4. Resetear el ComboBox del tipo de pedido
    if (comboTipoPedido.getItemCount() > 0) {
        comboTipoPedido.setSelectedIndex(0);
    }
    
    // 5. Limpiar la JTable (el detalle del pedido)
    //DefaultTableModel modeloDetalle = (DefaultTableModel) JTABLE_Mant_Pedido.getModel();
    //modeloDetalle.setRowCount(0);
    MostrarPedidos();
    
    // 6. Resetear estados de botones si es necesario
    JTABLE_Mant_Pedido.clearSelection();
    BTN_Guardar.setEnabled(false);
    BTN_Modificar.setEnabled(false);
    BTN_Desactivar.setEnabled(false);
    
}
    
    private void CreateDetailTable (){
        
        DefaultTableModel detailModel = new DefaultTableModel();
        String[] header = {"ID Plato","Plato","Cantidad","Precio","Subtotal","Observaciones"};
        detailModel.setColumnIdentifiers(header);
        
        this.Table_Details.setModel(detailModel);
        TableColumnModel col = Table_Details.getColumnModel();
        col.getColumn(0).setPreferredWidth(0);
        col.getColumn(0).setMinWidth(0);
        col.getColumn(0).setMaxWidth(0);
        col.getColumn(1).setWidth(80);
        col.getColumn(2).setWidth(40);
        col.getColumn(3).setWidth(40);
        col.getColumn(4).setWidth(40);
        col.getColumn(5).setWidth(160);
        
    }
    
    
    
    
    
    
    
}
