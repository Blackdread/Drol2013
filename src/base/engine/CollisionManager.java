package base.engine;

import base.engine.entities.BasicEntity;
import base.engine.levels.LevelDrol;

public class CollisionManager {
	private LevelDrol lvl;
	
	public CollisionManager(LevelDrol niv)
	{
		lvl = niv;
	}
	
	/*
	 * Teste si il y a une collision aux coordonnées x,y de l'entitée e
	 */
	public boolean testerCollision(int x, int y, BasicEntity e)
	{
		int tileXMin, tileXMax, tileYMin, tileYMax;
		
		//Si on sort de la map, on a une collision
		if((e.getX() + x) < 0 || (e.getX() + x + e.getWidth()) > lvl.getLargeurNiveau()*lvl.getLargeurTile()
				|| (e.getY() + y) < 0 || (e.getY() + y + e.getHeight()) > lvl.getHauteurNiveau()*lvl.getHauteurTile())
				return true;
				
		tileXMin = (int) (e.getX() / lvl.getLargeurTile());
	    tileYMin = (int) (e.getY() / lvl.getHauteurTile());
	    tileXMax = (int) ((e.getX() + e.getWidth() - 1) / lvl.getLargeurTile());
	    tileYMax = (int) ((e.getY() + e.getHeight() - 1) / lvl.getHauteurTile());
	    
	    for(int i = tileXMin; i <= tileXMax; i++)
	    {
	        for(int j= tileYMin; j <= tileYMax; j++)
	        {
	        	//C'est un mur, il y a donc collision
	            if (lvl.getTileSet().getCorrespondanceTile().get(lvl.getTabNiveau()[i][j].getIndex()).isMur())
	                return true;
	        }
	    }
		
		return false;
	}

	public LevelDrol getLvl() {
		return lvl;
	}

	public void setLvl(LevelDrol lvl) {
		this.lvl = lvl;
	}

}
