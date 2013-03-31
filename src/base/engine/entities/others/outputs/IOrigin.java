package base.engine.entities.others.outputs;

/**
 * The position of this entity's center in the world. 
 * Rotating entities typically rotate around their origin
 * @see Works with IPosition
 * @author Yoann CAPLAIN
 *
 */
public interface IOrigin {
	
	public float getXorigin();
	public float getYorigin();
	//public float getZorigin();
	
	public float setXorigin(float x);
	public float setYorigin(float y);
	//public float setZorigin(float z);
	
	public void setLocationOrigin(float x, float y/*, float z*/);
}