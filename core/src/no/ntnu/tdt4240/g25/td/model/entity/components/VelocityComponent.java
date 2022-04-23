package no.ntnu.tdt4240.g25.td.model.entity.components;


import com.artemis.PooledComponent;
import com.badlogic.gdx.math.Vector2;

public class VelocityComponent extends PooledComponent {
    public Vector2 velocity;

    public VelocityComponent() {
        velocity = new Vector2();
    }


    public Vector2 get() {
        return velocity;
    }

    @Override
    protected void reset() {
        velocity.setZero();
    }
}