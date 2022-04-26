package no.ntnu.tdt4240.g25.td.model.entity.systems.map;

import com.artemis.BaseSystem;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;

import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.MapComponent;
import no.ntnu.tdt4240.g25.td.model.map.MapGrid;

public class MapManager extends BaseSystem {

    private MapComponent mapComponent;

    protected void initialize() {
        TiledMap map = new TmxMapLoader().load("levels/level1.tmx");
        MapGrid mapGrid = new MapGrid(map);
    }

    @Override
    protected void processSystem() {

    }


}
