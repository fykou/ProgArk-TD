package no.ntnu.tdt4240.g25.td.model.entity.systems.debug;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;

public class DebugUtils {

    private DebugUtils() {
        // no instances
    }

    public static void drawGrid(Viewport viewport, ShapeRenderer renderer, int cellSize) {

        // copy old color from renderer
        Color oldColor = new Color(renderer.getColor());
        int logicWidth = (int) viewport.getWorldWidth();
        int logicHeight = (int) viewport.getWorldHeight();
        int doubleWidth = logicWidth * 2;
        int doubleWeight = logicHeight * 2;

        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);

        renderer.setColor(Color.WHITE);

        // draw vertical lines
        for (int x = -doubleWidth; x < doubleWidth; x += cellSize) {
            renderer.line(x, -doubleWeight, x, doubleWeight);
        }

        // draw horizontal lines
        for (int y = -doubleWeight; y < doubleWeight; y += cellSize) {
            renderer.line(-doubleWidth, y, doubleWidth, y);
        }

        // draw 0/0 lines
        renderer.setColor(Color.RED);
        renderer.line(0, -doubleWeight, 0, doubleWeight);
        renderer.line(-doubleWidth, 0, doubleWidth, 0);

        // draw world bounds
        renderer.setColor(Color.GREEN);
        renderer.line(0, logicHeight, logicWidth, logicHeight);
        renderer.line(logicWidth, 0, logicWidth, logicHeight);

        renderer.end();
        renderer.setColor(oldColor);
    }
}