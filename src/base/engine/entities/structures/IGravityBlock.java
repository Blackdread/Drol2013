package base.engine.entities.structures;

/**
 * not up to date
 * Mettre un boolean pour savoir si l'objet depend de la gravite
 * @author Yoann CAPLAIN
 *
 */
@Deprecated 
public interface IGravityBlock {
	
	//public void updateGravity();
	
	public int getRotation();
	public void setRotation(int rotation);
	
	public int getvitesse();
	public void setVitesse(int vitesse);
	
	public int getAcceleration();
	public void setAcceleration(int acceleration);
	
	public int getDamagedDoneOnImpact();
	public void setDamagedDoneOnImpact(int damage);
	
	public boolean isGravityON();
	public void setGravityON(boolean gravity);
	
	public boolean isDamagedFixedOrDamageMultiVitesse();
	public void setDamagedFixedOrDamageMultiVitesse(boolean a);
}
