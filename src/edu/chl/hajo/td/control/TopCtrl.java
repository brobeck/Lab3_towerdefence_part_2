package edu.chl.hajo.td.control;

import edu.chl.hajo.td.event.EventBus;
import edu.chl.hajo.td.event.IEventHandler;
import edu.chl.hajo.td.event.ModelEvent;
import edu.chl.hajo.td.model.TowerDefence;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import static java.lang.System.out;

/*
        Control to display data coming from model (points, damage)
        See top.fxml
 */
public class TopCtrl implements IEventHandler {
    @FXML
    private Label points;
    @FXML
    private Label damage;

    // To be able to get ModelEvents
    public void initialize() {
        EventBus.INSTANCE.register(this);
    }

    @Override
    public void onModelEvent(ModelEvent evt) {
        TowerDefence td;
        switch (evt.getType()) {
            case CREEP_KILLED:
                td =  (TowerDefence) evt.getValue();
                points.setText(String.valueOf(td.getPoints()));
                break;
            case CREEP_FINISHED:
                td = (TowerDefence) evt.getValue();
                damage.setText(String.valueOf(td.getDamage()));
                break;
            case GUN_TOWER_FIRE:
                break;
            case NEW_TOWER:
                break;
            case LEVEL_OVER:
                break;
        }

    }
}
