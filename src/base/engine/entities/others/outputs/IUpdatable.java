package base.engine.entities.others.outputs;

/**
 * 
 * @author Yoann CAPLAIN
 * 
 */
public interface IUpdatable {

	/**
	 * 
	 * @param delta since last update (in milliseconds)
	 */
	public void update(final int delta);
}
