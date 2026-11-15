package project;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class BeverageController {
    @FXML 
    private ListView<Flavor> flavorListView;

    @FXML 
    private ComboBox<Size> sizeComboBox;

    @FXML
    private ComboBox<Integer> quantityComboBox;

    @FXML 
    private TextField priceTextField;

    private static BeverageController instance;

    private static Beverage currBeverage;

    @FXML 
    public void initialize() {
        instance = this;

        flavorListView.setItems(FXCollections.observableArrayList(Flavor.values()));
        flavorListView.getSelectionModel().select(Flavor.COLA);

        sizeComboBox.setItems(FXCollections.observableArrayList(Size.values()));
        sizeComboBox.setValue(Size.SMALL);
    }

    public static void initializeCurrBeverage() {
        instance.updateCurrBeverage();
    }

    @FXML 
    private void updateCurrBeverage() {
        Flavor flavor = flavorListView.getSelectionModel().getSelectedItem();
        Size size = sizeComboBox.getValue();
        Integer quantity = quantityComboBox.getValue();
        
        currBeverage = new Beverage(quantity, size, flavor);

        priceTextField.setText(String.format("%.2f", currBeverage.price()));
    }

    @FXML 
    private void addToOrder() {
        CurrentOrderController.currOrder.addItem(currBeverage);
    }

    @FXML
    private void switchToMainView() {
        ViewController.switchView("../resources/main-view.fxml");
    }
}