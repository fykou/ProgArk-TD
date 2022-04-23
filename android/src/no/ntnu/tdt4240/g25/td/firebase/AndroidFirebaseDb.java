package no.ntnu.tdt4240.g25.td.firebase;

import android.util.Log;


import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import no.ntnu.tdt4240.g25.td.firebase.FirebaseInterface;
import no.ntnu.tdt4240.g25.td.firebase.FirestoreCallbackRead;
import no.ntnu.tdt4240.g25.td.firebase.FirestoreCallbackWrite;

public class AndroidFirebaseDb implements FirebaseInterface {
    private String name;
    private Integer highScore;
    private static final String TAG = "TowerDefence --> ";
    private final FirebaseFirestore db;
    private ArrayList<Map<String,String>> topFiveHighScoresList;

    public AndroidFirebaseDb(){ db = FirebaseFirestore.getInstance(); }

//    Setters for name and highScore to be written to the DB.
    @Override
    public void setName(String name) { this.name = name; }

    @Override
    public void setHighScore(Integer highScore) { this.highScore = highScore; }

    /**
     * This is a async callback method that will provide an ArrayList<Map> of the top five high scores in the Firestore Database in descending order.
     * First item is the highest high score and so on.
     * @param firestoreCallbackRead (functional interface).
     */
    @Override
    public void getTopFiveHighScores(FirestoreCallbackRead firestoreCallbackRead) {
        topFiveHighScoresList = new ArrayList<>();
        db.collection("HighScores")
            .orderBy("highScore", Query.Direction.DESCENDING).limit(8)
            .get()
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String,String> highScoreMap = new HashMap<>();
                        if(document.get("name") != null | document.get("highScore") != null){
                            highScoreMap.put("name", document.get("name").toString());
                            highScoreMap.put("highScore", document.get("highScore").toString());
                            topFiveHighScoresList.add(highScoreMap);
                        }else{
                            Log.d(TAG, "One of the items was null");
                            break;
                        }
                    }
                    firestoreCallbackRead.onCompleteCallback(topFiveHighScoresList);
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            });
    }

    /**
     * This method updates the HighScores collection in our Firestore Database with a new document consisting of two fields.
     * And provides a boolean that is TRUE if the database has been populated successfully.
     * Will return a boolean on successful permutation of database.
     * Will return FALSE if either name or highscore is null.
     * @param firestoreCallbackWrite (functional interface).
     */
    @Override
    public void UpdateHighScoreInFirestore(FirestoreCallbackWrite firestoreCallbackWrite) {
        if (this.name == null | this.highScore == null){
            firestoreCallbackWrite.onSuccessCallback(false);
        } else{
            // Create a new user with a player name and a high score
            Map<String, Object> user = new HashMap<>();
            user.put("name", this.name);
            user.put("highScore", this.highScore);
            // Add a new document with a generated ID
            db.collection("HighScores")
                    .add(user)
                    .addOnSuccessListener(documentReference -> {
                        // Successful block of code, return true
                        firestoreCallbackWrite.onSuccessCallback(true);
                    }).addOnFailureListener(e -> {
                Log.w(TAG, "Error adding high score for username: " + name + " , with high score: " + highScore + ".", e);
                firestoreCallbackWrite.onSuccessCallback(false);
            });
        }
    }
}
