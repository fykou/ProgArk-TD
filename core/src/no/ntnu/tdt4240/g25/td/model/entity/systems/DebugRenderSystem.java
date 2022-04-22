package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.BaseSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class DebugRenderSystem extends BaseSystem {

    private final SpriteBatch batch;
    private MyCameraSystem cameraSystem;
    private BitmapFont font;
    private Texture debugGrid;

    public DebugRenderSystem(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    protected void initialize() {
        super.initialize();
        debugGrid = new Texture(Gdx.files.internal("debug_grid.png"));
    }

    @Override
    protected void processSystem() {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(cameraSystem.viewport.getCamera().combined);
        batch.enableBlending();

        batch.begin();
        batch.draw(debugGrid, 0, 0, 9, 16);
        batch.end();
    }
}
