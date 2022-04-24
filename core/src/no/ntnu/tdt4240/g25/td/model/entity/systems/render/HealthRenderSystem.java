package no.ntnu.tdt4240.g25.td.model.entity.systems.render;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import no.ntnu.tdt4240.g25.td.model.entity.components.MobComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.systems.MyCameraSystem;

@All({MobComponent.class, PositionComponent.class})
public class HealthRenderSystem extends IteratingSystem {

    private ComponentMapper<MobComponent> mMob;
    private ComponentMapper<PositionComponent> mPosition;
    private MyCameraSystem cameraSystem;

    @Wire
    private ShapeRenderer renderer;

    @Override
    protected void process(int entityId) {
        MobComponent mob = mMob.get(entityId);
        PositionComponent position = mPosition.get(entityId);
        renderer.setProjectionMatrix(cameraSystem.viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        // mob position is the center of the mob, so we need to offset the health bar,
        // so that it is drawn in the correct position
        float width = 0.5f;
        float progressWidth = width * Math.max(mob.health / mob.maxHealth, 0);
        float height = 0.1f;
        renderer.setColor(Color.RED);
        renderer.rect(position.get().x - .5f + width/2, position.get().y - .5f, width, height);
        renderer.setColor(Color.GREEN);
        renderer.rect(position.get().x - .5f + width/2, position.get().y - .5f, progressWidth, height);
        renderer.end();
    }
}
