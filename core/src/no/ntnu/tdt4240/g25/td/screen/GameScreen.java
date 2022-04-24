package no.ntnu.tdt4240.g25.td.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;


import no.ntnu.tdt4240.g25.td.TdGame;
import no.ntnu.tdt4240.g25.td.model.GameWorld;
import no.ntnu.tdt4240.g25.td.service.AssetService;

/**
 * WIP
 */
public class GameScreen extends ScreenAdapter {

    // Game screen size
    public int MENU_LOGIC_WIDTH = 720;
    public int MENU_LOGIC_HEIGHT = 1280;

    private TdGame game;
    private Screen parent;
    private GameWorld gameWorld;
    private SpriteBatch sb;
    private ShapeRenderer sr;
    private OrthographicCamera camera;

    // Exit button
    private Rectangle exitButton;
    private GlyphLayout exitButtonLayout;

    // Fonts
    private BitmapFont font;


    public GameScreen(TdGame game, Screen parent) {
        this.game = game;
        this.parent = parent;
        this.gameWorld = new GameWorld(game.getAssetManager(), game.getShapeRenderer(), game.getBatch());
        this.sb = game.getBatch();
        this.sr = game.getShapeRenderer();
        this.font = game.getAssetManager().assetManager.get(AssetService.Font.LARGE.path, BitmapFont.class);

        exitButton = new Rectangle(0, 0, MENU_LOGIC_WIDTH / 13f, MENU_LOGIC_HEIGHT / 20f)
                .setCenter(MENU_LOGIC_WIDTH - (MENU_LOGIC_WIDTH / 13f), MENU_LOGIC_HEIGHT - MENU_LOGIC_HEIGHT / 20f);

        exitButtonLayout = new GlyphLayout(font, "X", font.getColor(), exitButton.width, Align.center, false);

        // Camera
        float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        camera = new OrthographicCamera(MENU_LOGIC_HEIGHT / aspectRatio, MENU_LOGIC_HEIGHT);
        camera.position.set(MENU_LOGIC_WIDTH / 2f, MENU_LOGIC_HEIGHT / 2f, 0);

    }

    @Override
    public void show() {

    }

    public void handleInput() {
        if (Gdx.input.justTouched()) {

            // Input coordinates
            Vector3 inputCoordinates = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));


            if (exitButton.contains(inputCoordinates.x, inputCoordinates.y)) {
                game.setScreen(parent);
            }
        }
    }

    @Override
    public void render(float delta) {
        handleInput();
        super.render(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // just for testing/debugging as big deltas mess up stuff
        if (delta > 0.1f) {
            delta = 0.1f;
        }
        gameWorld.update(delta);

        // GUI
        camera.update();
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setProjectionMatrix(camera.combined);
        sr.setColor(Color.WHITE);
        sr.rect(exitButton.x, exitButton.y, exitButton.width, exitButton.height);
        sr.end();
        sb.begin();
        sb.setProjectionMatrix(camera.combined);
        font.draw(sb, exitButtonLayout, exitButton.x, (exitButton.y + exitButton.height / 2f) + font.getCapHeight() / 2f);
        sb.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        gameWorld.resize(width, height);
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
