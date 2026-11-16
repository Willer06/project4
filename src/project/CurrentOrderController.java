package project;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CurrentOrderController {
    private static int uniqueOrderNumber;
    public static Order currOrder;

    @FXML 
    private TextField subTotalTextField;

    @FXML 
    private TextField salesTaxTextField;

    private static final double SALES_TAX = 0.06625;

    @FXML 
    private TextField totalAmountTextField;

    @FXML 
    private ListView<MenuItem> currentOrderListView;
    
    private static CurrentOrderController instance;

    static {
        uniqueOrderNumber = 1;
        currOrder = new Order(uniqueOrderNumber);
    }

    @FXML 
    public void initialize() {
        instance = this;
    }

    public static void startNewOrder() {
        uniqueOrderNumber++;
        currOrder = new Order(uniqueOrderNumber);
    }

    public static void loadCurrentOrder() {
        instance.currentOrderListView.getItems().clear();

        double subTotal = 0.0;
        for(MenuItem item : currOrder.getItems()) {
            subTotal += item.price();
            instance.currentOrderListView.getItems().add(item);
        }

        instance.subTotalTextField.setText(String.format("%.2f", subTotal));
        instance.salesTaxTextField.setText(String.format("%.2f", subTotal * SALES_TAX));
        instance.totalAmountTextField.setText(String.format("%.2f", subTotal + subTotal * SALES_TAX));
    }

    @FXML
    private void switchToMainView() {
        ViewController.switchView("../resources/main-view.fxml");
    }
}