package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.DelayedIteratingSystem;

import no.ntnu.tdt4240.g25.td.model.entity.components.StateComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TowerComponent;

@All({TowerComponent.class, StateComponent.class})
public class FiringSystem extends DelayedIteratingSystem {

    ComponentMapper<TowerComponent> mTower;

    @Override
    protected float getRemainingDelay(int entityId) {
        var tower = mTower.get(entityId);
        return tower.cooldown;
    }

    @Override
    protected void processDelta(int entityId, float accumulatedDelta) {
        var tower = mTower.get(entityId);
        tower.cooldown -= accumulatedDelta;
    }

    @Override
    protected void processExpired(int entityId) {
        var tower = mTower.get(entityId);
        tower.cooldown = 1/tower.fireRate;
    }
}
