package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;
import com.artemis.PooledComponent;
import com.artemis.annotations.PooledWeaver;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

@PooledWeaver
public class TextureComponent extends PooledComponent {

    public TextureRegion region;
    public float offsetRotation; // degrees, as a workaround for turrets pointing "north" instead of to the right
    public float scale;

    public TextureComponent(TextureRegion region, float offsetRotation, float scale) {
        this.region = region;
        this.offsetRotation = offsetRotation;
        this.scale = scale;
    }

    public TextureComponent() {
        this(0);
    }

    public TextureComponent(float offsetRotation) {
        this(offsetRotation, 1);
    }

    public TextureComponent(float offsetRotation, float scale) {
        this(null, offsetRotation, scale);
    }

    @Override
    protected void reset() {
        region = null;
        offsetRotation = 0;
        scale = 1;
    }
}
