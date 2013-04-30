package base.engine.entities.others.triggers;

import base.engine.EngineManager;
import base.engine.entities.others.outputs.InputsAndOutputs;

/**
 * It is a trigger volume damages entities that touch it
 * @author Yoann CAPLAIN
 *
 */
public class TriggerHurt extends TriggerObjectInZone {

	private static final long serialVersionUID = 5164306649515055551L;
	/**
	 * The amount of damage done to entities that touch this trigger
	 * The damage is done every half-second
	 * Use negative value to heal
	 */
	private int damage;
	
	public TriggerHurt(EngineManager e, String name, int xx, int yy, int w, int h) {
		super(e,name, xx, yy, w, h);
	}
	
	@Override
	public void copy(InputsAndOutputs objetACopier){
		super.copy(objetACopier);
		damage = ((TriggerHurt)objetACopier).damage;
	}
	
	
	@Override
	public void update(int delta) {
		super.update(delta);
		
	}
	
	
	// Inputs
	/**
	 * Set a new amount of damage for this trigger
	 * @param dmg damage
	 */
	public void SetDamage(int dmg){
		damage = dmg;
	}
	
	// Outputs
	/**
	 * Fired whenever this trigger hurts something other than a player
	 */
	public void OnHurt(){
		
	}
	/**
	 * Fired whenever this trigger hurts a player
	 */
	public void OnHurtPlayer(){
		
	}

}
