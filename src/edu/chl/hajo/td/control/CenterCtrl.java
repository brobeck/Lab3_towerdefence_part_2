package edu.chl.hajo.td.control;

import edu.chl.hajo.td.ServiceLocator;
import edu.chl.hajo.td.model.TDMap;
import edu.chl.hajo.td.model.TDTile;
import edu.chl.hajo.td.model.TowerDefence;
import edu.chl.hajo.td.model.towers.AbstractTower;
import edu.chl.hajo.td.model.towers.TowerFactory;
import edu.chl.hajo.td.util.Point2D;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import static edu.chl.hajo.td.model.towers.TowerFactory.Type.NONE;
import static java.lang.System.out;

/*
        Controller for center drawing/animation area
        See center.fxml
 */
public class CenterCtrl {

    @FXML
    public void handleMouseClicked(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        TowerFactory.Type selectedTowerType = RightCtrl.getSelectedTowerType();
        out.print(selectedTowerType);
        if(selectedTowerType != NONE) {
            tryPlaceTower(selectedTowerType, x, y);
        }
    }

    private void tryPlaceTower(TowerFactory.Type selectedTowerType, double x, double y) {
        TowerDefence td = GameLoop.getTowerDefence();
        TDMap map = td.getMap();
        TDTile tile = map.getTileFor(x, y);
        boolean tileFree = !tile.isOccupied();

        if(tileFree) {
            AbstractTower newTower = TowerFactory.getTower(selectedTowerType, map.getPosFor(tile));
            td.addTower(newTower);
            tile.setOccupied(true);
        }
    }
}





