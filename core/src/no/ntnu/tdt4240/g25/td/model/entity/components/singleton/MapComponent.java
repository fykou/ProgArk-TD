package no.ntnu.tdt4240.g25.td.model.entity.components.singleton;

import com.artemis.Component;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import net.mostlyoriginal.api.Singleton;

import no.ntnu.tdt4240.g25.td.model.map.MapTileType;

@Singleton
public class MapComponent extends Component {
    /**
     * The waypoints in tile coordinates.
     */
    public TiledMap map;
    public Array<Vector2> path = new Array<>();
    public final Vector2 selectedTile = new Vector2(-1, -1);

    public MapTileType getTileTypeByCoordinate(int layer, int col, int row) {
        TiledMapTileLayer.Cell cell = ((TiledMapTileLayer) map.getLayers().get(layer)).getCell((col), (row));
        if(cell != null){
            TiledMapTile tile = cell.getTile();
            if(tile != null){
                int id = tile.getId();
                return MapTileType.getTileTypeById(id);
            }
        }
        return MapTileType.PLAIN;
    }
}
