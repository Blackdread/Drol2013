package base.engine.entities;

import org.newdawn.slick.geom.Shape;

public interface ICollidableObject {

	/**
	 * Shape without the position of the object
	 */
	public Shape getNormalCollisionShape();
	 /**
	  * Collision shape adapted to the position of the object
	  */
	public Shape getCollisionShape();
 
	public int getCollisionType();
	
	public void updateShape();
 
	public boolean isCollidingWith(ICollidableObject collidable);
	
	public boolean onCollision();
	
}
