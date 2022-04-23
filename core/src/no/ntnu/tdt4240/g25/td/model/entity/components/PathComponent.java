package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;
import com.artemis.PooledComponent;
import com.badlogic.gdx.math.Vector2;

public class PathComponent extends PooledComponent {
    public int currentCheckpoint;

    @Override
    protected void reset() {
        currentCheckpoint = 0;
    }
}

