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
public class Robe extends Item {
    int def;
    int hp;
    int reqMaterial;

    public Robe(String name, int quantity, int def, int hp) {
        super(name, quantity);
        this.def = def;
        this.hp = hp;
        reqMaterial = 5;
    }
    
    //Accessors
    
    public int getDef() {
        return def;
    }

    public int getHp() {
        return hp;
    }
    
    public int getReqMaterial() {
        return reqMaterial;
    }
    
}
