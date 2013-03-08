/**
 * 
 */
package base.engine.entities.triggers.outputs;

import java.util.ArrayList;

/**
 * 
 * @author Yoann CAPLAIN
 */
public abstract class InputsAndOutputs implements IInputsAndOutputs {

	/**
	 * The targetname that other entities refer to this entity by
	 * Should be unique
	 */
	protected String name;
	
	protected ArrayList<Outputs> array_outputs = new ArrayList<Outputs>();
	
	public InputsAndOutputs(String name){
		this.name = name;
	}
	
	public ArrayList<String> get_list_outputs(){
		ArrayList<String> list_outputs = new ArrayList<String>();
		// empty for the moment
		return list_outputs;
	}
	
	public ArrayList<String> get_list_inputs() {
		ArrayList<String> list_inputs = new ArrayList<String>();
		// empty for the moment
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
	public void fireInputs(String nameOfInput){
		
	}

	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.IInputsAndOutputs#fireInputs(java.lang.String, java.lang.Object)
	 */
	@Override
	public void fireInputs(String nameOfInput, Object parameter){
		
	}

	/**
	 * The targetname that other entities refer to this entity by
	 * Should be unique
	 */
	public String getName() {
		return name;
	}

	/**
	 * The targetname that other entities refer to this entity by
	 * Should be unique
	 */
	public void setName(String name) {
		this.name = name;
	}

}
