package no.ntnu.tdt4240.g25.td.model.entity.factories;

import com.artemis.World;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.IntMap;

import no.ntnu.tdt4240.g25.td.model.MobType;
import no.ntnu.tdt4240.g25.td.model.entity.components.AnimationComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.BoundsComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.MobComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.RotationComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.StateComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TextureComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.VelocityComponent;
import no.ntnu.tdt4240.g25.td.service.AssetService;

public class MobFactory {

    AssetService assetService;
    World world;

    public MobFactory(AssetService assetService) {
        this.assetService = assetService;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    public void create(float x, float y, MobType type) {
        var animationsMap = new IntMap<Animation<TextureAtlas.AtlasRegion>>();
        animationsMap.put(StateComponent.STATE_IDLE, new Animation<>(1, assetService.wrapRegionInArray(assetService.getAtlasRegion(type.atlasPath, type.name()))));
        animationsMap.put(StateComponent.STATE_MOVING, new Animation<>(1/8f, assetService.getAtlasRegionArray(type.atlasPath, type.name())));
        world.createEntity().edit()
                .add(new PositionComponent(x, y))
                .add(new BoundsComponent(100, 100))
                .add(new VelocityComponent())
                .add(new RotationComponent(0))
                .add(new StateComponent(StateComponent.STATE_IDLE))
                .add(new MobComponent(type))
                .add(new TextureComponent(-90f))
                .add(new AnimationComponent(animationsMap));
    }

}
