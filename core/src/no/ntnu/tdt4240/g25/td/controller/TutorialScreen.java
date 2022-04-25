package no.ntnu.tdt4240.g25.td.controller;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;

import no.ntnu.tdt4240.g25.td.TdGame;
import no.ntnu.tdt4240.g25.td.asset.Audio;
import no.ntnu.tdt4240.g25.td.asset.GameMusic;
import no.ntnu.tdt4240.g25.td.view.TutorialView;

public class TutorialScreen extends ScreenAdapter {

    private final TdGame game;
    private final Screen parent;

    private final TutorialView view;

    public TutorialScreen(TdGame game, Screen parent) {
        this.game = game;
        this.parent = parent;
        this.view = new TutorialView(game.getBatch(), new ViewCallbackHandler());
    }

    @Override
    public void render(float delta) {
        view.act(delta);
        view.draw();
    }

    @Override
    public void show() {
        Audio.playMusic(GameMusic.MENU);
        view.show();
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
        public void onBackToMenuButtonClicked() {
            game.setScreen(parent);
        }
    }

}
