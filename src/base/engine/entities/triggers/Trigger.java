package base.engine.entities.triggers;

import base.engine.Engine;
import base.engine.entities.structures.ITriggerBlock;

public abstract class Trigger implements ITriggerBlock {

	protected boolean triggerON;
	protected Engine engine;
	
	

	@Override
	public boolean isTrigger() {
		// TODO Auto-generated method stub
		return triggerON;
	}
	@Override
	public void setTrigger(boolean set) {
		triggerON = set;
	}
	public Engine getEngine() {
		return engine;
	}
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
}
