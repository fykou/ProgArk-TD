package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Rectangle;

public class BoundsComponent extends Component {
    public Rectangle bounds;

    public BoundsComponent() {
        this(0, 0);
    }

    public BoundsComponent(float width, float height) {
        this(new Rectangle(0, 0, width, height));
    }

    public BoundsComponent(Rectangle bounds) {
        this.bounds = bounds;
    }

    public Rectangle get() {
        return bounds;
    }

}
