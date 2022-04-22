package no.ntnu.tdt4240.g25.td.model;

public class MapTile {

    private int x;
    private int y;
    private MapTileType tile;


    public MapTile(int x, int y, MapTileType tile) {
        this.tile = tile;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public MapTileType getTile() {
        return tile;
    }
}
