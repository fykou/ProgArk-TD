package no.ntnu.tdt4240.g25.td.model.entity.factories;

import com.artemis.World;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.IntMap;

import no.ntnu.tdt4240.g25.td.model.entity.components.AnimationComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.RotationComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TextureComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.StateComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TowerComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.VelocityComponent;
import no.ntnu.tdt4240.g25.td.service.AssetService;

public class TowerFactory {

    AssetService assetService;

    public TowerFactory(AssetService assetService) {
        this.assetService = assetService;
        initAnimations();
    }

    /**
     * Creates and adds a new rank 1 "normal" (type 1) tower entity placed at the given position.
     *
     * @param world  the engine to add the entity to
     * @param x      the x-coordinate of the tower
     * @param y      the y-coordinate of the tower
     */
    public void createSingleTower(World world, float x, float y) {
        var animationsMap = new IntMap<Animation<TextureAtlas.AtlasRegion>>();
        animationsMap.put(StateComponent.STATE_IDLE, turret_01_mk1_idle);
        animationsMap.put(StateComponent.STATE_ATTACKING, turret_01_mk1_attack);
        world.createEntity().edit()
                .add(new PositionComponent(x, y))
                .add(new RotationComponent())
                .add(new TowerComponent(TowerComponent.TYPE_1_RANGE, TowerComponent.TYPE_1_MK1_DAMAGE, TowerComponent.TYPE_1_MK1_RATE, TowerComponent.TOWER_TYPE_SINGLE))
                .add(new StateComponent(StateComponent.STATE_IDLE))
                .add(new TextureComponent(-90f))
                .add(new AnimationComponent(animationsMap));

    }

    /**
     * Creates and adds a new rank 1 "AoE" (type 2) tower entity placed at the given position.
     *
     * @param world  the engine to add the entity to
     * @param x      the x-coordinate of the tower
     * @param y      the y-coordinate of the tower
     */
    public void createAoETower(World world, float x, float y) {
        var animationsMap = new IntMap<Animation<TextureAtlas.AtlasRegion>>();
        animationsMap.put(StateComponent.STATE_IDLE, turret_02_mk1_idle);
        animationsMap.put(StateComponent.STATE_ATTACKING, turret_02_mk1_attack);
        world.createEntity().edit()
                .add(new PositionComponent(x, y))
                .add(new TowerComponent(TowerComponent.TYPE_2_RANGE, TowerComponent.TYPE_2_MK1_DAMAGE, TowerComponent.TYPE_2_MK1_RATE, TowerComponent.TOWER_TYPE_AOE))
                .add(new StateComponent(StateComponent.STATE_IDLE))
                .add(new TextureComponent(-90))
                .add(new AnimationComponent(animationsMap));
    }

    public final static float TURRET_FIRE_DURATION = 1 / 30f;
    public final static float IDLE_DURATION = 1; // placeholder, as idle animation is 1 frame long

    public Animation<TextureAtlas.AtlasRegion> turret_01_mk1_idle;
    public Animation<TextureAtlas.AtlasRegion> turret_01_mk2_idle;
    public Animation<TextureAtlas.AtlasRegion> turret_01_mk3_idle;
    public Animation<TextureAtlas.AtlasRegion> turret_01_mk4_idle;

    public Animation<TextureAtlas.AtlasRegion> turret_01_mk1_attack;
    public Animation<TextureAtlas.AtlasRegion> turret_01_mk2_attack;
    public Animation<TextureAtlas.AtlasRegion> turret_01_mk3_attack;
    public Animation<TextureAtlas.AtlasRegion> turret_01_mk4_attack;

    public Animation<TextureAtlas.AtlasRegion> turret_02_mk1_idle;
    public Animation<TextureAtlas.AtlasRegion> turret_02_mk2_idle;
    public Animation<TextureAtlas.AtlasRegion> turret_02_mk3_idle;
    public Animation<TextureAtlas.AtlasRegion> turret_02_mk4_idle;

    public Animation<TextureAtlas.AtlasRegion> turret_02_mk1_attack;
    public Animation<TextureAtlas.AtlasRegion> turret_02_mk2_attack;
    public Animation<TextureAtlas.AtlasRegion> turret_02_mk3_attack;
    public Animation<TextureAtlas.AtlasRegion> turret_02_mk4_attack;


    /**
     * Initializes the animations for the towers by loading them from the asset service.
     */
    private void initAnimations() {
        // Type 1 tower turrets
        turret_01_mk1_attack = new Animation<>(TURRET_FIRE_DURATION, assetService.getTurretRegionArray(AssetService.TurretRegions.TYPE_1_MK1));
        turret_01_mk2_attack = new Animation<>(TURRET_FIRE_DURATION, assetService.getTurretRegionArray(AssetService.TurretRegions.TYPE_1_MK2));
        turret_01_mk3_attack = new Animation<>(TURRET_FIRE_DURATION, assetService.getTurretRegionArray(AssetService.TurretRegions.TYPE_1_MK3));
        turret_01_mk4_attack = new Animation<>(TURRET_FIRE_DURATION, assetService.getTurretRegionArray(AssetService.TurretRegions.TYPE_1_MK4));

        turret_01_mk1_idle = new Animation<>(IDLE_DURATION, assetService.getTurretRegion(AssetService.TurretRegions.TYPE_1_MK1));
        turret_01_mk2_idle = new Animation<>(IDLE_DURATION, assetService.getTurretRegion(AssetService.TurretRegions.TYPE_1_MK2));
        turret_01_mk3_idle = new Animation<>(IDLE_DURATION, assetService.getTurretRegion(AssetService.TurretRegions.TYPE_1_MK3));
        turret_01_mk4_idle = new Animation<>(IDLE_DURATION, assetService.getTurretRegion(AssetService.TurretRegions.TYPE_1_MK4));

        // Type 2 tower turrets
        turret_02_mk1_attack = new Animation<>(TURRET_FIRE_DURATION, assetService.getTurretRegionArray(AssetService.TurretRegions.TYPE_2_MK1));
        turret_02_mk2_attack = new Animation<>(TURRET_FIRE_DURATION, assetService.getTurretRegionArray(AssetService.TurretRegions.TYPE_2_MK2));
        turret_02_mk3_attack = new Animation<>(TURRET_FIRE_DURATION, assetService.getTurretRegionArray(AssetService.TurretRegions.TYPE_2_MK3));
        turret_02_mk4_attack = new Animation<>(TURRET_FIRE_DURATION, assetService.getTurretRegionArray(AssetService.TurretRegions.TYPE_2_MK4));

        turret_02_mk1_idle = new Animation<>(IDLE_DURATION, assetService.getTurretRegion(AssetService.TurretRegions.TYPE_2_MK1));
        turret_02_mk2_idle = new Animation<>(IDLE_DURATION, assetService.getTurretRegion(AssetService.TurretRegions.TYPE_2_MK2));
        turret_02_mk3_idle = new Animation<>(IDLE_DURATION, assetService.getTurretRegion(AssetService.TurretRegions.TYPE_2_MK3));
        turret_02_mk4_idle = new Animation<>(IDLE_DURATION, assetService.getTurretRegion(AssetService.TurretRegions.TYPE_2_MK4));

    }

}
