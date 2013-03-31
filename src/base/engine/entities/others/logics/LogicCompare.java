package base.engine.entities.others.logics;

import java.util.ArrayList;

import base.engine.entities.others.outputs.Outputs;

/**
 * It examines the relationship between two numbers ("value" and "compare value"), and fires appropriate output(s).
 * The equation is (value - compare value).
 * Integer would be more appropriate but float works
 * @author Yoann CAPLAIN
 *
 */
public class LogicCompare extends Logic {

	/**
	 * intial value
	 */
	protected float initialValue;
	
	/**
	 * Value that will be compared to initialValue
	 */
	protected float compareValue;
	
	/**
	 * @see Logic
	 * @param Float initial value
	 */
	public LogicCompare(String name, float initialValue){
		super(name);
	}
	/**
	 * @see Logic
	 * @param Float initial value
	 * @param Float compare value
	 */
	public LogicCompare(String name, float initialValue, float compareValue){
		super(name);
	}
	
	public ArrayList<String> get_list_outputs(){
		ArrayList<String> list_outputs = new ArrayList<String>();
		list_outputs.addAll(super.get_list_outputs());
		list_outputs.add("OnEqualTo");
		list_outputs.add("OnGreaterThan");
		list_outputs.add("OnLessThan");
		list_outputs.add("OnNotEqual");
		
		return list_outputs;
	}
	
	public ArrayList<String> get_list_inputs() {
		ArrayList<String> list_inputs = new ArrayList<String>();
		list_inputs.addAll(super.get_list_inputs());
		list_inputs.add("SetInitialValue");
		list_inputs.add("SetInitialValueAndCompare");
		list_inputs.add("SetCompareValue");
		list_inputs.add("Compare");
		// Pourrai ajouter un input qui va chercher une valeur puis compare (chercher dans un math_counter par exemple)
		
		return list_inputs;
	}
	
	@Override
	public void fireOutputs(final String nameOfOutput) {
		if(nameOfOutput != null){
			if(nameOfOutput.equalsIgnoreCase("OnEqualTo"))
				OnEqualTo();
			else if(nameOfOutput.equalsIgnoreCase("OnGreaterThan"))
				OnGreaterThan();
			else if(nameOfOutput.equalsIgnoreCase("OnLessThan"))
				OnLessThan();
			else if(nameOfOutput.equalsIgnoreCase("OnNotEqual"))
				OnNotEqual();
			else
				super.fireOutputs(nameOfOutput);
		}
	}
	
	@Override
	public void fireInputs(final String nameOfInput) {
		if(nameOfInput != null){
			if(nameOfInput.equalsIgnoreCase("Compare"))
				compare();
			else
				super.fireInputs(nameOfInput);
		}
	}
	
	@Override
	public void fireInputs(final String nameOfInput, Object parameter){
		if(nameOfInput != null && parameter != null){
			float temp = 1.0f;
			try{
				temp = (Float)parameter;	// Normalement on ne devrait pas avoir d'erreur !
			}catch(Exception e){e.printStackTrace();}
			
			if(nameOfInput.equalsIgnoreCase("SetInitialValue"))
					setInitialValue(temp);
			else if(nameOfInput.equalsIgnoreCase("SetInitialValueAndCompare")){
					setInitialValueAndCompare(temp);
					//compare();	erreur ?
			}else if(nameOfInput.equalsIgnoreCase("SetCompareValue"))
					setCompareValue(temp);
			else
				super.fireInputs(nameOfInput, parameter);
		}
	}
	
	/*
	 * Outputs
	 */
	private void OnEqualTo(){
		for(Outputs v : this.array_outputs)
			if(v != null)
				if(v.getNameOfTheOutput().equalsIgnoreCase("OnEqualTo"))
					v.fireOutput();
	}
	/**
	 * Fired when the input value is greater than the compare value
	 */
	private void OnGreaterThan(){
		for(Outputs v : this.array_outputs)
			if(v != null)
				if(v.getNameOfTheOutput().equalsIgnoreCase("OnGreaterThan"))
					v.fireOutput();
	}
	/**
	 * Fired when the input value is less than the compare value
	 */
	private void OnLessThan(){
		for(Outputs v : this.array_outputs)
			if(v != null)
				if(v.getNameOfTheOutput().equalsIgnoreCase("OnLessThan"))
					v.fireOutput();
	}
	/**
	 * Fired when the input value is different from the compare value
	 */
	private void OnNotEqual(){
		for(Outputs v : this.array_outputs)
			if(v != null)
				if(v.getNameOfTheOutput().equalsIgnoreCase("OnNotEqual"))
					v.fireOutput();
	}
	
	/*
	 * Inputs
	 */
	/**
	 * Set the value that will be compared against the compare value
	 * @param initialValue
	 */
	public void setInitialValue(float initialValue) {
		this.initialValue = initialValue;
	}
	/**
	 * Set the value that will be compared against the compare value and performs the comparison
	 * @param initialValue
	 */
	public void setInitialValueAndCompare(float initialValue){
		this.initialValue = initialValue;
		compare();
	}
	/**
	 * Set the compare value
	 * @param compareValue
	 */
	public void setCompareValue(float compareValue) {
		this.compareValue = compareValue;
	}
	/**
	 * Force a compare of the input value with the compare value
	 * equation is (value - compare value)
	 */
	public void compare(){
		float result = this.initialValue - this.compareValue;
		if(result == 0)
			OnEqualTo();
		if(result > 0)
			OnGreaterThan();
		else
			OnLessThan();
		if(result != 0)
			OnNotEqual();
	}
	
		
	public float getInitialValue() {
		return this.initialValue;
	}
	public float getCompareValue() {
		return this.compareValue;
	}

}
