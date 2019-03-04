package edu.chl.hajo.td.model.creeps;



/*
 *       An object that follows a path
 */

import edu.chl.hajo.td.model.Entity;
import edu.chl.hajo.td.model.Path;
import edu.chl.hajo.td.util.Point2D;
import lombok.Getter;
import lombok.Setter;

import java.util.Iterator;

import static edu.chl.hajo.td.model.creeps.CreepType.*;
import static java.lang.System.out;

public abstract class AbstractCreep  extends Entity implements ICreep {
    @Getter
    private final Path path;
    @Getter
    private final Iterator<Point2D> pathIterator;
    @Getter
    @Setter
    private Point2D nextPoint;

    public AbstractCreep(Path path) {
        super(10, 10);

        this.path = path;
        this.pathIterator = path.getPoints().iterator();
        this.nextPoint = pathIterator.next();
        setPos(nextPoint);
    }
}
