package no.ntnu.tdt4240.g25.td;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import no.ntnu.tdt4240.g25.td.service.AssetService;

public class TdGame extends Game {

	private SpriteBatch batch;
	private AssetService assetService;
	private FirebaseInterface _FBIC;

	public TdGame(FirebaseInterface FBIC) {_FBIC = FBIC;}

	@Override
	public void create() {
		batch = new SpriteBatch();
		_FBIC.SomeFunction();
		_FBIC.FirstFireBaseTest();
		assetService = new AssetService();
		assetService.loadTextures();
		while (!assetService.update()) {
			// Wait for loading to complete
		}

		setScreen(new GameScreen(this, null));
	}

	public AssetService getAssetManager() {
		return assetService;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

}
