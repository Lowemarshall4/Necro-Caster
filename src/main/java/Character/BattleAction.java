/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Character;

import Ability.AttackAbility;
import Ability.DefenseAbility;
import java.util.ArrayList;




public interface BattleAction {
        
    public abstract void pickAction(ArrayList<Person> allies, ArrayList<Person> enemies);
    
    public abstract void defenseAction(ArrayList<Person> allies);
        
    public abstract void attack(AttackAbility ability, Person target);
    
    public abstract void defend(DefenseAbility ability, Person target);
       
    public abstract AttackAbility chooseAtkAbility();
    
    public abstract DefenseAbility chooseDefAbility();
    
    public abstract Person chooseAtkTarget(ArrayList<Person> playerTeam, ArrayList<Person> enemyTeam);
    
    public abstract Person chooseDefTarget(ArrayList<Person> allies);

           
}
