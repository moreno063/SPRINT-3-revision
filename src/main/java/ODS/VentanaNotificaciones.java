package ODS;

import static io.grpc.Context.key;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VentanaNotificaciones extends javax.swing.JFrame {

    public VentanaNotificaciones() {
        initComponents();
        this.setLocationRelativeTo(null);
        inicializarTabla();
        cargarNotificaciones();
    }
    private void inicializarTabla(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("De");
        model.addColumn("Fecha");
        model.addColumn("Estado");
        model.addColumn("ID");
        tblNotificaciones.setModel(model);
        tblNotificaciones.getColumnModel().getColumn(3).setMinWidth(0);
        tblNotificaciones.getColumnModel().getColumn(3).setMaxWidth(0);
        tblNotificaciones.getColumnModel().getColumn(3).setWidth(0);
    }
    private void cargarNotificaciones() {
        DefaultTableModel model = (DefaultTableModel) tblNotificaciones.getModel();
        model.setRowCount(0); 
        
        List<Map<String, Object>> notificaciones = 
                PersonaProvider.obtenerTablasCompartidas(Login.usuarioActual);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        
        for (Map<String, Object> notif : notificaciones) {
            model.addRow(new Object[]{
                notif.get("de"),
                sdf.format(notif.get("fecha")),
                notif.get("leido").equals(true) ? "✓" : "Nuevo",
                notif.get("id")
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblNotificaciones = new javax.swing.JTable();
        btnVer = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Notificaciones");

        tblNotificaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"De", "Fecha", "Estado", "ID"}
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
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
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerActionPerformed
        int fila = tblNotificaciones.getSelectedRow();
        if (fila < 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una notificación primero");
            return;
        }
        
        String idNotificacion = tblNotificaciones.getValueAt(fila, 3).toString();
        String usuarioOrigen = tblNotificaciones.getValueAt(fila, 0).toString();
        
        
        PersonaProvider.marcarComoLeido(Login.usuarioActual, idNotificacion);
        
        List<Map<String, Object>> notificaciones = PersonaProvider.obtenerTablasCompartidas(Login.usuarioActual);
        Map<String, Object> notificacionSeleccionada = null;
        
        for (Map<String, Object> notif : notificaciones){
            if (notif.get("id").equals(idNotificacion)){
                notificacionSeleccionada = notif;
                break;
            }
        }
        
        if(notificacionSeleccionada != null){
            @SuppressWarnings("Unchecked")
            List<Map<String, Object>> datos = (List<Map<String, Object>>) notificacionSeleccionada.get("datos");
            mostrarDatosCompartidos(usuarioOrigen, datos);
            
        }
        cargarNotificaciones();
        
    }
    
    private void mostrarDatosCompartidos(String usuarioOrigen, List<Map<String, Object>> datos){
        JFrame frame = new JFrame("Tabla compartida por: " + usuarioOrigen);
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        
        if (!datos.isEmpty()){
            for (String key : datos.get(0).keySet()){
                model.addColumn(key);
            }
        }
        for (Map<String, Object> fila :datos){
            Object[] rowData = new Object[model.getColumnCount()];
            for ( int i = 0; i <model.getColumnCount(); i++){
                String columnName = model.getColumnName(i);
                rowData[i] = fila.get(columnName);
            }
            model .addRow(rowData);
        }
        table.setModel(model);
        frame.add(new JScrollPane(table));
        frame.pack();
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
    }
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnVer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblNotificaciones;
}