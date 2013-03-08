package base.engine.entities.triggers.outputs;

public interface IUpdatable {

	/**
	 * 
	 * @param delta since last update (in milliseconds)
	 */
	public void update(final int delta);
}
