package base.engine.entities.structures;

import base.engine.Engine;

public interface ITriggerBlock {
	
	public boolean isTrigger();
	public void setTrigger(boolean set);
	public Engine getEngine();
	public void setEngine(Engine engine);
	
	public void checkTrigger();
	
}
