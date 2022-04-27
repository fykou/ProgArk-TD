package no.ntnu.tdt4240.g25.td.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;

import no.ntnu.tdt4240.g25.td.TdGame;
import no.ntnu.tdt4240.g25.td.view.AbstractView;
import no.ntnu.tdt4240.g25.td.view.GameOverView;

public class GameOverController extends AbstractController {

    private final GameOverView view;
    private final int score;

    public GameOverController(TdGame game, Screen parent, int score) {
        super(game, parent);
        this.score = score;
        this.view = new GameOverView(game.getBatch(), new ViewCallbackHandler());
        Gdx.input.setInputProcessor(view);
    }

    @Override
    protected AbstractView getView() {
        return view;
    }

    public class ViewCallbackHandler {
        public void toMenu() {
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
