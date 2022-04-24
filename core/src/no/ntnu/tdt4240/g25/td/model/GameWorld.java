package no.ntnu.tdt4240.g25.td.model;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.link.EntityLinkManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import net.mostlyoriginal.api.SingletonPlugin;

import no.ntnu.tdt4240.g25.td.model.entity.factories.MobFactory;
import no.ntnu.tdt4240.g25.td.model.entity.factories.ProjectileFactory;
import no.ntnu.tdt4240.g25.td.model.entity.factories.TowerFactory;
import no.ntnu.tdt4240.g25.td.model.entity.systems.AimingSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.WaveSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.map.MapManager;
import no.ntnu.tdt4240.g25.td.model.entity.systems.render.AnimationSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.BoundsSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.CollisionSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.DamageSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.ExpireSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.FindTargetSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.FiringSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.render.HealthRenderSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.render.MapRenderSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.MovementSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.PathingSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.MyCameraSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.render.RenderSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.debug.DebugRenderSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.render.WidgetRenderSystem;
import no.ntnu.tdt4240.g25.td.model.map.MapGrid;
import no.ntnu.tdt4240.g25.td.service.AssetService;

public class GameWorld {

    public static int GAME_WIDTH = 9;
    public static int GAME_HEIGHT = 16;

    TowerFactory towerFactory;
    MobFactory mobFactory;
    ProjectileFactory projectileFactory;
    World world;


    public GameWorld(AssetService assetManager, ShapeRenderer renderer, SpriteBatch batch) {
        createFactories();
        createWorld(batch, renderer, assetManager);
    }

    protected void createWorld(SpriteBatch batch, ShapeRenderer renderer, AssetService assetManager) {
        WorldConfiguration config = new WorldConfigurationBuilder()
                .dependsOn(
                        EntityLinkManager.class,
                        SingletonPlugin.class)
                .with(
                        // Managers who need to initialize Singleton Components etc.
                        new MapManager(),

                        // Game systems
                        new WaveSystem(),
                        new MovementSystem(),
                        new PathingSystem(),
                        new BoundsSystem(),
                        new CollisionSystem(),
                        new FindTargetSystem(1f / 60),
                        new AimingSystem(),
                        new FiringSystem(),
                        new DamageSystem(),
                        new ExpireSystem(),

                        // Renders
                        new MyCameraSystem(GAME_WIDTH, GAME_HEIGHT),
                        new AnimationSystem(),
                        new MapRenderSystem(),
                        new RenderSystem(),
                        new HealthRenderSystem(),
                        new WidgetRenderSystem(),
                        //new DebugRenderSystem(),

                        // Factories
                        towerFactory,
                        mobFactory,
                        projectileFactory
                )
                .build()
                .register(batch)
                .register(renderer)
                .register(assetManager)
                .register(MapGrid.getTestGrid(GAME_WIDTH, GAME_HEIGHT));

        this.world = new World(config);
        // set world for the factories to be able to create entities
    }

    protected void createFactories() {
        towerFactory = new TowerFactory();
        mobFactory = new MobFactory();
        projectileFactory = new ProjectileFactory();
    }

    public void update(float delta) {
        world.setDelta(delta);
        world.process();
    }

    public void resize(int width, int height) {
        world.getSystem(MyCameraSystem.class).updateViewports(width, height);
    }
}
