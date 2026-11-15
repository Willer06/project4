package project;

public class Burger extends Sandwich {
    private boolean doublePatty;

    public Burger(int quantity, Bread bread) {
        super(quantity, bread, Protein.BEEF_PATTY);
        this.doublePatty = false;
    }

    public void setDoublePatty(boolean doublePatty) {
        this.doublePatty = doublePatty;
    }

    @Override 
    public double price() {
        double addOnPrice = 0.0;
        for(AddOns addOn : addOns) {
            addOnPrice += addOn.getPrice();
        }

        double proteinPrice = doublePatty ? protein.getPrice() + 2.50 : protein.getPrice();
        return quantity * (proteinPrice + addOnPrice);
    }
}
