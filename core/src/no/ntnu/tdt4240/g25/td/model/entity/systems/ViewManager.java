package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.Aspect;
import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.math.Vector3;

import no.ntnu.tdt4240.g25.td.model.entity.components.ExpireComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TowerComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.UpgradeComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.MapComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.PlayerComponent;
import no.ntnu.tdt4240.g25.td.model.entity.factories.TowerFactory;
import no.ntnu.tdt4240.g25.td.model.map.MapTile;
import no.ntnu.tdt4240.g25.td.model.map.MapTileType;
import no.ntnu.tdt4240.g25.td.view.GameView;

public class ViewManager extends BaseSystem {

    private MapComponent map;
    private PlayerComponent player;

    private MyCameraSystem cameraSystem;
    private ComponentMapper<TowerComponent> mTower;
    private ComponentMapper<PositionComponent> mPosition;
    private ComponentMapper<UpgradeComponent> mUpgrade;
    private EntitySubscription towerSubscription;

    private TowerFactory towerFactory;

    @Wire
    private GameView view;

    @Override
    protected void initialize() {
        towerSubscription = world.getAspectSubscriptionManager().get(Aspect.all(TowerComponent.class, PositionComponent.class));
    }

    /**
     * Process the system.
     */
    @Override
    protected void processSystem() {

    }

}
