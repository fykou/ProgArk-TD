package no.ntnu.tdt4240.g25.td.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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
    private final GameController.ViewCallback viewCallback;

    // UI elements
    private final TextButton upgradeButton = new TextButton("Upgrade", skin);
    private final TextButton buy1Button = new TextButton("Buy", skin, "emphasis");
    private final TextButton buy2Button = new TextButton("Buy", skin, "emphasis");
    private final Window newTowerWindow = new Window("", skin);
    private final Window upgradeWindow = new Window("Upgrade tower", skin);

    private final Label upgradeCostLabel = new Label("", skin);

    private final Image type1Image = new Image(Assets.getInstance().getAtlasRegion(TowerType.TYPE_1.atlasPath, TowerLevel.MK1.name()));
    private final Image type2Image = new Image(Assets.getInstance().getAtlasRegion(TowerType.TYPE_2.atlasPath, TowerLevel.MK1.name()));
    private final Image upgradeImage = new Image(Assets.getInstance().getAtlasRegion(TowerType.TYPE_1.atlasPath, TowerLevel.MK1.name()));

    // Top bar game stats
    // Use buttons bcs background color
    private final TextButton numLivesLeft = new TextButton("", skin);
    private final TextButton numCashTotal = new TextButton("", skin);
    private final TextButton waveTimer = new TextButton("", skin);
    private final TextButton waveScore = new TextButton("", skin);

    private WaveComponent wave;
    private PlayerComponent player;

    public GameView(SpriteBatch batch, GameController.ViewCallback viewCallback) {
        super(viewport, batch);
        this.viewCallback = viewCallback;
        buildTopBar();
        buildBuyDialogue();
        buildUpgradeDialogue();

    }

    /**
     * Override the corresponding method in AbstractView -> Stage in order to
     * capture clicks not handled by the ui, and pass them to the controller
     * so the game world can be updated accordingly.
     * @param screenX x coordinate of the click
     * @param screenY y coordinate of the click
     * @param pointer the pointer that was used to click
     * @param button the button that was used to click
     * @return true if the click was handled by the ui, false otherwise
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        boolean handled = super.touchUp(screenX, screenY, pointer, button);
        if (!handled) {
            viewCallback.onWorldClick(screenX, screenY);
            return true;
        }
        return false;
    }
    /**
     * Builds the top bar with game stats
     */
    private void buildTopBar() {
        Table topBar = new Table(skin);
        topBar.setFillParent(true);
        topBar.align(Align.top);

        numLivesLeft.getLabel().setText("");
        numCashTotal.getLabel().setText("");
        waveScore.getLabel().setText("");
        waveTimer.getLabel().setText("");

        numLivesLeft.setTouchable(Touchable.disabled);
        numCashTotal.setTouchable(Touchable.disabled);
        waveScore.setTouchable(Touchable.disabled);
        waveTimer.setTouchable(Touchable.disabled);

        numCashTotal.getLabel().setFontScale(1.5f);
        numLivesLeft.getLabel().setFontScale(1.5f);
        waveScore.getLabel().setFontScale(1.5f);
        waveTimer.getLabel().setFontScale(1.5f);

        topBar.add(waveScore).align(Align.center).pad(10);
        topBar.add(waveTimer).align(Align.center).pad(10);
        topBar.add(numCashTotal).align(Align.center).pad(10);
        topBar.add(numLivesLeft).align(Align.center).pad(10);
        this.addActor(topBar);
    }


    /**
     * Builds the buy dialogue, should only be called once
     */
    private void buildBuyDialogue() {

        final TextButton cancelButton = new TextButton("Cancel", skin, "emphasis");

        final Table buyTable1 = new Table(skin);
        final Table buyTable2 = new Table(skin);
        final Table mainTable = new Table(skin);

        int buttonWidth = 250;
        int buttonHeight = 90;
        buy1Button.getLabel().setFontScale(1.5f);
        buy2Button.getLabel().setFontScale(1.5f);
        cancelButton.getLabel().setFontScale(1.5f);

        buyTable1.add(new Label("Single target", skin)).align(Align.center).pad(10);
        buyTable1.row();
        buyTable1.add(type1Image).size(200, 200).align(Align.center).pad(10);
        buyTable1.row();
        buyTable1.add(new Label("Cost: " + TowerType.TYPE_1.baseCost, skin)).size(buttonWidth, buttonHeight).align(Align.center).pad(10);
        buyTable1.row();
        buyTable1.add(buy1Button).size(250, 90).align(Align.center).pad(10);
        buyTable1.row();


        buyTable2.add(new Label("AoE", skin)).align(Align.center).pad(10);
        buyTable2.row();
        buyTable2.add(type2Image).size(200, 200).align(Align.center).pad(10);
        buyTable2.row();
        buyTable2.add(new Label("Cost: " + TowerType.TYPE_2.baseCost, skin)).size(buttonWidth, buttonHeight).align(Align.center).pad(10);
        buyTable2.row();
        buyTable2.add(buy2Button).size(buttonWidth, 90).align(Align.center).pad(10);
        buyTable2.row();

        mainTable.add(buyTable1).align(Align.center).pad(10);
        mainTable.add(buyTable2).align(Align.center).pad(10);
        mainTable.row();
        mainTable.add(cancelButton).size(buttonWidth, buttonHeight).colspan(2).align(Align.center).pad(10);

        upgradeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                viewCallback.onUpgradeButtonClicked();
            }
        });

        buy1Button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                viewCallback.onBuy1ButtonClicked();
                newTowerWindow.setVisible(false);
            }
        });

        buy2Button.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                viewCallback.onBuy2ButtonClicked();
                newTowerWindow.setVisible(false);
            }
        });

        cancelButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                newTowerWindow.setVisible(false);
            }
        });
        newTowerWindow.add(mainTable).align(Align.center);
        newTowerWindow.pack();
        newTowerWindow.setMovable(false);
        newTowerWindow.setModal(true);
        // center the window
        newTowerWindow.setPosition(GameView.viewport.getWorldWidth() / 2 - newTowerWindow.getWidth() / 2,
                GameView.viewport.getWorldHeight() / 2 - newTowerWindow.getHeight() / 2);
        newTowerWindow.setVisible(false);
        this.addActor(newTowerWindow);
    }

    /**
     * Creates the upgrade window, should be called only once.
     */
    private void buildUpgradeDialogue() {
        final Table upgradeTable = new Table();
        final TextButton cancelButton = new TextButton("Cancel", skin);

        int buttonWidth = 250;
        int buttonHeight = 90;

        upgradeCostLabel.setFontScale(1.5f);
        cancelButton.getLabel().setFontScale(1.5f);

        upgradeTable.add(upgradeImage).size(200, 200).align(Align.center).pad(10);
        upgradeTable.row();
        upgradeTable.add(upgradeCostLabel).align(Align.center).pad(10);
        upgradeTable.row();
        upgradeTable.add(upgradeButton).size(buttonWidth, buttonHeight).align(Align.center).pad(10);
        upgradeTable.row();
        upgradeTable.add(cancelButton).size(buttonWidth, buttonHeight).align(Align.center).pad(10);

        upgradeButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                viewCallback.onUpgradeButtonClicked();
                upgradeWindow.setVisible(false);
            }
        });

        cancelButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                upgradeWindow.setVisible(false);
            }
        });

        upgradeTable.setFillParent(true);
        upgradeWindow.setMovable(false);
        upgradeWindow.setModal(true);
        upgradeWindow.add(upgradeTable).align(Align.center);
        upgradeWindow.pack();
        // center the window
        upgradeWindow.setPosition(GameView.viewport.getWorldWidth() / 2 - newTowerWindow.getWidth() / 2,
                GameView.viewport.getWorldHeight() / 2 - newTowerWindow.getHeight() / 2);
        upgradeWindow.setVisible(false);
        this.addActor(upgradeWindow);
    }


    public class GameViewCallback {
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
                text = String.format(Locale.ENGLISH, "   Wave:   \n%.1f", time);
            } else {
                text = String.format(Locale.ENGLISH, "Next wave:\n%.1f", time);
            }
            waveTimer.getLabel().setText(text);
        }
        public void toggleUpgradeWindow(int cash, TowerType type, TowerLevel level) {
            TowerLevel nextLevel = level.nextLevel();
            if (nextLevel == null) {
                return;
            }
            upgradeImage.setDrawable(new TextureRegionDrawable(Assets.getInstance().getAtlasRegion(type.atlasPath, nextLevel.name())));
            upgradeWindow.setVisible(!upgradeWindow.isVisible());
            upgradeCostLabel.setText("Cost: " + type.baseCost * level.level);
            upgradeButton.setDisabled(type.baseCost * level.level > cash);
        }
        public void toggleNewTowerWindow(int cash) {
            newTowerWindow.setVisible(!newTowerWindow.isVisible());
            buy1Button.setDisabled(cash < TowerType.TYPE_1.baseCost);
            buy2Button.setDisabled(cash < TowerType.TYPE_2.baseCost);
        }
    }

    public GameViewCallback getTopBarCallback() {
        return new GameViewCallback();
    }
}
