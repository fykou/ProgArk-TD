package no.ntnu.tdt4240.g25.td.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool.Poolable;

public class SpriteComponent implements Component, Poolable {

    public TextureRegion textureRegion;

    public SpriteComponent() {
        textureRegion = null;
    }

    public SpriteComponent(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    @Override
    public void reset() {
        textureRegion = null;
    }
}
