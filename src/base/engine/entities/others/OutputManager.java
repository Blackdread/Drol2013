package base.engine.entities.others;

import java.util.ArrayList;
import java.util.HashMap;

import base.engine.entities.others.outputs.IUpdatable;
import base.engine.entities.others.outputs.InputsAndOutputs;
import base.engine.entities.others.outputs.Outputs;

/**
 * Classe a part des autres Manager car elle ne peut pas heriter de Manager
 * Elle est specifique par rapport aux outputs
 * @author Yoann CAPLAIN
 *
 */
public class OutputManager implements IUpdatable{

	private static OutputManager instance;
	
	protected HashMap<Integer, Outputs> hashId = new HashMap<Integer, Outputs>();
	
	protected HashMap<String, ArrayList<Outputs>> hashNameOfTheOwner = new HashMap<String, ArrayList<Outputs>>();
	protected HashMap<String, ArrayList<Outputs>> hashNameOfTheReceiver = new HashMap<String, ArrayList<Outputs>>();
	
	// TODO faire une fonction qui met l'activator a jour pour les entites qui en ont besoin -> attention Pas toute donc c'est un peu plus dur a faire
	
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
		for(Outputs v : hashId.values()){
			if(v != null)
				v.update(delta);
		}
		// TODO y a des boolean a ajouter pour savoir s'il faut enlever des outputs etc
	}
	
	/**
	 * Will delete outputs that are trigger Once and has fired there output (delay is passed)
	 */
	public void removeOutputsThatAreTriggerOnceAndFiredThereOutput(){
		for(Outputs v : hashId.values())
			if(v != null)
				if(v.isFireOnce() && v.isHasBeenFiredAtleastOnce()){
					removeOutputsOfTheOwner(v.getId());
					removeOutputs(v.getId());
				}
	}
	
	/**
	 * Remove an output from a owner
	 * @param id of the output
	 */
	public void removeOutputsOfTheOwner(final int id){
		Outputs tmp = hashId.get(id);
		if(tmp != null)
			tmp.removeThisOutputsFromOwner();
	}
	
	/**
	 * Delete output that match id
	 * @param id unique
	 */
	public void removeOutputs(final int id){
		
		removeOutputsOfTheOwner(id);
		
		boolean continuer = true;
		for(ArrayList<Outputs> w : hashNameOfTheOwner.values()){
			if(w != null){
				for(int i=0;i<w.size();i++)
					if(w.get(i) != null)
						if(w.get(i).getId() == id){
							w.remove(i);
							w.trimToSize();
							continuer = false;
							break;
						}
				if(!continuer)
					break;
			}
		}
		continuer = true;			
		for(ArrayList<Outputs> w : hashNameOfTheReceiver.values()){
			if(w != null){
				for(int i=0;i<w.size();i++)
					if(w.get(i) != null)
						if(w.get(i).getId() == id){
							w.remove(i);
							w.trimToSize();
							continuer = false;
							break;
						}
				if(!continuer)
					break;
			}
		}
		
		hashId.remove(id);
	}
	
	/**
	 * Remove outputs which trigger on the entity name given in parameter
	 * @param nameOfEntityThatOutputOn name of the entity that outputs may trigger on (not case sensitive)
	 */
	public void removeOutput(final String nameOfEntityThatOutputOn){
		hashNameOfTheReceiver.remove(nameOfEntityThatOutputOn);
		
		for(ArrayList<Outputs> w : hashNameOfTheOwner.values())
			if(w != null){
				for(int i=0;i < w.size();i++)
					if(w.get(i) != null)
						if(w.get(i).getNameOfTheEntityToFireInput().equalsIgnoreCase(nameOfEntityThatOutputOn)){
							
							removeOutputsOfTheOwner(w.get(i).getId());
							
							hashId.remove(w.get(i).getId());	// ATTENTION est ce bien juste ? car si l'ajout est mal fait etc
							w.remove(i);
						}
				w.trimToSize();
			}
						
	}
	/**
	 * 
	 * @param nameWeSearch targetName of the entity (should be unique) (Not case sensitive)
	 * @return An arraylist that contains Outputs that triggers those entities with targetname == nameWeSearch
	 */
	public ArrayList<Outputs> getAllOutputsThatTriggerOn(final String nameWeSearch){
		return hashNameOfTheReceiver.get(nameWeSearch);
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
	
	/**
	 * Set the activator of I/O chain
	 * 
	 * Don't forget to call it !!!
	 * Ex: If an entity enter a trigger and that entity implements IActivator then the trigger must call this function
	 * and the entity in the parameter
	 * Triggers must call this function if needed
	 * Ex: func_tracktrain and path_track -> see Valve User And Outputs
	 * @param activator
	 */
	public void setActivatorForThe_IO_chain(InputsAndOutputs activator){	// TODO Manque des parametres
		// Rajouter dans les parametres l'entite qui commence cette I/O chain (generalement l'entite qui passe dans un trigger ou un logicTimer)
		/*
		 * Algo :
		 * Parcourir les outputs de l'entite (que ceux qui vont etre active ? -> besoin d'un parametre en plus)
		 * Mettre l'activator dans les outputs parcouru
		 * Parcourir les outputs des entites sur lesquelles les 1er outputs ont ete active et 
		 * ainsi de suite jusqu'a atteindre la fin de la I/O chain (Attention il peut y avoir des boucles)
		 * 
		 */
	}
	
	public void addOutput(Outputs a){
		ArrayList<Outputs> tmp = hashNameOfTheOwner.get(a.getNameOfTheOwner());
		if(tmp == null){
			tmp = new ArrayList<Outputs>();
			tmp.add(a);
			hashNameOfTheOwner.put(a.getNameOfTheOwner(), tmp);
		}else
			tmp.add(a);	// Pas de verification que l'objet est y deja
		
		ArrayList<Outputs> tmp2 = hashNameOfTheReceiver.get(a.getNameOfTheEntityToFireInput());
		if(tmp2 == null){
			tmp2 = new ArrayList<Outputs>();
			tmp2.add(a);
			hashNameOfTheReceiver.put(a.getNameOfTheOwner(), tmp2);
		}else
			tmp2.add(a);	// Pas de verification que l'objet est y deja
		
		hashId.put(a.getId(), a);
	}
	
	 private OutputManager(){
		 
	 }
	 
	 private static Object objetSynchrone = new Object();


}
