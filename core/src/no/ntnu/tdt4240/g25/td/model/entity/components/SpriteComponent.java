package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool.Poolable;

public class SpriteComponent implements Component, Poolable {

    public TextureRegion region;

    public SpriteComponent() {
        region = null;
    }

    public SpriteComponent(TextureRegion region) {
        this.region = region;
    }

    @Override
    public void reset() {
        region = null;
    }
}
