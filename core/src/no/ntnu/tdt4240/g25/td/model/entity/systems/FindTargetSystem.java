package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.artemis.annotations.All;
import com.artemis.annotations.Exclude;
import com.artemis.systems.IteratingSystem;
import com.artemis.utils.IntBag;

import no.ntnu.tdt4240.g25.td.model.entity.components.HasTargetComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.MobComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TowerComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;

@All(TowerComponent.class)
@Exclude(HasTargetComponent.class)
public class FindTargetSystem extends IteratingSystem {

    ComponentMapper<TowerComponent> towerMapper;
    ComponentMapper<PositionComponent> transformMapper;

    EntitySubscription subscription = world.getAspectSubscriptionManager()
            .get(Aspect.all(MobComponent.class, PositionComponent.class));
    IntBag enemies = subscription.getEntities();

    @Override
    protected void process(int entityId) {
        TowerComponent tower = towerMapper.get(entityId);
        PositionComponent transform = transformMapper.get(entityId);

        for (int i = 0; i < enemies.size(); i++) {
            int enemy = enemies.get(i);
            PositionComponent enemyTransform = transformMapper.get(enemy);

        }
    }
}
