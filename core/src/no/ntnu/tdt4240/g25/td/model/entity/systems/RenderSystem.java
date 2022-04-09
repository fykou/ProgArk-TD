package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.ComponentMapper;
import com.artemis.annotations.All;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;

import no.ntnu.tdt4240.g25.td.model.entity.components.TransformComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.TextureComponent;

@All({TransformComponent.class, TextureComponent.class})
public class RenderSystem extends SortedIteratingSystem {

    ComponentMapper<TextureComponent> spriteMapper;
    ComponentMapper<TransformComponent> positionMapper;
    private final SpriteBatch batch;
    private final OrthographicCamera cam;

    public RenderSystem(SpriteBatch batch) {
        super();
        this.batch = batch;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }


    @Override
    protected void process(int entityId) {
        TextureComponent textureComponent = spriteMapper.get(entityId);
        TransformComponent position = positionMapper.get(entityId);
        if (textureComponent.region == null) {
            return;
        }
        float width = textureComponent.region.getRegionWidth();
        float height = textureComponent.region.getRegionHeight();
        float originX = width / 2f;
        float originY = height / 2f;

        batch.draw(textureComponent.region,
                position.x - originX, position.y - originY,
                originX, originY,
                width, height,
                1, 1,
                position.rotation - 90);

    }

    @Override
    protected void begin() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(cam.combined);
        batch.enableBlending();
        batch.begin();
    }

    @Override
    protected void end() {
        batch.end();
        sortedIds.clear();
    }

    @Override
    protected void sort() {
        sortedIds.sort((o1, o2) -> Float.compare(positionMapper.get(o1).z, positionMapper.get(o2).z));
    }
}
