package no.ntnu.tdt4240.g25.td.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Locale;

import no.ntnu.tdt4240.g25.td.asset.Assets;
import no.ntnu.tdt4240.g25.td.asset.Audio;
import no.ntnu.tdt4240.g25.td.asset.GameMusic;
import no.ntnu.tdt4240.g25.td.controller.SettingsScreen;

public class SettingsView extends AbstractView implements View {

    private final Skin skin = Assets.getInstance().getSkin();
    private final CheckBox musicToggle = new CheckBox("Music", skin, "switch");
    private final CheckBox SFXToggle = new CheckBox("Sound", skin, "switch");
    private final Button volumeUp = new Button(skin, "plus");
    private final Button volumeDown = new Button(skin, "minus");
    private final TextButton backButton = new TextButton("Back to Menu", skin, "big");
    private final Label volumeValue = new Label("volume", skin, "default"); //spinner
    private final Table table = new Table();

    private final SettingsScreen.ViewCallbackHandler viewCallback;

    public SettingsView(SpriteBatch batch, SettingsScreen.ViewCallbackHandler viewCallback) {
        super(viewport, batch);
        Gdx.input.setInputProcessor(this);
        this.viewCallback = viewCallback;
        buildTable();
        attatchListeners();
    }

    private void buildTable() {
        Label title = new Label("Settings", skin, "title");

        table.setFillParent(true);
        table.add(title).padBottom(50).colspan(3).row();
        table.add(musicToggle).pad(10).colspan(3).row();
        table.add(SFXToggle).pad(10).colspan(3).row();
        table.add(volumeDown).padBottom(10).padTop(10);
        table.add(volumeValue).width(30).pad(10);
        table.add(volumeUp).padBottom(10).padTop(10).row();
        table.add(backButton).pad(10).colspan(3).row();

        getRoot().addActor(table);
    }

    public void updateVolume(float volume) {
    	this.volumeValue.setText(String.format(Locale.ENGLISH, "%.0f", volume * 100) + "%");
    }

    public void setMusic(boolean music) {
    	this.musicToggle.setChecked(music);
    }

    public void setSFX(boolean sfx) {
    	this.SFXToggle.setChecked(sfx);
    }

    private void attatchListeners() {
        musicToggle.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                viewCallback.toggleMusic();
                viewCallback.getMusic();
            }
        });
        SFXToggle.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                viewCallback.toggleSFX();
                viewCallback.getSFX();
            }
        });
        volumeUp.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                viewCallback.increaseVolume();
                viewCallback.getVolume();
            }
        });
        volumeDown.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                viewCallback.decreaseVolume();
                viewCallback.getVolume();
            }
        });
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                viewCallback.clickOk();
            }
        });

    }

    @Override
    public void show() {
        Audio.playMusic(GameMusic.SETTINGS);
        viewCallback.getVolume();
        viewCallback.getMusic();
        viewCallback.getSFX();
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Audio.stopMusic();
        Gdx.input.setInputProcessor(null);

    }
}
