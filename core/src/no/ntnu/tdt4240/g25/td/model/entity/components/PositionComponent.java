package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

public class PositionComponent extends Component {

    public Vector2 position;
    public float z; // z-index for rendering

    public PositionComponent(float x, float y) {
        this(x, y, 0);
    }

    public PositionComponent() {
        this(0, 0, 0);
    }

    public PositionComponent(float x, float y, float z) {
        this.position = new Vector2(x, y);
        this.z = z;
    }

    public Vector2 get() {
        return position;
    }
}

