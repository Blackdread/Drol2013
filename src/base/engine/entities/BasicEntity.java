package base.engine.entities;

import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import base.engine.entities.others.outputs.InputsAndOutputs;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public abstract class BasicEntity extends InputsAndOutputs implements IEntity {
	
	protected Shape shape;
	
	//protected int direction;
	protected Vector2f direction;
	
	public static final Vector2f HAUT = new Vector2f(0,1);
	public static final Vector2f BAS = new Vector2f(0,-1);
	public static final Vector2f GAUCHE = new Vector2f(-1,0);
	public static final Vector2f DROITE = new Vector2f(1,0);
	
	public BasicEntity(String name) {
		super(name);
		direction = HAUT;
	}

	@Override
	public int getHeight() {
		if(shape != null)
			//return (int) (shape.getMaxY()-shape.getMinY()); -> voir code source de shape -> c'est ce qui est fait
			return (int) shape.getHeight();
		else 
			return 32;
	}

	@Override
	public int getWidth() {
		if(shape != null)
			//return (int) (shape.getMaxX()-shape.getMinX()); -> voir code source de shape -> c'est ce qui est fait
			return (int) shape.getWidth();
		else 
			return 32;
	}

	public Vector2f getDirection() {
		return direction;
	}

	public void setDirection(Vector2f direction) {
		this.direction = direction;
	}
	
}
