package no.ntnu.tdt4240.g25.td.view;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import java.util.Locale;

import no.ntnu.tdt4240.g25.td.TdGame;
import no.ntnu.tdt4240.g25.td.asset.Assets;
import no.ntnu.tdt4240.g25.td.controller.GameController;
import no.ntnu.tdt4240.g25.td.controller.GameOverController;

public class GameOverView extends AbstractView {

    private final Skin skin = Assets.getInstance().getSkin();
    private final TextButton backToMenu = new TextButton("Back to menu", skin);
    private final TextButton submitScore = new TextButton("Submit", skin);
    private final GameOverController.ViewCallbackHandler controller;
    private final Label submittedScore = new Label("", skin);

    public GameOverView(SpriteBatch batch, GameOverController.ViewCallbackHandler controller) {
        super(viewport, batch);
        this.controller = controller;
        buildTable();
    }

    public void buildTable() {

        String message = String.format(Locale.ENGLISH, "You survived %d waves!", controller.getScore());

        final Table table = new Table(skin);
        final Label title = new Label("Game Over", skin, "title");
        final Label score = new Label(message, skin);
        table.setFillParent(true);
        table.add(title).padBottom(50).colspan(2).align(Align.center).row();
        table.add(score).align(Align.center).size(400, 90).align(Align.center).padBottom(25).row();
        table.add(submittedScore).align(Align.center).size(350, 90).padBottom(25).colspan(2).row();
        table.add(submitScore).align(Align.center).size(350, 90).padBottom(25).colspan(2).row();
        table.add(backToMenu).size(350, 90).colspan(2).row();

        backToMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.toMenu();
            }
        });
        submitScore.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                controller.submitScore();
            }
        });

        // Adjust sizes
        backToMenu.getLabel().setFontScale(2);
        submitScore.getLabel().setFontScale(2);
        submittedScore.setFontScale(1.f);
        score.setFontScale(1.5f);
        title.setFontScale(2f);
        addActor(table);
    }

    public void submitScoreSuccessful() {
        submittedScore.setText("Score submitted!");
    }

}
