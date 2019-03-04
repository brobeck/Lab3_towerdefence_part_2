package edu.chl.hajo.td.model;


import edu.chl.hajo.td.util.Point2D;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/*
 *   A path for creeps (waves) to follow
 *   A creepClassName (wave) always follows exactly one path
 *
 */
public class Path {

    @Getter
    private final int id;

    @Getter
    private final List<Point2D> points;  // TODO

    // TODO

    public Path(int id, List<String> strPoints, int tileSize) {
        this.id = id;
        this.points = parseStringPoints(strPoints, tileSize);
    }


    public List<Point2D> parseStringPoints(List<String> strPoints, int tileSize) {
        List<Point2D> points = new ArrayList<>();
        for (String str : strPoints) {
            String[] split = str.split(",");
            double x = Double.parseDouble(split[0]) * tileSize;
            double y = Double.parseDouble(split[1]) * tileSize;
            Point2D newPoint = new Point2D(x, y);
            points.add(newPoint);
        }

        return points;
    }


    public Path get() {
        return this;
    }
}
