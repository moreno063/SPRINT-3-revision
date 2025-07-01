package ODS;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class UsuarioProvider {
    static Firestore db;
    static {
        Conexion.conectarFirebase();
        db = Conexion.db;
    }
    public static boolean registrarUsuario (String usuario, Map<String, Object>data){
        try {
            DocumentReference userRef = db.collection("Usuarios").document(usuario);
            
            if (userRef.get().get().exists()){
                return false;
            }
            userRef.set(data);
            userRef.collection("Personas").document("inicial").set(Map.of("timestamp", new Date()));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } 
    }
    
    public static boolean validarCredenciales(String usuario, String password){
        try {
            if(db == null){
                db = FirestoreClient.getFirestore();
                if(db==null){
                    throw new Exception ("No se pudo conectar a Firebase");
                }
            }
            DocumentReference docRef = db.collection("Usuarios").document(usuario);
            DocumentSnapshot document = docRef.get().get();
            
            if (document.exists()){
                String storedPassword = document.getString("password");
                return password.equals(storedPassword);
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error al validar credenciales" + e.getMessage());
            return false;
        }
    }
}   
