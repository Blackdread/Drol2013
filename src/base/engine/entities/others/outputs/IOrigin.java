package base.engine.entities.others.outputs;

/**
 * The position of this entity's center in the world. 
 * Rotating entities typically rotate around their origin
 * 
 * If x and y origins equals zero, this means that entity doesn't need to have an origin (like InfoTarget, Filter, Logic)
 * 
 * @see Works with IPosition
 * @author Yoann CAPLAIN
 *
 */
public interface IOrigin {
	
	public float getXorigin();
	public float getYorigin();
	//public float getZorigin();
	
	public void setXorigin(float x);
	public void setYorigin(float y);
	//public void setZorigin(float z);
	
	public void setLocationOrigin(float x, float y/*, float z*/);
}