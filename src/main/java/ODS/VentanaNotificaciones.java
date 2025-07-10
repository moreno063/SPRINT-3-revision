package ODS;

import static ODS.Conexion.db;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.ListenerRegistration;
import java.awt.BorderLayout;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class VentanaNotificaciones extends javax.swing.JFrame {

    private ListenerRegistration notificacionesListener;
    private Firestore db;

    public VentanaNotificaciones() {
        initComponents();
        this.db = Conexion.db;
        this.setLocationRelativeTo(null);
        inicializarTabla();
        configurarListenerNotificaciones();
    }

    private void inicializarTabla() {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("De");
        model.addColumn("Fecha");
        model.addColumn("Estado");
        model.addColumn("ID");
        tblNotificaciones.setModel(model);
        tblNotificaciones.getColumnModel().getColumn(3).setMinWidth(0);
        tblNotificaciones.getColumnModel().getColumn(3).setMaxWidth(0);
        tblNotificaciones.getColumnModel().getColumn(3).setWidth(0);
    }

    private void configurarListenerNotificaciones() {
        DefaultTableModel model = (DefaultTableModel) tblNotificaciones.getModel();
        model.setRowCount(0);

        if (notificacionesListener != null) {
            notificacionesListener.remove();
        }

        notificacionesListener = db.collection("compartidos")
                .document(Login.usuarioActual)
                .collection("tablas")
                .addSnapshotListener((querySnapshot, error) -> {
                    if (error != null) {
                        System.err.println("Error en listener: " + error.getMessage());
                        SwingUtilities.invokeLater(() -> {
                            JOptionPane.showMessageDialog(this,
                                    "Error al cargar notificaciones: " + error.getMessage(),
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        });
                        return;
                    }

                    SwingUtilities.invokeLater(() -> {
                        model.setRowCount(0);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                        if (querySnapshot == null || querySnapshot.isEmpty()) {
                            model.addRow(new Object[]{"", "No hay notificaciones disponibles", "", ""});
                            return;
                        }

                        for (DocumentSnapshot doc : querySnapshot.getDocuments()) {
                            try {
                                boolean leido = doc.getBoolean("leido");
                                boolean editable = doc.getBoolean("editable");

                                model.addRow(new Object[]{
                                    doc.getString("de"),
                                    sdf.format(doc.getTimestamp("fecha").toDate()),
                                    leido ? (editable ? "✓ Editable" : "✓ Leído") : "Nuevo",
                                    doc.getId()
                                });
                            } catch (Exception e) {
                                System.err.println("Error procesando documento: " + e.getMessage());
                            }
                        }
                    });
                });
    }

    private void mostrarTablaCompartida(String usuarioOrigen, String idNotificacion,
            List<Map<String, Object>> datos) {
        JFrame frame = new JFrame("Tabla compartida por: " + usuarioOrigen);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        System.out.println("[DEBUG] Datos recibidos para mostrar:");
        
        if (datos != null) {
            for (Map<String, Object> fila : datos) {
                System.out.println("Fila: " + fila);
                System.out.println("Campos disponibles: " + fila.keySet());
            }
        }

        JTable tablaDatos = new JTable();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        String[] columnOrder = {
            "Cama",
            "Cedula",
            "Nombre",
            "Edad",
            "FechaIngreso",
            "Diagnostico",
            "Pendientes",
            "Email"};
        for (String col : columnOrder) {
            model.addColumn(col);
        }

        if (datos != null && !datos.isEmpty()) {
            for (Map<String, Object> fila : datos) {
                Object[] rowData = new Object[columnOrder.length];
                for (int i = 0; i < columnOrder.length; i++) {
                    String campo = columnOrder[i];
                    Object valor = fila.get(campo);
                    rowData[i] = (valor != null) ? valor : "";
                }
                model.addRow(rowData);
            }
        }

        tablaDatos.setModel(model);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(new JScrollPane(tablaDatos), BorderLayout.CENTER);
        panelPrincipal.setBackground(new Color(221, 255, 170));

        JPanel panelBotones = new JPanel();
        JButton btnGuardar = new JButton("Guardar Cambios");

        btnGuardar.addActionListener(e -> {
            List<Map<String, Object>> nuevosDatos = new ArrayList<>();

            for (int i = 0; i < model.getRowCount(); i++) {
                Map<String, Object> fila = new HashMap<>();
                for (int j = 0; j < model.getColumnCount(); j++) {
                    fila.put(model.getColumnName(j), model.getValueAt(i, j));
                }
                nuevosDatos.add(fila);
            }

            if (PersonaProvider.guardarCambiosTablaCompartida(
                    Login.usuarioActual, idNotificacion, nuevosDatos)) {

                JOptionPane.showMessageDialog(frame,
                        "Cambios guardados exitosamente",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);

                PersonaProvider.marcarComoLeido(Login.usuarioActual, idNotificacion);
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Error al guardar cambios",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panelBotones.add(btnGuardar);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        frame.add(panelPrincipal);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNotificaciones = new javax.swing.JTable();
        btnVer = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Notificaciones");

        tblNotificaciones.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"De", "Fecha", "Estado", "ID"}
        ) {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tblNotificaciones.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tblNotificaciones);
        if (tblNotificaciones.getColumnModel().getColumnCount() > 0) {
            tblNotificaciones.getColumnModel().getColumn(3).setMinWidth(0);
            tblNotificaciones.getColumnModel().getColumn(3).setPreferredWidth(0);
            tblNotificaciones.getColumnModel().getColumn(3).setMaxWidth(0);
        }

        btnVer.setText("Ver Tabla");
        btnVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerActionPerformed(evt);
            }
        });

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel1.setText("Tablas Compartidas");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnVer)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnCerrar))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnCerrar)
                                        .addComponent(btnVer))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
    }

    @Override
    public void dispose() {
        if (notificacionesListener != null) {
            notificacionesListener.remove();
        }
        super.dispose();
    }

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {
        int fila = tblNotificaciones.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una notificación primero");
            return;
        }

        String idNotificacion = tblNotificaciones.getValueAt(fila, 3).toString();
        String usuarioOrigen = tblNotificaciones.getValueAt(fila, 0).toString();

        db.collection("compartidos")
                .document(Login.usuarioActual)
                .collection("tablas")
                .document(idNotificacion)
                .get()
                .addListener(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            DocumentSnapshot doc = db.collection("compartidos")
                                    .document(Login.usuarioActual)
                                    .collection("tablas")
                                    .document(idNotificacion)
                                    .get()
                                    .get(); // Esto bloquea hasta que se complete

                            if (doc.exists()) {
                                List<Map<String, Object>> datos = (List<Map<String, Object>>) doc.get("datos");

                                if (!doc.getBoolean("leido")) {
                                    PersonaProvider.marcarComoLeido(Login.usuarioActual, idNotificacion);
                                }

                                SwingUtilities.invokeLater(() -> {
                                    mostrarTablaCompartida(usuarioOrigen, idNotificacion, datos);
                                });
                            }
                        } catch (Exception e) {
                            SwingUtilities.invokeLater(() -> {
                                JOptionPane.showMessageDialog(VentanaNotificaciones.this,
                                        "Error al cargar la tabla compartida: " + e.getMessage(),
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            });
                        }
                    }
                }, Runnable::run);
    }

    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnVer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblNotificaciones;
}
