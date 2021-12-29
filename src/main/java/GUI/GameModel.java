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
import GUI.Containers.MapPanel;
import GUI.Containers.StatsPanel;
import Inventory.Equipment;
import Inventory.Inventory;
import Inventory.MaterialItem;
import Inventory.Ring;
import Inventory.Robe;
import Inventory.Staff;
import Objects.Enemies;
import Objects.Maps;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;



/**
 *
 * @author Marshall
 */
public class GameModel {
    private TileMap map1 = Maps.MAP1;
    private TileMap map2 = Maps.MAP2;
    private TileMap map3 = Maps.MAP3;
    
    private AttackAbility basicAttack;
    private DefenseAbility heal;
    public ArrayList<AttackAbility> attackAbilities;
    public ArrayList<DefenseAbility> defenseAbilities;
    private Stats playerStats;
    private Equipment equipment;
    private Staff basicStaff;
    private Robe basicRobe;
    private Ring basicRing;
    private Inventory inventory;
    public Player player;
    public volatile Battle battle;
    
    public static ImageIcon PLAYERMAPIMAGE = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Tile.class.getResource("/tiles/7.png")));
    public static ImageIcon PLAYERBATTLEIMAGE = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Tile.class.getResource("/characterImages/0.png")));
    public static ArrayList<Person> ENEMIESMAP1;
    public static ArrayList<Person> ENEMIESMAP2;
    public static ArrayList<Person> ENEMIESMAP3;

    public static ImageIcon INVISIBLEIMAGE = new ImageIcon(Toolkit.getDefaultToolkit().getImage(Tile.class.getResource("/tiles/0.png")));    
    public ArrayList<MaterialItem> materialList;
    public ArrayList<Staff> staffList;
    public ArrayList<Robe> robeList;
    public ArrayList<Ring> ringList;
    int curMap;
    
    public GameModel() {

        
        staffList = new ArrayList<>();
        robeList = new ArrayList<>();
        ringList = new ArrayList<>();
        materialList = new ArrayList<>();
        attackAbilities = new ArrayList<>();
        defenseAbilities = new ArrayList<>();
        
        map1.belowMap = map2;
        map2.aboveMap = map1;
        map2.belowMap = map3;
        map3.aboveMap = map2;
        
        curMap = 0;
        
        basicAttack = new AttackAbility("Basic Attack", 5, 10, 0);
        heal = new DefenseAbility("Heal", 10, 0, 30);
        
        attackAbilities.add(basicAttack);
        defenseAbilities.add(heal);
        
        
        playerStats = new Stats(1, 100, 100, 1, 1, 1, 100, null);
        
        
        
        basicRobe = new Robe("Basic Robe", 1, 1, 10);
        basicStaff = new Staff("Basic Staff", 1, 1, 1);
        basicRing = new Ring("Basic Ring", 1, 5, 5);

        
        equipment = new Equipment(basicStaff, basicRobe, basicRing);
        inventory = new Inventory(equipment);
        
        
        
        
        
        player = new Player("Mith", playerStats, attackAbilities, defenseAbilities, inventory);

        ENEMIESMAP1 = Enemies.MAP1ENEMIES;
        ENEMIESMAP2 = Enemies.MAP2ENEMIES;
        ENEMIESMAP3 = Enemies.MAP3ENEMIES;
        

        
    }
    
    public TileMap getMap1() {
        return map1;
    }
}
