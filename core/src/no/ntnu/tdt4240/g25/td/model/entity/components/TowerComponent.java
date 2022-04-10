package no.ntnu.tdt4240.g25.td.model.entity.components;


import com.artemis.Component;

public class TowerComponent extends Component {
    public static final int MAX_LEVEL = 4;
    public static final int MIN_LEVEL = 1;

    public static final int TOWER_TYPE_SINGLE = 0;
    public static final int TOWER_TYPE_AOE = 1;

    public static final int TURN_RATE_DEGREES = 180;

    // Type 1, aka "normal" tower
    public static int TYPE_1_RANGE = 500;

    public static int TYPE_1_MK1_DAMAGE = 6;
    public static float TYPE_1_MK1_RATE = 3;
    public static int TYPE_1_MK2_DAMAGE = 12;
    public static float TYPE_1_MK2_RATE = 2;
    public static int TYPE_1_MK3_DAMAGE = 18;
    public static float TYPE_1_MK3_RATE = 1.5f;
    public static int TYPE_1_MK4_DAMAGE = 24;
    public static float TYPE_1_MK4_RATE = 1;

    // Type 2, aka "AoE" tower
    public static int TYPE_2_RANGE = 500;

    public static int TYPE_2_MK1_DAMAGE = 3;
    public static float TYPE_2_MK1_RATE = 3;
    public static int TYPE_2_MK2_DAMAGE = 6;
    public static float TYPE_2_MK2_RATE = 2;
    public static int TYPE_2_MK3_DAMAGE = 9;
    public static float TYPE_2_MK3_RATE = 1.5f;
    public static int TYPE_2_MK4_DAMAGE = 12;
    public static float TYPE_2_MK4_RATE = 1;


    public float range;
    public float damage;
    public float fireRate;
    public float firingTimer;
    public int upgradeLevel;
    public int type;

    public TowerComponent(float range, float damage, float fireRate, int type) {
        this.range = range;
        this.damage = damage;
        this.fireRate = fireRate;
        this.type = type;
        this.upgradeLevel = MIN_LEVEL;
    }

    public TowerComponent() {
        this(0, 0, 0, 0);
    }
}
