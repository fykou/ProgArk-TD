package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.PooledComponent;

public class RotationComponent extends PooledComponent {
    private float rotation;

    public RotationComponent(float rotation) {
        this.rotation = rotation % 360;
    }

    public RotationComponent() {
        this(0);
    }

    public float get() {
        return rotation;
    }

    public void set(float rotation) {
        this.rotation = rotation % 360;
    }

    @Override
    protected void reset() {
        rotation = 0;
    }
}
