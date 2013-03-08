package base.engine.entities.triggers.outputs;

import java.util.ArrayList;

/**
 * Toute classe qui peut recevoir des input et envoyer des output doit implementer cette classe.
 * 
 * @author Yoann CAPLAIN
 *
 */
public interface IInputsAndOutputs {
	/*
	 * J'implementerai ça plus tard
	 * 	Kill 
 			Removes this entity from the world. 
		KillHierarchy 
 			Removes this entity and all its children from the world.
	 * 
	 * 
	 * 
	 */
	
	/**
	 * Meant to be used with hierarchy (super.get_list_inputs())
	 * @return ArrayList<String> that contains all inputs available for that entity
	 */
	public ArrayList<String> get_list_inputs();
	
	/**
	 * Meant to be used with hierarchy (super.get_list_outputs())
	 * @return ArrayList<String> that contains all outputs available for that entity
	 */
	public ArrayList<String> get_list_outputs();
	
	/**
	 * 
	 * @important THIS MUST be an arrayList because HashMap/HashTable "does not guarantee that the order will remain constant over time"
	 * @return ArrayList<Outputs> List of Outputs to fire or not depending on Inputs etc
	 */
	public ArrayList<Outputs> get_array_outputs();
	
	/**
	 * Contains a switch to fire different outputs
	 * Outputs are private (or are directly in fireOutputs if those outputs don't modify the object when fired)
	 * Parameter is in Outputs Class
	 * Meant to be used with hierarchy (super.fireOutputs(String))
	 * @important switch depends on ArrayList that has been ordered
	 * @param nameOfOutput represents the function to activate
	 */
	public void fireOutputs(final String nameOfOutput);
	
	/**
	 * Search in the arrayList of outputs and fire if(element_of_array_outputs.equalsIgnoreCase(nameOfOutput))
	 * @param nameOfOutput represents the name of the output to activate
	 */
	public void fireOutput(final String nameOfOutput);
	
	/**
	 * Contains a switch to fire different intputs
	 * Inputs are 
	 * Meant to be used with hierarchy (super.fireInputs(String))
	 * @param nameOfInput represents the function to activate
	 */
	public void fireInputs(final String nameOfInput);
	
	/**
	 * Contains a switch to fire different intputs
	 * Inputs are 
	 * Meant to be used with hierarchy (super.fireInputs(String, Object))
	 * @param nameOfInput represents the function to activate
	 * @param parameter represents the parameter of the function
	 */
	public void fireInputs(final String nameOfInput, Object parameter);
	
	/**
	 * The targetname that other entities refer to this entity by
	 * Should be unique
	 */
	public String getName();
}
