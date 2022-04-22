package no.ntnu.tdt4240.g25.td;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AndroidInterfaceClass implements FirebaseInterface{
    private boolean highScoreDbSuccessful = false;
    private String name;
    private Integer highScore;
    private static final String TAG = "TowerDefence --> ";
    private FirebaseFirestore db;
    private ArrayList topFivehighScoresList = new ArrayList();

    public AndroidInterfaceClass(){ db = FirebaseFirestore.getInstance(); }

//    Setters for name and highScore to be written to the DB.
    @Override
    public void setName(String name) { this.name = name; }

    @Override
    public void setHighScore(Integer highScore) { this.highScore = highScore; }

    /**
     * This is a async callback method that will provide an ArrayList<Map> of the top five highscores in the Firestore Database in descending order.
     * First item is the highest highscore and so on.
     * @param firestoreCallbackRead (functional interface).
     */
    @Override
    public void getTopFiveHighScores(FirestoreCallbackRead firestoreCallbackRead) {
        db.collection("HighScores")
            .orderBy("highScore", Query.Direction.DESCENDING).limit(5)
            .get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Map highScoreMap = new HashMap();
                            highScoreMap.put("name", document.get("name"));
                            highScoreMap.put("highScore", document.get("highScore"));
                            topFivehighScoresList.add(highScoreMap);
                        }
                        firestoreCallbackRead.onCompleteCallback(topFivehighScoresList);
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                }
            });
    }

    /**
     * This method updates the HighScores collection in our Firestore Database with a new document consisting of two fields.
     * And provides a boolean that is TRUE if the database has been populated successfully.
     * @param firestoreCallbackWrite (functional interface).
     */
    @Override
    public void UpdateHighScoreInFirestore(FirestoreCallbackWrite firestoreCallbackWrite) {
        // Create a new user with a player name and a high score
        Map<String, Object> user = new HashMap<>();
        user.put("name", this.name);
        user.put("highScore", this.highScore);
        // Add a new document with a generated ID
        db.collection("HighScores")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        highScoreDbSuccessful = true;
                        firestoreCallbackWrite.onSuccessCallback(highScoreDbSuccessful);
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w(TAG, "Error adding highscore for username: " + name + " , with highscore: " + highScore + ".", e);
            }
        });
    }
}
