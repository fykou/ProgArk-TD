package no.ntnu.tdt4240.g25.td.model;

public enum MobType {
    NORMAL(10, 1f, "enemies/normal.atlas"),
    TANK(40, .75f, "enemies/tank.atlas");

    public final int baseHealth;
    public final String atlasPath;
    public final float baseSpeed;

    MobType(int baseHealth, float baseSpeed, String atlasPath) {
        this.baseHealth = baseHealth;
        this.baseSpeed = baseSpeed;
        this.atlasPath = atlasPath;
    }
}
