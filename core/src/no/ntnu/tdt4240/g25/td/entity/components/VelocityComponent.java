package no.ntnu.tdt4240.g25.td.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

public class VelocityComponent implements Component, Poolable {
    public Vector2 velocity = new Vector2();

    public VelocityComponent() {
        velocity.setZero();
    }

    @Override
    public void reset() {
        velocity.setZero();
    }
}

