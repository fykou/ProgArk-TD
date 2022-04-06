package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.ashley.utils.ImmutableArray;

import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.VelocityComponent;

public class MovementSystem extends IntervalSystem {
    private final ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
    private final ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
    ImmutableArray<Entity> entities;

    public MovementSystem(float interval) {
        super(interval);
    }

    @Override
    public void addedToEngine(final Engine engine) {
        entities = engine.getEntitiesFor(Family.all(PositionComponent.class, VelocityComponent.class).get());
    }

    @Override
    protected void updateInterval() {
        for (final Entity entity : entities) {
            final PositionComponent position = pm.get(entity);
            final VelocityComponent velocity = vm.get(entity);

            float delta = getInterval();
            position.position.x += velocity.velocity.x * delta;
            position.position.y += velocity.velocity.y * delta;
            // change rotation to match velocity direction, where (1,1) is up and right
            position.rotation = (float) Math.atan2(velocity.velocity.y, velocity.velocity.x);
        }
    }
}
