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
       
        //Metodo para cargar el combobox
        cargarUnidadMedida();
        
        //definir los encabezados de la tabla
        String titulos[]={"DNI","Nombre del empleado","Apellidos del empleado","Fecha de nacimiento","Fecha de registro", "direccion","correo", "telefono", "genero"};
        
        //Asignar los tiutlos al modelo
        modeloTablaEmpleado.setColumnIdentifiers(titulos);
        
        //Establecer el modelo a la JTable
        JTABLE_Mant_Empleado.setModel(modeloTablaEmpleado);
        
        //Desabilitar campo de codigo (solo se mostrara no se escribe)
        txtdniempleado.setEnabled(false);
        BTN_Cancel.setEnabled(false);
        BTN_Nuevo.setEnabled(false);
        BTN_Guardar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        BTN_Desactivar.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtdniempleado = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtfechanacimiento = new javax.swing.JTextField();
        jComboBox_genero = new javax.swing.JComboBox<>();
        txtNombreEmpleado = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txttelefeono = new javax.swing.JTextField();
        txtApellidoEmpleado = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtfecharegistro = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtdireccion = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtcorreo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_Empleado = new javax.swing.JTable();
        BTN_EXCEL = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXT_BuscarProductos = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        BTN_Cerrar1 = new javax.swing.JButton();
        BTN_PDF = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        BTN_VerEmpleados = new javax.swing.JButton();
        BTN_Desactivar = new javax.swing.JButton();
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
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 110, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Fecha de nacimiento");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 140, -1));

        txtdniempleado.setEditable(false);
        txtdniempleado.setBackground(new java.awt.Color(255, 255, 255));
        txtdniempleado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtdniempleado.setForeground(new java.awt.Color(0, 0, 204));
        txtdniempleado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtdniempleado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtdniempleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 120, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Nombre del Empleado");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 140, -1));

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
        jPanel1.add(jComboBox_genero, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, 120, 30));

        txtNombreEmpleado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNombreEmpleado.setForeground(new java.awt.Color(0, 0, 204));
        txtNombreEmpleado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombreEmpleado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtNombreEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreEmpleadoKeyTyped(evt);
            }
        });
        jPanel1.add(txtNombreEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 40, 190, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Apellido del Empleado");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 140, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Genero");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 170, 100, -1));

        txttelefeono.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txttelefeono.setForeground(new java.awt.Color(0, 0, 204));
        txttelefeono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txttelefeono.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txttelefeono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefeonoKeyTyped(evt);
            }
        });
        jPanel1.add(txttelefeono, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 220, 30));

        txtApellidoEmpleado.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtApellidoEmpleado.setForeground(new java.awt.Color(0, 0, 204));
        txtApellidoEmpleado.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtApellidoEmpleado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtApellidoEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoEmpleadoKeyTyped(evt);
            }
        });
        jPanel1.add(txtApellidoEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 140, 30));

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
        jLabel10.setText("Direccion");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 90, 100, -1));

        txtdireccion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtdireccion.setForeground(new java.awt.Color(0, 0, 204));
        txtdireccion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtdireccion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtdireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdireccionKeyTyped(evt);
            }
        });
        jPanel1.add(txtdireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, 200, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Correo");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 100, -1));

        txtcorreo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcorreo.setForeground(new java.awt.Color(0, 0, 204));
        txtcorreo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcorreo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtcorreo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcorreoKeyTyped(evt);
            }
        });
        jPanel1.add(txtcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 220, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Telefono");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 170, 100, -1));

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

        TXT_BuscarProductos.addActionListener(this::TXT_BuscarProductosActionPerformed);
        TXT_BuscarProductos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BuscarProductosKeyReleased(evt);
            }
        });
        jPanel2.add(TXT_BuscarProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 13, 290, -1));

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

        BTN_Desactivar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Desactivar.setText("     QUITAR");
        BTN_Desactivar.addActionListener(this::BTN_DesactivarActionPerformed);
        jPanel3.add(BTN_Desactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 280, 165, 48));

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
        BTN_Desactivar.setEnabled(true);
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
            txtdniempleado.setText(dni);
            txtNombreEmpleado.setText(nombres);
            txtApellidoEmpleado.setText(apellidos);
            txtfechanacimiento.setText(fechanacimiento);
            txtfecharegistro.setText(fecharegistro);
            txtdireccion.setText(direccion);
            txtcorreo.setText(correo);
            txttelefeono.setText(telefono);

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

    private void TXT_BuscarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_BuscarProductosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_BuscarProductosActionPerformed

    private void TXT_BuscarProductosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarProductosKeyReleased

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
        this.listarProductos();
        this.BTN_Nuevo.setEnabled(true);
        this.BTN_Guardar.setEnabled(false);
        this.BTN_Desactivar.setEnabled(false);
        this.BTN_Modificar.setEnabled(false);
    }//GEN-LAST:event_BTN_VerEmpleadosActionPerformed

    private void BTN_DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DesactivarActionPerformed
       
    }//GEN-LAST:event_BTN_DesactivarActionPerformed

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
        try {
            String nuevodni = txtdniempleado.getText().trim();
            String nuevonombresEmpleados = txtNombreEmpleado.getText().trim();
            String nuevoapellidosEmpleados = txtApellidoEmpleado.getText().trim();
            String nuevofechanac = txtfechanacimiento.getText().trim();
            String nuevofechareg = txtfecharegistro.getText().trim();
            String nuevodireccion = txtdireccion.getText().trim();
            String nuevocorreo = txtcorreo.getText().trim();
            String nuevotele = txttelefeono.getText().trim();
        
        // Obtener Género del ComboBox
        String nombreGenero = (String) jComboBox_genero.getSelectedItem();

        // 2. Validar campos obligatorios (DNI, Nombres, Apellidos son críticos)
        if (nuevodni.isEmpty() || nuevonombresEmpleados.isEmpty() || nuevoapellidosEmpleados.isEmpty() || nuevofechanac.isEmpty() || nuevofechareg.isEmpty() || nuevodireccion.isEmpty() || nuevocorreo.isEmpty() || nuevotele.isEmpty() || nombreGenero == null) {
            JOptionPane.showMessageDialog(this, "DNI, Nombres, Apellidos y Género son obligatorios.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 3. Validar longitud del DNI (Debe ser 8 según tu SQL)
        if (nuevodni.length() != 8) {
            JOptionPane.showMessageDialog(this, "El DNI debe tener exactamente 8 dígitos.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // . Definir el estado (Por defecto 1 si está activo)
        int estado = 1; 

        // . Llamar al método de modificación del DAO (EmpleadoMethod)
        // idSeleccionado debe ser el ID obtenido al seleccionar la fila de la tabla
        EmpleadoMethod.modificarEmpleado(dni, nombres, apellidos, fNac, fReg, 
                                         direccion, correo1, tel1, idGenero, estado);

        JOptionPane.showMessageDialog(this, "Empleado actualizado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        // . Refrescar tabla y limpiar campos
        listarEmpleados(); // Tu método para recargar la JTable
        limpiarCamposEmpleado();

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
            
            if (nombreProducto.isEmpty()){
                JOptionPane.showMessageDialog(this, "El nombre del producto no puede estar vacio.", "Validación", JOptionPane.WARNING_MESSAGE);
                txtNombreProducto.requestFocus();
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
            PR.insertarProducto(nombreProducto, precioUnitario, stockMinimo, stockActual, codigoUnidad);

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
        JTABLE_Mant_Empleado.setRowSelectionAllowed(true);
        JTABLE_Mant_Empleado.setColumnSelectionAllowed(true);
        JTABLE_Mant_Empleado.setCellSelectionEnabled(true);

        BTN_Guardar.setEnabled(true);
        BTN_Desactivar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        BTN_Nuevo.setVisible(true);
        BTN_Nuevo.setEnabled(true);
        BTN_Cancel.setVisible(false);
        JTABLE_Mant_Empleado.setEnabled(true);
    }//GEN-LAST:event_BTN_CancelActionPerformed

    private void txttelefeonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefeonoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txttelefeonoKeyTyped

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

    private void txtcorreoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcorreoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcorreoKeyTyped

    private void jComboBox_generoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_generoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_generoActionPerformed

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
    private javax.swing.JButton BTN_Desactivar;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_PDF;
    private javax.swing.JButton BTN_VerEmpleados;
    private javax.swing.JTable JTABLE_Mant_Empleado;
    private javax.swing.JTextField TXT_BuscarProductos;
    private javax.swing.JComboBox<String> jComboBox_genero;
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
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtApellidoEmpleado;
    private javax.swing.JTextField txtNombreEmpleado;
    private javax.swing.JTextField txtcorreo;
    private javax.swing.JTextField txtdireccion;
    private javax.swing.JTextField txtdniempleado;
    private javax.swing.JTextField txtfechanacimiento;
    private javax.swing.JTextField txtfecharegistro;
    private javax.swing.JTextField txttelefeono;
    // End of variables declaration//GEN-END:variables
    
    //llenar elementos en el jcombobox el metodo viene de la clase
    //y la consulta de la vista
    private void cargarGenero() {
        try {
            jComboBox_genero.removeAllItems();
            jComboBox_genero.addItem("<<Seleccionar>>"); // Opción por defecto

            ResultSet rs = UM.listarGenero();
            while (rs.next()) {
                jComboBox_genero.addItem(rs.getString("Genero"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar el genero: " + e.getMessage());
        }
    }
    
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
                rs.getInt("ID"), 
                rs.getString("Nombre de Empleado"),
                rs.getString("Apellido de Empleado"), 
                rs.getString("Fecha de Nacimiento"), 
                rs.getString("Fecha de Registro"),    
                rs.getString("Lugar de Residencia"),    
                rs.getString("Correo Principal"),    
                rs.getString("Teléfono Principal"),    
                rs.getInt("Genero")
            };
                modeloTablaEmpleado.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar la tabla de empleados: " + e.getMessage(), "Error de Datos", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void limpiarCamposEmpleado() {
            txtdniempleado.setText("");
            txtNombreEmpleado.setText("");
            txtApellidoEmpleado.setText("");
            txtfechanacimiento.setText("");
            txtdireccion.setText("");
            txtcorreo.setText("");
            txttelefeono.setText("");
            jComboBox_genero.setSelectedIndex(0); // O -1 si quieres dejarlo vacío
            BTN_Guardar.setEnabled(true);
            BTN_Cancel.setEnabled(true);
            BTN_Nuevo.setEnabled(false);
            BTN_Modificar.setEnabled(false);
            BTN_Desactivar.setEnabled(false);
            BTN_VerEmpleados.setEnabled(false);
        }
}
