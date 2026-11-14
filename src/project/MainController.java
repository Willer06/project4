package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

public class MainController {
    @FXML 
    private Button burgersButton;

    @FXML 
    private Button sandwichesButton;

    @FXML 
    private Button beveragesButton;

    @FXML 
    private Button sidesButton;

    @FXML 
    private Button cartButton;

    @FXML 
    private Button receiptButton;

    
    @FXML 
    private void switchToBurgers() {
        ViewSwitcher.switchView("../resources/burger-view.fxml");
    }

    @FXML 
    private void switchToSandwiches() {
        ViewSwitcher.switchView("../resources/sandwich-view.fxml");
    }

    @FXML 
    private void switchToBeverages() {
        ViewSwitcher.switchView("../resources/beverage-view.fxml");
    }

    @FXML 
    private void switchToSides() {
        ViewSwitcher.switchView("../resources/side-view.fxml");
    }

    @FXML 
    private void switchToCart() {
        
    }

    @FXML 
    private void switchToReceipt() {
        
    }
}