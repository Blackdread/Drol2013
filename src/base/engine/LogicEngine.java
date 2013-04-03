package base.engine;


import base.engine.entities.Tir;
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
		deplacement = new Deplacement(null, c_manager);
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
					
				case MessageKey.I_SHOOT:
					if(mes.i_data.containsKey(MessageKey.P_ID))
					{
						int id,x ,y;
						id = mes.i_data.get(MessageKey.P_ID);
						if(mes.i_data.containsKey(MessageKey.P_X))
						{
							x = mes.i_data.get(MessageKey.P_X);
							if(mes.i_data.containsKey(MessageKey.P_Y))
							{
								y = mes.i_data.get(MessageKey.P_Y);
								Tir t = new Tir("tirHero", 10, 1);
								t.setLocation(x, y);
								lvl.getArrayEntite().put(t.getId(), t);
								
								for(int i = (int) (x/lvl.getLargeurTile()); i < (x + t.getWidth())/lvl.getLargeurTile(); i++)
								{
									for(int j = (int) ((y)/lvl.getHauteurTile()); j < ((y + t.getHeight())/lvl.getHauteurTile()); j++)
									{
										lvl.getTabNiveau()[j][i].ajouterEntite(t);
									}
								}
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
		c_manager.setLvl(lvl);
		deplacement.setLvl(lvl);
	}
}
