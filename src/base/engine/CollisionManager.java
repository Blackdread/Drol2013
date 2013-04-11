package base.engine;

import base.engine.entities.BasicEntity;
import base.engine.entities.ICollidableObject;
import base.engine.entities.others.triggers.Trigger;
import base.engine.entities.others.triggers.TriggerObjectInZone;
import base.engine.entities.others.triggers.TriggerTeleport;
import base.engine.levels.Level;
import base.engine.levels.LevelDrol;

/**
 * Les methodes peuvent devenir static -> A voir
 * @author Yoann CAPLAIN
 * @author Nicolas DUPIN
 */
public class CollisionManager{

	private static CollisionManager instance;
	
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
	
	/**
	 * Teste si il y a une collision aux coordonnées x,y de l'entitée e
	 */
	public boolean testerCollision(int x, int y, BasicEntity e)
	{
		LevelDrol lvl = (LevelDrol) Level.getCurrentLevelUsed();
		
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
	    for(int i = tileXMin; i <= tileXMax; i++)
	    {
	        for(int j= tileYMin; j <= tileYMax; j++)
	        {
	        	 for(int k=0;k<lvl.getTabNiveau()[j][i].getEntiteProcheSize();k++)
	        		 if(lvl.getTabNiveau()[j][i].getEntiteProcheAt(k) instanceof Trigger){
	        			 ((TriggerObjectInZone)lvl.getTabNiveau()[j][i].getEntiteProcheAt(k)).addAnEntityToActON(e);
	        			 System.out.println("ajouter dans Trigger");
	        		 }
	        }
	    }
	   
		
		return false;
	}

	
	public static boolean isEntityCollidingWithGround(BasicEntity e){
		LevelDrol lvl = (LevelDrol) Level.getCurrentLevelUsed();
		
		if((e.getY() + e.getHeight()) > lvl.getHauteurNiveau()*lvl.getHauteurTile())
			return true;
		
		int tileXMin, tileXMax, tileYMax;
		tileXMin = (int) (e.getX() / lvl.getLargeurTile());
	    tileXMax = (int) ((e.getX() + e.getWidth() ) / lvl.getLargeurTile());
	    tileYMax = (int) ((e.getY() + e.getHeight() ) / lvl.getHauteurTile());
	    
    	for(int i = tileXMin; i <= tileXMax; i++)
        {
        	//C'est un mur, il y a donc collision
            if (lvl.getTileSet().getCorrespondanceTile().get(lvl.getTabNiveau()[tileYMax][i].getIndex()).isMur())
                return true;
        }
	    
	    return false;
	}
	
	public static boolean isEntityCollidingWithLeftOrRight(BasicEntity e){
		LevelDrol lvl = (LevelDrol) Level.getCurrentLevelUsed();
		
		if(e.getX() < 0 || (e.getX() + e.getWidth()) > lvl.getLargeurNiveau()*lvl.getLargeurTile())
			return true;
		
		int tileXMin, tileXMax, tileYMin, tileYMax;
		tileXMin = (int) (e.getX() / lvl.getLargeurTile());
		if(tileXMin < 1)
			tileXMin = 1;
	    tileYMin = (int) (e.getY() / lvl.getHauteurTile());
	    tileXMax = (int) ((e.getX() + e.getWidth() ) / lvl.getLargeurTile());
	    if(tileXMax > lvl.getLargeurNiveau() - 1)
	    	tileXMax = lvl.getLargeurNiveau() - 1;
	    tileYMax = (int) ((e.getY() + e.getHeight() ) / lvl.getHauteurTile());
	    
        for(int j= tileYMin; j <= tileYMax; j++)
        {
        	//C'est un mur, il y a donc collision
            if (lvl.getTileSet().getCorrespondanceTile().get(lvl.getTabNiveau()[j][tileXMin].getIndex()).isMur())
                return true;
            if (lvl.getTileSet().getCorrespondanceTile().get(lvl.getTabNiveau()[j][tileXMax].getIndex()).isMur())
                return true;
        }
	    
	    return false;
		
	}
	
	 private CollisionManager(){
		 
	 }
	 
	 private static Object objetSynchrone = new Object();
}
