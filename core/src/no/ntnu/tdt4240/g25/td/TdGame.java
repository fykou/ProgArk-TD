package no.ntnu.tdt4240.g25.td;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import no.ntnu.tdt4240.g25.td.firebase.FirebaseInterface;
import no.ntnu.tdt4240.g25.td.screen.LoadingScreen;
import no.ntnu.tdt4240.g25.td.service.AssetService;

public class TdGame extends Game {

	private SpriteBatch batch;
	private AssetService assetService;
	private final FirebaseInterface _FBIC;
	private ShapeRenderer shapeRenderer;


	public TdGame(FirebaseInterface FBIC) {
	    _FBIC = FBIC;
	}
	public Music music;
	public Sound touchSound;
	public Sound highScoreLoad;


    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        assetService = new AssetService();
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Race to Mars.mp3"));
        touchSound = Gdx.audio.newSound(Gdx.files.internal("sounds/PUNCH_DESIGNED_HEAVY_74.wav"));
        highScoreLoad = Gdx.audio.newSound(Gdx.files.internal("sounds/Activate Glyph Forcefield.wav"));

        music.setVolume(0.2f);
        music.setLooping(true);
        music.play();

//      Need to set name and highscore on _FBIC object.
//      These fields will be used to send data to Firestore
//      _FBIC.setName("name");
//      _FBIC.setHighScore(9);
//        _FBIC.UpdateHighScoreInFirestore(highScoreDbSuccessful -> {
//            System.out.println("This boolean returns true if highscore was successfully written to Firestore DB: "+ highScoreDbSuccessful);
//        });

        setScreen(new LoadingScreen(this, null));
    }

    public AssetService getAssetManager() {
        return assetService;
    }

    public FirebaseInterface getDb() {
        return _FBIC;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

}
