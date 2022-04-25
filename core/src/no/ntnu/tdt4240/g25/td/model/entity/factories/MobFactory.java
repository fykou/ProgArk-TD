package no.ntnu.tdt4240.g25.td.model.entity.factories;

import com.artemis.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.IntMap;

import no.ntnu.tdt4240.g25.td.asset.Assets;
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

public class MobFactory extends EntityFactory {

    private ComponentMapper<MobComponent> mMob;
    private ComponentMapper<PositionComponent> mPosition;
    private ComponentMapper<VelocityComponent> mVelocity;
    private ComponentMapper<BoundsComponent> mBounds;
    private ComponentMapper<TextureComponent> mTexture;
    private ComponentMapper<AnimationComponent> mAnimation;
    private ComponentMapper<RotationComponent> mRotation;
    private ComponentMapper<StateComponent> mState;
    private ComponentMapper<PathComponent> mPath;

    public void create(float x, float y, MobType type) {
        float size = type == MobType.TANK ? 1.2f : 0.75f;
        IntMap<Animation<TextureAtlas.AtlasRegion>> animationsMap = new IntMap<>();
        animationsMap.put(StateComponent.STATE_IDLE, new Animation<>(1, Assets.getInstance().wrapRegionInArray(Assets.getInstance().getAtlasRegion(type.atlasPath, type.name()))));
        animationsMap.put(StateComponent.STATE_MOVING, new Animation<>(1 / 8f, Assets.getInstance().getAtlasRegionArray(type.atlasPath, type.name())));
        int newId = world.create();
        mPosition.create(newId).get().set(x, y);
        mBounds.create(newId).get().set(x, y,0.8f, 0.8f);
        mVelocity.create(newId).get();
        mPath.create(newId).currentCheckpoint = 0;
        mRotation.create(newId);
        mState.create(newId).set(StateComponent.STATE_IDLE, true);
        mMob.create(newId).set(type, 1f); // get wavemultiplier parameter when we have it
        mTexture.create(newId).set(null, -90f, size);
        mAnimation.create(newId).animations = animationsMap;
    }
}
