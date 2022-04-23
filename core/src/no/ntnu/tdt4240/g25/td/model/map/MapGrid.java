package no.ntnu.tdt4240.g25.td.model.map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MapGrid implements Iterable<MapTile> {

    private Array<MapTile> grid;
    private Array<Vector2> path;
    private int width;
    private int height;

    public MapGrid(int width, int height, Array<Vector2> path) {
        this.width = width;
        this.height = height;
        this.path = path;
        createMapGrid();
    }

    public void createMapGrid() {
        grid = new Array<>(width * height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (path.contains(new Vector2(i, j), false)) {
                    grid.add(new MapTile(i, j, MapTileType.ROAD));
                }
                else {
                    grid.add(new MapTile(i, j, MapTileType.PLAIN));
                }
            }
        }
    }

    public static MapGrid getTestGrid(int width, int height) {
        // create a test grid, containing a path going from 0,0 to 9,16 , only being able
        // to move up, down, left and right.
        Array<Vector2> path = new Array<>();
        path.addAll(
                new Vector2(4, 0),
                new Vector2(4, 1),
                new Vector2(4, 2),
                new Vector2(4, 3),
                new Vector2(4, 4),
                new Vector2(4, 5),
                new Vector2(4, 6),
                new Vector2(5, 6),
                new Vector2(6, 6),
                new Vector2(7, 6),
                new Vector2(7, 7),
                new Vector2(7, 8),
                new Vector2(7, 9),
                new Vector2(7, 10),
                new Vector2(7, 11),
                new Vector2(7, 12),
                new Vector2(6, 12),
                new Vector2(5, 12),
                new Vector2(4, 12),
                new Vector2(3, 12),
                new Vector2(2, 12),
                new Vector2(2, 13),
                new Vector2(2, 14),
                new Vector2(2, 15),
                new Vector2(2, 16)
        );
        return new MapGrid(width, height, path);

    }

    public Array<MapTile> getGrid() {
        return grid;
    }

    public Array<Vector2> getPath() {
        return path;
    }

    @Override
    public Iterator<MapTile> iterator() {
        return new Array.ArrayIterator<>(grid);
    }
}
