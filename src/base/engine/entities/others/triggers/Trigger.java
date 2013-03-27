package base.engine.entities.others.triggers;

import java.util.ArrayList;

import base.engine.entities.others.filters.Filter;
import base.engine.entities.others.outputs.IDisable;
import base.engine.entities.others.outputs.IFireOnce;
import base.engine.entities.others.outputs.IUpdatable;
import base.engine.entities.others.outputs.InputsAndOutputs;
import base.utils.Timer;

/**
 * Trigger is a brush entity
 * It is a volume that fires outputs when a specified type of entity enters or leaves it.
 * @author Yoann CAPLAIN
 *
 */
public abstract class Trigger extends InputsAndOutputs implements ITrigger, IDisable, IFireOnce, IUpdatable {
	
	/**
	 * true -> once it has been fired if it's a fireOnce this will be deleted from the world
	 * Will always be false if it is not a fireOnce
	 */
	protected boolean hasbeenFired;
	
	protected boolean enabled;
	
	/**
	 * Amount of time, in seconds, after the trigger_multiple has triggered before it can be triggered again.
	 */
	protected Timer delayBeforeReset;
	
	// *******
	// Filters
	// *******
	/**
	 * Filter entities can be used to restrict what activates the trigger
	 */
	protected Filter filterEntityThatActivate;
	
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
	 * Need to DESTROY the trigger (remove it from TriggerManager etc)
	 */
	protected boolean fireOnce;
	
	public Trigger(String name) {
		super(name);
		delayBeforeReset = new Timer(1000);
	}
	
	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	/**
	 * @return true it's disabled
	 */
	@Override
	public boolean isDisabled() {
		return !enabled;
	}
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	@Override
	public boolean isFireOnce() {
		return fireOnce;
	}
	@Override
	public void setFireOnce(boolean fireOnce) {
		this.fireOnce = fireOnce;
	}
	@Override
	public boolean isHasbeenFired() {
		return hasbeenFired;
	}
	@Override
	public void setHasbeenFired(boolean hasBeenFired) {
		this.hasbeenFired = hasBeenFired;
	}
	@Override
	public void toggle() {
		enabled = !enabled;
	}


}
