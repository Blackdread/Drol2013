package base.engine;

import org.lwjgl.Sys;

import base.engine.logics.IA;


/**
 * 
 * 
 *@author Yoann CAPLAIN
 *@author Nicolas DUPIN
 */
public class EngineManager {
	
	public static final char SOUND_ENGINE = '0';
	public static final char LOGIC_ENGINE = '1';
	public static final char NETWORK_ENGINE = '2';
	
	private static final int NB_ENGINE = 3;
	private static EngineManager instance;
	/**
	 * TODO A changer par un hashMap normalement
	 */
	private Engine tabEngine[];

	public Engine[] getTabEngine() {
		return tabEngine;
	}//*/

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

	private EngineManager(){
		tabEngine = new Engine[NB_ENGINE];
		tabEngine[0] = new SoundEngine();
		tabEngine[1] = new LogicEngine();
		tabEngine[2] = new NetworkEngine();
	}
	
	/**
	 * 
	 */
	public void update(int delta){
		IA.getInstance().update(delta);
		
		for(int i=0;i<NB_ENGINE;i++)
			while(tabEngine[i].processMessage());
	}
	
	/**
	 * 
	 * @param delta
	 */
	public void update2(int delta){
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
		long time = getTime();
			do{
				for(int i=0;i<NB_ENGINE;i++)
					tabEngine[i].processMessage();
			}while(getTime() - time < delta);
	}
	
	
	
	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	private static Object objetSynchrone = new Object();
	
	public static EngineManager getInstance() {
		if (null == instance) { // Premier appel
            synchronized(objetSynchrone) {	// evite d'avoir (multi-thread) plusieurs thread qui instansie en meme temps le manager
                if (null == instance) {
                    instance = new EngineManager();
                }
            }
        }
		return instance;
	 }
	
	
}
