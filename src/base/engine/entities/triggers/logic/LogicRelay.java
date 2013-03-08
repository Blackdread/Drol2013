package base.engine.entities.triggers.logic;

import java.util.ArrayList;

import base.engine.entities.triggers.outputs.IDisable;
import base.engine.entities.triggers.outputs.IFireOnce;
import base.engine.entities.triggers.outputs.Outputs;

/**
 * It is a message forwarder. It can be used to fire many outputs at once from just one input, or, 
 * by being disabled, to break an I/O chain
 * 
 * @author Yoann CAPLAIN
 *
 */
public class LogicRelay extends Logic implements IDisable, IFireOnce{

	private boolean enabled;
	
	/**
	 * true -> once it has been fired if it's a fireOnce this will be deleted from the world
	 */
	private boolean hasbeenFired;
	
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
	
	private boolean startDisabled;
	
	public LogicRelay(String name) {
		super(name);
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
				enabled = true;
			else if(nameOfInput.equalsIgnoreCase("Disable"))
				enabled = false;
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
	
	}
	
	/**
	 *  Toggle the relay between enabled and disabled
	 */
	public void toggle(){
		if(enabled)
			enabled = false;
		else
			enabled = true;
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
		
	}
	
	/*
	 * Outputs
	 */
	/**
	 * Fired when the relay is spawned. If the relay is set to only trigger once, 
	 * it will delete itself after firing this output
	 */
	private void OnSpawn(){
		
	}
	 /**
	  * Fired when the relay is triggered. If the relay is set to only trigger once, 
	  * it will delete itself after firing this output
	  */
	private void OnTrigger (){
		
	}

	@Override
	public void setEnable(boolean enabled) {
		this.enabled = enabled;
	}
	
	/**
	 * @return true it's disabled
	 */
	@Override
	public boolean isDisabled() {
		return !enabled;
	}
	public boolean isStartDisabled() {
		return startDisabled;
	}


	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void setStartDisabled(boolean startDisabled) {
		this.startDisabled = startDisabled;
	}

	@Override
	public boolean isFireOnce() {
		return fireOnce;
	}

	@Override
	public void setFireOnce(boolean fireOnce) {
		this.fireOnce = fireOnce;
	}
	 
}
