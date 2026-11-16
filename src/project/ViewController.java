package project;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ViewController {
    private static Scene scene;
    private static HashMap<String, Parent> parentMap;

    static {
        parentMap = new HashMap<String, Parent>();
        ArrayList<String> paths = new ArrayList<String>(List.of(
            "../resources/main-view.fxml",
            "../resources/burger-view.fxml",
            "../resources/sandwich-view.fxml",
            "../resources/beverage-view.fxml",
            "../resources/side-view.fxml",
            "../resources/combo-view.fxml",
            "../resources/current-order-view.fxml",
            "../resources/orders-placed-view.fxml"
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

    public static void setScene(Scene scene) {
        ViewController.scene = scene;
    }

    public static void switchView(String fxmlFilePath) {
        scene.setRoot(parentMap.get(fxmlFilePath));
    }
}