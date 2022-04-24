package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;
import com.artemis.PooledComponent;
import com.badlogic.gdx.math.Rectangle;

public class BoundsComponent extends PooledComponent {
    public Rectangle bounds;

    public BoundsComponent() {
        bounds = new Rectangle();
    }

    public Rectangle get() {
        return bounds;
    }

    @Override
    protected void reset() {
        bounds.set(0, 0, 0, 0);
    }

}
