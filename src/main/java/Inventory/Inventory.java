/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inventory;

import java.util.ArrayList;


public class Inventory {
    private ArrayList<MaterialItem> materials;
    private ArrayList<Staff> equippableStaffs;
    private ArrayList<Robe> equippableRobes;
    private ArrayList<Ring> equippableRings;
    private Equipment equipment;
    
    public Inventory(Equipment equipment){
        materials = new ArrayList<>();
        equippableStaffs = new ArrayList<>();
        equippableRobes = new ArrayList<>();
        equippableRings = new ArrayList<>();
        this.equipment = equipment;
    }
    
    
    public void addItem(MaterialItem item) {
        if(materials.contains(item)){
            item.increaseQuantity(1);
        } else{
          materials.add(item);   
        }
    }
    
    public void addItem(Staff staff) {
        if(equippableStaffs.contains(staff)){
            staff.increaseQuantity(1);
        } else{
          equippableStaffs.add(staff);   
        }
    }
    public void addItem(Robe robe) {
        if(equippableRobes.contains(robe)){
            robe.increaseQuantity(1);
        } else{
          equippableRobes.add(robe);   
        }
    }
    public void addItem(Ring ring) {
        if(equippableRings.contains(ring)){
            ring.increaseQuantity(1);
        } else{
          equippableRings.add(ring);   
        }
    }
    

    
    public void equipStaff(Staff staff) {
        equippableStaffs.add(equipment.getEquippedStaff());
        equipment.setEquippedStaff(staff);
        equippableStaffs.remove(staff);
    }  
    
    public void equipRobe(Robe robe) {
        equippableRobes.add(equipment.getEquippedRobe());
        equipment.setEquippedRobe(robe);
        equippableRobes.remove(robe);
    }
    
    public void equipRing(Ring ring) {
        equippableRings.add(equipment.getEquippedRing());
        equipment.setEquippedRing(ring);
        equippableRings.remove(ring);         
    }
        




        
    //Accessors & Mutators

    public ArrayList<MaterialItem> getMaterials() {
        return materials;
    }

    public ArrayList<Staff> getEquippableStaffs() {
        return equippableStaffs;
    }
    public ArrayList<Robe> getEquippableRobes() {
        return equippableRobes;
    }
    public ArrayList<Ring> getEquippableRings() {
        return equippableRings;
    }

    public Equipment getEquipment() {
        return equipment;
    }
    

}


   
   

