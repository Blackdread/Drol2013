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
	/**
	 * 
	 * @param nameWeSearch targetName of the entity (should be unique) (Not case sensitive)
	 * @return An arraylist that contains Outputs that triggers those entities with targetname == nameWeSearch
	 */
	public ArrayList<Outputs> getAllOutputsThatTriggerOn(final String nameWeSearch){
		/*
		 * v doit implementer ITargetName sinon ça plantera !! A voir si je fais les verifications pour eviter les erreurs
		 */
		ArrayList<Outputs> array = new ArrayList<Outputs>();
		if(arrayOutputInstancie != null)
			for(Outputs v : arrayOutputInstancie)
				if(v != null)
					if(v.getNameOfTheEntityToFireInput().equalsIgnoreCase(nameWeSearch)){
						array.add(v);
					}
		
		return array;
	}
	/**
	 * A besoin de chercher dans toutes les entites qui peuvent recevoir des inputs si leur nom
	 * correspond a nameWeSearch pour ensuite declencher l'input
	 * @param nameWeSearch targetName of the entity (should be unique) (Not case sensitive)
	 * @param input name
	 * @param parameter may be null
	 */
	public void triggerInputsOnEntity(final String nameWeSearch, final String nameOfInput, Object parameter){
		//if(parameter == null)
		// TODO
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
