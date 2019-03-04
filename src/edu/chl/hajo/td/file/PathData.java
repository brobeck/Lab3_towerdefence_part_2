package edu.chl.hajo.td.file;

import java.util.List;

/*
    Class holding data from JSON file
     **** Nothing to do here ***

*/
public class PathData {
    public final int id;
    // This is ["1,3", "4,2" ... ] i.e. pair of grid coordinates (NOT Tiles coordinates)
    public final List<String> points;

    public PathData(int id, List<String> points) {
        this.id = id;
        this.points = points;
    }
}