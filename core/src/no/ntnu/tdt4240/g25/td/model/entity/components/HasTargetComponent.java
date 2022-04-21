package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;
import com.artemis.annotations.EntityId;
import com.artemis.annotations.PooledWeaver;

@PooledWeaver
public class HasTargetComponent extends Component {
    @EntityId public int targetId = -1;
    public boolean canShoot = false;
}


