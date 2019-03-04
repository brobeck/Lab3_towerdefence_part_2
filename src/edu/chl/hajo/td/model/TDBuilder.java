package edu.chl.hajo.td.model;

import edu.chl.hajo.td.file.LevelData;
import edu.chl.hajo.td.file.MapData;
import edu.chl.hajo.td.file.PathData;
import edu.chl.hajo.td.file.WaveData;
import edu.chl.hajo.td.model.creeps.AbstractCreep;
import edu.chl.hajo.td.model.creeps.CreepFactory;
import edu.chl.hajo.td.util.Pair;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;


/*
        Class responsible for building a complete level

        The visual map is used to create a logical map.

        Visual map created with the Tiled editor
        see https://www.mapeditor.org/

        NOTE: Only public class in package

        NOTE: build() must be called before getVisualMap!



 */
public class TDBuilder {

    @Getter
    private static int tileSize;  // 32x32 pixels
    @Getter
    private static int gameSize;  // 20x20 tiles
    @Getter
    private static double gameWidth;
    @Getter
    private static double gameHeight;

    public static TowerDefence build(LevelData levelData, MapData mapData) {

        tileSize = mapData.tileWidth;   // Same width and height
        gameSize = mapData.mapWidth;     // Same width and height
        gameWidth = tileSize * gameSize;
        gameHeight = gameWidth;



        // The tiles (for the logical map)
        TDTile[][] logicTiles = new TDTile[mapData.mapWidth][mapData.mapHeight];
        for (int y = 0; y < mapData.mapHeight; y++) {
            for (int x = 0; x < mapData.mapWidth; x++) {
                logicTiles[x][y] = new TDTile();
            }
        }
        // Set occupied tiles (original data for this in Map)
        for (Pair p : mapData.occupied) {
            logicTiles[p.y][p.x].setOccupied(true);
        }

        // The paths
        List<PathData> pathData = levelData.paths;
        List<Path> paths = new ArrayList<>();
        for (PathData pd : pathData) {
            Path path = new Path(pd.id, pd.points, mapData.tileWidth);
            paths.add(path);
        }

        // Create logical map
        TDMap logicalMap = new TDMap(logicTiles, mapData.tileHeight);

        // The waves
        List<WaveData> waveData = levelData.waves;

        List<Wave> waves = new ArrayList<>();
        for (WaveData wd : waveData) {
            AbstractCreep prototype = CreepFactory.getCreep(wd.creepClassName, paths.get(0));
            //TODO Uncomment when your model classes are in place
            Wave wave = new Wave(wd.nCreeps, wd.initDelay, wd.spawnPeriod, prototype);
            waves.add(wave);
        }
        //TODO Uncomment when your model classes are in place
        return new TowerDefence(logicalMap, waves);
    }


}
