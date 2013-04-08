package base.engine.entities.others;

import java.util.ArrayList;
import java.util.HashMap;

import base.engine.entities.BasicEntity;

/**
 * 
 * @author Yoann CAPLAIN
 */
public abstract class Manager {
	
	protected HashMap<String, ArrayList<BasicEntity>> hashMapEntity = new HashMap<String, ArrayList<BasicEntity>>();
	
	/**
	 * Add entity in a HashMap then an array list
	 * @param entity
	 */
	synchronized public void addEntity(BasicEntity entity) {
		ArrayList<BasicEntity> tmp = hashMapEntity.get(entity.getTargetName());
		if(tmp == null){
			tmp = new ArrayList<BasicEntity>();
			tmp.add(entity);
			hashMapEntity.put(entity.getTargetName(), tmp);
		}else
			tmp.add(entity);	// Pas de verification que l'objet est y deja
	}

	/**
	 * Remove entities that matches given name
	 * @param entityName name of entity
	 */
	synchronized public void removeEntity(final String entityName) {
		ArrayList<BasicEntity> tmp = hashMapEntity.get(entityName);
		if(tmp != null){
			tmp.clear();
			tmp.trimToSize();
		}
	}
	
	/**
	 * Remove entities that matches given name and given id (id is unique)
	 * @param entityName name of the entity we search
	 * @param idEntity id of the entity to remove
	 */
	synchronized public void removeEntity(final String entityName, final int idEntity) {
		ArrayList<BasicEntity> tmp = hashMapEntity.get(entityName);
		if(tmp != null){
			for(int i=0; i < tmp.size(); i++)
				if(tmp.get(i) != null)
					if(tmp.get(i).getId() == idEntity){
						tmp.remove(i);
						break;
					}
			tmp.trimToSize();
		}
	}
	
	/**
	 * 
	 * @param entityName entity name
	 * @return An array list containing all entity that matches given name or null if not found
	 */
	synchronized public ArrayList<BasicEntity> getEntity(final String entityName) {
		return hashMapEntity.get(entityName);
	}
	
	/**
	 * Get the array list which matches name then search for id
	 * @param entityName entity name
	 * @param entity id
	 * @return An entity that matches given id but not necessarily name
	 */
	synchronized public BasicEntity getEntity(final String entityName, final int id) {
		ArrayList<BasicEntity> tmp = hashMapEntity.get(entityName);
		if(tmp != null)
			for(BasicEntity v : tmp)
				if(v != null)
					if(v.getId() == id)
						return v;
		return null;
	}
}
