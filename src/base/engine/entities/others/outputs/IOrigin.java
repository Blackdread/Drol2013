package base.engine.entities.others.outputs;

/**
 * The position of this entity's center in the world. 
 * Rotating entities typically rotate around their origin
 * @author Yoann CAPLAIN
 *
 */
public interface IOrigin {
	
	public float getX();
	public float getY();

}