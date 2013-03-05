package base.engine.entities.triggers.filters;

import base.engine.entities.triggers.outputs.InputsAndOutputs;

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
}
