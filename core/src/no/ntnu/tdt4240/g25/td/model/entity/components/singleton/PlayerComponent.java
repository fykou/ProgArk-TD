package no.ntnu.tdt4240.g25.td.model.entity.components.singleton;

import com.artemis.Component;

import net.mostlyoriginal.api.Singleton;

@Singleton
public class PlayerComponent extends Component {
    public int lives = 20;
    public int cash = 50;

    public void enemyKilledReward() {
        cash += 10;
    }
    public void waveClearReward() {
        cash += 50;
    }
}
