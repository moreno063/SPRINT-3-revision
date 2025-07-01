package ODS;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.FileInputStream;
import java.io.IOException;

public class Conexion {

    public static Firestore db;
    private static boolean initialized = false;

    public static void conectarFirebase() {
        try {
            if(!initialized){
            FileInputStream sa = new FileInputStream("clave1.json");
            
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(sa))                    
                    .build();

            FirebaseApp.initializeApp(options);
            initialized = true;
            System.out.println("Exito al conectar");
            }
            db = FirestoreClient.getFirestore();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
