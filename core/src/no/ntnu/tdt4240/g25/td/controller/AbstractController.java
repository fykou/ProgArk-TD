package no.ntnu.tdt4240.g25.td.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;

import no.ntnu.tdt4240.g25.td.TdGame;
import no.ntnu.tdt4240.g25.td.view.AbstractView;

public abstract class AbstractController extends ScreenAdapter {
    protected final TdGame game;
    protected final Screen parent;

    protected abstract AbstractView getView();

    public AbstractController(TdGame game, Screen parent) {
        this.game = game;
        this.parent = parent;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(getView());
        getView().show();
    }

    @Override
    public void render(float delta) {
        getView().act(delta);
        getView().draw();
    }

    @Override
    public void resize(int width, int height) {
        getView().getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        super.pause();
        getView().pause();
    }

    @Override
    public void resume() {
        super.resume();
        getView().resume();
    }

    @Override
    public void hide() {
        super.hide();
        getView().hide();
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        super.dispose();
        getView().dispose();
    }

}