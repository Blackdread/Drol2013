package base.engine.entities.others.outputs;

public interface IFireOnce {

	
	public boolean isFireOnce();
	public void setFireOnce(boolean fireOnce);
	
	/**
	 * true -> once it has been fired if it's a fireOnce this will be deleted from the world
	 * Will always be false if it is not a fireOnce
	 */
	public boolean isHasbeenFired();
	public void setHasbeenFired(boolean hasBeenFired);
}
