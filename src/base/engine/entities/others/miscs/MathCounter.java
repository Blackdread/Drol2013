package base.engine.entities.others.miscs;

import java.util.ArrayList;

import base.engine.entities.others.outputs.IDisable;
import base.engine.entities.others.outputs.InputsAndOutputs;

/**
 * math_counter is a point entity available in all Source games. 
 * It stores and manipulates an integer value. 
 * It can trigger on reaching user-defined maximum or minimum values, or output 
 * its value every time it changes. It also has the ability to perform simple mathematical functions.  
 * Note:	Only stores integers.
 * @author Yoann CAPLAIN
 *
 */
public class MathCounter extends InputsAndOutputs implements IDisable{
	
	private boolean enabled;
	
	/**
	 * Starting value for the counter
	 */
	private int initialValue;
	/**
	 * Minimum legal value for the counter. 
	 * If min=0 and max=0, no clamping is performed. 
	 */
	private int minimumLegalValue;
	
	/**
	 * Maximum legal value for the counter. 
	 * If min=0 and max=0, no clamping is performed.
	 */
	private int maximumLegalValue;
	 
	
	public MathCounter(String name) {
		super(name);
	}

	
	public ArrayList<String> get_list_outputs(){
		ArrayList<String> list_outputs = new ArrayList<String>();
		list_outputs.addAll(super.get_list_outputs());
		list_outputs.add("OutValue");
		list_outputs.add("OnHitMin");
		list_outputs.add("OnHitMax");
		list_outputs.add("OnGetValue");
		list_outputs.add("OnChangedFromMin");
		list_outputs.add("OnChangedFromMax");
		
		return list_outputs;
	}
	
	public ArrayList<String> get_list_inputs() {
		ArrayList<String> list_inputs = new ArrayList<String>();
		list_inputs.addAll(super.get_list_inputs());
		list_inputs.add("Add");
		list_inputs.add("Divide");
		list_inputs.add("Multiply");
		list_inputs.add("SetValue");
		list_inputs.add("SetValueNoFire");
		list_inputs.add("Subtract");
		list_inputs.add("SetHitMax");
		list_inputs.add("SetHitMin");
		list_inputs.add("GetValue");
		list_inputs.add("SetMaxValueNoFire");
		list_inputs.add("SetMinValueNoFire");
		
		return list_inputs;
	}

	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.InputsAndOutputs#fireOutputs(java.lang.String)
	 */
	@Override
	public void fireOutputs(String nameOfOutput) {
		if(nameOfOutput != null){
			if(nameOfOutput.equalsIgnoreCase("OutValue"))
				OutValue();
			else if(nameOfOutput.equalsIgnoreCase("OnHitMin"))
				OnHitMin();
			else if(nameOfOutput.equalsIgnoreCase("OnHitMax"))
				OnHitMax();
			else if(nameOfOutput.equalsIgnoreCase("OnGetValue"))
				OnGetValue();
			else if(nameOfOutput.equalsIgnoreCase("OnChangedFromMin"))
				OnChangedFromMin();
			else if(nameOfOutput.equalsIgnoreCase("OnChangedFromMax"))
				OnChangedFromMax();
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
			if(nameOfInput.equalsIgnoreCase("GetValue"))
				getValue();
			else if(nameOfInput.equalsIgnoreCase("Enable"))
				enable();
			else if(nameOfInput.equalsIgnoreCase("Disable"))
				disable();
			else if(nameOfInput.equalsIgnoreCase("Toggle"))
				toggle();
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
			int temp = 0;
			try{
				temp = (Integer)parameter;	// Normalement on ne devrait pas avoir d'erreur !
			}catch(Exception e){e.printStackTrace();}
			
			if(nameOfInput.equalsIgnoreCase("Add"))
				add(temp);
			else if(nameOfInput.equalsIgnoreCase("Divide"))
				divide(temp);
			else if(nameOfInput.equalsIgnoreCase("Multiply"))
				multiply(temp);
			else if(nameOfInput.equalsIgnoreCase("SetValue"))
				setValue(temp);
			else if(nameOfInput.equalsIgnoreCase("SetValueNoFire"))
				setValueNoFire(temp);
			else if(nameOfInput.equalsIgnoreCase("Subtract"))
				subtract(temp);
			else if(nameOfInput.equalsIgnoreCase("SetHitMax"))
				setHitMax(temp);
			else if(nameOfInput.equalsIgnoreCase("SetHitMin"))
				setHitMin(temp);
			else if(nameOfInput.equalsIgnoreCase("SetMaxValueNoFire"))
				setMaxValueNoFire(temp);
			else if(nameOfInput.equalsIgnoreCase("SetMinValueNoFire"))
				setMinValueNoFire(temp);
			else
				super.fireInputs(nameOfInput, parameter);
		}
	}
	
	// Inputs
	/**
	 * Add an amount to the counter and fire the OutValue output with the result. 
	 * @param a
	 */
	public void add(int a){

	}
	
	/**
	 * Divide the counter by an amount and fire the OutValue output with the result. 
	 * @param a
	 */
	public void divide(int a){

	}

	/**
	 * Multiply the counter by an amount and fire the OutValue output with the result. 
	 * @param a
	 */
	public void multiply(int a){

	}

	/**
	 * Set the counter to a new value and fire the OutValue output with the result. 
	 * @param a
	 */
	public void setValue(int a){

	} 

	/**
	 * Set the counter to a new value without firing any outputs. 
	 * @param a
	 */
	public void setValueNoFire(int a){

	}

	/**
	 * Subtract an amount from the counter and fire the OutValue output with the result. 
	 * @param a
	 */
	public void subtract(int a){

	}

	/**
	 * Set the upper bound of the counter and fire the OutValue output with the current value. 
	 * @param a
	 */
	public void setHitMax(int a){

	}

	/**
	 * Set the lower bound of the counter and fire the OutValue output with the current value. 
	 * @param a
	 */
	public void setHitMin(int a){

	}

	/**
	 *  Causes the counter fire its OnGetValue output with the current value of the counter. 
	 *  Used for polling the counter when you don't want constant updates from the OutValue output
	 *  Exemple: send the value to a LogicCompare or LogicBranch etc
	 */
	public void getValue(){

	}

	/**
	 * Set the MaxValue without firing any outputs.
	 * @param a
	 */
	public void setMaxValueNoFire(int a){

	}

	/**
	 * Set the MinValue without firing any outputs.
	 * @param a
	 */
	public void setMinValueNoFire(int a){

	}


	// Outputs
	/**
	 * Fired when the counter value changes
	 */
	private void OutValue(){

	}
	/**
	 * Fired when the counter value meets or goes below the min value. 
	 * The counter must go back above the min value before the output will fire again
	 */
	private void OnHitMin(){

	}
	/**
	 * Fired when the counter value meets or exceeds the max value. 
	 * The counter must go below the max value before the output will fire again
	 */
	private void OnHitMax(){

	}
	/**
	 *  Fired in response to the GetValue input. 
	 *  Used for polling the counter when you don't want constant updates from the OutValue output
	 */
	private void OnGetValue(){

	}
	/**
	 * Fired when the counter value changes from the minimum value
	 */
	private void OnChangedFromMin(){

	}
	/**
	 * Fired when the counter value changes from the max value
	 */
	private void OnChangedFromMax(){

	}

	
	
	
	@Override
	public void toggle(){
		enabled = !enabled;
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
	public void enable(){
		enabled = true;
	}
	@Override
	public void disable(){
		enabled = false;
	}
}