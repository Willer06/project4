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
        this.bread = bread;
        this.protein = protein;
        this.addOns = new ArrayList<>();
    }

    /**
     * Changes the bread selection.
     *
     * @param bread newly selected bread.
     */
    public void setBread(Bread bread) {
        this.bread = bread;
    }

    /**
     * Changes the protein selection.
     *
     * @param protein newly selected protein.
     */
    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    /**
     * Adds a topping if it is not present.
     *
     * @param addOn topping to add.
     */
    public void appendAddOn(AddOns addOn) {
        if(addOn != null && !addOns.contains(addOn)) {
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
        StringBuilder addOnString = new StringBuilder();
        for(int i = 0; i < addOns.size(); i++) {
            addOnString.append(addOns.get(i).toString());
            if(i != addOns.size() - 1) addOnString.append(", ");
        }

        return String.format("Sandwich, [%s] [%s] [%s] [%d] $%.2f",
            protein.toString(),
            bread.toString(),
            addOnString.toString(),
            quantity,
            price()
        );
    }
}
