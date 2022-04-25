package no.ntnu.tdt4240.g25.td.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import no.ntnu.tdt4240.g25.td.TdGame;
import no.ntnu.tdt4240.g25.td.asset.Assets;

public class LoadingScreen extends ScreenAdapter {

    private final TdGame game;
    private final Screen parent;
    private final SpriteBatch sb;

    public LoadingScreen(TdGame game, Screen parent) {
        this.game = game;
        this.parent = parent;
        sb = game.getBatch();

        Assets.getInstance().loadFonts(Gdx.graphics.getHeight());
        Assets.getInstance().loadSkin();
        Assets.getInstance().finishLoading(); // block until assets for loading screen are in
        Assets.getInstance().loadTextures();
        Assets.getInstance().loadSounds();
        Assets.getInstance().loadMusic();
    }
    @Override
    public void render(float delta) {
        if (Assets.getInstance().update()) {
            game.setScreen(new MenuScreen(game, parent));
        }
    }
}
