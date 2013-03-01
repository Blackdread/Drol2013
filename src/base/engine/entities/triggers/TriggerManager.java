package base.engine.entities.triggers;

import java.util.ArrayList;

/**
 * 
 * Faire le checktrigger sur les TriggerObjectInZone seulement si un objet 
 * s'est deplace -> on gagne en vitesse d'excution et moins de loop
 * 
 * @author Yoann CAPLAIN
 * 
 */
public class TriggerManager {

	private static TriggerManager instance;
	
	private ArrayList<Trigger> arrayTriggerInstancie = new ArrayList<Trigger>();
	
	
	public static TriggerManager getInstance() {
		if (null == instance) { // Premier appel
            synchronized(objetSynchrone__) {	// evite d'avoir (multi-thread) plusieurs thread qui instansie en meme temps le manager
                if (null == instance) {
                    instance = new TriggerManager();
                }
            }
        }
        return instance;
	    }
	
	synchronized public void addTrigger(Trigger a){
		if(arrayTriggerInstancie != null)
			arrayTriggerInstancie.add(a);
	}
	synchronized public void getTriggerAt(int a){
		if(arrayTriggerInstancie != null)
			if(arrayTriggerInstancie.size() > a)
				arrayTriggerInstancie.get(a);
	}
	
	/**
	 * 
	 */
	 private TriggerManager(){
		 
	 }
	 private static Object objetSynchrone__;
}
