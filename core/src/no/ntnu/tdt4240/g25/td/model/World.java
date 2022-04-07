package no.ntnu.tdt4240.g25.td.model;

import com.badlogic.ashley.core.PooledEngine;

public class World {
    PooledEngine engine;

    public World(PooledEngine engine) {
        this.engine = engine;
    }

    public void update(float delta) {
        engine.update(delta);
    }

    public PooledEngine getEngine() {
        return engine;
    }

    public void dispose() {
        engine.clearPools();
    }
}
