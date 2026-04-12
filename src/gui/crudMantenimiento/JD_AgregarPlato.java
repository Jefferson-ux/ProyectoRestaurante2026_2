
package gui.crudMantenimiento;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.dao.DetallePedidoMethod;
import logic.dao.DetallePedidoMethod.DetallePedido;
import gui.crudMantenimiento.Frm_Pedido;



public final class JD_AgregarPlato extends javax.swing.JDialog {
    
    private boolean cargandoCombo = false;
    LocalDate current_date; 
    DetallePedidoMethod objetoMetodo;
    
    DefaultTableModel modeloTabla = new DefaultTableModel();
    
    int idPedidoLocal;
    
    String fecha_pedido;
    
    public JD_AgregarPlato(java.awt.Frame parent, boolean modal, int idPedido,String fecha) {
        super(parent, modal);
        FlatLightLaf.setup();
        this.objetoMetodo = new DetallePedidoMethod();
        initComponents();
        
        this.setLocationRelativeTo(this);
        this.setTitle("Pedidos");
        this.setResizable(false);
        this.current_date = LocalDate.now();
        this.fecha_pedido = fecha;
        
        String[] header = {"ID Detalle","Plato","Cantidad","Precio","Subtotal","Observaciones"};
        modeloTabla.setColumnIdentifiers(header);
        JTABLE_Dialog.setModel(modeloTabla);
        Table_Size();
        cargarCombo();
        
        
        
        this.idPedidoLocal = idPedido;
                
        calcularSubtotalRapido();
        mostrarIdPedido();
        this.txtIdPedido.setEditable(false);
        if (idPedidoLocal>0){
            MostrarDetalles(idPedidoLocal);
        }
        txtFechaRegistro.setText(fecha);
        
        
    }
    
    private void Table_Size() {
        // ID: Lo ocultamos
        TableColumnModel colModel = JTABLE_Dialog.getColumnModel();
 
        colModel.getColumn(0).setMinWidth(0);
        colModel.getColumn(0).setPreferredWidth(0);
        colModel.getColumn(0).setMaxWidth(0);
        
        colModel.getColumn(1).setMinWidth(110);
        colModel.getColumn(1).setPreferredWidth(130);

        colModel.getColumn(2).setMinWidth(50);
        colModel.getColumn(2).setPreferredWidth(50);

        colModel.getColumn(3).setMinWidth(60);
        colModel.getColumn(3).setPreferredWidth(60);

        colModel.getColumn(4).setMinWidth(60);
        colModel.getColumn(4).setPreferredWidth(60);

        colModel.getColumn(5).setMinWidth(100);
        colModel.getColumn(5).setPreferredWidth(150);
        
        JTABLE_Dialog.getTableHeader().setReorderingAllowed(false); // No mover columnas
        JTABLE_Dialog.setRowHeight(25); // Filas más altas para que respire el diseño
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtFechaRegistro = new javax.swing.JTextField();
        cmbPlatillo = new javax.swing.JComboBox<>();
        spnCantidad = new javax.swing.JSpinner();
        scrollObs = new javax.swing.JScrollPane();
        txtObservaciones = new javax.swing.JTextArea();
        txtSubtotal = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIdPedido = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTABLE_Dialog = new javax.swing.JTable();
        btn_guardar = new javax.swing.JButton();
        btn_anadir = new javax.swing.JButton();
        btn_actualizar = new javax.swing.JButton();
        btn_quitar = new javax.swing.JButton();
        btn_salir = new javax.swing.JButton();
        txtPrecio = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtFechaRegistro.setEditable(false);
        txtFechaRegistro.setBackground(new java.awt.Color(255, 255, 255));
        txtFechaRegistro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtFechaRegistro.setForeground(new java.awt.Color(0, 0, 204));
        txtFechaRegistro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFechaRegistro.setText("13/12/2026");
        txtFechaRegistro.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtFechaRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaRegistroActionPerformed(evt);
            }
        });
        jPanel1.add(txtFechaRegistro, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 160, 30));

        cmbPlatillo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbPlatillo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPlatilloItemStateChanged(evt);
            }
        });
        jPanel1.add(cmbPlatillo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 190, 30));

        spnCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, 50, 1));
        spnCantidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnCantidadStateChanged(evt);
            }
        });
        jPanel1.add(spnCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 50, 120, 30));

        txtObservaciones.setColumns(20);
        txtObservaciones.setRows(5);
        scrollObs.setViewportView(txtObservaciones);

        jPanel1.add(scrollObs, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 170, 450, 60));

        txtSubtotal.setEditable(false);
        txtSubtotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtSubtotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSubtotal.setFocusable(false);
        txtSubtotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSubtotalActionPerformed(evt);
            }
        });
        jPanel1.add(txtSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 130, 120, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel12.setText("Platillo:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 60, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel7.setText("Cantidad");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 50, 80, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel6.setText("Observaciones (Opcional):");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 160, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel8.setText("Precio Unitario");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 90, 90, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel2.setText("Subtotal");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 90, -1));

        txtIdPedido.setBackground(new java.awt.Color(255, 255, 255));
        txtIdPedido.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtIdPedido.setForeground(new java.awt.Color(0, 0, 204));
        txtIdPedido.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtIdPedido.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtIdPedido.setFocusable(false);
        txtIdPedido.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                txtIdPedidoInputMethodTextChanged(evt);
            }
        });
        jPanel1.add(txtIdPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 120, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Codigo de Pedido Actual");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 540, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel9.setText("Fecha de Registro:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 130, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jLabel13.setText("Codigo de Pedido Actual:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 170, -1));

        JTABLE_Dialog.setModel(new javax.swing.table.DefaultTableModel(
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
        JTABLE_Dialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTABLE_DialogMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTABLE_Dialog);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 610, 170));

        btn_guardar.setText("Guardar");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 430, 100, 40));

        btn_anadir.setText("Añadir");
        btn_anadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_anadirActionPerformed(evt);
            }
        });
        jPanel1.add(btn_anadir, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 100, 40));

        btn_actualizar.setText("Actualizar");
        btn_actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_actualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_actualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 430, 100, 40));

        btn_quitar.setText("Quitar");
        btn_quitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_quitarActionPerformed(evt);
            }
        });
        jPanel1.add(btn_quitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 430, 100, 40));

        btn_salir.setText("Salir");
        btn_salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_salirActionPerformed(evt);
            }
        });
        jPanel1.add(btn_salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 430, 100, 40));

        txtPrecio.setEditable(false);
        txtPrecio.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        txtPrecio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPrecio.setText("0");
        jPanel1.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 120, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, 490));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFechaRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaRegistroActionPerformed

    private void btn_salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_salirActionPerformed
         int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de que deseas cerrar el formulario?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            // Limpiamos tabla
            modeloTabla.setRowCount(0);

            // Limpiamos campos de texto y spinners
            txtPrecio.setText("0.00");
            txtSubtotal.setText("0.00");
            spnCantidad.setValue(1);
            txtObservaciones.setText("");
    this.dispose();

        }

    }//GEN-LAST:event_btn_salirActionPerformed

    private void spnCantidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnCantidadStateChanged
        calcularSubtotalRapido();
    }//GEN-LAST:event_spnCantidadStateChanged

    
    
    
    
    
    private void btn_anadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_anadirActionPerformed
                       
    try {
        // 1. Validar que se haya seleccionado un platillo
        if (cmbPlatillo.getSelectedIndex() <= 0) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un platillo.");
            return;
        }

        // 2. Obtener el nombre y el ID real del plato usando tu objetoMetodo
        String nombreSeleccionado = cmbPlatillo.getSelectedItem().toString();
        int idPlatillo = objetoMetodo.obtenerIdPlatilloPorNombre(nombreSeleccionado);

        if (idPlatillo == -1) {
            JOptionPane.showMessageDialog(this, "Error: No se pudo encontrar el ID del plato.");
            return;
        }

        // 3. Capturar cantidad y precio
        int cantidad = (Integer) spnCantidad.getValue();
        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a cero.");
            return;
        }

        // Limpiamos el texto de precio (quitamos S/. si lo tiene) y convertimos
        double precio = Double.parseDouble(txtPrecio.getText().replaceAll("[^0-9.]", ""));
        double subtotal = cantidad * precio;

        // 4. Capturar observaciones
        String obs = txtObservaciones.getText().trim();
        if (obs.isEmpty()) {
            obs = "Sin observaciones";
        }

        // 5. Agregar la fila a JTABLE_Dialog
        // Estructura: {"ID","Plato","Cantidad","Precio","Subtotal","Observaciones"}
        DefaultTableModel modelo = (DefaultTableModel) JTABLE_Dialog.getModel();
        Object[] fila = {
            idPlatillo, 
            nombreSeleccionado, 
            cantidad, 
            String.format("S/. %.2f", precio),
            String.format("S/. %.2f", subtotal),
            obs
        };
        
        modelo.addRow(fila);

        // 6. Limpiar campos de entrada para el siguiente plato (sin cerrar el diálogo)
        limpiarCamposDetalle();
        cmbPlatillo.requestFocus();

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(this, "Error de base de datos: " + ex.getMessage());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Error en el formato del precio.");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage());
    }
  
    }//GEN-LAST:event_btn_anadirActionPerformed

    private void txtSubtotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSubtotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSubtotalActionPerformed

    private void cmbPlatilloItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPlatilloItemStateChanged
        String plato = (String) cmbPlatillo.getSelectedItem();
        String param = null;
        try {
            param = String.valueOf(objetoMetodo.obtenerPrecioPorPlatoMenu(plato));
        } catch (SQLException ex) {
            Logger.getLogger(JD_AgregarPlato.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.txtPrecio.setText(param);
        calcularSubtotalRapido();
    }//GEN-LAST:event_cmbPlatilloItemStateChanged

    private void txtIdPedidoInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_txtIdPedidoInputMethodTextChanged
        
    }//GEN-LAST:event_txtIdPedidoInputMethodTextChanged

    private void btn_quitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_quitarActionPerformed
                                         
    // 1. Obtener el índice de la fila seleccionada
    int filaSeleccionada = JTABLE_Dialog.getSelectedRow();

    // 2. Validar que realmente haya una fila seleccionada
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Por favor, selecciona el plato que deseas quitar de la lista.");
        return;
    }

    // 3. (Opcional) Confirmar la eliminación
    int confirmar = JOptionPane.showConfirmDialog(this, 
            "¿Estás seguro de quitar este platillo del pedido?", 
            "Confirmar", JOptionPane.YES_NO_OPTION);

    if (confirmar == JOptionPane.YES_OPTION) {
        // 4. Eliminar la fila del modelo de la tabla
        modeloTabla.removeRow(filaSeleccionada);
        
        // 5. Limpiar los campos de edición para evitar confusiones
        limpiarCamposDetalle();
        
        JOptionPane.showMessageDialog(this, "Platillo quitado de la lista temporal.");
    }

    }//GEN-LAST:event_btn_quitarActionPerformed

    private void btn_actualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_actualizarActionPerformed
                                                   
    int filaSeleccionada = JTABLE_Dialog.getSelectedRow();
    
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, "Selecciona una fila para modificar.");
        return;
    }

    // --- VALIDACIÓN CRÍTICA ---
    if (cmbPlatillo.getSelectedIndex() <= 0) { // Si es 0, es "<<Seleccionar>>"
        JOptionPane.showMessageDialog(this, "Por favor, elija un platillo válido de la lista.");
        return;
    }

  

    try {
        // Captura de datos
        String plato = cmbPlatillo.getSelectedItem().toString();
        int idPlatillo = objetoMetodo.obtenerIdPlatilloPorNombre(plato);
        int cantidad = (Integer) spnCantidad.getValue();
        
        String strPrecio = txtPrecio.getText().replace("S/. ", "").replace(",", "");
        double precio = Double.parseDouble(strPrecio);
        double subtotal = precio * cantidad;
        
        String obs = txtObservaciones.getText().trim();
        if (obs.isEmpty()) obs = "Sin observaciones";

        int filaModelo = JTABLE_Dialog.convertRowIndexToModel(filaSeleccionada);

        // --- ACTUALIZACIÓN DE COLUMNAS (Índices Corregidos) ---
        modeloTabla.setValueAt(idPlatillo, filaModelo, 0); // ID Detalle
        modeloTabla.setValueAt(plato, filaModelo, 1);      // Nombre Platillo
        modeloTabla.setValueAt(cantidad, filaModelo, 2);   // Cantidad
        modeloTabla.setValueAt(String.format("S/. %.2f", precio), filaModelo, 3);
        modeloTabla.setValueAt(String.format("S/. %.2f", subtotal), filaModelo, 4);
        modeloTabla.setValueAt(obs, filaModelo, 5);        // Observaciones (Última columna)

        JOptionPane.showMessageDialog(this, "Fila actualizada.");
        limpiarCamposDetalle();
        JTABLE_Dialog.clearSelection();

    } catch (Exception e) {
        // Esto te dirá si aún hay algún índice mal
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }

    }//GEN-LAST:event_btn_actualizarActionPerformed

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
                                        
    // 1. Obtener el modelo de la tabla del JDialog (el carrito actual)
    DefaultTableModel modeloDialog = (DefaultTableModel) JTABLE_Dialog.getModel();
    
    // 2. Validación de seguridad
    if (modeloDialog.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "La lista de platos está vacía. Añada al menos uno.");
        return;
    }

    // 3. Referenciar al padre (Frm_Pedido) y su tabla de detalles física
    Frm_Pedido padre = (Frm_Pedido) this.getOwner();
    DefaultTableModel modeloDestino = (DefaultTableModel) padre.Table_Details.getModel();

    // 4. Trasladar la información fila por fila
    for (int i = 0; i < modeloDialog.getRowCount(); i++) {
        // Extraemos los valores directamente como Objetos/Strings
        Object id    = modeloDialog.getValueAt(i, 0);
        Object plato = modeloDialog.getValueAt(i, 1);
        Object cant  = modeloDialog.getValueAt(i, 2);
        
        // Pasamos el texto tal cual está en la celda (con el "S/." incluido)
        Object precio   = modeloDialog.getValueAt(i, 3); 
        Object subtotal = modeloDialog.getValueAt(i, 4);
        
        Object obs = modeloDialog.getValueAt(i, 5);

        // 5. Crear la fila para la tabla Table_Details en Frm_Pedido
        // El orden debe ser: {"ID Detalle","Plato","Cantidad","Precio","Subtotal","Observaciones"}
        Object[] filaParaPadre = {
            id,
            plato,
            cant,
            precio,   // Se pasa como String "S/. 00.00"
            subtotal, // Se pasa como String "S/. 00.00"
            obs
        };

        // Agregamos la fila a la tabla del formulario principal
        modeloDestino.addRow(filaParaPadre);
    }

    // 6. Limpiar la tabla del diálogo para que quede vacía la próxima vez
    modeloDialog.setRowCount(0);
    
    // 7. Cerrar el JDialog
    this.dispose(); 


    }//GEN-LAST:event_btn_guardarActionPerformed

    private void JTABLE_DialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTABLE_DialogMouseClicked
        if (!JTABLE_Dialog.isEnabled()) {
            return;
        }
        /*
        int selectRow = JTABLE_Dialog.getSelectedRow();

        //String[] header = {"ID Detalle","Plato","Cantidad","Precio","Subtotal","Observaciones"};
        if (selectRow >= 0) {
            // String id = JTABLE_Dialog.getValueAt(selectRow, 0).toString().trim();
            String platillo = JTABLE_Dialog.getValueAt(selectRow, 1).toString().trim();
            String cantidad = JTABLE_Dialog.getValueAt(selectRow, 2).toString().trim();
            // String precio = JTABLE_Dialog.getValueAt(selectRow, 3).toString().trim();
            // String subtotal = JTABLE_Dialog.getValueAt(selectRow, 4).toString().trim();
            String observaciones = JTABLE_Dialog.getValueAt(selectRow, 5).toString().trim();

            txtIdPedido.setText(id);
            txtDNICliente.setText(dni_cliente);
            txtDNIEmpleado.setText(dni_empleado);
            //String categoriaOriginal = categoria;
            boolean find = false;
            for (int i=0;i<comboTipoPedido.getItemCount();i++){
                String item = comboTipoPedido.getItemAt(i).trim();
                if (item.equalsIgnoreCase(tipo_pedido)){
                    comboTipoPedido.setSelectedIndex(i);
                    find=true;
                    break;
                }

            }
            txtFechaPedido.setText(fecha_pedido);

            String nombresC = JTABLE_Dialog.getValueAt(selectRow, 8).toString().trim();
            String apellidosC = JTABLE_Dialog.getValueAt(selectRow, 9).toString().trim();
            String nombresE = JTABLE_Dialog.getValueAt(selectRow, 10).toString().trim();
            String apellidosE = JTABLE_Dialog.getValueAt(selectRow, 11).toString().trim();

            txtNombresCliente.setText(nombresC);
            txtNombresEmpleado.setText(nombresE);
            txtApellidosCliente.setText(apellidosC);
            txtApellidosEmpleado.setText(apellidosE);

            BTN_Guardar.setEnabled(false);
            BTN_VerPlatos.setEnabled(false);
            BTN_Modificar.setEnabled(true);
            BTN_Desactivar.setEnabled(true);

        }*/
    }//GEN-LAST:event_JTABLE_DialogMouseClicked

    
    
    
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
            java.util.logging.Logger.getLogger(JD_AgregarPlato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JD_AgregarPlato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JD_AgregarPlato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JD_AgregarPlato.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JD_AgregarPlato dialog = new JD_AgregarPlato(new javax.swing.JFrame(), true, -1, "");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTable JTABLE_Dialog;
    private javax.swing.JButton btn_actualizar;
    private javax.swing.JButton btn_anadir;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_quitar;
    private javax.swing.JButton btn_salir;
    private javax.swing.JComboBox<String> cmbPlatillo;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane scrollObs;
    private javax.swing.JSpinner spnCantidad;
    private javax.swing.JTextField txtFechaRegistro;
    private javax.swing.JTextField txtIdPedido;
    private javax.swing.JTextArea txtObservaciones;
    private javax.swing.JFormattedTextField txtPrecio;
    private javax.swing.JFormattedTextField txtSubtotal;
    // End of variables declaration//GEN-END:variables
    
    private void calcularSubtotalRapido() {
        double p = Double.parseDouble(txtPrecio.getText());
        int c = (Integer) spnCantidad.getValue();
        txtSubtotal.setText(String.format("%.2f", p * c));
    }
    
 public void cargarCombo() {
    cargandoCombo = true; // Bloqueamos los eventos
    try {
        cmbPlatillo.removeAllItems();
        cmbPlatillo.addItem("<<Seleccionar>>");
        ResultSet rs = objetoMetodo.comboListarPlatillos();
        
        while (rs.next()) {
            cmbPlatillo.addItem(rs.getString(1));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al cargar platos: " + e.getMessage());
    } finally {
        cargandoCombo = false; // Liberamos
    }
}
   
    
    private void limpiarCamposDetalle() {
        spnCantidad.setValue(1);
        txtObservaciones.setText("");
        cmbPlatillo.setSelectedIndex(0);
    }
    
    private void mostrarIdPedido (){
        if (idPedidoLocal<=0){
            this.txtIdPedido.setText("PENDIENTE");
        } else {
            this.txtIdPedido.setText(String.valueOf(idPedidoLocal));
        }
    }
    
    //private void llenar 
    
public void MostrarDetalles(int idPedido) {
    // 1. Limpiar y preparar
    JTABLE_Dialog.setAutoCreateRowSorter(true);
    modeloTabla.setRowCount(0);
    
    // 2. Llamar al método que retorna la LISTA de objetos
    List<DetallePedido> lista = this.objetoMetodo.listarDetalles(idPedido);
    
    // 3. Recorrer la lista con un for-each
    for (DetallePedido d : lista) {
        Object[] fila = {
            d.idDetalle,
            d.nombrePlato,
            d.cantidad,
            String.format("S/ %.2f", d.precioUnitario), // Formateamos aquí en Java
            String.format("S/ %.2f", d.subtotal),
            d.observacion
        };
        modeloTabla.addRow(fila);
    }
}
    

        public void detalleEmpty() {
    // 1. Limpiar campos de texto
    txtFechaRegistro.setText(String.valueOf(current_date));
    txtPrecio.setText("");
    txtObservaciones.setText("");
    spnCantidad.setValue(1);
    
    if (cmbPlatillo.getItemCount() > 0) {
        cmbPlatillo.setSelectedIndex(0);
    }
    
}
    

}
