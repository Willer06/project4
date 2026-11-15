package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

public class SandwichController {

    @FXML 
    private void addToOrder() {
    
    }

    @FXML
    private void switchToMainView() {
        ViewController.switchView("../resources/main-view.fxml");
    }
}