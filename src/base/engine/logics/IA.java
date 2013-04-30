package base.engine.logics;

import java.util.HashMap;

import base.engine.EngineManager;
import base.engine.entities.ActiveEntity;
import base.engine.entities.BasicEntity;
import base.engine.entities.Zombi;
import base.engine.entities.others.outputs.IUpdatable;
import base.utils.Timer;
import base.utils.Vector2f;

public class IA implements IUpdatable{
	
	private HashMap<Integer, ActiveEntity> updatable = new HashMap<Integer, ActiveEntity>();
	
	private Timer spawnZombi = new Timer(800);
	
	private EngineManager engineManager;
	
	 public IA(EngineManager engineManager){
		 this.engineManager = engineManager;
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
		for(ActiveEntity v : updatable.values()){	// on n'a pas besoin de la Key
			if(v != null)
				v.update(delta);
		}
		
		spawnZombi.update(delta);
		
		if(spawnZombi.isTimeComplete()){
			spawnZombie();
			spawnZombi.resetTime();
		}
	}
	
	/**
	 * Spawn un zombie
	 */
	public void spawnZombie(){
		if(engineManager.getCurrentLevelUsed().getMaxZombieEnMemeTemps() > engineManager.getCurrentLevelUsed().getNbZombie()){
			Zombi z = new Zombi("zombi", engineManager, 10);
			
			Vector2f vec = engineManager.getCurrentLevelUsed().trouverZoneLibre(z);
			
			z.setLocation(vec.x, vec.y);
			engineManager.addEntity(z);
		}
	}
	
	public void addEntity(ActiveEntity entity){
		updatable.put(entity.getId(), entity);
		if(entity instanceof Zombi)
			engineManager.getCurrentLevelUsed().setNbZombie(engineManager.getCurrentLevelUsed().getNbZombie()+1);
	}
	
	public void removeEntity(ActiveEntity entity){
		updatable.remove(entity.getId());
		if(entity instanceof Zombi)
			engineManager.getCurrentLevelUsed().setNbZombie(engineManager.getCurrentLevelUsed().getNbZombie()-1);
	}
	public void removeEntity(int id){
		BasicEntity tmp = updatable.remove(id);
		
		if(tmp != null)
			if(tmp instanceof Zombi)
				engineManager.getCurrentLevelUsed().setNbZombie(engineManager.getCurrentLevelUsed().getNbZombie()-1);
	}
	
	public void clear(){
		updatable.clear();
	}
	 
}
