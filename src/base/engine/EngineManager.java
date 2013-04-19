package base.engine;

import java.util.HashMap;

import org.lwjgl.Sys;

import base.engine.levels.Level;
import base.engine.logics.IA;


/**
 * 
 * 
 *@author Yoann CAPLAIN
 *@author Nicolas DUPIN
 */

public class EngineManager{
	
	public static final char SOUND_ENGINE = '0';
	public static final char LOGIC_ENGINE = '1';
	public static final char NETWORK_ENGINE = '2';
	
	public static final char IA = '3';
	
	private static final int NB_ENGINE = 3;
	/**
	 * TODO A changer par un hashMap normalement
	 */
	private Engine tabEngine[];
	//private HashMap<Character, Engine> tabEngine;
	
	private IA ia;
	
	public Level currentLevelUsed = null;
	
	public EngineManager(){
		tabEngine = new Engine[NB_ENGINE];
		tabEngine[0] = new SoundEngine();
		tabEngine[1] = new LogicEngine();
		tabEngine[2] = new NetworkEngine();
		
		ia = new IA();
	}
	
	public void receiveMessage(Message mes){
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
	 * 
	 */
	public void update(int delta){
		ia.update(delta);
		
		for(int i=0;i<NB_ENGINE;i++)
			while(tabEngine[i].processMessage());
	}
	
	/**
	 * 
	 * @param delta
	 */
	public void update2(int delta){
		ia.update(delta);
		
		long time;
		for(int i=0;i<NB_ENGINE;i++){
			time = getTime();
			while(tabEngine[i].processMessage() && getTime() - time < delta);	// un processMessage est fait au minimum
		}
	}
	/**
	 * 
	 * @param delta
	 */
	public void update3(int delta){
		ia.update(delta);
		
		long time = getTime();
			do{
				for(int i=0;i<NB_ENGINE;i++)
					tabEngine[i].processMessage();
			}while(getTime() - time < delta);
	}
	
	
	
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	public IA getIA(){
		return ia;
	}
	
	public Level getCurrentLevelUsed() {
		return currentLevelUsed;
	}

	public void setCurrentLevelUsed(Level currentLevelUsed) {
		Level.currentLevelUsed = currentLevelUsed;
	}
	
	public Engine[] getTabEngine(){
		return tabEngine;
	}
}
