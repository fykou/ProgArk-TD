package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IntervalSystem;
import com.badlogic.ashley.utils.ImmutableArray;

import no.ntnu.tdt4240.g25.td.model.entity.components.TransformComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.VelocityComponent;

public class MovementSystem extends IntervalSystem {
    private final ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);
    private final ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
    ImmutableArray<Entity> entities;

    public MovementSystem(float interval) {
        super(interval);
    }

    @Override
    public void addedToEngine(final Engine engine) {
        entities = engine.getEntitiesFor(Family.all(TransformComponent.class, VelocityComponent.class).get());
    }

    @Override
    protected void updateInterval() {
        for (final Entity entity : entities) {
            final TransformComponent position = tm.get(entity);
            final VelocityComponent velocity = vm.get(entity);

            float delta = getInterval();
            position.position.x += velocity.velocity.x * delta;
            position.position.y += velocity.velocity.y * delta;
            // change rotation to the angle in degrees counter clockwise matching the velocity vector.

            position.rotation = velocity.velocity.angleDeg();

            // this is wrong, because the velocity is not in the direction of the rotation. The sprite is pointing 90 degrees to the left compared
            // to the velocity vector. Fix this

        }
    }
}
