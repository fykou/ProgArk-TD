package no.ntnu.tdt4240.g25.td;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AndroidInterfaceClass implements FirebaseInterface{
    private static final String TAG = "TowerDefenceAndroid";
    private FirebaseFirestore db;

    public AndroidInterfaceClass(){
         db = FirebaseFirestore.getInstance();
    }
    @Override
    public void SomeFunction() {
        System.out.println("From the android package: wubaduba");
    }

    @Override
    public void FirstFireBaseTest() {
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }
    @Override
    public void SetOnValueChangeListener() {
    }

    @Override
    public void SetValueInDb(String target, String value) {
    }
}
