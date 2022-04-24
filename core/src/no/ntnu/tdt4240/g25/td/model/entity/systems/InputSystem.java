package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.Aspect;
import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.math.Vector3;

import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TowerComponent;

public class InputSystem extends BaseSystem {

    Vector3 lastClick;
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

        IntBag towerIds = towerSubscription.getEntities();


        if (lastClick != null) {

            cameraSystem.viewport.unproject(lastClick);

            for (int i = 0; i < towerIds.size(); i++) {
                int towerId = towerIds.get(i);
                TowerComponent tower = mTower.get(towerId);
                PositionComponent position = mPosition.get(towerId);

                // Check if the click is inside the tower
                if ((int) lastClick.x + 0.5f == position.position.x && (int) lastClick.y + 0.5f == position.position.y) {
                    System.out.println("Tower id: " + towerId);
                }
            }

            lastClick = null;

        }
    }

    public void setLastClick(Vector3 lastClick) {
        this.lastClick = lastClick;
    }


}
