package no.ntnu.tdt4240.g25.td.service;

public enum TerrainAtlas {
    SUMMER("map/terrain/summer.atlas"),
    WINTER("map/terrain/winter.atlas"),
    FALL("map/terrain/fall.atlas"),
    SPRING("map/terrain/spring.atlas");

    public final String path;

    TerrainAtlas(String path) {
        this.path = path;
    }
}
