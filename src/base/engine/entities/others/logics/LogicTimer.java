/**
 * 
 */
package base.engine.entities.others.logics;

import java.util.ArrayList;

import base.engine.entities.others.outputs.IDisable;
import base.engine.entities.others.outputs.IUpdatable;
import base.utils.Timer;

/**
 * It fires an output at regular or random intervals. It can optionally alternate between a "high" and a "low" output
 * @Note Enabling the entity resets its timer.
 * @author Yoann CAPLAIN
 *
 */
public class LogicTimer extends Logic implements IDisable, IUpdatable{

	/**
	 * Makes the entity fire at random intervals
	 * Range of values is restricted by the next two KVs
	 */
	private boolean useRandomTime;
	 
	/**
	 * If "Use Random Time" is set, this is the minimum time between timer fires 
	 * The time will be a random number between this and the "Maximum Random Interval"
	 */
	private int minimumRandomInterval;
	 
	 /**
	  *  If "Use Random Time" is set, this is the maximum time between timer fires
	  *  The time will be a random number between the "Minimum Random Interval" and this
	  */
	private int maximumRandomInterval;
	 
	 /**
	  * If "Use Random Time" isn't set, this is the time between timer fires, in milliseconds
	  */
	private int refireInterval;
	
	private Timer timer;
	 
	/*
	 * 0 = infinite
	 */
	//private int maxRefire;	// A voir si je le mets
	
	private boolean enabled;
	
	/**
	 * false -> last is low
	 * true -> last is high
	 */
	private boolean remindLastOutputForOscillator;
	
	// *******
	// Flags
	// *******
	/**
	 * alternates between OnTimerHigh and OnTimerLow outputs
	 */
	private boolean isOscillator;
	
	public LogicTimer(String name){
		super(name);
		refireInterval = 1000;
		timer = new Timer(1000);
		enabled = true;
		remindLastOutputForOscillator = false;
		minimumRandomInterval = 1000;
		maximumRandomInterval = 3000;
		useRandomTime = false;
	}
	
	@Override
	public void update(final int delta) {
		if(enabled)
			if(timer != null){
				timer.update(delta);
				
				if(timer.isTimeComplete()){
					timer.resetTime();
					OnTimer();
					
					if(useRandomTime){
						//refireInterval = minimumRandomInterval + (int)(Math.random()*maximumRandomInterval);
						timer.setEventTime(minimumRandomInterval + (int)(Math.random()*maximumRandomInterval));
					}
					
					if(isOscillator){
						if(remindLastOutputForOscillator){
							remindLastOutputForOscillator = false;
							OnTimerHigh();
						}else{
							remindLastOutputForOscillator = true;
							OnTimerLow();
						}
					}
				}
			}
	}

	
	public ArrayList<String> get_list_outputs(){
		ArrayList<String> list_outputs = new ArrayList<String>();
		list_outputs.addAll(super.get_list_outputs());
		list_outputs.add("OnTimer");
		list_outputs.add("OnTimerHigh");
		list_outputs.add("OnTimerLow");
		
		return list_outputs;
	}
	
	public ArrayList<String> get_list_inputs() {
		ArrayList<String> list_inputs = new ArrayList<String>();
		list_inputs.addAll(super.get_list_inputs());
		list_inputs.add("RefireTime");
		list_inputs.add("ResetTimer");
		list_inputs.add("FireTimer");
		list_inputs.add("Enable");
		list_inputs.add("Disable");
		list_inputs.add("Toggle");
		list_inputs.add("ToggleRandomTime");
		list_inputs.add("LowerRandomBound");
		list_inputs.add("UpperRandomBound");
		list_inputs.add("AddToTimer");
		list_inputs.add("SubtractFromTimer");
		
		return list_inputs;
	}

	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.InputsAndOutputs#fireOutputs(java.lang.String)
	 */
	@Override
	public void fireOutputs(String nameOfOutput) {
		if(nameOfOutput != null){
			if(nameOfOutput.equalsIgnoreCase("OnTimer"))
				OnTimer();
			else if(nameOfOutput.equalsIgnoreCase("OnTimerHigh"))
				OnTimerHigh();
			else if(nameOfOutput.equalsIgnoreCase("OnTimerLow"))
				OnTimerLow();
			else
				super.fireOutputs(nameOfOutput);
		}
	}

	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.InputsAndOutputs#fireInputs(java.lang.String)
	 */
	@Override
	public void fireInputs(String nameOfInput) {
		if(nameOfInput != null){
			 if(nameOfInput.equalsIgnoreCase("ResetTimer"))
				resetTimer();
			else if(nameOfInput.equalsIgnoreCase("FireTimer"))
				fireTimer();
			else if(nameOfInput.equalsIgnoreCase("Enable"))
				enable();
			else if(nameOfInput.equalsIgnoreCase("Disable"))
				disable();
			else if(nameOfInput.equalsIgnoreCase("Toggle"))
				toggle();
			else if(nameOfInput.equalsIgnoreCase("ToggleRandomTime"))
				toggleRandomTime();
			else
				super.fireInputs(nameOfInput);
		}
		
	}

	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.InputsAndOutputs#fireInputs(java.lang.String, java.lang.Object)
	 */
	@Override
	public void fireInputs(String nameOfInput, Object parameter) {
		if(nameOfInput != null && parameter != null){
			int temp = 1000;
			try{
				temp = (Integer)parameter;	// Normalement on ne devrait pas avoir d'erreur !
			}catch(Exception e){e.printStackTrace();}
			
			if(nameOfInput.equalsIgnoreCase("RefireTime"))
				this.refireTime(temp);
			else if(nameOfInput.equalsIgnoreCase("LowerRandomBound"))
				this.lowerRandomBound(temp);
			else if(nameOfInput.equalsIgnoreCase("UpperRandomBound"))
				this.upperRandomBound(temp);
			else if(nameOfInput.equalsIgnoreCase("AddToTimer"))
				this.addToTimer(temp);
			else if(nameOfInput.equalsIgnoreCase("SubtractFromTimer"))
				this.subtractFromTimer(temp);
			else
				super.fireInputs(nameOfInput, parameter);
		}
	}
	
	/*
	 * Outputs
	 */
	/**
	 * Fired when the timer expires
	 */
	private void OnTimer(){
		fireOutput("OnTimer");
	}
	 
	/**
	 * Fired every other time for an oscillating timer
	 */
	private void OnTimerHigh(){
		fireOutput("OnTimerHigh");
	}
	 
	/**
	 *  Fired every other time for an oscillating time
	 */
	private void OnTimerLow(){
		fireOutput("OnTimerLow");
	}
	
	/*
	 * Inputs
	 */
	/**
	 * Set a new Refire Interval
	 * @param interval in milliseconds
	 */
	public void refireTime(int interval){
		refireInterval = interval;
		if(timer != null)
			timer.setEventTime(interval);
		else
			timer = new Timer(interval);
	}
	/**
	 * Reset the timer. It will fire after the Refire Interval expires
	 */
	public void resetTimer(){
		if(timer != null)
			timer.resetTime();
	}
	/**
	 * Force the timer to fire immediately
	 * Do not check if it's enabled
	 */
	public void fireTimer(){
		OnTimer();
	}
	/**
	 *  Enabling the entity resets its timer
	 */
	@Override
	public void enable(){
		enabled = true;
		resetTimer();
	}
	@Override
	public void disable(){
		enabled = false;
	}
	
	/**
	 * Toggle the timer on/off
	 */
	@Override
	public void toggle(){
		enabled = !enabled;
	}
	/**
	 * Toggle random interval on/off
	 * Reset timer and set event time between min and max
	 */
	public void toggleRandomTime(){
		if(useRandomTime)
			useRandomTime = false;
		else{
			useRandomTime = true;
			//refireInterval = minimumRandomInterval + (int)(Math.random()*maximumRandomInterval);
			timer.setEventTime(minimumRandomInterval + (int)(Math.random()*maximumRandomInterval));
			timer.resetTime();
		}
	}
	
	/**
	 * Set a new Minimum Random Interval
	 * @param minRand
	 */
	public void lowerRandomBound(int minRand){
		minimumRandomInterval = minRand;
	}
	/**
	 * Set a new Maximum Random Interval
	 * @param maxRand
	 */
	public void upperRandomBound(int maxRand){
		maximumRandomInterval = maxRand;
	}
	/**
	 * Add time to the timer if it is currently enabled. Does not change the Refire Interval
	 */
	public void addToTimer(int add){
		if(timer != null)
			timer.update(add);
	}
	/**
	 * Subtract time from the timer if it is currently enabled. Does not change the Refire Interval
	 */
	public void subtractFromTimer(int sub){
		if(timer != null)
			timer.subtractTime(sub);
	}


	public boolean isUseRandomTime() {
		return useRandomTime;
	}


	public int getMinimumRandomInterval() {
		return minimumRandomInterval;
	}


	public int getMaximumRandomInterval() {
		return maximumRandomInterval;
	}


	public int getRefireInterval() {
		return refireInterval;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public boolean isRemindLastOutputForOscillator() {
		return remindLastOutputForOscillator;
	}


	public boolean isOscillator() {
		return isOscillator;
	}

	/**
	 * 
	 * @param useRandomTime
	 */
	public void setUseRandomTime(boolean useRandomTime) {
		this.useRandomTime = useRandomTime;
	}


	public void setMinimumRandomInterval(int minimumRandomInterval) {
		this.minimumRandomInterval = minimumRandomInterval;
	}


	public void setMaximumRandomInterval(int maximumRandomInterval) {
		this.maximumRandomInterval = maximumRandomInterval;
	}

	public void setRemindLastOutputForOscillator(
			boolean remindLastOutputForOscillator) {
		this.remindLastOutputForOscillator = remindLastOutputForOscillator;
	}


	public void setOscillator(boolean isOscillator) {
		this.isOscillator = isOscillator;
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

}
