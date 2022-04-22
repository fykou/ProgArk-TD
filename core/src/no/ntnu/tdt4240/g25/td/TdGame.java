package no.ntnu.tdt4240.g25.td;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import no.ntnu.tdt4240.g25.td.service.AssetService;

public class TdGame extends Game {

	private SpriteBatch batch;
	private AssetService assetService;
	private FirebaseInterface _FBIC;

	public TdGame(FirebaseInterface FBIC) {_FBIC = FBIC;}

	@Override
	public void create() {
		batch = new SpriteBatch();
		_FBIC.getTopFiveHighScores(new FirestoreCallbackRead() {
			@Override
			public void onCompleteCallback(ArrayList topFiveHighScoresList) {
				System.out.println("Callback fra TdGame: "+ topFiveHighScoresList);
			}
		});
//		Usikker på hvordan vi skal sende inn inputs når det er inception, async, callbackfunksjoner.
//		Lagde to settere. eksempel på hvordan det kan gjøres:
//		_FBIC.setName("callback is my name!");
//		_FBIC.setHighScore(9001);
		_FBIC.UpdateHighScoreInFirestore(new FirestoreCallbackWrite() {
			@Override
			public boolean onSuccessCallback(boolean highScoreDbSuccessful) {
				System.out.println("Boolean trigger for db interaction success: "+ highScoreDbSuccessful);
				return highScoreDbSuccessful;
			}
		});
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
