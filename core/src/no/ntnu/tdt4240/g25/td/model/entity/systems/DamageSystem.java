package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.audio.Sound;

import no.ntnu.tdt4240.g25.td.TdConfig;
import no.ntnu.tdt4240.g25.td.model.entity.components.DamageComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.ExpireComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.MobComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PathComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.StateComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.VelocityComponent;
import no.ntnu.tdt4240.g25.td.service.AssetService;
import no.ntnu.tdt4240.g25.td.service.SoundFx;

@All({DamageComponent.class, MobComponent.class, StateComponent.class})
public class DamageSystem extends IteratingSystem {

    ComponentMapper<DamageComponent> mDamage;
    ComponentMapper<MobComponent> mMob;
    ComponentMapper<StateComponent> mState;
    ComponentMapper<ExpireComponent> mExpire;
    ComponentMapper<PathComponent> mPath;
    ComponentMapper<VelocityComponent> mVelocity;

    @Wire
    private AssetService assets;
    private Sound explosion;

    @Override
    protected void initialize() {
        explosion = assets.assetManager.get(SoundFx.EXPLODE.path, Sound.class);
    }

    @Override
    protected void process(int entityId) {
        DamageComponent damage = mDamage.get(entityId);
        MobComponent mob = mMob.get(entityId);
        StateComponent state = mState.get(entityId);
        mob.health -= damage.damage;
        if(mob.health <= 0) {
            long id = explosion.play(TdConfig.get().getVolume());
            explosion.setLooping(id,false);
            mPath.remove(entityId);
            mVelocity.remove(entityId);
            ExpireComponent expire = mExpire.create(entityId);
            expire.timeLeft = 1;
            state.set(StateComponent.STATE_DYING, false);
        }
        mDamage.remove(entityId);
    }
}
