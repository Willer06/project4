package project;

/**
 * Represents a beef burger with an optional second patty.
 *
 * @author Benjamin Gluzman, William Becerra
 */
public class Burger extends Sandwich {
    /** Additional charge for a second patty. */
    private static final double DOUBLE_PATTY_EXTRA = 2.50;
    /** Whether the burger contains two patties. */
    private boolean doublePatty;

    /**
     * Constructs a single-patty burger.
     *
     * @param quantity number of burgers.
     * @param bread burger bread.
     */
    public Burger(int quantity, Bread bread) {
        super(quantity, bread, Protein.BEEF_PATTY);
        this.doublePatty = false;
    }

    /**
     * Selects the number of patties.
     *
     * @param doublePatty true for two patties; false for one.
     */
    public void setDoublePatty(boolean doublePatty) {
        this.doublePatty = doublePatty;
    }

    /**
     * Returns this burger's doublePatty attribute
     * @return doublePatty attribute
     */
    public boolean getDoublePatty() {
        return doublePatty;
    }

    /**
     * Computes burger prices with patties and selected toppings.
     *
     * @return total price for the selected quantity.
     */
    @Override
    public double price() {
        double extras = 0.0;
        for (AddOns option : addOns) {
            extras += option.getPrice();
        }
        double pattyPrice = protein.getPrice() + (doublePatty ? DOUBLE_PATTY_EXTRA : 0.0);
        return quantity * (pattyPrice + extras);
    }

    /**
     * Returns a burger's string representation
     *
     * @return burger's string representation
     */
    @Override
    public String toString() {
        StringBuilder addOnString = new StringBuilder();
        for(int i = 0; i < addOns.size(); i++) {
            addOnString.append(addOns.get(i).toString());
            if(i != addOns.size() - 1) addOnString.append(", ");
        }

        return String.format("Burger, %s [%s] [%s] [%d] $%.2f",
            doublePatty ? "double" : "single",
            bread.toString(),
            addOnString.toString(),
            quantity,
            price()
        );
    }
}
