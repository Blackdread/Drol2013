package base.engine.entities;

import org.newdawn.slick.geom.Vector2f;

import base.engine.CollisionManager;
import base.engine.EngineManager;
import base.engine.Game;
import base.engine.Message;
import base.engine.MessageKey;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public abstract class MoveableEntity extends ActiveEntity implements IGravity{
	
	protected boolean gravityON = true;
	protected static final float gravity = 0.00681f;
	
	/**
	 * Weight
	 * in grammes (not sure, maybe mg)
	 */
	protected int mass = 1;
	protected Vector2f vitesse;
	/**
	 * If = 0 -> no limit
	 */
	protected float vitesseMax = 0;
	/**
	 * @see IGravity
	 */
	protected float defaultVitesse = 2;
	
	protected Vector2f acceleration;
	protected float accelerationEntity;
	
	protected boolean moving;
	
	public MoveableEntity(String name, EngineManager e, int maxLife) {
		super(name, e, maxLife);
		vitesse = new Vector2f(0, 0);
		acceleration = new Vector2f(0, 0);
		moving = false;
	}
	
	/**
	 * Gestion de la gravite dans cette update et du deplacement
	 */
	@Override
	public void update(int delta) {
		//vitesse = vitesse.add(acceleration.scale(((float)delta)/1000.0f));
		super.update(delta);
		if(acceleration.x != 0 || acceleration.y != 0){
			if(vitesseMax != 0){
				if(Math.sqrt(Math.abs(vitesse.dot(vitesse))) >= vitesseMax){
					// On garde le vecteur dans la meme orientation mais on le reduit pour ne pas depasser la vitesse max
					
				}else{
					vitesse.add(acceleration.scale(((float)delta)/1000.0f)); // Si le vecteur acceleration n'est pas colineaire a la vitesse, la trajectoire va changer, etc

					
				}
			}else{
				vitesse.add(acceleration.scale(((float)delta)/1000.0f)); // Si le vecteur acceleration n'est pas colineaire a la vitesse, la trajectoire va changer, etc
				
			}
		}
			
		if(gravityON){
			// TODO gerer le poids
			vitesse.y += mass * gravity * delta;
		}

		if(vitesse.x != 0 || vitesse.y != 0){
			//Si il bouge
			
			Message m = new Message();
			m.instruction = MessageKey.I_MOVE_ENTITY;
			m.i_data.put(MessageKey.P_ID, id);
			m.i_data.put(MessageKey.P_X, (int)vitesse.x);
			m.i_data.put(MessageKey.P_Y, (int)vitesse.y);
			m.engine = EngineManager.LOGIC_ENGINE;
			
			engineManager.receiveMessage(m);
		}
	}
	
	@Override
	public void onCollision(ICollidableObject collideWith) {
		// TODO collision sur les x
		//if(collideWith == null && !CollisionManager.isEntityCollidingWithLeftOrRight(this)){	// TODO verifier que c bien la collision sur y
		if(collideWith == null && (CollisionManager.isEntityCollidingWithGround(this) || CollisionManager.isEntityCollidingWithTop(this))){
			vitesse.y = 0.00000f;
			
			System.out.println("vitesse mis a zero");
		}
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
	@Override
	public float getDefaultVitesse(){
		return defaultVitesse;
	}
	@Override
	public void setDefaultVitesse(float vitesse){
		defaultVitesse = vitesse;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}
}
