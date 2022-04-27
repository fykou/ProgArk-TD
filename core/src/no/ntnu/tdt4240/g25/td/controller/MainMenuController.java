package no.ntnu.tdt4240.g25.td.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;

import no.ntnu.tdt4240.g25.td.TdGame;
import no.ntnu.tdt4240.g25.td.view.AbstractView;
import no.ntnu.tdt4240.g25.td.view.MainMenuView;

public class MainMenuController extends AbstractController {


    private final MainMenuView view;

    @Override
    protected AbstractView getView() {
        return view;
    }

    public MainMenuController(TdGame game, Screen parent) {
        super(game, parent);
        this.view = new MainMenuView(game.getBatch(), new ViewCallbackHandler());
    }

    public class ViewCallbackHandler {
        public void toGame() {
            game.setScreen(new GameController(game, MainMenuController.this));
        }
        public void toSettings() {
            game.setScreen(new SettingsController(game, MainMenuController.this));
        }
        public void toHighScore() {
            game.setScreen(new HighscoreController(game, MainMenuController.this));
        }
        public void toTutorial() {
            game.setScreen(new TutorialController(game, MainMenuController.this));
        }
        public void quit() {
            Gdx.app.exit();
        }
    }

}
