package no.ntnu.tdt4240.g25.td.model.entity.systems;


import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;

import no.ntnu.tdt4240.g25.td.model.entity.components.ExpireComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.MobComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PathComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.VelocityComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.WaypointsComponent;

@All({VelocityComponent.class, PositionComponent.class, MobComponent.class, PathComponent.class})
public class PathingSystem extends IteratingSystem {

    ComponentMapper<PositionComponent> mPosition;
    ComponentMapper<VelocityComponent> mVelocity;
    ComponentMapper<PathComponent> mPath;
    ComponentMapper<MobComponent> mMob;
    ComponentMapper<ExpireComponent> mExpire;

    WaypointsComponent waypoints;


    @Override
    protected void process(int entityId) {
        final PositionComponent position = mPosition.get(entityId);
        final VelocityComponent velocity = mVelocity.get(entityId);
        final PathComponent path = mPath.get(entityId);
        final MobComponent mob = mMob.get(entityId);

        // if velocity is zero, set velocity to the next checkpoint
        if (velocity.get().len() == 0) {
            Vector2 nextCheckpoint = waypoints.path.get(path.currentCheckpoint);
            Vector2 direction = nextCheckpoint.cpy().sub(position.get());
            velocity.get().set(direction.setLength(mob.speed));
        }

        // if close enough to the next checkpoint that it would be overshot on next update, move to
        // next checkpoint. If no more checkpoints, expire and subtract life from player
        if (position.get().dst(waypoints.path.get(path.currentCheckpoint)) > velocity.get().len() * world.delta
                && velocity.get().len() >= 0.0f) {
            if (path.currentCheckpoint == waypoints.path.size) {
                ExpireComponent expire = mExpire.create(entityId);
                expire.timeLeft = 0;
                // TODO: subtract life
            }
            return;
        }

        path.currentCheckpoint++;

        Vector2 nextCheckpoint = waypoints.path.get(path.currentCheckpoint).cpy();
        Vector2 direction = nextCheckpoint.sub(position.get());

        velocity.get().set(direction).setLength(mob.speed);

        /*if (path.getRight()) {
            if (path.previousCoordinate < path.currentCheckpoint && path.currentCheckpoint <= position.get().x) {
                position.get().set(checkpoints[path.checkpointNumber]);
                path.checkpointNumber++;
                path.currentCheckpoint = (checkpoints[path.checkpointNumber].y);
                if (position.get().y - path.currentCheckpoint < 0) {
                    path.setUp(true);
                    velocity.get().set(up);
                }
                else {
                    path.setDown(true);
                    velocity.get().set(down);
                }
                path.setLeft(false);
                path.setRight(false);
            }
            path.setPreviousCoordinate(position.get().x);
        }

        if(path.getLeft()) {
            if (path.previousCoordinate > path.currentCheckpoint && path.currentCheckpoint >= position.get().x) {
                position.get().set(checkpoints[path.checkpointNumber]);
                path.checkpointNumber++;
                path.currentCheckpoint = checkpoints[path.checkpointNumber].y;
                if (position.get().y - path.currentCheckpoint < 0) {
                    path.setUp(true);
                    velocity.get().set(up);
                } else {
                    path.setDown(true);
                    velocity.get().set(down);
                }
                path.setLeft(false);
                path.setRight(false);
            }
            path.setPreviousCoordinate(position.get().x);
        }


        if (path.getUp()) {
            if (path.previousCoordinate < path.currentCheckpoint && path.currentCheckpoint <= position.get().y) {
                position.get().set(checkpoints[path.checkpointNumber]);
                path.checkpointNumber++;
                path.currentCheckpoint = checkpoints[path.checkpointNumber].x;
                if (position.get().x - path.currentCheckpoint < 0) {
                    path.setRight(true);
                    velocity.get().set(right);
                }
                else {
                    path.setLeft(true);
                    velocity.get().set(left);
                }
                path.setUp(false);
                path.setDown(false);
            }
            path.setPreviousCoordinate(position.get().y);
        }

        if(path.getDown()) {
            if (path.previousCoordinate > path.currentCheckpoint && path.currentCheckpoint >= position.get().y) {
                position.get().set(checkpoints[path.checkpointNumber]);
                path.checkpointNumber++;
                path.currentCheckpoint = checkpoints[path.checkpointNumber].x;
                if (position.get().x - path.currentCheckpoint < 0) {
                    path.setRight(true);
                    velocity.get().set(right);
                }
                else {
                    path.setLeft(true);
                    velocity.get().set(left);
                }
                path.setUp(false);
                path.setDown(false);
            }
            path.setPreviousCoordinate(position.get().y);
        }*/
    }
}
