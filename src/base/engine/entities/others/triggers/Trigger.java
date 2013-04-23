package base.engine.entities.others.triggers;

import java.util.ArrayList;

import base.engine.EngineManager;
import base.engine.entities.BasicEntity;
import base.engine.entities.others.filters.Filter;
import base.engine.entities.others.outputs.IDisable;
import base.engine.entities.others.outputs.IFireOnce;
import base.engine.entities.others.outputs.IUpdatable;
import base.utils.Timer;

/**
 * Trigger is a brush entity
 * It is a volume that fires outputs when a specified type of entity enters or leaves it.
 * @see TriggerObjectInZone TriggerObjectInZone is the ONLY class that may herit from Trigger
 * @author Yoann CAPLAIN
 *
 */
public abstract class Trigger extends BasicEntity implements ITrigger, IDisable, IFireOnce, IUpdatable {
	
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
	 * TODO Mettre le nom du filter (String)
	 */
	private Filter filterEntityThatActivate;
	
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
	
	public Trigger(EngineManager e,String name) {
		super(e,name);
		hasbeenFired = false;
		delayBeforeReset = new Timer(1000);
	}
	
	public ArrayList<String> get_list_inputs() {
		ArrayList<String> list_inputs = new ArrayList<String>();
		list_inputs.addAll(super.get_list_inputs());
		list_inputs.add("Enable");
		list_inputs.add("Disable");
		list_inputs.add("Toggle");
		
		return list_inputs;
	}
	
	@Override
	public void fireOutputs(String nameOfOutput) {
		if(nameOfOutput != null){
			if(nameOfOutput.equalsIgnoreCase("Enable"))
				setEnabled(true);
			else if(nameOfOutput.equalsIgnoreCase("Disable"))
				setEnabled(false);
			else if(nameOfOutput.equalsIgnoreCase("Toggle"))
				toggle();
			else
				super.fireOutputs(nameOfOutput);
		}
	}
	
	/**
	 * 
	 * @param entityToFilter
	 * @return true if the entity has passed the filter
	 */
	protected boolean testFilter(Object entityToFilter){
		// TODO le filtre peut changer en un String (donc faire une recherche dans le filterManager) ou rester comme il est
		if(filterEntityThatActivate != null){
			return filterEntityThatActivate.checkFilterConditions(entityToFilter);
		}else
			return true;
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

	public Filter getFilterEntityThatActivate() {
		return filterEntityThatActivate;
	}

	public void setFilterEntityThatActivate(Filter filterEntityThatActivate) {
		this.filterEntityThatActivate = filterEntityThatActivate;
	}


}
