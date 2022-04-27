package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.BaseSystem;
import com.badlogic.gdx.Gdx;
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
        camera.setToOrtho(false, GameWorld.WORLD_WIDTH, GameWorld.WORLD_HEIGHT);
        TiledMap map = new TmxMapLoader().load("levels/level1.tmx");
        Array<RectangleMapObject> points = new Array<>();
        MapLayer waypoints = map.getLayers().get("waypoints");
        //waypoints
        waypoints.getObjects().getByType(RectangleMapObject.class)
                .forEach(points::add);
        // need mapheight and tilesize before computing waypoints to world coordinates
        int mapHeight = map.getProperties().get("height", Integer.class);
        int tileSize = map.getProperties().get("tilewidth", Integer.class);
        points.sort(Comparator.comparingInt(a -> a.getProperties().get("id", Integer.class)));
        points.forEach(point -> {
            Vector2 test = new Vector2(point.getRectangle().x, point.getRectangle().y);
            float unitScale = 1f/tileSize;
            float x = test.x * unitScale;
            float y = test.y * unitScale;
            mapComponent.path.add(new Vector2(x, y));
            System.out.println(point.getProperties().get("id")+"\t"+"x: " + x + "\t\ty: " + y);
        });
        mapComponent.map = map;
    }

    @Override
    protected void processSystem() {

    }


}
