package no.ntnu.tdt4240.g25.td.model.entity.systems.render;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.RotationComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TextureComponent;
import no.ntnu.tdt4240.g25.td.model.entity.systems.MyCameraSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.SortedIteratingSystem;

@All({PositionComponent.class, TextureComponent.class})
public class RenderSystem extends IteratingSystem {

    ComponentMapper<TextureComponent> mTexture;
    ComponentMapper<PositionComponent> mPosition;
    ComponentMapper<RotationComponent> mRotation;

    @Wire
    SpriteBatch batch;

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
    }
}
