package base.engine.entities.others;

import java.util.ArrayList;

import base.engine.entities.others.logics.Logic;
import base.engine.entities.others.outputs.IUpdatable;

/**
 * 
 * 
 * @author Yoann CAPLAIN
 * 
 */
public class LogicManager implements IUpdatable{

	private static LogicManager instance;
	
	/**
	 * On perd un peu le principe de : je ne sais pas ce que peuvent ajouter les programmeurs mais au moins 
	 * on gagne en loop car les autres Logic n'ont pas besoin d'etre updater.
	 * Ils sont juste appelés par des Outputs
	 */
	private ArrayList<Logic> arrayLogicThatImplementsIUpdatable = new ArrayList<Logic>();
	
	private ArrayList<Logic> arrayLogicInstancie = new ArrayList<Logic>();
	
	/*
	 * Je sais pas trop si c'est interessant de faire ca comme ca. Ca permet de chercher par rapport au targetname sauf
	 * que je ne le considere pas comme pouvant etre unique donc...
	 */
	//private static HashMap<String, Logic> hashTrigger = new HashMap<String, Logic>();
	
	public static LogicManager getInstance(){
		if (null == instance) { // Premier appel
            synchronized(objetSynchrone__) {	// evite d'avoir (multi-thread) plusieurs thread qui instansie en meme temps le manager
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
	
	public void addLogic(Logic a){
		if(a instanceof IUpdatable)
			arrayLogicThatImplementsIUpdatable.add(a);
		else
			arrayLogicInstancie.add(a);
	}
	/**
	 * May be deleted, useless since we have two separate array list
	 */
	@Deprecated
	public void getLogicAt(int a){
		if(arrayLogicInstancie != null)
			if(arrayLogicInstancie.size() > a)
				arrayLogicInstancie.get(a);
	}
	
	/**
	 * Remove logic that matches the name
	 * @param nameOfLogic (not case sensitive)
	 */
	public void removeLogic(final String nameOfLogic){
		int i;
		int k;
		if(arrayLogicInstancie != null){
			k = arrayLogicInstancie.size();
			for(i=0; i <  k ;i++){
				if(arrayLogicInstancie.get(i) != null)
					if(arrayLogicInstancie.get(i).getTargetName().equalsIgnoreCase(nameOfLogic)){
						OutputManager.getInstance().removeOutput(nameOfLogic);
						arrayLogicInstancie.remove(i);
						k--;
						i--;
						//break;	-> name is supposed not unique
					}
				//i++;	
			}
		}
		
		if(arrayLogicThatImplementsIUpdatable != null){
			k = arrayLogicThatImplementsIUpdatable.size();
			for(i=0; i <  k ;i++){
				if(arrayLogicThatImplementsIUpdatable.get(i) != null)
					if(arrayLogicThatImplementsIUpdatable.get(i).getTargetName().equalsIgnoreCase(nameOfLogic)){
						arrayLogicThatImplementsIUpdatable.remove(i);
						k--;
						i--;
						//return;	-> name is supposed not unique
					}
				//i++;
			}
		}
	}
	
	/**
	 * I prefer to use targetName to remove entities
	 * @param logicInstance
	 */
	@Deprecated
	public void removeLogic(Object logicInstance){
		if(arrayLogicInstancie != null)
			arrayLogicInstancie.remove(logicInstance);
		
		if(arrayLogicThatImplementsIUpdatable != null)
			arrayLogicThatImplementsIUpdatable.remove(logicInstance);
	}
	
	 private LogicManager(){
		 
	 }
	 private static Object objetSynchrone__;


}

