/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Character;

import Ability.AttackAbility;
import Ability.DefenseAbility;
import Inventory.Inventory;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;


public class Player extends Person implements BattleAction {
    public ArrayList<Person> allies;
    private int xp;
    private int xpToNextLevel;
    private Inventory inventory;
    public boolean ranFromBattle;
    private int imageID;
    private ImageIcon characterImage;



    public Player(String name, Stats stats,
                  ArrayList<AttackAbility> atkAbilities,
                  ArrayList<DefenseAbility> defAbilities, Inventory inventory) {
        
        super(name, stats, atkAbilities, defAbilities);
        this.allies = new ArrayList<>();
        this.inventory = inventory;
        this.xp = 0;
        this.xpToNextLevel = 100;
        this.ranFromBattle = false;
        this.imageID = 0;
        this.characterImage = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Player.class.getResource("/characterImages/0.png")));
        
    }
    
    /**
     * Will eventually take data passed from the AI to execute a chosen action. 
     * Defaults to attacking.
     * @param good takes the players team list
     * @param evil takes the AI enemy team list
     */
    @Override
    public void pickAction(ArrayList<Person> good, ArrayList<Person> evil) {
        //this.canAct = false;
        //while (!canAct) {
          //  System.out.println("MyTurn");
        //}
        
        //Random rand = new Random();
        //attack(new AttackAbility("atk", 0, 10, 5), evil.get(rand.nextInt(evil.size())));
        //NEEDS WAY TO WAIT TILL PLAYER GOES
        }
    
    /**
     * Will Eventually interact with UI data to grab an AttackAbility. 
     * For now it defaults to a test attackability
     * @return a test attack ability (for now)
     */
    @Override
    public AttackAbility chooseAtkAbility() {
        //Will Interact With UI and display Attack Abilities
        //VERIFY REQUIRED MANA
        AttackAbility ability = new AttackAbility("TestDmg", 5, 20, 1); //TestData
        return ability;
    }
    
    /**
     *Will Eventually interact with UI to grab a target from enemy team.
     * @param playerTeam takes playerTeam of Player (AI team)
     * @return a random test target (for now)
     */
    @Override
    public Person chooseAtkTarget(ArrayList<Person> playerTeam, ArrayList<Person> enemyTeam) {
        Random rand = new Random();
        Person target = playerTeam.get(rand.nextInt(playerTeam.size())); //TestData// Recieve input from UI selection prompt 
        return target;
        }

    /**
     * Will Eventually interact with UI to grab a DefenseAbility 
     * @return chosen defense ability
     */
    @Override
    public DefenseAbility chooseDefAbility() {
        //Will Interact With UI and display Defense Abilities
        //VERIFY REQUIRED MANA
        DefenseAbility ability = new DefenseAbility("TestDef", 20, 10, 100); //TestData
        return ability;
    }

    /**
     * Will eventually interact with UI to grab a defense target
     * @param allies takes Players team 
     * @return chosen defense target
     */
    @Override
    public Person chooseDefTarget(ArrayList<Person> allies) {
        Person target = allies.get(0); //Test Data// Recieve input from UI selection prompt 
        return target;
    }
    
    /**
     * If the player does not gain a level from the xp gained, 
     * @param xp takes total xp gained from battle
     */
    public void increaseXp(int xp) {
        if(this.xp + xp < xpToNextLevel) {      //If player does not go up a level from the xp gained
            this.xp += xp;
        }
        else{                                               //If the player gains a level from the xp gained
            int previousXpToNextLevel = xpToNextLevel; 
            this.increaseLevel();
            increaseXp(xp - (previousXpToNextLevel - this.xp ));  //recursive call on overflow xp gained after hitting next level xp limit
        }
    }
    
    /**
     * Increases the players stat level
     * sets the xp to reach the next level
     * calls method to update stats for level 
     */
    public void increaseLevel() {
        this.getStats().increaseLevel();
        setXpToNextLevel();
        this.getStats().updateStatsForLevel();
        this.increaseCurHp(this.getStats().getMaxHp()-this.getCurHp());
        this.increaseCurMana(this.getStats().getMaxMana() - this.getCurMana());
    }
    // Accesors/Mutators

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }
    public int getXpToNextLevel() {
        return xpToNextLevel;
    }

    public void setXpToNextLevel() {
        xpToNextLevel = (this.getStats().getLevel()*100);
    }
    
    public Inventory getInventory() {
        return inventory;
    }

    public boolean isRanFromBattle() {
        return ranFromBattle;
    }

    public ImageIcon getCharacterImage() {
        return characterImage;
    }

    public void setRanFromBattle(boolean ranFromBattle) {
        this.ranFromBattle = ranFromBattle;
    }
    
    public void maxHpandMana() {
        increaseCurHp(getStats().getMaxHp()-getCurHp());
        increaseCurMana(getStats().getMaxMana() - getCurMana());
    }
    
}
