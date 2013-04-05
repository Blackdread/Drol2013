package base.engine.entities.others.logics;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import base.engine.entities.others.outputs.IDisable;
import base.engine.entities.others.outputs.IFireOnce;
import base.engine.entities.others.outputs.IUpdatable;
import base.engine.entities.others.outputs.Outputs;
import base.utils.ResourceManager;
import base.utils.Timer;

/**
 * It is a message forwarder. It can be used to fire many outputs at once from just one input, or, 
 * by being disabled, to break an I/O chain
 * 
 * @author Yoann CAPLAIN
 *
 */
public class LogicRelay extends Logic implements IDisable, IFireOnce, IUpdatable{

	private static final int MINIMUM_DELAY = 2;
	private static final int DELAY_BEFORE_FIRE_ON_SPAWN = 1000;
	
	private boolean enabled;
	
	/**
	 * true -> once it has been fired if it's a fireOnce this will be deleted from the world
	 * Will always be false if it is not a fireOnce
	 */
	private boolean hasbeenFired;
	
	private Timer timerFastRetrigger;
	
	/**
	 * Will be true once it has fired
	 * We don't want to trigger it immediately on spawn because some entities may not be created yet
	 * Delay is DELAY_BEFORE_FIRE_ON_SPAWN milliseconds
	 */
	private boolean hasBeenFiredOnSpawn;
	
	/*
	 * Flags
	 */
	/**
	 * Only trigger once
	 */
	private boolean fireOnce;
	/**
	 * Allow fast retrigger
	 */
	private boolean allowFastRetrigger;
	
	public LogicRelay(String name) {
		super(name);
		allowFastRetrigger = false;
		fireOnce = false;
		enabled = true;
		timerFastRetrigger = new Timer(MINIMUM_DELAY);
		hasbeenFired = false;
		hasBeenFiredOnSpawn = false;
	}


	@Override
	public void update(int delta) {
		if(enabled)
			if(!hasBeenFiredOnSpawn){
				timerFastRetrigger.addTime(delta);
				if(timerFastRetrigger.getDeltaStock() >= DELAY_BEFORE_FIRE_ON_SPAWN){
					for(Outputs v : get_array_outputs())
						if(v.getNameOfTheOutput().equalsIgnoreCase("OnSpawn")){	// eviter qu'il fire pour rien et perde son FireOnce, il pourrait y avoir des OnTrigger
							OnSpawn();
							break;
						}
					hasBeenFiredOnSpawn = true;
					timerFastRetrigger.setEventTime(MINIMUM_DELAY);
					timerFastRetrigger.resetTime();
				}
			}else{
				timerFastRetrigger.update(delta);
			}
	}
	
	@Override
	public void render(Graphics g, int x, int y) {
		Image tmp = ResourceManager.getImage("logicRelay");
		if(tmp != null)
			g.drawImage(tmp, x, y);
	}
	
	public ArrayList<String> get_list_outputs(){
		ArrayList<String> list_outputs = new ArrayList<String>();
		list_outputs.addAll(super.get_list_outputs());
		list_outputs.add("OnSpawn");
		list_outputs.add("OnTrigger");
		
		return list_outputs;
	}
	
	public ArrayList<String> get_list_inputs() {
		ArrayList<String> list_inputs = new ArrayList<String>();
		list_inputs.addAll(super.get_list_inputs());
		list_inputs.add("Trigger");
		list_inputs.add("Enable");
		list_inputs.add("Disable");
		list_inputs.add("Toggle");
		list_inputs.add("CancelPending");
		list_inputs.add("cancelAllPending");
		list_inputs.add("EnableRefire");
		
		return list_inputs;
	}
	
	@Override
	public void fireOutputs(String nameOfOutput) {
		if(nameOfOutput != null){
			if(nameOfOutput.equalsIgnoreCase("OnSpawn"))
				OnSpawn();
			else if(nameOfOutput.equalsIgnoreCase("OnTrigger"))
				OnTrigger();
			else
				super.fireOutputs(nameOfOutput);
		}
	}

	@Override
	public void fireInputs(String nameOfInput) {
		if(nameOfInput != null){
			 if(nameOfInput.equalsIgnoreCase("Trigger"))
				 trigger();
			 else if(nameOfInput.equalsIgnoreCase("Enable"))
				enable();
			else if(nameOfInput.equalsIgnoreCase("Disable"))
				disable();
			else if(nameOfInput.equalsIgnoreCase("Toggle"))
				toggle();
			else if(nameOfInput.equalsIgnoreCase("CancelPending"))
				cancelPending();
			else if(nameOfInput.equalsIgnoreCase("cancelAllPending"))
				cancelAllPending();
			else if(nameOfInput.equalsIgnoreCase("EnableRefire"))
				enableRefire();
			else
				super.fireInputs(nameOfInput);
		}
		
	}

	@Override
	public void fireInputs(String nameOfInput, Object parameter) {
		if(nameOfInput != null && parameter != null){
			
			super.fireInputs(nameOfInput, parameter);
		}
	}
	
	
	/*
	 * Inputs
	 */
	/**
	 *  Trigger the relay, causing its OnTrigger output to fire if it is enabled
	 */
	public void trigger(){
		OnTrigger();
	}
	
	/**
	 *  Toggle the relay between enabled and disabled
	 */
	@Override
	public void toggle(){
		enabled = !enabled;
	}
	
	/**
	 *  Cancel any events fired by this relay that are currently pending in the I/O event queue
	 *  Do not cancel input pending on him
	 */
	public void cancelPending(){
		if(array_outputs != null)
			for(Outputs v : array_outputs){
				if(v != null)
					v.cancelOutput();
			}
	}
	/**
	 *  Cancel any events fired by this relay that are currently pending in the I/O event queue
	 *  Cancel inputs pending on him (use the targetName so it should be UNIQUE)
	 */
	public void cancelAllPending(){
		cancelPending();
		/*
		 * A faire pour les inputs
		 */
	}
	
	/**
	 *  Allows a slow trigger to fire again. (Used as an internal callback)
	 */
	public void enableRefire(){
		allowFastRetrigger = true;
	}
	
	/*
	 * Outputs
	 */
	/**
	 * Fired when the relay is spawned. If the relay is set to only trigger once, 
	 * it will delete itself after firing this output
	 * Supposed to be called only once !
	 */
	private void OnSpawn(){
		if(fireOnce && !hasbeenFired && enabled){
			hasbeenFired = true;
			//fireOutput("OnSpawn");
		}
		if(enabled)
			fireOutput("OnSpawn");
		
	}
	 /**
	  * Fired when the relay is triggered. If the relay is set to only trigger once, 
	  * it will delete itself after firing this output
	  */
	private void OnTrigger(){
		if(enabled && !hasbeenFired){
			if(fireOnce)
				hasbeenFired = true;
			if(allowFastRetrigger)
				fireOutput("OnTrigger");
			else{
				if(timerFastRetrigger.isTimeComplete()){
					fireOutput("OnTrigger");
					timerFastRetrigger.resetTime();
				}
			}
		}
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	/**
	 * @return true it's disabled
	 */
	@Override
	public boolean isDisabled() {
		return !enabled;
	}
	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public boolean isFireOnce() {
		return fireOnce;
	}
	@Override
	public void setFireOnce(boolean fireOnce) {
		this.fireOnce = fireOnce;
	}
	@Override
	public boolean isHasbeenFired() {
		return hasbeenFired;
	}
	@Override
	public void setHasbeenFired(boolean hasBeenFired) {
		this.hasbeenFired = hasBeenFired;
	}
	public boolean isAllowFastRetrigger() {
		return allowFastRetrigger;
	}

	public void setAllowFastRetrigger(boolean allowFastRetrigger) {
		this.allowFastRetrigger = allowFastRetrigger;
	}

	@Override
	public void enable() {
		enabled = true;
	}

	@Override
	public void disable() {
		enabled = false;
	}
	

	 
}
