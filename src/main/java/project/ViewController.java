package project;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * Loads RU Burger views using their absolute classpath resource locations.
 * Each navigation loads a fresh FXML root to avoid stale controller instances.
 *
 * @author Benjamin Gluzman, William Becerra
 */
public class ViewController {

    /** CSS color matcher for original colors declared directly in FXML. */
    private static final Pattern BUTTON_COLOR = Pattern.compile(
            "-fx-background-color\\s*:\\s*(#[0-9a-fA-F]{6,8})");

    /** Scene shared by every screen in the ordering application. */
    private static Scene scene;

    /**
     * Converts a supported legacy path to an absolute classpath path.
     *
     * @param path requested resource name or classpath path.
     * @return absolute classpath resource path, or null for invalid input.
     */
    private static String normalizePath(String path) {
        if (path == null || path.isBlank()) {
            return null;
        }
        String normalized = path.replace('\\', '/');
        if (normalized.startsWith("../resources/")) {
            normalized = normalized.substring("../resources".length());
        }
        return normalized.startsWith("/") ? normalized : "/" + normalized;
    }

    /**
     * Loads a fresh FXML root from the packaged Maven resources.
     *
     * @param path absolute classpath path of the FXML file.
     * @return root node of the loaded screen.
     * @throws IOException if the resource cannot be found or parsed.
     */
    private static Parent loadView(String path) throws IOException {
        URL location = ViewController.class.getResource(path);
        if (location == null) {
            throw new IOException("FXML not found in resources: " + path);
        }
        try {
            Parent root = new FXMLLoader(location).load();
            configureHover(root);
            return root;
        } catch (IOException | RuntimeException error) {
            throw new IOException("FXML load failed at " + location, error);
        }
    }

    /**
     * Adds immediate darker hover feedback to buttons whose normal colors are
     * declared in each FXML element's inline JavaFX CSS style attribute.
     * No external stylesheet or animation is required.
     *
     * @param root root node of the loaded FXML view.
     */
    private static void configureHover(Parent root) {
        applyHoverRecursively(root);
    }

    /**
     * Adds mouse enter/exit handlers to a button and visits all child nodes.
     *
     * @param node current FXML node to inspect.
     */
    private static void applyHoverRecursively(Node node) {
        if (node instanceof Button button) {
            String normalStyle = button.getStyle();
            Matcher matcher = BUTTON_COLOR.matcher(normalStyle);
            String hoverColor = matcher.find()
                    ? "derive(" + matcher.group(1) + ", -20%)" : "#C5C5C5";
            button.setOnMouseEntered(event -> button.setStyle(normalStyle
                    + " -fx-background-color: " + hoverColor + ";"));
            button.setOnMouseExited(event -> button.setStyle(normalStyle));
        }
        if (node instanceof Parent parent) {
            for (Node child : parent.getChildrenUnmodifiable()) {
                applyHoverRecursively(child);
            }
        }
    }

    /**
     * Shows the full cause and stack trace of a failed navigation.
     *
     * @param path resource path that could not be opened.
     * @param error cause of the loading or navigation error.
     */
    private static void showNavigationError(String path, Exception error) {
        Throwable cause = error;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("RU Burger - Navigation Error");
        alert.setHeaderText("Unable to open " + path);
        alert.setContentText(cause.getClass().getSimpleName() + ": " + cause.getMessage()
                + "\nSee Details for the loaded file and complete error.");
        StringWriter trace = new StringWriter();
        error.printStackTrace(new PrintWriter(trace));
        TextArea details = new TextArea(trace.toString());
        details.setEditable(false);
        details.setWrapText(false);
        details.setPrefColumnCount(85);
        details.setPrefRowCount(18);
        alert.getDialogPane().setExpandableContent(details);
        alert.showAndWait();
    }

    /**
     * Registers the main scene shared by all RU Burger screens.
     *
     * @param newScene scene created by the application entry point.
     */
    public static void setScene(Scene newScene) {
        scene = newScene;
    }

    /**
     * Opens the requested screen without altering its FXML layout.
     *
     * @param fxmlFilePath absolute resource path or supported legacy path.
     */
    public static void switchView(String fxmlFilePath) {
        String path = normalizePath(fxmlFilePath);
        if (scene == null || path == null) {
            showNavigationError(fxmlFilePath,
                    new IllegalStateException("Scene or resource path unavailable"));
            return;
        }
        try {
            scene.setRoot(loadView(path));
            if (scene.getWindow() instanceof Stage stage) {
                stage.sizeToScene();
                stage.setTitle("RU Burger - " + path.replace("/", "")
                        .replace("-view.fxml", ""));
            }
        } catch (IOException | RuntimeException error) {
            showNavigationError(path, error);
        }
    }
}
