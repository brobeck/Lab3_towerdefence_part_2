package edu.chl.hajo.td.model;

import edu.chl.hajo.td.model.creeps.AbstractCreep;
import edu.chl.hajo.td.util.Point2D;
import edu.chl.hajo.td.util.Vector2D;
import lombok.Getter;
import lombok.Setter;

abstract public class Entity {

    @Setter
    @Getter
    private Point2D pos;    // Center position

    @Getter
    @Setter
    private double width; // Upper right corner: x - width / 2

    @Getter
    @Setter
    private double height;

    private static final Vector2D INIT_DIR = new Vector2D(-1, 0);

    @Getter
    @Setter
    private Vector2D dir = INIT_DIR;

    public Entity(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public void targetDir(Point2D targetPoint) {
        dir = new Vector2D(targetPoint.getX() - pos.getX(),targetPoint.getY() - pos.getY());
    }

    public void addPos(Vector2D v) {
        pos = pos.add(v);
    }
}
