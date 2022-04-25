package no.ntnu.tdt4240.g25.td.model.entity.factories;

import com.artemis.ComponentMapper;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntMap;

import no.ntnu.tdt4240.g25.td.asset.Assets;
import no.ntnu.tdt4240.g25.td.model.TowerLevel;
import no.ntnu.tdt4240.g25.td.model.TowerType;
import no.ntnu.tdt4240.g25.td.model.entity.components.AnimationComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.RotationComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TextureComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.StateComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TowerComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;

public class TowerFactory extends EntityFactory {

    private ComponentMapper<PositionComponent> mPosition;
    private ComponentMapper<RotationComponent> mRotation;
    private ComponentMapper<TextureComponent> mTexture;
    private ComponentMapper<AnimationComponent> mAnimation;
    private ComponentMapper<TowerComponent> mTower;
    private ComponentMapper<StateComponent> mState;


    public void create(float tileX, float tileY, TowerType type, TowerLevel level) {
        float x = tileX + .5f;
        float y = tileY + .5f;
        IntMap<Animation<TextureAtlas.AtlasRegion>> animationsMap = new IntMap<>();
        animationsMap.put(StateComponent.STATE_IDLE, new Animation<>(1, Assets.getInstance().wrapRegionInArray(Assets.getInstance().getAtlasRegion(type.atlasPath, level.name()))));
        animationsMap.put(StateComponent.STATE_ATTACKING, getAnimation(type, level));
        int newId = world.create();
        mPosition.create(newId).get().set(x, y);
        mRotation.create(newId).get();
        mTower.create(newId).set(type, level);
        mState.create(newId).set(StateComponent.STATE_IDLE, false);
        mTexture.create(newId).set(null,-90f, 1.2f);
        mAnimation.create(newId).animations = animationsMap;
    }

    public Animation<TextureAtlas.AtlasRegion> getAnimation(TowerType type, TowerLevel level) {
        Array<TextureAtlas.AtlasRegion> regions = Assets.getInstance().getAtlasRegionArray(type.atlasPath, level.name());
        float frameDuration = 1 / 30f;
        return new Animation<>(frameDuration, regions);
    }
}
