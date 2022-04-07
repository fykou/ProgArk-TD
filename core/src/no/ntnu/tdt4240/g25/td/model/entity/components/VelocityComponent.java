package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

public class VelocityComponent implements Component, Poolable {
    public Vector2 velocity = new Vector2();
    public VelocityComponent() {
        velocity.setZero();
    }
    public VelocityComponent(float x, float y) {
        velocity.set(x, y);
    }
    public VelocityComponent(Vector2 v) {
        velocity.set(v);
    }
    @Override
    public void reset() {
        velocity.setZero();
    }
}

