package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.Aspect;
import com.artemis.annotations.All;
import com.artemis.annotations.Exclude;
import com.artemis.systems.IntervalIteratingSystem;

import no.ntnu.tdt4240.g25.td.model.entity.components.HasTargetComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TowerComponent;

@All(TowerComponent.class)
@Exclude(HasTargetComponent.class)
public class Targeting extends IntervalIteratingSystem {


    /**
     * Creates a new IntervalEntityProcessingSystem.
     *
     * @param aspect   the aspect to match entities
     * @param interval the interval in seconds between processing of entities
     */
    public Targeting(Aspect.Builder aspect, float interval) {
        super(aspect, interval);
    }

    @Override
    protected void process(int entityId) {

    }
}
