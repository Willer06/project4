package project;

import java.util.ArrayList;

public class Order {
    private int number; //a unique integer to identify the order
    private ArrayList<MenuItem> items;

    public Order() {
        // TODO: set this.number to some unique integer

        items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }
}
