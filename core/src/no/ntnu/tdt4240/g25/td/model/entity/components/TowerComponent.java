package no.ntnu.tdt4240.g25.td.model.entity.components;


import com.artemis.PooledComponent;

import no.ntnu.tdt4240.g25.td.model.TowerLevel;
import no.ntnu.tdt4240.g25.td.model.TowerType;

public class TowerComponent extends PooledComponent {

    public float range;
    public float damage;
    public float fireRate;
    public float cooldown;
    public float splashRadius;
    // keep track of the tower type for doing upgrades later on
    public TowerType type;
    public TowerLevel level;

    public TowerComponent() {
        reset();
    }

    public void set(TowerType type, TowerLevel level) {
        range = type.range;
        damage = getDamage(type, level);
        fireRate = getFireRate(type, level);
        cooldown = 0;
        splashRadius = type.splashRadius;
        this.type = type;
        this.level = level;
    }

    @Override
    protected void reset() {
        range = 0;
        damage = 0;
        fireRate = 0;
        cooldown = 0;
        splashRadius = 0;
        type = null;
        level = null;
    }

    private float getDamage(TowerType type, TowerLevel level) {
        float damage;
        switch (level) {
            case MK2:
                damage = type.mk2Damage;
                break;
            case MK3:
                damage = type.mk3Damage;
                break;
            case MK4:
                damage = type.mk4Damage;
                break;
            default: // ie. level 1 or anything else
                damage = type.mk1Damage;
        }
        return damage;
    }

    private float getFireRate(TowerType type, TowerLevel level) {
        float fireRate;
        switch (level) {
            case MK2:
                fireRate = type.mk2FireRate;
                break;
            case MK3:
                fireRate = type.mk3FireRate;
                break;
            case MK4:
                fireRate = type.mk4FireRate;
                break;
            default: // ie. level 1 or anything else
                fireRate = type.mk1FireRate;
        }
        return fireRate;
    }

}
