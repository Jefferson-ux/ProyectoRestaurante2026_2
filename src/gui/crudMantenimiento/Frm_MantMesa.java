package gui.crudMantenimiento;

import com.formdev.flatlaf.FlatLightLaf;
import gui.menu.Frm_MenuPrincipal;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

   /*Método de Mesa*/
import logic.dao.MesaMethod;


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

public class Frm_MantMesa extends javax.swing.JFrame {

    DefaultTableModel modeloTablaMesa = new DefaultTableModel();
    //Objeto conexión a la base de datos
    MesaMethod methods;

    public Frm_MantMesa() {
        FlatLightLaf.setup();
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("Mantenimiento de las mesa");

        ImageIcon icono = new ImageIcon(getClass().getResource("/assets/icon_user.png"));
        // 2. Extraemos la imagen del objeto ImageIcon
        Image imagen = icono.getImage();
        // 3. Lo asignamos a la ventana
        this.setIconImage(imagen);

        this.methods = new MesaMethod();

        txtnumeroMesa.setEditable(false);
        txtcapacidad.setEditable(false);

        String[] header = {"ID ", "Número de Mesa","Capacidad"};

        modeloTablaMesa.setColumnIdentifiers(header);
        JTABLE_Mant_Mesa.setModel(modeloTablaMesa);

        //Desactivar button
        BTN_Nuevo.setEnabled(false);
        BTN_Desactivar.setEnabled(false);
        BTN_Guardar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
        txtcodigomesa.setEnabled(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtcodigomesa = new javax.swing.JTextField();
        BTN_VerMesas = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtnumeroMesa = new javax.swing.JTextField();
        txtcapacidad = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Mant_Mesa = new javax.swing.JTable();
        BTN_Nuevo = new javax.swing.JButton();
        BTN_Cancel = new javax.swing.JButton();
        BTN_Guardar = new javax.swing.JButton();
        BTN_Modificar = new javax.swing.JButton();
        BTN_Desactivar = new javax.swing.JButton();
        BTN_EXCEL = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        TXT_BuscarMesas = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        BTN_Cerrar1 = new javax.swing.JButton();
        BTN_PDF = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        BTN_Back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("MANTENIMIENTO DE MESAS");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 0, 240, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 0, 51), null));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Codigo de Mesa");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Capacidad");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        txtcodigomesa.setEditable(false);
        txtcodigomesa.setBackground(new java.awt.Color(255, 255, 255));
        txtcodigomesa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtcodigomesa.setForeground(new java.awt.Color(0, 0, 204));
        txtcodigomesa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcodigomesa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(txtcodigomesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 330, 30));

        BTN_VerMesas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_VerMesas.setText("VER MESAS");
        BTN_VerMesas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_VerMesasActionPerformed(evt);
            }
        });
        jPanel1.add(BTN_VerMesas, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 180, 50));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Número de mesa");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        txtnumeroMesa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtnumeroMesa.setForeground(new java.awt.Color(0, 0, 204));
        txtnumeroMesa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnumeroMesa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtnumeroMesa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnumeroMesaKeyTyped(evt);
            }
        });
        jPanel1.add(txtnumeroMesa, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 59, 330, 30));

        txtcapacidad.setForeground(new java.awt.Color(0, 0, 204));
        txtcapacidad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        txtcapacidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcapacidad.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel1.add(txtcapacidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 330, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 790, 170));

        JTABLE_Mant_Mesa.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTABLE_Mant_Mesa.setForeground(new java.awt.Color(0, 0, 204));
        JTABLE_Mant_Mesa.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Mant_Mesa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_Mant_MesaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Mant_Mesa);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 810, 220));

        BTN_Nuevo.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Nuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_add.png"))); // NOI18N
        BTN_Nuevo.setText("      NUEVO");
        BTN_Nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_NuevoActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_Nuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 190, 50));

        BTN_Cancel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_cancel.png"))); // NOI18N
        BTN_Cancel.setText("     CANCELAR");
        BTN_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_CancelActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_Cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 190, 48));

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
        getContentPane().add(BTN_Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 190, 50));

        BTN_Modificar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_update.png"))); // NOI18N
        BTN_Modificar.setText("    MODIFICAR");
        BTN_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_ModificarActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 220, 200, 50));

        BTN_Desactivar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Desactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_delete.png"))); // NOI18N
        BTN_Desactivar.setText("     QUITAR");
        BTN_Desactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_DesactivarActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_Desactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 220, 180, 50));

        BTN_EXCEL.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_EXCEL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/excel.png"))); // NOI18N
        BTN_EXCEL.setText("     Exportar XLSX");
        BTN_EXCEL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_EXCELActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_EXCEL, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 560, 170, 40));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Ingresar el Nombre de la Facultad");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, 30));

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
        jPanel2.add(TXT_BuscarMesas, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 14, 290, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_search_white.png"))); // NOI18N
        jLabel5.setText("BUSCAR");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 120, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, 810, 50));

        BTN_Cerrar1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        BTN_Cerrar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_close.png"))); // NOI18N
        BTN_Cerrar1.setText("     Cerrar");
        BTN_Cerrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_Cerrar1ActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_Cerrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 560, 130, 40));

        BTN_PDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/pdf.png"))); // NOI18N
        BTN_PDF.setText("     Exportar PDF");
        BTN_PDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_PDFActionPerformed(evt);
            }
        });
        getContentPane().add(BTN_PDF, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 560, 170, 40));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BTN_Back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/icon_back.png"))); // NOI18N
        BTN_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_BackActionPerformed(evt);
            }
        });
        jPanel3.add(BTN_Back, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 560, 40, 40));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 620));

        pack();
    }// </editor-fold>//GEN-END:initComponents




    //// --> -->
    private void JTABLE_Mant_MesaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_Mant_MesaMouseClicked
                if (!JTABLE_Mant_Mesa.isEnabled()) {
            return;
        }
        int selectRow = JTABLE_Mant_Mesa.getSelectedRow();
        if (selectRow >= 0) {
            String codigo = JTABLE_Mant_Mesa.getValueAt(selectRow, 0).toString();
            String numero_mesa = JTABLE_Mant_Mesa.getValueAt(selectRow, 1).toString();
            String capacidad = JTABLE_Mant_Mesa.getValueAt(selectRow, 2).toString();

            txtcodigomesa.setText(codigo);
            txtnumeroMesa.setText(numero_mesa);
            txtcapacidad.setText(capacidad);
        }

        txtnumeroMesa.setEditable(true);
        txtcapacidad.setEditable(true);
        BTN_Guardar.setEnabled(false);
        BTN_VerMesas.setEnabled(false);
        BTN_Modificar.setEnabled(true);
        BTN_Desactivar.setEnabled(true);
    }//GEN-LAST:event_JTABLE_Mant_MesaMouseClicked





    private void BTN_NuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_NuevoActionPerformed
        txtnumeroMesa.setText("");
        txtcodigomesa.setText("");
        txtcapacidad.setText("");

        txtnumeroMesa.requestFocus();
        txtcapacidad.setEnabled(true);
        JTABLE_Mant_Mesa.setEnabled(false);

        BTN_Guardar.setEnabled(true);
        BTN_Desactivar.setEnabled(false);
        BTN_Modificar.setEnabled(false);

        JTABLE_Mant_Mesa.setEnabled(false);
            BTN_Nuevo.setVisible(false);
            BTN_Cancel.setVisible(true);


        txtnumeroMesa.setEditable(true);
        txtcapacidad.setEditable(true);


    }//GEN-LAST:event_BTN_NuevoActionPerformed

    private void BTN_GuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BTN_GuardarMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_GuardarMouseClicked

    private void BTN_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_GuardarActionPerformed

   // 1. Validar que el campo no esté vacío
    String nombre = txtnumeroMesa.getText().trim();
    String capacidad = txtcapacidad.getText().trim();
    if (nombre.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Ingrese el número de mesa", "Campo requerido", JOptionPane.WARNING_MESSAGE);
      txtnumeroMesa.requestFocus();
      return;
    }
    if (capacidad.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Ingrese la capacidad de la mesa", "Campo requerido", JOptionPane.WARNING_MESSAGE);
      txtcapacidad.requestFocus();
      return;
    }



    int capacidadInt = Integer.parseInt(capacidad);

            if (capacidadInt<=0) {
            JOptionPane.showMessageDialog(this, "La capacidad de las mesas debe ser positiva.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (capacidadInt>=20) {
            JOptionPane.showMessageDialog(this, "La capacidad de las mesas debe ser menor de 20.\nEsto por temas de ergonomía.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }



            try {
            if (this.methods.existeMesaConNumero(nombre, 0)) {
                JOptionPane.showMessageDialog(this, "Ya existe otra categoría con el mismo nombre.",
                        "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }       } catch (SQLException ex) {
            Logger.getLogger(Frm_Categoria.class.getName()).log(Level.SEVERE, null, ex);
        }




    // 2. Confirmar si el usuario desea guardar
    int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea guardar el registro de mesas?", "Confirmación", JOptionPane.YES_NO_OPTION);
    if (respuesta == JOptionPane.YES_OPTION) {

      try {
        // 3. Llamar al método para insertar
        this.methods.insertarMesas(nombre, capacidadInt);

        // 4. Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(this, "Mesa registrada correctamente", "Registro exitoso", JOptionPane.INFORMATION_MESSAGE);

        // 5. Actualizar tabla y limpiar campos
        this.MostrarMesas();

      } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error al registrar mesa:\n" + ex.getMessage(), "Error de base de datos", JOptionPane.ERROR_MESSAGE);
      } finally {
          JTABLE_Mant_Mesa.setEnabled(true);
            BTN_Guardar.setEnabled(false);
            BTN_Nuevo.setVisible(true);
            BTN_Cancel.setVisible(false);

            txtnumeroMesa.setEditable(false);
            txtcapacidad.setEditable(false);

      }
    }
    }//GEN-LAST:event_BTN_GuardarActionPerformed

    private void BTN_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_ModificarActionPerformed

    try {
        String codStr = txtcodigomesa.getText().trim();
        String numMesa = txtnumeroMesa.getText().trim(); // El "nuevoNombre" en tu método
        String capacidadStr = txtcapacidad.getText().trim();

        if (codStr.isEmpty() || numMesa.isEmpty() || capacidadStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos obligatorios.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int id = Integer.parseInt(codStr);
        int capacidad = Integer.parseInt(capacidadStr);


        if (capacidad<=0) {
            JOptionPane.showMessageDialog(this, "La capacidad de las mesas debe ser positiva.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (capacidad>=20) {
            JOptionPane.showMessageDialog(this, "La capacidad de las mesas debe ser menor de 20.\nEsto por temas de ergonomía.", "Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }



                try {
            if (this.methods.existeMesaConNumero(numMesa, 0)) {
                JOptionPane.showMessageDialog(this, "Ya existe otra categoría con el mismo nombre.",
                        "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }       } catch (SQLException ex) {
            Logger.getLogger(Frm_Categoria.class.getName()).log(Level.SEVERE, null, ex);
        }




        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea modificar los datos de la mesa?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            // Llama a tu método: modificarMesas(int id, String nuevoNombre, int nuevaCapacidad)
            this.methods.modificarMesas(id, numMesa, capacidad);

            JOptionPane.showMessageDialog(this, "Mesa actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.MostrarMesas();
            this.limpiarCamposMesas();
            txtnumeroMesa.setEditable(false);
            txtcapacidad.setEditable(false);
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Asegúrese de que el ID y la Capacidad sean números.", "Error de formato", JOptionPane.ERROR_MESSAGE);
    } catch (IllegalArgumentException e) {
        // Captura: "El número de mesa ya está registrado."
        JOptionPane.showMessageDialog(this, e.getMessage(), "Validación", JOptionPane.WARNING_MESSAGE);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al actualizar mesa: " + e.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
    }

    }//GEN-LAST:event_BTN_ModificarActionPerformed




            private void limpiarCamposMesas() {
    txtcodigomesa.setText("");
    txtnumeroMesa.setText("");
    txtcapacidad.setText("");

    // Control de botones
    BTN_Guardar.setEnabled(true);
    BTN_Modificar.setEnabled(false);
    BTN_Desactivar.setEnabled(false);

    BTN_VerMesas.setEnabled(false);
}





    private void BTN_DesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_DesactivarActionPerformed
    // 1. Validar que se haya seleccionado una Mesa
    String codStr = txtcodigomesa.getText().trim();
    if (codStr.isEmpty()) {
      JOptionPane.showMessageDialog(this,"Seleccione una Mesa en la tabla para desactivar.","Campo requerido",JOptionPane.WARNING_MESSAGE);
      return;
    }
    int codigo = Integer.parseInt(codStr); // Convertir a entero
    // 2. Confirmar la acción con el usuario
    int opcion = JOptionPane.showConfirmDialog(this,"¿Está seguro de que desea desactivar esta Mesa?","Confirmar desactivación",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
    if (opcion == JOptionPane.YES_OPTION) {
      try {
       // 3. Llamar al método que ejecuta el procedure de desactivación
        this.methods.downFacultades(codigo);
       // 4. Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(this,"Mesa desactivada correctamente.","Operación exitosa",JOptionPane.INFORMATION_MESSAGE);
       // 5. Actualizar tabla y limpiar campos
        this.MostrarMesas();
       // Limpia los campos de texto
        txtcodigomesa.setText("");
        txtcapacidad.setText("");
        BTN_Desactivar.setEnabled(false);
        BTN_Modificar.setEnabled(false);
      } catch (SQLException ex) {
        // 6. Captura cualquier error lanzado por el procedure (por SIGNAL)
        JOptionPane.showMessageDialog(this,"Error al desactivar Mesa:\n" + ex.getMessage(),"Error de base de datos",JOptionPane.ERROR_MESSAGE);
      }
   }
    }//GEN-LAST:event_BTN_DesactivarActionPerformed
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




    private void BTN_VerMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VerMesasActionPerformed
        this.MostrarMesas();

        this.BTN_Nuevo.setEnabled(true);
        this.BTN_Guardar.setEnabled(false);
        this.BTN_Desactivar.setEnabled(false);
        this.BTN_Modificar.setEnabled(false);
        this.BTN_VerMesas.setEnabled(false);

    }//GEN-LAST:event_BTN_VerMesasActionPerformed

    private void TXT_BuscarMesasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_BuscarMesasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_BuscarMesasActionPerformed

    private void txtnumeroMesaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnumeroMesaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnumeroMesaKeyTyped

    private void BTN_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_BackActionPerformed
        Frm_MenuPrincipal mainMenu = new Frm_MenuPrincipal();
        mainMenu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BTN_BackActionPerformed


    private void BTN_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_CancelActionPerformed
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Desea cancelar la operación?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {

            JTABLE_Mant_Mesa.setEnabled(true);
            BTN_Guardar.setEnabled(false);
            BTN_Nuevo.setVisible(true);
            BTN_Cancel.setVisible(false);

            BTN_Desactivar.setEnabled(false);
            BTN_Modificar.setEnabled(false);


            txtnumeroMesa.setEditable(false);
            txtcapacidad.setEditable(false);
            this.limpiarCamposMesas();

        }
    }//GEN-LAST:event_BTN_CancelActionPerformed

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
            java.util.logging.Logger.getLogger(Frm_MantMesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frm_MantMesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frm_MantMesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frm_MantMesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new Frm_MantMesa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_Back;
    private javax.swing.JButton BTN_Cancel;
    private javax.swing.JButton BTN_Cerrar1;
    private javax.swing.JButton BTN_Desactivar;
    private javax.swing.JButton BTN_EXCEL;
    private javax.swing.JButton BTN_Guardar;
    private javax.swing.JButton BTN_Modificar;
    private javax.swing.JButton BTN_Nuevo;
    private javax.swing.JButton BTN_PDF;
    private javax.swing.JButton BTN_VerMesas;
    private javax.swing.JTable JTABLE_Mant_Mesa;
    private javax.swing.JTextField TXT_BuscarMesas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JFormattedTextField txtcapacidad;
    private javax.swing.JTextField txtcodigomesa;
    private javax.swing.JTextField txtnumeroMesa;
    // End of variables declaration//GEN-END:variables

//Método para mostrar las Mesaes
    public void MostrarMesas() {
        //Ordenar ASC, DESC
        JTABLE_Mant_Mesa.setAutoCreateRowSorter(true);
        //Limpiar la tabla antes de mostrar nuevos datos
        modeloTablaMesa.setRowCount(0);
        try {
            //Llama al método que retorna los datos de Mesaes
            ResultSet rs = this.methods.listarMesas();
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("ID"),
                    rs.getString("Número de Mesa"),
                    rs.getString("Capacidad")
                };
                modeloTablaMesa.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los resultados:\n" + e.getMessage(),
                    "ErrorDeConsulta", JOptionPane.ERROR_MESSAGE);
        }
    }

//Método para mostrar las Mesaes
    public void BuscarMesaPorNombre() {
        modeloTablaMesa.setRowCount(0);
        String nombre = TXT_BuscarMesas.getText().trim();
        try {
            //Llama al método que retorna los datos de Mesaes
            ResultSet rs = this.methods.buscarMesas(nombre);
            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("ID"),
                    rs.getString("Número de Mesa"),
                    rs.getString("Capacidad")
                };
                modeloTablaMesa.addRow(fila);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar Mesaes:\n" + e.getMessage(),
                    "Error de búsqueda", JOptionPane.ERROR_MESSAGE);
        }
    }

//Método para insertar nuevo valor a las Mesaes
}
