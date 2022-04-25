package no.ntnu.tdt4240.g25.td.model.entity.systems.render;

import com.artemis.BaseSystem;
import com.artemis.annotations.Wire;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.BuildMenu;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.UpgradeMenu;
import no.ntnu.tdt4240.g25.td.model.entity.systems.MyCameraSystem;
import no.ntnu.tdt4240.g25.td.asset.Assets;
import no.ntnu.tdt4240.g25.td.asset.Font;
import no.ntnu.tdt4240.g25.td.utils.MyShapeRenderer;

public class MenuRenderSystem extends BaseSystem {

    @Wire
    private SpriteBatch batch;
    @Wire
    private MyShapeRenderer shapeRenderer;

    private BuildMenu buildMenu;
    private UpgradeMenu upgradeMenu;
    private MyCameraSystem cameraSystem;

    private BitmapFont largeFont;
    private BitmapFont mediumFont;
    private BitmapFont smallFont;

    // Layouts for text rendering
    private GlyphLayout buyLayout;
    private GlyphLayout sellLayout;
    private GlyphLayout upgradeLayout;
    private GlyphLayout upgradeToLayout;
    private GlyphLayout cancelLayout;

    @Override
    protected void initialize() {
        largeFont = Assets.getInstance().getFont(Font.LARGE);
        mediumFont = Assets.getInstance().getFont(Font.MEDIUM);
        smallFont = Assets.getInstance().getFont(Font.SMALL);
        buyLayout = new GlyphLayout(largeFont, "BUY");
        sellLayout = new GlyphLayout(largeFont, "SELL");
        upgradeLayout = new GlyphLayout(largeFont, "UPGRADE");
        upgradeToLayout = new GlyphLayout(largeFont, "placeholder");
        cancelLayout = new GlyphLayout(largeFont, "CANCEL");
    }

    @Override
    protected void processSystem() {
        if (buildMenu.pending) {
            // set layouts for text rendering
            drawOverlay();
            drawBuildMenu();
        }
        if (upgradeMenu.pending) {
            drawOverlay();
            drawUpgradeMenu();
        }
    }

    private void drawBuildMenu() {
        shapeRenderer.setProjectionMatrix(cameraSystem.guiViewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0, 0, 0, 1);
        shapeRenderer.roundedRect(buildMenu.type1Button.x, buildMenu.type1Button.y, buildMenu.type1Button.width, buildMenu.type1Button.height, 15);
        shapeRenderer.roundedRect(buildMenu.type2Button.x, buildMenu.type2Button.y, buildMenu.type2Button.width, buildMenu.type2Button.height, 15);
        shapeRenderer.roundedRect(buildMenu.cancelButton.x, buildMenu.cancelButton.y, buildMenu.cancelButton.width, buildMenu.cancelButton.height, 15);
        shapeRenderer.end();
        batch.setProjectionMatrix(cameraSystem.guiViewport.getCamera().combined);
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
