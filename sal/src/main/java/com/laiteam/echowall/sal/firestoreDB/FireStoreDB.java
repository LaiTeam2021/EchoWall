package com.laiteam.echowall.sal.firestoreDB;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.Timestamp;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
public class FireStoreDB {

    private final Firestore db;

    public FireStoreDB(){
        InputStream serviceAccount = null;
        GoogleCredentials credentials = null;
        try {
            serviceAccount = new FileInputStream("sal/src/main/resources/developerforfun-bc209-firebase-adminsdk-6wbk9-b18af7ed00.json");
            credentials = GoogleCredentials.fromStream(serviceAccount);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp.initializeApp(options);
        db = FirestoreClient.getFirestore();
    }

    public void triggerVerificationCodeEmail(String email, String code) {
        db.collection("mail").document(email).delete();
        DocumentReference docRef = db.collection("mail").document(email);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        Map<String, Object> data = new HashMap<>();
        data.put("to", email);
        Map<String, Object> messageBody = new HashMap<>();
        messageBody.put("subject", "No-reply");
        messageBody.put("text", "Your varification code is: " + code + ", please verify in 10 minutes.");
        //messageBody.put("html", "This is the <code>HTML</code> section of the email body.");
        messageBody.put("code", code);
        data.put("message", messageBody);
        ApiFuture<WriteResult> result = docRef.set(data);
    }

    public boolean checkVerificationCode(String email, String code){
        DocumentReference docRef = db.collection("mail").document(email);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = null;
        try {
            document = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Mail mail = null;
        if (!document.exists()) {
            // convert document to POJO
            return false;

        }
        mail = document.toObject(Mail.class);
        String verificationCode = mail.message.get("code").toString();
        Timestamp createDate = (Timestamp)mail.delivery.get("endTime");
        System.out.println(verificationCode);
        System.out.println(createDate.getSeconds());
        System.out.println(Timestamp.now().getSeconds());
        if(!verificationCode.equals(code)){
            return false;
        }
        if(Timestamp.now().getSeconds() - createDate.getSeconds() > 60){
            return false;
        }
        return true;
    }

}
