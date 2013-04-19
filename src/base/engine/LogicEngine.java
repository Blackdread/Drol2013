package base.engine;

import org.newdawn.slick.geom.Vector2f;

import base.engine.entities.BasicEntity;
import base.engine.entities.MoveableEntity;
import base.engine.entities.PlayableEntity;
import base.engine.entities.Tir;
import base.engine.entities.TirLinear;
import base.engine.levels.LevelDrol;
import base.engine.logics.Deplacement;
import base.engine.logics.IA;

/**
 * 
 * @author Yoann CAPLAIN
 * @author Nicolas DUPIN
 * @since 19/03/2013
 */
public class LogicEngine extends Engine {

	public static long tmp = System.currentTimeMillis();
	
	public LogicEngine(EngineManager engineManager)
	{
		super(engineManager);
		tmp = System.currentTimeMillis();
	}
	
	@Override
	public boolean processMessage() {
		Message mes;
		//while(!this.message_queue.isEmpty()){
		
		if(!this.message_queue.isEmpty()){
			mes = this.message_queue.poll();
			switch(mes.instruction){
				case MessageKey.I_MOVE_ENTITY:
					if(mes.i_data.containsKey(MessageKey.P_ID))
					{
						int id, x, y;
						id = mes.i_data.get(MessageKey.P_ID);
						if(mes.i_data.containsKey(MessageKey.P_X))
						{
							x = mes.i_data.get(MessageKey.P_X);
							if(mes.i_data.containsKey(MessageKey.P_Y))
							{
								y = mes.i_data.get(MessageKey.P_Y);
								Deplacement.deplacerEntity(x, y, id);
							}
						}
					}
					break;
					
				case MessageKey.I_START_ENTITY_MOVE:
					if(mes.i_data.containsKey(MessageKey.P_ID))
					{
						BasicEntity tmp = engineManager.getCurrentLevelUsed().getEntity(mes.i_data.get(MessageKey.P_ID));
						boolean changeDir = true;
						if(mes.b_data.containsKey(MessageKey.P_CHANGE_DIRECTION))
							changeDir = mes.b_data.get(MessageKey.P_CHANGE_DIRECTION);
						if(tmp != null)
							if(tmp instanceof MoveableEntity){
								Vector2f vec = ((MoveableEntity)tmp).getvitesse();
								float defVit = ((MoveableEntity)tmp).getDefaultVitesse();
								if(mes.f_data.containsKey(MessageKey.P_VITESSE_Y))
									defVit = mes.f_data.get(MessageKey.P_VITESSE_Y);
								
								
								if(mes.i_data.containsKey(MessageKey.P_DIRECTION))
									switch(mes.i_data.get(MessageKey.P_DIRECTION)){
									case BasicEntity.DROITE:
										vec.x += defVit;
										if(changeDir)
											tmp.setDirection(BasicEntity.DROITE);
										break;
									case BasicEntity.GAUCHE:
										vec.x -= defVit;
										if(changeDir)
											tmp.setDirection(BasicEntity.GAUCHE);
										break;
									case BasicEntity.HAUT:
										vec.y -= defVit;
										if(changeDir)
											tmp.setDirection(BasicEntity.HAUT);
										break;
									}
							}
					}
					break;
					
				case MessageKey.I_STOP_ENTITY:
					if(mes.i_data.containsKey(MessageKey.P_ID))
					{
						BasicEntity tmp = engineManager.getCurrentLevelUsed().getEntity(mes.i_data.get(MessageKey.P_ID));
						if(tmp instanceof MoveableEntity)
							((MoveableEntity)tmp).setVitesseToZero();
					}
					break;
					
				case MessageKey.I_MOVE_ENTITY_TO:
					if(mes.i_data.containsKey(MessageKey.P_ID))
					{
						int id, x, y;
						id = mes.i_data.get(MessageKey.P_ID);
						if(mes.i_data.containsKey(MessageKey.P_X))
						{
							x = mes.i_data.get(MessageKey.P_X);
							if(mes.i_data.containsKey(MessageKey.P_Y))
							{
								y = mes.i_data.get(MessageKey.P_Y);
								// TODO
							}
						}
					}
					break;
					
				case MessageKey.I_SHOOT:
					int x ,y;
					
					if(mes.i_data.containsKey(MessageKey.P_X))
					{
						x = mes.i_data.get(MessageKey.P_X);
						if(mes.i_data.containsKey(MessageKey.P_Y))
						{
							y = mes.i_data.get(MessageKey.P_Y);
							
							if(mes.i_data.containsKey(MessageKey.P_VITESSE_X))
							{
								if(mes.i_data.containsKey(MessageKey.P_VITESSE_Y))
								{
									Vector2f vitesse = new Vector2f();
									vitesse.x = mes.i_data.get(MessageKey.P_VITESSE_X);
									vitesse.y = mes.i_data.get(MessageKey.P_VITESSE_Y);
									if(System.currentTimeMillis() - tmp > 100){	// juste pour debug, ce sera pas la plus tard
									Tir t = new TirLinear(1, vitesse, null);
									
									if(mes.o_data.containsKey(MessageKey.P_ENTITY))
									{
										System.out.println("Contient");
										Object b = mes.o_data.get(MessageKey.P_ENTITY);
										if(b instanceof BasicEntity)
											t.setExpediteur((BasicEntity)b);
									}
									
									t.setLocation(x, y);
									engineManager.getIA().addEntity(t); 	// TODO ?? pk 2 fois ?
									engineManager.getCurrentLevelUsed().getArrayEntite().put(t.getId(), t);
									engineManager.getIA().addEntity(t);		// TODO ?? pk 2 fois ?
									Deplacement.deplacerEntity(0, 0, t.getId());
									tmp =  System.currentTimeMillis();
									}
								}
							}
							
							
						}
					}
					break;
				case MessageKey.I_REMOVE_ENTITY:
					if(mes.i_data.containsKey(MessageKey.P_ID))
					{
						engineManager.getCurrentLevelUsed().removeEntity(mes.i_data.get(MessageKey.P_ID));
						engineManager.getIA().removeEntity(mes.i_data.get(MessageKey.P_ID));
						
					}
					break;
				case MessageKey.I_JUMP:
					if(mes.i_data.containsKey(MessageKey.P_ID)){
						BasicEntity tmp = engineManager.getCurrentLevelUsed().getEntity(mes.i_data.get(MessageKey.P_ID));
						if(tmp != null && tmp instanceof PlayableEntity){
							((PlayableEntity)tmp).jump();
						}
					}
					break;
			}
		}
		else
			return false;
		return true;
	}

}
