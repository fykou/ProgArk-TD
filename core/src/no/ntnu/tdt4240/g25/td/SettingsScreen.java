package no.ntnu.tdt4240.g25.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class SettingsScreen extends ScreenAdapter {

    private TdGame game;
    private Screen parent;
    private SpriteBatch sb;

    private Texture exitSettingsButton;
    private Texture menuBackground;

    private int exitSettingsButtonWidth = 200;
    private int exitSettingsButtonHeight = 100;
    private int exitSettingsButton_posX = 100;
    private int exitSettingsButton_posY = 100;

    public SettingsScreen(TdGame game, Screen parent) {
        this.game = game;
        this.parent = parent;
        this.sb = game.getBatch();

        this.exitSettingsButton = new Texture("screens/ok.png");
        this.menuBackground = new Texture("screens/menuBackground.png");
    }

    @Override
    public void show() {

    }

    public void handleInput() {
        if (Gdx.input.justTouched()) {

            // Input coordinates
            Vector3 inputCoordinates = new Vector3(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 0);

            // Draw bounds around OK/Exit settings Button
            Rectangle exitSettingsButtonBounds = new Rectangle(exitSettingsButton_posX, exitSettingsButton_posY, exitSettingsButtonWidth, exitSettingsButtonHeight);

            if (exitSettingsButtonBounds.contains(inputCoordinates.x, inputCoordinates.y)) {
                game.setScreen(parent);
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

        sb.begin();
        sb.draw(menuBackground, 0, 0);
        sb.draw(exitSettingsButton, exitSettingsButton_posX, exitSettingsButton_posY, exitSettingsButtonWidth, exitSettingsButtonHeight);
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
