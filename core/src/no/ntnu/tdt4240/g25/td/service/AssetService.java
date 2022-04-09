package no.ntnu.tdt4240.g25.td.service;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

public class AssetService {

    public final AssetManager assetManager = new AssetManager();

    public Array<TextureAtlas.AtlasRegion> getTurretRegionArray(String regionName){
        return assetManager.get(Textures.TURRET_ATLAS_PATH, TextureAtlas.class).findRegions(regionName);
    }

    /**
     * Helper method in order to fetch the texture used for idle animation for the turrets, as the idle animation is
     * the first frame of the attack animation.
     * @param regionName the name of the region to fetch the texture for
     * @return the region at index 0 for the given region name
     */
    public TextureAtlas.AtlasRegion getTurretRegion(String regionName){
        return assetManager.get(Textures.TURRET_ATLAS_PATH, TextureAtlas.class).findRegion(regionName, 0);
    }

    public void loadTextures(){
        assetManager.load(Textures.TURRET_ATLAS_PATH, TextureAtlas.class);
        assetManager.load(Textures.TERRAIN_ATLAS, TextureAtlas.class);
        assetManager.load(Textures.BUILDSPOTS_ATLAS, TextureAtlas.class);
        assetManager.load(Textures.WALLS_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();
    }

    public void dispose() {
        assetManager.dispose();
    }

    public boolean update() {
        return assetManager.update();
    }

    public static abstract class Textures {

        // Tower assets
        public static final String TURRET_ATLAS_PATH = "turrets/turrets.atlas";

        // Map assets
        public static final String TERRAIN_ATLAS = "map/terrain.atlas";
        public static final String BUILDSPOTS_ATLAS = "map/buildspots.atlas";
        public static final String WALLS_ATLAS = "map/walls.atlas";

    }

    public static abstract class Sounds {
        public static final String EXAMPLE_SOUND = "example/sound.mp3";
    }

    public static abstract class TurretRegions {
        public static final String TYPE_1_MK1 = "turret_01_mk1";
        public static final String TYPE_1_MK2 = "turret_01_mk2";
        public static final String TYPE_1_MK3 = "turret_01_mk3";
        public static final String TYPE_1_MK4 = "turret_01_mk4";

        public static final String TYPE_2_MK1 = "turret_02_mk1";
        public static final String TYPE_2_MK2 = "turret_02_mk2";
        public static final String TYPE_2_MK3 = "turret_02_mk3";
        public static final String TYPE_2_MK4 = "turret_02_mk4";
    }

}
