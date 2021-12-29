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
public class Ring extends Item {
    int mana;
    int hp;
    int reqMaterial;

    public Ring(String name, int quantity, int mana, int hp) {
        super(name, quantity);
        this.mana = mana;
        this.hp = hp;
        this.reqMaterial = 3;
    }
    
    //Accessors

    public int getMana() {
        return mana;
    }

    public int getHp() {
        return hp;
    }
    
    public int getReqMaterial() {
        return reqMaterial;
    }
    
    
}
