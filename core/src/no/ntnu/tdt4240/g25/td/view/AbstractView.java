package no.ntnu.tdt4240.g25.td.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import no.ntnu.tdt4240.g25.td.TdGame;

public abstract class AbstractView extends Stage implements View {
    public static final Viewport viewport = new FitViewport(TdGame.UI_WIDTH, TdGame.UI_HEIGHT, new OrthographicCamera());

    public AbstractView(Viewport fitViewport, SpriteBatch batch) {
        super(fitViewport, batch);
    }

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public void resize(int width, int height) {
        this.getViewport().update(width, height, true);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {

    }
}
