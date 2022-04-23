package no.ntnu.tdt4240.g25.td.model.entity.components.singleton;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import net.mostlyoriginal.api.Singleton;

@Singleton
public class WaypointsComponent extends Component {
    public Array<Vector2> path = new Array<>();
}
