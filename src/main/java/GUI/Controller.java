/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Ability.AttackAbility;
import Ability.DefenseAbility;
import Character.AI;
import Character.Battle;
import Character.Person;
import Character.Player;
import Character.Stats;
import GUI.Containers.BattlePanel;
import GUI.Containers.StatsPanel;
import Inventory.Equipment;
import Inventory.Item;
import Inventory.MaterialItem;
import Inventory.Ring;
import Inventory.Robe;
import Inventory.Staff;
import Objects.Enemies;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import java.sql.*;
import java.util.List;

/**
 *
 * @author Marshall
 */
public class Controller implements KeyListener{
    private GameView view;
    private GameModel model;
    public Battle newBattle;


    
    
    public Controller(GameView view, GameModel model) {
        this.view = view;
        this.model = model;
        newBattle = model.battle;
    }
    
    // INITIALIZER METHODS //
    public final void beginGame() {
        loadGameState();
        setStatsPanelStats();
        view.mainFrame.add(view.mainMenuPanel);
        view.mainFrame.pack();
        view.mainFrame.setVisible(true);
        initActionListeners();
    }

    
    public void initMapView() {
        model.player.maxHpandMana();
        view.mainMenuPanel.setVisible(false);
        setStatsPanelStats();
        view.mainFrame.add(view.mapPanel,BorderLayout.CENTER);
        view.mainFrame.add(view.statsPanel, BorderLayout.EAST);
        view.mapPanel.setVisible(true);
        view.statsPanel.setVisible(true);
        view.mapPanel.setFocusable(true);
        view.mapPanel.requestFocusInWindow();
        view.mapPanel.addKeyListener(this);
        view.mainFrame.pack();
        
        
    }     
    
    public void initActionListeners() {
        view.mainMenuPanel.getBtnPlaygame().addActionListener(ev -> {
            initMapView();
        });
        view.mainMenuPanel.getBtnExitGame().addActionListener(ev -> {
            view.mainFrame.dispose();
        });
        view.mainMenuPanel.getBtnHowToPlay().addActionListener(ev -> {
            view.mainFrame.add(view.howToPlayPanel);
            view.mainMenuPanel.setVisible(false);
            view.howToPlayPanel.setVisible(true);
        });
        view.howToPlayPanel.getBtnReturnToMenu().addActionListener(ev -> {
            view.howToPlayPanel.setVisible(false);
            view.mainMenuPanel.setVisible(true);
        });
        view.statsPanel.getBtnInv().addActionListener(ev -> {
            updateInventoryMaterials();
            updateEquippablesPanel();
            updateCraftablesPanel();
            view.invFrame.setVisible(true);
        });
        view.invFrame.getBtnExit().addActionListener(ev -> {
            setStatsPanelStats();
            model.player.maxHpandMana();
            view.invFrame.setVisible(false);
            
            view.mapPanel.requestFocusInWindow();
        });
   }
    
        public void setStatsPanelStats() {
        Stats stats = model.player.getStats();
        Equipment equip = model.player.getInventory().getEquipment();
        model.player.maxHpandMana();
        model.player.getStats().updateStatsForEquipment(equip);
        view.statsPanel.getLevelValue().setText(String.valueOf(stats.getLevel()));
        view.statsPanel.getAttackValue().setText(String.valueOf(stats.getAtk()));
        view.statsPanel.getDefenseValue().setText(String.valueOf(stats.getDef()));
        view.statsPanel.getAgilityValue().setText(String.valueOf(stats.getAgil()));
        view.statsPanel.getEquippedStaff().setText(equip.getEquippedStaff().getName());
        view.statsPanel.getEquippedRobe().setText(equip.getEquippedRobe().getName());
        view.statsPanel.getEquippedRing().setText(equip.getEquippedRing().getName());
        
        view.statsPanel.getXpProgressBar().setMaximum(model.player.getXpToNextLevel());
        view.statsPanel.getXpProgressBar().setValue(model.player.getXp());
        view.statsPanel.getXpProgressBar().setStringPainted(true);
        view.statsPanel.getXpProgressBar().setString((String.format("%1$d / %2$d", model.player.getXp(), model.player.getXpToNextLevel())));
        view.statsPanel.getXpProgressBar().setForeground(Color.GREEN);
        view.statsPanel.getHealthProgressBar().setMaximum(model.player.getStats().getMaxHp());
        view.statsPanel.getHealthProgressBar().setValue(model.player.getCurHp());
        view.statsPanel.getHealthProgressBar().setStringPainted(true);
        view.statsPanel.getHealthProgressBar().setString(String.format("%1$d / %2$d", model.player.getCurHp(), model.player.getStats().getMaxHp()));
        view.statsPanel.getHealthProgressBar().setForeground(Color.RED);
        view.statsPanel.getManaProgressBar().setMaximum(model.player.getStats().getMaxMana());
        view.statsPanel.getManaProgressBar().setValue(model.player.getCurMana());
        view.statsPanel.getManaProgressBar().setStringPainted(true);
        view.statsPanel.getManaProgressBar().setString((String.format("%1$d / %2$d", model.player.getCurMana(), model.player.getStats().getMaxMana())));
        view.statsPanel.getManaProgressBar().setForeground(Color.BLUE);

        view.statsPanel.revalidate();
        
    }

    // KEY LISTENER FOR MAP MOVEMENT//
    @Override
    public void keyPressed(KeyEvent e) {
        view.mapPanel.requestFocusInWindow();
        switch (e.getKeyCode()) {
            case 65: //A
                if(view.mapPanel.player.getX() >= 0){
                   view.mapPanel.player.setLocation(view.mapPanel.player.getX()-16, view.mapPanel.player.getY()); 
                }
                break;
            case 68: //D
                if(view.mapPanel.player.getX() + view.mapPanel.player.getWidth() <= view.mapPanel.map.getLayers()[0].getWidthInPixels()){
                view.mapPanel.player.setLocation(view.mapPanel.player.getX()+16, view.mapPanel.player.getY());
                }
                break;
            case 87: //W
                if(view.mapPanel.player.getY() >=0){
                view.mapPanel.player.setLocation(view.mapPanel.player.getX(), view.mapPanel.player.getY()-16);
                }
                if(inMapChangeZoneTop()) {
                    switchMap(view.mapPanel.map.aboveMap);
                    view.mapPanel.player.setLocation(view.mapPanel.map.getLayers()[0].getWidthInPixels()/2,
                            view.mapPanel.map.getLayers()[0].getHeightInPixels()-200);
                }
                break;
            case 83: //S
                if(view.mapPanel.player.getY() + view.mapPanel.player.getHeight() <= view.mapPanel.map.getLayers()[0].getHeightInPixels()){
                view.mapPanel.player.setLocation(view.mapPanel.player.getX(), view.mapPanel.player.getY()+16);
                }
                if (inMapChangeZoneBottom()) {
                    switchMap(view.mapPanel.map.belowMap);
                    view.mapPanel.player.setLocation(view.mapPanel.map.getLayers()[0].getWidthInPixels()/2,200);
                }
                break;
            case 32: //Space
                model.player.ranFromBattle = false;
                BattlePanel newBattlePanel = new BattlePanel();
                view.battlePanel = newBattlePanel;
                newBattle = new Battle(getBattleEnemies(), model.player,
                                        view, this);
                model.battle = newBattle;
                loadAbilityButtons();
                view.battlePanel.initAbilityOptions();
                view.mainFrame.add(view.battlePanel);
                updateBattleEnemiesImage();
                view.mapPanel.setVisible(false);
                view.statsPanel.setVisible(false);
                view.battlePanel.revalidate();
                view.battlePanel.setVisible(true);
                newBattle.execute();
                
                break;


        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        //UNUSED
    }


    @Override
    public void keyReleased(KeyEvent e) {
        //UNUSED
    }

    //MAP METHODS//
    public int[] getPlayerPosition() {
        int[] pos = {view.mapPanel.player.getX(),view.mapPanel.player.getY()};
        return pos;
    }
    
    public void switchMap(TileMap newMap) {
        view.mapPanel.map = newMap;
        view.mapPanel.repaint();
        view.mapPanel.revalidate();
    }
    
    public boolean inMapChangeZoneBottom() {
        if (((getPlayerPosition()[0] <=  view.mapPanel.map.bottomMapChangeCoords[0]+64
                && getPlayerPosition()[0] >= view.mapPanel.map.bottomMapChangeCoords[0]-64 )
                && (getPlayerPosition()[1] <= view.mapPanel.map.bottomMapChangeCoords[1]+64
                && getPlayerPosition()[1] >= view.mapPanel.map.bottomMapChangeCoords[1]-64))
                && view.mapPanel.map.belowMap != null){
            model.curMap += 1;
            return true;
        } else return false;
    }
    
    public boolean inMapChangeZoneTop() {
        if(((getPlayerPosition()[0] <=  view.mapPanel.map.topMapChangeCoords[0]+64
        && getPlayerPosition()[0] >= view.mapPanel.map.topMapChangeCoords[0]-64 )
        && (getPlayerPosition()[1] <= view.mapPanel.map.topMapChangeCoords[1]+64
        && getPlayerPosition()[1] >= view.mapPanel.map.topMapChangeCoords[1]-64))
        && view.mapPanel.map.aboveMap != null){
            model.curMap -=1;
            return true;
        } else return false;
    }
    
    // BATTLE METHODS //
    public void updateBattleEnemiesImage() {
        for(Person ally : model.battle.getAliveAllies()) {
            view.battlePanel.characterPanel.add(new JLabel(ally.getCharacterImage()));
        }
        view.battlePanel.characterPanel.add(new JLabel(GameModel.INVISIBLEIMAGE));
        view.battlePanel.characterPanel.add(new JLabel(GameModel.INVISIBLEIMAGE));
        view.battlePanel.characterPanel.add(new JLabel(GameModel.INVISIBLEIMAGE));
        view.battlePanel.characterPanel.add(new JLabel(GameModel.INVISIBLEIMAGE));
        view.battlePanel.characterPanel.add(new JLabel(GameModel.INVISIBLEIMAGE));
        view.battlePanel.characterPanel.add(new JLabel(GameModel.INVISIBLEIMAGE));
        view.battlePanel.characterPanel.add(new JLabel(GameModel.INVISIBLEIMAGE));
        for(Person enemy : model.battle.getAliveEnemies()) {
            view.battlePanel.characterPanel.add(new JLabel(enemy.getCharacterImage()));
        }
    }
    
    public void loadAbilityButtons() {
        for(AttackAbility ab : model.attackAbilities) {
            JButton btn = new JButton(ab.getName());
            btn.addActionListener(ev ->{
                view.battlePanel.targetChoicesPanel.removeAll();
                view.battlePanel.repaint();
                JLabel targets = new JLabel("Targets");
                targets.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
                view.battlePanel.targetChoicesPanel.add(targets);
                view.battlePanel.selectedAbility.setText(btn.getText());
                view.battlePanel.abilityPanel.revalidate();
                for (Person enemy : model.battle.getAliveEnemies()) {
                    JButton btn2 = new JButton(enemy.getName());
                    btn2.addActionListener(ev2 -> {
                        model.player.canAct = false;
                        model.player.attack(ab, enemy);
                    });
                    view.battlePanel.targetChoicesPanel.add(btn2);
                }
                view.battlePanel.abilityPanel.revalidate();
            });
            view.battlePanel.btnAttackAbilities.add(btn);
            }
        for(DefenseAbility ab : model.defenseAbilities) {
            JButton btn = new JButton(ab.getName());
            btn.addActionListener(ev -> {
                view.battlePanel.targetChoicesPanel.removeAll();
                view.battlePanel.repaint();
                JLabel targets = new JLabel("Targets");
                targets.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
                view.battlePanel.targetChoicesPanel.add(targets);
                view.battlePanel.selectedAbility.setText(btn.getText());
                view.battlePanel.abilityPanel.revalidate();
                for (Person ally : model.battle.getAliveAllies()) {
                    JButton btn2 = new JButton(ally.getName());
                    btn2.addActionListener(ev2 -> {
                        model.player.canAct = false;
                        model.player.defend(ab, ally);
                    });
                view.battlePanel.targetChoicesPanel.add(btn2);  
                }
            });
            view.battlePanel.btnDefenseAbilities.add(btn);  
            
            }
        view.battlePanel.btnRun = new JButton("Run");
        view.battlePanel.btnRun.addActionListener(ev -> {
            model.player.decreaseCurHp(model.player.getStats().getMaxHp());
            newBattle.isBattleOver();
        });
        }
    
    public void updateBattleStats() {
        view.battlePanel.decorPanel.removeAll();
        JLabel allies = new JLabel("Allies:");
        allies.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
        view.battlePanel.decorPanel.add(allies);
        for (Person ally : model.battle.getAliveAllies()) {
            if (ally.getName().equals("Mith")) {
                JLabel name = new JLabel(ally.getName());
                JProgressBar health = new JProgressBar(0, model.player.getStats().getMaxHp());
                health.setValue(model.player.getCurHp());
                health.setStringPainted(true);
                health.setString(String.valueOf(model.player.getCurHp()) + "/" +model.player.getStats().getMaxHp());
                health.setForeground(Color.RED);
                JProgressBar mana = new JProgressBar(0, model.player.getStats().getMaxMana());
                mana.setStringPainted(true);
                mana.setValue(model.player.getCurMana());
                mana.setString(String.valueOf(model.player.getCurMana() + "/" + model.player.getStats().getMaxMana()));
                mana.setForeground(Color.BLUE);
                view.battlePanel.decorPanel.add(name);
                view.battlePanel.decorPanel.add(health);
                view.battlePanel.decorPanel.add(mana);
            } else{
                JLabel name = new JLabel(ally.getName());
                JProgressBar health = new JProgressBar(0, ally.getStats().getMaxHp());
                health.setValue(ally.getCurHp());
                health.setStringPainted(true);
                health.setForeground(Color.RED);
                view.battlePanel.decorPanel.add(name);
                view.battlePanel.decorPanel.add(health);
            }
        }
        JLabel enemies = new JLabel("Enemies:");
        enemies.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
        view.battlePanel.decorPanel.add(enemies);
        for (Person enemy : model.battle.getAliveEnemies()) {
            JLabel name = new JLabel(enemy.getName());
            JProgressBar health = new JProgressBar(0, enemy.getStats().getMaxHp());
            health.setValue(enemy.getCurHp());
            health.setStringPainted(true);
            health.setForeground(Color.RED);
            view.battlePanel.decorPanel.add(name);
            view.battlePanel.decorPanel.add(health);
        }
    }
    

    public ArrayList<Person> getBattleEnemies() {
        Random rand = new Random();
        ArrayList<Person> enemies = new ArrayList<>();
        AI enemy = null;
        while (enemies.size() <= rand.nextInt(5)) {
            try {
                switch (model.curMap) {
                    case 0:
                        {
                            enemy = (AI)((AI) GameModel.ENEMIESMAP1.get(rand.nextInt(GameModel.ENEMIESMAP1.size()))).clone();
                            break;
                        }
                    case 1:
                        {
                            enemy = (AI)((AI) GameModel.ENEMIESMAP2.get(rand.nextInt(GameModel.ENEMIESMAP2.size()))).clone();
                            break;
                        }
                    case 2:
                        {
                            enemy = (AI)((AI) GameModel.ENEMIESMAP3.get(rand.nextInt(GameModel.ENEMIESMAP3.size()))).clone();
                            break;
                        }
                    default:
                        break;
                }
                enemies.add(enemy);
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return enemies;
    }
    
    public void destroyBattle() {
        model.player.canAct = false;
        //model.battle = null;
    }
    
    // INVENTORY METHODS //
    public void updateInventoryMaterials() {
        view.invFrame.materialsPanel.removeAll();
        JLabel mats = new JLabel("Materials");
        mats.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        mats.setHorizontalAlignment(JLabel.CENTER);
        view.invFrame.materialsPanel.add(mats);
        for (MaterialItem item : model.player.getInventory().getMaterials()) {
            view.invFrame.materialsPanel.add(new JLabel(item.getDesc()));
            
        }
    }
    
    public void updateEquippablesPanel() {
        view.invFrame.equipmentPanel.removeAll();
        JLabel eq = new JLabel("Equippables");
        eq.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        eq.setHorizontalAlignment(JLabel.CENTER);
        view.invFrame.equipmentPanel.add(eq);
        for(Staff equip : model.player.getInventory().getEquippableStaffs()) {
            JButton equipStaff = new JButton(String.format("Equip %1$s", equip.getName()));
            equipStaff.addActionListener(ev -> {
                model.player.getInventory().equipStaff(equip);
                updateEquippablesPanel();
                view.invFrame.revalidate();
            });
            view.invFrame.equipmentPanel.add(equipStaff);
        } 
        for(Robe equip : model.player.getInventory().getEquippableRobes()) {
            JButton equipRobe = new JButton(String.format("Equip %1$s", equip.getName()));
            equipRobe.addActionListener(ev -> {
                model.player.getInventory().equipRobe(equip);
                updateEquippablesPanel();
                view.invFrame.revalidate();
            });
            view.invFrame.equipmentPanel.add(equipRobe);
        } 
        for(Ring equip : model.player.getInventory().getEquippableRings()) {
            JButton equipRing = new JButton(String.format("Equip %1$s", equip.getName()));
            equipRing.addActionListener(ev -> {
                model.player.getInventory().equipRing(equip);
                updateEquippablesPanel();
                view.invFrame.revalidate();
            });
            view.invFrame.equipmentPanel.add(equipRing);
        } 
            
    }
    
    public void updateCraftablesPanel() {
        view.invFrame.craftablesPanel.removeAll();
        JLabel cr = new JLabel("Craftables");
        cr.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        cr.setHorizontalAlignment(JLabel.CENTER);
        view.invFrame.craftablesPanel.add(cr);
        for(MaterialItem item : model.player.getInventory().getMaterials()) {
            if(!model.player.getInventory().getEquippableRings().contains(item)) {
                if(item.getQuantity() >= item.getCraftableRing().getReqMaterial()) {
                    JButton craftRing = new JButton(String.format("Craft %1$s", item.getCraftableRing().getName()));
                    craftRing.addActionListener(ev -> {
                        item.decreaseQuantity(item.getCraftableRing().getReqMaterial());
                        model.player.getInventory().getEquippableRings().add(item.getCraftableRing());
                        updateInventoryMaterials();
                        updateCraftablesPanel();
                        updateEquippablesPanel();
                        view.invFrame.revalidate();
                    });
                    view.invFrame.craftablesPanel.add(craftRing);
                }
            }
            if(!model.player.getInventory().getEquippableRobes().contains(item)) {
                if(item.getQuantity() >= item.getCraftableRobe().getReqMaterial()) {
                    JButton craftRobe = new JButton(String.format("Craft %1$s", item.getCraftableRobe().getName()));
                    craftRobe.addActionListener(ev -> {
                        item.decreaseQuantity(item.getCraftableRobe().getReqMaterial());
                        model.player.getInventory().getEquippableRobes().add(item.getCraftableRobe());
                        updateInventoryMaterials();
                        updateCraftablesPanel();
                        updateEquippablesPanel();
                        view.invFrame.revalidate();
                    });
                    view.invFrame.craftablesPanel.add(craftRobe);
                }
            }
            if(!model.player.getInventory().getEquippableStaffs().contains(item)) {
                if(item.getQuantity() >= item.getCraftableStaff().getReqMaterial()) {
                    JButton craftStaff = new JButton(String.format("Craft %1$s", item.getCraftableStaff().getName()));
                    craftStaff.addActionListener(ev -> {
                        item.decreaseQuantity(item.getCraftableStaff().getReqMaterial());
                        model.player.getInventory().getEquippableStaffs().add(item.getCraftableStaff());
                        updateInventoryMaterials();
                        updateCraftablesPanel();
                        updateEquippablesPanel();
                        view.invFrame.revalidate();
                    });
                    view.invFrame.craftablesPanel.add(craftStaff);
                }
            }
        }
    }
    

    //DATABASE QUERYS
    public void loadGameState() {
        String statQuery;
        String mainQuery;
        String equipQuery;
        Connection conn;
        Statement statStmt;
        Statement mainStmt;
        Statement equipStmt;
        ResultSet statResults;
        ResultSet mainResults;
        ResultSet equipResults;

        
        try {
            conn = 
                DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/rpg?zeroDateTimeBehavior=CONVERT_TO_NULL",
                            "root","Hunterone12Backstroke133");
            statStmt = conn.createStatement();
            mainStmt = conn.createStatement();
            equipStmt = conn.createStatement();
            // LOADING PLAYER STATS
            statQuery = "SELECT * FROM stats ORDER BY stat_id LIMIT 1";
            statResults = statStmt.executeQuery(statQuery);
            statResults.next();
            int level = statResults.getInt("level");
            int maxHp = statResults.getInt("max_hp");
            int maxMana = statResults.getInt("max_mana");
            int agil = statResults.getInt("agility");
            int atk = statResults.getInt("attack");
            int def = statResults.getInt("defense");
            int xpWorth = statResults.getInt("xp_worth");
            Stats playerStats = new Stats(level, maxHp, maxMana, agil, atk, def, xpWorth, null);
            model.player.setStats(playerStats);
            statResults.close();
            
            equipQuery = "SELECT * FROM equipment ORDER BY equipment_id LIMIT 1";
            equipResults = equipStmt.executeQuery(equipQuery);
            

            mainQuery = 
                "SELECT name, st.stat_id, level, max_hp, max_mana, st.agility, st.attack, st.defense,\n" +
            "        xp_worth, item_drop_name, e.attack_ability_id, aa.ability_name, aa.mana_cost,\n" +
            "        damage, agil_damage, e.defense_ability_id, da.ability_name, da.mana_cost,\n" +
            "        da.defense, heal_amt, image_id, item_name, staff_name, staff_attack,\n" +
            "        staff_agility, robe_name, robe_defense, robe_hp, ring_name, ring_mane, ring_hp\n" +
                "FROM enemies e\n" +
                "    INNER JOIN stats st\n" +
                "        ON e.stat_id = st.stat_id\n" +
                "    INNER JOIN attack_abilities aa\n" +
                "        ON e.attack_ability_id = aa.attack_ability_id\n" +
                "    INNER JOIN defense_abilities da\n" +
                "        ON e.defense_ability_id = da.defense_ability_id\n" +
                "    INNER JOIN items i\n" +
                "        ON st.item_drop_name = i.item_name\n" +
                "    INNER JOIN staffs s\n" +
                "        ON i.craftable_staff_id = s.staff_id\n" +
                "    INNER JOIN robes ro\n" +
                "         ON i.craftable_robe_id = ro.robe_id\n" +
                "    INNER JOIN rings ri ON i.craftable_ring_id = ri.ring_id";
            
            mainResults = mainStmt.executeQuery(mainQuery);
            while (mainResults.next()) {
                // LOADING STAFFS FROM DATABASE
                String staffName = mainResults.getString("staff_name");
                int staffAtk = mainResults.getInt("staff_attack");
                int staffAgil = mainResults.getInt("staff_agility");
                Staff newStaff = new Staff(staffName, 1, staffAtk, staffAgil);
                
                //LOADING ROBES FROM DATABASE
                String robeName = mainResults.getString("robe_name");
                int robeDef = mainResults.getInt("robe_defense");
                int robeHp = mainResults.getInt("robe_hp");
                Robe newRobe = new Robe(robeName, 1, robeDef, robeHp);

                
                //LOADING RINGS FROM DATABASE
                String ringName = mainResults.getString("ring_name");
                int ringMana = mainResults.getInt("ring_mane");
                int ringHp = mainResults.getInt("ring_hp");
                Ring newRing = new Ring(ringName, 1, ringMana, ringHp);
                
                //LOADING MATERIALITEMS FROM DATABASE
                String materialName = mainResults.getString("item_name");
                MaterialItem newItem = new MaterialItem(
                    materialName, 1, newStaff, newRobe, newRing);
                
                //ADDING ALL TO GAME MODEL
                model.staffList.add(newStaff);
                model.robeList.add(newRobe);
                model.ringList.add(newRing);
                model.materialList.add(newItem);
                
                // LOADING ENEMIES FROM DATABASE
                String enemyName = mainResults.getString("name");
                int enemyLevel = mainResults.getInt("level");
                int enemyMaxHp = mainResults.getInt("max_hp");
                int enemyMaxMana = mainResults.getInt("max_mana");
                int enemyAgil = mainResults.getInt("agility");
                int enemyAtk = mainResults.getInt("attack");
                int enemyDef = mainResults.getInt("defense");
                int enemyXpWorth = mainResults.getInt("xp_worth");
                Stats enemyStats = new Stats(enemyLevel, enemyMaxHp, enemyMaxMana,
                        enemyAgil, enemyAtk, enemyDef, enemyXpWorth, newItem);
                
                String atkAbilName = mainResults.getString("aa.ability_name");
                int atkManaCost = mainResults.getInt("aa.mana_cost");
                int atkDmg = mainResults.getInt("damage");
                int agilDmg = mainResults.getInt("agil_damage");
                AttackAbility newAttack = new AttackAbility(atkAbilName, atkManaCost, atkDmg, agilDmg);
                ArrayList<AttackAbility> enemyAttack = new ArrayList<>(List.of(newAttack));
                
                String defAbilName = mainResults.getString("da.ability_name");
                int defManaCost = mainResults.getInt("da.mana_cost");
                int defGain = mainResults.getInt("da.defense");
                int hp = mainResults.getInt("heal_amt");
                DefenseAbility newDefense = new DefenseAbility(defAbilName, defManaCost, defGain, hp);
                ArrayList<DefenseAbility> enemyDefense = new ArrayList<>(List.of(newDefense));
                
                int imageID = mainResults.getInt("image_id");
                
                AI newEnemy = new AI(enemyName, enemyStats, enemyAttack, enemyDefense, imageID);
               
                //ADDING ENEMY TO GAME MODEL
                switch (mainResults.getString("name")) {
                    case "Slime":
                        GameModel.ENEMIESMAP1.add(newEnemy);
                        break;
                    case "Crab":
                        GameModel.ENEMIESMAP2.add(newEnemy);
                        break;
                    case "Hulk":
                        GameModel.ENEMIESMAP3.add(newEnemy);
                        break;            
                }
                

                
               
                
            }
            mainResults.close();
            statStmt.close();
            mainStmt.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GameModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
  
}
