package edu.chl.hajo.td.control;

import edu.chl.hajo.td.IRenderer;
import edu.chl.hajo.td.model.TowerDefence;
import javafx.animation.AnimationTimer;
import lombok.Getter;


/*
        The game loop driven by a timer
        Also access point for all controls to access the
        model
 */
public class GameLoop extends AnimationTimer {

    @Getter
    private static TowerDefence towerDefence; // Accessible by other controls
    private final IRenderer renderer;
    @Getter
    private boolean isRunning;

    public GameLoop(TowerDefence towerDefence, IRenderer renderer) {
        this.towerDefence = towerDefence;
        this.renderer = renderer;
    }

    @Override
    public void handle(long now) {
        towerDefence.update(now);
        renderer.render(towerDefence);
    }

    @Override
    public void start() {
        super.start();
        isRunning = true;
    }

    @Override
    public void stop() {
        super.stop();
        isRunning = false;
    }
}
