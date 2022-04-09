package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector3;

public class TransformComponent extends Component {

    public float x;
    public float y;
    public float z;
    public float rotation;

    public TransformComponent(float x, float y) {
        this.x = x;
        this.y = y;
        this.z = 0;
        this.rotation = 0;
    }

    public TransformComponent() {
        this(0, 0, 0, 0);
    }

    public TransformComponent(float x, float y, float z, float rotation) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.rotation = rotation;
    }
}

