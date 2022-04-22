package no.ntnu.tdt4240.g25.td.model;

public enum TileType {

    PLAIN("map/terrain/summer.atlas"),
    OBSTACLE("map/terrain/summer.atlas"),
    TREES("map/terrain/summer.atlas"),
    ROAD("map/terrain/summer.atlas"),
    ROAD_TURN("map/terrain/summer.atlas");

    public final String atlasPath;

    TileType(String atlasPath) {
        this.atlasPath = atlasPath;
    }
}
