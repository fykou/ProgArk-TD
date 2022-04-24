package no.ntnu.tdt4240.g25.td;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.math.MathUtils;

public class TdConfig {

    private static TdConfig instance;
    private final Preferences prefs;

    private TdConfig() {
        prefs = Gdx.app.getPreferences("no.ntnu.tdt4240.g25.td.settings");
    }

    // Singleton
    public static TdConfig get() {
        if(instance == null) {
            instance = new TdConfig();
        }
        return instance;
    }

    public boolean getSfxEnabled() {
        return prefs.getBoolean("sfxEnabled", true);
    }
    public void setSfxEnabled(boolean sfx) {
        prefs.putBoolean("sfxEnabled", sfx);
        prefs.flush();
    }
    public boolean getMusicEnabled() {
        return prefs.getBoolean("musicEnabled", true);
    }
    public void setMusicEnabled(boolean musicEnabled) {
        prefs.putBoolean("musicEnabled", musicEnabled);
        prefs.flush();
    }
    public float getVolume() {
        return prefs.getFloat("gameVolume", .5f);
    }
    public void setVolume(float gameVolume) {
        prefs.putFloat("gameVolume", MathUtils.clamp(gameVolume, 0, 1));
        prefs.flush();
    }
}
