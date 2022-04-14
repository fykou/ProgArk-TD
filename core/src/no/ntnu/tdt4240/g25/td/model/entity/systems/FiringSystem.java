package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;

import no.ntnu.tdt4240.g25.td.model.entity.components.HasTargetComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.StateComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TowerComponent;
import no.ntnu.tdt4240.g25.td.model.entity.factories.ProjectileFactory;

@All({TowerComponent.class, StateComponent.class, HasTargetComponent.class, PositionComponent.class})
public class FiringSystem extends IteratingSystem {

    public static int PROJECTILE_SPEED = 750;

    @Wire
    ProjectileFactory projectileFactory;
    ComponentMapper<TowerComponent> mTower;
    ComponentMapper<StateComponent> mState;
    ComponentMapper<HasTargetComponent> mTarget;
    ComponentMapper<PositionComponent> mPosition;

    @Override
    protected void process(int entityId) {
        var target = mTarget.get(entityId);
        var tower = mTower.get(entityId);

        tower.cooldown -= world.getDelta();
        if (tower.cooldown > 0 || !target.canShoot) {
            return; // exit here if the tower cannot fire, or if it is on cooldown
        }
        var state = mState.get(entityId);
        var position = mPosition.get(entityId);
        // create the projectile and set the position to the tower position, velocity towards the target
        // and offset the position by the towers height for it to spawn roughly at the tip of the turret
        var startPosition = position.position.cpy();
        var enemyPosition = mPosition.get(target.targetId).position;
        var velocity = enemyPosition.cpy().sub(startPosition).setLength(PROJECTILE_SPEED);
        var offset = velocity.cpy().setLength(50);
        startPosition.add(offset);
        projectileFactory.create(startPosition.x, startPosition.y, velocity.x, velocity.y, tower.damage, tower.splashRadius);
        state.set(StateComponent.STATE_ATTACKING, false);
        tower.cooldown = 1/tower.fireRate;
    }
}
