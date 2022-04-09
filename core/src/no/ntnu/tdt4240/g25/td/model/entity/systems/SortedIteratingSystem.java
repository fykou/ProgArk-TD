package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;


import java.util.Comparator;

public abstract class SortedIteratingSystem extends BaseEntitySystem {

    protected final Array<Integer> sortedIds = new Array<>(100);

    public SortedIteratingSystem() {

    }

    public SortedIteratingSystem(Aspect.Builder aspect) {
        super(aspect);
    }

    protected abstract void process(int entityId);

    @Override
    protected abstract void begin();

    @Override
    protected abstract void end();

    protected abstract void sort();

    @Override
    protected void processSystem() {
        IntBag actives = subscription.getEntities();
        int[] ids = actives.getData();
        for (int i = 0, s = actives.size(); s > i; i++) {
            sortedIds.add(ids[i]);
        }
        sort();
        for (Integer id : new Array.ArrayIterator<>(sortedIds)) {
            process(id);
        }
        sortedIds.clear();
    }
}
