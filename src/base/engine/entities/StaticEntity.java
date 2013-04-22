package base.engine.entities;

import base.engine.EngineManager;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public abstract class StaticEntity extends ActiveEntity {

	public StaticEntity(String name, EngineManager e, int maxLife) {
		super(name, e, maxLife);
		
	}

}
