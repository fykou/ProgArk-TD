package no.ntnu.tdt4240.g25.td.model;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import no.ntnu.tdt4240.g25.td.model.entity.factories.MobFactory;
import no.ntnu.tdt4240.g25.td.model.entity.factories.ProjectileFactory;
import no.ntnu.tdt4240.g25.td.model.entity.factories.TowerFactory;
import no.ntnu.tdt4240.g25.td.model.entity.systems.AnimationSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.AimingSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.FindTargetSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.MovementSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.RenderSystem;
import no.ntnu.tdt4240.g25.td.service.AssetService;

public class GameWorld {
    TowerFactory towerFactory;
    MobFactory mobFactory;
    ProjectileFactory projectileFactory;
    World world;


    public GameWorld(AssetService assetManager, SpriteBatch batch) {
        createFactories(assetManager);
        createWorld(batch);

        towerFactory.createTower(150, 150, TowerType.TYPE_1, TowerLevel.MK1);
        towerFactory.createTower(150, 250, TowerType.TYPE_1, TowerLevel.MK2);
        towerFactory.createTower(150, 350, TowerType.TYPE_1, TowerLevel.MK3);
        towerFactory.createTower(150, 450, TowerType.TYPE_1, TowerLevel.MK4);

        towerFactory.createTower(350, 150, TowerType.TYPE_2, TowerLevel.MK1);
        towerFactory.createTower(350, 250, TowerType.TYPE_2, TowerLevel.MK2);
        towerFactory.createTower(350, 350, TowerType.TYPE_2, TowerLevel.MK3);
        towerFactory.createTower(350, 450, TowerType.TYPE_2, TowerLevel.MK4);

        mobFactory.createMob(250, 100, MobType.NORMAL);

        projectileFactory.createProjectile(50,50,150,500,50,200);
        projectileFactory.createProjectile(250,0,0, 500,50,0);
    }

    protected void createWorld(SpriteBatch batch) {
        WorldConfiguration config = new WorldConfigurationBuilder()
                .with(new MovementSystem(1f/60))
                .with(new RenderSystem(batch))
                .with(new AnimationSystem())
                .with(new FindTargetSystem(1f/60))
                .with(new AimingSystem())
                .build()
                // now register the factories to be injected into the systems
                .register(towerFactory)
                .register(mobFactory)
                .register(projectileFactory);

        this.world = new World(config);

        // set world for the factories to be able to create entities
        towerFactory.setWorld(world);
        mobFactory.setWorld(world);
        projectileFactory.setWorld(world);
    }

    protected void createFactories(AssetService assetManager) {
        towerFactory = new TowerFactory(assetManager);
        mobFactory = new MobFactory(assetManager);
        projectileFactory = new ProjectileFactory(assetManager);
    }

    public void update(float delta) {
        world.setDelta(delta);
        world.process();
    }
}
