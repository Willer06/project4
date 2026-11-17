package project;

/**
 * Represents an item on the RU Burger menu.
 *
 * @author Benjamin Gluzman, William Becerra
 */
public abstract class MenuItem {
    /** Quantity of this menu item. */
    protected int quantity;

    /**
     * Initializes an item with a positive quantity.
     *
     * @param quantity quantity of the item.
     */
    public MenuItem(int quantity) {
        if (quantity < 1) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }
        this.quantity = quantity;
    }

    /**
     * Calculates the price for the entire quantity.
     *
     * @return total item price.
     */
    public abstract double price();
}
