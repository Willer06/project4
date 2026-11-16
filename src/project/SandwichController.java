package project;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import javafx.scene.layout.HBox;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.Node;

public class SandwichController {
    @FXML 
    private HBox breadHBox;

    @FXML 
    private ToggleGroup breadToggleGroup;

    @FXML
    private HBox proteinHBox;

    @FXML 
    private ToggleGroup proteinToggleGroup;

    @FXML 
    private HBox addOnHBox;

    @FXML
    private ComboBox<Integer> quantityComboBox;

    @FXML 
    private TextField priceTextField;

    private static SandwichController instance;

    private static Sandwich currSandwich;

    @FXML 
    public void initialize() {
        instance = this;

        for(Bread bread : Bread.values()) {
            RadioButton rb = new RadioButton(bread.toString());
            rb.setUserData(bread);
            rb.setToggleGroup(breadToggleGroup);
            if(bread == Bread.BRIOCHE) rb.setSelected(true);
            rb.setOnAction(event -> { updateCurrSandwich(); });

            breadHBox.getChildren().add(rb);
        }

        for(Protein protein : Protein.values()) {
            if(protein == Protein.BEEF_PATTY) continue;

            RadioButton rb = new RadioButton(protein.toString());
            rb.setUserData(protein);
            rb.setToggleGroup(proteinToggleGroup);
            if(protein == Protein.ROAST_BEEF) rb.setSelected(true);
            rb.setOnAction(event -> { updateCurrSandwich(); });

            proteinHBox.getChildren().add(rb);
        }

        for(AddOns addOn : AddOns.values()) {
            CheckBox cb = new CheckBox(addOn.toString());
            cb.setUserData(addOn);
            cb.setOnAction(event -> { updateCurrSandwich(); });
            
            addOnHBox.getChildren().add(cb);
        }
    }

    public static void initializeCurrSandwich() {
        instance.updateCurrSandwich();
    }

    @FXML 
    private void updateCurrSandwich() {
        Bread bread = (Bread) breadToggleGroup.getSelectedToggle().getUserData();
        Protein protein = (Protein) proteinToggleGroup.getSelectedToggle().getUserData();
        
        ArrayList<AddOns> addOns = new ArrayList<>();
        for(Node node : addOnHBox.getChildren()) {
            if(node instanceof CheckBox cb && cb.isSelected()) {
                addOns.add((AddOns) cb.getUserData());
            }
        }

        Integer quantity = quantityComboBox.getValue();

        currSandwich = new Sandwich(quantity, bread, protein, addOns);
        priceTextField.setText(String.format("$%.2f", currSandwich.price()));
    }

    @FXML 
    private void addToOrder() {
        CurrentOrderController.currOrder.addItem(currSandwich);
    }

    @FXML 
    private void switchToComboView() {
        ViewController.switchView("../resources/combo-view.fxml");
        ComboController.initializeCurrCombo(currSandwich);
    }

    @FXML
    private void switchToMainView() {
        ViewController.switchView("../resources/main-view.fxml");
    }
}