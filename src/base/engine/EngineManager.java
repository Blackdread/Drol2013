package base.engine;


/**
 * 
 * 
 *@author Yoann CAPLAIN
 *@author Nicolas DUPIN
 */
public class EngineManager {

	private static EngineManager instance;
	
	private EngineManager(){
		 
	}
	 
	 private static Object objetSynchrone__;
	
	public static EngineManager getInstance() {
		if (null == instance) { // Premier appel
            synchronized(objetSynchrone__) {	// evite d'avoir (multi-thread) plusieurs thread qui instansie en meme temps le manager
                if (null == instance) {
                    instance = new EngineManager();
                }
            }
        }
		return instance;
	 }
	
	
}
