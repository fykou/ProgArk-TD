package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import no.ntnu.tdt4240.g25.td.model.entity.components.AnimationComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.SpriteComponent;

public class AnimationSystem extends IteratingSystem {

    private final ComponentMapper<AnimationComponent> am = ComponentMapper.getFor(AnimationComponent.class);
    private final ComponentMapper<SpriteComponent> sm = ComponentMapper.getFor(SpriteComponent.class);

    public AnimationSystem() {
        super(Family.all(AnimationComponent.class, SpriteComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        AnimationComponent ac = am.get(entity);
        SpriteComponent sc = sm.get(entity);

        if (ac.isPlaying) {
            ac.update(deltaTime);
            sc.region.setRegion(ac.getKeyFrame());
        }
    }
}

