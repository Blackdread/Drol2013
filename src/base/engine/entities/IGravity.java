package base.engine.entities;

import org.newdawn.slick.geom.Vector2f;

/**
 * Mettre un boolean pour savoir si l'objet depend de la gravite
 * @author Yoann CAPLAIN
 *
 */
public interface IGravity {
	
	//public void updateGravity(final int delta);
	public void update(final int delta);
	
	//public int getRotation();
	//public void setRotation(int rotation);
	
	/**
	 * Weight
	 */
	public int getMass();
	public void setMass(int mass);
	
	public Vector2f getvitesse();
	public void setVitesse(Vector2f vitesse);
	
	public Vector2f getAcceleration();
	public void setAcceleration(Vector2f acceleration);
	
	//public int getDamagedDoneOnImpact();
	//public void setDamagedDoneOnImpact(int damage);
	
	
	public boolean isGravityON();
	public void setGravityON(boolean gravity);
	
	//public boolean isDamagedFixedOrDamageMultiVitesse();
	//public void setDamagedFixedOrDamageMultiVitesse(boolean a);
}
