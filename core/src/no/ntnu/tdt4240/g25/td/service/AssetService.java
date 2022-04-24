package no.ntnu.tdt4240.g25.td.service;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.utils.Array;

import no.ntnu.tdt4240.g25.td.model.MobType;
import no.ntnu.tdt4240.g25.td.model.TowerType;

public class AssetService {

    public final AssetManager assetManager;

    public AssetService() {
        assetManager = new AssetManager();
        FileHandleResolver resolver = new InternalFileHandleResolver();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
    }

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

    public BitmapFont getFont(Font font) {
        return assetManager.get(font.path, BitmapFont.class);
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

        for (TerrainAtlas atlas : TerrainAtlas.values()) {
            assetManager.load(atlas.path, TextureAtlas.class);
        }
    }

    public void loadFonts(float initialSize) {
        FreetypeFontLoader.FreeTypeFontLoaderParameter smallParam = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        smallParam.fontFileName = Font.GILROY.path;
        smallParam.fontParameters.size = 20;
        assetManager.load(Font.SMALL.path, BitmapFont.class, smallParam);

        FreetypeFontLoader.FreeTypeFontLoaderParameter mediumParam = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        mediumParam.fontFileName = Font.GILROY.path;
        mediumParam.fontParameters.size = 30;
        assetManager.load(Font.MEDIUM.path, BitmapFont.class, mediumParam);

        FreetypeFontLoader.FreeTypeFontLoaderParameter largeParam = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        largeParam.fontFileName = Font.GILROY.path;
        largeParam.fontParameters.size = 40;
        assetManager.load(Font.LARGE.path, BitmapFont.class, largeParam);
    }

    public void dispose() {
        assetManager.dispose();

    }

    public boolean update() {
        return assetManager.update();
    }

    public enum Atlas {
        BUILDSPOTS("map/buildspots.atlas"),
        WALLS("map/walls.atlas"),
        EXPLOSION("effects/explosion.atlas"),
        SPARKS("effects/sparks.atlas"),
        BULLET("projectiles/bullet.atlas"),
        PLASMA("projectiles/plasma.atlas");

        public final String path;

        Atlas(String path) {
            this.path = path;
        }
    }


    public enum Font {
        GILROY("fonts/gilroy.ttf"),
        SMALL("small.ttf"),
        MEDIUM("medium.ttf"),
        LARGE("large.ttf");

        public final String path;

        Font(String path) {
            this.path = path;
        }
    }

    public enum TerrainAtlas {
        SUMMER("map/terrain/summer.atlas"),
        WINTER("map/terrain/winter.atlas"),
        FALL("map/terrain/fall.atlas"),
        SPRING("map/terrain/spring.atlas");
      
        public final String path;
      
        TerrainAtlas(String path) {
            this.path = path;
        }
    }
}
