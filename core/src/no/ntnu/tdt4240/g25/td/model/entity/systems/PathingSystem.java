package no.ntnu.tdt4240.g25.td.model.entity.systems;


import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.annotations.Exclude;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.math.Vector2;

import no.ntnu.tdt4240.g25.td.model.entity.components.ExpireComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.MobComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PathComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.VelocityComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.MapComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.PlayerComponent;

@All({VelocityComponent.class, PositionComponent.class, MobComponent.class, PathComponent.class})
@Exclude({ExpireComponent.class})
public class PathingSystem extends IteratingSystem {

    private PlayerComponent player;

    private ComponentMapper<PositionComponent> mPosition;
    private ComponentMapper<VelocityComponent> mVelocity;
    private ComponentMapper<PathComponent> mPath;
    private ComponentMapper<MobComponent> mMob;
    private ComponentMapper<ExpireComponent> mExpire;

    private MapComponent waypoints;

    @Override
    protected void process(int entityId) {
        final PositionComponent position = mPosition.get(entityId);
        final VelocityComponent velocity = mVelocity.get(entityId);
        final PathComponent path = mPath.get(entityId);
        final MobComponent mob = mMob.get(entityId);

        // if velocity is zero, set velocity to the next checkpoint TODO: fix this, maybe in wave system?
        if (velocity.get().len() == 0) {
            Vector2 nextCheckpoint = waypoints.path.get(path.currentCheckpoint);
            Vector2 direction = nextCheckpoint.cpy().sub(position.get());
            velocity.get().set(direction.setLength(mob.speed));
        }

        // if close enough to the next checkpoint that it would be overshot on next update, move to
        // next checkpoint.
        if (position.get().dst(waypoints.path.get(path.currentCheckpoint)) > velocity.get().len() * world.delta
                && velocity.get().len() >= 0.0f) {
            return;
        }

        path.currentCheckpoint++;

        // If no more checkpoints, expire and subtract life from player
        if (path.currentCheckpoint >= waypoints.path.size - 1) {
            ExpireComponent expire = mExpire.create(entityId);
            expire.timeLeft = 0;
            player.lives--;
        }
        Vector2 nextCheckpoint = waypoints.path.get(path.currentCheckpoint).cpy();
        Vector2 direction = nextCheckpoint.sub(position.get());

        velocity.get().set(direction).setLength(mob.speed);

    }
}
