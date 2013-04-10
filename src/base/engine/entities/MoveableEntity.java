package base.engine.entities;

import org.newdawn.slick.geom.Vector2f;

import base.engine.EngineManager;
import base.engine.Message;
import base.engine.MessageKey;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public abstract class MoveableEntity extends ActiveEntity implements IGravity{
	
	protected boolean gravityON = true;
	protected static final float gravity = 9.81f;
	
	/**
	 * Weight
	 * in grammes (not sure, maybe mg)
	 */
	protected int mass = 1;
	protected Vector2f vitesse;
	/**
	 * If = 0 -> no limit
	 */
	protected float vitesseMax = 1;
	
	protected Vector2f acceleration;
	protected float accelerationEntity;
	
	public MoveableEntity(String name, int maxLife) {
		super(name, maxLife);
		vitesse = new Vector2f(0, 0);
		acceleration = new Vector2f(0, 0);
	}

	public MoveableEntity(String name, int maxLife, int vx, int vy) {
		super(name, maxLife);
		vitesse = new Vector2f(vx, vy);
		acceleration = new Vector2f(0, 0);
	}

	/**
	 * Gestion de la gravite dans cette update et du deplacement
	 */
	@Override
	public void update(int delta) {
		//vitesse = vitesse.add(acceleration.scale(((float)delta)/1000.0f));
		
		if(acceleration.x != 0 || acceleration.y != 0){
			if(vitesseMax != 0){
				if(Math.sqrt(Math.abs(vitesse.dot(vitesse))) >= vitesseMax){
					// On garde le vecteur dans la meme orientation mais on le reduit pour ne pas depasser la vitesse max
				}else{
					vitesse.add(acceleration.scale(((float)delta)/1000.0f)); // Si le vecteur acceleration n'est pas colineaire a la vitesse, la trajectoire va changer, etc
				}
			}else
				vitesse.add(acceleration.scale(((float)delta)/1000.0f)); // Si le vecteur acceleration n'est pas colineaire a la vitesse, la trajectoire va changer, etc
		}
			
		if(gravityON){
			// TODO gerer le poids
			// Est-ce bien vitesse qu'il faut changer ou la position ?
			vitesse.y += (float)mass/1000.0f * gravity * (float)delta/1000.0f;	// TODO formule juste ?
		}
		
		
		Message m = new Message();
		m.instruction = MessageKey.I_MOVE_ENTITY;
		m.i_data.put(MessageKey.P_ID, id);
		m.i_data.put(MessageKey.P_X, (int)vitesse.x);
		m.i_data.put(MessageKey.P_Y, (int)vitesse.y);
		m.engine = EngineManager.LOGIC_ENGINE;
		
		EngineManager.getInstance().receiveMessage(m);
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
	
	@Override
	public float getVitesseMax() {
		return vitesseMax;
	}

	@Override
	public void setVitesseMax(float vitesse) {
		vitesseMax = vitesse;
	}
	@Override
	public float getGravity(){
		return gravity;
	}
	@Override
	public void setVitesseToZero(){
		vitesse.x = 0;
		vitesse.y = 0;
	}
	@Override
	public void setAccelerationToZero(){
		acceleration.x = 0;
		acceleration.y = 0;
	}
	@Override
	public float getAccelerationEntity(){
		return accelerationEntity;
	}
	@Override
	public void setAccelerationEntity(float acceleration){
		accelerationEntity = acceleration;
	}
}
