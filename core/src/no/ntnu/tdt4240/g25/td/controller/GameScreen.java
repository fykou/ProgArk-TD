package no.ntnu.tdt4240.g25.td.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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

import no.ntnu.tdt4240.g25.td.TdConfig;
import no.ntnu.tdt4240.g25.td.TdGame;
import no.ntnu.tdt4240.g25.td.asset.Assets;
import no.ntnu.tdt4240.g25.td.model.GameWorld;
import no.ntnu.tdt4240.g25.td.asset.Font;
import no.ntnu.tdt4240.g25.td.asset.GameMusic;
import no.ntnu.tdt4240.g25.td.asset.SoundFx;
import no.ntnu.tdt4240.g25.td.utils.MyShapeRenderer;

/**
 * WIP
 */
public class GameScreen extends ScreenAdapter {

    // Game screen size
    public static int MENU_LOGIC_WIDTH = 720;
    public static int MENU_LOGIC_HEIGHT = 1280;

    private final TdGame game;
    private final Screen parent;
    private final GameWorld gameWorld;
    private final SpriteBatch sb;
    private final MyShapeRenderer sr;
    private final OrthographicCamera camera;

    // Exit button
    private final Rectangle exitButton;
    private final GlyphLayout exitButtonLayout;

    // Fonts
    private final BitmapFont font;

    // SoundFX
    private final Sound sound;
//    private final Sound gameStartSound;

    // Music
    private final Music gameMusic;

    public GameScreen(TdGame game, Screen parent) {
        this.game = game;
        this.parent = parent;
        this.gameWorld = new GameWorld(game.getShapeRenderer(), game.getBatch());
        this.sb = game.getBatch();
        this.sr = game.getShapeRenderer();
        this.font = Assets.getInstance().getFont(Font.LARGE);
        this.gameMusic = Assets.getInstance().getMusic(GameMusic.GAME);
        this.sound = Assets.getInstance().getSound(SoundFx.TOUCH);

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
        // Play game music
        if(TdConfig.get().getMusicEnabled()){
            gameMusic.setVolume(TdConfig.get().getVolume());
            gameMusic.setLooping(true);
            gameMusic.play();
        }

    }

    public void handleInput() {
        if (Gdx.input.justTouched()) {
            Vector3 clickCoordinates = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            Vector3 inputCoordinates = camera.unproject(clickCoordinates.cpy());
            if (exitButton.contains(inputCoordinates.x, inputCoordinates.y)) {
                // Go back to parent screen and play sound confirmation
                if(TdConfig.get().getSfxEnabled()){
                    long id = sound.play(TdConfig.get().getVolume());
                    sound.setLooping(id,false);
                }
                game.setScreen(parent);
            }
            else {
                gameWorld.handleInput(clickCoordinates.cpy());
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