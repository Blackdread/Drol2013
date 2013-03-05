package base.engine.entities.triggers;

import java.util.ArrayList;

import base.engine.Engine;
import base.engine.entities.BasicEntity;
import base.engine.entities.triggers.filters.Filter;
import base.engine.entities.triggers.outputs.InputsAndOutputs;
import base.utils.Timer;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public abstract class Trigger extends InputsAndOutputs implements ITrigger {
	
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
	 * @deprecated May be deleted and a filter will be used
	 */
	protected ArrayList<Integer> arrayFilterEntityIdThatActivate = new ArrayList<Integer>();
	
	
	// *******
	// Flags
	// *******	
	/**
	 * Trigger qui se declenche une fois puis plus jamais sinon plusieurs fois
	 * Need to DESTROY the trigger (remove it from TriggerManager then garbageColector)
	 */
	protected boolean triggerOnce;
	
	public Trigger(String name) {
		super(name);
	}
	
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

}
