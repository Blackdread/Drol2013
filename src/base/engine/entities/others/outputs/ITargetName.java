package base.engine.entities.others.outputs;

public interface ITargetName {
	
	/**
	 * The targetname that other entities refer to this entity by
	 * Should be unique but having the same name may be useful for some outputs
	 * Everywhere the name will be supposed not unique
	 */
	public void setTargetName(String name);
	/**
	 * The targetname that other entities refer to this entity by
	 * Should be unique but having the same name may be useful for some outputs
	 * Everywhere the name will be supposed not unique
	 */
	public String getTargetName();
}
