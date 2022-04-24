package no.ntnu.tdt4240.g25.td.model.entity.systems;

import com.artemis.BaseSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.mostlyoriginal.api.system.camera.CameraSystem;

import no.ntnu.tdt4240.g25.td.model.GameWorld;
import no.ntnu.tdt4240.g25.td.screen.GameScreen;

public class MyCameraSystem extends BaseSystem {

    public Viewport viewport;
    public Viewport guiViewport;

    public MyCameraSystem(int width, int height) {
        setupViewport(width, height);
    }

    protected void setupViewport( int width, int height) {

        OrthographicCamera camera = new OrthographicCamera();
        viewport = new FitViewport(GameWorld.GAME_WIDTH, GameWorld.GAME_HEIGHT, camera);
        viewport.update(width, height, true);

        OrthographicCamera guiCamera = new OrthographicCamera();
        guiViewport = new FitViewport(GameScreen.MENU_LOGIC_WIDTH, GameScreen.MENU_LOGIC_HEIGHT, guiCamera);
        guiViewport.update(width, height, true);
    }

    public void updateViewports(int width, int height) {
    	viewport.update(width, height, true);
    	guiViewport.update(width, height, true);
    }

    @Override
    protected void processSystem() {

    }
}
