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
	
	protected ArrayList<String> list_inputs = new ArrayList<String>();
	protected ArrayList<String> list_outputs = new ArrayList<String>();
	
	protected ArrayList<Outputs> array_outputs = new ArrayList<Outputs>();
	
	public InputsAndOutputs(String name){
		this.name = name;
	}
	
	public ArrayList<String> get_list_outputs(){
		return list_outputs;
	}
	
	public ArrayList<String> get_list_inputs() {
		return list_inputs;
	}
	
	public ArrayList<Outputs> get_array_outputs() {
		return array_outputs;
	}
	
	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.IInputsAndOutputs#addInListInputsInputsOfTheClass()
	 */
	@Override
	public abstract void addInListInputsInputsOfTheClass();
	
	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.IInputsAndOutputs#addInListOutputsOutputsOfTheClass()
	 */
	@Override
	public abstract void addInListOutputsOutputsOfTheClass();

	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.IInputsAndOutputs#fireOutputs(java.lang.String)
	 */
	@Override
	public abstract void fireOutputs(String nameOfOutput);

	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.IInputsAndOutputs#fireInputs(java.lang.String)
	 */
	@Override
	public abstract void fireInputs(String nameOfInput);

	/* (non-Javadoc)
	 * @see base.engine.entities.triggers.outputs.IInputsAndOutputs#fireInputs(java.lang.String, java.lang.Object)
	 */
	@Override
	public abstract void fireInputs(String nameOfInput, Object parameter);

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
