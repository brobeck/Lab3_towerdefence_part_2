package edu.chl.hajo.td.model.towers;

/*
 *   Base class for all towers
 *
 */

import edu.chl.hajo.td.model.Entity;
import edu.chl.hajo.td.util.Point2D;
import lombok.Getter;
import lombok.Setter;

import static edu.chl.hajo.td.model.TowerDefence.TENTH_SEC;

public abstract class AbstractTower extends Entity implements ITower {

    @Getter
    @Setter
    private long lastShot;

    public AbstractTower(Point2D pos) {
        super(20, 20);
        setPos(pos);
    }
}
