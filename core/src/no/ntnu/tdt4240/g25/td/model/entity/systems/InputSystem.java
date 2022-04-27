package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.Aspect;
import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.artemis.annotations.Wire;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;

import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TowerComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.MapComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.PlayerComponent;
import no.ntnu.tdt4240.g25.td.model.entity.factories.TowerFactory;
import no.ntnu.tdt4240.g25.td.model.map.MapTileType;
import no.ntnu.tdt4240.g25.td.view.GameView;

public class InputSystem extends BaseSystem implements InputProcessor {

    private Vector3 lastClick;
    private MapComponent map;
    private PlayerComponent player;

    private MyCameraSystem cameraSystem;
    private ComponentMapper<TowerComponent> mTower;
    private ComponentMapper<PositionComponent> mPosition;
    private EntitySubscription towerSubscription;
    private TowerFactory towerFactory;

    @Wire
    private GameView gameView;

    @Override
    protected void initialize() {
        towerSubscription = world.getAspectSubscriptionManager().get(Aspect.all(TowerComponent.class, PositionComponent.class));
    }



    @Override
    protected void processSystem() {
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Vector3 click = cameraSystem.viewport.unproject(new Vector3(screenX, screenY, 0));
        int tileX = (int) click.x;
        int tileY = (int) click.y;
        // check if the tile is empty/can be built on
        MapTileType tileType = map.getTileTypeByCoordinate(0, tileX, tileY);
        if (tileType == MapTileType.PLAIN) {
            // check if the spot already has a tower
            IntBag towerIds = towerSubscription.getEntities();
            for (int i = 0; i < towerIds.size(); i++) {
                int towerId = towerIds.get(i);
                PositionComponent towerPos = mPosition.get(towerId);
                if ((int)towerPos.get().x == tileX && (int)towerPos.get().y == tileY) {
                    Gdx.app.log("InputSystem", "Tower already exists");
                    // upgrade trigger
                }
                else {
                    // buy trigger
                }
            }
        }
        gameView.triggerBuyDialogue(tileX, tileY, player.cash);
        return true;
    }

    public boolean keyDown(int keycode) {
        return false;
    }
    public boolean keyUp(int keycode) {
        return false;
    }
    public boolean keyTyped(char character) {
        return false;
    }
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
