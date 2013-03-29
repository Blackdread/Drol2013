package base.engine.entities.others.triggers;

import base.engine.entities.BasicEntity;

/**
 * It is a trigger that teleports entities that touch its volume
 * Entities are teleported to the Remote Destination, and have their angles set to that of the Remote Destination's.
 * If a Local Destination Landmark is specified, teleported entities are offset 
 * from the target by their initial offset from the landmark, and their angles are left alone
 * @author Yoann CAPLAIN
 *
 */
public class TriggerTeleport extends TriggerObjectInZone {

	/**
	 * The entity specifying the point to which entities should be teleported. 
	 * The target entity can be either an info_target or an info_teleport_destination
	 */
	private String remoteDestination;
	
	/**
	 * If specified, then teleported entities are offset from
	 * the target by their initial offset from the landmark
	 */
	private String localDestinationLandmark;
	
	public TriggerTeleport(String name, int xx, int yy, int w, int h) {
		super(name, xx, yy, w, h);
		
		
	}
	
	@Override
	public void update(int delta) {
		super.update(delta);
		// TODO verifier si il faut teleport les entite qui se trouve a l'interieur
	}
	
	@Override
	public void addAnEntityToActON(BasicEntity entity) {
		super.addAnEntityToActON(entity);
		if(enabled)
			if(testFilter(entity))
				if(remoteDestination != null){
					// TODO recuperer la destination puis envoyer un msg a l'engine pour deplacer l'entite
					
				}
		
	}

}
