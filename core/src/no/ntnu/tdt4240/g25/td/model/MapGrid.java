package no.ntnu.tdt4240.g25.td.model;

import com.badlogic.gdx.math.Vector2;

import java.util.List;
import java.util.Set;

public class MapGrid {

    private List<MapTile> grid;
    private Set<Vector2> path;
    private int width;
    private int height;

    public MapGrid(int width, int height, Set<Vector2> path) {
        this.width = width;
        this.height = height;
        this.path = path;
        createMapGrid();
    }

    public void createMapGrid() {
        int gridSize = 1;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (path.contains(new Vector2(i, j))) {
                    grid.add(new MapTile(i, j, gridSize, MapTileType.ROAD));
                }
                else {
                    grid.add(new MapTile(i, j, gridSize, MapTileType.PLAIN));
                }
            }
        }
    }

    public List<MapTile> getGrid() {
        return grid;
    }
}
