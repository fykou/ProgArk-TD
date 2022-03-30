package no.ntnu.tdt4240.g25.td.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Comparator;

import no.ntnu.tdt4240.g25.td.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.entity.components.SpriteComponent;

public class RenderSystem extends SortedIteratingSystem {

    private ComponentMapper<SpriteComponent> spriteMapper = ComponentMapper.getFor(SpriteComponent.class);
    private ComponentMapper<PositionComponent> positionMapper = ComponentMapper.getFor(PositionComponent.class);


    private SpriteBatch batch;


    public RenderSystem(SpriteBatch batch) {
        super(Family.all(SpriteComponent.class, PositionComponent.class).get(), new ZComparator());
        this.batch = batch;
    }


    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        // TODO: Implement

    }
}
