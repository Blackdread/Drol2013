package base.engine.entities.others;

import java.util.ArrayList;
import java.util.Map.Entry;

import base.engine.entities.BasicEntity;
import base.engine.entities.others.outputs.IUpdatable;
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
public class TriggerManager extends Manager implements IUpdatable{
	
	@Override
	public void update(final int delta) {
		for(ArrayList<BasicEntity> v : hashMapEntity.values()){
			if(v != null)
				for(BasicEntity w :  v)
					if(w != null){
						((Trigger)w).update(delta);	// pas de verification, on est dans TriggerManager il doit y avoir que des Trigger !
					}
		}
	}
	
	/*	N'est plus un singleton depuis l'ajout du serveur
	 private TriggerManager(){
		 
	 }
	 private static Object objetSynchrone = new Object();
		private static TriggerManager instance;
		
		public static TriggerManager getInstance(){
			if (null == instance) { // Premier appel
	            synchronized(objetSynchrone) {	// evite d'avoir (multi-thread) plusieurs thread qui instansie en meme temps le manager
	                if (null == instance) {
	                    instance = new TriggerManager();
	                }
	            }
	        }
			return instance;
		}
*/
}
