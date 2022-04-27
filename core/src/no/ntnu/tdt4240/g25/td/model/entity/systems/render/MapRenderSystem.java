package no.ntnu.tdt4240.g25.td.model.entity.systems.render;

import com.artemis.Aspect;
import com.artemis.BaseSystem;
import com.artemis.ComponentMapper;
import com.artemis.EntitySubscription;
import com.artemis.annotations.Wire;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;

import no.ntnu.tdt4240.g25.td.asset.Assets;
import no.ntnu.tdt4240.g25.td.asset.ObjectAtlas;
import no.ntnu.tdt4240.g25.td.model.entity.components.PositionComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TowerComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.MapComponent;
import no.ntnu.tdt4240.g25.td.model.entity.systems.MyCameraSystem;

public class MapRenderSystem extends BaseSystem {

    @Wire
    private SpriteBatch batch;

    private MapComponent mapComponent;
    private MyCameraSystem cameraSystem;
    private OrthoCachedTiledMapRenderer mapRenderer;

    EntitySubscription towerSubscription;
    ComponentMapper<PositionComponent> mPosition;
    TextureRegion towerBase;

    protected void initialize() {
        mapRenderer = new OrthoCachedTiledMapRenderer(mapComponent.map, 1/128f);
        mapRenderer.setView(cameraSystem.camera);
        towerSubscription = world.getAspectSubscriptionManager().get(Aspect.all(TowerComponent.class, PositionComponent.class));
        towerBase = Assets.getInstance().getAtlas(ObjectAtlas.BUILDSPOTS.path).findRegion("single");
    }

    @Override
    protected void processSystem() {
        IntBag towerIds = towerSubscription.getEntities();
        mapRenderer.render();
        batch.setProjectionMatrix(cameraSystem.camera.combined);
        batch.begin();
        for (int i = 0; i < towerIds.size(); i++) {
            int towerId = towerIds.get(i);
            PositionComponent position = mPosition.get(towerId);
            batch.draw(towerBase, position.get().x, position.get().y);
        }
        batch.end();
    }
}