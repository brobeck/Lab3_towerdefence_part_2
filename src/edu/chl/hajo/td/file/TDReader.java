package edu.chl.hajo.td.file;

import com.google.gson.Gson;
import edu.chl.hajo.td.util.AnimatedImage;
import edu.chl.hajo.td.util.Pair;
import edu.chl.hajo.td.util.URLFactory;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import org.mapeditor.core.Map;
import org.mapeditor.core.TileLayer;
import org.mapeditor.io.TMXMapReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/*
        Class to read resources
        All file handling here

        **** Nothing to do here ***
 */
public final class TDReader {

    // --- Level data from JSON ----------------------

    private static final String LEVELS_DIR = "levels/";

    public static LevelData readLevel(String levelFileName) throws IOException {
        URL url = URLFactory.getAbsoluteURLFor(LEVELS_DIR + levelFileName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
        Gson gson = new Gson();
        return gson.fromJson(bufferedReader, LevelData.class);
    }


    // ------------- Map from Tiled (tmx file) --------------------------

    public static MapData readMap(String tileMapFileName) throws Exception {
        URL url = URLFactory.getAbsoluteURLFor(tileMapFileName);
        Map visualMap = new TMXMapReader().readMap(url.getPath());

        List<Pair> occupied = getOccupied(visualMap);
        int mapWidth = visualMap.getWidth();
        int mapHeight = visualMap.getHeight();
        return new MapData(visualMap, occupied, mapWidth, mapHeight,
                visualMap.getTileWidth(), visualMap.getTileHeight());
    }

    // Cant place anything on an occupied tile
    private static List<Pair> getOccupied(Map visualMap) {
        // Top left tile in visualMap MUST be an non occupied tile (using the id of that tile)
        TileLayer layer = (TileLayer) visualMap.getLayer(0);
        int nonOccupiedId = layer.getTileAt(0, 0).getId();
        List<Pair> occupied = new ArrayList<>();
        for (int y = 0; y < visualMap.getHeight(); y++) {
            for (int x = 0; x < visualMap.getWidth(); x++) {
                if (layer.getTileAt(x, y).getId() != nonOccupiedId) {
                    occupied.add(new Pair(x, y));
                }
            }
        }
        return occupied;
    }

    // ------------- Assets --------------------------

    private static final String SOUND_DIR = "sound/";
    private static final String IMAGE_DIR = "img/";

    public static Image getImage(String imageFileName, int preferredWith, int preferredHeight) {
        URL url = URLFactory.getAbsoluteURLFor(IMAGE_DIR + imageFileName);
        return new Image(url.toString(), preferredWith, preferredHeight, true, true);
    }

    public static AnimatedImage getAnimatedImage(String spriteSheetFileName, int spriteWidth, int spriteHeight,
                                                 long delay) throws IOException {
        URL url = URLFactory.getAbsoluteURLFor(IMAGE_DIR + spriteSheetFileName);
        BufferedImage spriteSheet = ImageIO.read(url);
        return new AnimatedImage(spriteSheet, spriteWidth, spriteHeight, delay);
    }

    public static AudioClip getAudioClip(String clipFileName) {
        return new AudioClip(SOUND_DIR + clipFileName);
    }

    // Pure static class
    private TDReader() {
    }


}
