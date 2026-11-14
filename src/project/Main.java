package project;

import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private static final String STAGE_TITLE = "RU Burger";

    private static final int STAGE_WIDTH = 960;
    private static final int STAGE_HEIGHT = 720;
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Pane(), STAGE_WIDTH, STAGE_HEIGHT);
        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchView("../resources/main-view.fxml");

        stage.setTitle(STAGE_TITLE);
        stage.setScene(scene);
        stage.show();
    }
}