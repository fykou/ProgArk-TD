package no.ntnu.tdt4240.g25.td.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;

import no.ntnu.tdt4240.g25.td.TdGame;
import no.ntnu.tdt4240.g25.td.view.GameOverView;

public class GameOverController extends ScreenAdapter {

    private final GameOverView view;
    private final TdGame game;
    private final Screen parent;
    private final int score;

    public GameOverController(TdGame game, Screen parent, int score) {
        this.game = game;
        this.parent = parent;
        this.score = score;
        this.view = new GameOverView(game.getBatch(), new ViewCallbackHandler());
        Gdx.input.setInputProcessor(view);
    }

    @Override
    public void show() {
        view.show();
        Gdx.input.setInputProcessor(view);
    }

    @Override
    public void render(float delta) {
        view.act(delta);
        view.draw();
    }

    @Override
    public void resize(int width, int height) {
        view.resize(width, height);
    }

    @Override
    public void hide() {
        view.hide();
        Gdx.input.setInputProcessor(null);
    }

    public class ViewCallbackHandler {
        public void toMenu() {
            if (parent != null) parent.dispose();
            game.setScreen(new MainMenuController(game, GameOverController.this));
        }
        public int getScore() {
            return score;
        }

        public void submitScore() {
            game.getDb().setHighScore(score);
            game.getDb().UpdateHighScoreInFirestore( highScoreDbSuccessful -> {
                if (highScoreDbSuccessful) {
                    view.submitScoreSuccessful();
                }
            });
        }
    }
}
