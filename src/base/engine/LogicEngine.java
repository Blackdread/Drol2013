package base.engine;


import base.engine.levels.LevelDrol;
import base.engine.logics.Deplacement;

public class LogicEngine extends Engine {

	private CollisionManager c_manager;
	private Deplacement deplacement;
	private LevelDrol lvl;
	
	public LogicEngine(LevelDrol lvl)
	{
		super();
		this.lvl = lvl;
		c_manager = new CollisionManager(lvl);
		deplacement = new Deplacement(lvl);
	}
	
	@Override
	public boolean processMessage() {
		
	
		Message mes;
		//while(!this.message_queue.isEmpty()){
		
		if(!this.message_queue.isEmpty()){
			mes = this.message_queue.poll();
			switch(mes.instruction){
			
				case MessageKey.I_MOVE_ENTITY:
					if(mes.i_data.contains(MessageKey.P_ID))
					{
						int id, x, y;
						id = mes.i_data.get(MessageKey.P_ID);
						if(mes.i_data.contains(MessageKey.P_X))
						{
							x = mes.i_data.get(MessageKey.P_X);
							if(mes.i_data.contains(MessageKey.P_Y))
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
}
