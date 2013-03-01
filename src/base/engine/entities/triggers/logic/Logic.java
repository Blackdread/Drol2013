package base.engine.entities.triggers.logic;

public abstract class Logic {

	/**
	 * The targetname that other entities refer to this entity by
	 */
	protected String name;
	
	
	public Logic(String name){
		this.name = name;
	}
	
	public abstract void fireOutputs();
}
