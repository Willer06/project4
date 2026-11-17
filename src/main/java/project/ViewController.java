package project;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Loads all the views for RU Burger
 * 
 * @author Benjamin Gluzman, William Becerra
 *  
 */
public class ViewController {
    /** Scene shared by every view */
    private static Scene scene;

    /** HashMap of cached views */
    private static HashMap<String, Parent> parentMap;

    static {
        parentMap = new HashMap<String, Parent>();
        ArrayList<String> paths = new ArrayList<String>(List.of(
            "/main-view.fxml",
            "/burger-view.fxml",
            "/sandwich-view.fxml",
            "/beverage-view.fxml",
            "/side-view.fxml",
            "/combo-view.fxml",
            "/current-order-view.fxml",
            "/orders-placed-view.fxml"
        ));

        for(String path : paths) {
            try {
                FXMLLoader loader = new FXMLLoader(ViewController.class.getResource(path));
                Parent root = loader.load();
                parentMap.put(path, root);
            }
            catch(IOException e) { }
        }
    }

    /**
     * Sets the static variable scene which all views use
     * 
     * @param scene
     */
    public static void setScene(Scene scene) {
        ViewController.scene = scene;
    }

    /**
     * Switches the view to the view given by fxmlFilePath
     * 
     * @param fxmlFilePath
     */
    public static void switchView(String fxmlFilePath) {
        scene.setRoot(parentMap.get(fxmlFilePath));

        if(scene.getWindow() instanceof Stage stage) {
            stage.sizeToScene();
            stage.setTitle("RU Burger - " + fxmlFilePath.replace("/", "").replace("-view.fxml", ""));
        }
    }
}