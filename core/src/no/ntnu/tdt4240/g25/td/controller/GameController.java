package no.ntnu.tdt4240.g25.td.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;

import no.ntnu.tdt4240.g25.td.TdGame;
import no.ntnu.tdt4240.g25.td.asset.Audio;
import no.ntnu.tdt4240.g25.td.model.GameWorld;
import no.ntnu.tdt4240.g25.td.asset.GameMusic;
import no.ntnu.tdt4240.g25.td.view.AbstractView;
import no.ntnu.tdt4240.g25.td.view.GameView;

/**
 * WIP
 */
public class GameController extends AbstractController {

    // Game screen size
    public static int MENU_LOGIC_WIDTH = 720;
    public static int MENU_LOGIC_HEIGHT = 1280;
    private final GameWorld gameWorld;
    private boolean paused;
    private final GameView view;

    public GameController(TdGame game, Screen parent) {
        super(game, parent);
        this.view = new GameView(game.getBatch(), new ViewCallback());
        this.gameWorld = new GameWorld(
                game.getShapeRenderer(),
                game.getBatch(),
                new GameWorldCallback(),
                view.getTopBarCallback()
        );
        Gdx.input.setInputProcessor(view);
    }

    @Override
    protected AbstractView getView() {
        return view;
    }

    @Override
    public void show() {
        // Play game music
        Audio.playMusic(GameMusic.GAME);
        super.show();
    }

    @Override
    public void render(float delta) {
        if (!paused) {
            gameWorld.update(delta);
        }
        super.render(delta);
    }

    @Override
    public void resize(int width, int height) {
        gameWorld.resize(width, height);
        super.resize(width, height);
    }


    @Override
    public void hide() {
        super.hide();
        Audio.stopMusic();
    }

    @Override
    public void dispose() {
        super.dispose();
        gameWorld.dispose();
    }

    public class ViewCallback {
        public void onWorldClick(int screenX, int screenY) {
            System.out.println("Clicked at " + screenX + ", " + screenY);
            gameWorld.clickOnWorld(screenX, screenY);
        }
        public void onQuit() {
            game.setScreen(parent);
            dispose();
        }
        public void onPause() {
            paused = true;
        }
        public void onResume() {
            paused = false;
        }

        public void onSettings() {
            game.setScreen(new SettingsController(game, GameController.this));
        }
        public void onUpgradeButtonClicked() {
            gameWorld.upgradeSelectedTower();
        }

        public void onBuy1ButtonClicked() {
            gameWorld.createTower1();
        }

        public void onBuy2ButtonClicked() {
            gameWorld.createTower2();
        }
    }

    public class GameWorldCallback {
        public void onGameOver() {
            game.setScreen(new GameOverController(game, GameController.this, gameWorld.getScore()));
            GameController.this.dispose();
        }
    }
}
