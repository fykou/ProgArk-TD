package no.ntnu.tdt4240.g25.td.model.entity.components.singleton;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import net.mostlyoriginal.api.Singleton;

import no.ntnu.tdt4240.g25.td.model.map.MapTile;

@Singleton
public class MapComponent extends Component {
    /**
     * The waypoints in tile coordinates.
     */
    public Array<Vector2> path = new Array<>();
    public Array<MapTile> mapGrid = new Array<>();
}
