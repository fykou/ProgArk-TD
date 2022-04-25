package no.ntnu.tdt4240.g25.td.controller;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;

import java.util.ArrayList;
import java.util.Map;

import no.ntnu.tdt4240.g25.td.TdGame;
import no.ntnu.tdt4240.g25.td.asset.Audio;
import no.ntnu.tdt4240.g25.td.asset.GameMusic;
import no.ntnu.tdt4240.g25.td.asset.SoundFx;
import no.ntnu.tdt4240.g25.td.view.HighscoreView;

public class HighscoreScreen extends ScreenAdapter {

    private final TdGame game;
    private final Screen parent;

    private final HighscoreView view;

    // Highscores
    private ArrayList<Map<String, String>> highscores;

    public HighscoreScreen(TdGame game, Screen parent) {
        this.game = game;
        this.parent = parent;
        this.view = new HighscoreView(game.getBatch(), new ViewCallbackHandler());
    }


    @Override
    public void show() {
        Audio.playMusic(GameMusic.MENU);
        view.show();
    }

    @Override
    public void render(float delta) {
        view.act(delta);
        view.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        view.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
        view.pause();
    }

    @Override
    public void resume() {
        super.resume();
        view.resume();
    }

    @Override
    public void hide() {
        super.hide();
        view.hide();
    }

    @Override
    public void dispose() {
        super.dispose();
            view.dispose();
    }

    public class ViewCallbackHandler {

        ArrayList<Map<String, String>> highscores;

        public void goBackToMenu() {
            Audio.playFx(SoundFx.TOUCH);
            game.setScreen(parent);
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
