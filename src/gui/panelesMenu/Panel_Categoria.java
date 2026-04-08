package gui.panelesMenu;


import com.formdev.flatlaf.FlatLightLaf;
import gui.menu.Frm_MenuPrincipal;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

   /*Método de Categoría*/
import logic.dao.CategoriaMethod;


  /*excel*/
import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFRow;

/*pdf*/
//import com.itextpdf.text.*;
//import com.itextpdf.text.pdf.*;
//import java.text.SimpleDateFormat;
//import java.util.Date;



public class Panel_Categoria extends javax.swing.JPanel {

        DefaultTableModel modeloTablaMesa = new DefaultTableModel();
        CategoriaMethod methods = new CategoriaMethod();
    
    public Panel_Categoria() {
        initComponents();


        txtnombrecategoria.setEditable(false);

        String[] header = {"ID ", "Nombre de la Categoría"};

        modeloTablaMesa.setColumnIdentifiers(header);
        JTABLE_Mant_Categoria.setModel(modeloTablaMesa);

        //Desactivar button
        BTN_Nuevo.setEnabled(false);
        BTN_Desactivar.setEnabled(false);
        BTN_Guardar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        txtcodigocategoria.setEnabled(false);
        
        BTN_Cancel.setVisible(false);
        
        
    }
    

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BTN_PDF = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        BTN_Cancel = new javax.swing.JButton();
        BTN_Desactivar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXT_BuscarCategorias = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_Categoria = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtcodigocategoria = new javax.swing.JTextField();
        BTN_VerCategorias = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtnombrecategoria = new javax.swing.JTextField();
        BTN_EXCEL = new javax.swing.JButton();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BTN_PDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pdf.png"))); // NOI18N
        BTN_PDF.setText("     Exportar PDF");
        BTN_PDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_PDFActionPerformed(evt);
            }
        });
        add(BTN_PDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 470, 170, 40));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_update.png"))); // NOI18N
        BTN_Modificar.setText("    MODIFICAR");
        BTN_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ModificarActionPerformed(evt);
            }
        });
        add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, 200, 50));

        BTN_Cancel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_cancel.png"))); // NOI18N
        BTN_Cancel.setText("     CANCELAR");
        BTN_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CancelActionPerformed(evt);
            }
        });
        add(BTN_Cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 190, 48));

        BTN_Desactivar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Desactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_delete.png"))); // NOI18N
        BTN_Desactivar.setText("     QUITAR");
        BTN_Desactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_DesactivarActionPerformed(evt);
            }
        });
        add(BTN_Desactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 150, 180, 50));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingresar el Nombre de la Facultad");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 30));

        TXT_BuscarCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_BuscarCategoriasActionPerformed(evt);
            }
        });
        TXT_BuscarCategorias.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_BuscarCategoriasKeyReleased(evt);
            }
        });
        jPanel2.add(TXT_BuscarCategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 14, 290, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_search_white.png"))); // NOI18N
        jLabel5.setText("BUSCAR");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 120, 30));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 810, 50));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_add.png"))); // NOI18N
        BTN_Nuevo.setText("      NUEVO");
        BTN_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_NuevoActionPerformed(evt);
            }
        });
        add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 190, 50));

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
        add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 190, 50));

        JTABLE_Mant_Categoria.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_Categoria.setForeground(new java.awt.Color(0, 0, 204));
        JTABLE_Mant_Categoria.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Mant_Categoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_CategoriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Mant_Categoria);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 810, 190));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("MANTENIMIENTO DE CATEGORÍA");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Codigo de Categoría");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        txtcodigocategoria.setEditable(false);
        txtcodigocategoria.setBackground(new java.awt.Color(255, 255, 255));
        txtcodigocategoria.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcodigocategoria.setForeground(new java.awt.Color(0, 0, 204));
        txtcodigocategoria.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcodigocategoria.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtcodigocategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 330, 30));

        BTN_VerCategorias.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_VerCategorias.setText("VER CATEGORÍAS");
        BTN_VerCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_VerCategoriasActionPerformed(evt);
            }
        });
        jPanel1.add(BTN_VerCategorias, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 30, 190, 50));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Nombre de categoría");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        txtnombrecategoria.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtnombrecategoria.setForeground(new java.awt.Color(0, 0, 204));
        txtnombrecategoria.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnombrecategoria.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtnombrecategoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombrecategoriaKeyTyped(evt);
            }
        });
        jPanel1.add(txtnombrecategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 330, 30));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 790, 110));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/excel.png"))); // NOI18N
        BTN_EXCEL.setText("     Exportar XLSX");
        BTN_EXCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_EXCELActionPerformed(evt);
            }
        });
        add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 470, 170, 40));
    }// </editor-fold>//GEN-END:initComponents

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

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed

        try {
            String codStr = txtcodigocategoria.getText().trim();
            String nuevoNombre = txtnombrecategoria.getText().trim();

            if (codStr.isEmpty() || nuevoNombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id = Integer.parseInt(codStr);

            try {
                if (methods.existeCategoriaConNombre(nuevoNombre, id)) {
                    JOptionPane.showMessageDialog(this, "Ya existe otra categoría con el mismo nombre.",
                        "Validación", JOptionPane.WARNING_MESSAGE);
                    return;
                }       } catch (SQLException ex) {
                    Logger.getLogger(Panel_Categoria.class.getName()).log(Level.SEVERE, null, ex);
                }

                int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea modificar esta categoría?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (respuesta == JOptionPane.YES_OPTION) {
                    // Llama a tu método: modificarCategoria(int id, String nuevoNombre)
                    this.methods.modificarCategoria(id, nuevoNombre);

                    JOptionPane.showMessageDialog(this, "Categoría actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    this.MostrarCategorias();// Refresca tu tabla
                    this.limpiarCamposCategoria();
                    txtnombrecategoria.setEditable(false);
                }

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Código no válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                // Captura: "Ya existe una categoría con ese nombre."
                JOptionPane.showMessageDialog(this, e.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error SQL: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_BTN_ModificarActionPerformed

    private void BTN_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CancelActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea cancelar la operación?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {

            BTN_Guardar.setEnabled(false);
            BTN_Desactivar.setEnabled(false);
            BTN_Modificar.setEnabled(false);
            BTN_Nuevo.setVisible(true);
            BTN_Cancel.setVisible(false);
            txtnombrecategoria.setEditable(false);
            JTABLE_Mant_Categoria.setEnabled(true);
            this.limpiarCamposCategoria();
        }
    }//GEN-LAST:event_BTN_CancelActionPerformed

    private void BTN_DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DesactivarActionPerformed
        // 1. Validar que se haya seleccionado una Categoria

        String codStr = txtcodigocategoria.getText().trim();
        String nuevoNombre = txtnombrecategoria.getText().trim();

        if (codStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,"Seleccione una Categoria en la tabla para desactivar.","Campo requerido",JOptionPane.WARNING_MESSAGE);
            return;
        }
        int codigo = Integer.parseInt(codStr); // Convertir a entero
        // 2. Confirmar la acción con el usuario
        int opcion = JOptionPane.showConfirmDialog(this,"¿Está seguro de que desea desactivar esta Categoria?","Confirmar desactivación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            try {
                // 3. Llamar al método que ejecuta el procedure de desactivación
                this.methods.desactivarCategoria(codigo);
                // 4. Mostrar mensaje de éxito
                JOptionPane.showMessageDialog(this,"Mesa desactivada correctamente.","Operación exitosa",JOptionPane.INFORMATION_MESSAGE);
                // 5. Actualizar tabla y limpiar campos
                this.MostrarCategorias();
                // Limpia los campos de texto
                txtcodigocategoria.setText("");
                txtnombrecategoria.setText("");
                BTN_Desactivar.setEnabled(false);
                BTN_Modificar.setEnabled(false);
            } catch (SQLException ex) {
                // 6. Captura cualquier error lanzado por el procedure (por SIGNAL)
                JOptionPane.showMessageDialog(this,"Error al desactivar Categoria:\n" + ex.getMessage(),"Error de base de datos",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BTN_DesactivarActionPerformed

    private void TXT_BuscarCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_BuscarCategoriasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_BuscarCategoriasActionPerformed

    private void TXT_BuscarCategoriasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_BuscarCategoriasKeyReleased
        this.BuscarCategoriaPorNombre();
    }//GEN-LAST:event_TXT_BuscarCategoriasKeyReleased

    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
        txtnombrecategoria.setText("");
        txtcodigocategoria.setText("");
        txtnombrecategoria.requestFocus();

        BTN_Guardar.setEnabled(true);
        BTN_Desactivar.setEnabled(false);
        BTN_Modificar.setEnabled(false);

        BTN_Nuevo.setVisible(false);
        BTN_Cancel.setVisible(true);

        txtnombrecategoria.setEditable(true);

        JTABLE_Mant_Categoria.setEnabled(false);

    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed

        // 1. Validar que el campo no esté vacío
        String nombre = txtnombrecategoria.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese el nombre de la categoría", "Campo requerido", JOptionPane.WARNING_MESSAGE);
            txtnombrecategoria.requestFocus();
            return;
        }

        try {
            if (this.methods.existeCategoriaConNombre(nombre, 0)) {
                JOptionPane.showMessageDialog(this, "Ya existe otra categoría con el mismo nombre.",
                    "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }       } catch (SQLException ex) {
                Logger.getLogger(Panel_Categoria.class.getName()).log(Level.SEVERE, null, ex);
            }

            // 2. Confirmar si el usuario desea guardar
            int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea guardar el registro de categorías?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {

                try {
                    // 3. Llamar al método para insertar
                    this.methods.insertarCategoria(nombre);

                    // 4. Mostrar mensaje de éxito
                    JOptionPane.showMessageDialog(this, "Categoría registrada correctamente", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);

                    // 5. Actualizar tabla y limpiar campos
                    this.MostrarCategorias();

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error al registrar la categoría:\n" + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
                } finally {
                    JTABLE_Mant_Categoria.setEnabled(true);
                    txtnombrecategoria.setEditable(false);
                    BTN_Nuevo.setVisible(true);
                    BTN_Cancel.setVisible(false);
                    this.limpiarCamposCategoria();

                }
            }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void JTABLE_Mant_CategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_CategoriaMouseClicked
        if (!JTABLE_Mant_Categoria.isEnabled()) {
            return;
        }

        int selectRow = JTABLE_Mant_Categoria.getSelectedRow();
        if (selectRow >= 0) {
            String codigo = JTABLE_Mant_Categoria.getValueAt(selectRow, 0).toString();
            String numero_mesa = JTABLE_Mant_Categoria.getValueAt(selectRow, 1).toString();

            txtcodigocategoria.setText(codigo);
            txtnombrecategoria.setText(numero_mesa);

        }
        BTN_Guardar.setEnabled(false);
        BTN_VerCategorias.setEnabled(false);
        BTN_Modificar.setEnabled(true);
        BTN_Desactivar.setEnabled(true);
        txtnombrecategoria.setEditable(true);
    }//GEN-LAST:event_JTABLE_Mant_CategoriaMouseClicked

    private void BTN_VerCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerCategoriasActionPerformed
        this.MostrarCategorias();

        BTN_Nuevo.setEnabled(true);
        this.BTN_Guardar.setEnabled(false);
        this.BTN_Desactivar.setEnabled(false);
        this.BTN_Modificar.setEnabled(false);
        this.BTN_VerCategorias.setEnabled(false);
    }//GEN-LAST:event_BTN_VerCategoriasActionPerformed

    private void txtnombrecategoriaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombrecategoriaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombrecategoriaKeyTyped

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Cancel;
    private javax.swing.JButton BTN_Desactivar;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_PDF;
    private javax.swing.JButton BTN_VerCategorias;
    private javax.swing.JTable JTABLE_Mant_Categoria;
    private javax.swing.JTextField TXT_BuscarCategorias;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtcodigocategoria;
    private javax.swing.JTextField txtnombrecategoria;
    // End of variables declaration//GEN-END:variables


    private void limpiarCamposCategoria() {
    txtcodigocategoria.setText("");
    txtnombrecategoria.setText("");

    // Control de botones
    BTN_Guardar.setEnabled(false);
    BTN_Modificar.setEnabled(false);
    BTN_Desactivar.setEnabled(false);
    txtnombrecategoria.setEditable(false);

    BTN_VerCategorias.setEnabled(false);
}
    
    public void MostrarCategorias() {
        //Ordenar ASC, DESC
        JTABLE_Mant_Categoria.setAutoCreateRowSorter(true);
        //Limpiar la tabla antes de mostrar nuevos datos
        modeloTablaMesa.setRowCount(0);
        try {
            //Llama al método que retorna los datos de Categoriaes
            ResultSet rs = this.methods.listarCategoria();
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("ID"),
                    rs.getString("Nombre de Categoría")
                };
                modeloTablaMesa.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los resultados:\n" + e.getMessage(),
                    "ErrorDeConsulta", JOptionPane.ERROR_MESSAGE);
        }
    }


public void BuscarCategoriaPorNombre() {
        modeloTablaMesa.setRowCount(0);
        String nombre = TXT_BuscarCategorias.getText().trim();
        try {
            //Llama al método que retorna los datos de Categoriaes
            ResultSet rs = this.methods.buscarCategoria(nombre);
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("ID"),
                    rs.getString("Nombre de Categoría")
                };
                modeloTablaMesa.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar Categoriaes:\n" + e.getMessage(),
                    "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        }
    }







}
