package project;

import java.util.ArrayList;

/**
 * Represents a numbered customer order and its menu items.
 *
 * @author Benjamin Gluzman, William Becerra
 */
public class Order {
    /** Unique number identifying this order. */
    private int number;
    /** Menu items belonging to the order. */
    private ArrayList<MenuItem> items;

    /**
     * Creates an empty order with a unique identifier.
     *
     * @param number identifier for the order.
     */
    public Order(int number) {
        this.number = number;
        this.items = new ArrayList<>();
    }

    /**
     * Returns the ordered items.
     *
     * @return mutable list of menu items.
     */
    public ArrayList<MenuItem> getItems() {
        return items;
    }

    /**
     * Retrieves the order identifier.
     *
     * @return order number.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Adds one menu item to the order.
     *
     * @param item product to add.
     */
    public void addItem(MenuItem item) {
        if(item != null) {
            items.add(item);
        }
    }

    /**
     * Removes one selected menu item.
     *
     * @param item product to remove.
     * @return true if the item was found and removed.
     */
    public boolean removeItem(MenuItem item) {
        return items.remove(item);
    }

    /**
     * Computes subtotal before tax.
     *
     * @return order subtotal.
     */
    public double subtotal() {
        double total = 0.0;
        for(MenuItem item : items) {
            total += item.price();
        }
        return total;
    }

    /**
     * Provides a readable order label.
     *
     * @return order number for display.
     */
    @Override
    public String toString() {
        return String.format("Order #%d", number);
    }
}
