package no.ntnu.tdt4240.g25.td.model;

public class MapTile {

    private int size;
    private int x;
    private int y;
    private MapTileType tile;


    public MapTile(int x, int y, int size, MapTileType tile) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.tile = tile;
    }

    public int getSize() {
        return size;
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
