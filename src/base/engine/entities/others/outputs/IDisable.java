package base.engine.entities.others.outputs;

/**
 * Enable/disable this entity from performing its task.
 * @author Yoann CAPLAIN
 *
 */
public interface IDisable {

	public void setEnable(boolean enabled);
	public boolean isDisabled();
	public boolean isEnabled();
	
	public void setStartDisabled(boolean startDisabled);
	public boolean isStartDisabled();
}
