package base.engine.entities.others.outputs;

/**
 * Enable/disable this entity from performing its task.
 * @author Yoann CAPLAIN
 *
 */
public interface IDisable {

	public void setEnabled(boolean enabled);
	public boolean isDisabled();
	public boolean isEnabled();
	
	public void toggle();
	public void enable();
	public void disable();
	
	// Plus necessaire car si il faut qu'il soit disabled au lancement de la map, il suffit de serializer la map avec enabled
	// a false pour l'entite
	//public void setStartDisabled(boolean startDisabled);
	//public boolean isStartDisabled();
}
