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

public class TutorialScreen extends ScreenAdapter {

    // Logic size menu
    public int MENU_LOGIC_WIDTH = 720;
    public int MENU_LOGIC_HEIGHT = 1280;

    private TdGame game;
    private Screen parent;
    private SpriteBatch sb;
    private ShapeRenderer sr;
    private OrthographicCamera camera;

    // Tutorial box
    private Rectangle tutorialBox;

    // Back to Menu Button
    private Rectangle goBackButton;
    private GlyphLayout goBackButtonLayout;

    // Fonts
    private BitmapFont font;
    private BitmapFont fontTutorial;

    public TutorialScreen(TdGame game, Screen parent) {
        this.game = game;
        this.parent = parent;
        this.sb = game.getBatch();
        this.sr = game.getShapeRenderer();
        this.font = game.getAssetManager().assetManager.get(AssetService.Font.LARGE.path, BitmapFont.class);
        this.fontTutorial = game.getAssetManager().assetManager.get(AssetService.Font.MEDIUM.path, BitmapFont.class);

        // Camera
        float aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        camera = new OrthographicCamera(MENU_LOGIC_HEIGHT / aspectRatio, MENU_LOGIC_HEIGHT);
        camera.position.set(MENU_LOGIC_WIDTH / 2f, MENU_LOGIC_HEIGHT / 2f, 0);

        // Tutorial box
        tutorialBox = new Rectangle(0, 0, MENU_LOGIC_WIDTH / 1.4f, MENU_LOGIC_HEIGHT - 500)
                .setCenter(MENU_LOGIC_WIDTH / 2f, MENU_LOGIC_HEIGHT / 2f + 100);

        // Back to Menu button
        goBackButton = new Rectangle(0, 0, MENU_LOGIC_WIDTH / 2f, MENU_LOGIC_WIDTH / 6f)
                .setCenter(MENU_LOGIC_WIDTH / 2f, (MENU_LOGIC_HEIGHT / 2f) - MENU_LOGIC_HEIGHT / 3f);

        goBackButtonLayout = new GlyphLayout(font, "Back to Menu", font.getColor(), goBackButton.width, Align.center, false);

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
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        handleInput();
        camera.update();

        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setProjectionMatrix(camera.combined);
        sr.setColor(Color.YELLOW);
        sr.rect(tutorialBox.x, tutorialBox.y, tutorialBox.width, tutorialBox.height);
        sr.setColor(Color.ROYAL);
        sr.rect(goBackButton.x, goBackButton.y, goBackButton.width, goBackButton.height);
        sr.end();

        sb.begin();
        sb.setProjectionMatrix(camera.combined);
        font.draw(sb, goBackButtonLayout, goBackButton.x, (goBackButton.y + goBackButton.height / 2f) + font.getCapHeight() / 2f);
        font.draw(sb, "HOW TO PLAY: ", 226, 1070);
        fontTutorial.draw(sb, "" +
                "Each round consists of a \nBuild-Phase and Wave-Phase.\n\n" +
                "Build-Phase: Buy and upgrade \nturrets. Turrets will shoot the \nenemy mobs!\n\n" +
                "Wave-Phase: Enemy mobs will \nspawn. Eliminate them before \nthey reach the end of the path!\n\n\n" +
                "Strategy and planning pays off. \nSurvive as many waves as you \ncan!",
                140, 950);
        sb.end();
    }

    @Override
    public void show() {

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
