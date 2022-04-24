package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.Aspect;
import com.artemis.BaseSystem;
import com.artemis.EntitySubscription;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.math.MathUtils;

import no.ntnu.tdt4240.g25.td.model.MobType;
import no.ntnu.tdt4240.g25.td.model.entity.components.ExpireComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.MobComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.WaveComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.WaypointsComponent;
import no.ntnu.tdt4240.g25.td.model.entity.factories.MobFactory;

public class WaveSystem extends BaseSystem {

    private WaveComponent wc;
    private WaypointsComponent wpc;
    private EntitySubscription aliveMobs;
    private MobFactory mobFactory;

    @Override
    protected void initialize() {

        aliveMobs = world.getAspectSubscriptionManager().get(
                Aspect.all(MobComponent.class).exclude(ExpireComponent.class));
    }

    @Override
    protected void processSystem() {
        IntBag ids = aliveMobs.getEntities();
        wc.enemiesAlive = ids.size() > 0;

        // Wave phase
        if (wc.active) {
            wc.time += world.delta;
            wc.normalEnemyCooldown -= world.delta;
            wc.tankEnemyCooldown -= world.delta;

            if (wc.remainingNormalEnemies <= 0 && wc.remainingTankEnemies <= 0 && !wc.enemiesAlive) {
                wc.active = false;
                wc.time = 0;
                wc.numberOfWaves++;
            }

            else if (wc.remainingNormalEnemies > 0 && wc.normalEnemyCooldown <= 0) {
                if (wc.normalsSpawned < WaveComponent.NUM_NORMAL_PER_CLUSTER) {
                    createEnemy(MobType.NORMAL);
                    wc.normalsSpawned++;

                }
                createEnemy(MobType.NORMAL);
                wc.normalEnemyCooldown = WaveComponent.NORMAL_ENEMY_INTERVAL;
            }
            if (wc.remainingTankEnemies > 0 && wc.tankEnemyCooldown <= 0) {

                createEnemy(MobType.TANK);
                wc.tankEnemyCooldown = WaveComponent.TANK_ENEMY_INTERVAL;
            }

            // Pause phase
        } else {
            wc.time += world.delta;
            if (wc.time >= WaveComponent.PAUSE_DURATION) {
                wc.remainingNormalEnemies = MathUtils.round(
                        WaveComponent.baseNormalEnemies + (wc.numberOfWaves * WaveComponent.waveMultiplier)
                );
                wc.remainingTankEnemies = MathUtils.round(
                        WaveComponent.baseTankEnemies + (wc.numberOfWaves * WaveComponent.waveMultiplier)
                );
                wc.time = 0;
                wc.active = true;
            }
        }
    }

    private void createEnemy(MobType type) {
        mobFactory.create(wpc.path.get(0).x, wpc.path.get(0).y, type);
        wc.enemiesAlive = true;
        switch (type) {
            case NORMAL:
                wc.remainingNormalEnemies--;
                break;
            case TANK:
                wc.remainingTankEnemies--;
                break;
        }
    }
}
