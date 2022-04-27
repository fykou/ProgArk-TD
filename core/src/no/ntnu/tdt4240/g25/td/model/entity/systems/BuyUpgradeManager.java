package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.Aspect;
import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.artemis.utils.IntBag;

import no.ntnu.tdt4240.g25.td.model.TowerLevel;
import no.ntnu.tdt4240.g25.td.model.TowerType;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TowerComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.BuyUpgradeComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.MapComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.PlayerComponent;
import no.ntnu.tdt4240.g25.td.model.entity.factories.TowerFactory;

public class BuyUpgradeManager extends BaseSystem {

    private BuyUpgradeComponent buyUpgrade;
    private TowerFactory towerFactory;
    private MapComponent map;
    private PlayerComponent player;

    private ComponentMapper<PositionComponent> mPosition;
    private ComponentMapper<TowerComponent> mTower;


    private EntitySubscription towerSubscription;

    @Override
    public void initialize() {
        towerSubscription = world.getAspectSubscriptionManager().get(
                Aspect.all(PositionComponent.class, TowerComponent.class));
    }

    public void buyTower1() {
        buyUpgrade.boughtTower1 = true;
    }

    public void buyTower2() {
        buyUpgrade.boughtTower2 = true;
    }

    public void upgradeTower() {
        buyUpgrade.upgradedTower = true;
    }

    @Override
    protected void processSystem() {
        if (buyUpgrade.boughtTower1) {
            towerFactory.create(map.selectedTile.x, map.selectedTile.y, TowerType.TYPE_1, TowerLevel.MK1);
            buyUpgrade.boughtTower1 = false;
            player.cash -= TowerType.TYPE_1.baseCost;
        }
        if (buyUpgrade.boughtTower2) {
            towerFactory.create(map.selectedTile.x, map.selectedTile.y, TowerType.TYPE_2, TowerLevel.MK1);
            buyUpgrade.boughtTower2 = false;
            player.cash -= TowerType.TYPE_2.baseCost;
        }
        if (buyUpgrade.upgradedTower) {
            IntBag towerIds = towerSubscription.getEntities();
            for (int i = 0; i < towerIds.size(); i++) {
                int towerId = towerIds.get(i);
                PositionComponent position = mPosition.get(towerId);
                if ((int) position.get().x == map.selectedTile.x && (int) position.get().y == map.selectedTile.y) {
                    TowerComponent tower = mTower.get(towerId);
                    player.cash -= tower.type.baseCost * tower.level.level;
                    towerFactory.upgradeTower(towerId);
                }
            }
            buyUpgrade.upgradedTower = false;
        }
    }
}
