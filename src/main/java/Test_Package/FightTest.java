import Ability.DefenseAbility;
import Ability.AttackAbility;
import Character.AI;
import Character.Person;
import Character.Battle;
import Inventory.Equipment;
import Character.Player;
import Character.Stats;
import java.util.ArrayList;
import Inventory.Staff;
import Inventory.Robe;
import Inventory.Ring;
import Inventory.Inventory;
import Inventory.MaterialItem;


public class FightTest {
    
    public static void main(String args[]) {
        //Ability Instantiations
        DefenseAbility heal = new DefenseAbility("Heal", 10, 0, 25);
        AttackAbility attack = new AttackAbility("Attack", 5, 20, 4);
        ArrayList atkAbilities = new ArrayList<AttackAbility>();
        ArrayList defAbilities = new ArrayList<DefenseAbility>();
        defAbilities.add(heal);
        atkAbilities.add(attack);
        
        //Item/Stat Instantiations
        Staff staff = new Staff("Cosmic", 1, 1, 2);
        Robe robe = new Robe("Gsorc", 1, 50, 10);
        Ring ring = new Ring("Exa HP", 1, 0, 140);
        Staff staff2 = new Staff("Slime Staff", 1, 10, 1);
        Robe robe2 = new Robe("Slime Robe", 1, 5, 200);
        Ring ring2 = new Ring("Slime Ring", 1, 100, 100);
        MaterialItem slime = new MaterialItem("slime", 100, staff2, robe2, ring2);
        Equipment equipment = new Equipment(staff, robe, ring);
        Inventory inventory = new Inventory(equipment);
        Stats playerStats = new Stats(1, 100000000, 50, 1, 1, 1, 0, null);
        playerStats.updateStatsForEquipment(equipment);   
        Stats aiStats = new Stats(0, 50, 25, 1, 1, 1, 50, slime);
        
        
        Player Mith = new Player("Mith", playerStats, atkAbilities, defAbilities, inventory);
       // AI Exotixx = new AI("Exotixx", aiStats, atkAbilities, defAbilities);
        // AI DonHam = new AI("DonHam", aiStats, atkAbilities, defAbilities);
        // AI Physixx = new AI("Physixx", aiStats, atkAbilities, defAbilities);
      //  System.out.println(Exotixx.getStats().getAtk());
        
        
        
        
        ArrayList<Person> enemies = new ArrayList<>();
        //enemies.add(Exotixx); enemies.add(Physixx); enemies.add(DonHam);
        
        
        
      /*  Battle battle = new Battle(enemies, Mith);

        System.out.println(Mith.getStats().getMaxHp());
        System.out.println("ALLIES");
        for(Person ally : Mith.allies) {
            System.out.println(ally.getName());
        }
        battle.battleLoop();
        
        System.out.println(Mith.getStats().getLevel());
        System.out.println(Mith.getXp());
        System.out.println(Mith.getXpToNextLevel());
        
        for(Person ally : Mith.allies) {
            System.out.println(ally.getName());
        }
        
        /*
        System.out.println("Mith Stats: HP->" + Mith.getHp() + " Mana->" + Mith.getMana());
        System.out.println("Exotixx Stats: HP->" + Exotixx.getHp() + " Mana->" + Exotixx.getMana());
        
        Mith.attack(Exotixx, attack);
        Mith.attack(Exotixx, attack);
        
        System.out.println("Mith Stats: HP->" + Mith.getHp() + " Mana->" + Mith.getMana());
        System.out.println("Exotixx Stats: HP->" + Exotixx.getHp() + " Mana->" + Exotixx.getMana());
        
        Exotixx.defend(heal);
        System.out.println("Exotixx Stats: HP->" + Exotixx.getHp() + " Mana->" + Exotixx.getMana());
        */
        
    }
}
