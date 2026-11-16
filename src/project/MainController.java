package project;

import javafx.fxml.FXML;

public class MainController {
    @FXML
    private void switchToMainView() {
        ViewController.switchView("../resources/main-view.fxml");
    }

    @FXML 
    private void switchToBurgerView() {
        ViewController.switchView("../resources/burger-view.fxml");
        BurgerController.initializeCurrBurger();
    }

    @FXML 
    private void switchToSandwichView() {
        ViewController.switchView("../resources/sandwich-view.fxml");
        SandwichController.initializeCurrSandwich();
    }

    @FXML 
    private void switchToBeverageView() {
        ViewController.switchView("../resources/beverage-view.fxml");
        BeverageController.initializeCurrBeverage();
    }

    @FXML 
    private void switchToSideView() {
        ViewController.switchView("../resources/side-view.fxml");
        SideController.initializeCurrSide();
    }

    @FXML 
    private void switchToCurrentOrderView() {
        ViewController.switchView("../resources/current-order-view.fxml");
        CurrentOrderController.loadCurrentOrder();
    }

    @FXML 
    private void switchToOrdersPlacedView() {
        ViewController.switchView("../resources/orders-placed-view.fxml");
    }
}