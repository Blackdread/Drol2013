package base.engine.entities;

import base.utils.Vector2f;

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
	public void setVitesseToZero();
	
	public float getVitesseMax();
	public void setVitesseMax(float vitesse);
	
	/**
	 * C'est la vitesse de base pour les deplacement de l'entite (Il n'y a pas de phase d'acceleration)
	 * @return
	 */
	public float getDefaultVitesse();
	public void setDefaultVitesse(float vitesse);
	
	public Vector2f getAcceleration();
	public void setAcceleration(Vector2f acceleration);
	public void setAccelerationToZero();
	
	/**
	 * C'est l'acceleration de l'entite pour passer de la vitesse 0 a une vitesse de mouvement
	 * @return
	 */
	public float getAccelerationEntity();
	/**
	 * C'est l'acceleration de l'entite pour passer de la vitesse 0 a une vitesse de mouvement
	 */
	public void setAccelerationEntity(float acceleration);
	
	
	
	//public int getDamagedDoneOnImpact();
	//public void setDamagedDoneOnImpact(int damage);
	
	
	public boolean isGravityON();
	public void setGravityON(boolean gravity);
	
	/**
	 * C'est juste un static (une constante), normalement 9.81f
	 * @return
	 */
	public float getGravity();
	
	//public boolean isDamagedFixedOrDamageMultiVitesse();
	//public void setDamagedFixedOrDamageMultiVitesse(boolean a);
}
