package base.engine.entities.others;

import java.util.ArrayList;
import java.util.HashMap;

import base.engine.entities.BasicEntity;
import base.engine.entities.others.outputs.ITargetName;
import base.engine.entities.others.outputs.IUpdatable;
import base.engine.entities.others.outputs.Outputs;

/**
 * Classe a part des autres Manager car elle ne peut pas heriter de Manager
 * Elle est specifique par rapport aux outputs
 * @author Yoann CAPLAIN
 *
 */
public class OutputManager implements IUpdatable{

private static OutputManager instance;
	
	private ArrayList<Outputs> arrayOutputInstancie = new ArrayList<Outputs>();
	
	protected HashMap<String, ArrayList<Outputs>> hashMapOutputsOuputName = new HashMap<String, ArrayList<Outputs>>();
	protected HashMap<String, ArrayList<Outputs>> hashMapOutputsInputName = new HashMap<String, ArrayList<Outputs>>();
	
	public static OutputManager getInstance() {
		if (null == instance) { // Premier appel
            synchronized(objetSynchrone) {
                if (null == instance) {
                    instance = new OutputManager();
                }
            }
        }
        return instance;
     }
	
	@Override
	public void update(int delta) {
		
		
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
	 * Remove outputs which trigger on the entity name given in parameter
	 * @param nameOfEntityThatOutputOn name of the entity that outputs may trigger on (not case sensitive)
	 */
	public void removeOutput(final String nameOfEntityThatOutputOn){
		int i;
		int k;
		if(arrayOutputInstancie != null){
			k = arrayOutputInstancie.size();
			for(i=0; i <  k ;i++){	// TODO Erreur dans le target name
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
		 * v doit implementer ITargetName sinon �a plantera !! A voir si je fais les verifications pour eviter les erreurs
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
	 * Si nameWeSearch termine par un '*' cela signifie que tout les noms qui ont le meme debut jusqu'a '*' seront triggered 
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
	synchronized public Outputs getOutputAt(int a){
		if(arrayOutputInstancie != null)
			if(arrayOutputInstancie.size() > a)
				return arrayOutputInstancie.get(a);
		return null;
	}
	
	 private OutputManager(){
		 
	 }
	 
	 private static Object objetSynchrone = new Object();


}
