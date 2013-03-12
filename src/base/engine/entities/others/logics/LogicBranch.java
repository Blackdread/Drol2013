package base.engine.entities.others.logics;

import java.util.ArrayList;

/**
 * It tests a boolean value and fires an output based on whether the value is true (one) or false (zero). 
 * Use this entity to branch between two potential sets of events.  
 * Note: Values greater than 1 are treated as 0.
 * 
 * @author Yoann CAPLAIN
 *
 */
public class LogicBranch extends Logic {

	private boolean value;
	
	public LogicBranch(String name) {
		super(name);
	}
	
	public ArrayList<String> get_list_outputs(){
		ArrayList<String> list_outputs = new ArrayList<String>();
		list_outputs.addAll(super.get_list_outputs());
		list_outputs.add("OnTrue");
		list_outputs.add("OnFalse");
		
		return list_outputs;
	}
	
	public ArrayList<String> get_list_inputs() {
		ArrayList<String> list_inputs = new ArrayList<String>();
		list_inputs.addAll(super.get_list_inputs());
		list_inputs.add("SetValue");
		list_inputs.add("SetValueTest");
		list_inputs.add("Toggle");
		list_inputs.add("ToggleTest");
		list_inputs.add("Test");
		
		return list_inputs;
	}

	@Override
	public void fireOutputs(String nameOfOutput) {
		if(nameOfOutput != null){
			if(nameOfOutput.equalsIgnoreCase("OnTrue"))
				//OnTrue();
				fireOutput("OnTrue");
			else if(nameOfOutput.equalsIgnoreCase("OnFalse"))
				//OnFalse();
				fireOutput("OnFalse");
			else
				super.fireOutputs(nameOfOutput);
		}
	}

	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.InputsAndOutputs#fireInputs(java.lang.String)
	 */
	@Override
	public void fireInputs(final String nameOfInput) {
		if(nameOfInput != null){
			if(nameOfInput.equalsIgnoreCase("Toggle"))
				toggle();
			else if(nameOfInput.equalsIgnoreCase("ToggleTest"))
				toggleTest();
			else if(nameOfInput.equalsIgnoreCase("Test"))
				toggleTest();
			else
				super.fireInputs(nameOfInput);
		}
		
	}

	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.InputsAndOutputs#fireInputs(java.lang.String, java.lang.Object)
	 */
	@Override
	public void fireInputs(final String nameOfInput, Object parameter) {
		if(nameOfInput != null && parameter != null){
			int temp = 0;
			try{
				temp = (Integer)parameter;	// Normalemnt on ne devrait pas avoir d'erreur !
			}catch(Exception e){e.printStackTrace();}
			
			if(nameOfInput.equalsIgnoreCase("SetValue"))
				setValue(temp);
			else if(nameOfInput.equalsIgnoreCase("SetValueTest"))
				setValueTest(temp);
			else
				super.fireInputs(nameOfInput, parameter);
				
		}
	}
	
	/*
	 * Inputs
	 */
	/**
	 * Set the boolean value without performing the comparison. 
	 * Use this to hold a value for a future test
	 */
	public void setValue(int val){
		if(val == 1)
			value = true;
		else
			value = false;
	}
	
	/**
	 * Set the boolean value and test it, firing OnTrue or OnFalse based on the new value
	 */
	public void setValueTest(int val){
		if(val == 1)
			value = true;
		else
			value = false;
		 test();
	}
	 
	/**
	 * Toggle the boolean value between true and false.
	 */
	public void toggle(){
		 if(value == true)
			 value = false;
		 else
			 value = true;
	}
	 
	/**
	 * Toggle the boolean value and tests it, firing OnTrue or OnFalse based on the new value
	 */
	public void toggleTest(){
		toggle();
		test();
	}
	/**
	 *  Test the input value and fire OnTrue or OnFalse based on the value
	 */
	public void test(){
		if(value == true)
			fireOutputs("OnTrue");
		 else
			 fireOutputs("OnFalse");
	}
	
	
	/*
	 * Outputs
	 */
	/*
	private void OnTrue(){
		fireOutput("OnTrue");
	}
	private void OnFalse(){
		fireOutput("OnFalse");
	}
	//*/
}
