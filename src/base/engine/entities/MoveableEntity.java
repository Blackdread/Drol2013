package base.engine.entities;

import org.newdawn.slick.geom.Vector2f;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public abstract class MoveableEntity extends ActiveEntity implements IGravity{
	
	protected boolean gravityON;
	
	/**
	 * Weight
	 */
	protected int mass;
	protected Vector2f vitesse;
	
	protected Vector2f acceleration;
	
	public MoveableEntity(String name, int maxLife) {
		super(name, maxLife);
		vitesse = new Vector2f(0, 0);
	}

	public MoveableEntity(String name, int maxLife, int vx, int vy) {
		super(name, maxLife);
		vitesse = new Vector2f(vx, vy);
	}

	/**
	 * Gestion de la gravite dans cette update
	 */
	@Override
	public void update(int delta) {
		
		
	}
	
	@Override
	public Vector2f getvitesse() {
		return vitesse;
	}

	@Override
	public void setVitesse(Vector2f vitesse) {
		this.vitesse = vitesse;
	}

	@Override
	public Vector2f getAcceleration() {
		return acceleration;
	}

	@Override
	public void setAcceleration(Vector2f acceleration) {
		this.acceleration = acceleration;
	}

	@Override
	public boolean isGravityON() {
		return gravityON;
	}

	@Override
	public void setGravityON(boolean gravity) {
		gravityON = gravity;
	}
	
	@Override
	public int getMass(){
		return mass;
	}
	
	@Override
	public void setMass(int mass){
		this.mass = mass;
	}
	
}
