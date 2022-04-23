package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;

import no.ntnu.tdt4240.g25.td.model.entity.components.DamageComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.ExpireComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.MobComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.StateComponent;

@All({DamageComponent.class, MobComponent.class, StateComponent.class})
public class DamageSystem extends IteratingSystem {

    ComponentMapper<DamageComponent> mDamage;
    ComponentMapper<MobComponent> mMob;
    ComponentMapper<StateComponent> mState;
    ComponentMapper<ExpireComponent> mExpire;

    @Override
    protected void process(int entityId) {
        DamageComponent damage = mDamage.get(entityId);
        MobComponent mob = mMob.get(entityId);
        StateComponent state = mState.get(entityId);
        mob.health -= damage.damage;
        if(mob.health <= 0) {
            ExpireComponent expire = mExpire.create(entityId);
            expire.timeLeft = 2;
            state.set(StateComponent.STATE_DYING, false);
        }
        mDamage.remove(entityId);
    }
}
