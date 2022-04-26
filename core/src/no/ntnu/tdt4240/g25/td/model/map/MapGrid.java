package no.ntnu.tdt4240.g25.td.model.map;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import jdk.internal.org.jline.utils.WCWidth;

public class MapGrid implements Iterable<MapTile> {


    private Array<Vector2> path;
    private Array<Vector2> tree;
    private int width;
    private int height;


    public MapGrid(TiledMap map) {
        this.width = map.getProperties().get("width", Integer.class);
        this.height = map.getProperties().get("height", Integer.class);

        this.path = new Array<>();


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
