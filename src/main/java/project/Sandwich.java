package project;

import java.util.ArrayList;

/**
 * Represents a sandwich customized by bread, protein, and add-ons.
 *
 * @author Benjamin Gluzman, William Becerra
 */
public class Sandwich extends MenuItem {
    /** Bread chosen for the sandwich. */
    protected Bread bread;
    /** Protein chosen for the sandwich. */
    protected Protein protein;
    /** Optional toppings on the sandwich. */
    protected ArrayList<AddOns> addOns;

    /**
     * Constructs an empty-topping sandwich.
     *
     * @param quantity number of sandwiches.
     * @param bread sandwich bread.
     * @param protein sandwich protein.
     */
    public Sandwich(int quantity, Bread bread, Protein protein) {
        super(quantity);
        this.bread = java.util.Objects.requireNonNull(bread, "Bread is required");
        this.protein = java.util.Objects.requireNonNull(protein, "Protein is required");
        this.addOns = new ArrayList<>();
    }

    /**
     * Changes the bread selection.
     *
     * @param bread newly selected bread.
     */
    public void setBread(Bread bread) {
        this.bread = java.util.Objects.requireNonNull(bread);
    }

    /**
     * Changes the protein selection.
     *
     * @param protein newly selected protein.
     */
    public void setProtein(Protein protein) {
        this.protein = java.util.Objects.requireNonNull(protein);
    }

    /**
     * Adds a topping if it is not present.
     *
     * @param addOn topping to add.
     */
    public void appendAddOn(AddOns addOn) {
        if (addOn != null && !addOns.contains(addOn)) {
            addOns.add(addOn);
        }
    }

    /**
     * Removes a topping from the sandwich.
     *
     * @param addOn topping to remove.
     */
    public void removeAddOn(AddOns addOn) {
        addOns.remove(addOn);
    }

    /**
     * Computes the price of all sandwiches and selected toppings.
     *
     * @return sandwich total price.
     */
    @Override
    public double price() {
        double extra = 0.0;
        for (AddOns option : addOns) {
            extra += option.getPrice();
        }
        return quantity * (protein.getPrice() + extra);
    }

    /**
     * Describes the sandwich for order lists and receipts.
     *
     * @return readable description and price.
     */
    @Override
    public String toString() {
        return "Sandwich [" + quantity + "] " + protein + " [" + bread
                + "] " + addOns + " $" + String.format(java.util.Locale.US, "%.2f", price());
    }
}
