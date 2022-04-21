package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.RotationComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TextureComponent;

@All({PositionComponent.class, TextureComponent.class})
public class RenderSystem extends SortedIteratingSystem {

    ComponentMapper<TextureComponent> mTexture;
    ComponentMapper<PositionComponent> mPosition;
    ComponentMapper<RotationComponent> mRotation;
    private final SpriteBatch batch;
    private final OrthographicCamera cam;

    public RenderSystem(SpriteBatch batch) {
        super();
        this.batch = batch;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }


    @Override
    protected void process(int entityId) {
        TextureComponent textureComponent = mTexture.get(entityId);
        PositionComponent position = mPosition.get(entityId);
        RotationComponent rotation = mRotation.has(entityId) ? mRotation.get(entityId) : null;
        if (textureComponent.region == null) {
            return;
        }
        float width = textureComponent.region.getRegionWidth();
        float height = textureComponent.region.getRegionHeight();
        float originX = width / 2f;
        float originY = height / 2f;


        float renderRot = rotation != null ? rotation.get() : 0f;
        batch.draw(textureComponent.region,
                position.get().x - originX, position.get().y - originY,
                originX, originY,
                width, height,
                1, 1,
                renderRot + textureComponent.offsetRotation); // TODO: fix rotation/initial direction in sprites rather than here

    }

    @Override
    protected void begin() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(cam.combined);
        batch.enableBlending();
        batch.begin();
    }

    @Override
    protected void end() {
        batch.end();
        entityIds.clear();
    }

    @Override
    protected void sort() {
        entityIds.sort((o1, o2) -> Float.compare(mPosition.get(o1).z, mPosition.get(o2).z));
    }
}
