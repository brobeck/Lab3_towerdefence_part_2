package edu.chl.hajo.td.model;

import edu.chl.hajo.td.event.EventBus;
import edu.chl.hajo.td.event.ModelEvent;
import edu.chl.hajo.td.model.creeps.AbstractCreep;
import edu.chl.hajo.td.model.creeps.AbstractCreep;
import edu.chl.hajo.td.util.Point2D;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 *  A Wave of creeps
 */


public class Wave  {
    @Getter
    private final AbstractCreep prototype;
    @Getter
    private final long initDelay;
    @Getter
    private final long spawnDelay;
    @Getter
    private long lastSpawnedCreep = 0;
    @Getter
    private final int maxCreeps;
    @Getter
    private List<AbstractCreep> creeps = new ArrayList<>();
    @Getter
    private List<AbstractCreep> finishedCreeps = new ArrayList<>();
    @Getter
    private List<AbstractCreep> deadCreeps = new ArrayList<>();

    // TODO

    public Wave(int maxCreeps, long initDelay, long spawnDelay, AbstractCreep prototype) {
        this.maxCreeps = maxCreeps;
        this.initDelay = initDelay;
        this.spawnDelay = spawnDelay;
        this.prototype = prototype.copyOf();
    }

    public int move() {
        int nrFinished = 0;

        Iterator<AbstractCreep> creepIterator = creeps.iterator();
        while (creepIterator.hasNext()) {
            AbstractCreep creep = creepIterator.next();

            Point2D creepPos        = creep.getPos();
            List<Point2D> creepPath = creep.getPath().getPoints();
            Point2D lastPoint       = creepPath.get(creepPath.size() - 1);
            double creepSpeed       = creep.getSpeed();

            boolean isFinished = creepPos.epsilonEquals(lastPoint, creepSpeed);

            if (isFinished) {
                nrFinished++;
                finishedCreeps.add(creep);
                creepIterator.remove();
            } else {
                creep.move();
            }
        }

        return nrFinished;
    }

    public void spawn(long now) {
        if (now - lastSpawnedCreep > spawnDelay) {
            lastSpawnedCreep = now;
            int totalCreeps = creeps.size() + finishedCreeps.size() + deadCreeps.size();

            if (totalCreeps < maxCreeps) {
                creeps.add(prototype.copyOf());
            }
        }
    }

    public void kill(AbstractCreep creep) {
        creeps.remove(creep);
        deadCreeps.add(creep);
    }
}
