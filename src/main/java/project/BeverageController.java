package project;

import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Controls drink selection, dynamic pricing, and adding beverages to an order.
 *
 * @author Benjamin Gluzman, William Becerra
 */
public class BeverageController {
    /** Maximum item quantity offered in the selector. */
    private static final int MAX_QUANTITY = 10;
    /** Latest beverage controller, used by the main menu. */
    private static BeverageController instance;
    /** Available drink flavors. */
    @FXML
    private ListView<Flavor> flavorListView;
    /** Selected drink size. */
    @FXML
    private ComboBox<Size> sizeComboBox;
    /** Quantity of the selected drink. */
    @FXML
    private ComboBox<Integer> quantityComboBox;
    /** Read-only running total for the customized drink. */
    @FXML
    private TextField priceTextField;
    /** Newly constructed beverage based on the current selection. */
    private Beverage currBeverage;

    /** Initializes controls and installs dynamic-price listeners. */
    @FXML
    public void initialize() {
        instance = this;
        flavorListView.setItems(FXCollections.observableArrayList(Flavor.values()));
        flavorListView.getSelectionModel().select(Flavor.COLA);
        sizeComboBox.setItems(FXCollections.observableArrayList(Size.values()));
        sizeComboBox.setValue(Size.SMALL);
        for (int quantity = 1; quantity <= MAX_QUANTITY; quantity++) {
            quantityComboBox.getItems().add(quantity);
        }
        quantityComboBox.setValue(1);
        priceTextField.setEditable(false);
        registerListeners();
        updateCurrBeverage();
    }

    /** Registers listeners for selection changes. */
    private void registerListeners() {
        flavorListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, before, after) -> updateCurrBeverage());
        sizeComboBox.valueProperty()
                .addListener((observable, before, after) -> updateCurrBeverage());
        quantityComboBox.valueProperty()
                .addListener((observable, before, after) -> updateCurrBeverage());
    }

    /** Updates the beverage in the most recently loaded view. */
    public static void initializeCurrBeverage() {
        if (instance != null) {
            instance.updateCurrBeverage();
        }
    }

    /** Reconstructs the selected beverage and displays its total. */
    @FXML
    private void updateCurrBeverage() {
        Flavor flavor = flavorListView.getSelectionModel().getSelectedItem();
        Size size = sizeComboBox.getValue();
        Integer quantity = quantityComboBox.getValue();
        if (flavor == null || size == null || quantity == null || quantity < 1) {
            currBeverage = null;
            priceTextField.clear();
            return;
        }
        currBeverage = new Beverage(quantity, size, flavor);
        priceTextField.setText(String.format(Locale.US, "%.2f", currBeverage.price()));
    }

    /** Adds the selected beverage to the active order. */
    @FXML
    private void addToOrder() {
        updateCurrBeverage();
        if (currBeverage == null) {
            new Alert(Alert.AlertType.WARNING, "Choose a flavor, size and quantity.").showAndWait();
            return;
        }
        CurrentOrderController.currOrder.addItem(currBeverage);
        new Alert(Alert.AlertType.INFORMATION, "Beverage added to Current Order.").showAndWait();
    }

    /** Returns to the main menu. */
    @FXML
    private void switchToMainView() {
        ViewController.switchView("/main-view.fxml");
    }
}
