package gui.crudMantenimiento;

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
import connection.ConnectionDB;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//Metodo de Empleado
import logic.dao.EmpleadoMethod;
//Metodo de genero para el jcombobox
import logic.dao.GeneroMethod;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Frm_Empleado extends javax.swing.JFrame {
    //Modelo para mostrar datos en ta tabla
    DefaultTableModel modeloTablaEmpleado = new DefaultTableModel();
    
    //Objeto conexión a la base de datos
    EmpleadoMethod PR = new EmpleadoMethod();
    GeneroMethod UM = new GeneroMethod();
    ConnectionDB CBD = new ConnectionDB();

    //Variable para comprobar cambios en mdoificar
    private String dniOriginal;
    private String nombreOriginal;
    private String apellidoOriginal;
    private String fechanacOriginal;
    private String fecharegOriginal;
    private String direccionOriginal;
    private String correoOriginal;
    private String telefonoOriginal;
    private String generoOriginal = "";
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Frm_Empleado.class.getName());

    public Frm_Empleado() {
        initComponents();
        
        //Posicion
        this.setLocationRelativeTo(null);
               
        //definir los encabezados de la tabla
        String titulos[]={"DNI","Nombre del empleado","Apellidos del empleado","Fecha de nacimiento","Fecha de registro", "direccion","correo", "telefono", "genero"};
        
        //Asignar los tiutlos al modelo
        modeloTablaEmpleado.setColumnIdentifiers(titulos);
        
        //Establecer el modelo a la JTable
        JTABLE_Mant_Empleado.setModel(modeloTablaEmpleado);
        
        // Ocultar columnas sensibles o internas: solo visual, no afecta modelo
        // Ocultar columna "id_empleado"
        JTABLE_Mant_Empleado.getColumnModel().getColumn(0).setMinWidth(0);
        JTABLE_Mant_Empleado.getColumnModel().getColumn(0).setMaxWidth(0);
        JTABLE_Mant_Empleado.getColumnModel().getColumn(0).setWidth(0);

        // Ocultar columna "Correo Secundario"
        JTABLE_Mant_Empleado.getColumnModel().getColumn(8).setMinWidth(0);
        JTABLE_Mant_Empleado.getColumnModel().getColumn(8).setMaxWidth(0);
        JTABLE_Mant_Empleado.getColumnModel().getColumn(8).setWidth(0);   

    // Ocultar columna "Observacion"
        JTABLE_Mant_Empleado.getColumnModel().getColumn(11).setMinWidth(0);
        JTABLE_Mant_Empleado.getColumnModel().getColumn(11).setMaxWidth(0);
        JTABLE_Mant_Empleado.getColumnModel().getColumn(11).setWidth(0);
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
        txtfechanacimiento = new javax.swing.JTextField();
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
        txtdniempleado1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtcorreo1 = new javax.swing.JTextField();
        txtobservaciones = new javax.swing.JTextField();
        txttelefono1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_Empleado = new javax.swing.JTable();
        BTN_EXCEL = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXT_BuscarEmpleados = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        BTN_Cerrar1 = new javax.swing.JButton();
        BTN_PDF = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        BTN_VerEmpleados = new javax.swing.JButton();
        BTN_Cambiar_Estado = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Cancel = new javax.swing.JButton();

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

        txtfechanacimiento.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtfechanacimiento.setForeground(new java.awt.Color(0, 0, 204));
        txtfechanacimiento.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtfechanacimiento.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtfechanacimiento.addActionListener(this::txtfechanacimientoActionPerformed);
        txtfechanacimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfechanacimientoKeyTyped(evt);
            }
        });
        jPanel1.add(txtfechanacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 140, 30));

        jComboBox_genero.addActionListener(this::jComboBox_generoActionPerformed);
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
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 130, -1));

        txtfecharegistro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtfecharegistro.setForeground(new java.awt.Color(0, 0, 204));
        txtfecharegistro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtfecharegistro.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtfecharegistro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtfecharegistroKeyTyped(evt);
            }
        });
        jPanel1.add(txtfecharegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 120, 140, 30));

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
        jPanel1.add(txtdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, 190, 30));

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

        txtdniempleado1.setEditable(false);
        txtdniempleado1.setBackground(new java.awt.Color(255, 255, 255));
        txtdniempleado1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtdniempleado1.setForeground(new java.awt.Color(0, 0, 204));
        txtdniempleado1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtdniempleado1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtdniempleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 120, 30));

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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 730, 300));

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 920, 220));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setText("     Exportar XLSX");
        BTN_EXCEL.addActionListener(this::BTN_EXCELActionPerformed);
        getContentPane().add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 650, 170, 50));

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

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 920, 50));

        BTN_Cerrar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar1.setText("     Cerrar");
        BTN_Cerrar1.addActionListener(this::BTN_Cerrar1ActionPerformed);
        getContentPane().add(BTN_Cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 650, 165, 50));

        BTN_PDF.setText("     Exportar PDF");
        BTN_PDF.addActionListener(this::BTN_PDFActionPerformed);
        getContentPane().add(BTN_PDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 650, 170, 50));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BTN_VerEmpleados.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_VerEmpleados.setText("VER EMPLEADOS");
        BTN_VerEmpleados.addActionListener(this::BTN_VerEmpleadosActionPerformed);
        jPanel3.add(BTN_VerEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, 165, 50));

        BTN_Cambiar_Estado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cambiar_Estado.setText("CAMBIAR ESTADO");
        BTN_Cambiar_Estado.addActionListener(this::BTN_Cambiar_EstadoActionPerformed);
        jPanel3.add(BTN_Cambiar_Estado, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 280, 165, 48));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setText("    MODIFICAR");
        BTN_Modificar.addActionListener(this::BTN_ModificarActionPerformed);
        jPanel3.add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 220, 165, 48));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setText("     GUARDAR");
        BTN_Guardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BTN_GuardarMouseClicked(evt);
            }
        });
        BTN_Guardar.addActionListener(this::BTN_GuardarActionPerformed);
        jPanel3.add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 160, 165, 48));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setText("      NUEVO");
        BTN_Nuevo.addActionListener(this::BTN_NuevoActionPerformed);
        jPanel3.add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 100, 165, 48));

        BTN_Cancel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cancel.setText("     CANCELAR");
        BTN_Cancel.addActionListener(this::BTN_CancelActionPerformed);
        jPanel3.add(BTN_Cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 100, 165, 48));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 930, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtfechanacimientoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtfechanacimientoKeyTyped
 
    }//GEN-LAST:event_txtfechanacimientoKeyTyped

    private void txtNombreEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreEmpleadoKeyTyped
 
    }//GEN-LAST:event_txtNombreEmpleadoKeyTyped

    private void JTABLE_Mant_EmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_EmpleadoMouseClicked
        int filaSeleccionada = JTABLE_Mant_Empleado.getSelectedRow();
        BTN_Modificar.setEnabled(true);
        BTN_Cambiar_Estado.setEnabled(true);
        BTN_Guardar.setEnabled(false);
        BTN_Cancel.setVisible(false);
        BTN_Cancel.setEnabled(false);
        BTN_VerEmpleados.setEnabled(false);

        if (filaSeleccionada != -1) {
            // Obtener los datos de la fila seleccionada
            String dni= JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 0).toString().trim();
            String nombres = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 1).toString().trim();
            String apellidos = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 4).toString().trim();
            String fechanacimiento = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 6).toString().trim();
            String fecharegistro = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 5).toString().trim();
            String direccion = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 2).toString().trim();
            String correo = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 2).toString().trim();
            String telefono = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 2).toString().trim();
            String genero = JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 2).toString().trim();
            
            
            // Mostrar en los controles
            txtcodigoempleado.setText(dni);
            txtNombreEmpleado.setText(nombres);
            txtApellidoEmpleado.setText(apellidos);
            txtfechanacimiento.setText(fechanacimiento);
            txtfecharegistro.setText(fecharegistro);
            txtdireccion.setText(direccion);
            txtcorreo2.setText(correo);
            txttelefono2.setText(telefono);

            // Guardar valores originales para comparación
            dniOriginal = dni;
            nombreOriginal = nombres;
            apellidoOriginal = apellidos;
            fechanacOriginal = fechanacimiento;
            fecharegOriginal = fecharegistro;
            direccionOriginal = direccion;
            correoOriginal = correo;
            telefonoOriginal = telefono;
            generoOriginal = genero;

            // Buscar coincidencia en el ComboBox ignorando mayúsculas
            boolean generoBD = false;
            for (int i = 0; i < jComboBox_genero.getItemCount(); i++) {
                String item = jComboBox_genero.getItemAt(i).trim();
                if (item.equalsIgnoreCase(genero)) {
                    jComboBox_genero.setSelectedIndex(i);
                    generoBD = true;
                    break;
                }
            }
    
            // 2. Convertimos la letra al texto que tiene tu ComboBox
            if (!generoBD) {
                JOptionPane.showMessageDialog(this,
                    "No se encontró el genero en la lista del combo.",
                    "Genero no encontrada", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_JTABLE_Mant_EmpleadoMouseClicked

    private void BTN_EXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EXCELActionPerformed
       try {
            // 1. Crear un nuevo libro de Excel (.xlsx)
            XSSFWorkbook workbook = new XSSFWorkbook();

            // 2. Crear una hoja dentro del libro
            XSSFSheet sheet = workbook.createSheet("Facultades");

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
        try {
            String texto = TXT_BuscarEmpleados.getText().trim();
            // Si el campo está vacio, listar todos
            if (texto.isEmpty()) {
                listarEmpleados();
                return;
            }
            // Limpiar la tabla
            modeloTablaEmpleado.setRowCount(0);
            
            ResultSet rs = PR.buscarEmpleados(texto);
            
            boolean hayResultados = false;
            while (rs.next()) {
            hayResultados = true;
            Object[] fila = {
                rs.getInt("id_empleado"),
                rs.getString("dni_empleado"),
                rs.getString("apellido_empleado"),
                rs.getString("nombre_empleado"),
                rs.getDate("fecha_nacimiento"),
                rs.getString("direccion_empleado"),
                rs.getString("telefono_principal"),
                rs.getString("correo_principal"),
                rs.getString("nombre_genero") 
            };
            modeloTablaEmpleado.addRow(fila);
        }
        if (!hayResultados) {
             // Lógica para manejar búsqueda sin éxito
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al filtrar empleados: " + e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE); //
    }
    }//GEN-LAST:event_TXT_BuscarEmpleadosKeyReleased

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

    private void BTN_VerEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerEmpleadosActionPerformed
        this.listarEmpleados();
    }//GEN-LAST:event_BTN_VerEmpleadosActionPerformed

    private void BTN_Cambiar_EstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_Cambiar_EstadoActionPerformed
        
    }//GEN-LAST:event_BTN_Cambiar_EstadoActionPerformed

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
        // 1. Obtener la fila seleccionada de tu JTable de empleados
    int filaSeleccionada = JTABLE_Mant_Empleado.getSelectedRow(); 

    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Seleccione un empleado de la tabla.", 
            "Aviso", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 2. Obtener el código del empleado (asumiendo que está en la columna 0)
    int codigoEmpleado = Integer.parseInt(JTABLE_Mant_Empleado.getValueAt(filaSeleccionada, 0).toString());

    // 3. Ventana de confirmación
    int confirmacion = JOptionPane.showConfirmDialog(this, 
        "¿Está seguro de dar de baja al empleado seleccionado?", 
        "Confirmar baja", JOptionPane.YES_NO_OPTION);

    if (confirmacion == JOptionPane.YES_OPTION) {
        try {
            // 4. Llamar al método de tu clase lógica PR
            // Nota: Asegúrate de tener el método 'darDeBajaEmpleado' en EmpleadoMethod.java
            PR.darDeBajaEmpleado(codigoEmpleado); 

            JOptionPane.showMessageDialog(this, "✅ Empleado dado de baja correctamente.");
            
            // 5. Refrescar la tabla y limpiar el formulario
            listarEmpleados(); 
            limpiarCampos(); 

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al dar de baja: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
    try {
    String dni = txtdniempleado1.getText().trim();
    String apellidos = txtApellidoEmpleado.getText().trim();
    String nombres = txtNombreEmpleado.getText().trim();
    String direccion = txtdireccion.getText().trim();
    String telefono = txttelefono1.getText().trim();
    String correo = txtcorreo1.getText().trim();
    String generoSeleccionado = jComboBox_genero.getSelectedItem().toString();

    // 2. Validaciones de campos obligatorios
    if (dni.isEmpty() || apellidos.isEmpty() || nombres.isEmpty() || correo.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Complete los campos obligatorios (*).", 
            "Validación", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    int idGenero = PR.obtenerIdGenero(generoSeleccionado);
    
    if (idGenero == -1) {
        JOptionPane.showMessageDialog(this, "Género no válido.");
        return;
    } 

    // 4. Ejecutar Inserción o Actualización
    if (txtcodigoempleado.getText().isEmpty()) {
        // INSERTAR: Pasamos null en el parámetro de fecha
        PR.insertarEmpleado(dni, nombres, apellidos, null, 
                           direccion, telefono, null, correo, idGenero);
    } else {
        // ACTUALIZAR: Pasamos null en el parámetro de fecha
        int idEmpleado = Integer.parseInt(txtcodigoempleado.getText());
        PR.actualizarEmpleado(idEmpleado, nombres, apellidos, null,
                             direccion, correo, telefono, null, idGenero);
    }

    // 5. Refrescar interfaz y limpiar cajas
    listarEmpleados();
    limpiarCampos();

} catch (IllegalArgumentException ex) {
    JOptionPane.showMessageDialog(this, ex.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(this, "Error de SQL: " + ex.getMessage(), 
        "Error", JOptionPane.ERROR_MESSAGE);
}
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
        limpiarCampos();
        BTN_Guardar.setEnabled(true);
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CancelActionPerformed
        JTABLE_Mant_Empleado.setRowSelectionAllowed(true);
        JTABLE_Mant_Empleado.setColumnSelectionAllowed(true);
        JTABLE_Mant_Empleado.setCellSelectionEnabled(true);

        BTN_Guardar.setEnabled(true);
        BTN_Cambiar_Estado.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        BTN_Nuevo.setVisible(true);
        BTN_Nuevo.setEnabled(true);
        BTN_Cancel.setVisible(false);
        JTABLE_Mant_Empleado.setEnabled(true);
    }//GEN-LAST:event_BTN_CancelActionPerformed

    private void txttelefono2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefono2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelefono2KeyTyped

    private void txtApellidoEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoEmpleadoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoEmpleadoKeyTyped

    private void txtfechanacimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfechanacimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfechanacimientoActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new Frm_Empleado().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Cambiar_Estado;
    private javax.swing.JButton BTN_Cancel;
    private javax.swing.JButton BTN_Cerrar1;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_PDF;
    private javax.swing.JButton BTN_VerEmpleados;
    private javax.swing.JTable JTABLE_Mant_Empleado;
    private javax.swing.JTextField TXT_BuscarEmpleados;
    private javax.swing.JComboBox<String> jComboBox_genero;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtApellidoEmpleado;
    private javax.swing.JTextField txtNombreEmpleado;
    private javax.swing.JTextField txtcodigoempleado;
    private javax.swing.JTextField txtcorreo1;
    private javax.swing.JTextField txtcorreo2;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtdniempleado1;
    private javax.swing.JTextField txtfechanacimiento;
    private javax.swing.JTextField txtfecharegistro;
    private javax.swing.JTextField txtobservaciones;
    private javax.swing.JTextField txttelefono1;
    private javax.swing.JTextField txttelefono2;
    // End of variables declaration//GEN-END:variables
   
    //método para mostrar registros en el jtable
    private void listarEmpleados() {
        try {
            // 1. Configuración de la tabla
            JTABLE_Mant_Empleado.setAutoCreateRowSorter(true);
            modeloTablaEmpleado.setRowCount(0);

            // 2. Obtener datos
            ResultSet rs = PR.listarEmpleados();

            while (rs.next()) {
            Object fila[] = {
                rs.getInt("Codigo de Empleado"),
                rs.getString("DNI"),
                rs.getString("Nombre de Empleado"),
                rs.getString("Apellido de Empleado"), 
                rs.getString("Fecha de Nacimiento"), 
                rs.getString("Fecha de Registro"),    
                rs.getString("Lugar de Residencia"),    
                rs.getString("Correo Principal"),    
                rs.getString("Correo Secundario"),    
                rs.getString("Teléfono Principal"),    
                rs.getString("Teléfono Secundario"),    
                rs.getInt("Género"),
                rs.getString("Observaciones"),   
            };
                modeloTablaEmpleado.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al listar empleados: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /* Limpiar Cajas de Empleado */
    private void limpiarCampos() {
        txtcodigoempleado.setText("");          
        txtdniempleado1.setText("");             
        txtNombreEmpleado.setText("");         
        txtApellidoEmpleado.setText("");       
        txtfechanacimiento.setText(null);  
        txtdireccion.setText("");       
        txtcorreo1.setText("");         
        txtcorreo2.setText("");         
        txttelefono1.setText("");       
        txttelefono2.setText("");       
        jComboBox_genero.setSelectedIndex(0); 
        txtobservaciones.setText(""); 
    }       
}
