package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;

import no.ntnu.tdt4240.g25.td.model.entity.components.AnimationComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TextureComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.StateComponent;

@All({AnimationComponent.class, TextureComponent.class, StateComponent.class})
public class AnimationSystem extends IteratingSystem {
    ComponentMapper<AnimationComponent> mAnimation;
    ComponentMapper<StateComponent> mState;
    ComponentMapper<TextureComponent> mTexture;

    @Override
    protected void process(int entityId) {
        AnimationComponent ac = mAnimation.get(entityId);
        StateComponent state = mState.get(entityId);
        TextureComponent tm = mTexture.get(entityId);
        if (ac.animations.containsKey(state.get())) {
            tm.region = ac.animations.get(state.get()).getKeyFrame(state.time, state.isLooping);
        }
        else if (ac.animations.containsKey(StateComponent.STATE_IDLE)) { // Fallback to idle animation
            tm.region = ac.animations.get(StateComponent.STATE_IDLE).getKeyFrame(state.time, state.isLooping);
        }
        else { // Fallback to null animation
            tm.region = null;
        }
        state.time += this.world.delta;
    }
}

