package project;

import java.net.URL;
import java.util.Locale;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Customizes combo meal sides and drinks, including their preview images.
 *
 * @author Benjamin Gluzman, William Becerra
 */
public class ComboController {
    /** Largest number of combos in one selection. */
    private static final int MAX_QUANTITY = 10;
    /** Most recently loaded combo screen controller. */
    private static ComboController instance;
    /** Burger or sandwich passed from a preceding screen. */
    private static Sandwich preparedSandwich;
    /** Quantity passed from the burger or sandwich screen. */
    private static int preparedQuantity = 1;
    /** Description of the burger or sandwich being ordered. */
    @FXML private TextField sandwichTextField;
    /** Combo side selector, restricted to chips and apple slices. */
    @FXML private ComboBox<SideOption> sideComboBox;
    /** Combo beverage selector, restricted to cola, tea, and juice. */
    @FXML private ComboBox<Flavor> drinkComboBox;
    /** Selected combo quantity. */
    @FXML private ComboBox<Integer> quantityComboBox;
    /** Dynamic total cost of the customized combo. */
    @FXML private TextField priceTextField;
    /** Image representing the chosen side. */
    @FXML private ImageView sideImageView;
    /** Image representing the chosen drink. */
    @FXML private ImageView drinkImageView;

    /** Initializes combo choices, default selections, and listeners. */
    @FXML
    public void initialize() {
        instance = this;
        sideComboBox.setItems(FXCollections.observableArrayList(
                SideOption.CHIPS, SideOption.APPLE_SLICES));
        drinkComboBox.setItems(FXCollections.observableArrayList(
                Flavor.COLA, Flavor.TEA, Flavor.JUICE));
        sideComboBox.setValue(SideOption.CHIPS);
        drinkComboBox.setValue(Flavor.COLA);
        for (int quantity = 1; quantity <= MAX_QUANTITY; quantity++) {
            quantityComboBox.getItems().add(quantity);
        }
        quantityComboBox.setValue(1);
        sandwichTextField.setEditable(false);
        priceTextField.setEditable(false);
        registerListeners();
        refreshCombo();
    }

    /** Registers selection listeners for live pricing and preview images. */
    private void registerListeners() {
        sideComboBox.valueProperty().addListener((obs, before, after) -> refreshCombo());
        drinkComboBox.valueProperty().addListener((obs, before, after) -> refreshCombo());
        quantityComboBox.valueProperty().addListener((obs, before, after) -> refreshCombo());
    }

    /**
     * Passes the main item and quantity into the combo screen.
     *
     * @param sandwich burger or sandwich with quantity one.
     * @param quantity number of combos requested.
     */
    public static void prepareCombo(Sandwich sandwich, int quantity) {
        preparedSandwich = sandwich;
        preparedQuantity = quantity;
    }

    /** Refreshes the prepared combo on the active screen after navigation. */
    public static void refreshPreparedCombo() {
        if (instance != null) {
            instance.quantityComboBox.setValue(preparedQuantity);
            instance.refreshCombo();
        }
    }

    /**
     * Loads the image associated with a combo option.
     *
     * @param imageView destination image view.
     * @param fileName resource filename for the selected option.
     */
    private void showImage(ImageView imageView, String fileName) {
        URL url = getClass().getResource("/" + fileName);
        imageView.setImage(url == null ? null : new Image(url.toExternalForm()));
    }

    /** Updates the images and calculates the running combo total. */
    private void refreshCombo() {
        SideOption side = sideComboBox.getValue();
        Flavor drink = drinkComboBox.getValue();
        Integer quantity = quantityComboBox.getValue();
        showImage(sideImageView, side == SideOption.APPLE_SLICES
                ? "combo_apple_slices.png" : "combo_potato_chips.png");
        String drinkImage = drink == Flavor.TEA ? "combo_tea_drink.png"
                : (drink == Flavor.JUICE ? "combo_orange_juice.png" : "combo_cola_drink.png");
        showImage(drinkImageView, drinkImage);
        sandwichTextField.setText(preparedSandwich == null
                ? "Choose a burger or sandwich first" : preparedSandwich.toString());
        if (preparedSandwich == null || side == null || drink == null || quantity == null) {
            priceTextField.clear();
            return;
        }
        priceTextField.setText(String.format(Locale.US, "%.2f",
                new Combo(quantity, preparedSandwich,
                        new Beverage(1, Size.MEDIUM, drink), new Side(1, Size.SMALL, side)).price()));
    }

    /** Adds the selected combo meal to the active order. */
    @FXML
    private void addToOrder() {
        if (preparedSandwich == null || sideComboBox.getValue() == null
                || drinkComboBox.getValue() == null || quantityComboBox.getValue() == null) {
            new Alert(Alert.AlertType.WARNING, "Choose a burger or sandwich first.").showAndWait();
            return;
        }
        Combo combo = new Combo(quantityComboBox.getValue(), preparedSandwich,
                new Beverage(1, Size.MEDIUM, drinkComboBox.getValue()),
                new Side(1, Size.SMALL, sideComboBox.getValue()));
        CurrentOrderController.currOrder.addItem(combo);
        new Alert(Alert.AlertType.INFORMATION,
                "Combo added to Current Order.").showAndWait();
    }

    /** Returns to the main menu. */
    @FXML
    private void switchToMainView() {
        ViewController.switchView("/main-view.fxml");
    }
}
