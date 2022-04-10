package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.BaseEntitySystem;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.utils.Array;

public abstract class SortedIteratingSystem extends BaseEntitySystem {

    protected final Array<Integer> entityIds = new Array<>(100);

    public SortedIteratingSystem() {

    }

    protected abstract void process(int entityId);


    /**
     * Implementation should do any tasks required before starting to process entities, e.g.
     * opening batch for drawing etc.
     */
    @Override
    protected abstract void begin();

    /**
     * Implementation should do any tasks required after finishing processing entities, e.g.
     * closing batch after drawing etc.
     */
    @Override
    protected abstract void end();

    /**
     * Sort or otherwise order the entities contained in entityIds.
     */
    protected abstract void sort();

    @Override
    protected void processSystem() {
        IntBag actives = subscription.getEntities();
        int[] ids = actives.getData();
        entityIds.ensureCapacity(actives.size());
        for (int i = 0, s = actives.size(); s > i; i++) {
            entityIds.add(ids[i]);
        }
        sort();
        for (Integer id : new Array.ArrayIterator<>(entityIds)) {
            process(id);
        }
        entityIds.clear();
    }
}
