package no.ntnu.tdt4240.g25.td;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import no.ntnu.tdt4240.g25.td.model.World;
import no.ntnu.tdt4240.g25.td.model.entity.systems.MovementSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.RenderSystem;

/**
 * WIP
 */
public class GameScreen extends ScreenAdapter {

    private TdGame game;
    private Screen parent;
    private World world;
    private PooledEngine engine;



    public GameScreen(TdGame game, Screen parent) {
        this.game = game;
        this.parent = parent;
        this.engine = new PooledEngine();
        setupEngine(engine, game.getBatch());
    }

    protected void setupEngine(PooledEngine engine, SpriteBatch batch) {
        engine.addSystem(new MovementSystem(1f/60));
        engine.addSystem(new RenderSystem(batch));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
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
