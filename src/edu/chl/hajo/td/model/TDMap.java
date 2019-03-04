package edu.chl.hajo.td.model;

import edu.chl.hajo.td.util.Point2D;
import lombok.Getter;


/*
     The logical Map

     This map uses (x, y) with y pointing down because
     "Tiled" library uses same and also graphics output
 */
public class TDMap {

    private final int tileSize;
    @Getter
    private final TDTile[][] tiles;


    public TDMap(TDTile[][] logicTiles, int tileSize) {
        this.tiles = logicTiles;
        this.tileSize = tileSize;
    }

    // Get tile for some mouse click
    public TDTile getTileFor(double x, double y) {
        int y1 = (int) y / tileSize;
        int x1 = (int) x / tileSize;
        return tiles[y1][x1];
    }

    // Return center position for tile
    public Point2D getPosFor(TDTile tile) {
        for (int y = 0; y < tiles.length; y++) {
            for (int x = 0; x < tiles.length; x++) {
                if (tiles[y][x] == tile) {
                    int y1 = tileSize * y + tileSize / 2;
                    int x1 = tileSize * x + tileSize / 2;
                    return new Point2D(x1, y1);
                }
            }
        }
        throw new IllegalArgumentException("Tile not in map??? " + tile);
    }
}
