package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;

public class MainController {
    // @FXML 
    // private Button burgersButton;

    // @FXML 
    // private Button sandwichesButton;

    // @FXML 
    // private Button beveragesButton;

    // @FXML 
    // private Button sidesButton;

    // @FXML 
    // private Button currentOrderButton;

    // @FXML 
    // private Button ordersPlacedButton;

    @FXML
    private void switchToMainView() {
        ViewController.switchView("../resources/main-view.fxml");
    }

    @FXML 
    private void switchToBurgerView() {
        ViewController.switchView("../resources/burger-view.fxml");
    }

    @FXML 
    private void switchToSandwichView() {
        ViewController.switchView("../resources/sandwich-view.fxml");
    }

    @FXML 
    private void switchToBeverageView() {
        ViewController.switchView("../resources/beverage-view.fxml");
    }

    @FXML 
    private void switchToSideView() {
        ViewController.switchView("../resources/side-view.fxml");
    }

    @FXML 
    private void switchToCurrentOrderView() {
        ViewController.switchView("../resources/current-order-view.fxml");
    }

    @FXML 
    private void switchToOrdersPlacedView() {
        ViewController.switchView("../resources/orders-placed-view.fxml");
    }
}