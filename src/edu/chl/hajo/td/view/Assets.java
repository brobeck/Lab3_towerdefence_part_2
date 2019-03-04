package edu.chl.hajo.td.view;

import edu.chl.hajo.td.event.ModelEvent;
//import edu.chl.hajo.td.file.AssetsReader;
import edu.chl.hajo.td.file.TDReader;

import edu.chl.hajo.td.model.creeps.Creep;
import edu.chl.hajo.td.model.towers.BasicGunTower;
import edu.chl.hajo.td.util.AnimatedImage;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*

   Handling assets for game, images, sounds, ...
   Used by Renderer and control layer (to access sounds)

*/

class Assets {

    //private final AssetsReader assetsReader;
    private final Map<Object, Object> objectImage = new HashMap<>();
    private final Map<ModelEvent, AudioClip> eventSound = new HashMap<>();

    public Assets() throws IOException {

        // TODO Bind creeps to images
        bindAnimated(Creep.class, "guardbot1.png", 28, 31, 100_000_000);
        //bindAnimated(Creep.class, "minion-45x66.png", 45, 66, 100_000_000);

        bind(BasicGunTower.class ,"towers/basicguntower.png", 45, 66);
    }

    // Get image to render (NOTE; Object (key) may be a Class object)
    public Image get(Object object) {
        Object o = objectImage.get(object);
        // If object not bound possibly class is?
        if (o == null) {
            o = objectImage.get(object.getClass());
        }
        // This will handle null (null always false)
        if (o instanceof Image) {
            return (Image) o;
        } else if (o instanceof AnimatedImage) {
            return ((AnimatedImage) o).getCurrentImage();
        } else {
            throw new IllegalArgumentException("Image not found");
        }
    }

    // -------------- Methods binding objects/classes to assets -----------------

    private void bind(Object object, String imageFileName, int preferredWith, int preferredHeight) {
        if (objectImage.get(object) == null) {
            //Image i = assetsReader.getImage(imageFileName, preferredWith, preferredHeight);
            Image i = TDReader.getImage(imageFileName, preferredWith, preferredHeight);
            objectImage.put(object, i);
        }
    }

    public void bindAnimated(Object object, String spriteSheetFileName, int spriteWidth, int spriteHeight, long delay) throws IOException {
        if (objectImage.get(object) == null) {
            //AnimatedImage ai = assetsReader.getAnimatedImage(spriteSheetFileName, spriteWidth, spriteHeight, delay);
            AnimatedImage ai = TDReader.getAnimatedImage(spriteSheetFileName, spriteWidth, spriteHeight, delay);
            objectImage.put(object, ai);
        }
    }
}
