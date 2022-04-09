package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.artemis.systems.IteratingSystem;

import no.ntnu.tdt4240.g25.td.model.entity.components.AnimationComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TextureComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.StateComponent;

@All({AnimationComponent.class, TextureComponent.class, StateComponent.class})
public class AnimationSystem extends IteratingSystem {
    ComponentMapper<AnimationComponent> animationMapper;
    ComponentMapper<StateComponent> stateMapper;
    ComponentMapper<TextureComponent> spriteMapper;

    @Override
    protected void process(int entityId) {
        var ac = animationMapper.get(entityId);
        var state = stateMapper.get(entityId);
        if (ac.animations.containsKey(state.get())) {
            var tm = spriteMapper.get(entityId);
            tm.region = ac.animations.get(state.get()).getKeyFrame(state.time, state.isLooping);
        }
        state.time += this.world.delta;
    }
}

