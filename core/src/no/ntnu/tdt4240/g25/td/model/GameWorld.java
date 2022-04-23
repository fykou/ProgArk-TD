package no.ntnu.tdt4240.g25.td.model;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.link.EntityLinkManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import no.ntnu.tdt4240.g25.td.model.entity.factories.MobFactory;
import no.ntnu.tdt4240.g25.td.model.entity.factories.ProjectileFactory;
import no.ntnu.tdt4240.g25.td.model.entity.factories.TowerFactory;
import no.ntnu.tdt4240.g25.td.model.entity.systems.AimingSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.render.AnimationSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.BoundsSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.CollisionSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.DamageSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.ExpireSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.FindTargetSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.FiringSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.render.MapRenderSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.MovementSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.MyCameraSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.render.RenderSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.debug.DebugRenderSystem;
import no.ntnu.tdt4240.g25.td.model.map.MapGrid;
import no.ntnu.tdt4240.g25.td.service.AssetService;

public class GameWorld {

    public static int GAME_WIDTH = 9;
    public static int GAME_HEIGHT = 16;

    TowerFactory towerFactory;
    MobFactory mobFactory;
    ProjectileFactory projectileFactory;
    World world;


    public GameWorld(AssetService assetManager, SpriteBatch batch) {
        createFactories(assetManager);
        createWorld(batch, assetManager);

        towerFactory.create(1, 1, TowerType.TYPE_1, TowerLevel.MK4);
        towerFactory.create(3, 1, TowerType.TYPE_2, TowerLevel.MK4);
        towerFactory.create(4, 4, TowerType.TYPE_2, TowerLevel.MK4);
        towerFactory.create(8, 7, TowerType.TYPE_1, TowerLevel.MK4);

        mobFactory.create(8, 8, MobType.TANK);
    }

    protected void createWorld(SpriteBatch batch, AssetService assetManager) {

        WorldConfiguration config = new WorldConfigurationBuilder()
                .dependsOn(EntityLinkManager.class)
                .with(
                        new MyCameraSystem(GAME_WIDTH, GAME_HEIGHT),
                        new MovementSystem(),
                        new BoundsSystem(),
                        new CollisionSystem(),
                        new FindTargetSystem(1f / 60),
                        new AimingSystem(),
                        new FiringSystem(),
                        new DamageSystem(),
                        new ExpireSystem(),
                        new AnimationSystem(),

                        // Renders
                        new MapRenderSystem(),
                        new RenderSystem(),
                        new DebugRenderSystem(batch)
                )

                .build()
                // now register the factories to be injected into the systems
                .register(towerFactory)
                .register(mobFactory)
                .register(projectileFactory)
                .register(batch)
                .register(assetManager)
                .register(MapGrid.getTestGrid(GAME_WIDTH, GAME_HEIGHT));


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

    public void resize(int width, int height) {
        world.getSystem(MyCameraSystem.class).updateViewports(width, height);
    }
}
