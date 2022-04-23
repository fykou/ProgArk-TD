package no.ntnu.tdt4240.g25.td.model.entity.systems;


import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import no.ntnu.tdt4240.g25.td.model.entity.components.ExpireComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.MobComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PathComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.VelocityComponent;

@All({VelocityComponent.class, PositionComponent.class, MobComponent.class, PathComponent.class})
public class PathingSystem extends IteratingSystem {
    ComponentMapper<PositionComponent> mPosition;
    ComponentMapper<VelocityComponent> mVelocity;
    ComponentMapper<PathComponent> mPath;
    ComponentMapper<MobComponent> mMob;
    ComponentMapper<ExpireComponent> mExpire;

    Array<Vector2> checkpoints = new Array<>();

    @Override
    protected void initialize() {
        checkpoints.addAll(
                new Vector2(250, 300),
                new Vector2(350, 300), new Vector2(350, 400), new Vector2(300, 400),
                new Vector2(300, 350), new Vector2(350, 350)
        );
    }

    @Override
    protected void process(int entityId) {
        final PositionComponent position = mPosition.get(entityId);
        final VelocityComponent velocity = mVelocity.get(entityId);
        final PathComponent path = mPath.get(entityId);
        final MobComponent mob = mMob.get(entityId);

        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        // if the mob has reached the end, add expire component to despawn and subtract life
        if (path.currentCheckpoint == checkpoints.size) {
            ExpireComponent expire = mExpire.create(entityId);
            expire.timeLeft = 0;
            // TODO: subtract life
            return;
        }

        // if velocity is zero, set velocity to the next checkpoint
        if (velocity.get().len() == 0) {
            Vector2 nextCheckpoint = checkpoints.get(path.currentCheckpoint);
            Vector2 direction = nextCheckpoint.cpy().sub(position.get());
            velocity.get().set(direction.setLength(mob.speed * 20));
        }

        // if close enough to the next checkpoint, move to next checkpoint
        if (position.get().dst(checkpoints.get(path.currentCheckpoint)) > velocity.get().len() && velocity.get().len() >= 0.0f) return;

        path.currentCheckpoint++;

        Vector2 nextCheckpoint = checkpoints.get(path.currentCheckpoint).cpy();
        Vector2 direction = nextCheckpoint.sub(position.get());

        velocity.get().set(direction).setLength(mob.speed * 20);

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
