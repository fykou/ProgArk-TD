package no.ntnu.tdt4240.g25.td.model.entity.systems.render;

import com.artemis.BaseSystem;
import com.artemis.annotations.Wire;
import com.artemis.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.BuildMenu;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.UpgradeMenu;
import no.ntnu.tdt4240.g25.td.model.entity.systems.MyCameraSystem;
import no.ntnu.tdt4240.g25.td.service.AssetService;
import no.ntnu.tdt4240.g25.td.utils.MyShapeRenderer;

public class MenuRenderSystem extends BaseSystem {

    @Wire
    private SpriteBatch batch;
    @Wire
    private MyShapeRenderer shapeRenderer;
    @Wire
    private AssetService assetService;

    private BuildMenu buildMenu;
    private UpgradeMenu upgradeMenu;
    private MyCameraSystem cameraSystem;

    @Override
    protected void processSystem() {
        if (buildMenu.pending) {
            drawOverlay();
            drawBuildMenu();
        }
        if (upgradeMenu.pending) {
            drawOverlay();
            drawUpgradeMenu();
        }
    }

    private void drawBuildMenu() {
        batch.setProjectionMatrix(cameraSystem.guiViewport.getCamera().combined);
        shapeRenderer.setProjectionMatrix(cameraSystem.guiViewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.rect(buildMenu.type1Button.x, buildMenu.type1Button.y, buildMenu.type1Button.width, buildMenu.type1Button.height);
        shapeRenderer.rect(buildMenu.type2Button.x, buildMenu.type2Button.y, buildMenu.type2Button.width, buildMenu.type2Button.height);
        shapeRenderer.rect(buildMenu.cancelButton.x, buildMenu.cancelButton.y, buildMenu.cancelButton.width, buildMenu.cancelButton.height);
        shapeRenderer.end();
    }

    private void drawUpgradeMenu() {

    }

    private void drawOverlay() {
        shapeRenderer.setProjectionMatrix(cameraSystem.viewport.getCamera().combined);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 0.4f);
        shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }
}
