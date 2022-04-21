package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.math.MathUtils;

import no.ntnu.tdt4240.g25.td.model.entity.components.HasTargetComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.RotationComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.StateComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TowerComponent;

@All({TowerComponent.class, HasTargetComponent.class, PositionComponent.class, RotationComponent.class, StateComponent.class})
public class AimingSystem extends IteratingSystem {

    public static final int MAX_ANGLE_DIFF_TO_FIRE = 1;
    public static final int TURN_RATE_DEGREES = 180;

    ComponentMapper<TowerComponent> mTower;
    ComponentMapper<HasTargetComponent> mHasTarget;
    ComponentMapper<PositionComponent> mPosition;
    ComponentMapper<RotationComponent> mRotation;
    ComponentMapper<StateComponent> mState;

    @Override
    protected void process(int entityId) {
        TowerComponent tower = mTower.get(entityId);
        RotationComponent rotation = mRotation.get(entityId);
        PositionComponent position = mPosition.get(entityId);
        HasTargetComponent target = mHasTarget.get(entityId);

        // first, check if current target is within range, if not, remove HasTargetComponent from
        // the entity and return.
        // TODO: check if target is still alive (this check should be done before the range check,
        //  as range checking could result in a null pointer or invalid entity id)
        if (target.targetId == -1) {
            mHasTarget.remove(entityId);
            return;
        }
        PositionComponent enemyPosition = mPosition.get(target.targetId);
        if (position.get().dst(enemyPosition.get()) > tower.range) {
            mPosition.remove(target.targetId);
            mState.get(entityId).set(StateComponent.STATE_IDLE, false);
            return;
        }

        // first, if the tower can rotate, aim towards the target, setting rotation to within +/- MAX_ANGLE_DIFF_TO_FIRE
        // of the target position, rotating at a rate of 180 degrees per second.
        float goalAngle = enemyPosition.get().cpy().sub(position.get()).angleDeg();

        // get the difference between the current angle and the goal angle
        float difference = goalAngle - rotation.get();

        // subtract 360 if the difference is greater than 180 degrees, so we can get the shortest direction
        if (difference > 180f) difference -= 360f;
        float direction = Math.signum(difference);

        if (Math.abs(difference) > MAX_ANGLE_DIFF_TO_FIRE) {
            float toTurn = direction * TURN_RATE_DEGREES * world.delta;
            if (Math.abs(toTurn) > Math.abs(difference)) toTurn = difference;
            rotation.set(rotation.get() + toTurn);
        }
        if (Math.abs(difference) < MAX_ANGLE_DIFF_TO_FIRE) {
            // if the difference is less than MAX_ANGLE_DIFF_TO_FIRE, allow the tower to fire
            target.canShoot = true;
        }
    }
}

