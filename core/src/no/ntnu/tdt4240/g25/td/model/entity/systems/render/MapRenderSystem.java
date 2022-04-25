package no.ntnu.tdt4240.g25.td.model.entity.systems.render;

import com.artemis.Aspect;
import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.artemis.annotations.All;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ArrayMap;

import no.ntnu.tdt4240.g25.td.asset.Font;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TextureComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TileComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TowerComponent;
import no.ntnu.tdt4240.g25.td.model.entity.systems.MyCameraSystem;
import no.ntnu.tdt4240.g25.td.model.map.MapGrid;
import no.ntnu.tdt4240.g25.td.model.map.MapTile;
import no.ntnu.tdt4240.g25.td.model.map.MapTileType;
import no.ntnu.tdt4240.g25.td.asset.Assets;
import no.ntnu.tdt4240.g25.td.asset.ObjectAtlas;
import no.ntnu.tdt4240.g25.td.asset.TerrainAtlas;

public class MapRenderSystem extends BaseSystem {

    @Wire
    private MapGrid mapGrid;
    @Wire
    private SpriteBatch batch;

    private MyCameraSystem cameraSystem;
    private ArrayMap<MapTileType, TextureRegion> tileTextures;

    EntitySubscription towerSubscription;
    ComponentMapper<PositionComponent> mPosition;
    TextureRegion towerBase;

    protected void initialize() {
        tileTextures = new ArrayMap<>();
        TextureAtlas atlas = Assets.getInstance().getAtlas(TerrainAtlas.SUMMER.path);
        for (MapTileType type : MapTileType.values()) {
            tileTextures.put(type, atlas.findRegion(type.regionName));
        }
        towerSubscription = world.getAspectSubscriptionManager().get(Aspect.all(TowerComponent.class, PositionComponent.class));
        towerBase = Assets.getInstance().getAtlas(ObjectAtlas.BUILDSPOTS.path).findRegion("single");
    }

    @Override
    protected void processSystem() {
        IntBag towerIds = towerSubscription.getEntities();
        batch.setProjectionMatrix(cameraSystem.viewport.getCamera().combined);
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