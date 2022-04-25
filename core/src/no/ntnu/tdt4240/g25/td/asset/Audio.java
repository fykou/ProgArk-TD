package no.ntnu.tdt4240.g25.td.asset;

import com.badlogic.gdx.audio.Music;

import no.ntnu.tdt4240.g25.td.TdConfig;

public class Audio {

    public static void playFx(SoundFx type) {
        if (TdConfig.get().getSfxEnabled()) {
            float volume = TdConfig.get().getVolume();
            Assets.getInstance().getSound(type).play(volume);
        }
    }

    public static void playMusic(GameMusic type) {
        Music music = Assets.getInstance().getMusic(type);
        if (TdConfig.get().getMusicEnabled()) {
            float volume = TdConfig.get().getVolume();
            music.setVolume(volume);
            music.setLooping(true);
            music.play();
        }
        else {
            music.stop();
        }
    }

    public static void changeCurrentPlayingVolume(float volume) {
        for (GameMusic music : GameMusic.values()) {
            Assets.getInstance().getMusic(music).setVolume(volume);
        }
    }

    public static void stopMusic() {
        Assets.getInstance().getMusic(GameMusic.MENU).stop();
        Assets.getInstance().getMusic(GameMusic.GAME).stop();
    }

}
