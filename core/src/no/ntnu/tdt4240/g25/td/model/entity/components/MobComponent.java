package no.ntnu.tdt4240.g25.td.model.entity.components;

import com.artemis.Component;

public class MobComponent extends Component {

    public static int NORMAL_MOB = 0;
    public static int TANK_MOB = 1;
    public static int BOSS_MOB = 2;

    public int mobType;
    public int health;
    public int maxHealth;

    public MobComponent(int mobType, int health, int maxHealth) {
        this.mobType = mobType;
        this.health = health;
        this.maxHealth = maxHealth;
    }

    public MobComponent(int mobType) {
        this(mobType, 100, 100);
    }

    public MobComponent() {
        this(NORMAL_MOB, 100, 100);
    }
}
