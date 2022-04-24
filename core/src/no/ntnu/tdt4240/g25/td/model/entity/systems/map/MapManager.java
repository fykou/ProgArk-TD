package no.ntnu.tdt4240.g25.td.model.entity.systems.map;

import com.artemis.BaseSystem;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.math.Vector2;

import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.MapComponent;
import no.ntnu.tdt4240.g25.td.model.map.MapGrid;

public class MapManager extends BaseSystem {

    private MapComponent mapComponent;

    @Wire
    MapGrid mapGrid;

    @Override
    protected void initialize() {
        mapComponent.mapGrid = mapGrid.getGrid();
        mapComponent.path.addAll(mapGrid.getPath());
        // convert from world coordinates to tile coordinates
        Vector2 offset = new Vector2(.5f, .5f);
        mapComponent.path.forEach(p -> p.add(offset));
        setEnabled(false);
    }

    @Override
    protected void processSystem() {

    }


}
