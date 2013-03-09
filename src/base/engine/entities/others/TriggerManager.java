package base.engine.entities.others;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.AngelCodeFont;

import base.engine.entities.others.triggers.Trigger;

/**
 * 
 * Faire le checktrigger sur les TriggerObjectInZone seulement si un objet 
 * s'est deplace -> on gagne en vitesse d'excution et moins de loop
 * 
 * @TODO Possible que ca devienne un Thread de facons a regarder en permanence si des entites entre/sort dans 
 * un trigger, ou etc... (Tout se passe quasiment dans l'efficacite et la rapidite des trigger et un thread pourrait
 * resoudre ca et permettre a l'engine de s'occuper des autres choses)
 * 
 * @author Yoann CAPLAIN
 * 
 */
public class TriggerManager {

	private static TriggerManager instance;
	
	private ArrayList<Trigger> arrayTriggerInstancie = new ArrayList<Trigger>();
	/*
	 * Je sais pas trop si c'est interessant de faire ca comme ca. Ca permet de chercher par rapport au targetname
	 */
	//private static HashMap<String, Trigger> hashTrigger = new HashMap<String, Trigger>();
	
	public static TriggerManager getInstance(){
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
