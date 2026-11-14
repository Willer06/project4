package project;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class ViewSwitcher {
    private static Scene scene;

    public static void setScene(Scene scene) {
        ViewSwitcher.scene = scene;
    }

    public static void switchView(String fxmlFilePath) {
        try {
            FXMLLoader loader = new FXMLLoader(ViewSwitcher.class.getResource(fxmlFilePath));
            Parent root = loader.load();
            scene.setRoot(root);
        }
        catch(IOException e) {
            
        }
    }
}