package project;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import javafx.scene.layout.HBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Labeled;
import javafx.scene.Node;

public class BurgerController {
    @FXML 
    private ToggleGroup pattyToggleGroup;

    @FXML 
    private HBox breadHBox;

    @FXML 
    private ToggleGroup breadToggleGroup;

    @FXML 
    private HBox addOnHBox;

    @FXML
    private ComboBox<Integer> quantityComboBox;

    @FXML 
    private TextField priceTextField;

    private static BurgerController instance;

    private static Burger currBurger;

    @FXML 
    public void initialize() {
        instance = this;

        for(Bread bread : Bread.values()) {
            if(bread == Bread.BAGEL || bread == Bread.SOURDOUGH) continue;

            RadioButton rb = new RadioButton(bread.toString());
            rb.setUserData(bread);
            rb.setToggleGroup(breadToggleGroup);
            if(bread == Bread.BRIOCHE) rb.setSelected(true);
            rb.setOnAction(event -> { updateCurrBurger(); });

            breadHBox.getChildren().add(rb);
        }

        for(AddOns addOn : AddOns.values()) {
            CheckBox cb = new CheckBox(addOn.toString());
            cb.setUserData(addOn);
            cb.setOnAction(event -> { updateCurrBurger(); });
            
            addOnHBox.getChildren().add(cb);
        }
    }

    public static void initializeCurrBurger() {
        instance.updateCurrBurger();
    }

    @FXML 
    private void updateCurrBurger() {
        boolean doublePatty = ((Labeled) pattyToggleGroup.getSelectedToggle()).getText().equals("DOUBLE");
        Bread bread = (Bread) breadToggleGroup.getSelectedToggle().getUserData();
        
        ArrayList<AddOns> addOns = new ArrayList<>();
        for(Node node : addOnHBox.getChildren()) {
            if(node instanceof CheckBox cb && cb.isSelected()) {
                addOns.add((AddOns) cb.getUserData());
            }
        }

        Integer quantity = quantityComboBox.getValue();

        currBurger = new Burger(quantity, bread, addOns, doublePatty);
        priceTextField.setText(String.format("$%.2f", currBurger.price()));
    }

    @FXML 
    private void addToOrder() {
        CurrentOrderController.currOrder.addItem(currBurger);
    }

    @FXML 
    private void switchToComboView() {
        ViewController.switchView("../resources/combo-view.fxml");
        ComboController.initializeCurrCombo(currBurger);
    }

    @FXML
    private void switchToMainView() {
        ViewController.switchView("../resources/main-view.fxml");
    }
}