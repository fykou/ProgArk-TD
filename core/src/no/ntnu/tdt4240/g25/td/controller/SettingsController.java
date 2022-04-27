package no.ntnu.tdt4240.g25.td.controller;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;

import no.ntnu.tdt4240.g25.td.TdConfig;
import no.ntnu.tdt4240.g25.td.TdGame;
import no.ntnu.tdt4240.g25.td.asset.Audio;
import no.ntnu.tdt4240.g25.td.asset.GameMusic;
import no.ntnu.tdt4240.g25.td.asset.SoundFx;
import no.ntnu.tdt4240.g25.td.view.AbstractView;
import no.ntnu.tdt4240.g25.td.view.SettingsView;

public class SettingsController extends AbstractController {


    private final SettingsView view;


    public SettingsController(TdGame game, Screen parent) {
        super(game, parent);
        this.view = new SettingsView(game.getBatch(), new ViewCallbackHandler());

    }

    @Override
    protected AbstractView getView() {
        return view;
    }

    @Override
    public void show() {
        super.show();
        Audio.playMusic(GameMusic.SETTINGS);
    }


    @Override
    public void hide() {
        super.hide();
        Audio.stopMusic();
    }

    public class ViewCallbackHandler {

        public void toggleMusic() {
            Audio.playFx(SoundFx.TOUCH);
            boolean enableMusic = !TdConfig.get().getMusicEnabled();
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
            boolean enableSFX = !TdConfig.get().getSfxEnabled();
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
            SettingsController.this.dispose();
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

}
