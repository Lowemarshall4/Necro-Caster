/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ability;

public class Ability {
    private String name;
    private int manaCost;
    private int type; //0 if atk, 1 if def.
    
    public Ability(String name, int manaCost) {
        this.name = name;
        this.manaCost = manaCost;
    }
    
    //Acessors
    
    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }
    
    //Overrides
    
    @Override
    public String toString(){
        String str = String.format("%1$s\n\tCost: %2$s", this.name, this.manaCost);
        return str;
    }
    
}
