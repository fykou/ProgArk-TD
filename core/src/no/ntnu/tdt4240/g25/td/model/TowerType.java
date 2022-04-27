package no.ntnu.tdt4240.g25.td.model;

public enum TowerType {

    TYPE_1(25,2, 0,
            6, 7, 8, 9, // damage for level 1, 2, 3, 4
            1f, 1.5f, 2f, 2.5f,  // firerate for level 1, 2, 3, 4
            "towers/TYPE_1.atlas"), // path to atlas in assets
    TYPE_2(35,2, 1,
            2, 3, 4, 5,
            1.33f, 1.5f, 1.8f, 2.1f,
            "towers/TYPE_2.atlas");

    public final int baseCost;
    public final int range;
    public final int splashRadius;
    public final int mk1Damage;
    public final int mk2Damage;
    public final int mk3Damage;
    public final int mk4Damage;
    public final float mk1FireRate;
    public final float mk2FireRate;
    public final float mk3FireRate;
    public final float mk4FireRate;
    public final String atlasPath;


    TowerType(
            int baseCost, int range, int splashRadius, int mk1Damage, int mk2Damage, int mk3Damage, int mk4Damage,
            float mk1FireRate, float mk2FireRate, float mk3FireRate, float mk4FireRate,
            String atlasPath) {
        this.baseCost = baseCost;
        this.range = range;
        this.splashRadius = splashRadius;
        this.mk1Damage = mk1Damage;
        this.mk1FireRate = mk1FireRate;
        this.mk2Damage = mk2Damage;
        this.mk2FireRate = mk2FireRate;
        this.mk3Damage = mk3Damage;
        this.mk3FireRate = mk3FireRate;
        this.mk4Damage = mk4Damage;
        this.mk4FireRate = mk4FireRate;
        this.atlasPath = atlasPath;
    }
}

