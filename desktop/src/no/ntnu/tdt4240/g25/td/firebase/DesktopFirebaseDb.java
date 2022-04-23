package no.ntnu.tdt4240.g25.td.firebase;


public class DesktopFirebaseDb implements FirebaseInterface {
    @Override
    public void getTopFiveHighScores(FirestoreCallbackRead firestoreCallbackRead) { }

    @Override
    public void UpdateHighScoreInFirestore(FirestoreCallbackWrite firestoreCallbackWrite) { }

    @Override
    public void setName(String name) { }

    @Override
    public void setHighScore(Integer highScore) { }
}
