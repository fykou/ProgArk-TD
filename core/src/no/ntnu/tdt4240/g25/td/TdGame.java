package no.ntnu.tdt4240.g25.td;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import no.ntnu.tdt4240.g25.td.service.AssetService;
import no.ntnu.tdt4240.g25.td.service.LoadingScreen;

public class TdGame extends Game {

	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	private AssetService assetService;

	@Override
	public void create() {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		assetService = new AssetService();

		setScreen(new LoadingScreen(this, null));
	}

	public AssetService getAssetManager() {
		return assetService;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public ShapeRenderer getShapeRenderer() {
		return shapeRenderer;
	}

}
