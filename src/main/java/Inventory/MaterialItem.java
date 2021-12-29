/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

/**
 *
 * @author Marshall
 */
public class MaterialItem extends Item {
    private final Staff craftableStaff;
    private final Robe craftableRobe;
    private final Ring craftableRing;
    
    public MaterialItem(String name, int quantity, Staff craftableStaff,
                        Robe craftableRobe, Ring craftableRing) {
        super(name, quantity);
        this.craftableStaff = craftableStaff;
        this.craftableRobe = craftableRobe;
        this.craftableRing = craftableRing;
    }
     
    public Staff getCraftableStaff() {
        return craftableStaff;
    }

    public Robe getCraftableRobe() {
        return craftableRobe;
    }

    public Ring getCraftableRing() {
        return craftableRing;
    }
    
}
