package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;

import no.ntnu.tdt4240.g25.td.model.entity.components.ExpireComponent;

@All({ExpireComponent.class})
public class ExpireSystem extends IteratingSystem {

    ComponentMapper<ExpireComponent> mExpire;

    @Override
    protected void process(int entityId) {
        ExpireComponent expire = mExpire.get(entityId);
        expire.timeLeft -= world.getDelta();
        if (expire.timeLeft <= 0) {
            world.delete(entityId);
        }
    }
}
