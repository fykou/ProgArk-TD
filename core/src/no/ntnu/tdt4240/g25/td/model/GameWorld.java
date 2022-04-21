package no.ntnu.tdt4240.g25.td.model;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.link.EntityLinkManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import no.ntnu.tdt4240.g25.td.model.entity.factories.MobFactory;
import no.ntnu.tdt4240.g25.td.model.entity.factories.ProjectileFactory;
import no.ntnu.tdt4240.g25.td.model.entity.factories.TowerFactory;
import no.ntnu.tdt4240.g25.td.model.entity.systems.AnimationSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.AimingSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.BoundsSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.CollisionSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.DamageSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.ExpireSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.FindTargetSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.FiringSystem;
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

        towerFactory.create(150, 150, TowerType.TYPE_1, TowerLevel.MK1);
        towerFactory.create(150, 250, TowerType.TYPE_1, TowerLevel.MK2);
        towerFactory.create(150, 350, TowerType.TYPE_1, TowerLevel.MK3);
        towerFactory.create(150, 450, TowerType.TYPE_1, TowerLevel.MK4);

        towerFactory.create(350, 150, TowerType.TYPE_2, TowerLevel.MK1);
        towerFactory.create(350, 250, TowerType.TYPE_2, TowerLevel.MK2);
        towerFactory.create(350, 350, TowerType.TYPE_2, TowerLevel.MK3);
        towerFactory.create(350, 450, TowerType.TYPE_2, TowerLevel.MK4);

        mobFactory.create(250, 100, MobType.TANK);
    }

    protected void createWorld(SpriteBatch batch) {
        WorldConfiguration config = new WorldConfigurationBuilder()
                .dependsOn(EntityLinkManager.class)
                .with(new MovementSystem())
                .with(new BoundsSystem())
                .with(new CollisionSystem())
                .with(new FindTargetSystem(1f/60))
                .with(new AimingSystem())
                .with(new FiringSystem())
                .with(new DamageSystem())
                .with(new ExpireSystem())
                .with(new AnimationSystem())
                .with(new RenderSystem(batch))
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
