package no.ntnu.tdt4240.g25.td.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import no.ntnu.tdt4240.g25.td.asset.Assets;
import no.ntnu.tdt4240.g25.td.asset.Audio;
import no.ntnu.tdt4240.g25.td.asset.GameMusic;
import no.ntnu.tdt4240.g25.td.asset.SoundFx;
import no.ntnu.tdt4240.g25.td.controller.MainMenuController;

public class MainMenuView extends AbstractView {

    private final Skin skin = Assets.getInstance().getSkin();
    private final TextButton playButton = new TextButton("Play", skin, "big");
    private final TextButton settingsButton = new TextButton("Settings", skin, "big");
    private final TextButton highScoreButton = new TextButton("Highscores", skin, "big");
    private final TextButton tutorialButton = new TextButton("Tutorial", skin, "big");
    private final TextButton quitButton = new TextButton("Quit", skin, "big");
    private final Table table = new Table(skin);

    private final MainMenuController.ViewCallbackHandler viewCallback;


    public MainMenuView(SpriteBatch batch, MainMenuController.ViewCallbackHandler viewCallback) {
        super(viewport, batch);
        Gdx.input.setInputProcessor(this);
        this.viewCallback = viewCallback;
        buildTable();
        attachListeners();
    }

    private void buildTable() {
        Image background = new Image(Assets.getInstance().getTexture("screens/backdrop.png"));
        background.setFillParent(true);
        addActor(background);
        Label title = new Label("Tower Defense", skin, "title");
        title.setFontScale(2);
        playButton.getLabel().setFontScale(2);
        settingsButton.getLabel().setFontScale(2);
        highScoreButton.getLabel().setFontScale(2);
        tutorialButton.getLabel().setFontScale(2);
        quitButton.getLabel().setFontScale(2);

        table.setFillParent(true);
        table.add(title).padBottom(200).row();
        table.add(playButton).size(350, 90).padBottom(90).row();
        table.add(settingsButton).size(350, 90).padBottom(90).row();
        table.add(highScoreButton).size(350, 90).padBottom(90).row();
        table.add(tutorialButton).size(350, 90).padBottom(90).row();
        table.add(quitButton).size(350, 90).padBottom(90).row();
        addActor(table);
    }

    private void attachListeners() {
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Audio.playFx(SoundFx.TOUCH);
                viewCallback.toGame();
            }
        });
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Audio.playFx(SoundFx.TOUCH);
                viewCallback.toSettings();
            }
        });
        highScoreButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Audio.playFx(SoundFx.TOUCH);
                viewCallback.toHighScore();
            }
        });
        tutorialButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Audio.playFx(SoundFx.TOUCH);
                viewCallback.toTutorial();
            }
        });
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Audio.playFx(SoundFx.TOUCH);
                viewCallback.quit();
            }
        });
    }

    @Override
    public void show() {
        Audio.playMusic(GameMusic.MENU);
        Gdx.input.setInputProcessor(this);
    }
    @Override
    public void resize(int width, int height) {
        this.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        Audio.stopMusic();
    }

    @Override
    public void resume() {
        Audio.playMusic(GameMusic.MENU);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        Audio.stopMusic();

    }
}
