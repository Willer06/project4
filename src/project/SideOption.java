package project;

public enum SideOption {
    CHIPS,
    FRIES,
    ONION_RINGS,
    APPLE_SLICES;

    public double getPrice() {
        switch(this) {
            case CHIPS:         return 1.99;
            case FRIES:         return 2.49;
            case ONION_RINGS:   return 3.29;
            case APPLE_SLICES:  return 1.29;
            default:            return 0.0;
        }
    }
}