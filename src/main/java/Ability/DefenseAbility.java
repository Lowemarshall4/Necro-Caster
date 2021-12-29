/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ability;

public class DefenseAbility extends Ability {
    private final int defScaler;
    private final int healAmount;
    private final int type;
    
    public DefenseAbility(String name, int manaCost, int defScaler, int healAmount){
        super(name, manaCost);
        this.defScaler = defScaler;
        this.healAmount = healAmount;
        this.type = 1;
    }
    
    //Accessors

    public int getDefScaler() {
        return defScaler;
    }

    public int getHealAmount() {
        return healAmount;
    }
    
    //Overrides

    @Override
    public String toString() {
        String str = super.toString(); 
        str += String.format("\n\tHeal Amount: %1$s\n\tDefense Bonus: %2$s", this.healAmount, this.defScaler);
        return str;
    }
    
    
    
}
