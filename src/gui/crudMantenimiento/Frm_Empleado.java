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
import static com.mysql.cj.conf.PropertyKey.logger;
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
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Frm_Empleado extends javax.swing.JFrame {

    //Modelo para mostrar datos en ta tabla
    DefaultTableModel modeloTablaEmpleado = new DefaultTableModel();
    
    //Objeto conexión a la base de datos
    EmpleadoMethod EM = new EmpleadoMethod();
    
    public Frm_Empleado() {
        initComponents();
        this.setLocationRelativeTo(null);

        this.EM = new EmpleadoMethod();
            
        // Definir los encabezados de la tabla
        String titulos[] = {"ID", "DNI", "Nombres", "Apellidos", "F. de Nac", "F. de Regitro", "Dirección", 
        "Correo Principal", "Correo Secundario", "Telefono Principal", "Telefono Secundario", "Genero", "Observaciones"};
        
        // Asignar los titulos al modelo
        modeloTablaEmpleado.setColumnIdentifiers(titulos);
        
        // Establecer el modelo de la JTable
        JTABLE_Mant_Empleado.setModel(modeloTablaEmpleado);
        

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
        txtdniempleado1 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtcorreo1 = new javax.swing.JTextField();
        txtobservaciones = new javax.swing.JTextField();
        txttelefono1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
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
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 122, 170, 30));

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

    private void txtNombreEmpleadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreEmpleadoKeyTyped
 
    }//GEN-LAST:event_txtNombreEmpleadoKeyTyped

    private void JTABLE_Mant_EmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_EmpleadoMouseClicked
       
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
        listarEmpleados();
    }//GEN-LAST:event_BTN_VerEmpleadosActionPerformed

    private void BTN_Cambiar_EstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_Cambiar_EstadoActionPerformed
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
            EM.darDeBajaEmpleado(codigoEmpleado); 

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
    }//GEN-LAST:event_BTN_Cambiar_EstadoActionPerformed

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
        try {
    // 1. Obtener datos de los campos de texto
    String dni = txtdniempleado1.getText().trim(); 
    String nombres = txtNombreEmpleado.getText().trim();
    String apellidos = txtApellidoEmpleado.getText().trim();
    java.util.Date fechaNacimiento = jDateChooser1.getDate();
    String direccion = txtdireccion.getText().trim();
    String correo1 = txtcorreo1.getText().trim();
    String correo2 = txtcorreo2.getText().trim();
    String telefono1 = txttelefono1.getText().trim();
    String telefono2 = txttelefono2.getText().trim();
    String generoSeleccionado = jComboBox_genero.getSelectedItem().toString();
    String observacion = txtobservaciones.getText().trim();

    // 2. Validar campos obligatorios
    if (dni.isEmpty() || nombres.isEmpty() || apellidos.isEmpty() || direccion.isEmpty() || correo1.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.", "Validación", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 3. Validar formato de DNI (8 dígitos)
    if (dni.length() != 8 || !dni.matches("\\d+")) {
        JOptionPane.showMessageDialog(this, "El DNI debe tener exactamente 8 números.", "Validación", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // 4. Formatear la fecha para MySQL
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    String fechaFormateada = (fechaNacimiento != null) ? sdf.format(fechaNacimiento) : null;

    // 5. Obtener ID del género desde la lógica
    int idGenero = EM.obtenerIdGenero(generoSeleccionado);

    // 6. Confirmación de la operación
    int confirmar = JOptionPane.showConfirmDialog(this, "¿Está seguro de actualizar los datos de este empleado?", "Confirmar Actualización", JOptionPane.YES_NO_OPTION);

    if (confirmar == JOptionPane.YES_OPTION) {
        // 7. Llamada al método de la capa lógica
        EM.actualizarEmpleado(
            dni, 
            nombres, 
            apellidos, 
            fechaFormateada, 
            direccion, 
            telefono1, 
            telefono2, 
            correo1, 
            correo2, 
            idGenero, 
            observacion
        );

        // 8. Actualizar la interfaz
        listarEmpleados(); 
        limpiarCampos();
        JOptionPane.showMessageDialog(this, "Empleado actualizado con éxito.");
    }

} catch (IllegalArgumentException e) {
    // Captura errores de validación de correo o duplicados
    JOptionPane.showMessageDialog(this, e.getMessage(), "Atención", JOptionPane.WARNING_MESSAGE);
} catch (SQLException e) {
    // Captura errores de conexión o del procedimiento almacenado
    JOptionPane.showMessageDialog(this, "Error en la base de datos: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
}
    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
    try {
        String dni = txtdniempleado1.getText().trim();
        String nombres = txtNombreEmpleado.getText().trim();
        String apellidos = txtApellidoEmpleado.getText().trim();
        java.util.Date fechaNacimiento = jDateChooser1.getDate();
        String direccion = txtdireccion.getText().trim();
        String correo1 = txtcorreo1.getText().trim();
        String correo2 = txtcorreo2.getText().trim();
        String telefono1 = txttelefono1.getText().trim();
        String telefono2 = txttelefono2.getText().trim();
        String generoSeleccionado = jComboBox_genero.getSelectedItem().toString();
        String observacion = txtobservaciones.getText().trim();

    // Validaciones de campos obligatorios
    if (dni.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Ingrese el DNI del empleado", "Campo requerido", JOptionPane.WARNING_MESSAGE);
      txtdniempleado1.requestFocus();
      return;
    }
    if (nombres.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Ingrese los nombres del empleado", "Campo requerido", JOptionPane.WARNING_MESSAGE);
      txtNombreEmpleado.requestFocus();
      return;
    }
    if (apellidos.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Ingrese los apellidos del empleado", "Campo requerido", JOptionPane.WARNING_MESSAGE);
      txtApellidoEmpleado.requestFocus();
      return;
    }
    if (direccion.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Ingrese la direccion del empleado", "Campo requerido", JOptionPane.WARNING_MESSAGE);
      txtdireccion.requestFocus();
      return;
    }
    if (correo1.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Ingrese el correo del empleado", "Campo requerido", JOptionPane.WARNING_MESSAGE);
      txtcorreo1.requestFocus();
      return;
    }    
    if (telefono1.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Ingrese el telefono del empleado", "Campo requerido", JOptionPane.WARNING_MESSAGE);
      txttelefono1.requestFocus();
      return;
    }  
    if (fechaNacimiento == null) {
        JOptionPane.showMessageDialog(this, "Seleccione una fecha de nacimiento");
        return;
    }
    
    // Validacion rapida de DNI para evitar error por tipo
    if (dni.length() != 8 || !dni.matches("\\d+")) {
        JOptionPane.showMessageDialog(this, "Ingrese un DNI válido de 8 digitos");
        return;
    }
    
    // Validar teléfonos
    if(!telefono2.isEmpty() && telefono1.equals(telefono2)) {
        JOptionPane.showMessageDialog(this, "Telefono 1 y Telefono 2 no pueden ser"
            + "iguales.", "Validacion", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Obtener el codigo del genero
    int id_genero = EM.obtenerIdGenero(generoSeleccionado);
    if (id_genero == -1) {
        JOptionPane.showMessageDialog(this, "El genero seleccionado no es valido");
        return;
    }
    
    // Convertir fecha a formato SQL
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
    String fechaFormateada = sdf.format(fechaNacimiento);
    
    // Intentar registrar (Validacion completa esta en el metodo)
    if (txtcodigoempleado.getText().isEmpty()) {
        
        EM.insertarEmpleado(
            dni,
            nombres,
            apellidos,
            fechaFormateada,
            direccion,
            telefono1,
            telefono2,
            correo1,
            correo2,
            id_genero,
            observacion
        );

        JOptionPane.showMessageDialog(this, "Empleado registrado correctamente.");
        listarEmpleados();
        limpiarCampos();
        } else {
    }

    } catch (IllegalArgumentException ex) {
    JOptionPane.showMessageDialog(this, ex.getMessage(), "Validacion", JOptionPane.WARNING_MESSAGE);
    
    } catch (SQLException ex) {
    JOptionPane.showMessageDialog(this, "Error al guardar empleado " + ex.getMessage(), 
        "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
        this.limpiarCampos();
        BTN_Guardar.setEnabled(true);
        BTN_Modificar.setEnabled(false);
        
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
            System.out.println("Error");
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtApellidoEmpleado;
    private javax.swing.JTextField txtNombreEmpleado;
    private javax.swing.JTextField txtcodigoempleado;
    private javax.swing.JTextField txtcorreo1;
    private javax.swing.JTextField txtcorreo2;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtdniempleado1;
    private javax.swing.JTextField txtfecharegistro;
    private javax.swing.JTextField txtobservaciones;
    private javax.swing.JTextField txttelefono1;
    private javax.swing.JTextField txttelefono2;
    // End of variables declaration//GEN-END:variables
   
    //método para mostrar registros en el jtable
    private void listarEmpleados() {
        try {
            JTABLE_Mant_Empleado.setAutoCreateRowSorter(true);
            // Limpiar la tabla antes de volver a llenarla
            modeloTablaEmpleado.setRowCount(0);

            // Llamar al método ya implementado en la clase de conexión
            ResultSet rs = this.EM.listarEmpleados();

            // Recorrer los resultados y agregarlos a la tabla
            while (rs.next()) {
                Object fila[] = {
                    rs.getInt("Codigo de Empleado"),
                    rs.getString("DNI"),
                    rs.getString("Nombre de Empleado"),
                    rs.getString("Apellido de Empleado"), 
                    rs.getDate("Fecha de Nacimiento"), 
                    rs.getString("Fecha de Registro"),    
                    rs.getString("Lugar de Residencia"),    
                    rs.getString("Correo Principal"),    
                    rs.getString("Correo Secundario"),    
                    rs.getString("Teléfono Principal"),    
                    rs.getString("Teléfono Secundario"),    
                    rs.getString("Género"),
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
        jDateChooser1.setDate(null);  
        txtfecharegistro.setText(null);  
        txtdireccion.setText("");       
        txtcorreo1.setText("");         
        txtcorreo2.setText("");         
        txttelefono1.setText("");       
        txttelefono2.setText("");       
        jComboBox_genero.setSelectedIndex(0); 
        txtobservaciones.setText(""); 
    }
    
    public void buscarEmpleadoPorNombre() {
        modeloTablaEmpleado.setRowCount(0);
        String nombre = TXT_BuscarEmpleados.getText().trim();
        try {
            //Llama al método que retorna los datos de Mesaes
            ResultSet rs = this.EM.buscarEmpleados(nombre);
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("Codigo de Empleado"),
                    rs.getString("DNI"),
                    rs.getString("Nombre de Empleado"),
                    rs.getString("Apellido de Empleado"), 
                    rs.getDate("Fecha de Nacimiento"), 
                    rs.getString("Fecha de Registro"),    
                    rs.getString("Lugar de Residencia"),    
                    rs.getString("Correo Principal"),    
                    rs.getString("Correo Secundario"),    
                    rs.getString("Teléfono Principal"),    
                    rs.getString("Teléfono Secundario"),    
                    rs.getString("Género"),
                    rs.getString("Observaciones"),   
                };
                modeloTablaEmpleado.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar Mesaes:\n" + e.getMessage(),
                    "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        }
    }
    // Dentro de Frm_Empleado.java

    private void llenarComboGenero() {
    try {
        // 1. Limpiamos por seguridad
        jComboBox_genero.removeAllItems(); 
        
        // 2. Pedimos la lista a la lógica (EmpleadoMethod)
        // Usamos java.util.List para evitar conflictos con otras librerías
        java.util.List<String> lista = EM.listarGeneros(); 
        
        // 3. Agregamos un ítem inicial neutro
        jComboBox_genero.addItem("Seleccione...");
        
        // 4. Llenamos con los datos de la DB
        for (String genero : lista) {
            jComboBox_genero.addItem(genero);
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al llenar combo: " + ex.getMessage());
    }
}
}
