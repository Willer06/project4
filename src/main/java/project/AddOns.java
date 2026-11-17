package project;

/**
 * Represents optional burger and sandwich toppings and their prices.
 *
 * @author Benjamin Gluzman, William Becerra
 */
public enum AddOns {
    /** The lettuce option. */
    LETTUCE,

    /** The tomatoes option. */
    TOMATOES,

    /** The onions option. */
    ONIONS,

    /** The avocado option. */
    AVOCADO,

    /** The cheese option. */
    CHEESE;

    /** Base price of lettuce. */
    private static final double LETTUCE_PRICE = 0.30;

    /** Base price of tomatoes. */
    private static final double TOMATOES_PRICE = 0.30;

    /** Base price of onions. */
    private static final double ONIONS_PRICE = 0.30;

    /** Base price of avocado. */
    private static final double AVOCADO_PRICE = 0.50;

    /** Base price of cheese. */
    private static final double CHEESE_PRICE = 1.00;

    /**
     * Returns the base price of this option.
     *
     * @return the base price in dollars.
     */
    public double getPrice() {
        switch (this) {
            case LETTUCE:
                return LETTUCE_PRICE;
            case TOMATOES:
                return TOMATOES_PRICE;
            case ONIONS:
                return ONIONS_PRICE;
            case AVOCADO:
                return AVOCADO_PRICE;
            case CHEESE:
                return CHEESE_PRICE;
            default:
                return 0.0;
        }
    }
}
