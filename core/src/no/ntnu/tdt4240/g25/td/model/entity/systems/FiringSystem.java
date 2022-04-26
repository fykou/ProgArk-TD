package no.ntnu.tdt4240.g25.td.model.entity.systems;

import static no.ntnu.tdt4240.g25.td.model.TowerType.TYPE_1;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;

import no.ntnu.tdt4240.g25.td.TdConfig;
import no.ntnu.tdt4240.g25.td.model.entity.components.HasTargetComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.StateComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TowerComponent;
import no.ntnu.tdt4240.g25.td.model.entity.factories.ProjectileFactory;
import no.ntnu.tdt4240.g25.td.asset.Assets;
import no.ntnu.tdt4240.g25.td.asset.SoundFx;

@All({TowerComponent.class, StateComponent.class, HasTargetComponent.class, PositionComponent.class})
public class FiringSystem extends IteratingSystem {

    public static int PROJECTILE_SPEED = 7;

    @Wire
    ProjectileFactory projectileFactory;
    ComponentMapper<TowerComponent> mTower;
    ComponentMapper<StateComponent> mState;
    ComponentMapper<HasTargetComponent> mTarget;
    ComponentMapper<PositionComponent> mPosition;

    private Sound type1;
    private Sound type2;

    @Override
    protected void initialize() {
        type1 = Assets.getInstance().getSound(SoundFx.FIRE_TYPE_1);
        type2 = Assets.getInstance().getSound(SoundFx.FIRE_TYPE_2);
    }

    @Override
    protected void process(int entityId) {
        HasTargetComponent target = mTarget.get(entityId);
        TowerComponent tower = mTower.get(entityId);

        tower.cooldown -= world.getDelta();
        // target is dead, but hasn't been removed by the EntityManager yet
        // exit here if the tower cannot fire, or if it is on cooldown
        if (target.targetId == -1 || tower.cooldown > 0 || !target.canShoot) return;

        if(tower.type == TYPE_1){
            long id = type1.play(TdConfig.get().getVolume());
            type1.setLooping(id,false);
        }else{
            long id = type2.play(TdConfig.get().getVolume());
            type2.setLooping(id,false);
        }
        StateComponent state = mState.get(entityId);
        PositionComponent position = mPosition.get(entityId);
        // create the projectile and set the position to the tower position, velocity towards the target
        // and offset the position by the towers height for it to spawn roughly at the tip of the turret
        Vector2 startPosition = position.position.cpy();
        Vector2 enemyPosition = mPosition.get(target.targetId).position;
        Vector2 velocity = enemyPosition.cpy().sub(startPosition).setLength(PROJECTILE_SPEED);
        Vector2 offset = velocity.cpy().setLength(0.5f);
        startPosition.add(offset);
        projectileFactory.create(startPosition.x, startPosition.y, velocity.x, velocity.y, tower.damage, tower.splashRadius);
        state.set(StateComponent.STATE_ATTACKING, false);
        tower.cooldown = 1/tower.fireRate;
    }
}
