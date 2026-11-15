package project;

import java.util.ArrayList;

public class Sandwich extends MenuItem {
    protected Bread bread;
    protected Protein protein;
    protected ArrayList<AddOns> addOns;

    public Sandwich(int quantity, Bread bread, Protein protein) {
        super(quantity);
        this.bread = bread;
        this.protein = protein;
        this.addOns = new ArrayList<>();
    }

    public void setBread(Bread bread) {
        this.bread = bread;
    }

    public void setProtein(Protein protein) {
        this.protein = protein;
    }

    public void appendAddOn(AddOns addOn) {
        this.addOns.add(addOn);
    }

    public void removeAddOn(AddOns addOn) {
        this.addOns.remove(addOn);
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

        return String.format("Sandwich, [%s] [%s] [%s] [%d] $.2f",
            protein.toString(),
            bread.toString(),
            addOnString.toString(),
            quantity,
            price()
        );
    }
}
