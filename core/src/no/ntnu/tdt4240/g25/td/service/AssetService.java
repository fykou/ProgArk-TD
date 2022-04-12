package no.ntnu.tdt4240.g25.td.service;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;

import no.ntnu.tdt4240.g25.td.model.MobType;
import no.ntnu.tdt4240.g25.td.model.TowerType;

public class AssetService {

    public final AssetManager assetManager = new AssetManager();

    public TextureAtlas getAtlas(String name) {
        return assetManager.get(name, TextureAtlas.class);
    }

    public TextureAtlas.AtlasRegion getAtlasRegion(String atlasPath, String regionName){
        return assetManager.get(atlasPath, TextureAtlas.class).findRegion(regionName, 0);
    }

    public Array<TextureAtlas.AtlasRegion> getAtlasRegionArray(String atlasPath, String regionName){
        return assetManager.get(atlasPath, TextureAtlas.class).findRegions(regionName);
    }

    public Array<TextureAtlas.AtlasRegion> wrapRegionInArray(TextureAtlas.AtlasRegion region){
        Array<TextureAtlas.AtlasRegion> regions = new Array<>();
        regions.add(region);
        return regions;
    }

    public void loadTextures(){
        // In order to add new types of towers and mobs together with the fact that their enums
        // are used other places, they're defined elsewhere, while other more generic assets are defined
        // with paths here.
        for (TowerType type : TowerType.values()) {
            assetManager.load(type.atlasPath, TextureAtlas.class);
        }
        for (MobType type : MobType.values()) {
            assetManager.load(type.atlasPath, TextureAtlas.class);
        }
        for (Atlas atlas : Atlas.values()) {
            assetManager.load(atlas.path, TextureAtlas.class);
        }
        assetManager.finishLoading();
    }

    public void dispose() {
        assetManager.dispose();
    }

    public boolean update() {
        return assetManager.update();
    }

    public enum Atlas {
        Terrain("map/terrain.atlas"),
        Buildspots("map/buildspots.atlas"),
        Walls("map/walls.atlas"),
        Explosion("effects/explosion.atlas"),
        Sparks("effects/sparks.atlas"),
        Bullet("projectiles/bullet.atlas"),
        Plasma("projectiles/plasma.atlas");

        public final String path;

        Atlas(String path) {
            this.path = path;
        }

    }
}
