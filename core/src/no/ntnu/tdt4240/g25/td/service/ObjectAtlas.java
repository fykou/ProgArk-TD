package no.ntnu.tdt4240.g25.td.service;

public enum ObjectAtlas {
    BUILDSPOTS("map/buildspots.atlas"),
    WALLS("map/walls.atlas"),
    EXPLOSION("effects/explosion.atlas"),
    SPARKS("effects/sparks.atlas"),
    BULLET("projectiles/bullet.atlas"),
    PLASMA("projectiles/plasma.atlas");

    public final String path;

    ObjectAtlas(String path) {
        this.path = path;
    }
}
