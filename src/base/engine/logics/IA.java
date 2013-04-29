package base.engine.logics;

import java.util.HashMap;

import base.engine.entities.ActiveEntity;
import base.engine.entities.others.outputs.IUpdatable;

public class IA implements IUpdatable{
	
	private HashMap<Integer, ActiveEntity> updatable = new HashMap<Integer, ActiveEntity>();
	
	 public IA(){
		 
	 }
	
	@Override
	public void update(int delta) {
		/*
		for(Entry<Integer, ActiveEntity> v : updatable.entrySet())
			if(v != null)
				if(v.getValue() != null){
					v.getValue().update(delta);
				}
				*/
		for(ActiveEntity v : updatable.values())	// on n'a pas besoin de la Key
			if(v != null)
				v.update(delta);
	}
	
	public void addEntity(ActiveEntity entity){
		updatable.put(entity.getId(), entity);
	}
	
	public void removeEntity(ActiveEntity entity){
		updatable.remove(entity.getId());
	}
	public void removeEntity(int id){
		updatable.remove(id);
	}
	
	public void clear(){
		updatable.clear();
	}
	 
}
