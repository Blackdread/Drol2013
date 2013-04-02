package base.engine.entities.others.triggers;

import java.util.ArrayList;

import base.engine.EngineManager;
import base.engine.Message;
import base.engine.MessageKey;
import base.engine.entities.BasicEntity;
import base.engine.entities.others.InfoManager;
import base.engine.entities.others.info.Info;
import base.utils.Configuration;

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
					ArrayList<Info> tmp = InfoManager.getInstance().getInfo(remoteDestination);
					if(tmp != null){
						int taille = tmp.size();
						if(taille > 1){
							// aleatoirement
							
						}
						if(tmp.get(taille-1) != null){
							Message m = new Message();
							m.instruction = MessageKey.I_MOVE_ENTITY;
							m.i_data.put(MessageKey.P_ID, entity.getId());
							m.i_data.put(MessageKey.P_X, (int)tmp.get(taille-1).getX());
							m.i_data.put(MessageKey.P_Y, (int)tmp.get(taille-1).getY());
							//m.engine = m.LOGIC_ENGINE;// TODO a faire
							
							EngineManager.getInstance().getTabEngine()[1].receiveMessage(m);	// TODO on ne doit plus recuperer le tableau mais 
							// ca ce trouve dans le message a quel engine envoyer
						}
					}else
						if(Configuration.isDebug())
							System.err.println("Trigger Teleport array is null");
				}
		
	}

}
