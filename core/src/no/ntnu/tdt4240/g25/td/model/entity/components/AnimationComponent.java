package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Pool;

public class AnimationComponent implements Component, Pool.Poolable {
    private Animation<TextureAtlas.AtlasRegion> animation;
    private float stateTime;
    public boolean isLooping;
    public boolean isPlaying;

    public AnimationComponent(Animation<TextureAtlas.AtlasRegion> animation, boolean isLooping) {
        this.animation = animation;
        this.stateTime = 0f;
        this.isLooping = isLooping;
        this.isPlaying = true;
    }

    public void update(float deltaTime) {
        stateTime += deltaTime;
        if (stateTime > animation.getAnimationDuration()) {
            stateTime = 0f;
            if (!isLooping) {
                isPlaying = false;
            }
        }
    }

    public TextureRegion getKeyFrame() {
        return animation.getKeyFrame(stateTime, isLooping);
    }

    @Override
    public void reset() {
        animation = null;
        stateTime = 0f;
    }
}

