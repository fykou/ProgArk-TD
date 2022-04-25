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
import no.ntnu.tdt4240.g25.td.controller.UsernameScreen;

public class UsernameView extends AbstractView {

    private final Skin skin = Assets.getInstance().getSkin();
    private final TextButton backButton = new TextButton("Save playername", skin);
    private final TextField userInput;
    private final Table table = new Table();
    String playerName;

    private final UsernameScreen.ViewCallbackHandler viewCallback;

    public UsernameView(SpriteBatch batch, UsernameScreen.ViewCallbackHandler viewCallback) {
        super(viewport, batch);
        Gdx.input.setInputProcessor(this);
        this.viewCallback = viewCallback;
        table.setFillParent(true);
        table.align(Align.center).add();
        Window tutorialWindow = new Window("Tutorial", skin);
        tutorialWindow.getTitleLabel().setAlignment(Align.center);
        tutorialWindow.getTitleLabel().setText("USERNAME: ");
        tutorialWindow.setMovable(false);
        table.add(tutorialWindow).align(Align.center).row();
        table.add(backButton).align(Align.center);

        userInput = new TextField("", skin);
        table.add(userInput).align(Align.center);
        addActor(table);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                playerName = userInput.getText();
                System.out.println(playerName);
                highScoreWrite();
            }
        });

    }

    public void highScoreWrite(){
        viewCallback.updatePlayerNameInDb(playerName);
        System.out.println("after fb method call...");
        viewCallback.toMenu();
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

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
