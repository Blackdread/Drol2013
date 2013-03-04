package base.engine.entities.triggers.logic;

import java.util.ArrayList;

import base.engine.entities.triggers.outputs.IInputsAndOutputs;
import base.engine.entities.triggers.outputs.Outputs;

public abstract class Logic implements IInputsAndOutputs{

	/**
	 * The targetname that other entities refer to this entity by
	 * Should be unique
	 */
	protected String name;
	
	protected ArrayList<String> list_inputs = new ArrayList<String>();
	protected ArrayList<String> list_outputs = new ArrayList<String>();
	
	protected ArrayList<Outputs> array_outputs = new ArrayList<Outputs>();
	
	public Logic(String name){
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
}
