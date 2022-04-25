package no.ntnu.tdt4240.g25.td.model.map;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import jdk.internal.org.jline.utils.WCWidth;

public class MapGrid implements Iterable<MapTile> {

    private Array<MapTile> grid;
    private Array<Vector2> path;
    private Array<Vector2> tree;
    private int width;
    private int height;


    public MapGrid(int width, int height, Array<Vector2> path, Array<Vector2> tree) {
        this.width = width;
        this.height = height;
        this.path = path;
        this.tree = tree;
        createMapGrid();
    }

    public void createMapGrid() {
        Gdx.app.setLogLevel(Application.LOG_DEBUG);
        grid = new Array<>(width * height);
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (path.contains(new Vector2(i, j), false)) {
                    if ((path.contains(new Vector2(i-1, j), false)
                            || path.contains(new Vector2(i+1, j), false)) &&
                            !(path.contains(new Vector2(i-1, j), false) &&
                                    (path.contains(new Vector2(i+1, j), false)))) {
                        grid.add(new MapTile(i, j, MapTileType.ROAD_TURN));
                        if (path.contains(new Vector2(i+1, j), false) && path.contains(new Vector2(i, j-1), false)) {
                            Gdx.app.log("yo", "UL" + Integer.toString(i) + " " + Integer.toString(j));
                        }
                        else if (path.contains(new Vector2(i+1, j), false) && path.contains(new Vector2(i, j+1), false)) {
                            Gdx.app.log("yo", "L" + Integer.toString(i) + " " + Integer.toString(j));
                        }
                        else if (path.contains(new Vector2(i-1, j), false) && path.contains(new Vector2(i, j+1), false)) {
                            Gdx.app.log("yo", "RL" + Integer.toString(i) + " " + Integer.toString(j));
                        }
                        else {
                            Gdx.app.log("yo", "URL" + Integer.toString(i) + " " + Integer.toString(j));
                        }
                    }
                    else {
                        if ((path.contains(new Vector2(i-1, j), false) &&
                                (path.contains(new Vector2(i+1, j), false)))) {
                            grid.add(new MapTile(i, j, MapTileType.OBSTACLE));
                        }
                        else {
                            grid.add(new MapTile(i, j, MapTileType.ROAD));
                        }
                    }
                }
                else {
                    if (tree.contains(new Vector2(i, j), false)) {
                        grid.add(new MapTile(i, j, MapTileType.TREES));
                    }
                    else {
                        grid.add(new MapTile(i, j, MapTileType.PLAIN));
                    }
                }
            }
        }
    }

    public static MapGrid getTestGrid(int width, int height) {
        // create a test grid, containing a path going from 0,0 to 9,16 , only being able
        // to move up, down, left and right.
        Array<Vector2> path = new Array<>();
        Array<Vector2> tree = new Array<>();
        Array<Integer> first = new Array<>();
        Array<Integer> second = new Array<>();
        first.addAll(2,2,0,0,1,0,0,2,2,
                2,2,0,0,1,0,0,2,2,
                2,2,0,0,1,1,0,0,2,
                2,2,2,0,0,1,0,0,2,
                2,2,2,0,0,1,0,0,2,
                2,2,0,0,1,1,0,0,2,
                2,2,0,0,1,0,0,2,2,
                2,0,0,1,1,0,0,2,2,
                2,0,0,1,0,0,0,2,2,
                2,0,0,1,1,1,0,0,2,
                2,2,0,0,0,1,0,0,2,
                2,2,0,0,1,1,0,0,2,
                2,2,0,0,1,0,0,2,2,
                2,2,0,0,1,0,0,2,2,
                2,2,0,0,1,0,0,2,2);

        /*int y = 1;
        for (int x = 0; x==8 && y==15; x++) {
            if (y<=height-1) {
                second.add(first.get((first.size - 1) - (y * width) + x));
                Gdx.app.log("yo", second.toString());
            }
            if (y==height) {
                second.add(first.get(x));
            }
            if ((x+1)%width == 0) {
                y = y + 1;
                x = 0;
            }
        }*/

        Array<Integer> heap = first;

        int number=0;
        int row=0;
        Array<Vector2> vectorMap = new Array<>();
        Gdx.app.setLogLevel(Application.LOG_DEBUG);

        for(int i : heap) {
            if (i == 1) {
                Vector2 limited = new Vector2(number % width, row);
                path.add(limited);
                if (number + width+1 <= heap.size) {
                    if (heap.get(number + 9 - 1) == 1 && heap.get(number + width) == 1) {
                        Vector2 placeholder = new Vector2((number + width) % width, row + 1);
                        path.add(placeholder);
                        heap.removeIndex(number + width);
                        heap.insert(number + width, 0);
                    }
                }
            }
            if (i == 2) {
                Vector2 limited = new Vector2(number % width, row);
                tree.add(limited);
            }
            number++;
            if (number % width == 0) {
                row++;
            }
        }

        //for (Vector2 i : path) {
        //    Gdx.app.log("yo",  "(" + Float.toString(i.y) + "," + Float.toString(i.x) + ")");
        //}
        //Gdx.app.setLogLevel(Application.LOG_DEBUG);
        //Gdx.app.log("yo", moon.toString(""));

        /*path.addAll(
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
        );*/
        return new MapGrid(width, height, path, tree);

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
