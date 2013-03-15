package base.engine;

import base.engine.entities.BasicEntity;
import base.engine.levels.LevelDrol;

public class LogicEngine extends Engine {

	private CollisionManager c_manager;
	private LevelDrol lvl;
	
	public LogicEngine(LevelDrol lvl)
	{
		super();
		this.lvl = lvl;
		c_manager = new CollisionManager(lvl);
	}
	
	@Override
	public boolean processMessage() {
		

		return true;
	}
	
	public void deplacerEntity(int x, int y, int id)
	{
		BasicEntity e = lvl.getArrayEntite().get(id);
		
		//On v�rifie qu'il n'y a pas de collision
		if(!c_manager.testerCollision(x, y, e))
		{
			/*
			 * TODO: Enlever l'entit� des tiles pr�c�dentes pour la remettre dans les tiles apres d�placement
			 */
			
			//On d�place l'entit� et on la replace dans la liste
			e.setLocation((float) x, (float) y);
			lvl.getArrayEntite().put(id, e);
		}		
	}

}
