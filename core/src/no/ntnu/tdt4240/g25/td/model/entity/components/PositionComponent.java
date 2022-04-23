package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.PooledComponent;
import com.badlogic.gdx.math.Vector2;

public class PositionComponent extends PooledComponent {

    public Vector2 position;
    public float z; // z-index for rendering

    public PositionComponent() {
        position = new Vector2();
    }


    public Vector2 get() {
        return position;
    }

    @Override
    protected void reset() {
        position.setZero();
        z = 0;
    }
}

