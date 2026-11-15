package project;

public enum AddOns {
    LETTUCE,
    TOMATOES,
    ONIONS,
    AVOCADO,
    CHEESE;

    public double getPrice() {
        switch(this) {
            case LETTUCE:   return 0.30;
            case TOMATOES:  return 0.30;
            case ONIONS:    return 0.30;
            case AVOCADO:   return 0.50;
            case CHEESE:    return 1.00;
            default:        return 0.0;
        }
    }
}
