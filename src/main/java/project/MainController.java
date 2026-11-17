package project;

import javafx.fxml.FXML;

/**
 * Handles navigation from the RU Burger main menu.
 *
 * @author Benjamin Gluzman, William Becerra
 */
public class MainController {
    /** Opens the main menu again. */
    @FXML
    private void switchToMainView() {
        ViewController.switchView("/main-view.fxml");
    }

    /** Opens the burger customization page. */
    @FXML
    private void switchToBurgerView() {
        ViewController.switchView("/burger-view.fxml");
    }

    /** Opens the sandwich customization page. */
    @FXML
    private void switchToSandwichView() {
        ViewController.switchView("/sandwich-view.fxml");
    }

    /** Opens the beverage customization page. */
    @FXML
    private void switchToBeverageView() {
        ViewController.switchView("/beverage-view.fxml");
        BeverageController.initializeCurrBeverage();
    }

    /** Opens the side customization page. */
    @FXML
    private void switchToSideView() {
        ViewController.switchView("/side-view.fxml");
        SideController.initializeCurrSide();
    }

    /** Opens the active order with refreshed totals. */
    @FXML
    private void switchToCurrentOrderView() {
        ViewController.switchView("/current-order-view.fxml");
        CurrentOrderController.loadCurrentOrder();
    }

    /** Opens the placed orders and refreshes their contents. */
    @FXML
    private void switchToOrdersPlacedView() {
        ViewController.switchView("/orders-placed-view.fxml");
        OrdersPlacedController.refreshPlacedOrders();
    }
}
