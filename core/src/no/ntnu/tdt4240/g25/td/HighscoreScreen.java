package no.ntnu.tdt4240.g25.td;

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

import org.w3c.dom.css.Rect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import no.ntnu.tdt4240.g25.td.model.GameWorld;
import no.ntnu.tdt4240.g25.td.service.AssetService;

public class HighscoreScreen extends ScreenAdapter {

    // Game screen size
    public int MENU_LOGIC_WIDTH = 720;
    public int MENU_LOGIC_HEIGHT = 1280;

    private TdGame game;
    private Screen parent;
    private SpriteBatch sb;
    private ShapeRenderer sr;
    private OrthographicCamera camera;

    // Back to Menu Button
    private Rectangle goBackButton;
    private GlyphLayout goBackButtonLayout;

    // Fonts
    private BitmapFont font;

    // Top five highscores
    private ArrayList<Map<String, String>> highscores;

    public HighscoreScreen(TdGame game, Screen parent) {

        this.game = game;
        this.parent = parent;
        this.sb = game.getBatch();
        this.sr = game.getShapeRenderer();
        this.font = game.getAssetManager().assetManager.get(AssetService.Font.LARGE.path, BitmapFont.class);

        // Camera
        float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        camera = new OrthographicCamera(MENU_LOGIC_HEIGHT / aspectRatio, MENU_LOGIC_HEIGHT);
        camera.position.set(MENU_LOGIC_WIDTH / 2f, MENU_LOGIC_HEIGHT / 2f, 0);

        // Back to Menu button
        goBackButton = new Rectangle(0, 0, MENU_LOGIC_WIDTH / 2f, MENU_LOGIC_WIDTH / 6f)
                .setCenter(MENU_LOGIC_WIDTH / 2f, (MENU_LOGIC_HEIGHT / 2f) - MENU_LOGIC_HEIGHT / 3f);

        goBackButtonLayout = new GlyphLayout(font, "Back to Menu", font.getColor(), goBackButton.width, Align.center, false);


        highscores = new ArrayList<>();
        addMockupData();
    }

    // TODO: Hente data fra firebase
    public void addMockupData() {
        Map<String, String> highscore1 = new HashMap();
        highscore1.put("highScore", "100");
        highscore1.put("name", "groven");
        highscores.add(highscore1);

        Map<String, String> highscore2 = new HashMap();
        highscore2.put("highScore", "50");
        highscore2.put("name", "vemund");
        highscores.add(highscore2);

        Map<String, String> highscore3 = new HashMap();
        highscore3.put("highScore", "47");
        highscore3.put("name", "Bjørn");
        highscores.add(highscore3);

        Map<String, String> highscore4 = new HashMap();
        highscore4.put("highScore", "35");
        highscore4.put("name", "Alex");
        highscores.add(highscore4);

        Map<String, String> highscore5 = new HashMap();
        highscore5.put("highScore", "14");
        highscore5.put("name", "Crolas og Lavik");
        highscores.add(highscore5);
    }

    public void handleInput() {
        if (Gdx.input.justTouched()) {
            // Input coordinates
            Vector3 inputCoordinates = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (goBackButton.contains(inputCoordinates.x, inputCoordinates.y)) {
                game.setScreen(parent);
            }
        }
    }

    @Override
    public void show() {

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
        sr.rect(goBackButton.x, goBackButton.y, goBackButton.width, goBackButton.height);

        sr.end();
        sb.begin();
        sb.setProjectionMatrix(camera.combined);
        font.draw(sb, goBackButtonLayout, goBackButton.x, (goBackButton.y + goBackButton.height / 2f) + font.getCapHeight() / 2f);


        font.draw(sb, "LEADERBOARD", MENU_LOGIC_WIDTH / 4f + 40, MENU_LOGIC_HEIGHT - 200);

        font.draw(sb, "Score", MENU_LOGIC_WIDTH / 5f + 60 + 330, MENU_LOGIC_HEIGHT - 360);

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
