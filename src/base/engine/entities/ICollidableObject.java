package base.engine.entities;

import org.newdawn.slick.geom.Shape;

/**
 * Un choix doit etre fait pour le shape soit il update sa position par rapport a l'objet qu'il represente soit il
 * est a la position (0,0) et on appel getCollisionShape() pour avoir le shape au bon endroit
 * @author Yoann CAPLAIN
 *
 */
public interface ICollidableObject {

	/**
	 * Shape without the position of the object
	 * if the position of this shape is different of (0,0) we return a translated shape with coordinates (0,0)
	 */
	public Shape getNormalCollisionShape();
	 /**
	  * Collision shape adapted to the position of the object
	  * if the position of this shape is (0,0) we return a translated shape with coordinates of the entity that owns it
	  */
	public Shape getCollisionShape();
	
	//public int getCollisionType();
	
	public void updateShape();
 
	/**
	 * Check with getCollisionShape()
	 * @param collidable
	 * @return true if they intersect or one of them is contained within shape
	 */
	public boolean isCollidingWith(ICollidableObject collidable);
	
	/**
	 * @return false means the entity doesn't need to verify it's colliding with anything
	 */
	public boolean isCollisionON();
	public void setCollisionON(boolean collision);
	
	public void onCollision(ICollidableObject collideWith);
	
}
