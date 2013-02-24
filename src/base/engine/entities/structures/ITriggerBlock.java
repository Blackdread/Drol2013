package base.engine.entities.structures;

import java.util.ArrayList;

import base.engine.Engine;
import base.engine.entities.BasicEntity;

public interface ITriggerBlock {
	
	public boolean isTriggerDeclenched();
	
	public boolean isTriggerON();
	public void setTriggerON(boolean set);
	public boolean isTriggerOnce();
	public void setTriggerOnce(boolean triggerOnce);
	public Engine getEngine();
	public void setEngine(Engine engine);
	
	/**
	 * Si triggerON alors on verifie si le trigger doit se declencher
	 */
	public abstract void checkTrigger();
	
	public ArrayList<BasicEntity> getArrayEntity();
	public void setArrayEntity(ArrayList<BasicEntity> arrayEntity);
	
}
