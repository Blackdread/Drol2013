package base.engine.entities;

import org.newdawn.slick.geom.Vector2f;

public abstract class MoveableEntity extends ActiveEntity{
	Vector2f vitesse;
	
	public MoveableEntity(String name, int maxLife) {
		super(name, maxLife);
		vitesse = new Vector2f(0, 0);
	}
	
	public MoveableEntity(String name, int maxLife, int vx, int vy) {
		super(name, maxLife);
		vitesse = new Vector2f(vx, vy);
	}
	
}
