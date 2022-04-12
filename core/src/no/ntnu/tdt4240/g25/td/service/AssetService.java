package no.ntnu.tdt4240.g25.td.service;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

import no.ntnu.tdt4240.g25.td.model.MobType;
import no.ntnu.tdt4240.g25.td.model.TowerType;

public class AssetService {

    public final AssetManager assetManager = new AssetManager();

    public TextureAtlas.AtlasRegion getAtlasRegion(String atlasPath, String regionName){
        return assetManager.get(atlasPath, TextureAtlas.class).findRegion(regionName, 0);
    }

    public Array<TextureAtlas.AtlasRegion> getAtlasRegionArray(String atlasPath, String regionName){
        return assetManager.get(atlasPath, TextureAtlas.class).findRegions(regionName);
    }

    public Array<TextureAtlas.AtlasRegion> wrapRegionInArray(TextureAtlas.AtlasRegion region){
        Array<TextureAtlas.AtlasRegion> regions = new Array<TextureAtlas.AtlasRegion>();
        regions.add(region);
        return regions;
    }

    public void loadTextures(){
        for (TowerType type : TowerType.values()) {
            assetManager.load(type.atlasPath, TextureAtlas.class);
        }
        for (MobType type : MobType.values()) {
            assetManager.load(type.atlasPath, TextureAtlas.class);
        }
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

        // Map assets
        public static final String TERRAIN_ATLAS = "map/terrain.atlas";
        public static final String BUILDSPOTS_ATLAS = "map/buildspots.atlas";
        public static final String WALLS_ATLAS = "map/walls.atlas";

    }

    public static abstract class Sounds {
        public static final String EXAMPLE_SOUND = "example/sound.mp3";
    }
}
