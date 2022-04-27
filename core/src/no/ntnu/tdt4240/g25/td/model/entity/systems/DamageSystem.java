package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.annotations.Exclude;
import com.artemis.systems.IteratingSystem;

import no.ntnu.tdt4240.g25.td.asset.Audio;
import no.ntnu.tdt4240.g25.td.asset.SoundFx;
import no.ntnu.tdt4240.g25.td.model.entity.components.DamageComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.ExpireComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.MobComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PathComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.StateComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.VelocityComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.PlayerComponent;

@All({DamageComponent.class, MobComponent.class, StateComponent.class})
@Exclude({ExpireComponent.class})
public class DamageSystem extends IteratingSystem {

    private PlayerComponent player;

    private ComponentMapper<DamageComponent> mDamage;
    private ComponentMapper<MobComponent> mMob;
    private ComponentMapper<StateComponent> mState;
    private ComponentMapper<ExpireComponent> mExpire;
    private ComponentMapper<PathComponent> mPath;
    private ComponentMapper<VelocityComponent> mVelocity;

    @Override
    protected void process(int entityId) {
        DamageComponent damage = mDamage.get(entityId);
        MobComponent mob = mMob.get(entityId);
        StateComponent state = mState.get(entityId);
        mob.health -= damage.damage;
        if(mob.health <= 0) {
            player.enemyKilledReward();
            Audio.playFx(SoundFx.EXPLODE);
            mPath.remove(entityId);
            mVelocity.remove(entityId);
            ExpireComponent expire = mExpire.create(entityId);
            expire.timeLeft = 1;
            state.set(StateComponent.STATE_DYING, false);
        }
        mDamage.remove(entityId);
    }
}
