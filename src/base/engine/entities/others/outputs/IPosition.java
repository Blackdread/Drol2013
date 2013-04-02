package base.engine.entities.others.outputs;

/**
 * 
 * @see Works with IOrigin
 * @author Yoann CAPLAIN
 *
 */
public interface IPosition {

	public float getX();
	public float getY();
	//public float getZ();
	
	public void setX(float x);
	public void setY(float y);
	//public void setZ(float z);
	
	public void setLocation(float x, float y/*, float z*/);
}
