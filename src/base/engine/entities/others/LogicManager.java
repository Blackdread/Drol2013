package base.engine.entities.others;

import java.util.ArrayList;

import base.engine.entities.BasicEntity;
import base.engine.entities.others.logics.Logic;
import base.engine.entities.others.outputs.IUpdatable;

/**
 * 
 * 
 * @author Yoann CAPLAIN
 * 
 */
public class LogicManager extends Manager implements IUpdatable{

	private static LogicManager instance;
	
	/**
	 * On perd un peu le principe de : je ne sais pas ce que peuvent ajouter les programmeurs mais au moins 
	 * on gagne en loop car les autres Logic n'ont pas besoin d'etre updater.
	 * Ils sont juste appelés par des Outputs
	 */
	private ArrayList<Logic> arrayLogicThatImplementsIUpdatable = new ArrayList<Logic>();
	
	public static LogicManager getInstance(){
		if (null == instance) { // Premier appel
            synchronized(objetSynchrone) {	// evite d'avoir (multi-thread) plusieurs thread qui instansie en meme temps le manager
                if (null == instance) {
                    instance = new LogicManager();
                }
            }
        }
        return instance;
	    }
	
	@Override
	public void update(final int delta) {
		for(Logic v : arrayLogicThatImplementsIUpdatable)
			if(v != null)
				((IUpdatable)v).update(delta);
	}
	
	@Override
	synchronized public void addEntity(BasicEntity entity) {
		super.addEntity(entity);
		if(entity instanceof IUpdatable)
			arrayLogicThatImplementsIUpdatable.add((Logic) entity);
	}
	
	@Override
	synchronized public void removeEntity(final String entityName) {
		ArrayList<BasicEntity> tmp = hashMapEntity.get(entityName);
		if(tmp != null){
			tmp.clear();
			tmp.trimToSize();
		}
	}
	
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
	

	synchronized public ArrayList<BasicEntity> getEntity(final String entityName) {
		return hashMapEntity.get(entityName);
	}
	

	synchronized public BasicEntity getEntity(final String entityName, final int id) {
		ArrayList<BasicEntity> tmp = hashMapEntity.get(entityName);
		if(tmp != null)
			for(BasicEntity v : tmp)
				if(v != null)
					if(v.getId() == id)
						return v;
		return null;
	}
	
	 private LogicManager(){
		 
	 }
	 private static Object objetSynchrone = new Object();


}

