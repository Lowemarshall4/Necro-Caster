/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

public class Item {
    protected final String name;
    protected int quantity;
    protected int type;


    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;


    }
    
    // Accessors/Mutators
    
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public void increaseQuantity(int amount) {
        quantity += amount;
    }
    
    public void decreaseQuantity(int amount) {
        quantity -= amount;
    }
    
    public String getDesc() {
        return name + " : " + quantity;
    }
    


    
    //Overrides
    
    @Override
    public String toString() {
        return String.format("%1$s - %2$s", this.name, this.quantity);
    }
    
    
}
