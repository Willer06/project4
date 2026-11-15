package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

public class SandwichController {
    private static Sandwich sandwich;


    
    public static void createNewSandwich() {

    }

    @FXML
    private void switchToMainView() {
        ViewController.switchView("../resources/main-view.fxml");
    }
}