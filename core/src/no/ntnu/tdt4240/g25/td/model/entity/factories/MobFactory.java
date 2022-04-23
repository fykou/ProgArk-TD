package no.ntnu.tdt4240.g25.td.model.entity.factories;

import com.artemis.ComponentMapper;
import com.artemis.annotations.Wire;
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

    @Wire
    ComponentMapper<MobComponent> mMob;
    @Wire
    ComponentMapper<PositionComponent> mPosition;
    @Wire
    ComponentMapper<VelocityComponent> mVelocity;
    @Wire
    ComponentMapper<BoundsComponent> mBounds;
    @Wire
    ComponentMapper<TextureComponent> mTexture;
    @Wire
    ComponentMapper<AnimationComponent> mAnimation;
    @Wire
    ComponentMapper<RotationComponent> mRotation;
    @Wire
    ComponentMapper<StateComponent> mState;
    @Wire
    ComponentMapper<PathComponent> mPath;

    public MobFactory(AssetService assetService) {
        super(assetService);
    }

    public void create(int tileX, int tileY, MobType type) {
        tileX -= .5f;
        tileY -= .5f;
        IntMap<Animation<TextureAtlas.AtlasRegion>> animationsMap = new IntMap<>();
        animationsMap.put(StateComponent.STATE_IDLE, new Animation<>(1, assetService.wrapRegionInArray(assetService.getAtlasRegion(type.atlasPath, type.name()))));
        animationsMap.put(StateComponent.STATE_MOVING, new Animation<>(1 / 8f, assetService.getAtlasRegionArray(type.atlasPath, type.name())));
        int newId = world.create();
        mPosition.create(newId).get().set(tileX, tileY);
        mBounds.create(newId).get().set(tileX, tileY,0.8f, 0.8f);
        mVelocity.create(newId).get();
        mPath.create(newId).currentCheckpoint = 0;
        mRotation.create(newId);
        mState.create(newId).set(StateComponent.STATE_IDLE, true);
        mMob.create(newId).set(type, 1f); // get wavemultiplier parameter when we have it
        mTexture.create(newId).offsetRotation = -90f;
        mAnimation.create(newId).animations = animationsMap;

    }

}
