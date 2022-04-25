package no.ntnu.tdt4240.g25.td.model.entity.systems.debug;

import com.artemis.BaseSystem;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import no.ntnu.tdt4240.g25.td.asset.Assets;
import no.ntnu.tdt4240.g25.td.asset.Font;
import no.ntnu.tdt4240.g25.td.model.entity.systems.MyCameraSystem;

public class DebugRenderSystem extends BaseSystem {


    private MyCameraSystem cameraSystem;
    private BitmapFont font;
    @Wire
    private ShapeRenderer shapeRenderer;
    @Wire
    private SpriteBatch spriteBatch;

    @Override
    protected void processSystem() {
        cameraSystem.viewport.apply();
        DebugUtils.drawGrid(cameraSystem.viewport, shapeRenderer, 1);
        spriteBatch.setProjectionMatrix(cameraSystem.guiViewport.getCamera().combined);
        spriteBatch.begin();
        Assets.getInstance().getFont(Font.LARGE).draw(spriteBatch, "FPS=" + Gdx.graphics.getFramesPerSecond(), 0, cameraSystem.guiViewport.getCamera().viewportHeight);
        spriteBatch.end();
    }
}
