package base.engine.entities.others.logics;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

/**
 * 
 * Compares a set of inputs to each other. If they are all the same, fires an OnEqual output. 
 * If any are different, fires the OnNotEqual output. 
 *
 * This entity has an internal list of integers that it has received from any inputValue input's. 
 * Any values sent to this entity, must be sent as an integer or boolean because 
 * "if it can't be converted" the entity will "just throw it away"
 * @author Yoann CAPLAIN
 *
 */
public class LogicMulticompare extends Logic {

	//private int value;
	
	private ArrayList<Integer> arrayValue;
	
	public LogicMulticompare(String name) {
		super(name);
		arrayValue = new ArrayList<Integer>();
	}

	@Override
	public void render(Graphics g, int x, int y) {
		super.render("LogicMulticompare", g, x, y);
	}
	
	public ArrayList<String> get_list_outputs(){
		ArrayList<String> list_outputs = new ArrayList<String>();
		list_outputs.addAll(super.get_list_outputs());
		list_outputs.add("OnEqual");
		list_outputs.add("OnNotEqual");
		
		return list_outputs;
	}
	
	public ArrayList<String> get_list_inputs() {
		ArrayList<String> list_inputs = new ArrayList<String>();
		list_inputs.addAll(super.get_list_inputs());
		list_inputs.add("InputValue");
		list_inputs.add("CompareValues");
		list_inputs.add("RemoveValues");
		
		return list_inputs;
	}

	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.InputsAndOutputs#fireOutputs(java.lang.String)
	 */
	@Override
	public void fireOutputs(String nameOfOutput) {
		if(nameOfOutput != null){
			if(nameOfOutput.equalsIgnoreCase("OnEqual"))
				OnEqual();
			else if(nameOfOutput.equalsIgnoreCase("OnNotEqual"))
				OnNotEqual();
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
			 if(nameOfInput.equalsIgnoreCase("CompareValues"))
				 compareValues();
			 if(nameOfInput.equalsIgnoreCase("RemoveValues"))
				 removeValues();
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
				if(parameter instanceof Integer)
					temp = (Integer)parameter;	// Normalemnt on ne devrait pas avoir d'erreur !
				else if(parameter instanceof Boolean){
					if((Boolean)parameter == true)
						temp = 1;
					else 
						temp = 0;
				}
			}catch(Exception e){e.printStackTrace();}
			
			if(nameOfInput.equalsIgnoreCase("InputValue"))
				inputValue(temp);
			else
				super.fireInputs(nameOfInput, parameter);
				
		}
	}
	
	/*
	 * Inputs
	 */
	public void inputValue(int a){
		arrayValue.add(a);
	}
	
	public void removeValues(){
		arrayValue.clear();
	}
	
	public void compareValues(){
		boolean tmp = true;
		for(int i = 1; i < arrayValue.size(); i++){
			if(arrayValue.get(i) != arrayValue.get(i-1)){
				tmp = false;
				break;
			}
		}
		if(tmp)
			OnEqual();
		else
			OnNotEqual();
	}
	
	/*
	 * Outputs
	 */
	private void OnEqual(){
		fireOutput("OnEqual");
	}
	private void OnNotEqual(){
		fireOutput("OnNotEqual");
	}
}
