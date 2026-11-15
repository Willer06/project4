package project;

public class Combo extends MenuItem {
    private Sandwich sandwich;
    private Beverage drink;
    private Side side;

    public Combo(int quantity, Sandwich sandwich, Beverage drink, Side side) {
        super(quantity);
        this.sandwich = sandwich;
        this.drink = drink;
        this.side = side;
    }

    public void setDrink(Beverage drink) {
        this.drink = drink;
    }
    
    public void setSide(Side side) {
        this.side = side;
    }

    @Override
    public double price() {
        return quantity * (sandwich.price() + drink.price() + side.price());
    }
}
