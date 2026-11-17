package project;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * Controls side selection, price updates, and adding sides to the order.
 *
 * @author Benjamin Gluzman, William Becerra
 */
public class SideController {
    /** Maximum selectable quantity for sides. */
    private static final int MAX_QUANTITY = 10;
    /** Latest side controller used for navigation compatibility. */
    private static SideController instance;
    /** List of side types. */
    @FXML
    private ListView<SideOption> sideOptionListView;
    /** Selected side size. */
    @FXML
    private ComboBox<Size> sizeComboBox;
    /** Selected side quantity. */
    @FXML
    private ComboBox<Integer> quantityComboBox;
    /** Dynamic side total. */
    @FXML
    private TextField priceTextField;
    /** Side represented by current user selections. */
    private Side currSide;

    /** Initializes selectors and their dynamic-price listeners. */
    @FXML
    public void initialize() {
        instance = this;
        sideOptionListView.setItems(FXCollections.observableArrayList(SideOption.values()));
        sideOptionListView.getSelectionModel().select(SideOption.CHIPS);
        sizeComboBox.setItems(FXCollections.observableArrayList(Size.values()));
        sizeComboBox.setValue(Size.SMALL);
        for (int quantity = 1; quantity <= MAX_QUANTITY; quantity++) {
            quantityComboBox.getItems().add(quantity);
        }
        quantityComboBox.setValue(1);
        priceTextField.setEditable(false);
        registerListeners();
        updateCurrSide();
    }

    /** Registers listeners to update side price after every selection. */
    private void registerListeners() {
        sideOptionListView.getSelectionModel().selectedItemProperty()
                .addListener((observable, before, after) -> updateCurrSide());
        sizeComboBox.valueProperty()
                .addListener((observable, before, after) -> updateCurrSide());
        quantityComboBox.valueProperty()
                .addListener((observable, before, after) -> updateCurrSide());
    }

    /** Updates the side in the most recently initialized view. */
    public static void initializeCurrSide() {
        if (instance != null) {
            instance.updateCurrSide();
        }
    }

    /** Reconstructs the side and displays the selected total price. */
    @FXML
    public void updateCurrSide() {
        SideOption option = sideOptionListView.getSelectionModel().getSelectedItem();
        Size size = sizeComboBox.getValue();
        Integer quantity = quantityComboBox.getValue();
        if (option == null || size == null || quantity == null || quantity < 1) {
            currSide = null;
            priceTextField.clear();
            return;
        }
        currSide = new Side(quantity, size, option);
        priceTextField.setText(String.format("%.2f", currSide.price()));
    }

    /** Adds the current side to the active order. */
    @FXML
    private void addToOrder() {
        updateCurrSide();
        if (currSide == null) {
            new Alert(Alert.AlertType.WARNING, "Select a side, size and quantity.").showAndWait();
            return;
        }
        CurrentOrderController.currOrder.addItem(currSide);
        new Alert(Alert.AlertType.INFORMATION, "Side added to Current Order.").showAndWait();
    }

    /** Returns to the main menu. */
    @FXML
    private void switchToMainView() {
        ViewController.switchView("/main-view.fxml");
    }
}
