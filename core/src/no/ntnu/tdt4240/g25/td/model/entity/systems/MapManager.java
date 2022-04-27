package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.BaseSystem;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;

import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.MapComponent;

public class MapManager extends BaseSystem {

    private MapComponent mapComponent;
    private TiledMap map;

    protected void initialize() {
        TiledMap map = new TmxMapLoader().load("levels/level1.tmx");
        Array<RectangleMapObject> points = new Array<>();
        MapLayer waypoints = map.getLayers().get("waypoints");
        //waypoints
        waypoints.getObjects().getByType(RectangleMapObject.class)
                .forEach(points::add);
        // need mapheight and tilesize before computing waypoints to world coordinates
        int tileSize = map.getProperties().get("tilewidth", Integer.class);
        points.sort(Comparator.comparingInt(a -> a.getProperties().get("id", Integer.class)));
        points.forEach(point -> {
            // project from pixel to world coordinates by applying unit scale (i.e. applying the
            // inverse of the tilesize)
            Vector2 test = new Vector2(point.getRectangle().x, point.getRectangle().y);
            float unitScale = 1f/tileSize;
            float x = test.x * unitScale;
            float y = test.y * unitScale;
            mapComponent.path.add(new Vector2(x, y));
        });
        // get the polygons from the blocked object layer
        mapComponent.map = map;
    }

    @Override
    protected void processSystem() {

    }


}
