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
}
