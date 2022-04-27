package no.ntnu.tdt4240.g25.td.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;
import java.util.Map;

import no.ntnu.tdt4240.g25.td.asset.Assets;
import no.ntnu.tdt4240.g25.td.asset.Audio;
import no.ntnu.tdt4240.g25.td.asset.GameMusic;
import no.ntnu.tdt4240.g25.td.asset.SoundFx;
import no.ntnu.tdt4240.g25.td.controller.HighscoreController;

public class HighscoreView extends AbstractView implements View {

    private final Skin skin = Assets.getInstance().getSkin();
    private final TextButton backButton = new TextButton("Back to Menu", skin, "big");
    private final Table table = new Table(skin);

    private final HighscoreController.ViewCallbackHandler viewCallback;

    public HighscoreView(SpriteBatch batch, HighscoreController.ViewCallbackHandler viewCallback) {
        super(viewport, batch);
        Gdx.input.setInputProcessor(this);
        this.viewCallback = viewCallback;
        viewCallback.getHighscores();
        attachListeners();
    }

    public void highscoreUpdated(ArrayList<Map<String, String>> newHighscores) {
        Label title = new Label("Leaderboard", skin, "title");
        Label nameLabel = new Label("Player", skin, "default");
        Label scoreLabel = new Label("Waves", skin, "default");

        table.setFillParent(true);
        table.add(title).padBottom(45).colspan(3).row();
        nameLabel.setFontScale(2.5f);
        scoreLabel.setFontScale(2.5f);
        table.add(nameLabel).align(Align.left).padBottom(40);
        table.add(scoreLabel).align(Align.right).padBottom(40).row();
        for (Map<String, String> highscore : newHighscores) {
            Label name = new Label(highscore.get("name"), skin);
            Label score = new Label(highscore.get("highScore"), skin);
            name.setFontScale(1.8f);
            score.setFontScale(1.8f);
            table.add(name).align(Align.left).padBottom(30);
            table.add(score).align(Align.right).padBottom(30).row();
        }
        table.add(backButton).size(400, 90).colspan(2).padTop(45).row();
        backButton.getLabel().setFontScale(2);
        getRoot().addActor(table);
    }

    private void attachListeners() {
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Audio.playFx(SoundFx.TOUCH);
                viewCallback.goBackToMenu();
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
        Audio.stopMusic();
        Gdx.input.setInputProcessor(null);
    }
}
