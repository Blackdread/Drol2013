package base.engine;

import org.lwjgl.Sys;


/**
 * 
 * 
 *@author Yoann CAPLAIN
 *@author Nicolas DUPIN
 */
public class EngineManager {
	
	private static final int NB_ENGINE = 3;
	private static EngineManager instance;
	private Engine tabEngine[]; //fdfs
	
	private EngineManager(){
		tabEngine = new Engine[NB_ENGINE];
		tabEngine[0] = new SoundEngine();
		tabEngine[1] = new LogicEngine();
		tabEngine[2] = new NetworkEngine();
	}
	
	/**
	 * 
	 */
	public void update(){
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
	
	private static Object objetSynchrone;
	
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
