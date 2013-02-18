package base.engine.entities;


import base.engine.Engine;


public abstract class MoveableEntity extends ActiveEntity{
	
	public MoveableEntity(Engine engine, int layer, int type, int maxLife) {
		super(engine, layer, type, maxLife);
		
	}
	
	
}
