package no.ntnu.tdt4240.g25.td.model;

public enum MobType {
    NORMAL(10, "enemies/normal.atlas"),
    TANK(25, "enemies/tank.atlas");

    public final int baseHealth;
    public final String atlasPath;

    MobType(int baseHealth, String atlasPath) {
        this.baseHealth = baseHealth;
        this.atlasPath = atlasPath;
    }
}
