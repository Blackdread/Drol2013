package base.engine;


import base.engine.entities.Tir;
import base.engine.levels.LevelDrol;
import base.engine.logics.Deplacement;

/**
 * 
 * @author Yoann CAPLAIN
 * @author Nicolas DUPIN
 */
public class LogicEngine extends Engine {

	private CollisionManager c_manager;
	private LevelDrol lvl;	// TODO A enlever ? et recuperer seulement quand necessaire
	
	
	
	public LogicEngine()
	{
		super();
		this.lvl = null;
		c_manager = CollisionManager.getInstance();
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
					
				case MessageKey.I_SHOOT:
					int x ,y;
					
					if(mes.i_data.containsKey(MessageKey.P_X))
					{
						x = mes.i_data.get(MessageKey.P_X);
						if(mes.i_data.containsKey(MessageKey.P_Y))
						{
							y = mes.i_data.get(MessageKey.P_Y);
							Tir t = new Tir("tirHero", 1);
							
							t.setLocation(x, y);
							lvl.getArrayEntite().put(t.getId(), t);
							Deplacement.deplacerEntity(x, y, t.getId());
						}
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
