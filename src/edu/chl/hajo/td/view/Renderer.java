package edu.chl.hajo.td.view;

import edu.chl.hajo.td.control.GameLoop;
import edu.chl.hajo.td.model.TowerDefence;
import edu.chl.hajo.td.model.Wave;
import edu.chl.hajo.td.model.creeps.AbstractCreep;
import edu.chl.hajo.td.model.towers.*;
import edu.chl.hajo.td.IRenderer;
import edu.chl.hajo.td.util.Point2D;
import edu.chl.hajo.td.util.Vector2D;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import org.mapeditor.core.Map;
import org.mapeditor.core.TileLayer;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static java.lang.Math.atan2;
import static java.lang.Math.toDegrees;
import static java.lang.System.out;
import static javafx.scene.paint.Color.*;

import static edu.chl.hajo.td.model.TDBuilder.*;

/*
       Renderer using images (mostly).
       Usage see GameLoop
 */
public class Renderer implements IRenderer {

    private final GraphicsContext fgCtx;  // Render foreground
    private final GraphicsContext bgCtx;  // Render background
    private final Assets assets;

    public Renderer(GraphicsContext fgCtx, GraphicsContext bgCtx) throws IOException {
        this.fgCtx = fgCtx;
        this.bgCtx = bgCtx;
        assets = new Assets();
    }
    // ------------- Interface -----------------------------

    private void clearScreen() {
        fgCtx.clearRect(0, 0, getGameWidth(), getGameHeight());
    }

    @Override
    public void render(TowerDefence td) {
        clearScreen();
        td.getTowers().forEach(this::renderTower);
        td.getWaves().forEach(this::renderWave);
    }

    // ---------------- Background ----------------------
    // This is done once!
     @Override
    public void renderBackground(Map map) {
        int width = map.getWidth();
        int height = map.getHeight();
        TileLayer layer = (TileLayer) map.getLayer(0);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final org.mapeditor.core.Tile tileAt = layer.getTileAt(x, y);
                BufferedImage bi = tileAt.getImage();
                Image image = SwingFXUtils.toFXImage(bi, null);
                // A Mystery! Have to add 12 to height and width to get view correct! Why?
                bgCtx.drawImage(image, x * (width + 12), y * (height + 12));
            }
        }
    }

    //---------------------- Wave ---------------------------------

    private void renderWave(Wave wave) {
        for (AbstractCreep c : wave.getCreeps()) {
            renderCreep(c);
        }
    }

    private void renderCreep(AbstractCreep c) {
        Image i = assets.get(c);
        Point2D pos = c.getPos();
        double w = c.getWidth();
        double h = c.getHeight();
        drawImage(i, pos, w, h);
        renderHp(c);
    }

    private final Vector2D translateHp = new Vector2D(1, -1);

    private void renderHp(AbstractCreep a) {
        double percent = (double) a.getHp() / a.getMaxHp();
        renderHpBar(a.getPos().add(translateHp.scale(10)), percent);
    }

    private void renderHpBar(Point2D pos, double percent) {
        double x1 = pos.getX();
        double y = pos.getY();
        double w1 = 10 * percent;

        fgCtx.setFill(DARKGREEN);
        fgCtx.fillRect(x1, y, w1, 2);
        fgCtx.setFill(RED);
        fgCtx.fillRect(x1 + w1, y, 10 * (1 - percent), 3);
    }

    // ------------------- Towers ------------------------

    private void renderTower(AbstractTower t) {
        if (t instanceof BasicGunTower) {
            renderBasicGunTower((BasicGunTower) t);
        } else if (t instanceof BasicImpactTower) {
            renderImpactTower((BasicImpactTower) t);
        }
    }

    private void renderBasicGunTower(BasicGunTower t) {
        renderDirected(t);
    }


    private void renderImpactTower(AbstractImpactTower t) {
        Point2D pos = t.getPos();
        double w = t.getWidth();
        double h = t.getHeight();
        double xTopLeft = pos.getX() - w / 2;
        double yTopLeft = pos.getY() - h / 2;
        fgCtx.setFill(DARKGRAY);
        fgCtx.fillOval(xTopLeft, yTopLeft, w, h);
        fgCtx.setStroke(LIGHTYELLOW);
        fgCtx.strokeOval(xTopLeft, yTopLeft, w, h);

    }

    // This assumes symmetrical images
    private void renderDirected(AbstractGunTower tower) {
        double x = tower.getPos().getX();
        double y = tower.getPos().getY();
        Vector2D dir = tower.getDir();
        Image img = assets.get(tower);
        fgCtx.save();
        fgCtx.translate(x, y);  // If image not symmetrical, adjust to rotation center
        fgCtx.rotate(toDegrees(atan2(dir.getY(), dir.getX())));
        fgCtx.translate(-x, -y);
        fgCtx.drawImage(img, x - img.getWidth() / 2, y - img.getHeight() / 2);
        fgCtx.restore();
    }


    private void drawImage(Image image, Point2D pos, double width, double height) {
        double xTopLeft = pos.getX() - width / 2;
        double yTopLeft = pos.getY() - height / 2;
        fgCtx.drawImage(image, xTopLeft, yTopLeft);
    }
}
