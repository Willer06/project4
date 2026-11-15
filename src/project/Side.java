package project;

public class Side extends MenuItem {
    private Size size;
    private SideOption sideOption;

    public Side(int quantity, Size size, SideOption sideOption) {
        super(quantity);
        this.size = size;
        this.sideOption = sideOption;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setSide(SideOption sideOption) {
        this.sideOption = sideOption;
    }

    @Override 
    public double price() {
        switch(size) {
            case SMALL:     return quantity * sideOption.getPrice();
            case MEDIUM:    return quantity * (sideOption.getPrice() + 0.50);
            case LARGE:     return quantity * (sideOption.getPrice() + 1.00);
            default:        return 0.0;
        }
    }

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
