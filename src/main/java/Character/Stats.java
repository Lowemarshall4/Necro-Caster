/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Character;

import Inventory.Equipment;
import Inventory.Item;
import Inventory.MaterialItem;

/**
 *
 * @author Marshall
 */
public class Stats {
    private int maxHp;
    private int maxMana;
    private int agil;
    private int atk;
    private int def;
    private int level;
    private int xpWorthOnDeath;
    private MaterialItem itemDropOnDeath;
    private int[] baseStats;
    
    
    public Stats(int level, int maxHp, int maxMana, int agil, int atk, int def,
                 int xpWorthOnDeath, MaterialItem itemDropOnDeath) {
        this.level = level;
        this.maxHp = maxHp + (5*level);
        this.maxMana = maxMana + (3*level);
        this.agil = agil + (1*level);
        this.atk = atk + (1*level);
        this.def = def + (1*level);
        this.xpWorthOnDeath = xpWorthOnDeath + (5*level);
        this.baseStats = new int[]{maxHp, maxMana, agil, atk, def};
        this.itemDropOnDeath = itemDropOnDeath;
    }
    
    /**
     * Updates the stats by increasing base stats by the stats that each piece of equipment gives
     * @param equipment takes players current equipment
     */
    public void updateStatsForEquipment(Equipment equipment) {
        this.maxHp = this.baseStats[0] + (equipment.getEquippedRobe().getHp()) + equipment.getEquippedRing().getHp();
        this.maxMana = this.baseStats[1] + equipment.getEquippedRing().getMana();
        this.agil = this.baseStats[2] + equipment.getEquippedStaff().getAgil();
        this.atk = this.baseStats[3] + equipment.getEquippedStaff().getAtk();
        this.def = this.baseStats[4] + equipment.getEquippedRobe().getDef();
    }
    
    /**
     * Updates stats for current level
     */
    public void updateStatsForLevel() {
        this.baseStats[0] += (5*level);
        this.baseStats[1] += (3*level);
        this.baseStats[2] += (1*level);
        this.baseStats[3] += (1*level);
        this.baseStats[4] += (1*level);
    }

    //Accessors/Mutators
    
    public int getMaxHp() {
        return maxHp;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getAgil() {
        return agil;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getLevel() {
        return level;
    }
    
    public void increaseLevel() {
        this.level +=1;
    }
    
    public int getXpWorthOnDeath() {
        return xpWorthOnDeath;
    }
    
    public MaterialItem getItemDropOnDeath() {
        return itemDropOnDeath;
    }
    
}
