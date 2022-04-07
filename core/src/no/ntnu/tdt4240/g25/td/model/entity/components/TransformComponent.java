package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool.Poolable;

public class TransformComponent implements Component, Poolable {

    public Vector3 position;
    public float rotation;

    public TransformComponent() {
        position = new Vector3();
        rotation = 0;
    }

    @Override
    public void reset() {
        position.setZero();
        rotation = 0;
    }
}

