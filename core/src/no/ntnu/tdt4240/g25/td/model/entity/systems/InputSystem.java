package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.Aspect;
import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TowerComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.BuildMenu;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.MapComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.UpgradeMenu;
import no.ntnu.tdt4240.g25.td.model.map.MapTile;
import no.ntnu.tdt4240.g25.td.model.map.MapTileType;

public class InputSystem extends BaseSystem {

    Vector3 lastClick;
    MapComponent map;
    BuildMenu build;
    UpgradeMenu upgrade;

    MyCameraSystem cameraSystem;
    ComponentMapper<TowerComponent> mTower;
    ComponentMapper<PositionComponent> mPosition;
    EntitySubscription towerSubscription;

    @Override
    protected void initialize() {
        towerSubscription = world.getAspectSubscriptionManager().get(Aspect.all(TowerComponent.class, PositionComponent.class));
    }

    public InputSystem() {
    }

    @Override
    protected void processSystem() {
        if (!build.pending && lastClick != null) {
            handleClickOnWorld(lastClick);
        }
        else if (build.pending && lastClick != null) {
            handleClickOnMenu(lastClick);
        }
    }

    public void setLastClick(Vector3 lastClick) {
        this.lastClick = lastClick;
    }

    private void handleClickOnWorld(Vector3 click) {
        IntBag towerIds = towerSubscription.getEntities();
        cameraSystem.viewport.unproject(lastClick);
        int tileX = MathUtils.floor(lastClick.x);
        int tileY = MathUtils.floor(lastClick.y);
        for (int i = 0; i < towerIds.size(); i++) {
            int towerId = towerIds.get(i);
            TowerComponent tower = mTower.get(towerId);
            PositionComponent position = mPosition.get(towerId);
            // Check if the click is inside the tower
            if (tileX + 0.5f == position.position.x && tileY + 0.5f == position.position.y) {
                Gdx.app.log("InputSystem", "Clicked on tower");
                upgrade.pending = true;
                upgrade.tower = towerId;
                lastClick = null;
                return;
            }
        }
        for (int i = 0; i < map.mapGrid.size ; i++) {
            MapTile tile = map.mapGrid.get(i);
            if (tile.getX() == tileX && tile.getY() == tileY) {
                if (tile.getTile() != MapTileType.PLAIN) continue;
                Gdx.app.log("InputSystem", "Clicked on open tile");
                build.x = tileX;
                build.y = tileY;
                build.pending = true;
                lastClick = null;
                return;
            }
        }
    }

    private void handleClickOnMenu(Vector3 click) {
        cameraSystem.guiViewport.unproject(lastClick);
    }


}
