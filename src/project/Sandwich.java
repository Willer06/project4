package project;

import java.util.ArrayList;

public class Sandwich extends MenuItem {
    protected Bread bread;
    protected Protein protein;
    protected ArrayList<AddOns> addOns;

    public Sandwich(int quantity, Bread bread, Protein protein, ArrayList<AddOns> addOns) {
        super(quantity);
        this.bread = bread;
        this.protein = protein;
        this.addOns = addOns;
    }

    @Override
    public double price() {
        double addOnPrice = 0.0;
        for(AddOns addOn : addOns) {
            addOnPrice += addOn.getPrice();
        }

        return quantity * (protein.getPrice() + addOnPrice);
    }

    @Override 
    public String toString() {
        StringBuilder addOnString = new StringBuilder();
        for(int i = 0; i < addOns.size(); i++) {
            addOnString.append(addOns.get(i).toString());
            if(i != addOns.size() - 1) addOnString.append(", ");
        }

        return String.format("Sandwich, [%s] [%s] [%s] [%d] $%.2f",
            protein.toString(),
            bread.toString(),
            addOnString.toString(),
            quantity,
            price()
        );
    }
}
