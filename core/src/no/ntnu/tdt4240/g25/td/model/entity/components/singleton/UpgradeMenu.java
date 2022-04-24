package no.ntnu.tdt4240.g25.td.model.entity.components.singleton;

import com.artemis.Component;
import com.artemis.annotations.EntityId;
import com.badlogic.gdx.math.Rectangle;

import net.mostlyoriginal.api.Singleton;

import no.ntnu.tdt4240.g25.td.screen.GameScreen;

@Singleton
public class UpgradeMenu extends Component {
    public int tower = -1;
    public boolean pending = false;
    public Rectangle upgradeButton;
    public Rectangle sellButton;
    public Rectangle cancelButton;

    public UpgradeMenu() {
        upgradeButton = new Rectangle(
                0, 0, GameScreen.MENU_LOGIC_WIDTH / 4f, GameScreen.MENU_LOGIC_HEIGHT / 4f);
        sellButton = new Rectangle(
                0, 0, GameScreen.MENU_LOGIC_WIDTH / 4f, GameScreen.MENU_LOGIC_HEIGHT / 4f);
        cancelButton = new Rectangle(
                0, 0, GameScreen.MENU_LOGIC_WIDTH / 4f, GameScreen.MENU_LOGIC_HEIGHT / 4f);
    }

}
