package no.ntnu.tdt4240.g25.td.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;

import no.ntnu.tdt4240.g25.td.TdGame;
import no.ntnu.tdt4240.g25.td.view.MainMenuView;

public class MainMenuController extends ScreenAdapter {

    private final TdGame game;
    private final Screen parent;

    private final MainMenuView view;

    public MainMenuController(TdGame game, Screen parent) {
        this.game = game;
        this.parent = parent;
        this.view = new MainMenuView(game.getBatch(), new ViewCallbackHandler());
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(view);
        view.show();
    }

    @Override
    public void render(float delta) {
        view.act(delta);
        view.draw();
    }


    @Override
    public void resize(int width, int height) {
        view.getViewport().update(width, height, true);
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
