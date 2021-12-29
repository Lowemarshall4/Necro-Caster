
import Ability.AttackAbility;
import Ability.DefenseAbility;
import Inventory.Equipment;
import Inventory.Inventory;
import Character.Player;
import Character.Stats;
import Inventory.Item;
import Inventory.MaterialItem;
import Inventory.Ring;
import Inventory.Robe;
import Inventory.Staff;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Marshall
 */
public class InventoryTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        DefenseAbility heal = new DefenseAbility("Heal", 10, 0, 25);
        AttackAbility attack = new AttackAbility("Attack", 5, 20, 4);
        ArrayList atkAbilities = new ArrayList<AttackAbility>();
        ArrayList defAbilities = new ArrayList<DefenseAbility>();
        defAbilities.add(heal);
        atkAbilities.add(attack);
        
        Staff staff = new Staff("Cosmic", 1, 5, 2);
        Robe robe = new Robe("Gsorc", 1, 10, 100);
        Ring ring = new Ring("Exa HP", 1, 0, 140);
        
        Staff staff2 = new Staff("Slime Staff", 1, 10, 1);
        Robe robe2 = new Robe("Slime Robe", 1, 5, 200);
        Ring ring2 = new Ring("Slime Ring", 1, 100, 100);
        
        Staff staff3 = new Staff("Bone Staff", 1, 20, 1);
        Robe robe3 = new Robe("Bone Robe", 1, 7, 300);
        Ring ring3 = new Ring("Bone Ring", 1, 300, 100);

        
        
        
        MaterialItem slime = new MaterialItem("slime", 100, staff2, robe2, ring2);
        MaterialItem bone = new MaterialItem("bone", 100, staff3, robe3, ring3);
        
        Equipment equipment = new Equipment(staff, robe, ring);
        Inventory inventory = new Inventory(equipment);
        
        Stats playerStats = new Stats(1, 100, 50, 1, 1, 1, 0, null);
        playerStats.updateStatsForEquipment(equipment);
        
       /* 
        Player Mith = new Player("Mith", playerStats, atkAbilities, defAbilities, inventory);
        
        System.out.println("MATERIALS: " + Mith.getInventory().getMaterials());
        System.out.println("EQUIPPABLES: " + Mith.getInventory().getEquippables());
        System.out.println(Mith.getInventory().getEquipment());
        
        Mith.getInventory().addItem(slime);
        
        Mith.getInventory().craftStaff(slime);
        Mith.getInventory().craftRobe(slime);
        Mith.getInventory().craftRing(slime);
        
   
        System.out.println("MATERIALS: " + Mith.getInventory().getMaterials());
        System.out.println("EQUIPPABLES: " + Mith.getInventory().getEquippables());
        System.out.println(Mith.getInventory().getEquipment());
        
        Mith.getInventory().equipStaff((Staff)Mith.getInventory().getEquippables().get((0))); //NEEDS CASTED FOR NOW Create a get method for UI
        
        System.out.println("MATERIALS: " + Mith.getInventory().getMaterials());
        System.out.println("EQUIPPABLES: " + Mith.getInventory().getEquippables());
        System.out.println(Mith.getInventory().getEquipment());
        System.out.println(Mith.getInventory().getEquipment().getEquippedStaff().getAtk());
        
        Mith.getInventory().equipRobe((Robe)Mith.getInventory().getEquippables().get(0));
        
        System.out.println("MATERIALS: " + Mith.getInventory().getMaterials());
        System.out.println("EQUIPPABLES: " + Mith.getInventory().getEquippables());
        System.out.println(Mith.getInventory().getEquipment());
        System.out.println(Mith.getInventory().getEquipment().getEquippedRobe());
        System.out.println(Mith.getInventory().getEquipment().getEquippedRobe().getDef());

        */
    }
}
