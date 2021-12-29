/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Containers;

import Character.AI;
import Character.Battle;
import Character.Person;
import GUI.Controller;
import GUI.GameModel;
import GUI.Tile;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Marshall
 */
public class BattlePanel extends JPanel {
    public ImageIcon battleBackground = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Tile.class.getResource("/BattleImages/BattleBackground.png")));
    public ImageIcon invisibleImage = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Tile.class.getResource("/tiles/0.png")));    
    public JPanel characterPanel;
    public JPanel abilityPanel;
    public JPanel targetChoicesPanel;
    public JPanel decorPanel;
    public JLabel selectedAbility;
    public ArrayList<JButton> btnAttackAbilities;
    public ArrayList<JButton> btnDefenseAbilities;
    public ArrayList<JButton> btnAllyTargets;
    public ArrayList<JButton> btnEnemyTargets;
    public JButton btnRun;
    
        
    
    public BattlePanel() {
        setVisible(false);
        setLayout(new BorderLayout());
        btnAttackAbilities = new ArrayList<>();
        btnDefenseAbilities = new ArrayList<>();
        btnEnemyTargets = new ArrayList<>();
        btnAllyTargets = new ArrayList<>();
        initComponents();
    }
    
    public void initComponents() {
        initDecoration();
        initTargetChoice();
        initCharacters();
        //initAbilityOptions();
    }
    
    public void initDecoration() {
        decorPanel = new JPanel();
        decorPanel.setLayout(new BoxLayout(decorPanel, BoxLayout.Y_AXIS));
        decorPanel.add(new JLabel("TEST TEST TEST TEST TEST"));
        decorPanel.setOpaque(false);
        add(decorPanel, BorderLayout.EAST);
    }
    
    public void initAbilityOptions() {
        abilityPanel = new JPanel();
        abilityPanel.setVisible(false);
        abilityPanel.setLayout(new BoxLayout(abilityPanel, BoxLayout.Y_AXIS));
        abilityPanel.setOpaque(false);
        
        JLabel attackHeader = new JLabel("Attack Abilities");
        attackHeader.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
        abilityPanel.add(attackHeader);
        for (JButton btn : btnAttackAbilities) {
            abilityPanel.add(btn);
        }
        
        JLabel defenseHeader = new JLabel("Defense Abilities");
        defenseHeader.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
        abilityPanel.add(defenseHeader);
        for (JButton btn : btnDefenseAbilities) {
            abilityPanel.add(btn);
        }
        abilityPanel.add(btnRun);
        JLabel curAbilityHeader = new JLabel("Currently Selected Ability");
        curAbilityHeader.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
        abilityPanel.add(curAbilityHeader);
        selectedAbility = new JLabel("");
        selectedAbility.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
        selectedAbility.setForeground(Color.WHITE);
        abilityPanel.add(selectedAbility);
        
        add(abilityPanel, BorderLayout.WEST);
    }
    
    public void initCharacters() {
        characterPanel = new JPanel(new FlowLayout());
        characterPanel.setOpaque(false);
        add(characterPanel, BorderLayout.SOUTH);
        
    }
     
    public void initTargetChoice() {
        targetChoicesPanel = new JPanel();
        targetChoicesPanel.setVisible(false);
        targetChoicesPanel.setOpaque(false);
        targetChoicesPanel.setLayout(new BoxLayout(targetChoicesPanel, BoxLayout.Y_AXIS));
        JLabel targets = new JLabel("Targets");
        targets.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        targetChoicesPanel.add(targets);
        for (JButton btn : btnEnemyTargets) {
            targetChoicesPanel.add(btn);
        }
  
        
        
        
        add(targetChoicesPanel, BorderLayout.CENTER);
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(battleBackground.getImage(), 0, 0, null);
    }
    
    public void updatePanels(ArrayList<Person> aliveAllies, ArrayList<Person> aliveEnemies) {
        characterPanel.removeAll();
        for(Person ally : aliveAllies) {
            characterPanel.add(new JLabel(ally.getCharacterImage()));
        }
        characterPanel.add(new JLabel(invisibleImage));
        characterPanel.add(new JLabel(invisibleImage));
        characterPanel.add(new JLabel(invisibleImage));
        characterPanel.add(new JLabel(invisibleImage));
        characterPanel.add(new JLabel(invisibleImage));
        characterPanel.add(new JLabel(invisibleImage));
        characterPanel.add(new JLabel(invisibleImage));
        for(Person enemy : aliveEnemies) {
            characterPanel.add(new JLabel(enemy.getCharacterImage()));
        }
    }
    

        
}
    
   
