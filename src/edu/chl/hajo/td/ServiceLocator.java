package edu.chl.hajo.td;

import edu.chl.hajo.td.model.TowerDefence;
import javafx.scene.Scene;
import lombok.Getter;

/*
        Global access to various services (like GUI)
        A singleton (enum)

        *** Nothing to do here ***
 */
public enum ServiceLocator {

    INSTANCE;

    @Getter
    private IRenderer renderer;
    @Getter
    private Scene scene;

    // -------- For all, should (can) only set once ------------

    public void setRenderer(IRenderer renderer) {
        if (this.renderer == null) {
            this.renderer = renderer;
        }
    }

    public void setScene(Scene scene) {
        if (this.scene == null) {
            this.scene = scene;
        }
    }

}
