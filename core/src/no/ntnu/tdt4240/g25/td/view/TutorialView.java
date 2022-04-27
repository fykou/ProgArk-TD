package no.ntnu.tdt4240.g25.td.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import no.ntnu.tdt4240.g25.td.asset.Assets;
import no.ntnu.tdt4240.g25.td.asset.Audio;
import no.ntnu.tdt4240.g25.td.asset.GameMusic;
import no.ntnu.tdt4240.g25.td.controller.TutorialController;

public class TutorialView extends AbstractView {

    private final String tutorialString = "" +
            "The waves never stop \ncoming!\n\n" +
            "Continously buy or upgrade \n" +
            "your turrets. Click on a \n" +
            "turret to upgrade.\n\n " +
            "Eliminate the enemy mobs\n" +
            "before they reach the end \n" +
            "of the path! \n\n\n" +
            "Strategy and planning pays \n" +
            "off. Survive as many waves \n" +
            "as you can, and climb the \n" +
            "global leaderboard!";

    private final Skin skin = Assets.getInstance().getSkin();
    private final TextButton backButton = new TextButton("Back to menu", skin);
    private final Table table = new Table();

    private final TutorialController.ViewCallbackHandler viewCallback;

    public TutorialView(SpriteBatch batch, TutorialController.ViewCallbackHandler viewCallback) {

        super(viewport, batch);
        Gdx.input.setInputProcessor(this);
        this.viewCallback = viewCallback;
        table.setFillParent(true);
        table.align(Align.center).add().row();
        Window tutorialWindow = new Window("Tutorial", skin);
        tutorialWindow.getTitleLabel().setAlignment(Align.center);
        tutorialWindow.getTitleLabel().setFontScale(1.5f);
        tutorialWindow.getTitleLabel().setText("HOW TO PLAY: ");
        tutorialWindow.setMovable(false);
        Label tutorialStringContent = new Label(tutorialString, skin);
        tutorialStringContent.setFontScale(1.7f);
        tutorialStringContent.setAlignment(Align.center);
        tutorialWindow.add(tutorialStringContent).align(Align.center);
        table.add(tutorialWindow).size(550, Gdx.graphics.getHeight() / 2.5f).align(Align.center).padBottom(5).row();
        table.add(new Label("", skin, "default")).align(Align.center).row();
        backButton.getLabel().setFontScale(2);
        table.add(backButton).size(350, 90).align(Align.center).pad(10).row();
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
