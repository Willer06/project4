package project;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 * Lists placed orders and supports cancellation and text export.
 *
 * @author Benjamin Gluzman, William Becerra
 */
public class OrdersPlacedController {
    /** New Jersey sales tax used when presenting completed orders. */
    private static final double SALES_TAX = 0.06625;
    /** Persistent orders maintained for the running application session. */
    private static final ArrayList<Order> PLACED_ORDERS = new ArrayList<>();
    /** Latest loaded placed-orders controller. */
    private static OrdersPlacedController instance;
    /** Selector of saved orders by identification number. */
    @FXML private ComboBox<Order> ordersComboBox;
    /** Read-only total for the selected placed order. */
    @FXML private TextField totalAmountTextField;
    /** Selectable rows describing all items in the selected order. */
    @FXML private ListView<String> orderDetailsListView;

    /** Initializes the placed-order list and its selection listener. */
    @FXML
    public void initialize() {
        instance = this;
        totalAmountTextField.setEditable(false);
        ordersComboBox.valueProperty().addListener((obs, before, after) -> displayOrder());
        refreshOrders();
    }

    /**
     * Adds an order to the placed-order collection.
     *
     * @param order nonempty order to be saved.
     */
    public static void saveOrder(Order order) {
        if(order != null && !order.getItems().isEmpty()) {
            PLACED_ORDERS.add(order);
            refreshPlacedOrders();
        }
    }

    /** Refreshes the order selector on the currently loaded screen. */
    public static void refreshPlacedOrders() {
        if(instance != null) {
            instance.refreshOrders();
        }
    }

    /** Reloads the order selector and preserves an existing selection. */
    private void refreshOrders() {
        Order selected = ordersComboBox.getValue();
        ordersComboBox.setItems(FXCollections.observableArrayList(PLACED_ORDERS));
        
        if(selected != null && PLACED_ORDERS.contains(selected)) {
            ordersComboBox.setValue(selected);
        }
        else if(!PLACED_ORDERS.isEmpty()) {
            ordersComboBox.setValue(PLACED_ORDERS.get(0));
        }
        else {
            ordersComboBox.setValue(null);
        }
        displayOrder();
    }

    /** Displays the selected order's items and total amount. */
    private void displayOrder() {
        Order selected = ordersComboBox.getValue();
        if (selected == null) {
            orderDetailsListView.getItems().clear();
            totalAmountTextField.clear();
            return;
        }
        
        for(MenuItem item : selected.getItems()) {
            selected.getItems().add(item);
        }
        
        totalAmountTextField.setText(String.format("%.2f", selected.subtotal() * (1.0 + SALES_TAX)));
    }

    /** Cancels and removes the selected placed order. */
    @FXML
    private void cancelOrder() {
        Order selected = ordersComboBox.getValue();
        if(selected == null) {
            new Alert(Alert.AlertType.WARNING, "Select an order to cancel.").showAndWait();
            return;
        }

        Alert prompt = new Alert(Alert.AlertType.CONFIRMATION, "Cancel " + selected + "?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> answer = prompt.showAndWait();

        if(answer.isPresent() && answer.get() == ButtonType.YES) {
            PLACED_ORDERS.remove(selected);
            refreshOrders();
        }
    }

    /**
     * Produces the full text content for order export.
     *
     * @return printable text describing all placed orders.
     */
    private String exportText() {
        StringBuilder builder = new StringBuilder("RU Burger - Placed Orders\n\n");
        for(Order order : PLACED_ORDERS) {
            builder.append(order).append(System.lineSeparator());
            for(project.MenuItem item : order.getItems()) {
                builder.append("  ").append(item).append(System.lineSeparator());
            }
            builder.append(String.format("  Total (with tax): $%.2f\n\n", order.subtotal() * (1.0 + SALES_TAX)));
        }
        return builder.toString();
    }

    /** Allows the user to export all placed orders to a text file. */
    @FXML
    private void exportOrders() {
        if(PLACED_ORDERS.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "There are no orders to export.").showAndWait();
            return;
        }

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Export RU Burger Orders");
        chooser.setInitialFileName("ru-burger-orders.txt");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text files", "*.txt"));
        File file = chooser.showSaveDialog(ordersComboBox.getScene().getWindow());
        
        if(file == null) return;
        
        try {
            Files.writeString(file.toPath(), exportText());
            new Alert(Alert.AlertType.INFORMATION, "Orders exported successfully.").showAndWait();
        }
        catch (IOException error) {
            new Alert(Alert.AlertType.ERROR, "Export failed: " + error.getMessage()).showAndWait();
        }
    }

    /** Returns to the main menu. */
    @FXML
    private void switchToMainView() {
        ViewController.switchView("/main-view.fxml");
    }
}
