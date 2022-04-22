package no.ntnu.tdt4240.g25.td;

import java.util.ArrayList;
import java.util.Map;

public interface FirebaseInterface {
    void getTopFiveHighScores(FirestoreCallbackRead firestoreCallbackRead);
    void UpdateHighScoreInFirestore(FirestoreCallbackWrite firestoreCallbackWrite);
    void setName(String name);
    void setHighScore(Integer highScore);
}
interface FirestoreCallbackRead {
    void onCompleteCallback(ArrayList<Map<String,String>> topFiveHighScoresList);
}
interface FirestoreCallbackWrite{
    void onSuccessCallback(boolean highScoreDbSuccessful);
}
