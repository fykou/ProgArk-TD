package no.ntnu.tdt4240.g25.td.model.entity.systems.map;

import com.artemis.BaseSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;

import no.ntnu.tdt4240.g25.td.model.GameWorld;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.MapComponent;
import no.ntnu.tdt4240.g25.td.model.entity.systems.MyCameraSystem;

public class MapManager extends BaseSystem {

    private MapComponent mapComponent;
    private MyCameraSystem cameraSystem;
    private TiledMap map;

    protected void initialize() {
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(true, GameWorld.WORLD_WIDTH, GameWorld.WORLD_HEIGHT);
        TiledMap map = new TmxMapLoader().load("levels/level1.tmx");
        Array<RectangleMapObject> points = new Array<>();
        MapLayer waypoints = map.getLayers().get("waypoints");
        //waypoints
        waypoints.getObjects().getByType(RectangleMapObject.class)
                .forEach(points::add);
        // get height and width of map
        map.getLayers().get("waypoints")
        int width = map.getProperties().get("width", Integer.class);
        int height = map.getProperties().get("height", Integer.class);
        points.sort(Comparator.comparingInt(a -> a.getProperties().get("id", Integer.class)));
        points.forEach(point -> {
                Vector3 test = camera.unproject(new Vector3(point.getRectangle().x, point.getRectangle().y, 0));
                Vector2 projected = new Vector2(test.x, test.y );
                mapComponent.path.add(projected);
                System.out.println(projected);
        });
        mapComponent.map = map;
    }

    @Override
    protected void processSystem() {

    }


}
