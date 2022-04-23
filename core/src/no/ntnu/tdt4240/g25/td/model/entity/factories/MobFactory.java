package no.ntnu.tdt4240.g25.td.model.entity.factories;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.IntMap;

import no.ntnu.tdt4240.g25.td.model.MobType;
import no.ntnu.tdt4240.g25.td.model.entity.components.AnimationComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.BoundsComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.MobComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PathComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.RotationComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.StateComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TextureComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.VelocityComponent;
import no.ntnu.tdt4240.g25.td.service.AssetService;

public class MobFactory extends EntityFactory {

    public MobFactory(AssetService assetService) {
        super(assetService);
    }

    public void create(float x, float y, MobType type) {
        IntMap<Animation<TextureAtlas.AtlasRegion>> animationsMap = new IntMap<>();
        animationsMap.put(StateComponent.STATE_IDLE, new Animation<>(1, assetService.wrapRegionInArray(assetService.getAtlasRegion(type.atlasPath, type.name()))));
        animationsMap.put(StateComponent.STATE_MOVING, new Animation<>(1 / 8f, assetService.getAtlasRegionArray(type.atlasPath, type.name())));
        world.createEntity().edit()
                .add(new PositionComponent(x, y))
                .add(new BoundsComponent(0.8f, 0.8f))
                .add(new VelocityComponent())
                .add(new PathComponent())
                .add(new RotationComponent(0))
                .add(new StateComponent(StateComponent.STATE_IDLE))
                .add(new MobComponent(type))
                .add(new TextureComponent(-90f))
                .add(new AnimationComponent(animationsMap));
    }

}
