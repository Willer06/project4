package project;

import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class OrdersPlacedController {
    @FXML 
    private ComboBox<Integer> orderSelectionComboBox;

    @FXML 
    private TextField totalAmountTextField;

    @FXML 
    private ListView<MenuItem> orderPlacedListView;

    private static OrdersPlacedController instance;

    public static ArrayList<Order> currOrders;
    
    static {
        currOrders = new ArrayList<>();
    }

    @FXML 
    public void initialize() {
        instance = this;
    }

    public static void initializeOrdersPlaced() {
        instance.updateOrdersPlaced();
    }

    @FXML 
    private void updateOrdersPlaced() {
        orderSelectionComboBox.getItems().clear();

        for(Order order : currOrders) {
            orderSelectionComboBox.getItems().add(order.getNumber());
        }

        totalAmountTextField.setText(String.format("$%.2f", calculateTotalAmount()));
        orderSelectionComboBox.getSelectionModel().selectFirst();
        updateOrderPlacedListView();
    }

    @FXML 
    private void updateOrderPlacedListView() {
        orderPlacedListView.getItems().clear();

        Order selectedOrder = getSelectedOrder();
        if(selectedOrder == null) return;


        for(MenuItem item : selectedOrder.getItems()) {
            orderPlacedListView.getItems().add(item);
        }
    }

    private Order getSelectedOrder() {
        Integer selectedOrderNumber = orderSelectionComboBox.getValue();
        if(selectedOrderNumber == null) return null;

        for(Order order : currOrders) {
            if(order.getNumber() == selectedOrderNumber) return order;
        }

        return null;
    }

    private double calculateTotalAmount() {
        double totalAmount = 0.0;

        for(Order order : currOrders) {
            for(MenuItem item : order.getItems()) {
                totalAmount += item.price();
            }
        }

        return totalAmount;
    }

    @FXML 
    private void handleCancelOrder() {
        Order selectedOrder = getSelectedOrder();
        if(selectedOrder == null) return;

        currOrders.remove(selectedOrder);
        updateOrdersPlaced();
    }

    @FXML 
    private void handleExportOrders() {
        StringBuilder res = new StringBuilder();

        res.append(String.format("TOTAL ORDER AMOUNT: %.2f\n\n", calculateTotalAmount()));
        for(Order order : currOrders) {
            res.append(String.format("ORDER #%d:\n", order.getNumber()));

            for(MenuItem item : order.getItems()) {
                res.append(String.format("\t%s\n", item.toString()));
            }
        }

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("./exported_orders.txt"))) {
            writer.write(res.toString());
        }
        catch(IOException e) { }
    }

    @FXML
    private void switchToMainView() {
        ViewController.switchView("../resources/main-view.fxml");
    }
}