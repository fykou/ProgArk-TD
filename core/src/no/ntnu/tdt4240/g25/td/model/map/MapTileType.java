package no.ntnu.tdt4240.g25.td.model.map;

public enum MapTileType {

    PLAIN(0, "plain"),
    OBSTACLE(1, "obstacle"),
    TREES(2, "trees"),
    ROAD(3, "road"),
    ROAD_TURN(4, "road_turn");

    public final String regionName;
    public final int id;

    MapTileType(int id, String regionName) {
        this.id = id;
        this.regionName = regionName;
    }
    public static MapTileType getTileTypeById(int id) {
        // the id modulo 7 is used to get the tile type
        // from the id. If the result of id -1 mod 7 is higher than the number of tile types,
        // return null
        return MapTileType.values()[(id - 1) % 7];
    }
}
