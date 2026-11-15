package project;

public class Beverage extends MenuItem {
    private Size   size;
    private Flavor flavor;

    public Beverage(int quantity, Size size, Flavor flavor) {
        super(quantity);
        this.size = size;
        this.flavor = flavor;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setFlavor(Flavor flavor) {
        this.flavor = flavor;
    }

    @Override
    public double price() {
        switch(size) {
            case SMALL:     return 1.99;
            case MEDIUM:    return 2.49;
            case LARGE:     return 2.99;
            default:        return 0.0;
        }
    }
}
