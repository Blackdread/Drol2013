package base.engine.entities.others.filters;

import base.engine.entities.others.outputs.InputsAndOutputs;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public abstract class Filter extends InputsAndOutputs{
	
	/**
	 * Inverts the filter, making the specified concept fail and all others pass
	 * @value true -> invert the filter
	 * @Default value false
	 */
	protected boolean negateFilter = false;
	
	/**
	 * 
	 * @param String targetname that other entities refer to this entity by
	 */
	public Filter(String name){
		super(name);
	}
	/**
	 * 
	 * @param String targetname that other entities refer to this entity by
	 * @param Boolean negate invert result
	 */
	public Filter(String name, boolean negate){
		super(name);
		this.negateFilter = negate;
	}
	

	public abstract boolean checkFilterConditions();
	/**
	 * 
	 * @param activatorName Name of the entity that triggered the trigger then the trigger send the name to the filter
	 * @return 
	 */
	public abstract boolean checkFilterConditions(final String activatorName);
	/**
	 * 
	 * @param activator The entity that triggered the trigger
	 * @return 
	 */
	public abstract boolean checkFilterConditions(Object activator);
}
