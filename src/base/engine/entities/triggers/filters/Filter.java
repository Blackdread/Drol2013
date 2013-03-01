package base.engine.entities.triggers.filters;

public abstract class Filter {
	
	/**
	 * The targetname that other entities refer to this entity by
	 */
	protected String name;
	
	/**
	 * Inverts the filter, making the specified concept fail and all others pass
	 * @value true -> invert the filter
	 * @Default value false
	 */
	protected boolean negateFilter = false;
	
	/**
	 * 
	 * @param String name to compare to 
	 */
	public Filter(String name){
		this.name = name;
	}
	/**
	 * 
	 * @param String name to compare to
	 * @param Boolean negate invert result
	 */
	public Filter(String name, boolean negate){
		this.name = name;
		this.negateFilter = negate;
	}
	

	public abstract boolean checkFilterConditions();
}
