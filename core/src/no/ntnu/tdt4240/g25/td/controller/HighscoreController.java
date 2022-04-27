package no.ntnu.tdt4240.g25.td.controller;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;

import java.util.ArrayList;
import java.util.Map;

import no.ntnu.tdt4240.g25.td.TdGame;
import no.ntnu.tdt4240.g25.td.asset.Audio;
import no.ntnu.tdt4240.g25.td.asset.GameMusic;
import no.ntnu.tdt4240.g25.td.asset.SoundFx;
import no.ntnu.tdt4240.g25.td.view.AbstractView;
import no.ntnu.tdt4240.g25.td.view.HighscoreView;

public class HighscoreController extends AbstractController {

    private final HighscoreView view;

    // Highscores
    private ArrayList<Map<String, String>> highscores;

    @Override
    protected AbstractView getView() {
        return view;
    }

    public HighscoreController(TdGame game, Screen parent) {
        super(game, parent);
        this.view = new HighscoreView(game.getBatch(), new ViewCallbackHandler());
    }


    @Override
    public void show() {
        super.show();
        Audio.playMusic(GameMusic.MENU);
    }

    public class ViewCallbackHandler {

        ArrayList<Map<String, String>> highscores;

        public void goBackToMenu() {
            Audio.playFx(SoundFx.TOUCH);
            game.setScreen(parent);
            HighscoreController.this.dispose();
        }
        public void getHighscores() {
            // Top five high scores
            highscores = new ArrayList<>();
            game.getDb().getTopFiveHighScores((ArrayList<Map<String, String>> topFiveHighScoresList) -> {
                // Play confirmation sound when HS-list successfully loads!
                Audio.playFx(SoundFx.HIGHSCORE_CONFIRMED);
                highscores.clear();
                highscores.addAll(topFiveHighScoresList);
                view.highscoreUpdated(highscores);
            });
        }
    }
}
