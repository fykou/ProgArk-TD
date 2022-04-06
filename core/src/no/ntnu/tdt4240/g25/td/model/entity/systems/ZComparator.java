package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;

import java.util.Comparator;

import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;

public class ZComparator implements Comparator<Entity> {
    private ComponentMapper<PositionComponent> pm;

    public ZComparator() {
        pm = ComponentMapper.getFor(PositionComponent.class);
    }

    /**
     * Compares two entities based on their z-coordinate.
     * @param t1 The first entity.
     * @param t2 The second entity.
     * @return -1 if t1 is behind t2, 1 if t1 is in front of t2, 0 if they are at the same z-coordinate.
     */
    @Override
    public int compare(Entity t1, Entity t2) {
        PositionComponent p1 = pm.get(t1);
        PositionComponent p2 = pm.get(t2);
        return (int) (p1.position.z - p2.position.z);
    }
}
