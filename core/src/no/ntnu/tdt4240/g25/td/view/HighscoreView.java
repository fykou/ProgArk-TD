package no.ntnu.tdt4240.g25.td.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
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
    private final Table table = new Table();
    private final VerticalGroup highscoresGroups = new VerticalGroup();

    private final HighscoreController.ViewCallbackHandler viewCallback;

    public HighscoreView(SpriteBatch batch, HighscoreController.ViewCallbackHandler viewCallback) {
        super(viewport, batch);
        Gdx.input.setInputProcessor(this);
        this.viewCallback = viewCallback;
        viewCallback.getHighscores();
        buildTable();
        attachListeners();
    }

    public void highscoreUpdated(ArrayList<Map<String, String>> newHighscores) {
        for (Map<String, String> highscore : newHighscores) {
            Label name = new Label(highscore.get("name"), skin);
            Label score = new Label(highscore.get("highScore"), skin);
            HorizontalGroup hsRecord = new HorizontalGroup();
            hsRecord.addActor(name);
            hsRecord.addActor(score);
            this.highscoresGroups.addActor(hsRecord);
        }
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

    private void buildTable() {

        Label title = new Label("Leaderboard", skin, "title");
        Label nameLabel = new Label("Name", skin, "default");
        Label scoreLabel = new Label("Score", skin, "default");

        table.setFillParent(true);
        table.add(title).padBottom(50).colspan(2).row();
        table.add(nameLabel).align(Align.left).padBottom(50);
        table.add(scoreLabel).align(Align.right).padBottom(50).row();
        table.add(highscoresGroups).align(Align.center).size(350, 270).padBottom(90).colspan(2).row();
        table.add(backButton).size(350, 90).colspan(2).row();

        getRoot().addActor(table);
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
