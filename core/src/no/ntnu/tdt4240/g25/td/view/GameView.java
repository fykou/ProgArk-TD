package no.ntnu.tdt4240.g25.td.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import java.util.Locale;

import no.ntnu.tdt4240.g25.td.asset.Assets;
import no.ntnu.tdt4240.g25.td.controller.GameController;
import no.ntnu.tdt4240.g25.td.model.TowerLevel;
import no.ntnu.tdt4240.g25.td.model.TowerType;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.PlayerComponent;
import no.ntnu.tdt4240.g25.td.model.entity.components.singleton.WaveComponent;

public class GameView extends AbstractView {

    private final Skin skin = Assets.getInstance().getSkin();
    private final GameController.ViewCallbackHandler viewCallback;
    private final Table upgradeTable = new Table(skin);
    private final Table buyTable1 = new Table(skin);
    private final Table buyTable2 = new Table(skin);
    private final Table mainTable = new Table(skin);
    private final Image type1Image = new Image(Assets.getInstance().getAtlasRegion(TowerType.TYPE_1.atlasPath, TowerLevel.MK1.name()));
    private final Image type2Image = new Image(Assets.getInstance().getAtlasRegion(TowerType.TYPE_2.atlasPath, TowerLevel.MK1.name()));
    private boolean openModal = false;

    // Top bar game stats
    // Use buttons bcs background color
    private final TextButton numLivesLeft = new TextButton("", skin);
    private final TextButton numCashTotal = new TextButton("", skin);
    private final TextButton waveTimer = new TextButton("", skin);
    private final TextButton waveScore = new TextButton("", skin);

    private WaveComponent wave;
    private PlayerComponent player;

    public GameView(SpriteBatch batch, GameController.ViewCallbackHandler viewCallback) {
        super(viewport, batch);
        this.viewCallback = viewCallback;
        buildBuyDialogue();
        buildTopBar();
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    private void buildTopBar() {
        Table topBar = new Table(skin);
        topBar.setFillParent(true);
        topBar.align(Align.top);

        numLivesLeft.getLabel().setText("");
        numCashTotal.getLabel().setText("");
        waveScore.getLabel().setText("");
        waveTimer.getLabel().setText("");

        numCashTotal.getLabel().setFontScale(1.5f);
        numLivesLeft.getLabel().setFontScale(1.5f);
        waveScore.getLabel().setFontScale(1.5f);
        waveTimer.getLabel().setFontScale(1.5f);

        topBar.add(waveScore).align(Align.center).pad(10);
        topBar.add(waveTimer).align(Align.center).pad(10);
        topBar.add(numCashTotal).align(Align.center).pad(10);
        topBar.add(numLivesLeft).align(Align.center).pad(10);
        topBar.setTouchable(Touchable.disabled);
        this.addActor(topBar);
    }

    private void buildBuyDialogue() {

        final TextButton upgradeButton = new TextButton("Upgrade", skin);
        final TextButton buy1Button = new TextButton("Buy", skin);
        final TextButton buy2Button = new TextButton("Buy", skin);
        final TextButton cancelButton = new TextButton("Cancel", skin);

        upgradeTable.setFillParent(true);
        upgradeTable.align(Align.top);
        upgradeTable.setTouchable(Touchable.disabled);

        buyTable1.setFillParent(true);
        buyTable1.align(Align.top);
        buyTable1.setTouchable(Touchable.disabled);

        buyTable2.setFillParent(true);
        buyTable2.align(Align.top);
        buyTable2.setTouchable(Touchable.disabled);

        mainTable.setFillParent(true);
        mainTable.align(Align.top);
        mainTable.setTouchable(Touchable.disabled);

        upgradeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                viewCallback.onUpgradeButtonClicked();
            }
        });

        buy1Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                viewCallback.onBuy1ButtonClicked();
            }
        });

        buy2Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                viewCallback.onBuy2ButtonClicked();
            }
        });

        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                viewCallback.onCancelButtonClicked();
            }
        });

        upgradeTable.add(upgradeButton).align(Align.center).pad(10);
        upgradeTable.row();
        upgradeTable.add(cancelButton).align(Align.center).pad(10);

        buyTable1.add(buy1Button).align(Align.center).pad(10);
        buyTable1.row();
        buyTable1.add(cancelButton).align(Align.center).pad(10);

        buyTable2.add(buy2Button).align(Align.center).pad(10);
        mainTable.add(buyTable1).align(Align.center).pad(10);
        mainTable.row();
        mainTable.add(buyTable2).align(Align.center).pad(10);
        mainTable.row();
        mainTable.add(upgradeTable).align(Align.center).pad(10);

        this.addActor(mainTable);
    }


    public class TopBarCallback {
        public void setCash(int cash) {
            numCashTotal.getLabel().setText("Cash\n" + cash);
        }
        public void setLives(int lives) {
            numLivesLeft.getLabel().setText("Lives\n" + lives);
        }
        public void setWave(int wave) {
            waveScore.getLabel().setText("Waves survived\n" + wave);
        }
        public void setWaveTime(float time, boolean active) {
            String text;
            if (active) {
                text = String.format(Locale.ENGLISH,"   Wave:   \n%.1f", time);
            }
            else {
                text = String.format(Locale.ENGLISH,"Next wave:\n%.1f", time);
            }
            waveTimer.getLabel().setText(text);
        }
    }

    public TopBarCallback getTopBarCallback() {
        return new TopBarCallback();
    }
}
