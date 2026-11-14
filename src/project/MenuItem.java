package project;

public abstract class MenuItem {
    protected int quantity;
    public abstract double price();

    public MenuItem(int quantity) {
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
