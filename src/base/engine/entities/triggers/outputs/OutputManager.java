package base.engine.entities.triggers.outputs;

import java.util.ArrayList;

public class OutputManager {

	
private static OutputManager instance;
	
	private ArrayList<Outputs> arrayOutputInstancie = new ArrayList<Outputs>();
	/*
	 * Je sais pas trop si c'est interessant de faire ca comme ca. Ca permet de chercher par rapport au targetname
	 * 
	 * ATTENTION ICI, CE n'est pas forcement bien car on veut des output ORDONNES hors un HashMap ne garanti pas l'ordre des
	 * elements
	 * 
	 */
	//private static HashMap<String, OutputManager> hashTrigger = new HashMap<String, OutputManager>();
	
	public static OutputManager getInstance() {
		if (null == instance) { // Premier appel
            synchronized(objetSynchrone__) {
                if (null == instance) {
                    instance = new OutputManager();
                }
            }
        }
        return instance;
	    }
	
	synchronized public void addOutput(Outputs a){
		if(arrayOutputInstancie != null)
			arrayOutputInstancie.add(a);
	}
	synchronized public void getOutputAt(int a){
		if(arrayOutputInstancie != null)
			if(arrayOutputInstancie.size() > a)
				arrayOutputInstancie.get(a);
	}
	@Deprecated
	synchronized public void removeOutputAt(int a){
		if(arrayOutputInstancie != null)
			if(arrayOutputInstancie.size() > a)
				arrayOutputInstancie.remove(a);
	}
	
	 private OutputManager(){
		 
	 }
	 
	 private static Object objetSynchrone__;
}
