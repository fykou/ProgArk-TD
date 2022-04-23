package no.ntnu.tdt4240.g25.td.model.map;

public enum MapTileType {

    PLAIN("plain"),
    OBSTACLE("obstacle"),
    TREES("trees"),
    ROAD("road"),
    ROAD_TURN("road_turn");

    public final String regionName;

    MapTileType(String regionName) {
        this.regionName = regionName;
    }
}
