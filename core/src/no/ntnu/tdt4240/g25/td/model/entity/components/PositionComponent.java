package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector3;

public class PositionComponent extends Component {

    public Vector3 position;

    public PositionComponent(float x, float y) {
        this(x, y, 0);
    }

    public PositionComponent() {
        this(0, 0, 0);
    }

    public PositionComponent(float x, float y, float z) {
        this.position = new Vector3(x, y, z);
    }

    public Vector3 get() {
        return position;
    }
}

