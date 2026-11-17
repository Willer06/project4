package project;

/**
 * Represents a burger or sandwich combo with an included side and drink.
 *
 * @author Benjamin Gluzman, William Becerra
 */
public class Combo extends MenuItem {
    /** Fixed additional unit cost for a combo. */
    private static final double COMBO_EXTRA_PRICE = 2.00;
    /** Main sandwich or burger in the combo. */
    private Sandwich sandwich;
    /** Included medium beverage. */
    private Beverage drink;
    /** Included small side. */
    private Side side;

    /**
     * Constructs a combo with the selected parts.
     *
     * @param quantity number of combos.
     * @param sandwich main burger or sandwich.
     * @param drink included drink.
     * @param side included side.
     */
    public Combo(int quantity, Sandwich sandwich, Beverage drink, Side side) {
        super(quantity);
        this.sandwich = sandwich;
        this.drink = drink;
        this.side = side;
    }

    /**
     * Changes the included beverage.
     *
     * @param drink replacement beverage.
     */
    public void setDrink(Beverage drink) {
        this.drink = drink;
    }

    /**
     * Changes the included side.
     *
     * @param side replacement side.
     */
    public void setSide(Side side) {
        this.side = side;
    }

    /**
     * Applies the two-dollar combo charge to one main item per combo.
     *
     * @return total price of all combos.
     */
    @Override
    public double price() {
        double mainUnitPrice = sandwich.price() / sandwich.quantity;
        return quantity * (mainUnitPrice + COMBO_EXTRA_PRICE);
    }

    /**
     * Describes the combo for the order list.
     *
     * @return combo details and calculated total.
     */
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

        return String.format("Combo, %s [%s] [%s] [%s] [%s] [%d] $%.2f",
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
