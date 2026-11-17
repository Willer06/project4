package project;

/**
 * Represents protein types and their base unit prices.
 *
 * @author Benjamin Gluzman, William Becerra
 */
public enum Protein {
    /** The roast beef option. */
    ROAST_BEEF,

    /** The salmon option. */
    SALMON,

    /** The chicken option. */
    CHICKEN,

    /** The beef patty option. */
    BEEF_PATTY;

    /** Base price of roast beef. */
    private static final double ROAST_BEEF_PRICE = 10.99;

    /** Base price of salmon. */
    private static final double SALMON_PRICE = 9.99;

    /** Base price of chicken. */
    private static final double CHICKEN_PRICE = 8.99;

    /** Base price of beef patty. */
    private static final double BEEF_PATTY_PRICE = 6.99;

    /**
     * Returns the base price of this option.
     *
     * @return the base price in dollars.
     */
    public double getPrice() {
        switch (this) {
            case ROAST_BEEF:    return ROAST_BEEF_PRICE;
            case SALMON:        return SALMON_PRICE;
            case CHICKEN:       return CHICKEN_PRICE;
            case BEEF_PATTY:    return BEEF_PATTY_PRICE;
            default:            return 0.0;
        }
    }
}
