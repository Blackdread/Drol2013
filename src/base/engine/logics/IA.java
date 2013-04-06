package base.engine.logics;

import java.util.HashMap;
import java.util.Map.Entry;

<<<<<<< .mine
import base.engine.CollisionManager;
import base.engine.entities.ActiveEntity;
import base.engine.entities.BasicEntity;
=======
import base.engine.entities.ActiveEntity;
import base.engine.entities.others.outputs.IUpdatable;
>>>>>>> .r138
import base.engine.entities.others.outputs.IUpdatable;

public class IA implements IUpdatable{

	private static IA instance;
	public static IA getInstance() {
		if (null == instance) { // Premier appel
            synchronized(objetSynchrone) {
                if (null == instance) {
                    instance = new IA();
                }
            }
        }
        return instance;
	 }
	
	private HashMap<Integer, ActiveEntity> updatable = new HashMap<Integer, ActiveEntity>();
	
	
	@Override
	public void update(int delta) {
		for(Entry<Integer, ActiveEntity> v : updatable.entrySet())
			if(v != null)
				if(v.getValue() != null){
					v.getValue().update(delta);
				}
	}
	
	public void ajouterEntite(ActiveEntity e)
	{
		updatable.put(e.getId(), e);
	}
	
	private static Object objetSynchrone = new Object();

	
	public void addEntity(ActiveEntity entity){
		updatable.put(entity.getId(), entity);
	}
	
	public void removeEntity(ActiveEntity entity){
		updatable.remove(entity.getId());
	}
	public void removeEntity(int id){
		updatable.remove(id);
	}
	

	 private IA(){
		 
	 }
	 
}
