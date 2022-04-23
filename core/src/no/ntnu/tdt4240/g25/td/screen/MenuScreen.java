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

import no.ntnu.tdt4240.g25.td.TdGame;
import no.ntnu.tdt4240.g25.td.service.AssetService;

public class MenuScreen extends ScreenAdapter {

    // Logic size menu
    public int MENU_LOGIC_WIDTH = 720;
    public int MENU_LOGIC_HEIGHT = 1280;

    private TdGame game;
    private Screen parent;
    private SpriteBatch sb;
    private ShapeRenderer sr;
    private OrthographicCamera camera;

    // Play Button
    private Rectangle playButton;
    private GlyphLayout playButtonLayout;

    // Leaderboard Button
    private Rectangle leaderboardButton;
    private GlyphLayout leaderboardButtonLayout;

    // Settings Button
    private Rectangle settingsButton;
    private GlyphLayout settingsButtonLayout;

    // Fonts
    private BitmapFont font;


    public MenuScreen(TdGame game, Screen parent) {
        this.game = game;
        this.parent = parent;
        this.sb = game.getBatch();
        this.sr = game.getShapeRenderer();
        this.font = game.getAssetManager().assetManager.get(AssetService.Font.LARGE.path, BitmapFont.class);

        // Camera
        float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        camera = new OrthographicCamera(MENU_LOGIC_HEIGHT / aspectRatio, MENU_LOGIC_HEIGHT);
        camera.position.set(MENU_LOGIC_WIDTH / 2f, MENU_LOGIC_HEIGHT / 2f, 0);


        // Play Button
        playButton = new Rectangle(0, 0, MENU_LOGIC_WIDTH / 2f, MENU_LOGIC_WIDTH / 4f)
                .setCenter(MENU_LOGIC_WIDTH / 2f, (MENU_LOGIC_HEIGHT / 2f) + MENU_LOGIC_HEIGHT / 5f);

        playButtonLayout = new GlyphLayout(font, "Play", font.getColor(), playButton.width, Align.center, false);

        // Settings Button
        settingsButton = new Rectangle(0, 0, MENU_LOGIC_WIDTH / 2f, MENU_LOGIC_WIDTH / 4f)
                .setCenter(MENU_LOGIC_WIDTH / 2f, (MENU_LOGIC_HEIGHT / 2f) - MENU_LOGIC_HEIGHT / 5f);

        settingsButtonLayout = new GlyphLayout(font, "Settings", Color.WHITE, settingsButton.width, Align.center, false);

        // Leaderboard Button
        leaderboardButton = new Rectangle(0, 0, MENU_LOGIC_WIDTH / 2f, MENU_LOGIC_WIDTH / 4f)
                .setCenter(MENU_LOGIC_WIDTH / 2f, (MENU_LOGIC_HEIGHT / 2f));

        leaderboardButtonLayout = new GlyphLayout(font, "Leaderboard", Color.WHITE, leaderboardButton.width, Align.center, false);
    }

    @Override
    public void show() {

    }

    public void handleInput() {
        if (Gdx.input.justTouched()) {

            // Input coordinates
            Vector3 inputCoordinates = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));


            if (playButton.contains(inputCoordinates.x, inputCoordinates.y)) {
                game.setScreen(new GameScreen(game, this));
            } else if (settingsButton.contains(inputCoordinates.x, inputCoordinates.y)) {
                game.setScreen(new SettingsScreen(game, this));
            } else if (leaderboardButton.contains(inputCoordinates.x, inputCoordinates.y)) {
                game.setScreen(new HighscoreScreen(game, this));
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
        // just for testing/debugging as big deltas mess up stuff
        if (delta > 0.1f) {
            delta = 0.1f;
        }
        camera.update();
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setProjectionMatrix(camera.combined);
        sr.setColor(Color.WHITE);
        sr.rect(playButton.x, playButton.y, playButton.width, playButton.height);
        sr.rect(settingsButton.x, settingsButton.y, settingsButton.width, settingsButton.height);
        sr.rect(leaderboardButton.x, leaderboardButton.y, leaderboardButton.width, leaderboardButton.height);
        sr.end();
        sb.begin();
        sb.setProjectionMatrix(camera.combined);
        font.draw(sb, playButtonLayout, playButton.x, (playButton.y + playButton.height / 2f) + font.getCapHeight() / 2f);
        font.draw(sb, settingsButtonLayout, settingsButton.x, (settingsButton.y + settingsButton.height / 2f) + font.getCapHeight() / 2f);
        font.draw(sb, leaderboardButtonLayout, leaderboardButton.x, (leaderboardButton.y + leaderboardButton.height / 2f) + font.getCapHeight() / 2f);
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
