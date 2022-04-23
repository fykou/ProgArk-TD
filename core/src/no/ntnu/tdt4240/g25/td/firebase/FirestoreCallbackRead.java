package no.ntnu.tdt4240.g25.td.firebase;

import java.util.ArrayList;
import java.util.Map;

public interface FirestoreCallbackRead {
    void onCompleteCallback(ArrayList<Map<String, String>> topFiveHighScoresList);
}
