package ODS;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import io.opencensus.metrics.export.Summary.Snapshot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PersonaProvider {

    CollectionReference reference;

    static Firestore db;

    static {
        Conexion.conectarFirebase();
        db = Conexion.db;
    }

    static public boolean guardarPersona(String usuario, String idPersona,
            Map<String, Object> data) {
        try {
            db.collection("Usuarios").document(usuario).collection("Personas")
                    .document(idPersona).set(data);
            return true;
        } catch (Exception e) {
            System.err.println("Error al guardar:" + e.getMessage());;
            return false;
        }

    }

    static public boolean actualizarPersona(String usuario, String idPersona,
            Map<String, Object> data) {

        try {
            db.collection("Usuarios").document(usuario).collection("Personas")
                    .document(idPersona).update(data);
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar" + e.getMessage());
            return false;
        }
    }

    static public boolean eliminarPersona(String usuario, String idPersona) {
        db = FirestoreClient.getFirestore();

        try {
            db.collection("Usuarios").document(usuario).collection("Personas").document(idPersona).delete();
            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar" + e.getMessage());
            return false;
        }
    }

    @SuppressWarnings("empty-statement")
    public static void cargarTablaPersona(JTable table, String usuario) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Hacer que todas las celdas sean editables
                return true;
            }
        };

        model.addColumn("Cama");
        model.addColumn("ID");
        model.addColumn("Cédula");
        model.addColumn("Fecha ingreso");
        model.addColumn("Nombre");
        model.addColumn("Edad");
        model.addColumn("Diagnóstico");
        model.addColumn("Pendientes");
        model.addColumn("Email");

        try {
            QuerySnapshot querySnapshot = db.collection("Usuarios")
                    .document(usuario).collection("Personas").get().get();

            for (DocumentSnapshot doc : querySnapshot.getDocuments()) {
                model.addRow(new Object[]{
                    doc.getString("Cama"),
                    doc.getId(),
                    doc.getString("Cedula"),
                    doc.getString("FechaIngreso"),
                    doc.getString("Nombre"),
                    doc.get("Edad").toString(),
                    doc.getString("Diagnostico"),
                    doc.getString("Pendientes"),
                    doc.getString("Email")
                });
            }

        } catch (Exception e) {
            System.err.println("Error al cargar la tabla: " + e.getMessage());
        }

        table.setModel(model);
    }

    public static boolean actualizarCampoPersona(String usuario, String idPersona, String campo, Object valor) {
        try {
            Map<String, Object> updates = new HashMap<>();
            updates.put(campo, valor);

            db.collection("Usuarios")
                    .document(usuario)
                    .collection("Personas")
                    .document(idPersona)
                    .update(campo, valor);
            return true;
        } catch (Exception e) {
            System.err.println("Error al actualizar campo: " + e.getMessage());
            return false;
        }
    }

    public static boolean compartirTabla(String usuarioOrigen,
            String usuarioDestino, List<Map<String, Object>> datos) {
        try {
            DocumentReference userRef = db.collection("Usuarios").document(usuarioDestino);
            DocumentSnapshot userSnapshot = userRef.get().get();
            if (!userSnapshot.exists()) {
                return false;
            }

            List<Map<String, Object>> datosFormateados = new ArrayList<>();
            for (Map<String, Object> filaOriginal : datos) {
                Map<String, Object> filaFormateada = new HashMap<>();
                filaFormateada.put("Cama", filaOriginal.get("Cama"));
                filaFormateada.put("Cedula", filaOriginal.get("Cedula"));
                filaFormateada.put("Nombre", filaOriginal.get("Nombre"));
                filaFormateada.put("Edad", filaOriginal.get("Edad"));
                filaFormateada.put("FechaIngreso", filaOriginal.get("FechaIngreso"));
                filaFormateada.put("Diagnostico", filaOriginal.get("Diagnostico"));
                filaFormateada.put("Pendientes", filaOriginal.get("Pendientes"));
                filaFormateada.put("Email", filaOriginal.get("Email"));
                datosFormateados.add(filaFormateada);
            }
            
            Map<String, Object> notificacion = new HashMap<>();
            notificacion.put("de", usuarioOrigen);
            notificacion.put("fecha", com.google.cloud.Timestamp.now());
            notificacion.put("datos", datosFormateados);
            notificacion.put("leido", false);
            notificacion.put("editable", true);

            db.collection("compartidos")
                    .document(usuarioDestino)
                    .collection("tablas")
                    .add(notificacion)
                    .get();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean guardarCambiosTablaCompartida(String usuario, String idNotificacion,
            List<Map<String, Object>> nuevosDatos) {
        try {
            DocumentReference notificacionRef = db.collection("compartidos")
                    .document(usuario).collection("tablas").document(idNotificacion);
            notificacionRef.update("datos", nuevosDatos);
            return true;
        } catch (Exception e) {
            System.err.println("Error al guardar cambios: " + e.getMessage());
            return false;
        }
    }

    public static List<Map<String, Object>> obtenerTablasCompartidas(String usuario) {
        List<Map<String, Object>> notificaciones = new ArrayList<>();
        try {
            QuerySnapshot querySnapshot = db.collection("compartidos").document(usuario)
                    .collection("tablas").get().get();
            for (DocumentSnapshot doc : querySnapshot.getDocuments()) {

                Map<String, Object> notificacion = new HashMap<>();
                notificacion.put("id", doc.getId());
                notificacion.put("de", doc.getString("de"));
                notificacion.put("fecha", doc.getTimestamp("fecha").toDate());
                notificacion.put("datos", doc.get("datos"));
                notificacion.put("leido", doc.getBoolean("leido"));
                notificacion.put("editable", doc.getBoolean("editable"));
                notificaciones.add(notificacion);
            }

        } catch (Exception e) {
            System.err.println("Error al obtener notificaciones: " + e.getMessage());
        }
        return notificaciones;

    }

    public static boolean marcarComoLeido(String usuario, String idNotificacion) {
        try {
            db.collection("compartidos").document(usuario).collection("tablas")
                    .document(idNotificacion).update("leido", true);
            return true;
        } catch (Exception e) {
            System.err.println("Error al marcar como leido: " + e.getMessage());
            return false;
        }
    }
}
