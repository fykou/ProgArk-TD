package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;
import com.artemis.annotations.EntityId;

public class HasTargetComponent extends Component {
    @EntityId public int targetId;
    public boolean canShoot = false;
}


