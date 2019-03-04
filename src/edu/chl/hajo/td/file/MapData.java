package edu.chl.hajo.td.file;


import edu.chl.hajo.td.util.Pair;
import org.mapeditor.core.Map;

import java.util.List;

/*
        Class holding data from JSON file
        **** Nothing to do here ***

 */
public class MapData {

    public final Map visualMap;
    public final List<Pair> occupied;
    public final int mapWidth;
    public final int mapHeight;
    public final int tileWidth;
    public final int tileHeight;

    public MapData(Map visualMap, List<Pair> occupied, int mapWidth, int mapHeight,
                   int tileWidth, int tileHeight) {
        this.visualMap = visualMap;
        this.occupied = occupied;
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
    }
}
