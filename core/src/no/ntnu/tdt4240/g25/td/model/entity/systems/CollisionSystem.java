package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;
import com.artemis.utils.IntBag;

import no.ntnu.tdt4240.g25.td.model.entity.components.BoundsComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.DamageComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.MobComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.ProjectileComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TextureComponent;

@All({ProjectileComponent.class, PositionComponent.class})
public class CollisionSystem extends IteratingSystem {

    public static int POSITION_CHECK_THRESHOLD = 250;

    ComponentMapper<ProjectileComponent> mProjectile;
    ComponentMapper<PositionComponent> mPosition;
    ComponentMapper<BoundsComponent> mBounds;
    ComponentMapper<DamageComponent> mDamage;
    EntitySubscription mobSubscription;

    @Override
    protected void initialize() {
        super.initialize();
        mobSubscription =
                world.getAspectSubscriptionManager().get(Aspect.all(
                        MobComponent.class, PositionComponent.class, BoundsComponent.class // Using texture compo for now
                ));
    }

    @Override
    protected void process(int entityId) {
        ProjectileComponent projectile = mProjectile.get(entityId);
        PositionComponent position = mPosition.get(entityId);
        IntBag mobIds = mobSubscription.getEntities();

        for (int i = 0; i < mobIds.size(); i++) {
            int mob = mobIds.get(i);
            PositionComponent mobPosition = mPosition.get(mob);
            if (position.get().dst(mobPosition.get()) > POSITION_CHECK_THRESHOLD) continue;
            BoundsComponent mobBounds = mBounds.get(mob);
            if (mobBounds != null && overlaps(position, mobBounds)) {
                DamageComponent damage = mDamage.create(mob);
                damage.damage = projectile.damage;

                // If the projectile has splash damage, apply it to all mobs in the area
                if (projectile.radius > 0) {
                    for (int j = 0; j < mobIds.size(); j++) {
                        int nearbyMob = mobIds.get(j);
                        if (nearbyMob == mob) continue;
                        PositionComponent nearbyMobPosition = mPosition.get(nearbyMob);
                        if (nearbyMobPosition.get().dst(mobPosition.get()) > POSITION_CHECK_THRESHOLD) continue;
                        if (nearbyMobPosition.get().dst(position.get()) > projectile.radius) continue;
                        BoundsComponent nearbyMobBounds = mBounds.get(nearbyMob);
                        if (nearbyMobBounds != null && overlaps(nearbyMobPosition, nearbyMobBounds)) {
                            applyDamage(nearbyMob, projectile.damage);
                        }
                    }
                }
                world.getEntity(entityId).deleteFromWorld();
            }
        }
    }

    private void applyDamage(int mob, float damage) {
        DamageComponent mobDamage = mDamage.create(mob);
        mobDamage.damage += damage;
    }

    private boolean overlaps(PositionComponent projectile, BoundsComponent mobBounds) {
        return mobBounds.get().contains(projectile.get());
    }
}
