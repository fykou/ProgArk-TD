package no.ntnu.tdt4240.g25.td.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;

import no.ntnu.tdt4240.g25.td.TdGame;
import no.ntnu.tdt4240.g25.td.view.GameOverView;

public class GameOverController extends ScreenAdapter {

    private final GameOverView view;

    public GameOverController(TdGame game, Screen parent) {
        this.view = new GameOverView(game.getBatch());

        Gdx.input.setInputProcessor(view);
    }

    @Override
    public void show() {
        view.show();
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
    }
}
