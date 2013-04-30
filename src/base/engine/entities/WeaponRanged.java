package base.engine.entities;

import java.io.Serializable;

import base.engine.EngineManager;
import base.engine.entities.others.outputs.IUpdatable;
import base.utils.Timer;

/**
 * Pourra devenir abstrait
 * Une arme contient le type de tir qu'elle cree (un seul type)
 * @author Yoann CAPLAIN
 *
 */
public abstract class WeaponRanged implements IUpdatable, Serializable{

	private static final long serialVersionUID = -4134985850675158200L;

	public final static int NO_DELAY = 0;
	
	protected transient EngineManager engineManager;
	
	/**
	 * If 0 there is no delay
	 */
	protected Timer delayBetweenShoot;
	
	public WeaponRanged(EngineManager engineManager){
		this.engineManager = engineManager;
		delayBetweenShoot = new Timer(NO_DELAY);
	}
	
	public WeaponRanged(EngineManager engineManager, final int delay){
		this.engineManager = engineManager;
		delayBetweenShoot = new Timer(delay);
	}
	
	public void copy(WeaponRanged objetACopier){
		delayBetweenShoot = objetACopier.delayBetweenShoot;
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
		}
	}
	
	public boolean isDelayWeapon(){
		if(delayBetweenShoot.getLimit() == NO_DELAY)
			return false;
		return true;
	}
	
}
