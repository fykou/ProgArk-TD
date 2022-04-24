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

    final static int baseNormalEnemies = 3;
    final static int baseTankEnemies = 1;
    final static float waveMultiplier = 1.5f;

    final static float WAVE_DURATION = 30f;
    final static float PAUSE_DURATION = 10f;
    final static float NORMAL_ENEMY_INTERVAL = 1f;
    final static float TANK_ENEMY_INTERVAL = 3f;

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
            // Wave is over, but if there are still enemies alive, wait for them to die
            // in case there are still remaining enemies for current wave, spawn them.
            if (wc.time >= WAVE_DURATION) {
                if (wc.remainingNormalEnemies > 0) {
                    createEnemy(MobType.NORMAL);
                } else if (wc.remainingTankEnemies > 0) {
                    createEnemy(MobType.TANK);
                } else {
                    if (!wc.enemiesAlive) {
                        wc.active = false;
                        wc.time = 0;
                        wc.numberOfWaves++;
                        // initalize new wave
                    }
                }
            }

            if (wc.time % NORMAL_ENEMY_INTERVAL <= 1 && wc.remainingNormalEnemies > 0) {
                createEnemy(MobType.NORMAL);
            }
            if (wc.time % TANK_ENEMY_INTERVAL <= 1 && wc.remainingTankEnemies > 0) {
                createEnemy(MobType.TANK);
            }

            // Pause phase
        } else {
            wc.time += world.delta;
            if (wc.time >= PAUSE_DURATION) {
                initializeWave();
            }
        }
    }

    private void initializeWave() {
        wc.remainingNormalEnemies = MathUtils.round(baseNormalEnemies + (wc.numberOfWaves * waveMultiplier));
        wc.remainingTankEnemies = MathUtils.round(baseTankEnemies + (wc.numberOfWaves * waveMultiplier));
        wc.time = 0;
        wc.active = true;
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
