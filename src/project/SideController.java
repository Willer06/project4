package project;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class SideController {
    @FXML 
    private ListView<SideOption> sideOptionListView;

    @FXML 
    private ComboBox<Size> sizeComboBox;

    @FXML
    private ComboBox<Integer> quantityComboBox;

    @FXML 
    private TextField priceTextField;

    private static SideController instance;

    private static Side currSide;

    @FXML 
    public void initialize() {
        instance = this;

        sideOptionListView.setItems(FXCollections.observableArrayList(SideOption.values()));
        sideOptionListView.getSelectionModel().select(SideOption.CHIPS);

        sizeComboBox.setItems(FXCollections.observableArrayList(Size.values()));
        sizeComboBox.setValue(Size.SMALL);
    }

    public static void initializeCurrSide() {
        instance.updateCurrSide();
    }

    @FXML 
    public void updateCurrSide() {
        SideOption sideOption = sideOptionListView.getSelectionModel().getSelectedItem();
        Size size = sizeComboBox.getValue();
        Integer quantity = quantityComboBox.getValue();
        
        currSide = new Side(quantity, size, sideOption);

        priceTextField.setText(String.format("%.2f", currSide.price()));
    }

    @FXML 
    private void addToOrder() {
        CurrentOrderController.currOrder.addItem(currSide);
    }
    
    @FXML
    private void switchToMainView() {
        ViewController.switchView("../resources/main-view.fxml");
    }
}