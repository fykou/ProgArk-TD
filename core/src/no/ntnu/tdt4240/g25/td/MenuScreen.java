package no.ntnu.tdt4240.g25.td;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import no.ntnu.tdt4240.g25.td.model.GameWorld;

public class MenuScreen extends ScreenAdapter {

    private TdGame game;
    private Screen parent;
    private SpriteBatch sb;

    private Texture playButton;
    private Texture settingsButton;

    // Play Button
    private int playButton_positionX;
    private int playButton_positionY;
    private int playButtonWidth;
    private int playButtonHeight;

    // Settings Button
    private int settingsButton_positionX;
    private int settingsButton_positionY;
    private int settingsButtonWidth;
    private int settingsButtonHeight;


    public MenuScreen(TdGame game, Screen parent) {
        this.game = game;
        this.parent = parent;
        this.sb = game.getBatch();
        this.playButton = new Texture("screens/play.png");
        this.settingsButton = new Texture("screens/settings.PNG");

        playButtonWidth = 150;
        playButtonHeight = 75;
        playButton_positionX = Gdx.graphics.getWidth() / 2 - (playButtonWidth / 2);
        playButton_positionY = Gdx.graphics.getHeight() / 2 - (playButtonHeight / 2);

        settingsButtonWidth = 150;
        settingsButtonHeight = 75;
        settingsButton_positionX = Gdx.graphics.getWidth() / 2 - (settingsButtonWidth / 2);
        settingsButton_positionY = Gdx.graphics.getHeight() / 2 - (settingsButtonHeight / 2) - 100;
    }

    @Override
    public void show() {

    }

    public void handleInput() {
        if (Gdx.input.justTouched()) {

            // Input coordinates
            Vector3 inputCoordinates = new Vector3(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), 0);

            // Draw bounds around Play Button
            Rectangle playButtonBounds = new Rectangle(playButton_positionX, playButton_positionY, playButtonWidth, playButtonHeight);

            // Draw bounds around Settings Button
            Rectangle settingsButtonBounds = new Rectangle(settingsButton_positionX, settingsButton_positionY, settingsButtonWidth, settingsButtonHeight);

            if (playButtonBounds.contains(inputCoordinates.x, inputCoordinates.y)){
                game.setScreen(new GameScreen(game, this));
            } else if (settingsButtonBounds.contains(inputCoordinates.x, inputCoordinates.y)) {
                System.out.println("Settings!");
            }
        }
    }

    @Override
    public void render(float delta) {
        handleInput();
        super.render(delta);
        // just for testing/debugging as big deltas mess up stuff
        if (delta > 0.1f) {
            delta = 0.1f;
        }

        sb.begin();
        //sb.draw(drawObject, Gdx.graphics.getWidth() / 2 - drawObject.getWidth() / 2, 200, 200, 100);

        sb.draw(playButton, playButton_positionX, playButton_positionY, playButtonWidth, playButtonHeight);
        sb.draw(settingsButton, settingsButton_positionX, settingsButton_positionY, settingsButtonWidth, settingsButtonHeight);


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
