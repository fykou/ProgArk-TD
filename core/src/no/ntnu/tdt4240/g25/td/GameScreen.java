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
        this.world = new World(engine);
        setupEngine(engine, game.getBatch());
    }

    protected void setupEngine(PooledEngine engine, SpriteBatch batch) {
        engine.addSystem(new MovementSystem(1f/60));
        engine.addSystem(new RenderSystem(batch));

        /*Entity test = new Entity();
        test.add(new TransformComponent(200, 200));
        Sprite sprite = new TextureAtlas(Gdx.files.internal("turrets/turret_01_mk1.atlas")).createSprite("turret_01_mk1");
        sprite.rotate90(true);
        test.add(new SpriteComponent(sprite));
        test.add(new VelocityComponent(1, 1));
        engine.addEntity(test);*/
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
        world.update(delta);
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
