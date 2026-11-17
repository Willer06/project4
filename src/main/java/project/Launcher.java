package project;

import javafx.application.Application;

/**
 * Provides a conventional Java entry point for the RU Burger JavaFX program.
 * Delegates application lifecycle management to {@link Main}.
 *
 * @author Benjamin Gluzman, William Becerra
 */
public class Launcher {

    /**
     * Starts the RU Burger application through the JavaFX launcher.
     *
     * @param args command-line arguments supplied to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
