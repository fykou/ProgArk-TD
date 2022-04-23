package no.ntnu.tdt4240.g25.td.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import no.ntnu.tdt4240.g25.td.TdGame;

public class LoadingScreen extends ScreenAdapter {

    private final TdGame game;
    private final Screen parent;
    private final SpriteBatch sb;

    public LoadingScreen(TdGame game, Screen parent) {
        this.game = game;
        this.parent = parent;
        sb = game.getBatch();

        game.getAssetManager().loadFonts(Gdx.graphics.getHeight());
        game.getAssetManager().assetManager.finishLoading(); // block until fonts are loaded for loading screen
        game.getAssetManager().loadTextures();
        game.getAssetManager().loadSounds();
        game.getAssetManager().loadMusic();

    }

    @Override
    public void render(float delta) {
        if ( game.getAssetManager().assetManager.update() ) {
            game.setScreen(new MenuScreen(game, parent));
        }
    }
}
