package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.DelayedIteratingSystem;

import no.ntnu.tdt4240.g25.td.model.entity.components.HasTargetComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.StateComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TowerComponent;

@All({TowerComponent.class, StateComponent.class, HasTargetComponent.class})
public class FiringSystem extends DelayedIteratingSystem {

    ComponentMapper<TowerComponent> mTower;
    ComponentMapper<StateComponent> mState;
    ComponentMapper<HasTargetComponent> mTarget;

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
        // First, we check whether the tower can fire, i.e. if the tower is aiming at a target,
        // by checking the target component that is set by the targeting system.
        var target = mTarget.get(entityId);
        if (!target.canShoot) {
            return; // exit here if the tower cannot fire, in order to not reset the cooldown
        }

        var state = mState.get(entityId);
        var tower = mTower.get(entityId);
        tower.cooldown = 1/tower.fireRate;
    }
}
