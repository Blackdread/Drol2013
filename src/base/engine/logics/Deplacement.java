package base.engine.logics;

import base.engine.CollisionManager;
import base.engine.entities.BasicEntity;
import base.engine.levels.LevelDrol;

public class Deplacement {
	
	private LevelDrol lvl;
	private CollisionManager c_manager;
	
	public Deplacement(LevelDrol lvl)
	{
		this.lvl = lvl;
	}
	
	public void deplacerEntity(int x, int y, int id)
	{
		BasicEntity e = lvl.getArrayEntite().get(id);
		int largeur = e.getWidth();
		int hauteur = e.getHeight();
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		
		
		//On v�rifie qu'il n'y a pas de collision
		if(!c_manager.testerCollision(x, y, e))
		{
			//Enlever l'entit� des tiles avant le d�placement
			for(int i = ex/lvl.getLargeurTile(); i < (ex + largeur)/lvl.getLargeurTile(); i++)
			{
				for(int j = ey/lvl.getHauteurTile(); j < (ey + hauteur)/lvl.getHauteurTile(); j++)
					lvl.getTabNiveau()[i][j].enleverEntite(id);
			}
			
			//On d�place l'entit�
			e.setLocation((float) x, (float) y);
			lvl.getArrayEntite().put(id, e);
			
			//On replace l'entit� dans les tiles
			for(int i = x/lvl.getLargeurTile(); i < (x + largeur)/lvl.getLargeurTile(); i++)
			{
				for(int j = y/lvl.getHauteurTile(); j < (y + hauteur)/lvl.getHauteurTile(); j++)
					lvl.getTabNiveau()[i][j].ajouterEntite(e);
			}
		}		
	}
}
