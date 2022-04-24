package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.PooledComponent;
import com.artemis.annotations.EntityId;


public class HasTargetComponent extends PooledComponent {
    @EntityId public int targetId = -1;
    public boolean canShoot = false;

    @Override
    protected void reset() {
        targetId = -1;
        canShoot = false;
    }
}


