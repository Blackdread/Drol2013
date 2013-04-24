package base.engine.entities;

import base.engine.entities.others.outputs.IUpdatable;
import base.utils.Timer;

/**
 * Pourra devenir abstrait
 * Une arme contient le type de tir qu'elle cree (un seul type)
 * @author Yoann CAPLAIN
 *
 */
public abstract class WeaponRanged implements IUpdatable{

	public final static int NO_DELAY = 0;
	
	/**
	 * If 0 there is no delay
	 */
	protected Timer delayBetweenShoot;
	
	public WeaponRanged(){
		delayBetweenShoot = new Timer(NO_DELAY);
	}
	
	public WeaponRanged(final int delay){
		delayBetweenShoot = new Timer(delay);
	}
	
	/**
	 * Instancie le tir de l'arme puis il faut lui mettre les bonnes variables (constructeur par defaut)
	 * @return un tir de facons a avoir acces directement au variable du tir
	 */
	public abstract Tir shoot();
	
	/**
	 * 
	 * @param xPos position ou le tire est creer
	 * @param yPos position ou le tire est creer
	 */
	public abstract Tir shoot(final int xPos, final int yPos);
	
	@Override
	public void update(final int delta) {
		if(delayBetweenShoot.getEventTime() != NO_DELAY){
			delayBetweenShoot.update(delta);
			System.out.println("updated "+delayBetweenShoot.getDeltaStock());
		}
	}
	
	public boolean isDelayWeapon(){
		if(delayBetweenShoot.getLimit() == NO_DELAY)
			return false;
		return true;
	}
	
}
