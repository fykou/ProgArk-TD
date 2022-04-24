package no.ntnu.tdt4240.g25.td.model.entity.systems.render;

import com.artemis.Aspect;
import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.artemis.annotations.Wire;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ArrayMap;

import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TowerComponent;
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
    private SpriteBatch batch;

    private MyCameraSystem cameraSystem;

    private ArrayMap<MapTileType, TextureRegion> tileTextures;

    EntitySubscription towerSubscription;
    ComponentMapper<PositionComponent> mPosition;
    TextureRegion towerBase;



    @Override
    protected void initialize() {
        tileTextures = new ArrayMap<>();
        TextureAtlas atlas = assetService.getAtlas(AssetService.TerrainAtlas.SUMMER.path);
        for (MapTileType type : MapTileType.values()) {
            tileTextures.put(type, atlas.findRegion(type.regionName));
        }
        towerSubscription = world.getAspectSubscriptionManager().get(Aspect.all(TowerComponent.class, PositionComponent.class));
        towerBase = assetService.getAtlas(AssetService.Atlas.BUILDSPOTS.path).findRegion("single");
    }

    @Override
    protected void processSystem() {
        IntBag towerIds = towerSubscription.getEntities();
        batch.setProjectionMatrix(cameraSystem.viewport.getCamera().combined);
        batch.enableBlending();
        batch.begin();
        for (MapTile tile : mapGrid) {
            TextureRegion texture = tileTextures.get(tile.getTile());
            batch.draw(texture, tile.getX(), tile.getY(), 1, 1);
        }
        for (int i = 0; i < towerIds.size(); i++) {
            PositionComponent tower = mPosition.get(towerIds.get(i));
            batch.draw(towerBase, tower.get().x -.5f, tower.get().y - .5f, .5f, .5f, 1, 1, 1, 1, 0);
        }
        batch.end();
    }
}