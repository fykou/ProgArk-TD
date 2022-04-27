package no.ntnu.tdt4240.g25.td.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;

import no.ntnu.tdt4240.g25.td.TdGame;
import no.ntnu.tdt4240.g25.td.view.AbstractView;
import no.ntnu.tdt4240.g25.td.view.UsernameView;

public class UsernameController extends AbstractController {

    private final UsernameView view;


    public UsernameController(TdGame game, Screen parent) {
        super(game, parent);
        this.view = new UsernameView(game.getBatch(), new ViewCallbackHandler());
    }

    @Override
    protected AbstractView getView() {
        return view;
    }

    public class ViewCallbackHandler {
        public void toMenu() {
            game.setScreen(new MainMenuController(game, UsernameController.this));
            //game.setScreen(new GameOverController(game, parent, 69));
        }
        public void setPlayerNameOnFirebaseObject(String playerName) {
            game.getDb().setName(playerName);
        }
    }

}
