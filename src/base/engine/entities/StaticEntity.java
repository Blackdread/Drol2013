package base.engine.entities;


import base.engine.Engine;

public abstract class StaticEntity extends ActiveEntity {

	public StaticEntity(Engine engine, int layer, int type, int maxLife) {
		super(engine, layer, type, maxLife);
		
	}

}
