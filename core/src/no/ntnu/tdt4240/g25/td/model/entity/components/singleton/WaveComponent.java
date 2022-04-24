package no.ntnu.tdt4240.g25.td.model.entity.components.singleton;

import com.artemis.Component;

import net.mostlyoriginal.api.Singleton;

@Singleton
public class WaveComponent extends Component {
    public float time = 0;
    public int numberOfWaves = 0;
    public boolean active = false;
    public boolean enemiesAlive = false;
    public int remainingNormalEnemies = 0;
    public int remainingTankEnemies = 0;
}
