package project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Configures and starts the primary JavaFX window for RU Burger.
 *
 * @author Benjamin Gluzman, William Becerra
 */
public class Main extends Application {
    /** Title of the main JavaFX stage. */
    private static final String STAGE_TITLE = "RU Burger";

    /**
     * Creates the shared scene and opens the main menu.
     *
     * @param stage primary JavaFX stage.
     */
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Pane());
        stage.setTitle(STAGE_TITLE);
        stage.setScene(scene);
        ViewController.setScene(scene);
        stage.show();
        ViewController.switchView("/main-view.fxml");
    }

    /**
     * Alternative JavaFX entry point for IDE launch configurations.
     *
     * @param args command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
