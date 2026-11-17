package project;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * Controls burger choices, running price, and burger/combo ordering.
 *
 * @author Benjamin Gluzman, William Becerra
 */
public class BurgerController {
    /** Maximum quantity of burgers in one selection. */
    private static final int MAX_QUANTITY = 10;
    /** Single-patty option. */
    @FXML private RadioButton singleRadio;
    /** Double-patty option. */
    @FXML private RadioButton doubleRadio;
    /** Brioche bread. */
    @FXML private RadioButton briocheRadio;
    /** Wheat bread. */
    @FXML private RadioButton wheatRadio;
    /** Pretzel bread. */
    @FXML private RadioButton pretzelRadio;
    /** Lettuce topping. */
    @FXML private CheckBox lettuceCheck;
    /** Tomatoes topping. */
    @FXML private CheckBox tomatoesCheck;
    /** Onions topping. */
    @FXML private CheckBox onionsCheck;
    /** Avocado topping. */
    @FXML private CheckBox avocadoCheck;
    /** Cheese topping. */
    @FXML private CheckBox cheeseCheck;
    /** Number of selected burgers. */
    @FXML private ComboBox<Integer> quantityComboBox;
    /** Read-only total price for the burger selection. */
    @FXML private TextField priceTextField;

    /** Initializes toggles, quantity choices, and price listeners. */
    @FXML
    public void initialize() {
        ToggleGroup patties = new ToggleGroup();
        singleRadio.setToggleGroup(patties);
        doubleRadio.setToggleGroup(patties);
        singleRadio.setSelected(true);
        ToggleGroup breads = new ToggleGroup();
        briocheRadio.setToggleGroup(breads);
        wheatRadio.setToggleGroup(breads);
        pretzelRadio.setToggleGroup(breads);
        briocheRadio.setSelected(true);
        for (int quantity = 1; quantity <= MAX_QUANTITY; quantity++) {
            quantityComboBox.getItems().add(quantity);
        }
        quantityComboBox.setValue(1);
        priceTextField.setEditable(false);
        registerListeners();
        updatePrice();
    }

    /** Listens to every configurable burger choice. */
    private void registerListeners() {
        RadioButton[] radios = {singleRadio, doubleRadio, briocheRadio,
                wheatRadio, pretzelRadio};
        CheckBox[] extras = {lettuceCheck, tomatoesCheck, onionsCheck,
                avocadoCheck, cheeseCheck};
        for (RadioButton option : radios) {
            option.selectedProperty().addListener((obs, before, after) -> updatePrice());
        }
        for (CheckBox option : extras) {
            option.selectedProperty().addListener((obs, before, after) -> updatePrice());
        }
        quantityComboBox.valueProperty()
                .addListener((obs, before, after) -> updatePrice());
    }

    /**
     * Builds a burger from the currently selected options.
     *
     * @param quantity number of burgers to construct.
     * @return configured burger.
     */
    private Burger buildBurger(int quantity) {
        Bread bread = wheatRadio.isSelected() ? Bread.WHEAT_BREAD
                : (pretzelRadio.isSelected() ? Bread.PRETZEL : Bread.BRIOCHE);
        Burger burger = new Burger(quantity, bread);
        burger.setDoublePatty(doubleRadio.isSelected());
        if (lettuceCheck.isSelected()) burger.appendAddOn(AddOns.LETTUCE);
        if (tomatoesCheck.isSelected()) burger.appendAddOn(AddOns.TOMATOES);
        if (onionsCheck.isSelected()) burger.appendAddOn(AddOns.ONIONS);
        if (avocadoCheck.isSelected()) burger.appendAddOn(AddOns.AVOCADO);
        if (cheeseCheck.isSelected()) burger.appendAddOn(AddOns.CHEESE);
        return burger;
    }

    /** Updates the running burger price. */
    @FXML
    private void updatePrice() {
        Integer quantity = quantityComboBox.getValue();
        if (quantity == null || quantity < 1) {
            priceTextField.clear();
            return;
        }
        priceTextField.setText(String.format("%.2f", buildBurger(quantity).price()));
    }

    /** Adds the customized burger to the active order. */
    @FXML
    private void addToOrder() {
        Integer quantity = quantityComboBox.getValue();
        if (quantity == null || quantity < 1) {
            new Alert(Alert.AlertType.WARNING, "Select a valid quantity.").showAndWait();
            return;
        }
        CurrentOrderController.currOrder.addItem(buildBurger(quantity));
        new Alert(Alert.AlertType.INFORMATION, "Burger added to Current Order.").showAndWait();
    }

    /** Opens the combo page using the selected burger and quantity. */
    @FXML
    private void openComboView() {
        Integer quantity = quantityComboBox.getValue();
        if (quantity == null || quantity < 1) return;
        ComboController.prepareCombo(buildBurger(1), quantity);
        ViewController.switchView("/combo-view.fxml");
        ComboController.refreshPreparedCombo();
    }

    /** Returns to the main menu. */
    @FXML
    private void switchToMainView() {
        ViewController.switchView("/main-view.fxml");
    }
}
