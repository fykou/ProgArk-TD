package no.ntnu.tdt4240.g25.td.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class HitPointsComponent implements Component, Poolable {
    public int hitPoints;
    public int maxHitPoints;

    public HitPointsComponent(int maxHitPoints) {
        this.hitPoints = this.maxHitPoints = maxHitPoints;
    }

    @Override
    public void reset() {
        this.hitPoints = 0;
        this.maxHitPoints = 0;
    }
}

