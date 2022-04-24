package no.ntnu.tdt4240.g25.td.model.entity.systems.render;

import com.artemis.BaseSystem;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;

import java.util.Locale;

import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.PlayerComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.WaveComponent;
import no.ntnu.tdt4240.g25.td.model.entity.systems.MyCameraSystem;
import no.ntnu.tdt4240.g25.td.screen.GameScreen;
import no.ntnu.tdt4240.g25.td.service.AssetService;
import no.ntnu.tdt4240.g25.td.service.Font;
import no.ntnu.tdt4240.g25.td.utils.MyShapeRenderer;

public class WidgetRenderSystem extends BaseSystem {

    public static final float TIMER_POS_X = (GameScreen.MENU_LOGIC_WIDTH / 9f) * 0f;
    public static final float TIMER_POS_Y = GameScreen.MENU_LOGIC_HEIGHT - (GameScreen.MENU_LOGIC_WIDTH / 16f);

    public static final float WAVE_POS_X = (GameScreen.MENU_LOGIC_WIDTH / 9f) * 3f;
    public static final float WAVE_POS_Y = GameScreen.MENU_LOGIC_HEIGHT - (GameScreen.MENU_LOGIC_WIDTH / 16f);

    public static final float HEALTH_POS_X = (GameScreen.MENU_LOGIC_WIDTH / 9f) * 6f;
    public static final float HEALTH_POS_Y = GameScreen.MENU_LOGIC_HEIGHT - (GameScreen.MENU_LOGIC_WIDTH / 16f);

    private WaveComponent wave;
    private PlayerComponent player;
    private MyCameraSystem camera;

    @Wire
    private SpriteBatch batch;
    @Wire
    private MyShapeRenderer renderer;
    @Wire
    private AssetService assetService;
    private BitmapFont font;
    private GlyphLayout timerLayout;
    private GlyphLayout waveLayout;

    @Override
    protected void initialize() {
        font = assetService.getFont(Font.MEDIUM);
        timerLayout = new GlyphLayout(font, "");
        waveLayout = new GlyphLayout(font, "");
        font.setUseIntegerPositions(false);
    }

    @Override
    protected void processSystem() {
        boolean waveActive = wave.active;
        float waveTime = waveActive ? wave.time : WaveComponent.PAUSE_DURATION - wave.time;
        int waveNumber = wave.numberOfWaves;
        int playerLives = player.lives;
        timerLayout.setText(font, formatTimerLayout(waveActive, waveTime), Color.WHITE, 180, Align.center, false);
        waveLayout.setText(font, formatWaveLayout(waveNumber, waveNumber), Color.WHITE, 180, Align.center, false);
        // draw widgets in the top row, i.e at coords 0,16 (top left) to 9,16 (top right)
        batch.setProjectionMatrix(camera.guiViewport.getCamera().combined);
        batch.begin();
        font.draw(batch, timerLayout, TIMER_POS_X, TIMER_POS_Y);
        font.draw(batch, waveLayout, WAVE_POS_X, WAVE_POS_Y);
        font.draw(batch, String.format(Locale.ENGLISH, "Lives: %d", playerLives), 0, 0);
        batch.end();
    }

    private String formatTimerLayout(boolean waveOrPause, float time) {
        String waveOrPauseString = waveOrPause ? "Wave" : "Next wave";
        return String.format(Locale.ENGLISH, "%s:\n%2.1f", waveOrPauseString, time);
    }

    private String formatWaveLayout(int waveNumber, int numberOfWaves) {
        return String.format(Locale.ENGLISH, "Waves survived:\n%d", waveNumber, numberOfWaves);
    }
}
