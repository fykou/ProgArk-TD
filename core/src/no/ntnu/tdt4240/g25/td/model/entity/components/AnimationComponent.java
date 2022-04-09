package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.IntMap;

public class AnimationComponent extends Component {
    public final IntMap<Animation<TextureAtlas.AtlasRegion>> animations;

    public AnimationComponent(IntMap<Animation<TextureAtlas.AtlasRegion>> animations) {
        this.animations = animations;
    }

    public AnimationComponent() {
        this(new IntMap<>());
    }

}

