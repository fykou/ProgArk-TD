package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;
import com.artemis.PooledComponent;

import no.ntnu.tdt4240.g25.td.model.MobType;

public class MobComponent extends PooledComponent {

    public MobType type;
    public float health;
    public float maxHealth;
    public float speed;


    public MobComponent() {
        this.type = MobType.NORMAL;
        this.health = 0;
        this.maxHealth = 0;
        this.speed = 0;
    }

    public void set(MobType type, float waveMultiplier) {
        this.type = type;
        this.health = type.baseHealth * waveMultiplier;
        this.maxHealth = type.baseHealth * waveMultiplier;
        this.speed = type.baseSpeed;
    }

    @Override
    protected void reset() {
        type = MobType.NORMAL;
        health = 0;
        maxHealth = 0;
        speed = 0;
    }
}
