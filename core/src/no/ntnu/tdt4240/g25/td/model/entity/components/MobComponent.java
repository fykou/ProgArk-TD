package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;
import com.artemis.PooledComponent;

import no.ntnu.tdt4240.g25.td.model.MobType;

public class MobComponent extends PooledComponent {

    public MobType type = MobType.NORMAL;
    public float maxHealth = 0;
    public float health = maxHealth;
    public float speed = 0f;

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
