package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.PooledComponent;

public class ExpireComponent extends PooledComponent {
    public float timeLeft = 5;

    @Override
    protected void reset() {
        this.timeLeft = 5;
    }
}
