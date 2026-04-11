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
import java.time.LocalDateTime;
import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;

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
        String[] header = {"ID","DNI Cliente","Cliente","DNI Empleado","Empleado","Tipo de Pedido","Fecha de Pedido","Cantidad de Platos","Nombre C","Apellido C","Nombre E","Apellidos E","Detalles"};

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
    colModel.getColumn(7).setPreferredWidth(90);
    
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

    // 3. Extras de la Tabla
    JTABLE_Mant_Pedido.getTableHeader().setReorderingAllowed(false); // No mover columnas
    JTABLE_Mant_Pedido.setRowHeight(25); // Filas más altas para que respire el diseño


        //Desactivar button
        BTN_Nuevo.setEnabled(false);
        BTN_Desactivar.setEnabled(false);
        BTN_Guardar.setEnabled(false);
        BTN_Cancel.setVisible(false);
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
        BTN_Cancel = new javax.swing.JButton();
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
        jButton2 = new javax.swing.JButton();
        jPanelEmpleado = new javax.swing.JPanel();
        txtDNIEmpleado = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtApellidosEmpleado = new javax.swing.JTextField();
        txtNombresEmpleado = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        searchEmpleado = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        comboTipoPedido = new javax.swing.JComboBox<>();
        txtFechaPedido = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        btnViewDetails = new javax.swing.JButton();

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
        jPanel3.add(BTN_VerPlatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 30, 150, 50));

        BTN_Desactivar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Desactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_delete.png"))); // NOI18N
        BTN_Desactivar.setText("     QUITAR");
        BTN_Desactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_DesactivarActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Desactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 280, 150, 48));

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
        jPanel3.add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 94, 150, 48));

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

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 920, 220));

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

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 370, 920, 50));

        panel.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIdPedido.setEditable(false);
        txtIdPedido.setBackground(new java.awt.Color(255, 255, 255));
        txtIdPedido.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIdPedido.setForeground(new java.awt.Color(0, 0, 204));
        txtIdPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdPedido.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panel.add(txtIdPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 90, 20));

        jPanelCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Cliente Atendido   ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanelCliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDNICliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNIClienteActionPerformed(evt);
            }
        });
        jPanelCliente.add(txtDNICliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 140, 22));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Nombres:*");
        jPanelCliente.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 90, -1));
        jPanelCliente.add(txtApellidosCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 220, 22));
        jPanelCliente.add(txtNombresCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 220, 22));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Apellidos:*");
        jPanelCliente.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 90, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("DNI:*");
        jPanelCliente.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 90, -1));

        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanelCliente.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 80, -1));

        panel.add(jPanelCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, 280, 230));

        jPanelEmpleado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Empleado Encargado   ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanelEmpleado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtDNIEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDNIEmpleadoActionPerformed(evt);
            }
        });
        jPanelEmpleado.add(txtDNIEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 140, 22));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("DNI:*");
        jPanelEmpleado.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 90, -1));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("Nombres:*");
        jPanelEmpleado.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 90, -1));
        jPanelEmpleado.add(txtApellidosEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 220, 22));
        jPanelEmpleado.add(txtNombresEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 220, 22));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("Apellidos:*");
        jPanelEmpleado.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 90, -1));

        searchEmpleado.setText("Buscar");
        searchEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchEmpleadoActionPerformed(evt);
            }
        });
        jPanelEmpleado.add(searchEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 80, -1));

        panel.add(jPanelEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 280, 220));

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 36)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("→");
        panel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 168, 60, 60));

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel6.setText("ID:");
        panel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 30, -1));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Fecha de Pedido:");
        panel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 130, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Detalles del Pedido");
        panel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 120, -1));

        comboTipoPedido.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        panel.add(comboTipoPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 150, -1));

        txtFechaPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaPedidoActionPerformed(evt);
            }
        });
        panel.add(txtFechaPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 40, 140, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Tipo de Pedido:*");
        panel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 130, -1));

        btnViewDetails.setText("Añadir o Ver");
        btnViewDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewDetailsActionPerformed(evt);
            }
        });
        panel.add(btnViewDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 120, -1));

        jPanel3.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 700, 330));

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
       if (!JTABLE_Mant_Pedido.isEnabled()) {
            return;
        }

//String[] header = {"ID","DNI Cliente","Cliente","DNI Empleado","Empleado",
        // "Tipo de Pedido","Fecha de Pedido","Cantidad de Platos"};
        int selectRow = JTABLE_Mant_Pedido.getSelectedRow();
        if (selectRow >= 0) {
            String id = JTABLE_Mant_Pedido.getValueAt(selectRow, 0).toString().trim();
            String dni_cliente = JTABLE_Mant_Pedido.getValueAt(selectRow, 1).toString().trim();
            String dni_empleado = JTABLE_Mant_Pedido.getValueAt(selectRow, 3).toString().trim();
            String tipo_pedido = JTABLE_Mant_Pedido.getValueAt(selectRow, 5).toString().trim();
            String fecha_pedido = JTABLE_Mant_Pedido.getValueAt(selectRow, 6).toString().trim();
            String cantidad = JTABLE_Mant_Pedido.getValueAt(selectRow, 7).toString().trim();
            
            
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
        

        

        BTN_Guardar.setEnabled(false);
        BTN_VerPlatos.setEnabled(false);
        BTN_Modificar.setEnabled(true);
        BTN_Desactivar.setEnabled(true);

    }//GEN-LAST:event_JTABLE_Mant_PedidoMouseClicked
    }




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

    private void BTN_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CancelActionPerformed
     /*   int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea cancelar la operación?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {

            BTN_Guardar.setEnabled(false);
            BTN_Desactivar.setEnabled(false);
            BTN_Modificar.setEnabled(false);
            BTN_Nuevo.setVisible(true);
            BTN_Cancel.setVisible(false);

            txtNombrePlato.setEditable(false);
            txtPrecio.setEditable(false);
            jTextAreaObservaciones.setEditable(false);
            jComboBoxCategoria.setEnabled(false);

            JTABLE_Mant_Reserva.setEnabled(true);

        } */
    }//GEN-LAST:event_BTN_CancelActionPerformed

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

        BTN_Guardar.setEnabled(true);
        BTN_Desactivar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        BTN_Nuevo.setVisible(false);
        BTN_Cancel.setVisible(true);
        
        
        
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
 
        ejecutarGuardado();
        
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
        /*
        
        try {
            // 1. Obtener datos del formulario
            String codigoStr = txtcodigoplato.getText().trim();
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
                    this.MostrarPedidos();
                    this.limpiarCamposPlatoMenu();

                    txtNombrePlato.setEditable(false);
                    txtPrecio.setEditable(false);
                    jTextAreaObservaciones.setEditable(false);
                    jComboBoxCategoria.setEnabled(false);

                    BTN_Modificar.setEnabled(false);
                    BTN_Desactivar.setEnabled(false);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "El código o el precio no son válidos.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                // Captura el error de "Ya existe un plato con ese nombre" que lanza tu método
                JOptionPane.showMessageDialog(this, e.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al modificar: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            }
        
        */
    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void BTN_DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DesactivarActionPerformed
        
        /*    

        // 1. Validar que se haya seleccionado una plato menu
        String codStr = txtcodigoplato.getText().trim();
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
                this.MostrarPedidos();
                // Limpia los campos de texto
                txtcodigoplato.setText("");
                //txtprecio.setText("");
                BTN_Desactivar.setEnabled(false);
                BTN_Modificar.setEnabled(false);
            } catch (SQLException ex) {
                // 6. Captura cualquier error lanzado por el procedure (por SIGNAL)
                JOptionPane.showMessageDialog(this,"Error al desactivar plato menu:\n" + ex.getMessage(),"Error de base de datos",JOptionPane.ERROR_MESSAGE);
            }
        }
        */
        
    }//GEN-LAST:event_BTN_DesactivarActionPerformed

    private void BTN_VerPlatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerPlatosActionPerformed
        this.MostrarPedidos();
        cargarComboBoxTipoPedido();
        this.BTN_Nuevo.setEnabled(true);
        this.BTN_Guardar.setEnabled(false);
        this.BTN_Desactivar.setEnabled(false);
        this.BTN_Modificar.setEnabled(false);
        this.BTN_VerPlatos.setEnabled(false);
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

    private void searchEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchEmpleadoActionPerformed
        
        
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
        
        
    }//GEN-LAST:event_searchEmpleadoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        
        JD_Empleado buscador = new JD_Empleado(this, true);
    
        // 2. Lo muestras (El programa se detiene aquí hasta que cierres el buscador)
        buscador.setVisible(true);
    
    // 3. Cuando el código vuelve aquí, el buscador ya se cerró pero sus datos siguen en memoria
        if (buscador.seleccionado) {
        // 4. LLENAS TUS TEXTFIELDS
        txtDNICliente.setText(buscador.dniProp);
        txtNombresCliente.setText(buscador.nombreProp);
        txtApellidosCliente.setText(buscador.apellidoProp);
    }
    }//GEN-LAST:event_jButton2ActionPerformed

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
    private javax.swing.JButton BTN_Cancel;
    private javax.swing.JButton BTN_Cerrar1;
    private javax.swing.JButton BTN_Desactivar;
    private javax.swing.JButton BTN_Desactivar1;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_PDF;
    private javax.swing.JButton BTN_VerPlatos;
    private javax.swing.JTable JTABLE_Mant_Pedido;
    private javax.swing.JTextField TXT_BuscarMesas;
    private javax.swing.JButton btnViewDetails;
    private javax.swing.JComboBox<String> comboTipoPedido;
    private javax.swing.JButton jButton2;
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
    private javax.swing.JPanel panel;
    private javax.swing.JButton searchEmpleado;
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
                String cliente = rs.getString("Nombre del Cliente")+rs.getString("Apellidos del Cliente");
                String empleado = rs.getString("Nombre del Empleado")+rs.getString("Apellidos del Empleado");
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


    
    
    
    private void ejecutarGuardado() {
    // Validaciones de IDs
    if (idClienteSeleccionado == -1 || idEmpleadoSeleccionado == -1) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar Cliente y Empleado primero.");
        return;
    }

    // Validar tabla no vacía
    DefaultTableModel modelo = (DefaultTableModel) JTABLE_Mant_Pedido.getModel();
    if (modelo.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "El pedido no tiene platos agregados.");
        return;
    }

    // Fecha (usando el formato de tu Procedure TIMESTAMP)
    String fechaSQL = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    PedidoMethod dao = new PedidoMethod();
    if (dao.guardarPedidoCompleto(fechaSQL, idClienteSeleccionado, idEmpleadoSeleccionado, idTipoPedidoSeleccionado, modelo)) {
        JOptionPane.showMessageDialog(this, "¡Pedido registrado con éxito!");
        limpiarTodo();
    }
}
    
    private void limpiarTodo() {
    // 1. Limpiar campos de texto de Cabecera
    txtIdPedido.setText("");
    txtFechaPedido.setText(""); // O podrías poner la fecha actual: LocalDate.now().toString()
    
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
    DefaultTableModel modeloDetalle = (DefaultTableModel) JTABLE_Mant_Pedido.getModel();
    modeloDetalle.setRowCount(0);
    
    // 6. Resetear estados de botones si es necesario
    BTN_Guardar.setEnabled(true);
    BTN_Modificar.setEnabled(false);
    BTN_Desactivar.setEnabled(false);
    
    // Foco inicial
    txtDNICliente.requestFocus();
}
    
    
}
