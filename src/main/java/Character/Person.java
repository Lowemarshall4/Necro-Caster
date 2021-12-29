/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Character;

import Ability.AttackAbility;
import Ability.DefenseAbility;
import GUI.Tile;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;


public abstract class Person implements BattleAction {
    private String name;
    private int curHp;
    private int curMana;
    private int curDef;
    private int curAgil;
    private Stats stats;
    private ArrayList<AttackAbility> atkAbilities;
    private ArrayList<DefenseAbility> defAbilities;
    public boolean canAct = true;
    private boolean isDead = false;
    private int imageID;
    private ImageIcon characterImage;

    public Person(String name, Stats stats, ArrayList<AttackAbility> atkAbilities,
                     ArrayList <DefenseAbility> defAbilities) {
        this.name = name;
        this.stats = stats;
        this.curHp = stats.getMaxHp();
        this.curMana = stats.getMaxMana();
        this.curDef = stats.getDef();
        this.curAgil = stats.getAgil();
        this.atkAbilities = atkAbilities;
        this.defAbilities = defAbilities;
    }
     
    
    public boolean isNotAlive(){
        if (curHp <= 0){
            canAct = false;
            isDead = true;   
        }
        return isDead;
    }
     
    
    //Overrides
    
    /**
     * Calculates the damage of the ability on target and reduces targets hp and
     * agility accordingly while lowering casters mana
     * @param ability takes AttackAbility to use in attack
     * @param target takes target to use ability on
     */
    @Override
    public void attack(AttackAbility ability, Person target){
        int totalDamage = (ability.getDmg() + this.stats.getAtk());
        this.decreaseCurMana(ability.getManaCost());
        if (totalDamage <= target.getCurHp()) {          //If they are not killed by ability
            target.decreaseCurHp(totalDamage);
        } else {                                        //If the ability does more dmg then they have health
            target.decreaseCurHp(target.getCurHp());    // Decrease all their health
        }
        if (target.curAgil -ability.getAgilScaler() >= 0) {     //If the agilScaler doesnt lower targets agility below 0
            target.decreaseCurAgil(ability.getAgilScaler());
        } else{
            target.decreaseCurAgil(target.getCurAgil());       // Decrease targets agility to 0
        }
        
        //TESTING DATA
        System.out.println(this.getName() + " Decreasing " + target.getName() + " Hp by "+ (totalDamage) + " And agility by " + ability.getAgilScaler() + " Putting him at " + target.getCurHp() + " HP and  " + target.getCurAgil() + " Agility");
        //  
    }
    
    /**
     * Increases the targets hp and defense while deducting mana cost
     * @param ability takes DefenseAbility to use in defense
     * @param target takes target to defend.
     */
    @Override
    public void defend(DefenseAbility ability, Person target){
        this.decreaseCurMana(ability.getManaCost());
        if (target.getCurDef() + ability.getDefScaler() <= 2*target.getStats().getDef()) {         //If ability will not exceed 2x targets normal def
            target.increaseCurDef(ability.getDefScaler());                                        
        } else {                                                                                  //If Ability will exceed 2x targets base def
            target.increaseCurDef( 2*target.getStats().getDef() - target.getCurDef());           //Set current def to 2x base def
        }
        if (target.getCurHp() + ability.getHealAmount() <= target.getStats().getMaxHp()) {      // If the heal doesnt increase them over their max hp
            target.increaseCurHp(ability.getHealAmount());                                      
        } else {                                                                                // If the heal will exceed their max hp
            target.increaseCurHp(target.getStats().getMaxHp() - target.getCurHp());             // Increase their hp to max
        }
        
        //TESTING DATA
        System.out.println(this.getName() + " Increasing " + target.getName() + " Hp by "+ ability.getHealAmount() + " And defense by " + ability.getDefScaler() + " Putting him at " + target.getCurHp() + " HP and  " + target.getCurDef() + "Defense");
        //
    }
    
    /**
     * Method inherited by Player and AI to help call methods to select the 
     * AttackAbility and the target and execute the attack.
     * @param enemies takes enemies of user
     */
    public void attackAction(ArrayList<Person> playerTeam, ArrayList<Person> enemyTeam) {
        AttackAbility atkAbility = this.chooseAtkAbility();
        Person target = this.chooseAtkTarget(playerTeam, enemyTeam);
        this.attack(atkAbility, target);
    }
    
      /**
     * Method inherited by Player and AI to help call methods to select the 
     * DefenseAbility and the target and execute the defense.
     * @param allies takes enemies of user
     */
    @Override
    public void defenseAction(ArrayList<Person> allies) {
        DefenseAbility defAbility = this.chooseDefAbility();
        Person target = this.chooseDefTarget(allies);
        this.defend(defAbility, target);
    }
    
    
    // Accesors/Mutators
    
    public String getName() {
        return name;
    }
    
    public Stats getStats() {
        return stats;
    }
    
    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public int getCurHp() {
        return curHp;
    }

    public void increaseCurHp(int hp) {
        this.curHp += hp;
    }
    
    public void decreaseCurHp(int hp) {
        this.curHp -= hp;
    }
    
    public int getCurMana() {
        return curMana;
    }
    
    public void increaseCurMana(int mana) {
        this.curMana += mana;
    }

    public void decreaseCurMana(int mana) {
        this.curMana -= mana;
    }

    public int getCurDef() {
        return curDef;
    }
    
    public void increaseCurDef(int amt) {
        this.curDef += amt;
    }
    
    public int getCurAgil() {
        return this.curAgil;
    }
    
    public void decreaseCurAgil(int amt) {
        this.curAgil -= amt;
    }
    
    public boolean isCanAct() {
        return canAct;
    }
     
    public void setCanAct(boolean canAct) {
        this.canAct = canAct;
    }

    public ArrayList<AttackAbility> getAtkAbilities() {
        return atkAbilities;
    }

    public ArrayList<DefenseAbility> getDefAbilities() {
        return defAbilities;
    }

    public ImageIcon getCharacterImage() {
        return characterImage;
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    
}

