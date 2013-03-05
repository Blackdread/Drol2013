package base.engine.entities.triggers;

import java.util.ArrayList;

import base.engine.Engine;
import base.engine.entities.BasicEntity;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public interface ITrigger {
	
	public boolean isTriggerDeclenched();
	
	public boolean isTriggerON();
	public void setTriggerON(boolean set);
	public boolean isTriggerOnce();
	public void setTriggerOnce(boolean triggerOnce);
	
	/**
	 * Si triggerON alors on verifie si le trigger doit se declencher
	 */
	public abstract void checkTrigger();
	
	public ArrayList<BasicEntity> getArrayEntity();
	public void setArrayEntity(ArrayList<BasicEntity> arrayEntity);
	
}
