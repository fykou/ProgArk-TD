package no.ntnu.tdt4240.g25.td.firebase;

public interface FirebaseInterface {
    void getTopFiveHighScores(FirestoreCallbackRead firestoreCallbackRead);
    void UpdateHighScoreInFirestore(FirestoreCallbackWrite firestoreCallbackWrite);
    void setName(String name);
    void setHighScore(Integer highScore);
}

