package base.engine;

import java.util.HashMap;

import org.lwjgl.Sys;

import base.engine.entities.ActiveEntity;
import base.engine.entities.BasicEntity;
import base.engine.entities.others.FilterManager;
import base.engine.entities.others.InfoManager;
import base.engine.entities.others.LogicManager;
import base.engine.entities.others.OutputManager;
import base.engine.entities.others.TriggerManager;
import base.engine.levels.LevelDrol;
import base.engine.logics.IA;


/**
 * 
 * 
 *@author Yoann CAPLAIN
 *@author Nicolas DUPIN
 *@version 2.0
 */

public class EngineManager{
	
	public static final char SOUND_ENGINE = '0';
	public static final char LOGIC_ENGINE = '1';
	public static final char NETWORK_ENGINE = '2';
	
	//public static final char IA = '3';
	
	private static final int NB_ENGINE = 3;
	
	/**
	 * TODO A changer par un hashMap normalement
	 */
	private Engine tabEngine[];
	//private HashMap<Character, Engine> tabEngine;
	
	private IA ia;
	private FilterManager filterManager;
	private LogicManager logicManager;
	private InfoManager infoManager;
	private TriggerManager triggerManager;
	private OutputManager outputManager;
	
	private LevelDrol currentLevelUsed = null;
	
	public EngineManager(){
		tabEngine = new Engine[NB_ENGINE];
		tabEngine[0] = new SoundEngine(this);
		tabEngine[1] = new LogicEngine(this);
		tabEngine[2] = new NetworkEngine(this);
		
		ia = new IA();	// lien vers manager ?
		
		// TODO ont-ils besoin d'un lien vers l'engineManager ?
		filterManager = new FilterManager();
		infoManager = new InfoManager();
		logicManager = new LogicManager();
		triggerManager = new TriggerManager();
		outputManager = new OutputManager();
	}
	
	synchronized public void receiveMessage(Message mes){
		switch(mes.engine){
		case SOUND_ENGINE:
			tabEngine[0].receiveMessage(mes);
			break;
		case LOGIC_ENGINE:
			tabEngine[1].receiveMessage(mes);
			break;
		case NETWORK_ENGINE:
			tabEngine[2].receiveMessage(mes);
			break;
		}
	}
	
	/**
	 * When an entity is created, it needs to be added in some Class
	 * @param e the entity to add
	 * TODO n'est jamais utiliser pour le moment
	 * TODO pas sur que ce soit garder
	 */
	public void addEntity(BasicEntity e){
		currentLevelUsed.getArrayEntite().put(e.getId(), e);
		if(e instanceof ActiveEntity)
			ia.addEntity((ActiveEntity)e);
		// TODO Faire pour les trigger, info, etc
	}
	
	/**
	 * Update everything with the parameter delta
	 * All message are proceed
	 * @param delta 
	 */
	public void update(int delta){
		updateWhatNeedsToBeUpdated(delta);
		
		for(int i=0;i<NB_ENGINE;i++)
			while(tabEngine[i].processMessage());
	}
	
	/**
	 * Will proceed "processMessage" for all Engines but each engine won't be processed more than delta 
	 * So engines may still have message pending
	 * @param delta 
	 */
	public void update2(int delta){
		updateWhatNeedsToBeUpdated(delta);
		
		long time;
		for(int i=0;i<NB_ENGINE;i++){
			time = getTime();
			while(tabEngine[i].processMessage() && getTime() - time < delta);	// un processMessage est fait au minimum
		}
	}
	/**
	 * Will proceed "processMessage" for all engines but total time elapsed time won't pass delta
	 * One message is proceed for each engine then do it again until delta is reached
	 * So engines may still have message pending
	 * @param delta
	 */
	public void update3(int delta){
		updateWhatNeedsToBeUpdated(delta);
		
		long time = getTime();
			do{
				for(int i=0;i<NB_ENGINE;i++)
					tabEngine[i].processMessage();
			}while(getTime() - time < delta);
	}
	
	private void updateWhatNeedsToBeUpdated(int delta){
		ia.update(delta);
		logicManager.update(delta);
		triggerManager.update(delta);
		outputManager.update(delta);
	}
	
	
	
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public IA getIA(){
		return ia;
	}
	
	public LogicManager getLogicManager() {
		return logicManager;
	}

	public InfoManager getInfoManager() {
		return infoManager;
	}

	public TriggerManager getTriggerManager() {
		return triggerManager;
	}

	public OutputManager getOutputManager() {
		return outputManager;
	}

	public FilterManager getFilterManager() {
		return filterManager;
	}

	public LevelDrol getCurrentLevelUsed() {
		return currentLevelUsed;
	}

	public void setCurrentLevelUsed(LevelDrol currentLevelUsed) {
		this.currentLevelUsed = currentLevelUsed;
	}
	
	/*
	public Engine[] getTabEngine(){
		return tabEngine;
	}*/
}
