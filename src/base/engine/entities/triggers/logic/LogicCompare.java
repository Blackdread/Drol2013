package base.engine.entities.triggers.logic;

import base.engine.entities.triggers.outputs.Outputs;

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
		addInListOutputsOutputsOfTheClass();
		addInListInputsInputsOfTheClass();
	}
	/**
	 * @see Logic
	 * @param Float initial value
	 * @param Float compare value
	 */
	public LogicCompare(String name, float initialValue, float compareValue){
		super(name);
		addInListOutputsOutputsOfTheClass();
		addInListInputsInputsOfTheClass();
	}
	
	@Override
	public void addInListOutputsOutputsOfTheClass(){
		this.list_outputs.add("OnEqualTo");
		this.list_outputs.add("OnGreaterThan");
		this.list_outputs.add("OnLessThan");
		this.list_outputs.add("OnNotEqual");
	}
	
	@Override
	public void addInListInputsInputsOfTheClass() {
		this.list_inputs.add("SetInitialValue");
		this.list_inputs.add("SetInitialValueAndCompare");
		this.list_inputs.add("SetCompareValue");
		this.list_inputs.add("Compare");
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
		}
	}
	
	@Override
	public void fireInputs(final String nameOfInput) {
		if(nameOfInput != null)
			if(nameOfInput.equalsIgnoreCase("Compare"))
				compare();
	}
	
	@Override
	public void fireInputs(final String nameOfInput, Object parameter){
		int i=0;
		for(String v : this.list_inputs){
			if(v!=null)
				if(v.equalsIgnoreCase(nameOfInput))
					break;
			i++;
		}
		switch(i){
		case 0:
			setInitialValue((Float) parameter);
			break;
		case 1:
			setInitialValueAndCompare((Float) parameter);
			compare();
			break;
		case 2:
			setCompareValue((Float) parameter);
			break;
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
