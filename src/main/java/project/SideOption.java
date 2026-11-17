package project;

/**
 * Represents side options and their prices for the small size.
 *
 * @author Benjamin Gluzman, William Becerra
 */
public enum SideOption {
    /** The chips option. */
    CHIPS,

    /** The fries option. */
    FRIES,

    /** The onion rings option. */
    ONION_RINGS,

    /** The apple slices option. */
    APPLE_SLICES;

    /** Base price of chips. */
    private static final double CHIPS_PRICE = 1.99;

    /** Base price of fries. */
    private static final double FRIES_PRICE = 2.49;

    /** Base price of onion rings. */
    private static final double ONION_RINGS_PRICE = 3.29;

    /** Base price of apple slices. */
    private static final double APPLE_SLICES_PRICE = 1.29;

    /**
     * Returns the base price of this option.
     *
     * @return the base price in dollars.
     */
    public double getPrice() {
        switch (this) {
            case CHIPS:
                return CHIPS_PRICE;
            case FRIES:
                return FRIES_PRICE;
            case ONION_RINGS:
                return ONION_RINGS_PRICE;
            case APPLE_SLICES:
                return APPLE_SLICES_PRICE;
            default:
                return 0.0;
        }
    }
}
