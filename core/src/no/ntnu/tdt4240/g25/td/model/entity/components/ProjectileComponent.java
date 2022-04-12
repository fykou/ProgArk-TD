package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;

public class ProjectileComponent extends Component {
    public float damage;
    public float radius; // radius of the explosion, if any

    public ProjectileComponent(float damage, float radius) {
        this.damage = damage;
        this.radius = radius;
    }

    public ProjectileComponent() {
        this(0, 0);
    }
}
