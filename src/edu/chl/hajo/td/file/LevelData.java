package edu.chl.hajo.td.file;

import java.util.List;

/*

    Class holding data from JSON file
     **** Nothing to do here ***

*/
public class LevelData {
    public final int id;
    public final String info;
    public final String tileMapFileName;
    public final List<PathData> paths;
    public final List<WaveData> waves;

    public LevelData(int id, String info, String tileMapFileName, List<PathData> paths, List<WaveData> waves) {
        this.id = id;
        this.info = info;
        this.tileMapFileName = tileMapFileName;
        this.paths = paths;
        this.waves = waves;
    }
}