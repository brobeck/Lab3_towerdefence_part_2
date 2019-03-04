package edu.chl.hajo.td.model.towers;

import edu.chl.hajo.td.util.Point2D;
import lombok.Getter;

import static edu.chl.hajo.td.model.TowerDefence.TENTH_SEC;

public abstract class AbstractGunTower extends AbstractTower {
    @Getter
    private final double range;    // Max distance to fire
    @Getter
    private final long coolDown;  // Time before next shoot
    @Getter
    private final int firePower;

    public AbstractGunTower(Point2D pos) {
        super(pos);

        this.range = 120;
        this.coolDown = TENTH_SEC * 5;
        this.firePower = 100;
    }

}
