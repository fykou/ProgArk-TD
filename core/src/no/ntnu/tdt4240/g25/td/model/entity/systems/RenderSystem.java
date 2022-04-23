package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.RotationComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TextureComponent;

@All({PositionComponent.class, TextureComponent.class})
public class RenderSystem extends SortedIteratingSystem {

    ComponentMapper<TextureComponent> mTexture;
    ComponentMapper<PositionComponent> mPosition;
    ComponentMapper<RotationComponent> mRotation;

    @Wire
    private SpriteBatch batch;

    MyCameraSystem cameraSystem;

    public RenderSystem() {
        super();
    }


    @Override
    protected void process(int entityId) {
        TextureComponent textureComponent = mTexture.get(entityId);
        PositionComponent position = mPosition.get(entityId);
        RotationComponent rotation = mRotation.has(entityId) ? mRotation.get(entityId) : null;
        if (textureComponent.region == null) {
            return;
        }
        float width = textureComponent.region.getRegionWidth() / 128f;
        float height = textureComponent.region.getRegionHeight() / 128f;
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

        batch.setProjectionMatrix(cameraSystem.viewport.getCamera().combined);
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
