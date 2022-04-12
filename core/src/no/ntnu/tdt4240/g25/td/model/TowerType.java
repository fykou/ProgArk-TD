package no.ntnu.tdt4240.g25.td.model;

public enum TowerType {

    TYPE_1(5000, 0,
            6, 12, 18, 24, // damage for level 1, 2, 3, 4
            2f, 2.5f, 3f, 3.5f,  // firerate for level 1, 2, 3, 4
            "towers/TYPE_1.atlas"), // path to atlas in assets
    TYPE_2(5000, 50, 3, 6, 9, 12,
            2f, 2.5f, 3f, 3.5f,
            "towers/TYPE_2.atlas");

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
            int range, int splashRadius, int mk1Damage, int mk2Damage, int mk3Damage, int mk4Damage,
            float mk1FireRate, float mk2FireRate, float mk3FireRate, float mk4FireRate,
            String atlasPath) {
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

