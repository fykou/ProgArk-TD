package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;

import no.ntnu.tdt4240.g25.td.model.entity.components.HasTargetComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.RotationComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.StateComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TowerComponent;

@All({TowerComponent.class, HasTargetComponent.class, PositionComponent.class})
public class AttackingSystem extends IteratingSystem {

    public static final int MAX_ANGLE_DIFF_TO_FIRE = 3;

    ComponentMapper<TowerComponent> mTower;
    ComponentMapper<HasTargetComponent> mHasTarget;
    ComponentMapper<PositionComponent> mPosition;
    ComponentMapper<RotationComponent> mRotation;
    ComponentMapper<StateComponent> mState;

    @Override
    protected void process(int entityId) {
        // first, check if current target is still alive/in the world
        // if not, remove HasTargetComponent

        TowerComponent tower = mTower.get(entityId);
        PositionComponent position = mPosition.get(entityId);
        HasTargetComponent target = mHasTarget.get(entityId);
        PositionComponent enemyPosition = mPosition.get(target.targetId);
        StateComponent state = mState.get(entityId);


        // first, if the tower can rotate, aim towards the target, setting rotation to within +/- 10 degrees
        // of the target position, rotating at a rate of 180 degrees per second
        if (mRotation.has(entityId)) {
            RotationComponent rotation = mRotation.get(entityId);
            var goalAngle = enemyPosition.get().cpy().sub(position.get()).angleDeg();
            var angleDiff = rotation.get() - goalAngle;
            if (Math.abs(angleDiff) > MAX_ANGLE_DIFF_TO_FIRE) {
                if (angleDiff > 0) {
                    rotation.set(rotation.get() - TowerComponent.TURN_RATE_DEGREES * world.getDelta());
                } else {
                    rotation.set(rotation.get() + TowerComponent.TURN_RATE_DEGREES * world.getDelta());
                }
                // repeat the check to see if we're within the angle range, if not, tower can't fire yet
                angleDiff = rotation.get() - goalAngle;
                if (Math.abs(angleDiff) > MAX_ANGLE_DIFF_TO_FIRE) {
                    return;
                }
            }
        }
        // then, if the tower can fire, fire at the target

    }
}
