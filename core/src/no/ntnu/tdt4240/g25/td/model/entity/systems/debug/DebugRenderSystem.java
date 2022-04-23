package no.ntnu.tdt4240.g25.td.model.entity.systems.debug;

import com.artemis.BaseSystem;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import no.ntnu.tdt4240.g25.td.model.entity.systems.MyCameraSystem;

public class DebugRenderSystem extends BaseSystem {

    private final SpriteBatch batch;
    private MyCameraSystem cameraSystem;
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;

    public DebugRenderSystem(SpriteBatch batch) {
        this.batch = batch;
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    protected void processSystem() {
        cameraSystem.viewport.apply();
        DebugUtils.drawGrid(cameraSystem.viewport, shapeRenderer, 1);
    }
    @Override
    protected void dispose() {
        super.dispose();
        shapeRenderer.dispose();
    }
}
