package edu.chl.hajo.td;

import edu.chl.hajo.td.model.TowerDefence;
import org.mapeditor.core.Map;

/*
      Specification for anything that can render

      *** Nothing to do here ***
 */

public interface IRenderer {

    void render(TowerDefence td);

    void renderBackground(Map map);
}
