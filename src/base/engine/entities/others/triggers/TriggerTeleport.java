package base.engine.entities.others.triggers;

import java.util.ArrayList;

import base.engine.EngineManager;
import base.engine.Game;
import base.engine.Message;
import base.engine.MessageKey;
import base.engine.entities.BasicEntity;
import base.engine.entities.others.info.Info;
import base.engine.entities.others.info.InfoTarget;
import base.engine.entities.others.outputs.IActivator;
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

	private static final long serialVersionUID = 8146371176634582789L;

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
	
	public TriggerTeleport(EngineManager e, String name, int xx, int yy, int w, int h) {
		super(e, name, xx, yy, w, h);
		enabled = true;
		
	}
	/**
	 * TODO ne pas teleporter une entite qui a un parent
	 */
	@Override
	public void addAnEntityToActON(BasicEntity entity) {
		super.addAnEntityToActON(entity);
		if(enabled)
			if(entity instanceof IActivator)	// TODO sur ?
			if(testFilter(entity))
				if(remoteDestination != null){
					ArrayList<BasicEntity> tmp = Game.getEngineManager().getInfoManager().getEntity(remoteDestination);
					if(tmp != null){
						int taille = tmp.size();
						if(taille > 1){
							// TODO aleatoirement ou deplacer toutes les entites qui ont le meme nom
							int i = (int) (Math.random()*taille);
							while(tmp.get(i) == null){
								taille--;
								tmp.remove(i);
								i = (int) (Math.random()*taille);
								// Dans le principe, un InfoTarget ou InfoTeleportDestination n'ont pas 
								// le meme nom que les autres type Info => Pas de verification
								if(taille == 0){
									taille = -1;
									break;
								}
							}
							taille = i + 1;
						}
						if(taille >= 1){
							Info tmpInfo = (Info) tmp.get(taille-1);
							if(tmpInfo != null)
								if(tmpInfo instanceof InfoTarget /*|| tmpInfo instanceof InfoTeleportDestination*/){
									Message m = new Message();
									m.instruction = MessageKey.I_MOVE_ENTITY_TO;
									m.i_data.put(MessageKey.P_ID, entity.getId());
									m.i_data.put(MessageKey.P_X, (int)tmpInfo.getX());
									m.i_data.put(MessageKey.P_Y, (int)tmpInfo.getY());
									m.engine = EngineManager.LOGIC_ENGINE;
									
									Game.getEngineManager().receiveMessage(m);
							}
						}
					}else
						if(Configuration.isDebug())
							System.err.println("Trigger Teleport array is null");
				}
		
	}

	public String getRemoteDestination() {
		return remoteDestination;
	}


	public void setRemoteDestination(String remoteDestination) {
		this.remoteDestination = remoteDestination;
	}


}
