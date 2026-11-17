package project;

import java.util.Locale;
import java.util.Optional;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 * Shows current order items, removes selected items, and places orders.
 *
 * @author Benjamin Gluzman, William Becerra
 */
public class CurrentOrderController {
    /** New Jersey sales tax applied to orders. */
    private static final double SALES_TAX = 0.06625;
    /** Sequential identifier assigned to the next current order. */
    private static int uniqueOrderNumber = 1;
    /** Shared currently open order. */
    public static Order currOrder = new Order(uniqueOrderNumber);
    /** Latest loaded current-order screen. */
    private static CurrentOrderController instance;
    /** Field showing subtotal before tax. */
    @FXML private TextField subTotalTextField;
    /** Field showing the sales tax. */
    @FXML private TextField salesTaxTextField;
    /** Field showing the total including tax. */
    @FXML private TextField totalAmountTextField;
    /** Selectable list of actual menu items in the current order. */
    @FXML private ListView<project.MenuItem> currentOrderListView;

    /** Configures the order display, prices, and the clear-all context action. */
    @FXML
    public void initialize() {
        instance = this;
        subTotalTextField.setEditable(false);
        salesTaxTextField.setEditable(false);
        totalAmountTextField.setEditable(false);
        MenuItem clearMenu = new MenuItem("Clear entire order");
        clearMenu.setOnAction(event -> clearOrder());
        currentOrderListView.setContextMenu(new ContextMenu(clearMenu));
        refreshOrderView();
    }

    /** Creates the next numbered, empty order. */
    public static void startNewOrder() {
        uniqueOrderNumber++;
        currOrder = new Order(uniqueOrderNumber);
        loadCurrentOrder();
    }

    /** Updates the current-order view if it has already been loaded. */
    public static void loadCurrentOrder() {
        if (instance != null) {
            instance.refreshOrderView();
        }
    }

    /** Rebuilds the list of items and refreshes tax and total. */
    private void refreshOrderView() {
        currentOrderListView.getItems().setAll(currOrder.getItems());
        updateAmounts(currOrder.subtotal());
    }

    /**
     * Updates all monetary total fields.
     *
     * @param subtotal the subtotal before tax.
     */
    private void updateAmounts(double subtotal) {
        double tax = subtotal * SALES_TAX;
        subTotalTextField.setText(String.format(Locale.US, "%.2f", subtotal));
        salesTaxTextField.setText(String.format(Locale.US, "%.2f", tax));
        totalAmountTextField.setText(String.format(Locale.US, "%.2f", subtotal + tax));
    }

    /** Removes only the currently selected menu item. */
    @FXML
    private void removeSelectedItem() {
        project.MenuItem selected = currentOrderListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Select an item to remove.").showAndWait();
            return;
        }
        currOrder.removeItem(selected);
        refreshOrderView();
    }

    /** Clears every item in the current order after confirmation. */
    private void clearOrder() {
        if (currOrder.getItems().isEmpty()) return;
        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION,
                "Remove all products from the current order?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.YES) {
            currOrder.getItems().clear();
            refreshOrderView();
        }
    }

    /** Saves a nonempty order and starts the next order. */
    @FXML
    private void placeOrder() {
        if (currOrder.getItems().isEmpty()) {
            new Alert(Alert.AlertType.WARNING,
                    "Add an item before placing an order.").showAndWait();
            return;
        }
        int orderNumber = currOrder.getNumber();
        OrdersPlacedController.saveOrder(currOrder);
        startNewOrder();
        new Alert(Alert.AlertType.INFORMATION,
                "Order #" + orderNumber + " placed successfully.").showAndWait();
    }

    /** Returns to the main menu. */
    @FXML
    private void switchToMainView() {
        ViewController.switchView("/main-view.fxml");
    }
}
