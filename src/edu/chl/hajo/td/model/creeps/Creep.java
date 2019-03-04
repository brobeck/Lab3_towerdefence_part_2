package edu.chl.hajo.td.model.creeps;

import edu.chl.hajo.td.model.Path;
import lombok.Getter;
import lombok.Setter;

import static edu.chl.hajo.td.model.creeps.CreepType.SMALLCREEP;
import static java.lang.System.out;

/*
 *     A basic creep (something that follows a path)
 *     - Follows exactly one path
 *     - Will damage you if arriving at path end
 *     - May be killed by some tower placed by you
 */
public class Creep extends AbstractCreep {

    @Setter
    @Getter
    private double speed;
    @Getter
    @Setter
    private int hp;  // Current health
    @Getter
    private final int maxHp;      // Needed for % display in GUI
    @Getter
    private final int killPoints;  // Points to player when killed
    @Getter
    private final int damage;      // Damage caused when arriving at (non existing) base of player

    public Creep(double speed, Path path, int maxHp, int killPoints, int damage) {
        super(path);

        this.speed = speed;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.killPoints = killPoints;
        this.damage = damage;
    }

    public Creep(Path path) {
        this(1.5, path, 500, 100, 50);
    }

    @Override
    public void move() {
        if (getPos().epsilonEquals(getNextPoint(), speed)) {
            setNextPoint(getPathIterator().next());
            targetDir(getNextPoint());
        }

        addPos(getDir().scale(speed));
    }

    @Override
    public boolean dealDamage(double damageDealt) {
        hp -= damageDealt;
        out.println(hp);
        return hp <= 0; //Did it die?
    }

    @Override
    public AbstractCreep copyOf() {
        return new Creep(speed, getPath(), maxHp, killPoints, damage);
    }

}
