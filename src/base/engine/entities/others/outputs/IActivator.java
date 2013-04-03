package base.engine.entities.others.outputs;

/**
 * This class represents an entity who is an activator
 * 
 * Activator est donne dans le filter, etc et ils gerent ce qu'il faut par rapport a ca.
 * Exemple: Un "activator" a un nom -> filterActivatorName, a de la vie -> filterLife ou logicCompare, 
 * apartient a une class -> filterActivatorClass, etc
 * Un Activator ne peut pas etre un Info ni un Filter ni un Trigger, c'est un monstre, une unite, etc
 * 
 * @author Yoann CAPLAIN
 */
public interface IActivator {

	/**
	 * @return The entity (this)
	 */
	public InputsAndOutputs getActivator();
}
