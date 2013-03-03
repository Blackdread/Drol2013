package base.engine.entities.triggers;

import java.util.ArrayList;

import base.engine.Engine;
import base.engine.entities.BasicEntity;
import base.engine.entities.triggers.filters.Filter;
import base.utils.Timer;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public abstract class Trigger implements ITrigger {

	/**
	 * The targetname that other entities refer to this entity by
	 */
	protected String name;
	
	/**
	 * 
	 */
	protected int triggerID;
	protected int triggerType;
	
	/**
	 * Vraie tant que le trigger est dans un etat triggerON et ses conditions validees.
	 */
	protected boolean isTriggerDeclenched;
	/**
	 * Si le trigger est "operationelle", -> agit ou pas
	 */
	protected boolean triggerON;
	
	/**
	 * Temps avant que le trigger soit declencher si condition validee
	 */
	protected Timer TimeBeforeDeclencheTrigger;
	
	// *******
	// Filters
	// *******
	// Il sera possible de cree une classe Filter puis de faire l'heritage et avoir : 
	// Filter damage type, Filter enemy, Filter health, Filter activator name, Filter activator class, etc
	/**
	 * Filter entities can be used to restrict what activates the trigger.
	 * Based on pointers !
	 */
	protected ArrayList<Filter> arrayFilterEntityThatActivate = new ArrayList<Filter>();
	
	/**
	 * Filter entities id can be used to restrict what activates the trigger.
	 * Example : player = 0 ; playerEnemy = 1 ; balle = 2 ; arrow = 3 etc
	 */
	protected ArrayList<Integer> arrayFilterEntityIdThatActivate = new ArrayList<Integer>();
	
	
	
	
	/**
	 * ArrayList qui contient les entites sur lesquelles le trigger agit
	 * Pour TriggerObjectInZone cet array contient les objets dans la zone
	 */
	protected ArrayList<BasicEntity> arrayEntityToActON = new ArrayList<BasicEntity>();
	
	// *******
	// Flags
	// *******	
	/**
	 * Trigger qui se declenche une fois puis plus jamais sinon plusieurs fois
	 */
	protected boolean triggerOnce;
	
	
	/**
	 * @return boolean Si le trigger est "operationelle" = peut etre declencher
	 */
	public boolean isTriggerON() {
		// TODO Auto-generated method stub
		return triggerON;
	}
	public void setTriggerON(boolean set) {
		triggerON = set;
	}
	public boolean isTriggerDeclenched() {
		return isTriggerDeclenched;
	}
	public void setTriggerDeclenched(boolean a) {
		isTriggerDeclenched = a;
	}
	public boolean isTriggerOnce() {
		return triggerOnce;
	}
	public void setTriggerOnce(boolean triggerOnce) {
		this.triggerOnce = triggerOnce;
	}
	public ArrayList<BasicEntity> getArrayEntityToActON(){
		return arrayEntityToActON;
	}
	public void setArrayEntityToActON(ArrayList<BasicEntity> arrayEntity) {
		this.arrayEntityToActON = arrayEntity;
	}
}
