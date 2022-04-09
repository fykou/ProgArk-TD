package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureComponent extends Component {

    public TextureRegion region;

    public TextureComponent() {
        region = null;
    }

    public TextureComponent(TextureRegion region) {
        this.region = region;
    }

}
