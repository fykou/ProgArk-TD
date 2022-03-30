package no.ntnu.tdt4240.g25.td;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class TdAssets {

    // Example assets
    public static final String EXAMPLE_TEXTURE = "example.png";

    // Texture assets

    // Font assets

    // Sound assets



    public final AssetManager assetManager = new AssetManager();
    public TdAssets() {
        // TODO: Load assets here
        // assetManager.load(EXAMPLE_TEXTURE, Texture.class);
    }

    public Texture getTexture(String name) {
        return assetManager.get(name, Texture.class);
    }

}
