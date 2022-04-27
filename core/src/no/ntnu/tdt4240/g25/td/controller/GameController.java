package no.ntnu.tdt4240.g25.td.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;

import no.ntnu.tdt4240.g25.td.TdGame;
import no.ntnu.tdt4240.g25.td.asset.Audio;
import no.ntnu.tdt4240.g25.td.model.GameWorld;
import no.ntnu.tdt4240.g25.td.asset.GameMusic;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.PlayerComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.WaveComponent;
import no.ntnu.tdt4240.g25.td.view.GameView;

/**
 * WIP
 */
public class GameController extends ScreenAdapter {

    // Game screen size
    public static int MENU_LOGIC_WIDTH = 720;
    public static int MENU_LOGIC_HEIGHT = 1280;
    private final TdGame game;
    private final Screen parent;
    private final GameWorld gameWorld;
    private boolean paused;
    private final GameView view;

    public GameController(TdGame game, Screen parent) {
        this.game = game;
        this.parent = parent;
        this.view = new GameView(game.getBatch(), new ViewCallbackHandler());
        this.gameWorld = new GameWorld(game.getShapeRenderer(), game.getBatch(), view.getTopBarCallback());
        Gdx.input.setInputProcessor(view);
    }

    @Override
    public void show() {
        // Play game music
        Audio.playMusic(GameMusic.GAME);
        Gdx.input.setInputProcessor(view);
    }

    @Override
    public void render(float delta) {
        if (!paused) {
            gameWorld.update(delta);
        }
        view.act(delta);
        view.draw();
    }

    @Override
    public void resize(int width, int height) {
        gameWorld.resize(width, height);
        view.resize(width, height);
    }

    @Override
    public void pause() {
        super.pause();
        view.pause();
    }

    @Override
    public void resume() {
        super.resume();
        view.resume();
    }

    @Override
    public void hide() {
        super.hide();
        view.hide();
        Audio.stopMusic();
    }

    @Override
    public void dispose() {
        super.dispose();
        view.dispose();
    }

    public class ViewCallbackHandler {
        public void onWorldClick(int screenX, int screenY) {
            System.out.println("Clicked at " + screenX + ", " + screenY);
            gameWorld.clickOnWorld(screenX, screenY);
        }
        public void onBackPressed() {
            game.setScreen(parent);
        }
        public void onPause() {
            paused = true;
        }
        public void onResume() {
            paused = false;
        }
        public GameWorld getWorld() {
            return gameWorld;
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

        public void onCancelButtonClicked() {
        }
    }

}
