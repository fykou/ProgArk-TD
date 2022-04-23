package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;
import com.artemis.PooledComponent;
import com.artemis.annotations.PooledWeaver;

public class DamageComponent extends PooledComponent {
    public float damage = 0;

    @Override
    protected void reset() {
        damage = 0;
    }
}
