package no.ntnu.tdt4240.g25.td.model;

import com.artemis.World;

import no.ntnu.tdt4240.g25.td.model.entity.factories.TowerFactory;
import no.ntnu.tdt4240.g25.td.service.AssetService;

public class GameWorld {
    TowerFactory towerFactory;
    World world;


    public GameWorld(World world, AssetService assetManager) {
        this.world = world;
        towerFactory = new TowerFactory(assetManager);
        towerFactory = new TowerFactory(assetManager);
        towerFactory.createSingleTower(world, 200, 200);
        towerFactory.createAoETower(world, 400, 400);
    }

    public void update(float delta) {
        world.setDelta(delta);
        world.process();
    }
}
