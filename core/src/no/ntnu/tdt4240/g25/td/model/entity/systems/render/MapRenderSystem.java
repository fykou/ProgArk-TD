package no.ntnu.tdt4240.g25.td.model.entity.systems.render;

import com.artemis.BaseSystem;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ArrayMap;

import no.ntnu.tdt4240.g25.td.model.entity.systems.MyCameraSystem;
import no.ntnu.tdt4240.g25.td.model.map.MapGrid;
import no.ntnu.tdt4240.g25.td.model.map.MapTile;
import no.ntnu.tdt4240.g25.td.model.map.MapTileType;
import no.ntnu.tdt4240.g25.td.service.AssetService;

public class MapRenderSystem extends BaseSystem {

    @Wire
    private MapGrid mapGrid;
    @Wire
    private AssetService assetService;
    @Wire
    private SpriteBatch spriteBatch;
    private MyCameraSystem cameraSystem;

    private ArrayMap<MapTileType, TextureRegion> tileTextures;


    @Override
    protected void initialize() {
        tileTextures = new ArrayMap<>();
        TextureAtlas atlas = assetService.getAtlas(AssetService.TerrainAtlas.SUMMER.path);
        for (MapTileType type : MapTileType.values()) {
            tileTextures.put(type, atlas.findRegion(type.regionName));
        }
    }

    @Override
    protected void processSystem() {
        spriteBatch.setProjectionMatrix(cameraSystem.viewport.getCamera().combined);
        spriteBatch.begin();
        for (MapTile tile : mapGrid) {
            TextureRegion texture = tileTextures.get(tile.getTile());
            spriteBatch.draw(texture, tile.getX(), tile.getY(), 1, 1);
        }
        spriteBatch.end();
    }
}