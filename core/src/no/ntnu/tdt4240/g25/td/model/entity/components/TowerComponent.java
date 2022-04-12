package no.ntnu.tdt4240.g25.td.model.entity.components;


import com.artemis.Component;

import no.ntnu.tdt4240.g25.td.model.TowerType;

public class TowerComponent extends Component {

    public float range;
    public float damage;
    public float fireRate;
    public float cooldown;
    public float splashRadius;
    public int level;
    // keep track of the tower type for doing upgrades later on
    public TowerType type;

    public TowerComponent(float range, float splashRadius, float damage, float fireRate, int level, TowerType type) {
        this.range = range;
        this.damage = damage;
        this.fireRate = fireRate;
        this.cooldown = 0;
        this.splashRadius = splashRadius;
        this.level = level;
        this.type = type;
    }
    public TowerComponent() {
        this(0, 0, 0, 0, 1, TowerType.TYPE_1);
    }

    public float currentCooldown() {
        return cooldown;
    }
}
