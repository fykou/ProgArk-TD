package no.ntnu.tdt4240.g25.td;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Map;

import java.util.ArrayList;
import java.util.Map;

import no.ntnu.tdt4240.g25.td.service.AssetService;
import no.ntnu.tdt4240.g25.td.service.LoadingScreen;

public class TdGame extends Game {

	private SpriteBatch batch;
	private AssetService assetService;
	private FirebaseInterface _FBIC;
	private ShapeRenderer shapeRenderer;

	public TdGame(FirebaseInterface FBIC) {_FBIC = FBIC;}


	@Override
	public void create() {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		assetService = new AssetService();
		_FBIC.getTopFiveHighScores((ArrayList<Map<String, String>> topFiveHighScoresList) -> System.out.println("Use this descending ordered list to display top 5 global high scores: " + topFiveHighScoresList));
//		Need to set name and highscore on _FBIC object.
//		These fields will be used to send data to Firestore
//		_FBIC.setName("name");
//		_FBIC.setHighScore(9);
		_FBIC.UpdateHighScoreInFirestore( highScoreDbSuccessful -> {
			System.out.println("This boolean returns true if highscore was successfully written to Firestore DB: "+ highScoreDbSuccessful);
		});

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
