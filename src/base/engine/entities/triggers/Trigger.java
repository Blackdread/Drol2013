package base.engine.entities.triggers;

import java.util.ArrayList;

import base.engine.Engine;
import base.engine.entities.BasicEntity;
import base.engine.entities.structures.ITriggerBlock;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public abstract class Trigger implements ITriggerBlock {

	/**
	 * Vraie tant que le trigger est dans un etat triggerON et ses conditions validees.
	 */
	protected boolean isTriggerDeclenched;
	/**
	 * Si le trigger est "operationelle", -> agit ou pas
	 */
	protected boolean triggerON;
	protected Engine engine;
	/**
	 * Les triggers se declenche par rapport aux entites (une ou plusieurs)
	 */
	protected ArrayList<BasicEntity> arrayEntity = new ArrayList<BasicEntity>();
	
	/**
	 * Trigger qui se declenche une fois puis plus jamais sinon plusieurs fois
	 */
	protected boolean triggerOnce;
	
	
	/**
	 * @return boolean Si le trigger est "operationelle"
	 */
	public boolean isTriggerON() {
		// TODO Auto-generated method stub
		return triggerON;
	}
	public void setTriggerON(boolean set) {
		triggerON = set;
	}
	public boolean isTriggerDeclenched() {
		return isTriggerDeclenched;
	}
	public void setTriggerDeclenched(boolean a) {
		isTriggerDeclenched = a;
	}
	public boolean isTriggerOnce() {
		return triggerOnce;
	}
	public void setTriggerOnce(boolean triggerOnce) {
		this.triggerOnce = triggerOnce;
	}
	public Engine getEngine() {
		return engine;
	}
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	public ArrayList<BasicEntity> getArrayEntity(){
		return arrayEntity;
	}
	public void setArrayEntity(ArrayList<BasicEntity> arrayEntity) {
		this.arrayEntity = arrayEntity;
	}
}
