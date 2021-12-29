/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Character;

import GUI.Controller;
import GUI.GameView;
import Inventory.MaterialItem;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingWorker;



public class Battle extends SwingWorker<Void, Void>  {
   
   protected Player player;
   protected ArrayList<Person> aliveEnemies;
   protected ArrayList<Person> aliveAllies;
   protected ArrayList<Person> deadEnemies;
   protected ArrayList<Person> deadAllies;
   protected boolean battleOver = false;
   protected boolean lostBattle = false;
   protected ArrayList<Person> allChars;
   protected GameView view;
   protected Controller controller;
    
    public Battle(ArrayList<Person> aliveEnemies, Player player, GameView view, Controller controller){
        this.player = player;
        this.aliveEnemies = aliveEnemies;
        this.aliveAllies = player.allies; aliveAllies.add(player);
        this.deadAllies = new ArrayList<>();
        this.deadEnemies = new ArrayList<>();
        this.allChars = new ArrayList<>();
        allChars.addAll(aliveAllies); allChars.addAll(aliveEnemies);
        this.view = view;
        this.controller = controller;
    }

    /**
     * Runs a round of the battle
     * 
     */
    public void goRound() {
        controller.updateBattleStats();
        for(Person cur: allChars) {
            cur.setCanAct(true);                                                                //Set canAct to true for all chars at beginning of each round
        }
        Collections.sort(allChars, (c1,c2) -> {return c2.getCurAgil()-c1.getCurAgil();});       //Sort all characters by their agility stat at beginning of each round to determine action order
        for(Person cur: allChars){
            
            boolean isDead = cur.isNotAlive();
            if (!isDead){                                                                       //Make sure each char isnt dead before they act
                if (cur.equals(player)) {
                    view.battlePanel.abilityPanel.setVisible(true);
                    view.battlePanel.targetChoicesPanel.setVisible(true);
                    while(checkForAction()) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            //Logger.getLogger(Battle.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    view.battlePanel.abilityPanel.setVisible(false);
                    view.battlePanel.targetChoicesPanel.setVisible(false);
                    Battle.wait(1000);
                    cur.pickAction(aliveAllies, aliveEnemies);
                    }
                
            }
            if (isBattleOver()) {                                                              //Stop the round if the battle is over
                JPanel endPanel = new JPanel();
                endPanel.setBackground(Color.GRAY);
                JButton btnEndBattle = new JButton("End Battle");
                btnEndBattle.setPreferredSize(new Dimension(600,100));
                btnEndBattle.addActionListener(ev -> {
                    view.mapPanel.setVisible(true);
                    view.mapPanel.requestFocusInWindow();
                    view.statsPanel.setVisible(true);
                    endPanel.setVisible(false);
                    view.mainFrame.remove(endPanel);
                    controller.setStatsPanelStats();
                    view.statsPanel.revalidate();
                    view.mapPanel.revalidate();
                    view.mainFrame.revalidate();
                });
                view.mainFrame.add(endPanel);
                endPanel.add(btnEndBattle);
                endPanel.setVisible(true);
                view.battlePanel.setVisible(false);
                break;
            }
            updateAliveCharacters();
            controller.updateBattleStats();
            view.battlePanel.updatePanels(aliveAllies, aliveEnemies);
            view.battlePanel.targetChoicesPanel.removeAll();
            view.battlePanel.targetChoicesPanel.repaint();
        }
        player.increaseCurMana(10);
    }

    /**
     * Update the ArrayLists of alive and dead allies and enemies
     */
    public void updateAliveCharacters() {
        
        for (Person character : allChars) {
            if(character.isNotAlive()) {
                if(aliveAllies.contains(character)) {
                    deadAllies.add(character);
                    aliveAllies.remove(character);
                } 
                else if(aliveEnemies.contains(character)) {
                    deadEnemies.add(character);
                    aliveEnemies.remove(character);
                }
            }
        }
    }
    
    /**
     * Checks conditions that would determine if the battle should end
     * @return boolean true if battle should end or false if not
     */
    public boolean isBattleOver() {
        lostBattle = false;
        if(player.isNotAlive()){    //If players dead
            lostBattle = true;
            battleOver=true;
            player.setXp(0);
        }
        else if (player.isRanFromBattle()) {
            lostBattle = true;
            battleOver = true;
        }
        else if(aliveEnemies.isEmpty()){   //If all Enemies are dead
            battleOver=true;
        }
        else{
            battleOver=false;
        }
        return battleOver;
    }
    
    /**
     * Main loop for battle
     */
    public void battleLoop() {
        player.setRanFromBattle(false);
        while (!this.isBattleOver()){
            this.goRound();
        }
        
        if (lostBattle == false){
            
        
            this.getEnemiesXp();        

            this.getEnemiesLoot();   
            
            this.getEnemyAllies();
            
        } else {
            player.allies.clear();
            player.allies.remove(player);
            lostBattle = false;
            player.setIsDead(false);
        }
        
    }
    
    /**
     * loop through dead enemies and collect their xp in a variable
     * call increase xp method with variable
     */
    public void getEnemiesXp() {
        int xpRecieved = 0;
        for(int i=0; i<deadEnemies.size(); i++) {
            xpRecieved += deadEnemies.get(i).getStats().getXpWorthOnDeath();
        }
        player.increaseXp(xpRecieved);
    }
    
    /**
     * Loop through dead enemies and collect their loot and add it to player inventory
     */
    public void getEnemiesLoot() {
        for(int i=0; i<deadEnemies.size(); i++) {
           MaterialItem enemyItem = deadEnemies.get(i).getStats().getItemDropOnDeath();
           this.player.getInventory().addItem(enemyItem);
        }
    }
    
    public void getEnemyAllies() {
        player.allies.clear();
        player.allies.addAll(aliveAllies);
        player.allies.remove(player);
        if (player.allies.size() < 3) {
            for(Person enemy : deadEnemies) {
                AI e = (AI)enemy;
                e.increaseCurHp(enemy.getStats().getMaxHp() - enemy.getCurHp());
                e.setIsDead(false);
                e.team = 1;
                player.allies.add(e);
            }
        }
    }
    
    public static void wait(int ms) {

    try
    {
        Thread.sleep(ms);
    }
    catch(InterruptedException ex)
    {
        Thread.currentThread().interrupt();
    }
    }
    
    
    
    //Accessors/Mutators

    public ArrayList<Person> getAliveEnemies() {
        return aliveEnemies;
    }

    public ArrayList<Person> getAliveAllies() {
        return aliveAllies;
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Person> getAllChars() {
        return allChars;
    }


    private boolean checkForAction() {
        if (player.canAct) {
            return true;
        } else{
            return false;
        }
    }

    @Override
    protected Void doInBackground() throws Exception {
        battleLoop();
        return null;
    }

    @Override
    protected void done() {
        controller.destroyBattle();
    }

    

}
    
    

