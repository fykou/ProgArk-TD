package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;

import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.SpriteComponent;

public class RenderSystem extends SortedIteratingSystem {

    private final ComponentMapper<SpriteComponent> spriteMapper;
    private final ComponentMapper<PositionComponent> positionMapper;


    private final SpriteBatch batch;
    private final Array<Entity> renderQueue;
    private final OrthographicCamera cam;
    private final Comparator<Entity> comparator;

    public RenderSystem(SpriteBatch batch) {
        super(Family.all(SpriteComponent.class, PositionComponent.class).get(), new ZComparator());
        this.batch = batch;
        comparator = new ZComparator();
        cam = new OrthographicCamera();
        renderQueue = new Array<>();
        spriteMapper = ComponentMapper.getFor(SpriteComponent.class);
        positionMapper = ComponentMapper.getFor(PositionComponent.class);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        renderQueue.sort(comparator);
        batch.setProjectionMatrix(cam.combined);
        batch.enableBlending();
        batch.begin();
        for (Entity e : new Array.ArrayIterator<>(renderQueue)) {
            SpriteComponent sprite = spriteMapper.get(e);
            PositionComponent position = positionMapper.get(e);
            if (sprite.region == null) {
                continue;
            }

            float width = sprite.region.getRegionWidth();
            float height = sprite.region.getRegionHeight();
            float originX = width/2f;
            float originY = height/2f;

            batch.draw(sprite.region,
                    position.position.x - originX, position.position.y - originY,
                    originX, originY,
                    width, height,
                    1, 1,
                    position.rotation);

        }
        batch.end();
        renderQueue.clear();
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        renderQueue.add(entity);
    }
}
