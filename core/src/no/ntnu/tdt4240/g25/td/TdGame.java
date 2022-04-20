package no.ntnu.tdt4240.g25.td;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import no.ntnu.tdt4240.g25.td.service.AssetService;

public class TdGame extends Game {

	private SpriteBatch batch;
	private AssetService assetService;

	@Override
	public void create() {
		batch = new SpriteBatch();

		assetService = new AssetService();
		assetService.loadTextures();
		while (!assetService.update()) {
			// Wait for loading to complete
		}

		setScreen(new MenuScreen(this, null));
	}

	public AssetService getAssetManager() {
		return assetService;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

}
