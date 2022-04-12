package no.ntnu.tdt4240.g25.td.model.entity.factories;

import com.artemis.World;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.IntMap;

import no.ntnu.tdt4240.g25.td.model.TowerLevel;
import no.ntnu.tdt4240.g25.td.model.TowerType;
import no.ntnu.tdt4240.g25.td.model.entity.components.AnimationComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.RotationComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TextureComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.StateComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TowerComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.service.AssetService;

public class TowerFactory {

    AssetService assetService;

    World world;

    public TowerFactory(AssetService assetService) {
        this.assetService = assetService;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    // generalize the create functions defined above to use the new tower types enum, so that I can remove the above two functions
    public void createTower(float x, float y, TowerType type, TowerLevel level) {
        var animationsMap = new IntMap<Animation<TextureAtlas.AtlasRegion>>();
        animationsMap.put(StateComponent.STATE_IDLE, new Animation<>(1, assetService.wrapRegionInArray(assetService.getAtlasRegion(type.atlasPath, level.name()))));
        animationsMap.put(StateComponent.STATE_ATTACKING, getAnimation(type, level));
        world.createEntity().edit()
                .add(new PositionComponent(x, y))
                .add(new RotationComponent())
                .add(new TowerComponent(type.range, type.splashRadius, getDamage(type, level), getFireRate(type, level), level.level, type))
                .add(new StateComponent(StateComponent.STATE_IDLE))
                .add(new TextureComponent(-90f))
                .add(new AnimationComponent(animationsMap));
    }

    public Animation<TextureAtlas.AtlasRegion> getAnimation(TowerType type, TowerLevel level) {
        var regions = assetService.getAtlasRegionArray(type.atlasPath, level.name());
        var frameDuration = regions.size / 60f;
        return new Animation<>(frameDuration, regions);
    }

    private float getDamage(TowerType type, TowerLevel level) {
        float damage;
        switch (level) {
            case MK2:
                damage = type.mk2Damage;
                break;
            case MK3:
                damage = type.mk3Damage;
                break;
            case MK4:
                damage = type.mk4Damage;
                break;
            default: // ie. level 1 or anything else
                damage = type.mk1Damage;
        }
        return damage;
    }

    private float getFireRate(TowerType type, TowerLevel level) {
        float fireRate;
        switch (level) {
            case MK2:
                fireRate = type.mk2FireRate;
                break;
            case MK3:
                fireRate = type.mk3FireRate;
                break;
            case MK4:
                fireRate = type.mk4FireRate;
                break;
            default: // ie. level 1 or anything else
                fireRate = type.mk1FireRate;
        }
        return fireRate;
    }
}
