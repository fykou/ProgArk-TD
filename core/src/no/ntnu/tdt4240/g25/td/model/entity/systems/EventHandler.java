package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.Aspect;
import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.artemis.annotations.Wire;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import no.ntnu.tdt4240.g25.td.controller.GameController;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TowerComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.BuyUpgradeComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.MapComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.PlayerComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.WaveComponent;
import no.ntnu.tdt4240.g25.td.model.entity.factories.TowerFactory;
import no.ntnu.tdt4240.g25.td.model.map.MapTileType;
import no.ntnu.tdt4240.g25.td.view.GameView;

public class EventHandler extends BaseSystem {

    private Vector3 lastClick;
    private MapComponent map;
    private PlayerComponent player;
    private WaveComponent wave;
    private BuyUpgradeComponent buyUpgrade;

    private MyCameraSystem cameraSystem;
    private ComponentMapper<TowerComponent> mTower;
    private ComponentMapper<PositionComponent> mPosition;
    private EntitySubscription towerSubscription;
    private TowerFactory towerFactory;

    @Wire
    private GameView.GameViewCallback view;
    @Wire
    private GameController.GameWorldCallback controller;

    @Override
    protected void initialize() {
        towerSubscription = world.getAspectSubscriptionManager().get(Aspect.all(TowerComponent.class, PositionComponent.class));
    }


    @Override
    protected void processSystem() {
        view.setCash(player.cash);
        view.setLives(player.lives);
        view.setWave(wave.numberOfWaves);
        view.setWaveTime(wave.active ? wave.time : WaveComponent.PAUSE_DURATION - wave.time, wave.active);
        if (player.lives <= 0) {
            controller.onGameOver();
        }
    }

    public void receiveClick(int screenX, int screenY) {
        Vector3 click = cameraSystem.viewport.unproject(new Vector3(screenX, screenY, 0));
        int tileX = (int) click.x;
        int tileY = (int) click.y;
        if (tileY >= 15) return; // Don't allow clicking on the top bar
        System.out.println("Clicked on tile: " + tileX + ", " + tileY);
        // check if the tile is empty/can be built on
        MapTileType tileType = map.getTileTypeByCoordinate(0, tileX, tileY);
        System.out.println("Tile type: " + tileType);
        if (tileType == MapTileType.PLAIN) {
            // check if the spot already has a tower
            IntBag towerIds = towerSubscription.getEntities();
            for (int i = 0; i < towerIds.size(); i++) {
                int towerId = towerIds.get(i);
                PositionComponent towerPos = mPosition.get(towerId);
                if ((int) towerPos.get().x == tileX && (int) towerPos.get().y == tileY) {
                    TowerComponent tower = mTower.get(towerId);
                    Gdx.app.log("InputSystem", "Tower already exists");
                    map.selectedTile.set(new Vector2(tileX, tileY));
                    view.toggleUpgradeWindow(player.cash, tower.type, tower.level);
                    return;
                }
            }
            // buy trigger
            map.selectedTile.set(new Vector2(tileX, tileY));
            Gdx.app.log("InputSystem", "hit empty tile");
            view.toggleNewTowerWindow(player.cash);
        }
    }
}
