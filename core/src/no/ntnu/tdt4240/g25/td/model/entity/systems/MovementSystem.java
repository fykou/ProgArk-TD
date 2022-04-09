package no.ntnu.tdt4240.g25.td.model.entity.systems;


import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IntervalIteratingSystem;
import com.artemis.systems.IntervalSystem;

import no.ntnu.tdt4240.g25.td.model.entity.components.TransformComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.VelocityComponent;

@All({VelocityComponent.class, TransformComponent.class})
public class MovementSystem extends IntervalIteratingSystem {
    ComponentMapper<TransformComponent> tm;
    ComponentMapper<VelocityComponent> vm;

    public MovementSystem(float interval) {
        super(Aspect.all(VelocityComponent.class, TransformComponent.class), interval);
    }
    @Override
    protected void process(int entityId) {
        final TransformComponent position = tm.get(entityId);
        final VelocityComponent velocity = vm.get(entityId);

        float delta = this.world.getDelta();
        position.x += velocity.x * delta;
        position.y += velocity.y * delta;
        // set rotation to the direction of movement, where
        position.rotation = (float) Math.atan2(velocity.y, velocity.x);
    }
}
