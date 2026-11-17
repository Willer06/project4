package project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {
    private static final String STAGE_TITLE = "RU Burger";

    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Pane());
        stage.setTitle(STAGE_TITLE);
        stage.setScene(scene);
        ViewController.setScene(scene);
        stage.show();
        ViewController.switchView("/main-view.fxml");
    }
}
