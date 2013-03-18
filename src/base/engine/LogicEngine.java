package base.engine;


import base.engine.levels.LevelDrol;
import base.engine.logics.Deplacement;

public class LogicEngine extends Engine {

	private CollisionManager c_manager;
	private Deplacement deplacement;
	private LevelDrol lvl;
	
	
	
	public LogicEngine()
	{
		super();
		this.lvl = null;
		c_manager = new CollisionManager(null);
		deplacement = new Deplacement(null);
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
								deplacement.deplacerEntity(x, y, id);
							}
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
