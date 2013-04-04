package base.engine.entities;

/**
 * Mettre un boolean pour savoir si l'objet depend de la gravite
 * @author Yoann CAPLAIN
 *
 */
public interface IGravity {
	
	//public void updateGravity(final int delta);
	//public void update(final int delta);
	
	//public int getRotation();
	//public void setRotation(int rotation);
	
	/**
	 * Weight
	 */
	public int getMass();
	public void setMass(int mass);
	
	public float getvitesse();
	public void setVitesse(float vitesse);
	
	public float getAcceleration();
	public void setAcceleration(float acceleration);
	
	//public int getDamagedDoneOnImpact();
	//public void setDamagedDoneOnImpact(int damage);
	
	public boolean isGravityON();
	public void setGravityON(boolean gravity);
	
	//public boolean isDamagedFixedOrDamageMultiVitesse();
	//public void setDamagedFixedOrDamageMultiVitesse(boolean a);
}
