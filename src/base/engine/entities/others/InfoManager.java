package base.engine.entities.others;

import java.util.ArrayList;
import java.util.HashMap;

import base.engine.entities.others.info.Info;


public class InfoManager {

	private static InfoManager instance;
	
	//private ArrayList<Info> arrayFilterInstancie = new ArrayList<Info>();
	/*
	 * Je sais pas trop si c'est interessant de faire ca comme ca. Ca permet de chercher par rapport au targetname
	 */
	//private static HashMap<String, Info> hashTrigger = new HashMap<String, Info>();
	private HashMap<String, ArrayList<Info>> arrayFilterInstancie = new HashMap<String, ArrayList<Info>>();
	
	public static InfoManager getInstance() {
		if (null == instance) { // Premier appel
            synchronized(objetSynchrone) {
                if (null == instance) {
                    instance = new InfoManager();
                }
            }
        }
        return instance;
	    }
	
	synchronized public void addInfo(Info a){
		ArrayList<Info> tmp = arrayFilterInstancie.get(a.getTargetName());
		if(tmp == null){
			tmp = new ArrayList<Info>();
			tmp.add(a);
			arrayFilterInstancie.put(a.getTargetName(), tmp);
		}else
			tmp.add(a);	// Pas de verification que l'objet est y deja
	}
	
	synchronized public ArrayList<Info> getInfo(String a){
		 return arrayFilterInstancie.get(a);
	}

	
	 private InfoManager(){
		 
	 }
	 
	 private static Object objetSynchrone = new Object();
}
