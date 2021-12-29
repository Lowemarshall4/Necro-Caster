/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;


public class Equipment {
    private Staff equippedStaff;
    private Robe equippedRobe;
    private Ring equippedRing;

    public Equipment(Staff staff, Robe robe, Ring ring) {
        this.equippedStaff = staff;
        this.equippedRobe = robe;
        this.equippedRing = ring;
    }


    
    //Accesors/Mutators

    public Staff getEquippedStaff() {
        return equippedStaff;
    }

    public Robe getEquippedRobe() {
        return equippedRobe;
    }

    public Ring getEquippedRing() {
        return equippedRing;
    }

    public void setEquippedStaff(Staff equippedStaff) {
        this.equippedStaff = equippedStaff;
    }

    public void setEquippedRobe(Robe equippedRobe) {
        this.equippedRobe = equippedRobe;
    }

    public void setEquippedRing(Ring equippedRing) {
        this.equippedRing = equippedRing;
    }
    
    
    
    @Override
    public String toString(){
        String str = String.format("Staff: %1$s\nRobe: %2$s\nRing: %3$s", 
                this.equippedStaff.getName(), this.equippedRobe.getName(), this.equippedRing.getName());
        return str;
    }
    
    
    
}
 
    
    

