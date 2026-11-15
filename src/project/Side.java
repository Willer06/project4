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
        double singlePrice = 0.0;
        switch(size) {
            case SMALL:     singlePrice = sideOption.getPrice();
            case MEDIUM:    singlePrice = sideOption.getPrice() + 0.50;
            case LARGE:     singlePrice = sideOption.getPrice() + 1.00;
        }

        return quantity * singlePrice;
    }
}
