package no.ntnu.tdt4240.g25.td.view;

public interface View {


    void show();

    void resize(int width, int height);

    void pause();

    void resume();

    void hide();

    void render(float delta);
}
