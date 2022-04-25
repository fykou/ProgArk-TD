package no.ntnu.tdt4240.g25.td.view;

/**
 * Mimics the Screen Interface from libGDX, minus the render() method, as the
 * views will also extend Stage, providing a draw() method.
 */
public interface View {


    public void show();

    public void resize(int width, int height);

    public void pause();

    public void resume();

    public void hide();
}
