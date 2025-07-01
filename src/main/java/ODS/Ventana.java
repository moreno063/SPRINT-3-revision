package ODS;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Ventana extends javax.swing.JFrame {

    public Ventana() {
        Conexion.conectarFirebase();
        initComponents();
        this.setLocationRelativeTo(null);
        PersonaProvider.cargarTablaPersona(tblPersona, Login.usuarioActual);
        txtId.setEnabled(false);

        ChoiceFunciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
            "Funciones adicionales",
            "Expandir tabla",
            "Compartir tabla",
            "Notificaciones",
            "Buscar"
        }));
        getContentPane().setLayout(new BorderLayout());
        configurarEdicionTabla();
        setVisible(true);
        verificarNotificaciones();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel10 = new javax.swing.JLabel();
        list1 = new java.awt.List();
        jPanel1 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPersona = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCama = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtPendientes = new javax.swing.JTextField();
        txtFechaIngreso = new javax.swing.JTextField();
        txtDiagnostico = new javax.swing.JTextField();
        ChoiceFunciones = new javax.swing.JComboBox<>();

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Fecha ingreso:");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(221, 255, 170));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, -1, -1));

        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, -1, -1));

        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel1.add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, -1, -1));

        btnLimpiar.setText("LIMPIAR");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 370, -1, -1));

        tblPersona.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblPersona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPersonaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPersona);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 6, 500, 158));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Cama:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 93, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Edad:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 93, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Pendientes:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 290, 140, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("ID:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 93, -1));

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 120, -1));

        txtCama.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtCama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCamaActionPerformed(evt);
            }
        });
        jPanel1.add(txtCama, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 120, -1));

        txtEdad.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtEdad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEdadActionPerformed(evt);
            }
        });
        jPanel1.add(txtEdad, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 120, -1));

        txtId.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });
        jPanel1.add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 120, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Email:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, 93, -1));

        txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });
        jPanel1.add(txtEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, 120, -1));
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 280, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon("C:\\Users\\USUARIO\\Documents\\NetBeansProjects\\tutorial1\\src\\logo1.png")); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, -1, -1));

        btnSalir.setBackground(new java.awt.Color(255, 51, 0));
        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSalir.setText("x");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 40, 40));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Nombre:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 93, -1));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Fecha ingreso:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 140, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Diagnostico:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 250, 140, -1));
        jPanel1.add(txtPendientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, 120, 30));
        jPanel1.add(txtFechaIngreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, 120, 30));

        txtDiagnostico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiagnosticoActionPerformed(evt);
            }
        });
        jPanel1.add(txtDiagnostico, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 250, 120, 30));

        ChoiceFunciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ChoiceFunciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChoiceFuncionesActionPerformed(evt);
            }
        });
        jPanel1.add(ChoiceFunciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 60, 170, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtCamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCamaActionPerformed
    }//GEN-LAST:event_txtCamaActionPerformed

    private void txtEdadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEdadActionPerformed
    }//GEN-LAST:event_txtEdadActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
    }//GEN-LAST:event_txtIdActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardar();

    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizar();

    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
    }//GEN-LAST:event_txtEmailActionPerformed

    private void tblPersonaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPersonaMouseClicked
        int filaSeleccionada = tblPersona.getSelectedRow();
        if (filaSeleccionada >= 0 && !editandoDesdeTabla) {
            txtCama.setText(tblPersona.getValueAt(filaSeleccionada, 0).toString());
            txtId.setText(tblPersona.getValueAt(filaSeleccionada, 1).toString());
            txtFechaIngreso.setText(tblPersona.getValueAt(filaSeleccionada, 2).toString());
            txtNombre.setText(tblPersona.getValueAt(filaSeleccionada, 3).toString());
            txtEdad.setText(tblPersona.getValueAt(filaSeleccionada, 4).toString());
            txtDiagnostico.setText(tblPersona.getValueAt(filaSeleccionada, 5).toString());
            txtPendientes.setText(tblPersona.getValueAt(filaSeleccionada, 6).toString());
            txtEmail.setText(tblPersona.getValueAt(filaSeleccionada, 7).toString());
        }

    }//GEN-LAST:event_tblPersonaMouseClicked

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        clearForm();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        int opcion = JOptionPane.showConfirmDialog(this, "¿Desea cerrar sesion?",
                "Cerrar sesion", JOptionPane.YES_NO_OPTION);
        if (opcion == JOptionPane.YES_OPTION) {
            this.dispose();
            new Login().setVisible(true);
        }

    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtDiagnosticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiagnosticoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiagnosticoActionPerformed

    private void ChoiceFuncionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChoiceFuncionesActionPerformed
        String seleccion = (String) ChoiceFunciones.getSelectedItem();

        switch (seleccion) {
            case "Expandir tabla":
                expandirTabla();
                break;
            case "Compartir tabla":
                compartirTabla();
                break;
            case "Notificaciones":
                new VentanaNotificaciones().setVisible(true);
                break;
            case "Buscar":
                mostrarDialogoBusqueda();
                break;
        }
        ChoiceFunciones.setSelectedIndex(0);

    }//GEN-LAST:event_ChoiceFuncionesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ChoiceFunciones;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.List list1;
    private javax.swing.JTable tblPersona;
    private javax.swing.JTextField txtCama;
    private javax.swing.JTextField txtDiagnostico;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFechaIngreso;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPendientes;
    // End of variables declaration//GEN-END:variables

    private void guardar() {
        try {
            String edadText = txtEdad.getText().trim();
            if (edadText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo Edad no puede estar vacío");
                return;
            }
            if (!edadText.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "La edad debe ser un número entero válido");
                return;
            }
            int edad = Integer.parseInt(edadText);

            Map<String, Object> datos = new HashMap<>();

            datos.put("Cama", txtCama.getText());
            datos.put("FechaIngreso", txtFechaIngreso.getText());
            datos.put("Nombre", txtNombre.getText());
            datos.put("Edad", edad);
            datos.put("Diagnostico", txtDiagnostico.getText());
            datos.put("Pendientes", txtPendientes.getText());
            datos.put("Email", txtEmail.getText());

            String idPersona = txtId.getText().isEmpty()
                    ? String.valueOf((int) (Math.random() * 100000))
                    : txtId.getText();

            if (PersonaProvider.guardarPersona(Login.usuarioActual, idPersona, datos)) {
                JOptionPane.showMessageDialog(null, "Guardado con exito");
                clearForm();
                PersonaProvider.cargarTablaPersona(tblPersona, Login.usuarioActual);
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Edad debe ser un numero valido");
        }

    }

    private void actualizar() {
        try {
            String edadText = txtEdad.getText().trim();
            if (edadText.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El campo Edad no puede estar vacío");
                return;
            }

            if (!edadText.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "La edad debe ser un número entero válido");
                return;
            }
            int edad = Integer.parseInt(edadText);

            Map<String, Object> datos = new HashMap<>();
            datos.put("Cama", txtCama.getText());  // Como String
            datos.put("FechaIngreso", txtFechaIngreso.getText());
            datos.put("Nombre", txtNombre.getText());
            datos.put("Edad", edad);
            datos.put("Diagnostico", txtDiagnostico.getText());
            datos.put("Pendientes", txtPendientes.getText());
            datos.put("Email", txtEmail.getText());

            if (PersonaProvider.actualizarPersona(Login.usuarioActual,
                    txtId.getText(), datos)) {
                JOptionPane.showMessageDialog(this, "Actualizado con exito");
                PersonaProvider.cargarTablaPersona(tblPersona, Login.usuarioActual);
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Edad debe ser un numero valido");
        }
    }

    private void eliminar() {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Eliminar este registro?",
                "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (PersonaProvider.eliminarPersona(Login.usuarioActual, txtId.getText())) {
                JOptionPane.showMessageDialog(this, "Eliminado exitosamente");
                clearForm();
                PersonaProvider.cargarTablaPersona(tblPersona, Login.usuarioActual);
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar");

            }
        }
    }
    private boolean tablaExpandida = false;
    private JPanel panelOriginal;
    private Rectangle scrollPaneOriginalBounds;
    private Map<Component, Object> originalComponents;
    private org.netbeans.lib.awtextra.AbsoluteLayout originalLayout;

    private void expandirTabla() {
        if (!tablaExpandida) {
            guardarEstadoOriginal();
            JPanel panelExpandido = new JPanel(new BorderLayout());
            jPanel1.remove(jScrollPane1);
            panelExpandido.add(jScrollPane1, BorderLayout.CENTER);

            JButton btnReducir = new JButton("Reducir Vista");
            btnReducir.addActionListener(e -> {
                getContentPane().removeAll();
                getContentPane().add(panelOriginal);
                restaurarEstadoOriginal();
                tablaExpandida = false;
                revalidate();
                repaint();
            });
            panelExpandido.add(btnReducir, BorderLayout.SOUTH);
            getContentPane().removeAll();
            getContentPane().add(panelExpandido);
            tablaExpandida = true;

        } else {
            getContentPane().removeAll();
            getContentPane().add(panelOriginal);
            restaurarEstadoOriginal();
            tablaExpandida = false;
        }
        revalidate();
        repaint();
    }

    private void guardarEstadoOriginal() {
        panelOriginal = jPanel1;
        scrollPaneOriginalBounds = jScrollPane1.getBounds();

        originalLayout = (org.netbeans.lib.awtextra.AbsoluteLayout) jPanel1.getLayout();

        originalComponents = new HashMap<>();
        for (Component comp : jPanel1.getComponents()) {
            originalComponents.put(comp, new org.netbeans.lib.awtextra.AbsoluteConstraints(
                    comp.getBounds().x,
                    comp.getBounds().y,
                    comp.getBounds().width,
                    comp.getBounds().height
            ));
        }
    }

    private void restaurarEstadoOriginal() {
        Container currentParent = jScrollPane1.getParent();
        if (currentParent != null) {
            currentParent.remove(jScrollPane1);
        }
        jPanel1.removeAll();
        for (Map.Entry<Component, Object> entry : originalComponents.entrySet()) {
            jPanel1.add(entry.getKey(), (org.netbeans.lib.awtextra.AbsoluteConstraints) entry.getValue());
        }
        jPanel1.setLayout(originalLayout);
        ChoiceFunciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
            "Funciones adicionales",
            "Expandir tabla",
            "Compartir tabla",
            "Notificaciones",
            "Buscar"
        }));

    }
    private boolean editandoDesdeTabla = false;

    private void configurarEdicionTabla() {
        tblPersona.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE && !editandoDesdeTabla) {
                try {
                    editandoDesdeTabla = true;

                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    if (row < 0 || column < 0) {
                        return;
                    }

                    String idPersona = tblPersona.getValueAt(row, 1).toString();
                    String campo = "";

                    switch (column) {
                        case 0:
                            campo = "Cama";
                            break;
                        case 2:
                            campo = "FechaIngreso";
                            break;
                        case 3:
                            campo = "Nombre";
                            break;
                        case 4:
                            campo = "Edad";
                            break;
                        case 5:
                            campo = "Diagnostico";
                            break;
                        case 6:
                            campo = "Pendientes";
                            break;
                        case 7:
                            campo = "Email";
                            break;
                        default:
                            return;
                    }
                    Object valor = tblPersona.getValueAt(row, column);

                    if (campo.equals("Edad")) {
                        try {
                            Integer.parseInt(valor.toString());
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "La edad debe ser un numero valido");

                            PersonaProvider.cargarTablaPersona(tblPersona, Login.usuarioActual);
                            return;
                        }
                    }
                    if (PersonaProvider.actualizarCampoPersona(Login.usuarioActual, idPersona, campo, valor)) {
                        if (campo.equals("Cama")) {
                            txtCama.setText(valor.toString());
                        } else if (campo.equals("Nombre")) {
                            txtNombre.setText(valor.toString());
                        } else if (campo.equals("Edad")) {
                            txtEdad.setText(valor.toString());
                        } else if (campo.equals("Diagnostico")) {
                            txtDiagnostico.setText(valor.toString());
                        } else if (campo.equals("Pendientes")) {
                            txtPendientes.setText(valor.toString());
                        } else if (campo.equals("Email")) {
                            txtEmail.setText(valor.toString());
                        } else if (campo.equals("FechaIngreso")) {
                            txtFechaIngreso.setText(valor.toString());
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Error al actualizar campo");
                        PersonaProvider.cargarTablaPersona(tblPersona, Login.usuarioActual);
                    }
                } finally {
                    editandoDesdeTabla = false;
                }
            }
        });
    }

    private void mostrarDialogoBusqueda() {
        JTextField txtBuscar = new JTextField(20);

        JPanel panelBusqueda = new JPanel();
        panelBusqueda.add(new JLabel("Texto a buscar: "));
        panelBusqueda.add(txtBuscar);

        int resultado = JOptionPane.showConfirmDialog(this, panelBusqueda,
                "Buscar en todos los campos", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (resultado == JOptionPane.OK_OPTION) {
            String texto = txtBuscar.getText().trim();

            if (!texto.isEmpty()) {
                buscarEnTabla(texto);
            } else {
                DefaultTableModel model = (DefaultTableModel) tblPersona.getModel();
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
                tblPersona.setRowSorter(sorter);
                sorter.setRowFilter(null);
            }
        }

    }

    private void buscarEnTabla(String texto) {
        DefaultTableModel model = (DefaultTableModel) tblPersona.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        tblPersona.setRowSorter(sorter);

        // Crear filtro que busque en todas las columnas
        RowFilter<DefaultTableModel, Object> filter = new RowFilter<DefaultTableModel, Object>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Object> entry) {
                for (int i = entry.getValueCount() - 1; i >= 0; i--) {
                    if (entry.getStringValue(i).toLowerCase().contains(texto.toLowerCase())) {
                        return true;
                    }
                }
                return false;
            }
        };
        sorter.setRowFilter(filter);

        if (tblPersona.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "No se encontraron coincidencias para: " + texto);
        }
    }

    private void compartirTabla() {
        String usuarioDestino = JOptionPane.showInputDialog(this, "Ingrese el nombre de usuario con quien compartir:",
                "Compartir tabla", JOptionPane.PLAIN_MESSAGE);
        if (usuarioDestino == null || usuarioDestino.trim().isEmpty()) {
            return;
        }

        usuarioDestino = usuarioDestino.trim();

            
        if (usuarioDestino.equals(Login.usuarioActual)){
            JOptionPane.showMessageDialog(this, "No puedes compartir la tabla contigo mismo",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        DefaultTableModel model = (DefaultTableModel) tblPersona.getModel();
        List<Map<String, Object>> datosTabla = new ArrayList<>();

        for (int i = 0; i < model.getRowCount(); i++) {
            Map<String, Object> fila = new HashMap<>();
            for (int j = 0; j < model.getColumnCount(); j++) {
                fila.put(model.getColumnName(j), model.getValueAt(i, j));
            }
            datosTabla.add(fila);
        }

        if (PersonaProvider.compartirTabla(Login.usuarioActual, usuarioDestino, datosTabla)) {
            JOptionPane.showMessageDialog(this, "Tabla compartida exitosamente con: " + usuarioDestino, "Exito"
                    ,JOptionPane.INFORMATION_MESSAGE );
        } else {
            JOptionPane.showMessageDialog(this, "Error al compartir la tabla"
                    , "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void verificarNotificaciones() {
        new Thread(() -> {
            while (true) {
                try {
                    List<Map<String, Object>> notificaciones
                            = PersonaProvider.obtenerTablasCompartidas(Login.usuarioActual);

                    long noLeidas = notificaciones.stream()
                            .filter(notif -> !(boolean) notif.get("leido"))
                            .count();

                    if (noLeidas > 0) {
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(this,
                                    "Tienes " + noLeidas + " tablas compartidas nuevas",
                                    "Nuevas notificaciones",
                                    JOptionPane.INFORMATION_MESSAGE);
                        });
                    }

                    Thread.sleep(300000); // Verificar cada 5 minutos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    void clearForm() {
        txtId.setText("");
        txtNombre.setText("");
        txtCama.setText("");
        txtEdad.setText("");
        txtDiagnostico.setText("");
        txtPendientes.setText("");
        txtEmail.setText("");
        txtFechaIngreso.setText("");
    }

}
