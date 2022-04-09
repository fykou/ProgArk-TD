package no.ntnu.tdt4240.g25.td.model.entity.components;


import com.artemis.Component;

public class VelocityComponent extends Component {
    public float x;
    public float y;

    public VelocityComponent(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public VelocityComponent() {
        this(0, 0);
    }
}