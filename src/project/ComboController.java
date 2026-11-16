package project;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.util.List;

public class ComboController {
    @FXML 
    private TextField sandwichTextField;

    @FXML 
    private ComboBox<SideOption> sideComboBox;

    @FXML 
    private ComboBox<Flavor> drinkComboBox;

    @FXML
    private ComboBox<Integer> quantityComboBox;

    @FXML 
    private TextField priceTextField;


    private static ComboController instance;

    private static Combo currCombo;

    @FXML 
    public void initialize() {
        instance = this;

        sideComboBox.setItems(FXCollections.observableArrayList(List.of(SideOption.CHIPS, SideOption.APPLE_SLICES)));
        sideComboBox.setValue(SideOption.CHIPS);

        drinkComboBox.setItems(FXCollections.observableArrayList(List.of(Flavor.COLA, Flavor.TEA, Flavor.JUICE)));
        drinkComboBox.setValue(Flavor.COLA);
    }

    public static void initializeCurrCombo(Sandwich sandwich) {
        instance.sandwichTextField.setText(sandwich.toString());

        currCombo = new Combo(0, sandwich, null, null);
        instance.updateCurrCombo();
    }

    @FXML 
    private void updateCurrCombo() {
        Side side = new Side(1, Size.SMALL, sideComboBox.getValue());
        Beverage drink = new Beverage(1, Size.MEDIUM, drinkComboBox.getValue());
        Integer quantity = quantityComboBox.getValue();

        currCombo.setSide(side);
        currCombo.setDrink(drink);
        currCombo.setQuantity(quantity);

        priceTextField.setText(String.format("$%.2f", currCombo.price()));
    }

    @FXML 
    private void addToOrder() {
        CurrentOrderController.currOrder.addItem(currCombo);
    }

    @FXML
    private void switchToMainView() {
        ViewController.switchView("../resources/main-view.fxml");
    }
}