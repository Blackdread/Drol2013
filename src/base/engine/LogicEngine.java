package base.engine;

import org.newdawn.slick.geom.Vector2f;

import base.engine.entities.BasicEntity;
import base.engine.entities.Tir;
import base.engine.levels.LevelDrol;
import base.engine.logics.Deplacement;
import base.engine.logics.IA;

/**
 * 
 * @author Yoann CAPLAIN
 * @author Nicolas DUPIN
 */
public class LogicEngine extends Engine {

	private CollisionManager c_manager;
	private LevelDrol lvl;	// TODO A enlever ? et recuperer seulement quand necessaire
	public static long tmp = System.currentTimeMillis();
	
	public LogicEngine()
	{
		super();
		this.lvl = null;
		c_manager = CollisionManager.getInstance();
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
									if(System.currentTimeMillis() - tmp > 500){	// juste pour debug, ce sera pas la plus tard
									Tir t = new Tir(1, vitesse);
									t.setLocation(x, y);
									IA.getInstance().addEntity(t);
									lvl.getArrayEntite().put(t.getId(), t);
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
						lvl.removeEntity(mes.i_data.get(MessageKey.P_ID));
						IA.getInstance().removeEntity(mes.i_data.get(MessageKey.P_ID));
						
					}
					break;
			}
		}
		else
			return false;
		return true;
	}

	public LevelDrol getLvl() {
		return lvl;
	}

	public void setLvl(LevelDrol lvl) {
		this.lvl = lvl;
	}
}
