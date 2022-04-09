package no.ntnu.tdt4240.g25.td;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import no.ntnu.tdt4240.g25.td.model.GameWorld;
import no.ntnu.tdt4240.g25.td.model.entity.systems.AnimationSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.MovementSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.RenderSystem;

/**
 * WIP
 */
public class GameScreen extends ScreenAdapter {

    private TdGame game;
    private Screen parent;
    private GameWorld gameWorld;
    private World world;

    public GameScreen(TdGame game, Screen parent) {
        this.game = game;
        this.parent = parent;
        setupWorld(game.getBatch());
        this.gameWorld = new GameWorld(world, game.getAssetManager());
    }

    protected void setupWorld(SpriteBatch batch) {
        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(new MovementSystem(1f/60))
                .with(new RenderSystem(batch))
                .with(new AnimationSystem())
                .build();
        this.world = new World(config);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
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
