package base.engine.entities.others.outputs;

import base.engine.entities.BasicEntity;

/**
 * Activator est donne dans le filter, etc et ils gerent ce qu'il faut par rapport a ca.
 * Exemple: Un "activator" a un nom -> filterActivatorName, a de la vie -> filterLife ou logicCompare, 
 * apartient a une class -> filterActivatorClass, etc
 * 
 * @author Yoann CAPLAIN
 */
public interface IActivator {

	public BasicEntity getActivator();
	public void setActivator(BasicEntity entity);
}
