package base.engine;

import base.utils.StatsSerializable;
import base.utils.Vector2f;
import base.engine.entities.BasicEntity;
import base.engine.entities.HeroEntity;
import base.engine.entities.MoveableEntity;
import base.engine.entities.PlayableEntity;
import base.engine.entities.Tir;
import base.engine.logics.Deplacement;

/**
 * 
 * @author Yoann CAPLAIN
 * @author Nicolas DUPIN
 * @since 19/03/2013
 */
public class LogicEngine extends Engine {
	
	public LogicEngine(EngineManager engineManager)
	{
		super(engineManager);
	}
	
	@Override
	synchronized public boolean processMessage() {
		Message mes;
		//while(!this.message_queue.isEmpty()){
		
		if(!this.message_queue.isEmpty()){
			mes = (Message) this.message_queue.poll();
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
								Deplacement.deplacerEntity(engineManager,x, y, id);
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
								
								//L'unit� se d�place
								if(mes.b_data.containsKey(MessageKey.P_BOOLEAN))
								{
									((MoveableEntity) tmp).setMoving(mes.b_data.get(MessageKey.P_BOOLEAN));
								}
							}
					}
					break;
					
				case MessageKey.I_STOP_ENTITY:
					if(mes.i_data.containsKey(MessageKey.P_ID))
					{
						BasicEntity tmp = engineManager.getCurrentLevelUsed().getEntity(mes.i_data.get(MessageKey.P_ID));
						if(tmp instanceof MoveableEntity)
						{
							((MoveableEntity)tmp).setVitesseToZero();
							if(mes.b_data.containsKey(MessageKey.P_BOOLEAN))
							{
								((MoveableEntity) tmp).setMoving(mes.b_data.get(MessageKey.P_BOOLEAN));
							}
						}
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
								// TODO est ce bon ? normalement oui
								BasicEntity tmp = engineManager.getCurrentLevelUsed().getEntity(id);
								if(tmp != null){
									Deplacement.enleverEntiteDesTiles(tmp);
									tmp.setLocation(x, y);
									Deplacement.ajouterEntiteDansTiles(tmp);
								}
								
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
									
									
									Tir t = null;//= new TirLinear(1, engineManager, vitesse, null);
									
									if(mes.o_data.containsKey(MessageKey.P_ENTITY))
									{
										//System.out.println("Contient");
										Object b = mes.o_data.get(MessageKey.P_ENTITY);
										if(b instanceof HeroEntity)
											t = ((HeroEntity)b).getWeapon().shoot();
										
										if(b instanceof BasicEntity)
											if(t != null)
											t.setExpediteur((BasicEntity)b);
									}
									if(t != null){
										t.setEngineManager(engineManager);
										
										t.setVitesse(vitesse);
										t.setLocation(x, y);
										engineManager.getIA().addEntity(t);
										engineManager.getCurrentLevelUsed().getArrayEntite().put(t.getId(), t);
										Deplacement.deplacerEntity(engineManager,0, 0, t.getId());
										
										if(!engineManager.isPlayingMulti()){
											StatsSerializable stats = new StatsSerializable("config/statsSolo.seria");
											stats.loadStats();
											
											stats.addStat("Nb de tirs", 1);
											
											stats.saveStats();
										}
									}//else
										//System.out.println("NULLLLL");
								}
							}
						}
					}
					break;
					
				case MessageKey.I_REMOVE_ENTITY:
					if(mes.i_data.containsKey(MessageKey.P_ID))
					{
						//engineManager.getCurrentLevelUsed().removeEntity(mes.i_data.get(MessageKey.P_ID));
						//engineManager.getIA().removeEntity(mes.i_data.get(MessageKey.P_ID));
						engineManager.removeEntity(mes.i_data.get(MessageKey.P_ID));
						
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
