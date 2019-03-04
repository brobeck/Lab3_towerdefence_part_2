package edu.chl.hajo.td.util;

import javafx.animation.AnimationTimer;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.util.Random;

import static java.lang.Math.round;

/*

    Class to generate a sequence of images out of a sprite
    sheet
    Assumes: A single animation in sprite sheet (row by row)

    *** Nothing to do here ***

 */
public class AnimatedImage extends AnimationTimer {

    private static final Random rand = new Random(); // All objects share same!
    private static Image[] images;   // All objects share same images!
    private int frameCounter = 0;    // Each object have!
    private final long delay;        // Time (nanos) between switching image to return

    public AnimatedImage(BufferedImage spriteSheet, double spriteWidth, double spriteHeight, long delay) {
        // Done once by first objects created
        if (images == null) {
            int nCols = (int) round(spriteSheet.getWidth() / spriteWidth);
            int nRows = (int) round(spriteSheet.getHeight() / spriteHeight);
            images = new Image[nCols * nRows];
            int spW = (int) spriteWidth;
            int spH = (int) spriteHeight;
            int i = 0;
            // Split big images to many small
            for (int y = 0; y < nRows; y++)
                for (int x = 0; x < nCols; x++) {
                    BufferedImage sub = spriteSheet.getSubimage(x * spW, y * spH, spW, spH);
                    Image fxImg = SwingFXUtils.toFXImage(sub, null);
                    images[i++] = fxImg;
                }
        }
        // Start with random image (else all move synchronously)
        frameCounter = rand.nextInt(images.length);
        this.delay = delay;
        super.start();
    }

    public Image getCurrentImage() {
        return images[frameCounter];
    }

    private long lastUpdateTime;

    // Will be called by JavaFX runtime
    @Override
    public void handle(long now) {
        if (now - lastUpdateTime > delay) {
            frameCounter = (frameCounter + 1) % images.length;
            lastUpdateTime = now;
        }
    }

}
