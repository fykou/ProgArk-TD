package no.ntnu.tdt4240.g25.td.model.entity.factories;

import com.artemis.World;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;

import no.ntnu.tdt4240.g25.td.model.entity.components.AnimationComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.ProjectileComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.RotationComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.StateComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TextureComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.VelocityComponent;
import no.ntnu.tdt4240.g25.td.service.AssetService;

public class ProjectileFactory {

    private AssetService assetService;
    private World world;

    public ProjectileFactory(AssetService assetService) {
        this.assetService = assetService;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void createProjectile(float x, float y, float vx, float vy, float damage, float splashRadius) {
        var animationsMap = new IntMap<Animation<TextureAtlas.AtlasRegion>>();
        // get bullet for projectile without splash, plasma for splash
        Array<TextureAtlas.AtlasRegion> regions;
        if (splashRadius != 0) {
            regions = assetService.getAtlasRegionArray(AssetService.Atlas.Plasma.path, AssetService.Atlas.Plasma.name());
        }
        else { // default to bullet
            regions = assetService.getAtlasRegionArray(AssetService.Atlas.Bullet.path, AssetService.Atlas.Bullet.name());
        }
        animationsMap.put(StateComponent.STATE_IDLE, new Animation<>(regions.size/60f,regions));
        world.createEntity().edit()
                .add(new StateComponent(StateComponent.STATE_IDLE))
                .add(new PositionComponent(x, y))
                .add(new VelocityComponent(vx, vy))
                .add(new RotationComponent())
                .add(new TextureComponent())
                .add(new AnimationComponent(animationsMap))
                .add(new ProjectileComponent(damage, splashRadius));

    }
}
