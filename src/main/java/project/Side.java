package project;

/**
 * Represents a side item with a chosen size.
 *
 * @author Benjamin Gluzman, William Becerra
 */
public class Side extends MenuItem {
    /** Additional charge for a medium side. */
    private static final double MEDIUM_SIZE_EXTRA = 0.50;
    /** Additional charge for a large side. */
    private static final double LARGE_SIZE_EXTRA = 1.00;
    /** Chosen side size. */
    private Size size;
    /** Chosen side option. */
    private SideOption sideOption;

    /**
     * Constructs a side item.
     *
     * @param quantity number of sides.
     * @param size side size.
     * @param sideOption side variety.
     */
    public Side(int quantity, Size size, SideOption sideOption) {
        super(quantity);
        this.size = size;
        this.sideOption = sideOption;
    }

    /**
     * Changes the selected side size.
     *
     * @param size the new size.
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Changes the selected side option.
     *
     * @param sideOption the new side option.
     */
    public void setSide(SideOption sideOption) {
        this.sideOption = sideOption;
    }

    /**
     * Calculates side price using the base option and selected size.
     *
     * @return total cost of all sides.
     */
    @Override
    public double price() {
        double unitPrice = sideOption.getPrice();
        switch (size) {
            case SMALL: break;
            case MEDIUM: unitPrice += MEDIUM_SIZE_EXTRA; break;
            case LARGE: unitPrice += LARGE_SIZE_EXTRA; break;
            default: return 0.0;
        }
        return quantity * unitPrice;
    }

    /**
     * Describes the side for an order list.
     *
     * @return side description and price.
     */
    @Override
    public String toString() {
        return String.format("Side, [%s, %s] [%d] $%.2f", 
            sideOption.toString(), 
            size.toString(),
            quantity,
            price()
        );
    }
}
