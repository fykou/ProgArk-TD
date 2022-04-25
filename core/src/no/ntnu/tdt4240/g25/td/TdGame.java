package no.ntnu.tdt4240.g25.td;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import no.ntnu.tdt4240.g25.td.firebase.FirebaseInterface;
import no.ntnu.tdt4240.g25.td.controller.LoadingScreen;
import no.ntnu.tdt4240.g25.td.asset.Assets;
import no.ntnu.tdt4240.g25.td.utils.MyShapeRenderer;

public class TdGame extends Game {

    public final static int UI_WIDTH = 720;
    public final static int UI_HEIGHT = 1280;
    public final static int WORLD_WIDTH = 9;
    public final static int WORLD_HEIGHT = 16;

	private SpriteBatch batch;
	private AssetService assetService;
	private final FirebaseInterface _FBIC;
	private MyShapeRenderer shapeRenderer;


	public TdGame(FirebaseInterface FBIC) {
	    _FBIC = FBIC;
	}

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new MyShapeRenderer();

//      Need to set name and highscore on _FBIC object.
//      These fields will be used to send data to Firestore
//      _FBIC.setName("name");
//      _FBIC.setHighScore(9);
//        _FBIC.UpdateHighScoreInFirestore(highScoreDbSuccessful -> {
//            System.out.println("This boolean returns true if highscore was successfully written to Firestore DB: "+ highScoreDbSuccessful);
//        });

        setScreen(new LoadingScreen(this, null));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        super.render();
    }

    public FirebaseInterface getDb() {
        return _FBIC;
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public MyShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

}
