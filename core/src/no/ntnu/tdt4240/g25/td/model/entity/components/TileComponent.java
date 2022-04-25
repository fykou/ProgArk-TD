package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.PooledComponent;

import net.mostlyoriginal.api.Singleton;

@Singleton
public class TileComponent extends PooledComponent {

    /**
     * Called whenever the component is recycled. Implementation should reset component to pristine state.
     */
    @Override
    protected void reset() {

    }
}
