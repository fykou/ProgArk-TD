package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;

import no.ntnu.tdt4240.g25.td.model.entity.components.MobComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.ProjectileComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TextureComponent;

@All({ProjectileComponent.class, TextureComponent.class, PositionComponent.class})
public class CollisionSystem extends IteratingSystem {

    ComponentMapper<ProjectileComponent> mProjectile;
    ComponentMapper<TextureComponent> mTexture;
    ComponentMapper<PositionComponent> mPosition;
    ComponentMapper<MobComponent> mMob;
    EntitySubscription mobSubscription;

    @Override
    protected void initialize() {
        mobSubscription =
                world.getAspectSubscriptionManager().get(Aspect.all(
                        MobComponent.class, PositionComponent.class, TextureComponent.class // Using texture compo for now
                ));
    }

    @Override
    protected void process(int entityId) {

    }
}
