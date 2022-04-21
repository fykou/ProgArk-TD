package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.artemis.annotations.All;
import com.artemis.annotations.Exclude;
import com.artemis.systems.IntervalIteratingSystem;
import com.artemis.utils.IntBag;

import no.ntnu.tdt4240.g25.td.model.entity.components.HasTargetComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.MobComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TowerComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;

@All(TowerComponent.class)
@Exclude(HasTargetComponent.class)
public class FindTargetSystem extends IntervalIteratingSystem {

    ComponentMapper<TowerComponent> mTower;
    ComponentMapper<PositionComponent> mPosition;
    ComponentMapper<HasTargetComponent> mTarget;
    EntitySubscription enemySubscription;

    /**
     * Creates a new IntervalEntityProcessingSystem.
     *
     * @param interval the interval in seconds between processing of entities.
     */
    public FindTargetSystem(float interval) {
        super(Aspect.all(TowerComponent.class).exclude(HasTargetComponent.class), interval);
    }


    @Override
    public void initialize() {
        super.initialize();
        enemySubscription = world.getAspectSubscriptionManager()
                .get(Aspect.all(MobComponent.class, PositionComponent.class));
    }


    @Override
    protected void process(int entityId) {
        TowerComponent tower = mTower.get(entityId);
        PositionComponent transform = mPosition.get(entityId);
        IntBag enemies = enemySubscription.getEntities();

        // Prefer to target the closest enemy, start by setting the target to null
        // and then iterate through all enemies and find the closest one
        float closest = Float.MAX_VALUE;
        int closestEnemy = -1;
        for (int i = 0; i < enemies.size(); i++) {
            int enemy = enemies.get(i);
            PositionComponent enemyTransform = mPosition.get(enemy);
            float distance = transform.get().dst(enemyTransform.get());
            if (distance > tower.range) continue;
            if (distance < closest) {
                closest = distance;
                closestEnemy = enemy;
            }
        }
        // if we found an enemy, set the target
        if (closest != Float.MAX_VALUE) {
            HasTargetComponent target = mTarget.create(entityId);
            target.targetId = closestEnemy;

        }
    }
}
