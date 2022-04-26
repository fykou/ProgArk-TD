package no.ntnu.tdt4240.g25.td.controller;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;

import no.ntnu.tdt4240.g25.td.TdConfig;
import no.ntnu.tdt4240.g25.td.TdGame;
import no.ntnu.tdt4240.g25.td.asset.Audio;
import no.ntnu.tdt4240.g25.td.asset.GameMusic;
import no.ntnu.tdt4240.g25.td.asset.SoundFx;
import no.ntnu.tdt4240.g25.td.view.SettingsView;

public class SettingsController extends ScreenAdapter {

    private final TdGame game;
    private final Screen parent;

    private boolean enableMusic;
    private boolean enableSFX;

    private final SettingsView view;

    public SettingsController(TdGame game, Screen parent) {
        this.game = game;
        this.parent = parent;
        this.view = new SettingsView(game.getBatch(), new ViewCallbackHandler());
    }

    public class ViewCallbackHandler {

        public void toggleMusic() {
            Audio.playFx(SoundFx.TOUCH);
            enableMusic = !enableMusic;
            System.out.println("Music: " + enableMusic);
            TdConfig.get().setMusicEnabled(enableMusic);
            // Toggle global menu and game music
            if (enableMusic) {
                Audio.playMusic(GameMusic.SETTINGS);
            } else {
                Audio.stopMusic();
            }
        }

        public void toggleSFX() {
            Audio.playFx(SoundFx.TOUCH);
            enableSFX = !enableSFX;
            TdConfig.get().setSfxEnabled(enableSFX);
        }

        public void increaseVolume() {
            Audio.playFx(SoundFx.TOUCH);
            float currentVolume = TdConfig.get().getVolume();
            if (currentVolume < 10) {
                TdConfig.get().setVolume(currentVolume + 0.1f);
                Audio.changeCurrentPlayingVolume(TdConfig.get().getVolume());
            }
        }

        public void decreaseVolume() {
            Audio.playFx(SoundFx.TOUCH);
            float currentVolume = TdConfig.get().getVolume();
            if (currentVolume > 0) {
                TdConfig.get().setVolume(currentVolume - 0.1f);
                Audio.changeCurrentPlayingVolume(TdConfig.get().getVolume());
            }
        }

        public void clickOk() {
            Audio.playFx(SoundFx.SAVESETTINGS);
            Audio.playFx(SoundFx.TOUCH);
            game.setScreen(parent);
        }

        public void getVolume() {
        	view.updateVolume(TdConfig.get().getVolume());
        }

        public void getMusic() {
        	view.setMusic(TdConfig.get().getMusicEnabled());
        }

        public void getSFX() {
        	view.setSFX(TdConfig.get().getSfxEnabled());
        }
    }

    @Override
    public void show() {
        Audio.playMusic(GameMusic.SETTINGS);
        view.show();
    }

    @Override
    public void render(float delta) {
        view.act(delta);
        view.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        view.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
        view.pause();
    }

    @Override
    public void resume() {
        super.resume();
        view.resume();
    }

    @Override
    public void hide() {
        super.hide();
        view.hide();
        Audio.stopMusic();
    }

    @Override
    public void dispose() {
        super.dispose();
        view.dispose();
    }

}
