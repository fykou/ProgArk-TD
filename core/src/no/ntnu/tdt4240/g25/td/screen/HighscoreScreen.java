package no.ntnu.tdt4240.g25.td.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
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

import java.util.ArrayList;
import java.util.Map;

import no.ntnu.tdt4240.g25.td.TdGame;
import no.ntnu.tdt4240.g25.td.service.AssetService;

public class HighscoreScreen extends ScreenAdapter {

    // Game screen size
    public int MENU_LOGIC_WIDTH = 720;
    public int MENU_LOGIC_HEIGHT = 1280;

    private final TdGame game;
    private final Screen parent;
    private final SpriteBatch sb;
    private final ShapeRenderer sr;
    private final OrthographicCamera camera;

    // Back to Menu Button
    private final Rectangle goBackButton;
    private final GlyphLayout goBackButtonLayout;

    // Fonts
    private final BitmapFont font;

    // Top five highscores
    private final ArrayList<Map<String, String>> highscores;

    // Sound FX
    private final Sound sound;
    private final Sound highscoreLoadSuccess;

    public HighscoreScreen(TdGame game, Screen parent) {
        this.game = game;
        this.parent = parent;
        this.sb = game.getBatch();
        this.sr = game.getShapeRenderer();
        this.font = game.getAssetManager().assetManager.get(AssetService.Font.LARGE.path, BitmapFont.class);
        this.highscoreLoadSuccess = game.getAssetManager().assetManager.get(AssetService.Sound.HIGHSCORE_CONFIRMED.path);
        this.sound = game.getAssetManager().assetManager.get(AssetService.Sound.TOUCH.path);

        // Camera
        float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        camera = new OrthographicCamera(MENU_LOGIC_HEIGHT / aspectRatio, MENU_LOGIC_HEIGHT);
        camera.position.set(MENU_LOGIC_WIDTH / 2f, MENU_LOGIC_HEIGHT / 2f, 0);

        // Back to Menu button
        goBackButton = new Rectangle(0, 0, MENU_LOGIC_WIDTH / 2f, MENU_LOGIC_WIDTH / 6f)
                .setCenter(MENU_LOGIC_WIDTH / 2f, (MENU_LOGIC_HEIGHT / 2f) - MENU_LOGIC_HEIGHT / 3f);

        goBackButtonLayout = new GlyphLayout(font, "Back to Menu", font.getColor(), goBackButton.width, Align.center, false);

        highscores = new ArrayList<>();

        game.getDb().getTopFiveHighScores((ArrayList<Map<String, String>> topFiveHighScoresList) -> {
            // Play sound when highscores successfully loaded!
            long id = highscoreLoadSuccess.play(1.0f);
            highscoreLoadSuccess.setVolume(id,0.85f);
            highscoreLoadSuccess.setPitch(id, 1);
            highscoreLoadSuccess.setLooping(id,false);

            highscores.clear();
            highscores.addAll(topFiveHighScoresList);
            System.out.println("updated highscores");
        });
    }

    public void handleInput() {
        if (Gdx.input.justTouched()) {
            // Input coordinates
            Vector3 inputCoordinates = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

            // Go back to parent screen and play sound
            if (goBackButton.contains(inputCoordinates.x, inputCoordinates.y)) {
                long id = sound.play(1.0f);
                sound.setVolume(id,0.5f);
                sound.setPitch(id, 1);
                sound.setLooping(id,false);
                game.setScreen(parent);
            }
        }
    }

    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();

        super.render(delta);
        // just for testing/debugging as big deltas mess up stuff
        if (delta > 0.1f) {
            delta = 0.1f;
        }

        // Prepare the highscore screen visuals
        camera.update();
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setProjectionMatrix(camera.combined);
        sr.setColor(Color.WHITE);
        sr.rect(goBackButton.x, goBackButton.y, goBackButton.width, goBackButton.height);
        sr.end();

        sb.begin();
        sb.setProjectionMatrix(camera.combined);
        font.draw(sb, goBackButtonLayout, goBackButton.x, (goBackButton.y + goBackButton.height / 2f) + font.getCapHeight() / 2f);
        font.draw(sb, "LEADERBOARD", MENU_LOGIC_WIDTH / 4f + 40, MENU_LOGIC_HEIGHT - 200);
        font.draw(sb, "Name", MENU_LOGIC_WIDTH / 5f, MENU_LOGIC_HEIGHT - 360);
        font.draw(sb, "Score", MENU_LOGIC_WIDTH / 5f + 330, MENU_LOGIC_HEIGHT - 360);

        // Loop through database payload and populate top 5 highscores to screen
        for (int i = 0; i < highscores.size(); i++) {
            font.draw(sb, (i + 1) + ".", MENU_LOGIC_WIDTH / 5f, MENU_LOGIC_HEIGHT - 450 - i*100);
            font.draw(sb, highscores.get(i).get("name"), MENU_LOGIC_WIDTH / 5f + 60, MENU_LOGIC_HEIGHT - 450 - i*100);
            font.draw(sb, highscores.get(i).get("highScore"), MENU_LOGIC_WIDTH / 5f + 60 + 350, MENU_LOGIC_HEIGHT - 450 - i*100);
        }
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
