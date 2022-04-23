package no.ntnu.tdt4240.g25.td.model.entity.systems;


import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IntervalIteratingSystem;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

import no.ntnu.tdt4240.g25.td.model.entity.components.MobComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PathComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.RotationComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.VelocityComponent;

@All({VelocityComponent.class, PositionComponent.class, MobComponent.class, PathComponent.class})
public class PathingSystem extends IteratingSystem {
    ComponentMapper<PositionComponent> mTransform;
    ComponentMapper<VelocityComponent> mVelocity;
    ComponentMapper<RotationComponent> mRotation;
    ComponentMapper<PathComponent> mPath;

    int speed = 20;

    Vector2 left = new Vector2(-speed, 0);
    Vector2 right = new Vector2(speed, 0);
    Vector2 up = new Vector2(0, speed);
    Vector2 down = new Vector2(0, -speed);

    Vector2[] checkpoints = {new Vector2(250, 300), //must also change in Path commponent current checkpoint to y
            new Vector2(350, 300), new Vector2(350, 400), new Vector2(300, 400),
            new Vector2(300, 350), new Vector2(350, 350)};

    @Override
    protected void process(int entityId) {
        final PositionComponent position = mTransform.get(entityId);
        final VelocityComponent velocity = mVelocity.get(entityId);
        final PathComponent path = mPath.get(entityId);
        final RotationComponent rotation = mRotation.has(entityId) ? mRotation.get(entityId) : null;

        float delta = this.world.getDelta();
        position.get().x += velocity.get().x * delta;
        position.get().y += velocity.get().y * delta;

        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        if (path.getRight()) {
            if (path.getPreviousCoordinate() < path.getCurrentCheckpoint() && path.getCurrentCheckpoint() <= position.get().x) {
                position.get().set(checkpoints[path.getCheckpointNumber()]);
                path.setCheckpointNumber(path.getCheckpointNumber()+1);
                path.setCurrentCheckpoint(checkpoints[path.getCheckpointNumber()].y);
                if (position.get().y - path.getCurrentCheckpoint() < 0) {
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
            if (path.getPreviousCoordinate() > path.getCurrentCheckpoint() && path.getCurrentCheckpoint() >= position.get().x) {
                position.get().set(checkpoints[path.getCheckpointNumber()]);
                path.setCheckpointNumber(path.getCheckpointNumber() + 1);
                path.setCurrentCheckpoint(checkpoints[path.getCheckpointNumber()].y);
                if (position.get().y - path.getCurrentCheckpoint() < 0) {
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
            if (path.getPreviousCoordinate() < path.getCurrentCheckpoint() && path.getCurrentCheckpoint() <= position.get().y) {
                position.get().set(checkpoints[path.getCheckpointNumber()]);
                path.setCheckpointNumber(path.getCheckpointNumber()+1);
                path.setCurrentCheckpoint(checkpoints[path.getCheckpointNumber()].x);
                if (position.get().x - path.getCurrentCheckpoint() < 0) {
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
            if (path.getPreviousCoordinate() > path.getCurrentCheckpoint() && path.getCurrentCheckpoint() >= position.get().y) {
                position.get().set(checkpoints[path.getCheckpointNumber()]);
                path.setCheckpointNumber(path.getCheckpointNumber()+1);
                path.setCurrentCheckpoint(checkpoints[path.getCheckpointNumber()].x);
                if (position.get().x - path.getCurrentCheckpoint() < 0) {
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
    }
}
