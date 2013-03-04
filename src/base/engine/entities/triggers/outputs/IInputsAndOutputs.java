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
	 * Un arrayList est surement mieux (qu'un hashTable) puisque que l'on se refere juste au String
	 * Moins maintenable si changement mais il n'est cense y avoir de gros changement (peut-etre juste un ajout d'input ou d'output)
	 * @return
	 */
	public ArrayList<String> get_list_inputs();
	
	/**
	 * Not supposed to be called from outside of the class.
	 * It's meant to be called at the instantiation of the class
	 * @important This needs to be ordered !
	 */
	public void addInListInputsInputsOfTheClass();
	
	public ArrayList<String> get_list_outputs();
	
	/**
	 * Not supposed to be called from outside of the class.
	 * It's meant to be called at the instantiation of the class
	 * @important This needs to be ordered !
	 */
	public void addInListOutputsOutputsOfTheClass();
	
	/**
	 * 
	 * @important THIS MUST be an arrayList because HashMap/HashTable "does not guarantee that the order will remain constant over time"
	 * @return ArrayList<Outputs> List of Outputs to fire or not depending on Inputs etc
	 */
	public ArrayList<Outputs> get_array_outputs();
	
	/**
	 * Contains a switch to fire different outputs
	 * Outputs are private
	 * Parameter is in Outputs Class
	 * @important switch depends on ArrayList that has been ordered
	 * @param nameOfOutput represents the function to activate
	 */
	public void fireOutputs(final String nameOfOutput);
	
	/**
	 * Contains a switch to fire different intputs
	 * Inputs are 
	 * @param nameOfInput represents the function to activate
	 */
	public void fireInputs(final String nameOfInput);
	
	/**
	 * Contains a switch to fire different intputs
	 * Inputs are 
	 * @param nameOfInput represents the function to activate
	 * @param parameter represents the parameter of the function
	 */
	public void fireInputs(final String nameOfInput, Object parameter);
}
