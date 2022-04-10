package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureComponent extends Component {

    public TextureRegion region;
    public float offsetRotation; // degrees, as a workaround for turrets pointing "north" instead of to the right

    public TextureComponent(TextureRegion region, float offsetRotation) {
        this.region = region;
        this.offsetRotation = offsetRotation;
    }

    public TextureComponent() {
        this(0);
    }

    public TextureComponent(float offsetRotation) {
        this(null, offsetRotation);
    }

}
