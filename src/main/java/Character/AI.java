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
import java.util.Random;
import javax.swing.ImageIcon;

public class AI extends Person implements BattleAction, Cloneable {
    private Random rand = new Random();
    private int imageID;
    private ImageIcon characterImage;
    public int team;

    public AI(String name, Stats stats, ArrayList<AttackAbility> atkAbilities,
              ArrayList<DefenseAbility> defAbility, int imageID) {
        super(name, stats, atkAbilities, defAbility);
        team = 0;
        this.imageID = imageID;
        characterImage = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Tile.class.getResource(String.format("/characterImages/%1$d.png", imageID))));
        
    }
    
    
    /**
     * Decision maker for what action the AI should do
     * 
     * @param enemy Takes enemy of the AI team (AKA player and team)
     * @param good Takes the enemy team (Not used currently)
     */
    
    @Override
    public void pickAction(ArrayList<Person> playerTeam, ArrayList<Person> enemyTeam) { 
        if (this.getCurHp() < this.getStats().getMaxHp()/4) {
            this.defend(this.getDefAbilities().get(rand.nextInt(this.getDefAbilities().size())), this);
        } else {
            this.attackAction(playerTeam, enemyTeam);
        }
        canAct = false;
    }
    
    /**
     * 
     * @return a random attack ability from the AI's attack ability list
    */
    @Override
    public AttackAbility chooseAtkAbility() {
        return this.getAtkAbilities().get(rand.nextInt(this.getAtkAbilities().size()));
    }
    
    /**
     *
     * @return a random defense ability from the AI's defense ability list
    */
    @Override
    public DefenseAbility chooseDefAbility() {
        return this.getDefAbilities().get(rand.nextInt(this.getDefAbilities().size()));
    }
    
    /**
     *@param enemies  
     *Returns a random target from the AI's attack ability list
    */
    public Person chooseAtkTarget(ArrayList<Person> playerTeam, ArrayList<Person> enemyTeam) {
        if(playerTeam.size() == 0 || enemyTeam.size() ==0) {
            return this;                                        //Janky solution to not getting OutOfBoundsError
        }
        else if (team == 0){
        return playerTeam.get(rand.nextInt(playerTeam.size()));
        }
        else {
            return enemyTeam.get(rand.nextInt(enemyTeam.size()));
        }
    }

    /**
     * 
     * @param allies takes the AI team list 
     * @return a random ally of AI
     */
    @Override
    public Person chooseDefTarget(ArrayList<Person> allies) {
        return allies.get(rand.nextInt(allies.size()));
    }

    @Override
    public String toString(){
        return this.getName();
    }

    public ImageIcon getCharacterImage() {
        return characterImage;
    }
    
    @Override
    public Object clone()throws CloneNotSupportedException{  
        return super.clone();  
    }  

    public void setTeam(int team) {
        this.team = team;
    }

    

    
    
    }
