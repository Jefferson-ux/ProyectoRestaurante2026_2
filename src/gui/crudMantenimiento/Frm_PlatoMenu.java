package gui.crudMantenimiento;

import com.formdev.flatlaf.FlatLightLaf;
import gui.menu.Frm_MenuPrincipal;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

   /*Método de Mesa*/
import logic.dao.PlatoMenuMethod;


  /*excel*/
import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.TableColumnModel;

//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFRow;

/*pdf*/
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.*;
//import java.text.SimpleDateFormat;
//import java.util.Date;

public class Frm_PlatoMenu extends javax.swing.JFrame {

    DefaultTableModel modeloTablaMesa = new DefaultTableModel();
    //Objeto conexión a la base de datos
    PlatoMenuMethod methods;

    public Frm_PlatoMenu() {
        FlatLightLaf.setup();
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Mantenimiento de los platos del Menú");

        ImageIcon icono = new ImageIcon(getClass().getResource("/assets/icon_user.png"));
        // 2. Extraemos la imagen del objeto ImageIcon
        Image imagen = icono.getImage();
        // 3. Lo asignamos a la ventana
        this.setIconImage(imagen);

        this.methods = new PlatoMenuMethod();

        String[] header = {"ID ", "Nombre del plato","Precio","Categoría","Descripción"};

        modeloTablaMesa.setColumnIdentifiers(header);
        JTABLE_Mant_Plato.setModel(modeloTablaMesa);

        TableColumnModel colModel = JTABLE_Mant_Plato.getColumnModel();

        txtNombrePlato.setEditable(false);
        txtPrecio.setEditable(false);
        jTextAreaDescripcion.setEditable(false);
        jComboBoxCategoria.setEnabled(false);


    // ID: Lo ocultamos o lo dejamos muy pequeño (según lo que dijo tu profe)
    colModel.getColumn(0).setPreferredWidth(0);
    colModel.getColumn(0).setMinWidth(35);
    colModel.getColumn(0).setMaxWidth(50);

    // Nombre: Espacio suficiente
    colModel.getColumn(1).setPreferredWidth(200);

    // Precio: Un ancho fijo pequeño para que no baile
    colModel.getColumn(2).setPreferredWidth(80);
    colModel.getColumn(2).setMaxWidth(100);

    // Categoría: Mediano
    colModel.getColumn(3).setPreferredWidth(120);

    // Descripción: ¡Que se lleve el resto del espacio!
    colModel.getColumn(4).setPreferredWidth(350);

    // 3. Extras de la Tabla
    JTABLE_Mant_Plato.getTableHeader().setReorderingAllowed(false); // No mover columnas
    JTABLE_Mant_Plato.setRowHeight(25); // Filas más altas para que respire el diseño


        //Desactivar button
        BTN_Nuevo.setEnabled(false);
        BTN_Desactivar.setEnabled(false);
        BTN_Guardar.setEnabled(false);
        BTN_Cancel.setVisible(false);
        BTN_Modificar.setEnabled(false);
        txtcodigoplato.setEnabled(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtcodigoplato = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxCategoria = new javax.swing.JComboBox<>();
        txtNombrePlato = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaDescripcion = new javax.swing.JTextArea();
        txtPrecio = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_Plato = new javax.swing.JTable();
        BTN_EXCEL = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXT_BuscarMesas = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        BTN_Desactivar1 = new javax.swing.JButton();
        BTN_Cerrar1 = new javax.swing.JButton();
        BTN_PDF = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        BTN_VerPlatos = new javax.swing.JButton();
        BTN_Desactivar = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Cancel = new javax.swing.JButton();
        BTN_Back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MANTENIMIENTO DE PLATOS DEL MENÚ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 830, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel2.setText("Codigo del Plato");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Descripción");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 150, -1));

        txtcodigoplato.setEditable(false);
        txtcodigoplato.setBackground(new java.awt.Color(255, 255, 255));
        txtcodigoplato.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcodigoplato.setForeground(new java.awt.Color(0, 0, 204));
        txtcodigoplato.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcodigoplato.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtcodigoplato, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 250, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Nombre del Plato*");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 220, -1));

        jComboBoxCategoria.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxCategoria.setForeground(new java.awt.Color(0, 0, 204));
        jPanel1.add(jComboBoxCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, 330, 30));

        txtNombrePlato.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtNombrePlato.setForeground(new java.awt.Color(0, 0, 204));
        txtNombrePlato.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNombrePlato.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtNombrePlato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombrePlatoActionPerformed(evt);
            }
        });
        txtNombrePlato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombrePlatoKeyTyped(evt);
            }
        });
        jPanel1.add(txtNombrePlato, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 40, 330, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Precio del Plato*");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 180, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Categoría*");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 140, -1));

        jTextAreaDescripcion.setColumns(20);
        jTextAreaDescripcion.setRows(5);
        jScrollPane2.setViewportView(jTextAreaDescripcion);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 630, 80));

        txtPrecio.setForeground(new java.awt.Color(0, 0, 204));
        txtPrecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtPrecio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel1.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 250, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 698, 300));

        JTABLE_Mant_Plato.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_Plato.setForeground(new java.awt.Color(0, 0, 204));
        JTABLE_Mant_Plato.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Mant_Plato.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_PlatoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Mant_Plato);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 400, 920, 220));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/excel.png"))); // NOI18N
        BTN_EXCEL.setText("     Exportar XLSX");
        BTN_EXCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_EXCELActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 650, 170, 50));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingresar el Nombre de la Facultad");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, 30));

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

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 920, 50));

        BTN_Cerrar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_close.png"))); // NOI18N
        BTN_Cerrar1.setText("     Cerrar");
        BTN_Cerrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_Cerrar1ActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_Cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 650, 165, 50));

        BTN_PDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pdf.png"))); // NOI18N
        BTN_PDF.setText("     Exportar PDF");
        BTN_PDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_PDFActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_PDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 650, 170, 50));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BTN_VerPlatos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_VerPlatos.setText("VER PLATOS DEL MENÚ");
        BTN_VerPlatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_VerPlatosActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_VerPlatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 30, 165, 50));

        BTN_Desactivar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Desactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_delete.png"))); // NOI18N
        BTN_Desactivar.setText("     QUITAR");
        BTN_Desactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_DesactivarActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Desactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 280, 165, 48));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_update.png"))); // NOI18N
        BTN_Modificar.setText("    MODIFICAR");
        BTN_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ModificarActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 219, 165, 48));

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
        jPanel3.add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 157, 165, 48));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_add.png"))); // NOI18N
        BTN_Nuevo.setText("      NUEVO");
        BTN_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_NuevoActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 94, 165, 48));

        BTN_Cancel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_cancel.png"))); // NOI18N
        BTN_Cancel.setText("     CANCELAR");
        BTN_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CancelActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 94, 165, 48));

        BTN_Back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_back.png"))); // NOI18N
        BTN_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_BackActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Back, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 650, 50, 50));

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
        txtcodigoplato.setText("");
        txtNombrePlato.setText("");
        jComboBoxCategoria.setSelectedIndex(0);
        BTN_Guardar.setEnabled(true);
        BTN_Modificar.setEnabled(false);
        BTN_Desactivar.setEnabled(false);
        BTN_VerPlatos.setEnabled(false);

    }



    ////    CLICKEO EN LA TABLA --> -->
    private void JTABLE_Mant_PlatoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_PlatoMouseClicked
        if (!JTABLE_Mant_Plato.isEnabled()) {
            return;
        }

        int selectRow = JTABLE_Mant_Plato.getSelectedRow();
        if (selectRow >= 0) {
            String codigo = JTABLE_Mant_Plato.getValueAt(selectRow, 0).toString().trim();
            String nombre_plato = JTABLE_Mant_Plato.getValueAt(selectRow, 1).toString().trim();
            String precio = JTABLE_Mant_Plato.getValueAt(selectRow, 2).toString().trim();
            String categoria = JTABLE_Mant_Plato.getValueAt(selectRow, 3).toString().trim();
            String descripcion = JTABLE_Mant_Plato.getValueAt(selectRow, 4).toString().trim();

            txtcodigoplato.setText(codigo);
            txtNombrePlato.setText(nombre_plato);
            txtPrecio.setText(precio);
            jTextAreaDescripcion.setText(descripcion);

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
        jTextAreaDescripcion.setEditable(true);

        BTN_Guardar.setEnabled(false);
        BTN_VerPlatos.setEnabled(false);
        BTN_Modificar.setEnabled(true);
        BTN_Desactivar.setEnabled(true);

    }//GEN-LAST:event_JTABLE_Mant_PlatoMouseClicked





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

    private void txtNombrePlatoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombrePlatoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombrePlatoKeyTyped

    private void BTN_Desactivar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_Desactivar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_Desactivar1ActionPerformed

    private void txtNombrePlatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombrePlatoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombrePlatoActionPerformed

    private void BTN_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_BackActionPerformed
        Frm_MenuPrincipal mainMenu = new Frm_MenuPrincipal();
        mainMenu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BTN_BackActionPerformed

    private void BTN_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CancelActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea cancelar la operación?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {

            BTN_Guardar.setEnabled(false);
            BTN_Desactivar.setEnabled(false);
            BTN_Modificar.setEnabled(false);
            BTN_Nuevo.setVisible(true);
            BTN_Cancel.setVisible(false);

            txtNombrePlato.setEditable(false);
            txtPrecio.setEditable(false);
            jTextAreaDescripcion.setEditable(false);
            jComboBoxCategoria.setEnabled(false);

            JTABLE_Mant_Plato.setEnabled(true);

        }
    }//GEN-LAST:event_BTN_CancelActionPerformed

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed

        txtcodigoplato.setText("");
        txtNombrePlato.setText("");
        txtPrecio.setText("");
        txtNombrePlato.setText("");
        jTextAreaDescripcion.setText("");

        txtNombrePlato.requestFocus();
        txtNombrePlato.setEditable(true);
        txtPrecio.setEditable(true);
        jComboBoxCategoria.setEnabled(false);
        jTextAreaDescripcion.setEditable(true);

        // 1. Limpiar la selección actual (Que no quede nada pintado de azul)
        JTABLE_Mant_Plato.clearSelection();

        // 2. Deshabilitar la tabla para que no se pueda hacer clic
        JTABLE_Mant_Plato.setEnabled(false);

        txtNombrePlato.setEditable(true);
        txtPrecio.setEditable(true);
        jTextAreaDescripcion.setEditable(true);
        jComboBoxCategoria.setEnabled(true);

        BTN_Guardar.setEnabled(true);
        BTN_Desactivar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        BTN_Nuevo.setVisible(false);
        BTN_Cancel.setVisible(true);
    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed

        // 1. Validar que el campo no esté vacío
        String nombre = txtNombrePlato.getText().trim();
        String precio = txtPrecio.getText().trim();
        String categoria = (String) jComboBoxCategoria.getSelectedItem();
        String descripcion = jTextAreaDescripcion.getText().trim();

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
            Logger.getLogger(Frm_PlatoMenu.class.getName()).log(Level.SEVERE, null, ex);
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
                    jTextAreaDescripcion.setEditable(false);
                    jComboBoxCategoria.setEnabled(false);

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error al registrar el plato:\n" + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
                } finally {
                    txtNombrePlato.setEditable(false);
                    txtPrecio.setEditable(false);
                    jComboBoxCategoria.setEditable(false);
                    jTextAreaDescripcion.setEditable(false);

                    BTN_Guardar.setEnabled(false);
                    BTN_VerPlatos.setEnabled(false);
                    BTN_Modificar.setEnabled(false);
                    BTN_Desactivar.setEnabled(false);
                    BTN_Nuevo.setVisible(true);
                    BTN_Cancel.setVisible(false);
                    JTABLE_Mant_Plato.setEnabled(true);
                }

            }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed
        try {
            // 1. Obtener datos del formulario
            String codigoStr = txtcodigoplato.getText().trim();
            String nuevoNombre = txtNombrePlato.getText().trim();
            String nuevoPrecioRaw = txtPrecio.getText().trim();
            String nombreCategoria = (String) jComboBoxCategoria.getSelectedItem();
            String nuevaDescripcion = jTextAreaDescripcion.getText().trim();

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
                    jTextAreaDescripcion.setEditable(false);
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
    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void BTN_DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DesactivarActionPerformed
        // 1. Validar que se haya seleccionado una facultad
        String codStr = txtcodigoplato.getText().trim();
        if (codStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Seleccione una facultad en la tabla para desactivar.","Campo requerido",JOptionPane.WARNING_MESSAGE);
            return;
        }
        int codigo = Integer.parseInt(codStr); // Convertir a entero
        // 2. Confirmar la acción con el usuario
        int opcion = JOptionPane.showConfirmDialog(this,"¿Está seguro de que desea desactivar esta facultad?","Confirmar desactivación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            try {
                // 3. Llamar al método que ejecuta el procedure de desactivación
                this.methods.downFacultades(codigo);
                // 4. Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(this,"Mesa desactivada correctamente.","Operación exitosa",JOptionPane.INFORMATION_MESSAGE);
                // 5. Actualizar tabla y limpiar campos
                this.MostrarPlatosMenu();
                // Limpia los campos de texto
                txtcodigoplato.setText("");
                //txtprecio.setText("");
                BTN_Desactivar.setEnabled(false);
                BTN_Modificar.setEnabled(false);
            } catch (SQLException ex) {
                // 6. Captura cualquier error lanzado por el procedure (por SIGNAL)
                JOptionPane.showMessageDialog(this,"Error al desactivar facultad:\n" + ex.getMessage(),"Error de base de datos",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTN_DesactivarActionPerformed

    private void BTN_VerPlatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerPlatosActionPerformed
        this.MostrarPlatosMenu();

        cargarComboBoxCategoria();
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
            java.util.logging.Logger.getLogger(Frm_PlatoMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_PlatoMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_PlatoMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_PlatoMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frm_PlatoMenu().setVisible(true);
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
    private javax.swing.JTable JTABLE_Mant_Plato;
    private javax.swing.JTextField TXT_BuscarMesas;
    private javax.swing.JComboBox<String> jComboBoxCategoria;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaDescripcion;
    private javax.swing.JTextField txtNombrePlato;
    private javax.swing.JFormattedTextField txtPrecio;
    private javax.swing.JTextField txtcodigoplato;
    // End of variables declaration//GEN-END:variables

//Método para mostrar las facultades
    public void MostrarPlatosMenu() {
        //Ordenar ASC, DESC
        JTABLE_Mant_Plato.setAutoCreateRowSorter(true);
        //Limpiar la tabla antes de mostrar nuevos datos
        modeloTablaMesa.setRowCount(0);
        try {
            //Llama al método que retorna los datos de facultades
            ResultSet rs = this.methods.listarPlatoMenu();
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
                modeloTablaMesa.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los resultados:\n" + e.getMessage(),
                    "ErrorDeConsulta", JOptionPane.ERROR_MESSAGE);
        }
    }

//Método para mostrar las facultades
    public void BuscarMesaPorNombre() {
        String parametro = TXT_BuscarMesas.getText().trim();
        if (parametro.isEmpty()) {
            parametro = "";
        }
        try {
            //Llama al método que retorna los datos de facultades
            ResultSet rs = this.methods.buscarPlatoMenu(parametro);

            try (rs) {
                modeloTablaMesa.setRowCount(0);
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
                modeloTablaMesa.addRow(fila);
            }
            }


        } catch (SQLException e) {
            System.err.println("Error silencioso en búsqueda: " + e.getMessage());
        }
    }

}
