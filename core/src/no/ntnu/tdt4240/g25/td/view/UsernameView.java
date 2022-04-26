package no.ntnu.tdt4240.g25.td.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import no.ntnu.tdt4240.g25.td.asset.Assets;
import no.ntnu.tdt4240.g25.td.asset.Audio;
import no.ntnu.tdt4240.g25.td.asset.SoundFx;
import no.ntnu.tdt4240.g25.td.controller.UsernameScreen;

public class UsernameView extends AbstractView {

    private final Skin skin = Assets.getInstance().getSkin();
    private final TextButton playButton = new TextButton("Play", skin);
    private final TextField userInput;
    private final Table table = new Table();
    private String playerName;

    private final UsernameScreen.ViewCallbackHandler viewCallback;

    public UsernameView(SpriteBatch batch, UsernameScreen.ViewCallbackHandler viewCallback) {
        super(viewport, batch);
        Gdx.input.setInputProcessor(this);
        this.viewCallback = viewCallback;
        userInput = new TextField("", skin);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                playerName = userInput.getText();
                setPlayerName();
            }
        });
    }

    public void setPlayerName(){
        Audio.playFx(SoundFx.TOUCH);
        viewCallback.setPlayerNameOnFirebaseObject(playerName);
        viewCallback.toMenu();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
        // NEW table
        Label title = new Label("Enter username", skin, "default");
        title.setFontScale(3);
        table.setFillParent(true);
        table.add(title).padBottom(50).colspan(2).row();
        table.add(userInput).align(Align.center).size(400, 75).padBottom(25).colspan(2).row();
        table.add(playButton).size(350, 90).colspan(2).row();

        // Adjust sizes
        playButton.getLabel().setFontScale(2);

        getRoot().addActor(table);
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
