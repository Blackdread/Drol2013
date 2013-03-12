/**
 * 
 */
package base.engine.entities.others.outputs;

import java.util.ArrayList;

/**
 * 
 * @author Yoann CAPLAIN
 */
public abstract class InputsAndOutputs implements IInputsAndOutputs, ITargetName {

	/**
	 * The targetname that other entities refer to this entity by
	 * Should be unique
	 * Everywhere the name will be supposed not unique
	 */
	protected String name;
	
	protected ArrayList<Outputs> array_outputs = new ArrayList<Outputs>();
	
	public InputsAndOutputs(String name){
		this.name = name;
	}
	
	public ArrayList<String> get_list_outputs(){
		ArrayList<String> list_outputs = new ArrayList<String>();
		list_outputs.add("OnUser1");
		list_outputs.add("OnUser2");
		list_outputs.add("OnUser3");
		list_outputs.add("OnUser4");
		
		return list_outputs;
	}
	
	public ArrayList<String> get_list_inputs() {
		ArrayList<String> list_inputs = new ArrayList<String>();
		list_inputs.add("FireUser1");
		list_inputs.add("FireUser2");
		list_inputs.add("FireUser3");
		list_inputs.add("FireUser4");
		
		return list_inputs;
	}
	
	public ArrayList<Outputs> get_array_outputs() {
		return array_outputs;
	}

	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.IInputsAndOutputs#fireOutputs(java.lang.String)
	 */
	@Override
	public void fireOutputs(String nameOfOutput){
		if(nameOfOutput.equalsIgnoreCase("OnUser1"))
			fireOutput("OnUser1");
		else if(nameOfOutput.equalsIgnoreCase("OnUser2"))
			fireOutput("OnUser2");
		else if(nameOfOutput.equalsIgnoreCase("OnUser3"))
			fireOutput("OnUser3");
		else if(nameOfOutput.equalsIgnoreCase("OnUser4"))
			fireOutput("OnUser4");
	}
	
	@Override
	public void fireOutput(final String nameOfOutput){
		for(Outputs v : this.array_outputs)
			if(v != null)
				if(v.getNameOfTheOutput().equalsIgnoreCase(""+nameOfOutput))
					v.fireOutput();
	}

	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.IInputsAndOutputs#fireInputs(java.lang.String)
	 */
	@Override
	public void fireInputs(final String nameOfInput){
		if(nameOfInput.equalsIgnoreCase("FireUser1"))
			fireOutput("OnUser1");
		else if(nameOfInput.equalsIgnoreCase("FireUser2"))
			fireOutput("OnUser2");
		else if(nameOfInput.equalsIgnoreCase("FireUser3"))
			fireOutput("OnUser3");
		else if(nameOfInput.equalsIgnoreCase("FireUser4"))
			fireOutput("OnUser4");
		
	}

	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.IInputsAndOutputs#fireInputs(java.lang.String, java.lang.Object)
	 */
	@Override
	public void fireInputs(final String nameOfInput, Object parameter){
		
	}
	
	@Override
	public String getTargetName() {
		return name;
	}
	@Override
	public void setTargetName(String name) {
		this.name = name;
	}

}
