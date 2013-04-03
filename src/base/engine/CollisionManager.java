package base.engine;

import base.engine.entities.BasicEntity;
import base.engine.levels.Level;
import base.engine.levels.LevelDrol;

/**
 * Les methodes peuvent devenir static -> A voir
 * @author Yoann CAPLAIN
 * @author Nicolas DUPIN
 */
public class CollisionManager{

	private static CollisionManager instance;
	//private LevelDrol lvl = (LevelDrol) Level.getCurrentLevelUsed();
	// TODO necessite de change la reference si le niveau change ou la 2eme methode
	
	public static CollisionManager getInstance() {
		if (null == instance) { // Premier appel
            synchronized(objetSynchrone) {
                if (null == instance) {
                    instance = new CollisionManager();
                }
            }
        }
        return instance;
	 }
	
	/*
	 * Teste si il y a une collision aux coordonnées x,y de l'entitée e
	 */
	public boolean testerCollision(int x, int y, BasicEntity e)
	{
		LevelDrol lvl = (LevelDrol) Level.getCurrentLevelUsed();	// TODO ‚a ou la 2eme methode mais necessite de change la reference si le niveau change
		
		int tileXMin, tileXMax, tileYMin, tileYMax;
		
		//Si on sort de la map, on a une collision
		if((e.getX() + x) < 0 || (e.getX() + x + e.getWidth()) > lvl.getLargeurNiveau()*lvl.getLargeurTile()
				|| (e.getY() + y) < 0 || (e.getY() + y + e.getHeight()) > lvl.getHauteurNiveau()*lvl.getHauteurTile())
				{return true;}
				
		tileXMin = (int) ((e.getX()+x) / lvl.getLargeurTile());
	    tileYMin = (int) ((e.getY()+y) / lvl.getHauteurTile());
	    tileXMax = (int) ((e.getX() + x + e.getWidth() - 1) / lvl.getLargeurTile());
	    tileYMax = (int) ((e.getY() + y + e.getHeight() - 1) / lvl.getHauteurTile());
	    
	    for(int i = tileXMin; i <= tileXMax; i++)
	    {
	        for(int j= tileYMin; j <= tileYMax; j++)
	        {
	        	//C'est un mur, il y a donc collision
	            if (lvl.getTileSet().getCorrespondanceTile().get(lvl.getTabNiveau()[j][i].getIndex()).isMur())
	                return true;
	        }
	    }
		
		return false;
	}

	
	 private CollisionManager(){
		 
	 }
	 
	 private static Object objetSynchrone = new Object();
}
