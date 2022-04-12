package no.ntnu.tdt4240.g25.td;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;

import no.ntnu.tdt4240.g25.td.model.GameWorld;

/**
 * WIP
 */
public class GameScreen extends ScreenAdapter {

    private TdGame game;
    private Screen parent;
    private GameWorld gameWorld;


    public GameScreen(TdGame game, Screen parent) {
        this.game = game;
        this.parent = parent;
        this.gameWorld = new GameWorld(game.getAssetManager(), game.getBatch());
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        // just for testing/debugging as big deltas mess up stuff
        if (delta > 0.1f) {
            delta = 0.1f;
        }
        gameWorld.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

    }

    @Override
    public void pause() {
        super.pause();

    }

    @Override
    public void resume() {
        super.resume();
    }

    @Override
    public void hide() {
        super.hide();

    }

    @Override
    public void dispose() {
        super.dispose();
    }

}
