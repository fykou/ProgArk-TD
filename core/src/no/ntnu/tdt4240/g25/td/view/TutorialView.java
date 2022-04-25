package no.ntnu.tdt4240.g25.td.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import no.ntnu.tdt4240.g25.td.asset.Assets;
import no.ntnu.tdt4240.g25.td.asset.Audio;
import no.ntnu.tdt4240.g25.td.asset.GameMusic;
import no.ntnu.tdt4240.g25.td.controller.TutorialScreen;

public class TutorialView extends AbstractView {

    private final String tutorialString = "" +
            "Each round consists of a \nBuild-Phase and Wave-Phase.\n\n" +
            "Build-Phase: Buy and upgrade \nturrets. Turrets will shoot the \nenemy mobs!\n\n" +
            "Wave-Phase: Enemy mobs will \nspawn. Eliminate them before \nthey reach the end of the path!\n\n\n" +
            "Strategy and planning pays off. \nSurvive as many waves as you \ncan!";

    private final Skin skin = Assets.getInstance().getSkin();
    private final TextButton backButton = new TextButton("Back to menu", skin);
    private final Table table = new Table();

    private final TutorialScreen.ViewCallbackHandler viewCallback;

    public TutorialView(SpriteBatch batch, TutorialScreen.ViewCallbackHandler viewCallback) {
        super(viewport, batch);
        Gdx.input.setInputProcessor(this);
        this.viewCallback = viewCallback;
        table.setFillParent(true);
        table.align(Align.center).add();
        Window tutorialWindow = new Window("Tutorial", skin);
        tutorialWindow.getTitleLabel().setAlignment(Align.center);
        tutorialWindow.getTitleLabel().setText("HOW TO PLAY: ");
        tutorialWindow.setMovable(false);
        tutorialWindow.add(new Label(tutorialString, skin)).align(Align.center);
        table.add(tutorialWindow).align(Align.center).row();
        table.add(backButton).align(Align.center);
        addActor(table);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                viewCallback.onBackToMenuButtonClicked();
        }
        });
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void resize(int width, int height) {
        this.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        Audio.stopMusic();
    }

    @Override
    public void resume() {
        Audio.playMusic(GameMusic.MENU);
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        Audio.stopMusic();
    }


}
