package no.ntnu.tdt4240.g25.td.controller;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;

import no.ntnu.tdt4240.g25.td.TdConfig;
import no.ntnu.tdt4240.g25.td.TdGame;
import no.ntnu.tdt4240.g25.td.asset.Assets;
import no.ntnu.tdt4240.g25.td.asset.Audio;
import no.ntnu.tdt4240.g25.td.asset.GameMusic;
import no.ntnu.tdt4240.g25.td.asset.SoundFx;
import no.ntnu.tdt4240.g25.td.view.AbstractView;
import no.ntnu.tdt4240.g25.td.view.TutorialView;

public class TutorialController extends AbstractController {


    private final TutorialView view;
    private final Sound sound;


    public TutorialController(TdGame game, Screen parent) {
        super(game, parent);
        this.view = new TutorialView(game.getBatch(), new ViewCallbackHandler());
        this.sound = Assets.getInstance().getSound(SoundFx.TOUCH);
    }

    @Override
    protected AbstractView getView() {
        return view;
    }

    @Override
    public void show() {
        super.show();
        Audio.playMusic(GameMusic.MENU);
    }

    public class ViewCallbackHandler {
        public void onBackToMenuButtonClicked() {
            if(TdConfig.get().getSfxEnabled()){
                long id = sound.play(TdConfig.get().getVolume());
                sound.setLooping(id,false);
            }
            game.setScreen(parent);
            TutorialController.this.dispose();
        }
    }

}
