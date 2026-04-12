package gui.crudMantenimiento;

import com.formdev.flatlaf.FlatLightLaf;
import java.sql.ResultSet;
import connection.ConnectionDB;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//Metodo de producto
import logic.dao.EmpleadoMethod;
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
import java.awt.HeadlessException;
import java.text.ParseException;
//Date
import java.text.SimpleDateFormat;
import java.util.Date;

public class Frm_Empleado extends javax.swing.JFrame {

    //Modelo para mostrar datos en ta tabla
    DefaultTableModel modeloTablaEmpleado = new DefaultTableModel();
    
    //Objeto conexión a la base de datos
    EmpleadoMethod EM = new EmpleadoMethod();
    ConnectionDB CBD = new ConnectionDB();

    //VAriable para comprobar cambios en mdoificar
    private String dniOriginal;
    private String nombreOriginal;
    private String apellidoOriginal;
    private String fechanacimientoOriginal;
    private String fecharegistroOriginal;
    private String direccionOriginal;
    private String correo1Original;
    private String correo2Original;
    private String telefono1Original;
    private String telefono2Original;
    private String ObservacionOriginal;
    private String generoOriginal = "";
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Frm_Empleado.class.getName());

    
    public Frm_Empleado() {
        FlatLightLaf.setup();
        initComponents();
        //titulo
        this.setTitle("Mantenimiento de Empleados");
        
        //Posicion
        this.setLocationRelativeTo(null);
       
        //Metodo para cargar el combobox
        cargarGenero();
        
        //definir los encabezados de la tabla
        String titulos[] = {"ID", "DNI", "Nombres", "Apellidos", "F. de Nac", "F. de Regitro", "Dirección", 
        "Correo Principal", "Correo Secundario", "Telefono Principal", "Telefono Secundario", "Genero", "Observaciones", "Estado"};
        
        // Asignar los titulos al modelo
        modeloTablaEmpleado.setColumnIdentifiers(titulos);
        
        // Establecer el modelo de la JTable
        JTABLE_Mant_Empleado.setModel(modeloTablaEmpleado);
        
        //ocultar columnas sesibles o internas: solo visual, noafecta al modelo, ocultar columna "id_empeado, 
        // correo2, telefono2, observacion y estado"
        JTABLE_Mant_Empleado.getColumnModel().getColumn(0).setMinWidth(0);
        JTABLE_Mant_Empleado.getColumnModel().getColumn(0).setMaxWidth(0);
        JTABLE_Mant_Empleado.getColumnModel().getColumn(0).setWidth(0);
        
        
        JTABLE_Mant_Empleado.getColumnModel().getColumn(8).setMinWidth(0);
        JTABLE_Mant_Empleado.getColumnModel().getColumn(8).setMaxWidth(0);
        JTABLE_Mant_Empleado.getColumnModel().getColumn(8).setWidth(0);
        
        JTABLE_Mant_Empleado.getColumnModel().getColumn(10).setMinWidth(0);
        JTABLE_Mant_Empleado.getColumnModel().getColumn(10).setMaxWidth(0);
        JTABLE_Mant_Empleado.getColumnModel().getColumn(10).setWidth(0);
        
        JTABLE_Mant_Empleado.getColumnModel().getColumn(12).setMinWidth(0);
        JTABLE_Mant_Empleado.getColumnModel().getColumn(12).setMaxWidth(0);
        JTABLE_Mant_Empleado.getColumnModel().getColumn(12).setWidth(0); 

        //Desabilitar campo de codigo (solo se mostrara no se escribe)
        txtcodigoempleado.setEnabled(false);
        BTN_Nuevo.setEnabled(false);
        BTN_Guardar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtcodigoempleado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox_genero = new javax.swing.JComboBox<>();
        txtNombreEmpleado = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txttelefono2 = new javax.swing.JTextField();
        txtApellidoEmpleado = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtfecharegistro = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtcorreo2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtdniempleado = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtcorreo1 = new javax.swing.JTextField();
        txtobservaciones = new javax.swing.JTextField();
        txttelefono1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        BTN_EXCEL = new javax.swing.JButton();
        BTN_Cerrar1 = new javax.swing.JButton();
        BTN_PDF = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        BTN_ListarEmpleados = new javax.swing.JButton();
        BTN_Desactivar = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Nuevo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_Empleado = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXT_BuscarEmpleados = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jCheckBoxListarInactivos = new javax.swing.JCheckBox();
        jCheckBoxActivar = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MANTENIMIENTO DE EMPLEADO");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 830, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel2.setText("DNI DEL EMPLEADO");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 110, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Fecha de nacimiento");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 140, -1));

        txtcodigoempleado.setEditable(false);
        txtcodigoempleado.setBackground(new java.awt.Color(255, 255, 255));
        txtcodigoempleado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcodigoempleado.setForeground(new java.awt.Color(0, 0, 204));
        txtcodigoempleado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcodigoempleado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtcodigoempleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 120, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("CODIGO");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 140, -1));

        jComboBox_genero.addActionListener(this::jComboBox_generoActionPerformed);
        jComboBox_genero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox_generoKeyReleased(evt);
            }
        });
        jPanel1.add(jComboBox_genero, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 250, 120, 30));

        txtNombreEmpleado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNombreEmpleado.setForeground(new java.awt.Color(0, 0, 204));
        txtNombreEmpleado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreEmpleado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtNombreEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreEmpleadoKeyTyped(evt);
            }
        });
        jPanel1.add(txtNombreEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 190, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Apellido del Empleado");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 140, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Genero");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 230, 100, -1));

        txttelefono2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txttelefono2.setForeground(new java.awt.Color(0, 0, 204));
        txttelefono2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttelefono2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txttelefono2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefono2KeyTyped(evt);
            }
        });
        jPanel1.add(txttelefono2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 220, 30));

        txtApellidoEmpleado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtApellidoEmpleado.setForeground(new java.awt.Color(0, 0, 204));
        txtApellidoEmpleado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtApellidoEmpleado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtApellidoEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoEmpleadoKeyTyped(evt);
            }
        });
        jPanel1.add(txtApellidoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 40, 140, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Fecha de Registro");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 130, -1));

        txtfecharegistro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtfecharegistro.setForeground(new java.awt.Color(0, 0, 204));
        txtfecharegistro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtfecharegistro.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtfecharegistro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfecharegistroKeyTyped(evt);
            }
        });
        jPanel1.add(txtfecharegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 120, 140, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("obervaciones");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 150, 100, -1));

        txtdireccion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtdireccion.setForeground(new java.awt.Color(0, 0, 204));
        txtdireccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtdireccion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdireccionKeyTyped(evt);
            }
        });
        jPanel1.add(txtdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 190, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Correo2");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 100, -1));

        txtcorreo2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcorreo2.setForeground(new java.awt.Color(0, 0, 204));
        txtcorreo2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcorreo2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtcorreo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcorreo2KeyTyped(evt);
            }
        });
        jPanel1.add(txtcorreo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 220, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Telefono2");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 230, 100, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Nombre del Empleado");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 140, -1));

        txtdniempleado.setBackground(new java.awt.Color(255, 255, 255));
        txtdniempleado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtdniempleado.setForeground(new java.awt.Color(0, 0, 204));
        txtdniempleado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtdniempleado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtdniempleado.addActionListener(this::txtdniempleadoActionPerformed);
        jPanel1.add(txtdniempleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 120, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setText("Correo");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 100, -1));

        txtcorreo1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcorreo1.setForeground(new java.awt.Color(0, 0, 204));
        txtcorreo1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcorreo1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtcorreo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcorreo1KeyTyped(evt);
            }
        });
        jPanel1.add(txtcorreo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 220, 30));

        txtobservaciones.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtobservaciones.setForeground(new java.awt.Color(0, 0, 204));
        txtobservaciones.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtobservaciones.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtobservaciones.addActionListener(this::txtobservacionesActionPerformed);
        txtobservaciones.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtobservacionesKeyTyped(evt);
            }
        });
        jPanel1.add(txtobservaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 180, 170, 50));

        txttelefono1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txttelefono1.setForeground(new java.awt.Color(0, 0, 204));
        txttelefono1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttelefono1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txttelefono1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefono1KeyTyped(evt);
            }
        });
        jPanel1.add(txttelefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 220, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Direccion");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 100, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Telefono");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 100, -1));
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 122, 170, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 760, 350));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setText("     Exportar XLSX");
        BTN_EXCEL.addActionListener(this::BTN_EXCELActionPerformed);
        getContentPane().add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 750, 170, 50));

        BTN_Cerrar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar1.setText("     Cerrar");
        BTN_Cerrar1.addActionListener(this::BTN_Cerrar1ActionPerformed);
        getContentPane().add(BTN_Cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 750, 165, 50));

        BTN_PDF.setText("     Exportar PDF");
        BTN_PDF.addActionListener(this::BTN_PDFActionPerformed);
        getContentPane().add(BTN_PDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 750, 170, 50));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BTN_ListarEmpleados.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_ListarEmpleados.setText("LISTAR EMPLEADOS");
        BTN_ListarEmpleados.addActionListener(this::BTN_ListarEmpleadosActionPerformed);
        jPanel3.add(BTN_ListarEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 110, 165, 50));

        BTN_Desactivar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Desactivar.setText("DESACTIVAR");
        BTN_Desactivar.addActionListener(this::BTN_DesactivarActionPerformed);
        jPanel3.add(BTN_Desactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 420, 165, 48));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setText("    MODIFICAR");
        BTN_Modificar.addActionListener(this::BTN_ModificarActionPerformed);
        jPanel3.add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 420, 165, 48));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setText("     GUARDAR");
        BTN_Guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_GuardarMouseClicked(evt);
            }
        });
        BTN_Guardar.addActionListener(this::BTN_GuardarActionPerformed);
        jPanel3.add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 420, 165, 48));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setText("      NUEVO");
        BTN_Nuevo.addActionListener(this::BTN_NuevoActionPerformed);
        jPanel3.add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 165, 48));

        JTABLE_Mant_Empleado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_Empleado.setForeground(new java.awt.Color(0, 0, 204));
        JTABLE_Mant_Empleado.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Mant_Empleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_EmpleadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Mant_Empleado);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 530, 1020, 220));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingresar el Nombre de Empleado");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, 30));

        TXT_BuscarEmpleados.addActionListener(this::TXT_BuscarEmpleadosActionPerformed);
        TXT_BuscarEmpleados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BuscarEmpleadosKeyReleased(evt);
            }
        });
        jPanel2.add(TXT_BuscarEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 13, 290, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("BUSCAR");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 120, 30));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 1010, 50));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCheckBoxListarInactivos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBoxListarInactivos.setText("Listar Empleados desactivados");
        jCheckBoxListarInactivos.addActionListener(this::jCheckBoxListarInactivosActionPerformed);
        jPanel4.add(jCheckBoxListarInactivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 190, 30));

        jCheckBoxActivar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBoxActivar.setText("Reactivar Empleado");
        jCheckBoxActivar.addActionListener(this::jCheckBoxActivarActionPerformed);
        jPanel4.add(jCheckBoxActivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 140, -1));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 190, 210, 120));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1010, 820));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreEmpleadoKeyTyped
 
    }//GEN-LAST:event_txtNombreEmpleadoKeyTyped

    private void BTN_EXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EXCELActionPerformed
       try {
            // 1. Crear un nuevo libro de Excel (.xlsx)
            XSSFWorkbook workbook = new XSSFWorkbook();

            // 2. Crear una hoja dentro del libro
            XSSFSheet sheet = workbook.createSheet("Empleados");

            // 3. Escribir la fila de encabezados desde el JTable
            XSSFRow headerRow = sheet.createRow(0); // Fila 0 para encabezados
            for (int i = 0; i < JTABLE_Mant_Empleado.getColumnCount(); i++) {
                headerRow.createCell(i).setCellValue(JTABLE_Mant_Empleado.getColumnName(i));
            }

            // 4. Escribir los datos fila por fila desde el JTable
            for (int i = 0; i < JTABLE_Mant_Empleado.getRowCount(); i++) {
                XSSFRow dataRow = sheet.createRow(i + 1); // Fila 1 en adelante
                for (int j = 0; j < JTABLE_Mant_Empleado.getColumnCount(); j++) {
                    Object valor = JTABLE_Mant_Empleado.getValueAt(i, j);
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

    private void TXT_BuscarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_BuscarEmpleadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_BuscarEmpleadosActionPerformed

    private void TXT_BuscarEmpleadosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarEmpleadosKeyReleased
        this.buscarEmpleadoPorNombre();
    }//GEN-LAST:event_TXT_BuscarEmpleadosKeyReleased

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
                Paragraph titulo = new Paragraph("LISTADO DE Empleados",
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLUE));
                titulo.setAlignment(Element.ALIGN_CENTER);
                document.add(titulo);
                document.add(new Paragraph(" ")); // espacio

                // TABLA DE DATOS
                PdfPTable pdfTable = new PdfPTable(JTABLE_Mant_Empleado.getColumnCount());
                pdfTable.setWidthPercentage(100);

                // Encabezados
                for (int i = 0; i < JTABLE_Mant_Empleado.getColumnCount(); i++) {
                    PdfPCell cell = new PdfPCell(new Phrase(JTABLE_Mant_Empleado.getColumnName(i)));
                    cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    pdfTable.addCell(cell);
                }

                // Filas de datos
                for (int row = 0; row < JTABLE_Mant_Empleado.getRowCount(); row++) {
                    for (int col = 0; col < JTABLE_Mant_Empleado.getColumnCount(); col++) {
                        Object value = JTABLE_Mant_Empleado.getValueAt(row, col);
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

    private void txttelefono2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefono2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelefono2KeyTyped

    private void txtApellidoEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoEmpleadoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoEmpleadoKeyTyped

    private void txtfecharegistroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfecharegistroKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfecharegistroKeyTyped

    private void txtdireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdireccionKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdireccionKeyTyped

    private void txtcorreo2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcorreo2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcorreo2KeyTyped

    private void jComboBox_generoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_generoActionPerformed

    }//GEN-LAST:event_jComboBox_generoActionPerformed

    private void txtcorreo1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcorreo1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcorreo1KeyTyped

    private void txtobservacionesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtobservacionesKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtobservacionesKeyTyped

    private void txttelefono1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefono1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelefono1KeyTyped

    private void jCheckBoxListarInactivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxListarInactivosActionPerformed
        this.listarEmpleadosInactivos();
        this.BTN_ListarEmpleados.setEnabled(true);
    }//GEN-LAST:event_jCheckBoxListarInactivosActionPerformed

    private void jCheckBoxActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxActivarActionPerformed
        // 1. Obtener fila seleccionada de la tabla de empleados
    int filaSeleccionada = JTABLE_Mant_Empleado.getSelectedRow();

    // 2. Validar selección inicial
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione un empleado de la tabla para reactivarlo.", "Aviso", JOptionPane.WARNING_MESSAGE);
        if (jCheckBoxActivar.isSelected()) {
            jCheckBoxActivar.setSelected(false);
        }
        return;
    }

    // 3. Obtener datos de la fila de forma segura
    // Asumiendo Columna 0 = ID, Columna 1 = Nombres, Columna 2 = Apellidos, Columna 13 = Estado
    int idEmpleado = Integer.parseInt(JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 0).toString());
    String nombreEmp = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 1).toString().trim();
    String apellidoEmp = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 2).toString().trim();
    String nombreCompleto = nombreEmp + " " + apellidoEmp;
    String estadoActual = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 13).toString().trim();

    // 4. Validar si ya está activo (evitar llamadas innecesarias)
    if (estadoActual.equalsIgnoreCase("Activo")) {
        JOptionPane.showMessageDialog(this,
            "El empleado '" + nombreCompleto + "' ya se encuentra activo.",
            "Información", JOptionPane.INFORMATION_MESSAGE);
        jCheckBoxActivar.setSelected(false);
        return;
    }

    // 5. Confirmación profesional
    int confirmacion = JOptionPane.showConfirmDialog(this,
        "¿Desea reactivar al empleado: " + nombreCompleto + "?\n"
        + "Esto permitirá que vuelva a figurar en las planillas y contratos.",
        "Confirmar Reactivación",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE);

    if (confirmacion == JOptionPane.YES_OPTION) {
        try {
            // 6. Ejecutar la reactivación usando tu clase de lógica (ejemplo: emDAO)
            // Cambia 'emDAO' por el nombre de tu objeto que contiene reactivarEmpleado
            EM.reactivarEmpleado(idEmpleado);
            JOptionPane.showMessageDialog(this,
                    "¡Éxito! El empleado '" + nombreCompleto + "' ha sido reactivado.",
                    "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
            // 7. Sincronizar Interfaz
            if (jCheckBoxListarInactivos != null) {
                jCheckBoxListarInactivos.setSelected(false);
            }
            jCheckBoxActivar.setSelected(false);
            // Refrescar tabla y limpiar formulario
            listarEmpleados(); // Tu método para cargar la JTable
            limpiarCamposEmpleado(); // Tu método para vaciar los JTextFields
        } catch (SQLException ex) {
            System.getLogger(Frm_Empleado.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    } else {
        // Si cancela, desmarcamos el checkbox
        jCheckBoxActivar.setSelected(false);
    }
    }//GEN-LAST:event_jCheckBoxActivarActionPerformed

    private void JTABLE_Mant_EmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_EmpleadoMouseClicked
    int filaSeleccionada = JTABLE_Mant_Empleado.getSelectedRow();

// 1. Configuración de botones generales
BTN_Modificar.setEnabled(filaSeleccionada != -1); 
BTN_Guardar.setEnabled(false);
BTN_Nuevo.setVisible(true);
BTN_Nuevo.setEnabled(true);

// 2. Lógica de habilitación para desactivar / activar
if (jCheckBoxListarInactivos.isSelected()) {
    BTN_Desactivar.setEnabled(false);
    jCheckBoxActivar.setEnabled(filaSeleccionada != -1);
} else {
    BTN_Desactivar.setEnabled(filaSeleccionada != -1);
    jCheckBoxActivar.setEnabled(false);
}

// 3. Extracción de datos si hay una fila seleccionada
if (filaSeleccionada != -1) {
    try {
        // --- ASIGNACIÓN SEGÚN LOS ÍNDICES DE TU VISTA SQL ---
        String id        = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 0).toString().trim(); // ID
        String dni       = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 1).toString().trim(); // DNI
        String nombres   = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 2).toString().trim(); // Nombre
        String apellidos = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 3).toString().trim(); // Apellido
        
        // Columna 4: Fecha de Nacimiento
        Object fechaNacObj = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 4);
        if (fechaNacObj instanceof java.util.Date) {
            jDateChooser1.setDate((java.util.Date) fechaNacObj);
        } else if (fechaNacObj != null) {
            java.util.Date fecha = new java.text.SimpleDateFormat("yyyy-MM-dd").parse(fechaNacObj.toString());
            jDateChooser1.setDate(fecha);
        }

        // Columna 5: Fecha de Registro (Solo para variable original)
        Object fechaRegObj = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 5);
        fecharegistroOriginal = (fechaRegObj != null) ? fechaRegObj.toString() : "";

        // Columna 6 en adelante
        String residencia = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 6).toString().trim();
        String correo1    = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 7).toString().trim();
        
        Object c2         = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 8);
        String correo2    = (c2 != null) ? c2.toString().trim() : "";
        
        String telf1      = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 9).toString().trim();
        
        Object t2         = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 10);
        String telf2      = (t2 != null) ? t2.toString().trim() : "";

        // AQUÍ EL CAMBIO CLAVE:
        // Columna 11: Genero (M/F)
        String genero     = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 11).toString().trim();
        
        // Columna 12: Observaciones
        Object obs        = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 12);
        String observacion = (obs != null) ? obs.toString().trim() : "";

        // 4. Mostrar en los controles (TextFields)
        txtcodigoempleado.setText(id);
        txtdniempleado.setText(dni);
        txtNombreEmpleado.setText(nombres);
        txtApellidoEmpleado.setText(apellidos);
        txtdireccion.setText(residencia);
        txtcorreo1.setText(correo1);
        txtcorreo2.setText(correo2);
        txttelefono1.setText(telf1);
        txttelefono2.setText(telf2);
        txtobservaciones.setText(observacion); // Ahora sí mostrará la observación real

        // 5. Guardar valores originales
        dniOriginal = dni;
        nombreOriginal = nombres;
        apellidoOriginal = apellidos;
        fechanacimientoOriginal = (fechaNacObj != null) ? fechaNacObj.toString() : "";
        direccionOriginal = residencia;
        correo1Original = correo1;
        correo2Original = correo2;
        telefono1Original = telf1;
        telefono2Original = telf2;
        ObservacionOriginal = observacion;
        generoOriginal = genero;

        // 6. Sincronizar JComboBox de Género
        boolean encontrado = false;
        for (int i = 0; i < jComboBox_genero.getItemCount(); i++) {
            // Comparamos contra la letra (M/F) o el nombre completo según tengas el combo
            String item = jComboBox_genero.getItemAt(i).trim();
            if (item.equalsIgnoreCase(genero) || item.startsWith(genero)) {
                jComboBox_genero.setSelectedIndex(i);
                encontrado = true;
                break;
            }
        }

        if (!encontrado && !genero.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El género '" + genero + "' no coincide con el catálogo.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }

    } catch (HeadlessException | ParseException e) {
        System.err.println("Error al cargar datos de la tabla: " + e.getMessage());
        e.printStackTrace();
    }
}
    }//GEN-LAST:event_JTABLE_Mant_EmpleadoMouseClicked

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
        limpiarCamposEmpleado();
        txtdniempleado.setEditable(true);
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
        // 1. Capturar y limpiar datos de los campos de texto
    String dni = txtdniempleado.getText().trim();
    String nombres = txtNombreEmpleado.getText().trim();
    String apellidos = txtApellidoEmpleado.getText().trim();
    String residencia = txtdireccion.getText().trim();
    String correo1 = txtcorreo1.getText().trim();
    String correo2 = txtcorreo2.getText().trim();
    String telf1 = txttelefono1.getText().trim();
    String telf2 = txttelefono2.getText().trim();
    String observacion = txtobservaciones.getText().trim();
    String generoNombre = (String) jComboBox_genero.getSelectedItem();

    // --- 2. VALIDACIONES DE CAMPOS OBLIGATORIOS ---
    if (campoVacio(txtdniempleado, "DNI") || campoVacio(txtNombreEmpleado, "Nombres") || 
        campoVacio(txtApellidoEmpleado, "Apellidos") || campoVacio(txtdireccion, "Dirección")) {
        return;
    }

    if (generoNombre == null || generoNombre.equals("Seleccione...")) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar un género.", "Validación", JOptionPane.WARNING_MESSAGE);
        jComboBox_genero.requestFocus();
        return;
    }

    // --- 3. VALIDACIONES DE FORMATO (RegEx) ---
    // DNI: 8 dígitos
    if (!dni.matches("^[0-9]{8}$")) {
        JOptionPane.showMessageDialog(this, "El DNI debe tener exactamente 8 números.", "Validación", JOptionPane.WARNING_MESSAGE);
        txtdniempleado.requestFocus();
        return;
    }

    // Correo Principal
    if (campoVacio(txtcorreo1, "Correo Principal") || 
        !correo1.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
        JOptionPane.showMessageDialog(this, "Ingrese un correo principal válido.", "Validación", JOptionPane.WARNING_MESSAGE);
        txtcorreo1.requestFocus();
        return;
    }

    // Teléfono Principal (7 a 15 dígitos)
    if (campoVacio(txttelefono1, "Teléfono Principal") || !telf1.matches("^[0-9]{7,15}$")) {
        JOptionPane.showMessageDialog(this, "El teléfono debe contener entre 7 y 15 números.", "Validación", JOptionPane.WARNING_MESSAGE);
        txttelefono1.requestFocus();
        return;
    }

    // --- 4. VALIDACIÓN DE FECHA (Manejo de java.sql.Date y Mayoría de edad) ---
    if (jDateChooser1.getDate() == null) {
        JOptionPane.showMessageDialog(this, "Debe seleccionar una fecha de nacimiento.", "Validación", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Convertimos la fecha del JDateChooser al tipo que espera tu DAO (java.sql.Date)
    java.sql.Date fechaNacSql = new java.sql.Date(jDateChooser1.getDate().getTime());
    
    // Cálculo de edad usando la API de Java 8+
    long edad = java.time.temporal.ChronoUnit.YEARS.between(
            fechaNacSql.toLocalDate(), java.time.LocalDate.now());

    if (edad < 18) {
        JOptionPane.showMessageDialog(this, "El empleado debe ser mayor de edad (actualmente tiene " + edad + " años).", "Validación", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // --- 5. CONFIRMACIÓN Y REGISTRO ---
    int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea registrar este nuevo empleado?", "Confirmación", JOptionPane.YES_NO_OPTION);
    
    if (respuesta == JOptionPane.YES_OPTION) {
        try {
            // Obtener el ID del género según el nombre seleccionado desde tu DAO
            int idGenero = EM.obtenerGenero(generoNombre);

            // Llamada al método del DAO
            // Seteamos null en fechaRegistro para que el Procedimiento use su valor por defecto (CURRENT_TIMESTAMP)
            this.EM.insertarEmpleado(dni, nombres, apellidos, fechaNacSql, null, residencia, 
                                    correo1, correo2, telf1, telf2, observacion, idGenero);

            JOptionPane.showMessageDialog(this, "¡Empleado registrado correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // 6. Actualizar tabla y limpiar c
                this.listarEmpleados();
                this.limpiarCamposEmpleado();

        } catch (SQLException ex) {
            // Manejo de errores personalizados según los SIGNAL de tu Base de Datos
            int errorCode = ex.getErrorCode();

            switch (errorCode) {
                case 1062 -> JOptionPane.showMessageDialog(this, "Error: El DNI o Correo ya existen en la base de datos.", "Duplicado", JOptionPane.ERROR_MESSAGE);
                case 4002 -> JOptionPane.showMessageDialog(this, "Error: El DNI ya está registrado en un empleado activo.", "Duplicado", JOptionPane.ERROR_MESSAGE);
                case 4020 -> JOptionPane.showMessageDialog(this, "Aviso: Este DNI pertenece a un empleado INACTIVO.\nDebe reactivarlo desde la pestaña de desactivados.", "Empleado Inactivo", JOptionPane.WARNING_MESSAGE);
                case 4009 -> JOptionPane.showMessageDialog(this, "Error: El correo principal y secundario no pueden ser iguales.", "Error Datos", JOptionPane.ERROR_MESSAGE);
                case 4012 -> JOptionPane.showMessageDialog(this, "Error: Los teléfonos no pueden ser iguales.", "Error Datos", JOptionPane.ERROR_MESSAGE);
                default -> JOptionPane.showMessageDialog(this, "Error de base de datos (" + errorCode + "):\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
        try {
        // 1. Capturar datos y limpiar espacios
        String idStr = txtcodigoempleado.getText().trim();
        String nuevoDni = txtdniempleado.getText().trim();
        String nuevosNombres = txtNombreEmpleado.getText().trim();
        String nuevosApellidos = txtApellidoEmpleado.getText().trim();
        String nuevaResidencia = txtdireccion.getText().trim();
        String nuevoCorreo1 = txtcorreo1.getText().trim();
        String nuevoCorreo2 = txtcorreo2.getText().trim();
        String nuevoTelf1 = txttelefono1.getText().trim();
        String nuevoTelf2 = txttelefono2.getText().trim();
        String nuevaObs = txtobservaciones.getText().trim();
        String generoNombre = (String) jComboBox_genero.getSelectedItem();

        // --- VALIDACIONES DE CAMPOS VACÍOS ---
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un empleado de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (campoVacio(txtdniempleado, "DNI") || 
            campoVacio(txtNombreEmpleado, "Nombres") || 
            campoVacio(txtApellidoEmpleado, "Apellidos")) return;

        if (generoNombre == null || generoNombre.equals("Seleccione...")) {
            JOptionPane.showMessageDialog(this, "Seleccione un género válido.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // --- VALIDACIÓN DE CAMBIOS ---
        if (nuevoDni.equals(dniOriginal) &&
            nuevosNombres.equalsIgnoreCase(nombreOriginal) &&
            nuevosApellidos.equalsIgnoreCase(apellidoOriginal) &&
            nuevoCorreo1.equalsIgnoreCase(correo1Original) &&
            nuevoCorreo2.equalsIgnoreCase(correo2Original) &&
            nuevoTelf1.equals(telefono1Original) &&
            nuevoTelf2.equals(telefono2Original) &&
            nuevaObs.equals(ObservacionOriginal) &&
            generoNombre.equalsIgnoreCase(generoOriginal)) {

            JOptionPane.showMessageDialog(this, "No se detectaron cambios en los datos del empleado.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // --- VALIDACIONES DE FORMATO ---
        if (!nuevoDni.matches("^[0-9]{8}$")) {
            JOptionPane.showMessageDialog(this, "DNI inválido (debe tener 8 dígitos).", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (jDateChooser1.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Seleccione la fecha de nacimiento.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. Confirmación
        int respuesta = JOptionPane.showConfirmDialog(this, 
            "¿Desea actualizar los datos de: " + nuevosNombres + " " + nuevosApellidos + "?", 
            "Confirmación", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                int idEmpleado = Integer.parseInt(idStr);
                int idGenero = EM.obtenerGenero(generoNombre);

                // ✅ Fecha correcta
                java.sql.Date fNac = new java.sql.Date(jDateChooser1.getDate().getTime());

                // 🚫 Fecha registro NO se modifica → se manda null
                this.EM.modificarEmpleado(
                    idEmpleado, 
                    nuevoDni, 
                    nuevosNombres, 
                    nuevosApellidos, 
                    fNac, 
                    null, 
                    nuevaResidencia, 
                    nuevoCorreo1, 
                    nuevoCorreo2, 
                    nuevoTelf1, 
                    nuevoTelf2, 
                    nuevaObs, 
                    idGenero
                );

                JOptionPane.showMessageDialog(this, "Empleado actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);

                // 3. Refrescar
                this.listarEmpleados();
                this.limpiarCamposEmpleado();

            } catch (SQLException ex) {
                int errorCode = ex.getErrorCode();

                switch (errorCode) {
                    case 20201 -> JOptionPane.showMessageDialog(this, "Error: El empleado ya no existe.", "Error ID", JOptionPane.ERROR_MESSAGE);
                    case 20215 -> JOptionPane.showMessageDialog(this, "Error: No se puede editar un empleado INACTIVO.", "Error Estado", JOptionPane.WARNING_MESSAGE);
                    case 20203, 1062 -> JOptionPane.showMessageDialog(this, "Error: El DNI ya está registrado en otro empleado.", "DNI Duplicado", JOptionPane.ERROR_MESSAGE);
                    case 5006 -> JOptionPane.showMessageDialog(this, "Error: El empleado debe ser mayor de edad.", "Validación", JOptionPane.ERROR_MESSAGE);
                    case 20209 -> JOptionPane.showMessageDialog(this, "Error: Los correos no pueden ser iguales.", "Validación", JOptionPane.ERROR_MESSAGE);
                    case 20212 -> JOptionPane.showMessageDialog(this, "Error: Los teléfonos no pueden ser iguales.", "Validación", JOptionPane.ERROR_MESSAGE);
                    default -> JOptionPane.showMessageDialog(this, "Error (" + errorCode + "):\n" + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
                }
            }
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error crítico: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void BTN_DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DesactivarActionPerformed
        // 1. Obtener la fila seleccionada de la tabla de empleados
    int filaSeleccionada = JTABLE_Mant_Empleado.getSelectedRow(); 

    // Validación inicial: ¿Seleccionó algo?
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione un empleado de la tabla para desactivarlo.", "Aviso", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 2. Obtener datos de la fila de forma segura
    // Columna 0: ID, Columna 1: Nombre, Columna 2: Apellido, Columna 13: Estado
    int idEmpleado = Integer.parseInt(JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 0).toString());
    String nombreCompleto = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 1).toString().trim() + " " +
                           JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 2).toString().trim();
    
    // El estado en tu vista de empleados suele estar en la columna 13
    String estadoActual = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 13).toString().trim();

    // 3. Validar si ya está desactivado para no trabajar en vano
    if (estadoActual.equalsIgnoreCase("Inactivo")) {
        JOptionPane.showMessageDialog(this, "El empleado '" + nombreCompleto + "' ya se encuentra desactivado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        return;
    }

    // 4. Confirmación profesional
    int confirmacion = JOptionPane.showConfirmDialog(this,
        "¿Está seguro de que desea desactivar al empleado: " + nombreCompleto + "?\n"
        + "Esto lo inhabilitará para nuevas operaciones pero mantendrá su historial en la base de datos.",
        "Confirmar Desactivación", 
        JOptionPane.YES_NO_OPTION, 
        JOptionPane.QUESTION_MESSAGE);

    if (confirmacion == JOptionPane.YES_OPTION) {
        try {
            // 5. Llamada al método del DAO (usando el objeto emDAO que creamos antes)
            EM.desactivarEmpleado(idEmpleado);

            JOptionPane.showMessageDialog(this, "Empleado desactivado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

            // 6. Actualizar interfaz respetando el filtro de inactivos
            // Si el checkbox de 'Listar Inactivos' no está marcado, el registro debe desaparecer de la vista
            if (jCheckBoxListarInactivos != null && jCheckBoxListarInactivos.isSelected()) {
                listarEmpleadosInactivos(); // Método para ver solo los de estado 0
            } else {
                listarEmpleados(); // El registro desaparece de la lista de activos (estado 1)
            }

            // 7. Limpiar el formulario
            limpiarCamposEmpleado();

        } catch (SQLException ex) {
            // Manejo de errores basado en el código de error del Procedure (20220 = No existe)
            int errorCode = ex.getErrorCode();
            String mensajeError = (errorCode == 20220) ? "El empleado no existe en la base de datos." : ex.getMessage();

            JOptionPane.showMessageDialog(this,
                "No se pudo cambiar el estado del empleado.\nDetalle: " + mensajeError,
                "Error de Base de Datos",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_BTN_DesactivarActionPerformed

    private void BTN_ListarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ListarEmpleadosActionPerformed
        this.listarEmpleados();
        this.BTN_Nuevo.setEnabled(true);
        this.BTN_Guardar.setEnabled(false);
        this.BTN_Modificar.setEnabled(false);
        this.BTN_ListarEmpleados.setEnabled(false);
        this.jCheckBoxListarInactivos.setSelected(false);
        jPanel3.requestFocus();
    }//GEN-LAST:event_BTN_ListarEmpleadosActionPerformed

    private void txtdniempleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdniempleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdniempleadoActionPerformed

    private void txtobservacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtobservacionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtobservacionesActionPerformed

    private void jComboBox_generoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox_generoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_generoKeyReleased

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
            System.out.println("Error");
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Frm_Empleado().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Cerrar1;
    private javax.swing.JButton BTN_Desactivar;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_ListarEmpleados;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_PDF;
    private javax.swing.JTable JTABLE_Mant_Empleado;
    private javax.swing.JTextField TXT_BuscarEmpleados;
    private javax.swing.JCheckBox jCheckBoxActivar;
    private javax.swing.JCheckBox jCheckBoxListarInactivos;
    private javax.swing.JComboBox<String> jComboBox_genero;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtApellidoEmpleado;
    private javax.swing.JTextField txtNombreEmpleado;
    private javax.swing.JTextField txtcodigoempleado;
    private javax.swing.JTextField txtcorreo1;
    private javax.swing.JTextField txtcorreo2;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtdniempleado;
    private javax.swing.JTextField txtfecharegistro;
    private javax.swing.JTextField txtobservaciones;
    private javax.swing.JTextField txttelefono1;
    private javax.swing.JTextField txttelefono2;
    // End of variables declaration//GEN-END:variables
   
    private void cargarGenero() {
        try {
            jComboBox_genero.removeAllItems();
            jComboBox_genero.addItem("<<Seleccionar>>"); // Opción por defecto

            ResultSet rs = EM.combobox_listarGenero();
            while (rs.next()) {
                jComboBox_genero.addItem(rs.getString("Genero"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar el genero: " + e.getMessage());
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
    
    //método para mostrar registros en el jtable
    private void listarEmpleados() {
        try {
        JTABLE_Mant_Empleado.setAutoCreateRowSorter(true);
        modeloTablaEmpleado.setRowCount(0); // modeloTablaEmpleado debe estar declarado arriba

        // emDAO es tu objeto de la clase EmpleadoMethod
        ResultSet rs = EM.listarEmpleados(); 

        while (rs.next()) {
            Object fila[] = {
                rs.getInt("ID"), 
                rs.getString("DNI"),
                rs.getString("Nombre de Empleado"),
                rs.getString("Apellido de Empleado"),
                rs.getDate("Fecha de Nacimiento"),
                rs.getTimestamp("Fecha de Registro"),
                rs.getString("Lugar de Residencia"),
                rs.getString("Correo Principal"),
                rs.getString("Correo Secundario"),
                rs.getString("Telefono Principal"),
                rs.getString("Telefono Secundario"),
                rs.getString("Genero"),
                rs.getString("Observaciones"),
                rs.getInt("Estado") == 1 ? "Activo" : "Inactivo"
            };
            modeloTablaEmpleado.addRow(fila);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar empleados: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void listarEmpleadosInactivos() {
    try {
        JTABLE_Mant_Empleado.setAutoCreateRowSorter(true);
        modeloTablaEmpleado.setRowCount(0);

        ResultSet rs = EM.listarEmpleadosDesactivados(); 

        while (rs.next()) {
            Object fila[] = {
                rs.getInt("ID"), 
                rs.getString("DNI"),
                rs.getString("Nombre de Empleado"),
                rs.getString("Apellido de Empleado"),
                rs.getDate("Fecha de Nacimiento"),
                rs.getTimestamp("Fecha de Registro"),
                rs.getString("Lugar de Residencia"),
                rs.getString("Correo Principal"),
                rs.getString("Correo Secundario"),
                rs.getString("Telefono Principal"),
                rs.getString("Telefono Secundario"),
                rs.getString("Genero"),
                rs.getString("Observaciones"),
                rs.getInt("Estado") == 1 ? "Activo" : "Inactivo"
            };
            modeloTablaEmpleado.addRow(fila);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar inactivos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    private void limpiarCamposEmpleado() {
    txtcodigoempleado.setText("");
    txtdniempleado.setText("");
    txtNombreEmpleado.setText("");
    txtApellidoEmpleado.setText("");
    jDateChooser1.setDate(null);
    txtdireccion.setText("");
    txtcorreo1.setText("");
    txtcorreo2.setText("");
    txttelefono1.setText("");
    txttelefono2.setText("");
    txtobservaciones.setText("");
    jComboBox_genero.setSelectedIndex(0);

    // Control de botones
    BTN_Guardar.setEnabled(true);
    BTN_Nuevo.setEnabled(false);
    BTN_Modificar.setEnabled(false);
    
    // Si tienes un botón para volver a listar
    if(BTN_ListarEmpleados != null) BTN_ListarEmpleados.setEnabled(true);
}

    
 public void buscarEmpleadoPorNombre() {
    modeloTablaEmpleado.setRowCount(0);
    String criterio = TXT_BuscarEmpleados.getText().trim();
    
    try {
        // Lógica de filtro inicial
        if (criterio.isEmpty()) {
            if (jCheckBoxListarInactivos.isSelected()) {
                this.listarEmpleadosInactivos();
            } else {
                this.listarEmpleados();
            }
            return;
        }

        boolean hayResultados = false;
        // Llama al método de búsqueda de tu DAO
        ResultSet rs = this.EM.buscarEmpleado(criterio);

        while (rs.next()) {
            hayResultados = true;
            Object[] fila = {
                rs.getInt("id_empleado"),
                rs.getString("dni_empleado"),
                rs.getString("nombre_empleado"),
                rs.getString("apellido_empleado"),
                rs.getDate("fecha_nacimiento"),
                rs.getTimestamp("fecha_registro"),
                rs.getString("direccion_empleado"),
                rs.getString("correo_principal"),
                rs.getString("correo_secundario"),
                rs.getString("telefono_principal"),
                rs.getString("telefono_secundario"),
                rs.getString("observacion_empleado"),
                rs.getString("genero"),
                rs.getInt("estado") == 1 ? "Activo" : "Inactivo"
            };
            modeloTablaEmpleado.addRow(fila);
        }

        if (!hayResultados) {
            JOptionPane.showMessageDialog(this, "No se encontraron coincidencias para: " + criterio,
                    "Sin resultados", JOptionPane.INFORMATION_MESSAGE);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error en la búsqueda:\n" + e.getMessage(),
                "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
    }
}
}
