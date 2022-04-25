package no.ntnu.tdt4240.g25.td.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
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

public class GameView extends AbstractView {

    private final Skin skin = Assets.getInstance().getSkin();
    private final GameController.ViewCallbackHandler viewCallback;
    private final Table upgradeTable = new Table(skin);
    private final Table buyTable1 = new Table(skin);
    private final Table buyTable2 = new Table(skin);
    private final Table mainTable = new Table(skin);
    private final TextButton upgradeButton = new TextButton("Upgrade", skin);
    private final TextButton buy1Button = new TextButton("Buy", skin);
    private final TextButton buy2Button = new TextButton("Buy", skin);
    private final TextButton cancelButton = new TextButton("Cancel", skin);
    private final Image type1Image = new Image(Assets.getInstance().getAtlasRegion(TowerType.TYPE_1.atlasPath, TowerLevel.MK1.name()));
    private final Image type2Image = new Image(Assets.getInstance().getAtlasRegion(TowerType.TYPE_2.atlasPath, TowerLevel.MK1.name()));

    private boolean openModal = false;

    public GameView(SpriteBatch batch, GameController.ViewCallbackHandler viewCallback) {
        super(viewport, batch);
        this.viewCallback = viewCallback;
        buildBuyDialogue();
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


    public void triggerBuyDialogue(float tileX, float tileY, int cash) {
        if (openModal) {
            toggleModal();
        }
        if (cash < 25) {
            buy1Button.setDisabled(true);
        }
        if (cash < 35) {
            buy2Button.setDisabled(true);
        }
        addActor(mainTable);
        mainTable.setPosition(viewport.getWorldWidth()/2 - mainTable.getWidth()/2, viewport.getWorldHeight()/2 - mainTable.getHeight()/2);

        buy1Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                viewCallback.getWorld().getTowerFactory().create(tileX, tileY, TowerType.TYPE_1, TowerLevel.MK1);
                toggleModal();
                buy1Button.removeListener(this);
            }
        });
        buy2Button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                viewCallback.getWorld().getTowerFactory().create(tileX, tileY, TowerType.TYPE_2, TowerLevel.MK1);
                toggleModal();
                buy2Button.removeListener(this);
            }
        });
        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                toggleModal();
                cancelButton.removeListener(this);
            }
        });
        openModal = true;



    }

    public void buildBuyDialogue() {
        /* Scene2d .............*/
        buyTable1.add(new Label(String.format(Locale.ENGLISH,"Single - %d$",TowerType.TYPE_1.baseCost), skin)).expand().fill();
        buyTable1.row();
        buyTable1.add(type1Image).align(Align.left).size(180,180);
        buyTable1.row();
        buyTable1.add(buy1Button).align(Align.left).size(180,50);
        buyTable1.row();

        buyTable2.add(new Label(String.format(Locale.ENGLISH,"AoE - %d$",TowerType.TYPE_2.baseCost), skin)).expand().fill();
        buyTable2.row();
        buyTable2.add(type2Image).align(Align.right).size(180,180);
        buyTable2.row();
        buyTable2.add(buy2Button).align(Align.right).size(180,50);
        buyTable2.row();

        mainTable.add(buyTable1).align(Align.left);
        mainTable.add();
        mainTable.add(buyTable2).align(Align.right);
        mainTable.row();
        mainTable.add(cancelButton).align(Align.center).size(180,50).colspan(3);
    }

    private void toggleModal() {
        mainTable.remove();
        buy1Button.clear();
        buy2Button.clear();
        cancelButton.clear();

        /* Scene2d ... Can't remove listeners with removing text labels  ¯\_(ツ)_/¯ */
        buy1Button.setText("Buy");
        buy2Button.setText("Buy");
        cancelButton.setText("Cancel");
    }

}
