package no.ntnu.tdt4240.g25.td.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
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
import no.ntnu.tdt4240.g25.td.service.AssetService;

public class SettingsScreen extends ScreenAdapter {

    // Logic size menu
    public int SETTINGS_LOGIC_WIDTH = 720;
    public int SETTINGS_LOGIC_HEIGHT = 1280;

    private TdGame game;
    private Screen parent;
    private SpriteBatch sb;
    private ShapeRenderer sr;
    private OrthographicCamera camera;

    // OK Button
    private Rectangle okButton;
    private GlyphLayout okButtonLayout;

    // SFX Checkbox
    private Rectangle sfxCheckbox;
    private GlyphLayout sfxCheckboxLayout;
    private Boolean enableSFX;

    // Music Checkbox
    private Rectangle musicCheckbox;
    private GlyphLayout musicCheckboxLayout;
    private Boolean enableMusic;

    // Volume
    private Rectangle volumePlus;
    private GlyphLayout volumePlusLayout;
    private Rectangle volumeMinus;
    private GlyphLayout volumeMinusLayout;
    private float gameVolume;


    // Fonts
    private BitmapFont font;

    public SettingsScreen(TdGame game, Screen parent) {

        this.game = game;
        this.parent = parent;
        this.sb = game.getBatch();
        this.sr = game.getShapeRenderer();
        this.font = game.getAssetManager().assetManager.get(AssetService.Font.LARGE.path, BitmapFont.class);

        // Settings
        enableSFX = TdConfig.get().getSfxEnabled();
        enableMusic = TdConfig.get().getMusicEnabled();
        gameVolume = TdConfig.get().getVolume();

        // Camera
        float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        camera = new OrthographicCamera(SETTINGS_LOGIC_HEIGHT / aspectRatio, SETTINGS_LOGIC_HEIGHT);
        camera.position.set(SETTINGS_LOGIC_WIDTH / 2f, SETTINGS_LOGIC_HEIGHT / 2f, 0);

        // OK Button
        okButton = new Rectangle(0, 0, SETTINGS_LOGIC_WIDTH / 4f, SETTINGS_LOGIC_HEIGHT / 12f)
                .setCenter(SETTINGS_LOGIC_WIDTH / 2f, (SETTINGS_LOGIC_HEIGHT / 7f));
        okButtonLayout = new GlyphLayout(font, "OK", font.getColor(), okButton.width, Align.center, false);

        // SFX Checkbox
        sfxCheckbox = new Rectangle(0, 0, SETTINGS_LOGIC_HEIGHT / 24f, SETTINGS_LOGIC_HEIGHT / 24f)
                .setCenter(SETTINGS_LOGIC_WIDTH / 2f + 70, SETTINGS_LOGIC_HEIGHT / 2f);
        sfxCheckboxLayout = new GlyphLayout(font, (enableSFX ? "X" : ""), font.getColor(), sfxCheckbox.width, Align.center, false);

        // Music Checkbox
        musicCheckbox = new Rectangle(0, 0, SETTINGS_LOGIC_HEIGHT / 24f, SETTINGS_LOGIC_HEIGHT / 24f)
                .setCenter(SETTINGS_LOGIC_WIDTH / 2f + 70, SETTINGS_LOGIC_HEIGHT / 2f + 100);
        musicCheckboxLayout = new GlyphLayout(font, (enableMusic ? "X" : ""), font.getColor(), musicCheckbox.width, Align.center, false);

        // Volume down (-)
        volumeMinus = new Rectangle(0, 0, SETTINGS_LOGIC_HEIGHT / 30f, SETTINGS_LOGIC_HEIGHT / 30f)
                .setCenter(SETTINGS_LOGIC_WIDTH / 2f + 70, SETTINGS_LOGIC_HEIGHT / 2f - 100);
        volumeMinusLayout = new GlyphLayout(font, "-", font.getColor(), volumeMinus.width, Align.center, false);

        // Volume up (+)
        volumePlus = new Rectangle(0, 0, SETTINGS_LOGIC_HEIGHT / 30f, SETTINGS_LOGIC_HEIGHT / 30f)
                .setCenter(SETTINGS_LOGIC_WIDTH / 2f + 200, SETTINGS_LOGIC_HEIGHT / 2f - 100);
        volumePlusLayout = new GlyphLayout(font, "+", font.getColor(), volumePlus.width, Align.center, false);

    }

    @Override
    public void show() {

    }

    public void handleInput() {
        if (Gdx.input.justTouched()) {

            // Input coordinates
            Vector3 inputCoordinates = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));


            // CLICK Ok button
            if (okButton.contains(inputCoordinates.x, inputCoordinates.y)) {
                game.setScreen(parent);
            }

            // CLICK SFX checkbox
            else if (sfxCheckbox.contains(inputCoordinates.x, inputCoordinates.y)) {
                enableSFX = !enableSFX;
                TdConfig.get().setSfxEnabled(enableSFX);
                sfxCheckboxLayout.setText(font, (enableSFX ? "X" : ""), font.getColor(), sfxCheckbox.width, Align.center, false);
            }

            // CLICK Music checkbox
            else if (musicCheckbox.contains(inputCoordinates.x, inputCoordinates.y)) {
                enableMusic = !enableMusic;
                TdConfig.get().setMusicEnabled(enableMusic);
                musicCheckboxLayout.setText(font, (enableMusic ? "X" : ""), font.getColor(), musicCheckbox.width, Align.center, false);
            }

            // Volume down
            else if (volumeMinus.contains(inputCoordinates.x, inputCoordinates.y)) {
                float currentVolume = TdConfig.get().getVolume();
                if (currentVolume > 0) {
                    TdConfig.get().setVolume(currentVolume - 0.1f);
                }
            }

            // Volume up
            else if (volumePlus.contains(inputCoordinates.x, inputCoordinates.y)) {
                float currentVolume = TdConfig.get().getVolume();
                if (currentVolume < 10) {
                    TdConfig.get().setVolume(currentVolume + 0.1f);
                }
            }
        }
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        handleInput();
        super.render(delta);
        // just for testing/debugging as big deltas mess up stuff
        if (delta > 0.1f) {
            delta = 0.1f;
        }

        camera.update();

        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setProjectionMatrix(camera.combined);
        sr.setColor(Color.WHITE);
        sr.rect(okButton.x, okButton.y, okButton.width, okButton.height);
        sr.rect(sfxCheckbox.x, sfxCheckbox.y, sfxCheckbox.width, sfxCheckbox.height);
        sr.rect(musicCheckbox.x, musicCheckbox.y, musicCheckbox.width, musicCheckbox.height);
        sr.rect(volumeMinus.x, volumeMinus.y, volumeMinus.width, volumeMinus.height);
        sr.rect(volumePlus.x, volumePlus.y, volumePlus.width, volumePlus.height);
        sr.end();

        sb.begin();
        sb.setProjectionMatrix(camera.combined);
        font.draw(sb, okButtonLayout, okButton.x, (okButton.y + okButton.height / 2f) + font.getCapHeight() / 2f);
        font.draw(sb, sfxCheckboxLayout, sfxCheckbox.x, (sfxCheckbox.y + sfxCheckbox.height / 2f) + font.getCapHeight() / 2f);
        font.draw(sb, musicCheckboxLayout, musicCheckbox.x, (musicCheckbox.y + musicCheckbox.height / 2f) + font.getCapHeight() / 2f);
        font.draw(sb, "Sound FX", sfxCheckbox.x - 220, sfxCheckbox.y + 40);
        font.draw(sb, "Music", musicCheckbox.x - 150, musicCheckbox.y + 40);
        font.draw(sb, "Volume", volumeMinus.x - 185, volumeMinus.y + 40);
        font.draw(sb, volumeMinusLayout, volumeMinus.x, (volumeMinus.y + volumeMinus.height / 2f) + font.getCapHeight() / 2f);
        font.draw(sb, volumePlusLayout, volumePlus.x, (volumePlus.y + volumePlus.height / 2f) + font.getCapHeight() / 2f);
        font.draw(sb, String.valueOf(TdConfig.get().getVolume() * 10), volumeMinus.x + 75, volumeMinus.y + 35);
        sb.end();
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
