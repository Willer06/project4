package project;

public enum Protein {
    ROAST_BEEF,
    SALMON,
    CHICKEN,
    BEEF_PATTY;

    public double getPrice() {
        switch(this) {
            case ROAST_BEEF:    return 10.99;
            case CHICKEN:       return 8.99;
            case SALMON:        return 9.99;
            case BEEF_PATTY:    return 6.99;
            default:            return 0.0;
        }
    }
}
