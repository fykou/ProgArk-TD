package no.ntnu.tdt4240.g25.td;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

import no.ntnu.tdt4240.g25.td.firebase.FirebaseInterface;

public class TdGame extends Game {

    public final static int UI_WIDTH = 720;
    public final static int UI_HEIGHT = 1280;


	private SpriteBatch batch;
	private final FirebaseInterface _FBIC;
	private ShapeRenderer shapeRenderer;


	public TdGame(FirebaseInterface FBIC) {
	    _FBIC = FBIC;
	}

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        setScreen(new LoadingScreen(this, null));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor( 0, 0, 0, 1 );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        float delta = MathUtils.clamp(Gdx.graphics.getDeltaTime(), 0, 1/25f);
        if (screen != null) screen.render(delta);
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
