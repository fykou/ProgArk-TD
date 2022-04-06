package no.ntnu.tdt4240.g25.td.model;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.utils.Pool;

import no.ntnu.tdt4240.g25.td.model.entity.systems.MovementSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.RenderSystem;

public class World {
    PooledEngine engine;

    public World(Engine engine) {

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
