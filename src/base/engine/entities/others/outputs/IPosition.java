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
	
	public float setX(float x);
	public float setY(float y);
	//public float setZ(float z);
	
	public void setLocation(float x, float y/*, float z*/);
}
