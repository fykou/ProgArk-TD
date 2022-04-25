package no.ntnu.tdt4240.g25.td.asset;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

import no.ntnu.tdt4240.g25.td.model.MobType;
import no.ntnu.tdt4240.g25.td.model.TowerType;

public class Assets {

    private final AssetManager assetManager;
    private static Assets instance;

    public static Assets getInstance() {
        if (instance == null) {
            instance = new Assets();
        }
        return instance;
    }

    private Assets() {
        assetManager = new AssetManager();
        FileHandleResolver resolver = new InternalFileHandleResolver();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
    }

    public Texture getTexture(String name) {
        return assetManager.get(name, Texture.class);
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

    public Skin getSkin() {
        return assetManager.get("scene2d/sgx-ui.json", Skin.class);
    }

    public Music getMusic(GameMusic music){
        return assetManager.get(music.path, Music.class);
    }

    public Sound getSound(SoundFx soundFx){
        return assetManager.get(soundFx.path, Sound.class);
    }

    public BitmapFont getFont(Font font) {
        return assetManager.get(font.path, BitmapFont.class);
    }

    public void finishLoading() {
        assetManager.finishLoading();
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
        for (ObjectAtlas objectAtlas : ObjectAtlas.values()) {
            assetManager.load(objectAtlas.path, TextureAtlas.class);
        }

        for (TerrainAtlas atlas : TerrainAtlas.values()) {
            assetManager.load(atlas.path, TextureAtlas.class);
        }
        assetManager.load("screens/backdrop.png", Texture.class);
        // tower textures to skin
    }

    public void loadMusic() {
        for (GameMusic music : GameMusic.values()) {
            assetManager.load(music.path, Music.class);
        }
    }

    public void loadSounds(){
        for (SoundFx soundFx : SoundFx.values()) {
            assetManager.load(soundFx.path, com.badlogic.gdx.audio.Sound.class);
        }
    }

    public void loadSkin(){
        assetManager.load("scene2d/sgx-ui.json", Skin.class);
    }

    public void addSpritesToSkin(Skin skin){
        for (TowerType type : TowerType.values()) {
            skin.add(type.atlasPath, assetManager.get(type.atlasPath, TextureAtlas.class));
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

}
