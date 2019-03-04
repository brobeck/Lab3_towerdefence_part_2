package edu.chl.hajo.td.file;

/*
     Class holding data from JSON file
        **** Nothing to do here ***
*/
public class WaveData {
    public final String creepClassName;
    public final int nCreeps;
    public final long initDelay;
    public final long spawnPeriod;
    public final int pathId;

    public WaveData(String creepClassName, int nCreeps, long initDelay, long spawnPeriod, int pathId) {
        this.creepClassName = creepClassName;
        this.nCreeps = nCreeps;
        this.initDelay = initDelay;
        this.spawnPeriod = spawnPeriod;
        this.pathId = pathId;
    }
}

