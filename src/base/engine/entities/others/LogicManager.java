package base.engine.entities.others;

import java.util.ArrayList;

import base.engine.entities.BasicEntity;
import base.engine.entities.others.logics.Logic;
import base.engine.entities.others.outputs.IUpdatable;

/**
 * 
 * 
 * @author Yoann CAPLAIN
 * @since 19/03/2013
 */
public class LogicManager extends Manager implements IUpdatable{
	
	/**
	 * On perd un peu le principe de : je ne sais pas ce que peuvent ajouter les programmeurs mais au moins 
	 * on gagne en loop car les autres Logic n'ont pas besoin d'etre updater.
	 * Ils sont juste appelés par des Outputs
	 */
	private ArrayList<Logic> arrayLogicThatImplementsIUpdatable = new ArrayList<Logic>();
	
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
		super.removeEntity(entityName);
		for(int i= 0; i < arrayLogicThatImplementsIUpdatable.size(); i++)
			if(arrayLogicThatImplementsIUpdatable.get(i) != null)
				if(arrayLogicThatImplementsIUpdatable.get(i).getTargetName().equalsIgnoreCase(entityName)){
					arrayLogicThatImplementsIUpdatable.remove(i);
					i--;
				}
		arrayLogicThatImplementsIUpdatable.trimToSize();
	}
	
	@Override
	synchronized public void removeEntity(final String entityName, final int idEntity) {
		super.removeEntity(entityName, idEntity);
		for(int i= 0; i < arrayLogicThatImplementsIUpdatable.size(); i++)
			if(arrayLogicThatImplementsIUpdatable.get(i) != null)
				if(arrayLogicThatImplementsIUpdatable.get(i).getId() == idEntity){
					arrayLogicThatImplementsIUpdatable.remove(i);
					break;
				}
		arrayLogicThatImplementsIUpdatable.trimToSize();
	}
	
	@Override
	synchronized public void removeEntity(final int idEntity) {
		super.removeEntity(idEntity);
		for(int i= 0; i < arrayLogicThatImplementsIUpdatable.size(); i++)
			if(arrayLogicThatImplementsIUpdatable.get(i) != null)
				if(arrayLogicThatImplementsIUpdatable.get(i).getId() == idEntity){
					arrayLogicThatImplementsIUpdatable.remove(i);
					break;
				}
		arrayLogicThatImplementsIUpdatable.trimToSize();
	}
	
	synchronized public void clear(){
		super.clear();
		arrayLogicThatImplementsIUpdatable.clear();
	}
	
	/*	n'a pas besoin d'etre redefinie normalement
	synchronized public ArrayList<BasicEntity> getEntity(final String entityName) {
		ArrayList<BasicEntity> tmp = super.getEntity(entityName);
		if(tmp == null){
			tmp = new ArrayList<BasicEntity>();
			for(BasicEntity v : arrayLogicThatImplementsIUpdatable)
				if(v != null)
					if(v.getTargetName().equalsIgnoreCase(entityName))
						tmp.add(v);
		}
		return tmp;
	}
	synchronized public BasicEntity getEntity(final String entityName, final int id) {
		
	}
	//*/
	
	/*	N'est plus un singleton depuis l'ajout du serveur
	 private LogicManager(){
		 
	 }
	 private static Object objetSynchrone = new Object();
	 private static LogicManager instance;
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
		    */
}

