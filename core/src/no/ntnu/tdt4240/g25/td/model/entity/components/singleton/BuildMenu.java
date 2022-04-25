package no.ntnu.tdt4240.g25.td.model.entity.components.singleton;


import com.artemis.Component;
import com.badlogic.gdx.math.Rectangle;

import net.mostlyoriginal.api.Singleton;

import no.ntnu.tdt4240.g25.td.controller.GameScreen;

@Singleton
public class BuildMenu extends Component {
    public float x;
    public float y;
    public boolean pending;
    public Rectangle type1Button;
    public Rectangle type2Button;
    public Rectangle cancelButton;

    public BuildMenu() {
        pending = false;
        type1Button = new Rectangle(
                0, 0, GameScreen.MENU_LOGIC_WIDTH / 4f, GameScreen.MENU_LOGIC_HEIGHT / 4f
        ).setCenter(GameScreen.MENU_LOGIC_WIDTH * .3f, GameScreen.MENU_LOGIC_HEIGHT / 2f);
        type2Button = new Rectangle(
                0, 0, GameScreen.MENU_LOGIC_WIDTH / 4f, GameScreen.MENU_LOGIC_HEIGHT / 4f
        ).setCenter(GameScreen.MENU_LOGIC_WIDTH * .7f, GameScreen.MENU_LOGIC_HEIGHT / 2f);
        cancelButton = new Rectangle(
                0, 0, GameScreen.MENU_LOGIC_WIDTH / 3f, GameScreen.MENU_LOGIC_HEIGHT / 8f
        ).setCenter(GameScreen.MENU_LOGIC_WIDTH * .5f, GameScreen.MENU_LOGIC_HEIGHT / 5f);
    }
}
