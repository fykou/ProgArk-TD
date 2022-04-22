package no.ntnu.tdt4240.g25.td;

import java.util.ArrayList;

public interface FirebaseInterface {
    void getTopFiveHighScores(FirestoreCallbackRead firestoreCallbackRead);
    void UpdateHighScoreInFirestore(FirestoreCallbackWrite firestoreCallbackWrite);
    void setName(String name);
    void setHighScore(Integer highScore);
}
interface FirestoreCallbackRead {
    void onCompleteCallback(ArrayList topFiveHighScoresList);
}
interface FirestoreCallbackWrite{
    boolean onSuccessCallback(boolean highScoreDbSuccessful);
}
