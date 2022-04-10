package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;

public class RotationComponent extends Component {
    private float rotation;

    public RotationComponent(float rotation) {
        this.rotation = rotation;
    }

    public RotationComponent() {
        this(0);
    }

    public float get() {
        return rotation;
    }

    public void set(float rotation) {
        this.rotation = rotation;
    }
}
