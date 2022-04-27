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
import no.ntnu.tdt4240.g25.td.controller.UsernameController;

public class UsernameView extends AbstractView {

    private final Skin skin = Assets.getInstance().getSkin();
    private final TextButton playButton = new TextButton("Play", skin);
    private final Label errorLabel = new Label("", skin);
    private final TextField userInput;
    private final Table table = new Table(skin);
    private String playerName;

    private final UsernameController.ViewCallbackHandler viewCallback;

    public UsernameView(SpriteBatch batch, UsernameController.ViewCallbackHandler viewCallback) {
        super(viewport, batch);
        Gdx.input.setInputProcessor(this);
        this.viewCallback = viewCallback;
        userInput = new TextField("", skin);

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                if (userInput.getText().length() <= 0) {
                    showError("Please enter a username");
                    return;
                }
                playerName = userInput.getText();
                userInput.clearSelection();
                setPlayerName();
            }
        });
        buildTable();
    }

    private void setPlayerName() {
        Audio.playFx(SoundFx.TOUCH);
        viewCallback.setPlayerNameOnFirebaseObject(playerName);
        viewCallback.toMenu();
    }

    private void buildTable() {
        Label title = new Label("Enter username", skin, "default");

        table.setFillParent(true);
        table.add(title).padBottom(50).colspan(2).row();
        table.add(errorLabel).padBottom(50).colspan(2).align(Align.center).row();
        table.add(userInput).align(Align.center).size(400, 75).padBottom(25).colspan(2).row();
        table.add(playButton).size(350, 90).colspan(2).row();

        userInput.setTextFieldListener((textField, c) -> errorLabel.setVisible(false));

        // Adjust sizes
        playButton.getLabel().setFontScale(2);
        title.setFontScale(3);
        errorLabel.setFontScale(2);
        getRoot().addActor(table);
    }
    private void showError(String error) {
        errorLabel.setText(error);
        errorLabel.setVisible(true);
    }
}
