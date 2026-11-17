package project;

/**
 * Represents a beverage with a selected size and flavor.
 *
 * @author Benjamin Gluzman, William Becerra
 */
public class Beverage extends MenuItem {
    /** Price of a small beverage. */
    private static final double SMALL_PRICE = 1.99;
    /** Price of a medium beverage. */
    private static final double MEDIUM_PRICE = 2.49;
    /** Price of a large beverage. */
    private static final double LARGE_PRICE = 2.99;
    /** Selected beverage size. */
    private Size size;
    /** Selected flavor. */
    private Flavor flavor;

    /**
     * Constructs a beverage with a size and flavor.
     *
     * @param quantity number of beverages.
     * @param size size of each beverage.
     * @param flavor beverage flavor.
     */
    public Beverage(int quantity, Size size, Flavor flavor) {
        super(quantity);
        this.size = java.util.Objects.requireNonNull(size);
        this.flavor = java.util.Objects.requireNonNull(flavor);
    }

    /**
     * Changes beverage size.
     *
     * @param size the new size.
     */
    public void setSize(Size size) {
        this.size = java.util.Objects.requireNonNull(size);
    }

    /**
     * Changes the beverage flavor.
     *
     * @param flavor the new flavor.
     */
    public void setFlavor(Flavor flavor) {
        this.flavor = java.util.Objects.requireNonNull(flavor);
    }

    /**
     * Calculates the beverage total.
     *
     * @return price of all beverages.
     */
    @Override
    public double price() {
        switch (size) {
            case SMALL: return quantity * SMALL_PRICE;
            case MEDIUM: return quantity * MEDIUM_PRICE;
            case LARGE: return quantity * LARGE_PRICE;
            default: return 0.0;
        }
    }

    /**
     * Describes the beverage for a receipt.
     *
     * @return beverage description and price.
     */
    @Override
    public String toString() {
        return flavor + ", " + size + " [" + quantity + "] $"
                + String.format(java.util.Locale.US, "%.2f", price());
    }
}
