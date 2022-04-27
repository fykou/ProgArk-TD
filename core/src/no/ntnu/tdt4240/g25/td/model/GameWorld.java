package no.ntnu.tdt4240.g25.td.model;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.link.EntityLinkManager;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import net.mostlyoriginal.api.SingletonPlugin;

import no.ntnu.tdt4240.g25.td.TdGame;
import no.ntnu.tdt4240.g25.td.model.entity.factories.MobFactory;
import no.ntnu.tdt4240.g25.td.model.entity.factories.ProjectileFactory;
import no.ntnu.tdt4240.g25.td.model.entity.factories.TowerFactory;
import no.ntnu.tdt4240.g25.td.model.entity.systems.AimingSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.InputSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.WaveSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.debug.DebugRenderSystem;
import no.ntnu.tdt4240.g25.td.model.entity.systems.MapManager;
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
import no.ntnu.tdt4240.g25.td.model.entity.systems.render.WidgetRenderSystem;
import no.ntnu.tdt4240.g25.td.view.GameView;

public class GameWorld {

    public final static int WORLD_WIDTH = 9;
    public final static int WORLD_HEIGHT = 16;

    private TowerFactory towerFactory;
    private MobFactory mobFactory;
    private ProjectileFactory projectileFactory;
    World world;


    public GameWorld(ShapeRenderer renderer, SpriteBatch batch, GameView view) {
        createFactories();
        createWorld(batch, renderer, view);
    }

    protected void createWorld(SpriteBatch batch, ShapeRenderer renderer, GameView view) {
        WorldConfiguration config = new WorldConfigurationBuilder()
                .dependsOn(
                        EntityLinkManager.class,
                        SingletonPlugin.class)
                .with(
                        // Managers who need to initialize Singleton Components etc.
                        new MapManager(),
                        new InputSystem(),


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
                        //new ViewManager(),

                        // Renders
                        new MyCameraSystem(TdGame.UI_WIDTH, TdGame.UI_HEIGHT),
                        new AnimationSystem(),

                        // Renders
                        new MapRenderSystem(),
                        new RenderSystem(),
                        new HealthRenderSystem(),
                        new WidgetRenderSystem(),
                        new DebugRenderSystem(),

                        // Factories
                        towerFactory,
                        mobFactory,
                        projectileFactory
                )
                .build()
                .register(view)
                .register(batch)
                .register(renderer);

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

    public InputProcessor getInputProcessor() {
        return world.getSystem(InputSystem.class);
    }
    public TowerFactory getTowerFactory() {
        return towerFactory;
    }
}
