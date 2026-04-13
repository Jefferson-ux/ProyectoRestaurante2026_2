package gui.crudMantenimiento;
import com.formdev.flatlaf.FlatLightLaf;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

//Factura
import logic.dao.ContratoMethod;
import connection.ConnectionDB;
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
import com.toedter.calendar.JDateChooser;
import java.awt.HeadlessException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.UnsupportedLookAndFeelException;
import logic.dao.UsuarioMethod;


public class Frm_Contrato extends javax.swing.JFrame {
        
    DefaultTableModel modeloTablaContrato = new DefaultTableModel();
    //Objeto conexión a la base de datos
    ContratoMethod CO = new ContratoMethod();
    
    //creamos una clase interna, se usara en el guardar,
    private static class DatosContrato {
        int idContrato;
        String descripcion;
        java.sql.Date fecha;
        int idTurno;
        int idEmpleado;
        int idTipoContrato;
        int idCargo;
        int estado;
    }
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Frm_Contrato.class.getName());
       
    public Frm_Contrato() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Mantenimiento de Usuario");
        
        this.CO = new ContratoMethod();

        String[] header = {"IdContrato","Tipo de Contrato","Empleado","Cargo","Descripcion", "Fecha de Contrato", "Turno", "Estado"};

        modeloTablaContrato.setColumnIdentifiers(header);
        JTABLE_Mant_Contrato.setModel(modeloTablaContrato);
        
        JTABLE_Mant_Contrato.getColumnModel().getColumn(0).setMinWidth(0);
        JTABLE_Mant_Contrato.getColumnModel().getColumn(0).setMaxWidth(0);
        JTABLE_Mant_Contrato.getColumnModel().getColumn(0).setWidth(0);
        
        this.cargarComboTurno();
        this.cargarComboTipoContrato();
        this.cargarComboCargo();
        this.cargarComboEmpleado();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        scrollTabla = new javax.swing.JScrollPane();
        JTABLE_Mant_Contrato = new javax.swing.JTable();
        BTN_PDF = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();
        BTN_Cerrar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXT_BuscarPorContrato = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtICodigoContrato = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jComboBox_TipodeContrato = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox_Cargo = new javax.swing.JComboBox<>();
        jComboBox_Empleado = new javax.swing.JComboBox<>();
        jComboBox_Turno = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txtFechaContrato = new com.toedter.calendar.JDateChooser();
        BTN_VerContratos = new javax.swing.JButton();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        BTN_Desactivar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jCheckBoxListarInactivos = new javax.swing.JCheckBox();
        jCheckBoxActivar = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MANTENIMIENTO - CONTRATO");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 830, 30));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JTABLE_Mant_Contrato.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_Contrato.setForeground(new java.awt.Color(0, 0, 204));
        JTABLE_Mant_Contrato.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Mant_Contrato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_ContratoMouseClicked(evt);
            }
        });
        scrollTabla.setViewportView(JTABLE_Mant_Contrato);

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
        jLabel4.setText("Buscar Contratos:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, 30));

        TXT_BuscarPorContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_BuscarPorContratoActionPerformed(evt);
            }
        });
        TXT_BuscarPorContrato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BuscarPorContratoKeyReleased(evt);
            }
        });
        jPanel2.add(TXT_BuscarPorContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 450, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_search_white.png"))); // NOI18N
        jLabel5.setText("BUSCAR");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 10, 120, 30));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 920, 50));

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel2.setText("Cargo");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 140, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel3.setText("Codigo de Contrato");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 130, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel6.setText("tipo de contrato");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 130, 20));

        txtICodigoContrato.setEditable(false);
        txtICodigoContrato.setBackground(new java.awt.Color(255, 255, 255));
        txtICodigoContrato.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtICodigoContrato.setForeground(new java.awt.Color(0, 0, 204));
        txtICodigoContrato.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtICodigoContrato.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtICodigoContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 90, 30));

        txtDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        txtDescripcion.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtDescripcion.setForeground(new java.awt.Color(0, 0, 204));
        txtDescripcion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDescripcion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });
        jPanel1.add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 150, 50));

        jLabel13.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel13.setText("Descripcion");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 130, -1));

        jComboBox_TipodeContrato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_TipodeContratoActionPerformed(evt);
            }
        });
        jComboBox_TipodeContrato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox_TipodeContratoKeyReleased(evt);
            }
        });
        jPanel1.add(jComboBox_TipodeContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, 190, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel7.setText("Fecha de Contrato");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 130, 20));

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel8.setText("Empleado");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 130, 20));

        jComboBox_Cargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_CargoActionPerformed(evt);
            }
        });
        jComboBox_Cargo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox_CargoKeyReleased(evt);
            }
        });
        jPanel1.add(jComboBox_Cargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, 130, 30));

        jComboBox_Empleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_EmpleadoActionPerformed(evt);
            }
        });
        jComboBox_Empleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox_EmpleadoKeyReleased(evt);
            }
        });
        jPanel1.add(jComboBox_Empleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 330, 50));

        jComboBox_Turno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_TurnoActionPerformed(evt);
            }
        });
        jComboBox_Turno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox_TurnoKeyReleased(evt);
            }
        });
        jPanel1.add(jComboBox_Turno, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 100, 120, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel9.setText("Turno");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, 130, 20));

        txtFechaContrato.setEnabled(false);
        txtFechaContrato.setRequestFocusEnabled(false);
        jPanel1.add(txtFechaContrato, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 50, 170, -1));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 620, 260));

        BTN_VerContratos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_VerContratos.setText("VER CONTRATOS");
        BTN_VerContratos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_VerContratosActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_VerContratos, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 20, 140, 50));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_add.png"))); // NOI18N
        BTN_Nuevo.setText("      NUEVO");
        BTN_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_NuevoActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 150, 50));

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
        jPanel3.add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 300, 140, 50));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_cancel_cogs.png"))); // NOI18N
        BTN_Modificar.setText("    MODIFICAR");
        BTN_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ModificarActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, 165, 48));

        BTN_Desactivar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Desactivar.setText("DESACTIVAR");
        BTN_Desactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_DesactivarActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Desactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 300, 170, 50));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(0, 0, 204))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jCheckBoxListarInactivos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBoxListarInactivos.setText("Listar Contratos Desactivados");
        jCheckBoxListarInactivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxListarInactivosActionPerformed(evt);
            }
        });
        jPanel4.add(jCheckBoxListarInactivos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 190, 30));

        jCheckBoxActivar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jCheckBoxActivar.setText("Reactivar Contratos");
        jCheckBoxActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxActivarActionPerformed(evt);
            }
        });
        jPanel4.add(jCheckBoxActivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 140, -1));

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 120, 210, 120));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 710));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarComboTurno () {
        try {
        ResultSet rs = CO.combobox_listarTurnos(); //Método de la clase
        jComboBox_Turno.removeAllItems();
        jComboBox_Turno.addItem("<<Seleccionar>>"); //opción por defecto

        while (rs.next()) {
            jComboBox_Turno.addItem(rs.getString("Turno"));
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar Turno: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    
    private void cargarComboTipoContrato () {
        try {
        ResultSet rs = CO.combobox_listarTipoContrato(); //Método de la clase
        jComboBox_TipodeContrato.removeAllItems();
        jComboBox_TipodeContrato.addItem("<<Seleccionar>>"); //Opción por defecto

        while (rs.next()) {
            jComboBox_TipodeContrato.addItem(rs.getString("Tipo de Contrato"));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar contrato: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    
    private void cargarComboCargo () {
         try {
        ResultSet rs = CO.combobox_listarCargo(); //Método de la clase
        jComboBox_Cargo.removeAllItems();
        jComboBox_Cargo.addItem("<<Seleccionar>>"); //Opción por defecto

        while (rs.next()) {
            jComboBox_Cargo.addItem(rs.getString("Nombre de Cargo"));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar ciclos: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }           
    }       
    
    private void cargarComboEmpleado () {
        try {
        ResultSet rs = CO.combobox_listarEmpleado(); //Método de la clase
        jComboBox_Empleado.removeAllItems();
        jComboBox_Empleado.addItem("<<Seleccionar>>"); //opción por defecto

        while (rs.next()) {
            jComboBox_Empleado.addItem(rs.getString("Empleado"));
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar Empleado: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    private void JTABLE_Mant_ContratoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_ContratoMouseClicked
       int fila = JTABLE_Mant_Contrato.getSelectedRow();

    if (fila != -1) {

        // 📌 1. OBTENER DATOS DE LA TABLA
        String idContrato = modeloTablaContrato.getValueAt(fila, 0).toString();
        String descripcion = modeloTablaContrato.getValueAt(fila, 1).toString();
        String fecha = modeloTablaContrato.getValueAt(fila, 2).toString();
        String turno = modeloTablaContrato.getValueAt(fila, 3).toString();
        String tipoContrato = modeloTablaContrato.getValueAt(fila, 5).toString();
        String cargo = modeloTablaContrato.getValueAt(fila, 6).toString();
        String estado = modeloTablaContrato.getValueAt(fila, 7).toString();

        // 📌 2. CARGAR DATOS AL FORMULARIO
        txtICodigoContrato.setText(idContrato);
        txtDescripcion.setText(descripcion);

        // 📌 3. FECHA (convertir String → Date)
        try {
            java.util.Date fechaConvertida = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
            txtFechaContrato.setDate(fechaConvertida);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Error al convertir la fecha: " + e.getMessage());
        }

        // 📌 4. COMBOBOX
        jComboBox_Empleado.setSelectedItem(turno);
        jComboBox_TipodeContrato.setSelectedItem(tipoContrato);
        jComboBox_Cargo.setSelectedItem(cargo);

        // 📌 5. ESTADO (si manejas activo/inactivo)
        if (estado.equalsIgnoreCase("Activo")) {
            jCheckBoxActivar.setSelected(true);
        } else {
            jCheckBoxActivar.setSelected(false);
        }

        // 📌 6. ACTIVAR BOTONES
        BTN_Modificar.setEnabled(true);
        BTN_Desactivar.setEnabled(true);

        // 📌 7. BLOQUEAR CAMPOS CLAVE (opcional pero recomendado)
        txtICodigoContrato.setEnabled(false);
    }
    }//GEN-LAST:event_JTABLE_Mant_ContratoMouseClicked



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

    private void TXT_BuscarPorContratoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarPorContratoKeyReleased
   try {
    String texto = TXT_BuscarPorContrato.getText().trim();

    // Si está vacío → mostrar todo
    if (texto.isEmpty()) {
        mostrarDatosTablaContratos();
        return;
    }

    // Limpiar tabla
    modeloTablaContrato.setRowCount(0);

    // Método que debes tener en tu DAO
    ResultSet rs = CO.buscarContrato(texto);

    boolean hayResultados = false;

    while (rs.next()) {
        hayResultados = true;

        Object[] fila = new Object[10];

        fila[0] = rs.getInt("ID");
        fila[1] = rs.getString("Tipo de Contrato");
        fila[2] = rs.getString("Empleado");
        fila[3] = rs.getString("Cargo");
        fila[4] = rs.getString("Descripción");
        fila[5] = rs.getString("Fecha de Contrato");
        fila[6] = rs.getString("Turno");
        fila[7] = rs.getString("Horario Inicio");
        fila[8] = rs.getString("Horario Final");

        int estado = rs.getInt("Estado");
        fila[9] = (estado == 1) ? "Activo" : "Inactivo";

        modeloTablaContrato.addRow(fila);
    }

    if (!hayResultados) {
        JOptionPane.showMessageDialog(this,
                "No se encontraron resultados para la búsqueda.",
                "Sin resultados",
                JOptionPane.INFORMATION_MESSAGE);
    }

} catch (SQLException e) {
    JOptionPane.showMessageDialog(this,
            "Error al buscar contratos: " + e.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE);
}
    }//GEN-LAST:event_TXT_BuscarPorContratoKeyReleased

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




    private void TXT_BuscarPorContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_BuscarPorContratoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_BuscarPorContratoActionPerformed

    private void BTN_VerContratosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerContratosActionPerformed
        this.mostrarDatosTablaContratos();
    }//GEN-LAST:event_BTN_VerContratosActionPerformed

    private void jComboBox_TipodeContratoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_TipodeContratoActionPerformed

    }//GEN-LAST:event_jComboBox_TipodeContratoActionPerformed

    private void jComboBox_TipodeContratoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox_TipodeContratoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_TipodeContratoKeyReleased

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void jComboBox_CargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_CargoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_CargoActionPerformed

    private void jComboBox_CargoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox_CargoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_CargoKeyReleased

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
        this.limpiarFormulario();
        BTN_Guardar.setEnabled(true);
        jCheckBoxActivar.setEnabled(false);

    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed
    try {
        // 📌 1. VALIDAR Y OBTENER DATOS (YA VALIDA TODO)
        DatosContrato datos = validarYObtenerDatosContrato();

        // 📌 2. VALIDACIÓN CLAVE QUE TE FALTABA 🔥 (EMPLEADO)
        if (datos.idEmpleado <= 0) {
            throw new IllegalArgumentException("Debe seleccionar un empleado válido.");
        }

        // 📌 3. INSERTAR
        CO.insertarContrato(
                datos.descripcion,
                datos.fecha,
                datos.idTurno,
                datos.idEmpleado,
                datos.idTipoContrato,
                datos.idCargo
        );

        // 📌 4. MENSAJE
        JOptionPane.showMessageDialog(this,
                "Contrato registrado correctamente.",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);

        // 📌 5. REFRESCAR
        mostrarDatosTablaContratos();

        // 📌 6. LIMPIAR
        limpiarFormulario();

        BTN_Guardar.setEnabled(false);

    } catch (IllegalArgumentException ex) {

        JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Validación",
                JOptionPane.WARNING_MESSAGE);

    } catch (SQLException ex) { // 🔥 ESTE TE FALTABA

        JOptionPane.showMessageDialog(this,
                "Error al guardar contrato:\n" + ex.getMessage(),
                "Error de base de datos",
                JOptionPane.ERROR_MESSAGE);

    } catch (HeadlessException ex) { // 🔥 fallback profesional

        JOptionPane.showMessageDialog(this,
                "Error inesperado:\n" + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed

    try {
        // 📌 1. VALIDAR ID
        if (txtICodigoContrato.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("Seleccione un contrato de la tabla.");
        }

        int idContrato = Integer.parseInt(txtICodigoContrato.getText().trim());

        if (idContrato <= 0) {
            throw new IllegalArgumentException("ID de contrato inválido.");
        }

        // 📌 2. OBTENER DATOS
        DatosContrato datos = validarYObtenerDatosContrato();

        // 📌 3. VALIDACIÓN EMPLEADO 🔥
        if (datos.idEmpleado <= 0) {
            throw new IllegalArgumentException("Debe seleccionar un empleado válido.");
        }

        // 📌 4. CONFIRMACIÓN
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Desea modificar este contrato?",
                "Confirmar modificación",
                JOptionPane.YES_NO_OPTION);

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        // 📌 5. ACTUALIZAR
        CO.modificarContrato(
                idContrato,
                datos.descripcion,
                datos.fecha,
                datos.idTurno,
                datos.idEmpleado,
                datos.idTipoContrato,
                datos.idCargo,
                datos.estado
        );

        // 📌 6. MENSAJE
        JOptionPane.showMessageDialog(this,
                "Contrato modificado correctamente.",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);

        // 📌 7. REFRESCAR
        mostrarDatosTablaContratos();

        // 📌 8. LIMPIAR
        limpiarFormulario();

        BTN_Modificar.setEnabled(false);

    } catch (NumberFormatException ex) {

        JOptionPane.showMessageDialog(this,
                "Código de contrato inválido.",
                "Error",
                JOptionPane.ERROR_MESSAGE);

    } catch (IllegalArgumentException ex) {

        JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Validación",
                JOptionPane.WARNING_MESSAGE);

    } catch (SQLException ex) { // 🔥 ESTE ES CLAVE

        JOptionPane.showMessageDialog(this,
                "Error al actualizar contrato:\n" + ex.getMessage(),
                "Error de base de datos",
                JOptionPane.ERROR_MESSAGE);

    } catch (HeadlessException ex) {

        JOptionPane.showMessageDialog(this,
                "Error inesperado:\n" + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void BTN_DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DesactivarActionPerformed
        try {
        // 📌 1. OBTENER ID DEL CONTRATO
        if (txtICodigoContrato.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Debe seleccionar un contrato de la tabla.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        int idContrato = Integer.parseInt(txtICodigoContrato.getText().trim());

        if (idContrato <= 0) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un contrato válido.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 📌 2. CONFIRMACIÓN
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea desactivar este contrato?",
                "Confirmar acción",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        // 📌 3. LLAMAR AL DAO (TU PROCEDURE Desactivar_Contrato)
        CO.darBajaUsuario(idContrato);

        // 📌 4. MENSAJE
        JOptionPane.showMessageDialog(this,
                "Contrato desactivado correctamente.",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);

        // 📌 5. REFRESCAR TABLA
        mostrarDatosTablaContratos();

        // 📌 6. LIMPIAR FORMULARIO
        limpiarFormulario();

        // 📌 7. DESACTIVAR BOTONES
        BTN_Modificar.setEnabled(false);
        BTN_Desactivar.setEnabled(false);

    } catch (NumberFormatException ex) {

        JOptionPane.showMessageDialog(this,
                "Código de contrato inválido.",
                "Error",
                JOptionPane.ERROR_MESSAGE);

    } catch (SQLException ex) {

        JOptionPane.showMessageDialog(this,
                "Error al desactivar contrato:\n" + ex.getMessage(),
                "Error de base de datos",
                JOptionPane.ERROR_MESSAGE);

    } catch (IllegalArgumentException ex) {

        JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Validación",
                JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_BTN_DesactivarActionPerformed


    private void jCheckBoxListarInactivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxListarInactivosActionPerformed
        this.listarContratosInactivos();
        this.BTN_Desactivar.setEnabled(false);

        this.BTN_Guardar.setEnabled(false);
        this.BTN_Modificar.setEnabled(false);
        this.BTN_VerContratos.setEnabled(true);



    }//GEN-LAST:event_jCheckBoxListarInactivosActionPerformed

    private void jCheckBoxActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxActivarActionPerformed
        // 📌 1. Obtener fila seleccionada
    int filaSeleccionada = JTABLE_Mant_Contrato.getSelectedRow();

    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this,
                "Seleccione un contrato para reactivarlo.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);

        jCheckBoxActivar.setSelected(false);
        return;
    }

    // 📌 2. Obtener datos de la tabla (SEGÚN TU ORDEN REAL)
    int idContrato = Integer.parseInt(
            JTABLE_Mant_Contrato.getValueAt(filaSeleccionada, 0).toString());

    String descripcion = JTABLE_Mant_Contrato.getValueAt(filaSeleccionada, 1).toString().trim();

    String estadoActual = JTABLE_Mant_Contrato.getValueAt(filaSeleccionada, 7).toString().trim();

    // 📌 3. Validar si ya está activo
    if (estadoActual.equalsIgnoreCase("Activo")) {
        JOptionPane.showMessageDialog(this,
                "El contrato '" + descripcion + "' ya está activo.",
                "Aviso",
                JOptionPane.INFORMATION_MESSAGE);

        jCheckBoxActivar.setSelected(false);
        return;
    }

    // 📌 4. Confirmación
    int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Desea reactivar el contrato:\n" + descripcion + "?",
            "Confirmar Reactivación",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);

    if (confirmacion == JOptionPane.YES_OPTION) {

        try {
            // 📌 5. LLAMAR AL DAO (🔥 debes tener este método)
            CO.reactivarContrato(idContrato);

            JOptionPane.showMessageDialog(this,
                    "¡Contrato reactivado correctamente!",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            // 📌 6. SINCRONIZAR INTERFAZ
            jCheckBoxListarInactivos.setSelected(false);
            jCheckBoxActivar.setSelected(false);

            listarContratosInactivos();   // 🔥 tu método nuevo
            limpiarFormulario();

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(this,
                    "Error al reactivar contrato:\n" + ex.getMessage(),
                    "Error de Base de Datos",
                    JOptionPane.ERROR_MESSAGE);

            jCheckBoxActivar.setSelected(false);
        }

    } else {
        jCheckBoxActivar.setSelected(false);
    }
    }//GEN-LAST:event_jCheckBoxActivarActionPerformed

    private void jComboBox_EmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_EmpleadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_EmpleadoActionPerformed

    private void jComboBox_EmpleadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox_EmpleadoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_EmpleadoKeyReleased

    private void jComboBox_TurnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_TurnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_TurnoActionPerformed

    private void jComboBox_TurnoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox_TurnoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_TurnoKeyReleased

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
} catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
    java.util.logging.Logger.getLogger(Frm_Contrato.class.getName())
            .log(java.util.logging.Level.SEVERE, null, ex);
}

        /* Create and display the form */
java.awt.EventQueue.invokeLater(() -> {
    try {
        new Frm_Contrato().setVisible(true);
    } catch (Exception ex) {
        javax.swing.JOptionPane.showMessageDialog(null,
                "Error al iniciar: " + ex.getMessage());
    }
});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Cerrar;
    private javax.swing.JButton BTN_Desactivar;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_PDF;
    private javax.swing.JButton BTN_VerContratos;
    private javax.swing.JTable JTABLE_Mant_Contrato;
    private javax.swing.JTextField TXT_BuscarPorContrato;
    private javax.swing.JCheckBox jCheckBoxActivar;
    private javax.swing.JCheckBox jCheckBoxListarInactivos;
    private javax.swing.JComboBox<String> jComboBox_Cargo;
    private javax.swing.JComboBox<String> jComboBox_Empleado;
    private javax.swing.JComboBox<String> jComboBox_TipodeContrato;
    private javax.swing.JComboBox<String> jComboBox_Turno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JScrollPane scrollTabla;
    private javax.swing.JTextField txtDescripcion;
    private com.toedter.calendar.JDateChooser txtFechaContrato;
    private javax.swing.JTextField txtICodigoContrato;
    // End of variables declaration//GEN-END:variables

    
 private void limpiarFormulario() {

    // 📌 1. CAMPOS DE TEXTO
    txtICodigoContrato.setText("");
    txtDescripcion.setText("");

    // 📌 2. FECHA
    txtFechaContrato.setDate(null);

    // 📌 3. COMBOBOX
    jComboBox_Empleado.setSelectedIndex(0);
    jComboBox_TipodeContrato.setSelectedIndex(0);
    jComboBox_Cargo.setSelectedIndex(0);

    // 📌 4. BUSCADOR
    TXT_BuscarPorContrato.setText("");
    TXT_BuscarPorContrato.requestFocus();

    // 📌 5. ESTADO (opcional)
    jCheckBoxActivar.setSelected(false);

    // 📌 6. BOTONES
    BTN_Modificar.setEnabled(false);
    BTN_Desactivar.setEnabled(false);
    BTN_Guardar.setEnabled(false);

    // 📌 7. ACTIVAR CAMPOS (por si estaban bloqueados)
    txtICodigoContrato.setEnabled(true);
    txtDescripcion.setEnabled(true);
    txtFechaContrato.setEnabled(true);
}
    
    
    
    
    
private DatosContrato validarYObtenerDatosContrato() throws IllegalArgumentException {

    DatosContrato datos = new DatosContrato();

    // 📌 1. DESCRIPCIÓN
    String descripcion = txtDescripcion.getText().trim();

    if (descripcion.isEmpty()) {
        throw new IllegalArgumentException("La descripción no puede estar vacía.");
    }

    datos.descripcion = descripcion;

    // 📌 2. FECHA (CORREGIDO)
    java.util.Date fechaUtil = txtFechaContrato.getDate(); // ✅ CORRECTO

    if (fechaUtil == null) {
        throw new IllegalArgumentException("Debe seleccionar una fecha.");
    }

    java.sql.Date fecha = new java.sql.Date(fechaUtil.getTime());

    // Validar fecha futura
    if (fecha.after(new java.sql.Date(System.currentTimeMillis()))) {
        throw new IllegalArgumentException("La fecha no puede ser futura.");
    }

    datos.fecha = fecha;

    // 📌 3. VALIDAR COMBOS
// 📌 3. VALIDAR COMBOS
String turno = jComboBox_Turno.getSelectedItem().toString();
String tipoContrato = jComboBox_TipodeContrato.getSelectedItem().toString();
String cargo = jComboBox_Cargo.getSelectedItem().toString();

// 🔥 AQUÍ AGREGAS ESTO
String empleado = jComboBox_Empleado.getSelectedItem().toString();

// VALIDACIÓN
if (turno.equals("<<Seleccionar>>") ||
    tipoContrato.equals("<<Seleccionar>>") ||
    cargo.equals("<<Seleccionar>>") ||
    empleado.equals("<<Seleccionar>>")) {

    throw new IllegalArgumentException("Debe seleccionar turno, tipo de contrato, cargo y empleado.");
}

    try {
        // 📌 4. OBTENER IDS DESDE DAO (como ya vienes trabajando)
        datos.idTurno = CO.obtenerCodigoTurno(turno);
        datos.idTipoContrato = CO.obtenerCodigoTipoContrato(tipoContrato);
        datos.idCargo = CO.obtenerCodigoCargo(cargo);
        datos.idEmpleado = CO.obtenerCodigoEmpleado(empleado);
        
    } catch (SQLException e) {
        throw new IllegalArgumentException("Error al obtener IDs: " + e.getMessage());
    }

    // 📌 5. VALIDAR IDS
    if (datos.idTurno == -1 || datos.idTipoContrato == -1 || datos.idCargo == -1) {
        throw new IllegalArgumentException("Error en los datos seleccionados.");
    }

    // 📌 6. ESTADO (por defecto activo)
    datos.estado = 1;

    return datos;
}
    
    private void mostrarDatosTablaContratos() {
    try {
        // Limpiar tabla
        modeloTablaContrato.setRowCount(0);

        // Obtener datos desde la BD (tu método listarContrato)
        ResultSet rs = CO.listarContrato();

        while (rs.next()) {

            Object[] fila = new Object[10];

            fila[0] = rs.getInt("ID");                       // ID (puedes ocultarlo)
            fila[1] = rs.getString("Tipo de Contrato");
            fila[2] = rs.getString("Empleado");
            fila[3] = rs.getString("Cargo");
            fila[4] = rs.getString("Descripción");
            fila[5] = rs.getString("Fecha de Contrato");
            fila[6] = rs.getString("Turno");
            fila[7] = rs.getString("Horario Inicio");
            fila[8] = rs.getString("Horario Final");

            // Convertir estado a texto
            int estado = rs.getInt("Estado");
            fila[9] = (estado == 1) ? "Activo" : "Inactivo";

            modeloTablaContrato.addRow(fila);
        }

    } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Sin registros",
                JOptionPane.INFORMATION_MESSAGE);

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this,
                "Error al mostrar los contratos: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}    
    public void listarContratosInactivos() {

    // 📌 Activar ordenamiento en la tabla
    JTABLE_Mant_Contrato.setAutoCreateRowSorter(true);

    // 📌 Limpiar tabla
    modeloTablaContrato.setRowCount(0);

    try {
        ResultSet rs = CO.listarContratoInactivo(); // tu DAO

        while (rs.next()) {

            Object[] fila = new Object[10];

            fila[0] = rs.getInt("ID");                       // ID (puedes ocultarlo)
            fila[1] = rs.getString("Tipo de Contrato");
            fila[2] = rs.getString("Empleado");
            fila[3] = rs.getString("Cargo");
            fila[4] = rs.getString("Descripción");
            fila[5] = rs.getString("Fecha de Contrato");
            fila[6] = rs.getString("Turno");
            fila[7] = rs.getString("Horario Inicio");
            fila[8] = rs.getString("Horario Final");

            // Convertir estado a texto
            int estado = rs.getInt("Estado");
            fila[9] = (estado == 1) ? "Activo" : "Inactivo";

            modeloTablaContrato.addRow(fila);
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this,
                "Error al mostrar contratos:\n" + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    }


