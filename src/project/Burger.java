package project;

import java.util.ArrayList;

public class Burger extends Sandwich {
    private boolean doublePatty;

    public Burger(int quantity, Bread bread, ArrayList<AddOns> addOns, boolean doublePatty) {
        super(quantity, bread, Protein.BEEF_PATTY, addOns);
        this.doublePatty = doublePatty;
    }

    public boolean getDoublePatty() {
        return doublePatty;
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

    @Override 
    public String toString() {
        StringBuilder addOnString = new StringBuilder();
        for(int i = 0; i < addOns.size(); i++) {
            addOnString.append(addOns.get(i).toString());
            if(i != addOns.size() - 1) addOnString.append(", ");
        }

        return String.format("Burger, %s [%s] [%s] [%d] $.2f",
            doublePatty ? "double" : "single",
            bread.toString(),
            addOnString.toString(),
            quantity,
            price()
        );
    }
}
