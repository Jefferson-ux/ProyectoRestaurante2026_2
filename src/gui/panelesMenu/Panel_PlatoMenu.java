package gui.panelesMenu;


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

public class Panel_PlatoMenu extends javax.swing.JPanel {

    DefaultTableModel modeloTablaMesa = new DefaultTableModel();
    //Objeto conexión a la base de datos
    PlatoMenuMethod methods;
    public Panel_PlatoMenu() {
        initComponents();
        
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

        BTN_Cancel = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXT_BuscarMesas = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        BTN_Desactivar1 = new javax.swing.JButton();
        BTN_Desactivar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_Plato = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        BTN_Modificar = new javax.swing.JButton();
        BTN_VerPlatos = new javax.swing.JButton();
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
        BTN_Nuevo = new javax.swing.JButton();
        BTN_PDF = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BTN_Cancel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_cancel.png"))); // NOI18N
        BTN_Cancel.setText("     CANCELAR");
        BTN_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CancelActionPerformed(evt);
            }
        });
        add(BTN_Cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(742, 86, 165, 48));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/excel.png"))); // NOI18N
        BTN_EXCEL.setText("     Exportar XLSX");
        BTN_EXCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_EXCELActionPerformed(evt);
            }
        });
        add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(738, 603, 170, 50));

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

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 314, 920, 50));

        BTN_Desactivar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Desactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_delete.png"))); // NOI18N
        BTN_Desactivar.setText("     QUITAR");
        BTN_Desactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_DesactivarActionPerformed(evt);
            }
        });
        add(BTN_Desactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(742, 254, 165, 48));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 920, 220));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MANTENIMIENTO DE PLATOS DEL MENÚ");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 830, 30));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_update.png"))); // NOI18N
        BTN_Modificar.setText("    MODIFICAR");
        BTN_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ModificarActionPerformed(evt);
            }
        });
        add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(742, 200, 165, 48));

        BTN_VerPlatos.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_VerPlatos.setText("VER PLATOS DEL MENÚ");
        BTN_VerPlatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_VerPlatosActionPerformed(evt);
            }
        });
        add(BTN_VerPlatos, new org.netbeans.lib.awtextra.AbsoluteConstraints(742, 30, 165, 50));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel2.setText("Codigo del Plato");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Descripción");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 150, -1));

        txtcodigoplato.setEditable(false);
        txtcodigoplato.setBackground(new java.awt.Color(255, 255, 255));
        txtcodigoplato.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcodigoplato.setForeground(new java.awt.Color(0, 0, 204));
        txtcodigoplato.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcodigoplato.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtcodigoplato, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 250, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Nombre del Plato*");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 10, 220, -1));

        jComboBoxCategoria.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jComboBoxCategoria.setForeground(new java.awt.Color(0, 0, 204));
        jPanel1.add(jComboBoxCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 90, 330, 30));

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
        jPanel1.add(txtNombrePlato, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 330, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Precio del Plato*");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 180, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Categoría*");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 140, -1));

        jTextAreaDescripcion.setColumns(20);
        jTextAreaDescripcion.setRows(5);
        jScrollPane2.setViewportView(jTextAreaDescripcion);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 630, 100));

        txtPrecio.setForeground(new java.awt.Color(0, 0, 204));
        txtPrecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtPrecio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPrecio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel1.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 250, 30));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 698, 270));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_add.png"))); // NOI18N
        BTN_Nuevo.setText("      NUEVO");
        BTN_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_NuevoActionPerformed(evt);
            }
        });
        add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(742, 94, 165, 48));

        BTN_PDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pdf.png"))); // NOI18N
        BTN_PDF.setText("     Exportar PDF");
        BTN_PDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_PDFActionPerformed(evt);
            }
        });
        add(BTN_PDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(556, 602, 170, 50));

        BTN_Guardar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_save.png"))); // NOI18N
        BTN_Guardar.setText("     GUARDAR");
        BTN_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_GuardarActionPerformed(evt);
            }
        });
        add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(742, 146, 165, 48));
    }// </editor-fold>//GEN-END:initComponents

    private void BTN_EXCELActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_EXCELActionPerformed
  
    }//GEN-LAST:event_BTN_EXCELActionPerformed

    private void TXT_BuscarMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_BuscarMesasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_BuscarMesasActionPerformed

    private void TXT_BuscarMesasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarMesasKeyReleased
        this.BuscarMesaPorNombre();
    }//GEN-LAST:event_TXT_BuscarMesasKeyReleased

    private void BTN_Desactivar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_Desactivar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_Desactivar1ActionPerformed

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

    private void txtNombrePlatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombrePlatoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombrePlatoActionPerformed

    private void txtNombrePlatoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombrePlatoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombrePlatoKeyTyped

    private void BTN_PDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_PDFActionPerformed

    }//GEN-LAST:event_BTN_PDFActionPerformed

    private void BTN_VerPlatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerPlatosActionPerformed
        this.MostrarPlatosMenu();

        cargarComboBoxCategoria();
        this.BTN_Nuevo.setEnabled(true);
        this.BTN_Guardar.setEnabled(false);
        this.BTN_Desactivar.setEnabled(false);
        this.BTN_Modificar.setEnabled(false);
        this.BTN_VerPlatos.setEnabled(false);
    }//GEN-LAST:event_BTN_VerPlatosActionPerformed

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
            Logger.getLogger(Panel_PlatoMenu.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(Panel_PlatoMenu.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(Panel_PlatoMenu.class.getName()).log(Level.SEVERE, null, ex);
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
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_DesactivarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Cancel;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextAreaDescripcion;
    private javax.swing.JTextField txtNombrePlato;
    private javax.swing.JFormattedTextField txtPrecio;
    private javax.swing.JTextField txtcodigoplato;
    // End of variables declaration//GEN-END:variables
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
