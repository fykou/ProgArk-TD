package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;
import com.artemis.PooledComponent;

import no.ntnu.tdt4240.g25.td.model.MobType;

public class MobComponent extends PooledComponent {

    public MobType type;
    public int health;
    public int maxHealth;
    public float speed;

    public MobComponent(MobType type, int health, int maxHealth, float speed) {
        this.type = type;
        this.health = health;
        this.maxHealth = maxHealth;
        this.speed = speed;
    }

    public MobComponent(MobType mobType) {
        this(mobType, 100, 100, 1);
    }

    public MobComponent() {
        this(MobType.NORMAL, 100, 100, 1);
    }

    @Override
    protected void reset() {
        type = MobType.NORMAL;
        health = 0;
        maxHealth = 0;
        speed = 0;
    }
}
