package project;

public class Combo extends MenuItem {
    private static final double EXTRA_COMBO_PRICE = 2.00;

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
        return quantity * (sandwich.price() + EXTRA_COMBO_PRICE);
    }

    @Override 
    public String toString() {
        StringBuilder addOnString = new StringBuilder();
        for(int i = 0; i < sandwich.addOns.size(); i++) {
            addOnString.append(sandwich.addOns.get(i).toString());
            if(i != sandwich.addOns.size() - 1) addOnString.append(", ");
        }

        String sandwichType = "";
        if(sandwich instanceof Burger) {
            Burger burger = (Burger)sandwich;
            sandwichType = "Burger, " + (burger.getDoublePatty() ? "double" : "single");
        }
        else {
            sandwichType = "Sandwich";
        }

        return String.format("Combo, %s [%s] [%s] [%s] [%s] [%d] $.2f",
            sandwichType,
            sandwich.bread.toString(),
            addOnString.toString(),
            drink.toString(),
            side.toString(),
            quantity,
            price()
        );
    }
}
