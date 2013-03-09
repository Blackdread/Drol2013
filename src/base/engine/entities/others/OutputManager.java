package base.engine.entities.others;

import java.util.ArrayList;

import base.engine.entities.others.outputs.ITargetName;
import base.engine.entities.others.outputs.Outputs;

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
	
	/**
	 * Will delete outputs that are trigger Once and has fired there output (delay is passed)
	 */
	public void removeOutputsThatAreTriggerOnceAndFiredThereOutput(){
		int i;
		int k;
		if(arrayOutputInstancie != null){
			k = arrayOutputInstancie.size();
			for(i=0; i <  k ;i++){
				if(arrayOutputInstancie.get(i) != null){
					if(arrayOutputInstancie.get(i).isFireOnce() && arrayOutputInstancie.get(i).isHasBeenFiredAtleastOnce()){
						arrayOutputInstancie.remove(i);
						k--;
						i--;
					}
				}
			}
		}
	}
	
	/**
	 * Remove outputs
	 * @param nameOfEntityThatOutputOn name of the entity that outputs may trigger on (not case sensitive)
	 */
	public void removeOutput(final String nameOfEntityThatOutputOn){
		int i;
		int k;
		if(arrayOutputInstancie != null){
			k = arrayOutputInstancie.size();
			for(i=0; i <  k ;i++){
				if(arrayOutputInstancie.get(i) != null){	// Verifier que c'est bien une instance de ITargetName ?
					if(((ITargetName)arrayOutputInstancie.get(i)).getTargetName().equalsIgnoreCase(nameOfEntityThatOutputOn)){
						arrayOutputInstancie.get(i).setObjectToDeclencheInput(null);
						arrayOutputInstancie.get(i).setParameter(null);
						arrayOutputInstancie.remove(i);
						k--;
						i--;
						//break;	-> name is supposed not unique
					}
				//i++;
				}
			}
		}
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
