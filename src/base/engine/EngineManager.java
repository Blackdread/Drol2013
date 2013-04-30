package base.engine;

import org.lwjgl.Sys;

import base.engine.entities.ActiveEntity;
import base.engine.entities.BasicEntity;
import base.engine.entities.PlayableEntity;
import base.engine.entities.others.FilterManager;
import base.engine.entities.others.InfoManager;
import base.engine.entities.others.LogicManager;
import base.engine.entities.others.OutputManager;
import base.engine.entities.others.TriggerManager;
import base.engine.entities.others.filters.Filter;
import base.engine.entities.others.info.Info;
import base.engine.entities.others.logics.Logic;
import base.engine.entities.others.triggers.Trigger;
import base.engine.levels.LevelDrol;
import base.engine.logics.Deplacement;
import base.engine.logics.IA;


/**
 * 
 * 
 *@author Yoann CAPLAIN
 *@author Nicolas DUPIN
 *@version 2.0
 */

public class EngineManager{
	
	public static final char SOUND_ENGINE = 0;
	public static final char LOGIC_ENGINE = 1;
	public static final char NETWORK_ENGINE = 2;
	
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
	
	private boolean playingMulti = false;
	private boolean server = false;
	
	public EngineManager(boolean partieMulti, boolean server){
		playingMulti = partieMulti;
		this.server = server;
		
		tabEngine = new Engine[NB_ENGINE];
		tabEngine[SOUND_ENGINE] = new SoundEngine(this);
		tabEngine[LOGIC_ENGINE] = new LogicEngine(this);
		tabEngine[NETWORK_ENGINE] = new NetworkEngine(this);
		
		ia = new IA(this);	// lien vers manager ?
		
		// TODO ont-ils besoin d'un lien vers l'engineManager ?
		filterManager = new FilterManager();
		infoManager = new InfoManager();
		logicManager = new LogicManager();
		triggerManager = new TriggerManager();
		outputManager = new OutputManager();
		
	}
	
	synchronized public void receiveMessage(Object mes){
		if(mes instanceof Message){
			if(((Message)mes).engine != Message.NO_ENGINE){
				tabEngine[((Message)mes).engine].receiveMessage(mes);
				if(playingMulti && !server){
					getNetworkEngine().sendObject(mes);
					//System.out.println("objet envoyer EngineManager");
				}
			}
		}else
			tabEngine[NETWORK_ENGINE].receiveMessage(mes);
		/*
		switch(mes.engine){
		case SOUND_ENGINE:
			tabEngine[SOUND_ENGINE].receiveMessage(mes);
			break;
		case LOGIC_ENGINE:
			tabEngine[LOGIC_ENGINE].receiveMessage(mes);
			break;
		case NETWORK_ENGINE:
			tabEngine[NETWORK_ENGINE].receiveMessage(mes);
			break;
		}
		//*/
	}
	
	/**
	 * When an entity is created, it needs to be added in some Class
	 * @param e the entity to add
	 * TODO n'est jamais utiliser pour le moment -> depuis le 29 avril 2013, j'ai commence a l'utiliser
	 * TODO pas sur que ce soit garder -> normalement c'est a garder !
	 */
	public void addEntity(BasicEntity e){
		currentLevelUsed.getArrayEntite().put(e.getId(), e);
		
		Deplacement.ajouterEntiteDansTiles(e);
		
		if(e instanceof ActiveEntity)
			ia.addEntity((ActiveEntity)e);
		// TODO Faire pour les trigger, info, etc
		if(e instanceof Trigger)
			triggerManager.addEntity(e);
		if(e instanceof Info)
			infoManager.addEntity(e);
		if(e instanceof Filter)
			filterManager.addEntity(e);
		if(e instanceof Logic)
			logicManager.addEntity(e);
	}
	
	/**
	 * Remove an entity from everywhere
	 * @param id entity ID
	 */
	public void removeEntity(final int id){
		currentLevelUsed.removeEntity(id);
		triggerManager.removeEntity(id);
		infoManager.removeEntity(id);
		filterManager.removeEntity(id);
		logicManager.removeEntity(id);
		
		ia.removeEntity(id);
		
		// TODO enlever les outputs de OutputsManager que l'entite avait
	}
	
	/**
	 * Fonction qui spawn une PlayableEntity en recherchant les spawns possibles sur le level
	 * Il cherche les InfoPlayerStart puis les InfoPlayerRobot ou InfoPlayerMonster
	 * @param play l'entite a spawn
	 * 
	 * TODO pas encore fait ni utiliser
	 */
	public void spawnNewUnit(PlayableEntity play/*  choisir quoi mettre en param    */){
		if(currentLevelUsed != null){
			
		}else
			System.err.println("currentLevelUsed is null - Spawn unit");
	}
	
	/**
	 * Remove all entities
	 */
	public void clearEverything(){
		System.out.println("clearEverything was called");
		
		ia.clear();
		filterManager.clear();
		logicManager.clear();
		infoManager.clear();
		triggerManager.clear();
		outputManager.clear();
		
		currentLevelUsed.clear();
		
		for(int i=0;i<NB_ENGINE;i++)
			tabEngine[i].clear();
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
	
	public NetworkEngine getNetworkEngine(){
		return (NetworkEngine) tabEngine[NETWORK_ENGINE];
	}
	
	public SoundEngine getSoundEngine(){
		return (SoundEngine) tabEngine[SOUND_ENGINE];
	}
	
	/*
	public Engine[] getTabEngine(){
		return tabEngine;
	}*/

	public synchronized boolean isPlayingMulti() {
		return playingMulti;
	}

	public synchronized boolean isServer() {
		return server;
	}

	public synchronized void setPlayingMulti(boolean playingMulti) {
		this.playingMulti = playingMulti;
	}

	public synchronized void setServer(boolean server) {
		this.server = server;
	}
}
