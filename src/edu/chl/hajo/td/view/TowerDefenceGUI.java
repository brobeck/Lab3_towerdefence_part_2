package edu.chl.hajo.td.view;

import edu.chl.hajo.td.ServiceLocator;
import edu.chl.hajo.td.util.URLFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.net.URL;


/*

        JavaFX startup class
        Started from Main

 */
public class TowerDefenceGUI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Relative path
        URL url = URLFactory.getRelativeURLFor("fxml/main.fxml", this);
        Parent root = FXMLLoader.load(url);

        Canvas fg = (Canvas) root.lookup("#foreground");
        fg.toFront();
        GraphicsContext fgCtx = fg.getGraphicsContext2D();
        Canvas bg = (Canvas) root.lookup("#background");
        bg.toBack();
        GraphicsContext bgCtx = bg.getGraphicsContext2D();

        Renderer renderer = new Renderer(fgCtx, bgCtx);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tower Defence");
        primaryStage.setResizable(false);
        primaryStage.show();

        // Make it possible to access globally
        ServiceLocator.INSTANCE.setRenderer(renderer);
        ServiceLocator.INSTANCE.setScene(scene);
    }

}
