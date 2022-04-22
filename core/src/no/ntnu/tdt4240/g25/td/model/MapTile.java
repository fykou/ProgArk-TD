package no.ntnu.tdt4240.g25.td.model;

public class TileGrid {

    private int width;
    private int height;
    private int x;
    private int y;
    private TileType tile;


    public TileGrid(int width, int height, int x, int y, TileType tile) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.tile = tile;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public TileType getTile() {
        return tile;
    }


}
