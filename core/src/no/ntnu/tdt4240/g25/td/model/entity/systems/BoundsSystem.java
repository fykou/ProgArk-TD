package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;

import no.ntnu.tdt4240.g25.td.model.entity.components.BoundsComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;

@All({BoundsComponent.class, PositionComponent.class})
public class BoundsSystem extends IteratingSystem {

    ComponentMapper<BoundsComponent> mBounds;
    ComponentMapper<PositionComponent> mPosition;

    @Override
    protected void process(int entityId) {
        BoundsComponent bounds = mBounds.get(entityId);
        PositionComponent position = mPosition.get(entityId);

        bounds.bounds.setCenter(position.get().x, position.get().y);
    }
}
