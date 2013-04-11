package base.engine.entities;

import base.engine.EngineManager;
import base.engine.Message;
import base.engine.MessageKey;
import base.engine.entities.others.outputs.IUpdatable;
import base.utils.Timer;

/**
 * Pourra devenir abstrait
 * Une arme contient le type de tir qu'elle cree (un seul type)
 * @author Yoann CAPLAIN
 *
 */
public class Weapon implements IUpdatable{

	private String bullet;
	/**
	 * If 0 there is no delay
	 */
	private Timer delayBetweenShoot;
	
	public Weapon(){
		bullet = "";
		delayBetweenShoot = new Timer(0);
	}
	
	public Weapon(String typeTir, final int delay){
		bullet = typeTir;
		delayBetweenShoot = new Timer(delay);
	}
	
	/**
	 * Instancie le tir de l'arme puis il faut lui mettre les bonnes variables (constructeur par defaut)
	 * @return un tir de facons a avoir acces directement au variable du tir
	 */
	public Tir shoot(){
		Class<Tir> tmp = null;
		try {
			tmp = (Class<Tir>) Class.forName(""+bullet);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return tmp;
	}
	
	/**
	 * 
	 * @param xPos position ou le tire est creer
	 * @param yPos position ou le tire est creer
	 */
	public void shoot(final int xPos, final int yPos){
		if(delayBetweenShoot.isTimeComplete()){
			
			Message m = new Message();
			m.instruction = MessageKey.I_SHOOT;
			m.s_data.put(MessageKey.P_NAME, ""+bullet);
			m.i_data.put(MessageKey.P_ID, id);
			m.i_data.put(MessageKey.P_X, xPos);
			m.i_data.put(MessageKey.P_Y, yPos);
			m.engine = EngineManager.LOGIC_ENGINE;
			
			EngineManager.getInstance().receiveMessage(m);
			
			delayBetweenShoot.resetTime();
		}
	}
	
	@Override
	public void update(final int delta) {
		delayBetweenShoot.update(delta);
		
	}
	
	public boolean isDelayWeapon(){
		if(delayBetweenShoot == null || delayBetweenShoot.getLimit() == 0)
			return true;
		return false;
	}
	
}
