package base.engine.entities;

import base.engine.EngineManager;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public abstract class StaticEntity extends ActiveEntity {

	private static final long serialVersionUID = -4058180091019839222L;

	public StaticEntity(String name, EngineManager e, int maxLife) {
		super(name, e, maxLife);
		
	}

}
