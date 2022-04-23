package no.ntnu.tdt4240.g25.td.model.entity.factories;

import com.artemis.ComponentMapper;
import com.artemis.World;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
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

public class TowerFactory extends EntityFactory {

    ComponentMapper<PositionComponent> mPosition;
    ComponentMapper<RotationComponent> mRotation;
    ComponentMapper<TextureComponent> mTexture;
    ComponentMapper<AnimationComponent> mAnimation;
    ComponentMapper<TowerComponent> mTower;
    ComponentMapper<StateComponent> mState;


    public TowerFactory(AssetService assetService) {
        super(assetService);
    }

    // generalize the create functions defined above to use the new tower types enum, so that I can remove the above two functions
    public void create(int tileX, int tileY, TowerType type, TowerLevel level) {
        tileX -= .5f;
        tileY -= .5f;
        IntMap<Animation<TextureAtlas.AtlasRegion>> animationsMap = new IntMap<>();
        animationsMap.put(StateComponent.STATE_IDLE, new Animation<>(1, assetService.wrapRegionInArray(assetService.getAtlasRegion(type.atlasPath, level.name()))));
        animationsMap.put(StateComponent.STATE_ATTACKING, getAnimation(type, level));
        int newId = world.create();
        mPosition.create(newId).get().set(tileX, tileY);
        mRotation.create(newId).get();
        mTower.create(newId).set(type, level);
        mState.create(newId).set(StateComponent.STATE_IDLE, false);
        mTexture.create(newId).set(null,-90f, 1.2f);
        mAnimation.create(newId).animations = animationsMap;
    }

    public Animation<TextureAtlas.AtlasRegion> getAnimation(TowerType type, TowerLevel level) {
        Array<TextureAtlas.AtlasRegion> regions = assetService.getAtlasRegionArray(type.atlasPath, level.name());
        float frameDuration = 1 / 30f;
        return new Animation<>(frameDuration, regions);
    }


}
