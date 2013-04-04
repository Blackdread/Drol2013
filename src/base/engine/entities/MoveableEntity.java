package base.engine.entities;

import org.newdawn.slick.geom.Vector2f;

	
public abstract class MoveableEntity extends ActiveEntity implements IGravity{
	
	protected boolean gravityON;
	
	/**
	 * Weight
	 */
	protected int mass;
	//protected float vitesse;
	protected Vector2f vitesse;
	
	protected float acceleration;
	
	public MoveableEntity(String name, int maxLife) {
		super(name, maxLife);
		vitesse = new Vector2f(0, 0);
	}

	/**
	 * Gestion de la gravite dans cette update
	 */
	@Override
	public void update(int delta) {
		
		
	}
	
	@Override
	public float getvitesse() {	// TODO a revoir car vector2f
		//return vitesse;
		return 1;
	}

	@Override
	public void setVitesse(float vitesse) {	// TODO a revoir car vector2f
		//this.vitesse = vitesse;
	}

	@Override
	public float getAcceleration() {	// TODO a revoir car vector2f
		return acceleration;
	}

	@Override
	public void setAcceleration(float acceleration) {	// TODO a revoir car vector2f
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
	
	public MoveableEntity(String name, int maxLife, int vx, int vy) {
		super(name, maxLife);
		vitesse = new Vector2f(vx, vy);
	}
	
}
