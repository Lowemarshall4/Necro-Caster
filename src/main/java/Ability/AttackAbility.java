/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ability;

public class AttackAbility extends Ability {
    private int dmg;
    private int agilScaler;
    private int type;

    public AttackAbility(String name, int manaCost, int dmg, int agilScaler){
        super(name, manaCost);
        this.dmg = dmg;
        this.agilScaler = agilScaler;
        this.type = 0;
    }

    //Accessors
    
    public int getDmg() {
        return dmg;
    }

    public int getAgilScaler() {
        return agilScaler;
    }
    
    //Overrides
    
    @Override
    public String toString(){
        String str = super.toString();
        str += String.format("\n\tDamage: %1$s\n\tAgility Bonus: %2$s", this.dmg, this.agilScaler);
        return str;
    }
    
}
