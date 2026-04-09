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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.table.TableColumnModel;
import logic.dao.ClienteMethod;

//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFRow;

/*pdf*/
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.*;
//import java.text.SimpleDateFormat;
//import java.util.Date;
public class Frm_Reserva extends javax.swing.JFrame {

    DefaultTableModel modeloTablaReserva = new DefaultTableModel();
    //Objeto conexión a la base de datos
    ReservaMethod methods;

    SpinnerDateModel modelInicio;
    JSpinner.DateEditor editorInicio;
    // Formato para el día (coincide con dd/MM/yyyy de tu vista)
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");

// Formato para las horas (coincide con HH:mm de tu vista)
    SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");

// Formato para el registro (si incluye hora usa dd/MM/yyyy HH:mm:ss)
    SimpleDateFormat formatoRegistro = new SimpleDateFormat("dd/MM/yyyy");



    public Frm_Reserva() {
        FlatLightLaf.setup();
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Mantenimiento de las Reservas");


        ImageIcon icono = new ImageIcon(getClass().getResource("/assets/icon_user.png"));
        // 2. Extraemos la imagen del objeto ImageIcon
        Image imagen = icono.getImage();
        // 3. Lo asignamos a la ventana
        this.setIconImage(imagen);

        this.methods = new ReservaMethod();

        // Títulos que verá el usuario
            // | DNI | Nombres y Apellidos | Nro Mesa | Inicio | Fin | PAX Nro Personas | Registro |
        String[] header = {"ID","Cliente", "Mesa","Día de Reserva","H. Entrada", "H. Salida", "Pax", "F. Registro","Observaciones"};
                         // ID   DNI    CLIENTE                                                 PAX
        // Modelo para Hora Inicio
        SpinnerDateModel modelInicio = new SpinnerDateModel();
        spnHoraInicio.setModel(modelInicio);
        JSpinner.DateEditor editorInicio = new JSpinner.DateEditor(spnHoraInicio, "HH:mm");
        spnHoraInicio.setEditor(editorInicio);

        // Modelo para Hora Fin
        SpinnerDateModel modelFin = new SpinnerDateModel();
        spnHoraFin.setModel(modelFin);
        JSpinner.DateEditor editorFin = new JSpinner.DateEditor(spnHoraFin, "HH:mm");
        spnHoraFin.setEditor(editorFin);

        modeloTablaReserva.setColumnIdentifiers(header);
        JTABLE_Mant_Reserva.setModel(modeloTablaReserva);

        TableColumnModel colModel = JTABLE_Mant_Reserva.getColumnModel();


    // ID: Lo ocultamos
    colModel.getColumn(0).setPreferredWidth(0);
    colModel.getColumn(0).setMinWidth(0);
    colModel.getColumn(0).setMaxWidth(0);

    // Cliente: Largo (Cubre Apellidos y Nombres)
    colModel.getColumn(1).setPreferredWidth(200);
    colModel.getColumn(1).setMaxWidth(2500);

    // Mesa: Pequeño
    colModel.getColumn(2).setPreferredWidth(80);

    // H. Entrada y H. Salida -> Pequeño
    colModel.getColumn(3).setPreferredWidth(100);

    // PAX : Pequeño
    colModel.getColumn(4).setPreferredWidth(80);

    // F. Registro -> Pequeño
    colModel.getColumn(5).setPreferredWidth(100);
    
        colModel.getColumn(6).setPreferredWidth(100);
    colModel.getColumn(6).setMinWidth(10);
    colModel.getColumn(6).setMaxWidth(600);

    // 3. Extras de la Tabla
    JTABLE_Mant_Reserva.getTableHeader().setReorderingAllowed(false); // No mover columnas
    JTABLE_Mant_Reserva.setRowHeight(25); // Filas más altas para que respire el diseño


        txtObservaciones.setEditable(false);



        //Desactivar button
        BTN_Nuevo.setEnabled(false);
        BTN_Desactivar.setEnabled(false);
        BTN_Guardar.setEnabled(false);
        BTN_Cancel.setVisible(false);
        BTN_Modificar.setEnabled(false);
        txtcodigoReserva.setEnabled(false);




        configurarSpinners();



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
        BTN_Cancel = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_Reserva = new javax.swing.JTable();
        BTN_PDF = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();
        BTN_Cerrar1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXT_BuscarMesas = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        BTN_Desactivar1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtcodigoReserva = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        jPanelClientes = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        comboCliente = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        spnCantidad = new javax.swing.JSpinner();
        jLabel17 = new javax.swing.JLabel();
        comboMesas = new javax.swing.JComboBox<>();
        jPanelRegistroReserva1 = new javax.swing.JPanel();
        dateDia = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        spnHoraInicio = new javax.swing.JSpinner();
        spnHoraFin = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtFechaRegistro = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MANTENIMIENTO DE RESERVA");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 830, 30));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BTN_VerPlatos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_VerPlatos.setText("VER RESERVAS");
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

        BTN_Back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_back.png"))); // NOI18N
        BTN_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_BackActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Back, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 650, 50, 50));

        BTN_Cancel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_cancel.png"))); // NOI18N
        BTN_Cancel.setText("     CANCELAR");
        BTN_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CancelActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(745, 94, 150, 48));

        JTABLE_Mant_Reserva.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_Reserva.setForeground(new java.awt.Color(0, 0, 204));
        JTABLE_Mant_Reserva.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Mant_Reserva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_ReservaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Mant_Reserva);

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
        jLabel4.setText("Buscar Reservas :");
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

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel2.setText("ID:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 30, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Observaciones");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 230, 150, -1));

        txtcodigoReserva.setEditable(false);
        txtcodigoReserva.setBackground(new java.awt.Color(255, 255, 255));
        txtcodigoReserva.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcodigoReserva.setForeground(new java.awt.Color(0, 0, 204));
        txtcodigoReserva.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcodigoReserva.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtcodigoReserva, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 90, 30));

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        jScrollPane2.setViewportView(txtObservaciones);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, 380, 60));

        jPanelClientes.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Registro de Reserva   ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanelClientes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("Cliente:*");
        jPanelClientes.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 90, -1));

        comboCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanelClientes.add(comboCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 230, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Cantidad de Personas:*");
        jPanelClientes.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 170, -1));
        jPanelClientes.add(spnCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 100, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Nro de Mesa:*");
        jPanelClientes.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 90, -1));

        comboMesas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanelClientes.add(comboMesas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 170, -1));

        jPanel1.add(jPanelClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 270, 260));

        jPanelRegistroReserva1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Fecha y Hora   ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N
        jPanelRegistroReserva1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanelRegistroReserva1.add(dateDia, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 170, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Fin");
        jPanelRegistroReserva1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, 80, -1));
        jPanelRegistroReserva1.add(spnHoraInicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 110, -1));
        jPanelRegistroReserva1.add(spnHoraFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 110, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Día de reserva");
        jPanelRegistroReserva1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 100, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Inicio");
        jPanelRegistroReserva1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 80, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("Fecha de Registro:*");
        jPanelRegistroReserva1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 130, -1));

        txtFechaRegistro.setEnabled(false);
        txtFechaRegistro.setRequestFocusEnabled(false);
        jPanelRegistroReserva1.add(txtFechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 170, -1));

        jPanel1.add(jPanelRegistroReserva1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 30, 380, 180));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 720, 330));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Rellenar el ComboBox
    private void cargarComboCliente() {
        try {
          comboCliente.removeAllItems();
          comboCliente.addItem("<<Seleccionar>>"); // Opción por defecto
          ResultSet rs = methods.comboListarClientes();
          while (rs.next()) {
            comboCliente.addItem(rs.getString("Info_Cliente"));
          }
        } catch (SQLException e) {
          JOptionPane.showMessageDialog(this, "Error al cargar los Clientes: " + e.getMessage());
        }
      }


    private void cargarMesas(){
                try {
          comboMesas.removeAllItems();
          comboMesas.addItem("<<Seleccionar>>"); // Opción por defecto
          ResultSet rs = methods.comboListarMesas();
          while (rs.next()) {
            comboMesas.addItem(rs.getString("Número de Mesa"));
          }
        } catch (SQLException e) {
          JOptionPane.showMessageDialog(this, "Error al cargar las Mesas: " + e.getMessage());
        }
    }


    private void limpiarReserva () {
        txtObservaciones.setText("");
        txtcodigoReserva.setText("");


        comboCliente.setSelectedIndex(0);
        comboMesas.setSelectedIndex(0);
        BTN_Guardar.setEnabled(true);
        BTN_Modificar.setEnabled(false);
        BTN_Desactivar.setEnabled(false);
        BTN_VerPlatos.setEnabled(false);

    }



    ////    CLICKEO EN LA TABLA --> -->
    private void JTABLE_Mant_ReservaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_ReservaMouseClicked
        if (!JTABLE_Mant_Reserva.isEnabled()) {
            return;
        }
        txtObservaciones.setEditable(true);
        comboCliente.setEnabled(true);
        comboMesas.setEnabled(true);
        spnCantidad.setEnabled(true);
        spnHoraFin.setEnabled(true);
        spnHoraInicio.setEnabled(true);
        dateDia.setEnabled(true);

        int selectRow = JTABLE_Mant_Reserva.getSelectedRow();
        if (selectRow >= 0) {
            String codigo = JTABLE_Mant_Reserva.getValueAt(selectRow, 0).toString().trim();
            String cliente = JTABLE_Mant_Reserva.getValueAt(selectRow, 1).toString().trim();
            String mesa = JTABLE_Mant_Reserva.getValueAt(selectRow, 2).toString().trim();
            String diaReserva = JTABLE_Mant_Reserva.getValueAt(selectRow, 3).toString().trim();
            String hInicio = JTABLE_Mant_Reserva.getValueAt(selectRow, 4).toString().trim();
            String hFinal = JTABLE_Mant_Reserva.getValueAt(selectRow, 5).toString().trim();
            String pax = JTABLE_Mant_Reserva.getValueAt(selectRow, 6).toString().trim();
            String F_Regitro = JTABLE_Mant_Reserva.getValueAt(selectRow, 7).toString().trim();
            String Observaciones = JTABLE_Mant_Reserva.getValueAt(selectRow, 8).toString().trim();
















            int codigoInt = Integer.parseInt(codigo);
            int paxInt = Integer.parseInt(pax);
            try {
    // 1. Convertir Día de Reserva
    Date dateReserva = formatoFecha.parse(diaReserva);
    dateDia.setDate(dateReserva); // Setear al JDateChooser

    // 2. Convertir Hora Inicio y Fin
    Date dateInicio = formatoHora.parse(hInicio);
    spnHoraInicio.setValue(dateInicio); // Setear al JSpinner

    Date dateFin = formatoHora.parse(hFinal);
    spnHoraFin.setValue(dateFin); // Setear al JSpinner

    Date dateRegistro = formatoFecha.parse(F_Regitro);
    txtFechaRegistro.setDate(dateRegistro); // Setear al JDateChooser


} catch (ParseException e) {
    System.err.println("Error en el formato de fecha: " + e.getMessage());
}

            spnCantidad.setValue(paxInt);


            txtcodigoReserva.setText(codigo);
            txtObservaciones.setText(Observaciones);
            //String clienteOriginal = cliente;
            boolean find = false;
            for (int i=0;i<comboCliente.getItemCount();i++){
                String item = comboCliente.getItemAt(i).trim();
                if (item.equalsIgnoreCase(cliente)){
                    comboCliente.setSelectedIndex(i);
                    find=true;
                    break;
                }
            }

            boolean find2 = false;
            for (int i=0;i<comboMesas.getItemCount();i++){
                String item = comboMesas.getItemAt(i).trim();
                if (item.equalsIgnoreCase(mesa)){
                    comboMesas.setSelectedIndex(i);
                    find2=true;
                    break;
                }
            }

        }


        txtObservaciones.setEditable(true);

        BTN_Guardar.setEnabled(false);
        BTN_VerPlatos.setEnabled(false);
        BTN_Modificar.setEnabled(true);
        BTN_Desactivar.setEnabled(true);

    }//GEN-LAST:event_JTABLE_Mant_ReservaMouseClicked





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
            }// Cierra el formulario actual
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
            txtObservaciones.setEditable(false);
            jComboBoxCategoria.setEnabled(false);

            JTABLE_Mant_Reserva.setEnabled(true);

        }*/
    }//GEN-LAST:event_BTN_CancelActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed

        txtcodigoReserva.setText("");
        comboCliente.setSelectedIndex(0);
        comboMesas.setSelectedIndex(0);
        //restaurarValoresDefault();
        txtObservaciones.setText("");
        


        txtObservaciones.setEditable(true);
        comboCliente.setEnabled(true);
        comboMesas.setEnabled(true);
        JTABLE_Mant_Reserva.clearSelection();
        JTABLE_Mant_Reserva.setEnabled(false);
         desbloquearCampos();

    // ... tu lógica de limpiar campos ...
    
    // Asignar la fecha actual del sistema
    dateDia.setDate(new java.util.Date()); 
    txtFechaRegistro.setDate(new java.util.Date());
    
    // ... resto de tu lógica (desbloquear campos, etc.) ...

        
        BTN_Guardar.setEnabled(true);
        BTN_Desactivar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        BTN_Nuevo.setVisible(false);
       // BTN_Cancel.setVisible(true);
        reactivarBotones(true);
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed

    try {
        // 1. Validar campos (Usando .getDate() para los JDateChooser)
        if (comboCliente.getSelectedIndex() <= 0 || comboMesas.getSelectedIndex() <= 0 || 
            dateDia.getDate() == null || txtFechaRegistro.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos obligatorios", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. OBTENCIÓN DE DATOS Y CONVERSIÓN DE TIEMPOS (Evita el error de formato)
        long fechaBaseLong = dateDia.getDate().getTime();
        java.util.Date horaIniDate = (java.util.Date) spnHoraInicio.getValue();
        java.util.Date horaFinDate = (java.util.Date) spnHoraFin.getValue();

        // Generamos Strings en formato yyyy-MM-dd HH:mm:ss exactos
        String hInicio = new java.sql.Timestamp(fechaBaseLong + horaIniDate.getTime()).toString();
        String hFinal = new java.sql.Timestamp(fechaBaseLong + horaFinDate.getTime()).toString();
        
        // Para la fecha de registro (JDateChooser)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fRegistro = sdf.format(txtFechaRegistro.getDate());
        
        String pax = spnCantidad.getValue().toString();
        String observaciones = txtObservaciones.getText().trim();

        // 3. Obtener IDs de la base de datos
        String clienteSel = (String) comboCliente.getSelectedItem();
        String mesaSel = (String) comboMesas.getSelectedItem();
        int idMesa = methods.obtenerIdMesaPorNumero(mesaSel);
        int idCliente = methods.obtenerIdClientePorConcatenado(clienteSel);

        // 4. Confirmar e Insertar
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea guardar la reserva?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            this.methods.insertarReserva(fRegistro, hInicio, hFinal, pax, observaciones, idCliente, idMesa);
            
            JOptionPane.showMessageDialog(this, "Reserva registrada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.MostrarReserva();
            this.limpiarReserva();
            this.bloquearCampos();
        }

    } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(this, ex.getMessage(), "Mesa Ocupada", JOptionPane.WARNING_MESSAGE);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }


    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
    
    String idReservaStr = txtcodigoReserva.getText().trim();
    if (idReservaStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Seleccione una reserva de la tabla.");
        return;
    }

    try {
        int idReserva = Integer.parseInt(idReservaStr);
        long fechaBaseLong = dateDia.getDate().getTime();
        
        // Conversión segura de horas a Timestamp String
        String hIni = new java.sql.Timestamp(fechaBaseLong + ((java.util.Date)spnHoraInicio.getValue()).getTime()).toString();
        String hFin = new java.sql.Timestamp(fechaBaseLong + ((java.util.Date)spnHoraFin.getValue()).getTime()).toString();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fReg = sdf.format(txtFechaRegistro.getDate());
        String pax = spnCantidad.getValue().toString();
        String observaciones = txtObservaciones.getText().trim();
        
        int idMesa = methods.obtenerIdMesaPorNumero(comboMesas.getSelectedItem().toString());
        int idCliente = methods.obtenerIdClientePorConcatenado(comboCliente.getSelectedItem().toString());

        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea modificar esta reserva?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            this.methods.modificarReserva(idReserva, fReg, hIni, hFin, pax, observaciones ,String.valueOf(idCliente), String.valueOf(idMesa));
            
            JOptionPane.showMessageDialog(this, "Reserva actualizada con éxito.");
            this.MostrarReserva();
            this.limpiarReserva();
            this.bloquearCampos();
        }
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error al modificar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_BTN_ModificarActionPerformed

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void BTN_DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DesactivarActionPerformed

  
    String codStr = txtcodigoReserva.getText().trim();
    if (codStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Seleccione una reserva para desactivar.");
        return;
    }

    int opcion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea anular esta reserva?", "Confirmar", JOptionPane.YES_NO_OPTION);
    if (opcion == JOptionPane.YES_OPTION) {
        try {
            this.methods.desactivarReserva(Integer.parseInt(codStr));
            JOptionPane.showMessageDialog(this, "Reserva anulada correctamente.");
            this.MostrarReserva();
            this.limpiarReserva();
            this.bloquearCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al desactivar reserva:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    }//GEN-LAST:event_BTN_DesactivarActionPerformed

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void BTN_VerPlatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerPlatosActionPerformed
        this.MostrarReserva();
        cargarComboCliente();
        cargarMesas();
        this.BTN_Nuevo.setEnabled(true);
        this.BTN_Guardar.setEnabled(false);
        this.BTN_Desactivar.setEnabled(false);
        this.BTN_Modificar.setEnabled(false);
        this.BTN_VerPlatos.setEnabled(false);
    }//GEN-LAST:event_BTN_VerPlatosActionPerformed

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
            java.util.logging.Logger.getLogger(Frm_Reserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_Reserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_Reserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_Reserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_Reserva().setVisible(true);
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
    private javax.swing.JTable JTABLE_Mant_Reserva;
    private javax.swing.JTextField TXT_BuscarMesas;
    private javax.swing.JComboBox<String> comboCliente;
    private javax.swing.JComboBox<String> comboMesas;
    private com.toedter.calendar.JDateChooser dateDia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelClientes;
    private javax.swing.JPanel jPanelRegistroReserva1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JSpinner spnHoraFin;
    private javax.swing.JSpinner spnHoraInicio;
    private com.toedter.calendar.JDateChooser txtFechaRegistro;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JTextField txtcodigoReserva;
    // End of variables declaration//GEN-END:variables

//Método para mostrar las plato menues
    public void MostrarReserva() {
        //Ordenar ASC, DESC
        JTABLE_Mant_Reserva.setAutoCreateRowSorter(true);
        //Limpiar la tabla antes de mostrar nuevos datos
        modeloTablaReserva.setRowCount(0);
        try {
            //Llama al método que retorna los datos de plato menues
            ResultSet rs = this.methods.listarReserva();
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("ID"),
                    rs.getString("Cliente Formateado"),
                    rs.getString("Mesa"),
                    rs.getString("Día de Reserva"),
                    rs.getString("Entrada"),
                    rs.getString("Salida"),
                    rs.getInt("Pax"),
                    rs.getString("Fecha de Registro"),
                    rs.getString("Observaciones")
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
            ResultSet rs = this.methods.buscarReserva(parametro);

            try (rs) {
                modeloTablaReserva.setRowCount(0);
            while (rs.next()) {
                /*
                CREATE OR REPLACE VIEW vista_reserva AS
SELECT
    r.id_reserva     AS `ID`,

    DATE_FORMAT(r.fecha_inicio, '%d/%m/%Y') AS `Día de Reserva`,

    TIME_FORMAT(r.fecha_inicio, '%H:%i') AS `Entrada`,
    TIME_FORMAT(r.fecha_fin, '%H:%i')    AS `Salida`,
    r.cantidad_personas AS `Pax`,
    m.numero_mesa       AS `Mesa`,
    CONCAT(c.nombre_cliente, ' ', c.apellido_cliente) AS `Cliente`,

    DATE_FORMAT(r.fecha_registro, '%d/%m/%Y') AS `Fecha de Registro`

FROM reserva r
INNER JOIN cliente c ON r.id_cliente = c.id_cliente
INNER JOIN mesa m ON r.id_mesa = m.id_mesa
WHERE r.estado = 1;
                */
                Object[] fila = {
                    rs.getInt("ID"),
                    rs.getString("Cliente Formateado"),
                    rs.getString("Mesa"),
                    rs.getString("Día de Reserva"),
                    rs.getString("Entrada"),
                    rs.getString("Salida"),
                    rs.getInt("Pax"),
                    rs.getString("Fecha de Registro"),
                    rs.getString("Observaciones")
                };
                modeloTablaReserva.addRow(fila);
            }
            }


        } catch (SQLException e) {
            System.err.println("Error silencioso en búsqueda: " + e.getMessage());
        }
    }


  private void configurarSpinners() {
    // 1. Obtener la hora actual redondeada a la decena
    Date horaRedondeada = redondearDecena(new Date());

    // 2. Aplicar el modelo con el salto de 10 y la hora inicial limpia
    configurarSaltoManual(spnHoraInicio, horaRedondeada);

    // Para la hora de fin, podemos poner la hora redondeada + 1 hora de diferencia
    Calendar calFin = Calendar.getInstance();
    calFin.setTime(horaRedondeada);
    calFin.add(Calendar.HOUR_OF_DAY, 1);
    configurarSaltoManual(spnHoraFin, calFin.getTime());
}

private void configurarSaltoManual(JSpinner spinner, Date horaInicial) {
    // Sobrescribimos el modelo para el salto de 10
    spinner.setModel(new SpinnerDateModel(horaInicial, null, null, Calendar.MINUTE) {
        @Override
        public Object getNextValue() {
            Calendar cal = Calendar.getInstance();
            cal.setTime(getDate());
            cal.add(Calendar.MINUTE, 10);
            return cal.getTime();
        }

        @Override
        public Object getPreviousValue() {
            Calendar cal = Calendar.getInstance();
            cal.setTime(getDate());
            cal.add(Calendar.MINUTE, -10);
            return cal.getTime();
        }
    });

    // Aplicamos el formato AM/PM
    spinner.setEditor(new JSpinner.DateEditor(spinner, "hh:mm a"));
}

private Date redondearDecena(Date fecha) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(fecha);

    int minutos = cal.get(Calendar.MINUTE);
    // Lógica: (Minutos / 10) redondeado * 10
    // Si quieres que siempre redondee HACIA ARRIBA (ej: 31 -> 40), usa Math.ceil
    int minutosRedondeados = (int) (Math.round(minutos / 10.0) * 10);

    if (minutosRedondeados == 60) {
        cal.add(Calendar.HOUR_OF_DAY, 1);
        cal.set(Calendar.MINUTE, 0);
    } else {
        cal.set(Calendar.MINUTE, minutosRedondeados);
    }

    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);

    return cal.getTime();
}

public void restaurarValoresDefault() {
    // 1. Obtener la fecha y hora actual redondeada
    // Usamos el método de redondeo que creamos antes para mantener la coherencia
    Date ahora = new Date();
    Date ahoraRedondeado = redondearDecena(ahora);

    // 2. Resetear JDateChooser al día de hoy
    dateDia.setDate(ahora);

    // 3. Resetear JSpinners (Inicio y Fin con 1 hora de diferencia)
    spnHoraInicio.setValue(ahoraRedondeado);

    Calendar calFin = Calendar.getInstance();
    calFin.setTime(ahoraRedondeado);
    calFin.add(Calendar.HOUR_OF_DAY, 1); // Por defecto 1 hora después
    spnHoraFin.setValue(calFin.getTime());

    // 4. Resetear JTextArea con la fecha de registro (Current Day / Time)
    // Usamos un formato legible para el restaurante
    /*SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    txtFechaRegistro.setText(sdf.format(ahora));*/
}


private void bloquearCampos() {
    // Bloquear controles de entrada
    txtcodigoReserva.setEditable(false);
    comboCliente.setEnabled(false);
    comboMesas.setEnabled(false);
    dateDia.setEnabled(false);
    spnHoraInicio.setEnabled(false);
    spnHoraFin.setEnabled(false);
    spnCantidad.setEnabled(false);
    txtObservaciones.setEditable(false);
    txtFechaRegistro.setEnabled(false);

    // Configurar botones
    BTN_Guardar.setEnabled(false);
    BTN_Modificar.setEnabled(false);
    BTN_Desactivar.setEnabled(false);
    
    // El botón Nuevo y Cancelar suelen alternar visibilidad
    BTN_Nuevo.setVisible(true);
    BTN_Cancel.setVisible(false);
    
    // Habilitar la tabla para que el usuario pueda seleccionar registros
    JTABLE_Mant_Reserva.setEnabled(true);
}


private void reactivarBotones(boolean esNuevo) {
    if (esNuevo) {
        // Si es una nueva reserva
        BTN_Guardar.setEnabled(true);
        BTN_Modificar.setEnabled(false);
        BTN_Desactivar.setEnabled(false);
    } else {
        // Si seleccionaste una de la tabla (para editar o borrar)
        BTN_Guardar.setEnabled(false);
        BTN_Modificar.setEnabled(true);
        BTN_Desactivar.setEnabled(true);
    }
    
    // Botones que siempre deberían estar activos en este punto
    BTN_Cancel.setVisible(true);
    BTN_Nuevo.setVisible(false);
    
}


private void desbloquearCampos() {
    comboCliente.setEnabled(true);
    comboMesas.setEnabled(true);
    spnCantidad.setEnabled(true);
    
    // Estos son los que necesitas activar según tu imagen
    dateDia.setEnabled(true); 
    spnHoraInicio.setEnabled(true);
    spnHoraFin.setEnabled(true);
    
    txtObservaciones.setEditable(true);
    // txtFechaRegistro suele quedar desactivado por ser automático
}


}
