package base.engine;

import java.util.HashMap;

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
	
	/**
	 * Teste si il y a une collision aux coordonnées x,y de l'entitée e
	 */
	public static boolean testerCollision(int x, int y, BasicEntity e)
	{
		LevelDrol lvl = e.getEngineManager().getCurrentLevelUsed();
		
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

	public static HashMap<Integer, BasicEntity> testerCollisionEntites(int x, int y, BasicEntity e)
	{
		LevelDrol lvl = e.getEngineManager().getCurrentLevelUsed();
		HashMap<Integer, BasicEntity> hm = new HashMap<Integer, BasicEntity>();
		
		int tileXMin = (int) ((e.getX()+x) / lvl.getLargeurTile());
	    int tileYMin = (int) ((e.getY()+y) / lvl.getHauteurTile());
	    int tileXMax = (int) ((e.getX() + x + e.getWidth() - 1) / lvl.getLargeurTile());
	    int tileYMax = (int) ((e.getY() + y + e.getHeight() - 1) / lvl.getHauteurTile());
	    
	    for(int i = tileXMin; i <= tileXMax; i++)
	    {
	        for(int j= tileYMin; j <= tileYMax; j++)
	        {
	        	//On vérifie les entités proches
	        	for(int k=0;k<lvl.getTabNiveau()[j][i].getEntiteProcheSize();k++)
	        	{
	        		/*
	        		 * Si on trouve une entité proche et qu'elle est en collision, on l'ajoute dans le hashmap
	        		 */
	        		
	        		BasicEntity b = lvl.getTabNiveau()[j][i].getEntiteProcheAt(k);
	        		
	        		if(e.getShape().intersects(b.getShape()))
	        			hm.put(lvl.getTabNiveau()[j][i].getEntiteProcheAt(k).getId(), lvl.getTabNiveau()[j][i].getEntiteProcheAt(k));
	        		
	        	}
	        }
	    }
	    
	    return hm;
	    
	}
	
	public static boolean isEntityCollidingWithTop(BasicEntity e){
		LevelDrol lvl = e.getEngineManager().getCurrentLevelUsed();
		
		if(e.getY() < 0)
			return true;
		
		int tileXMin, tileXMax, tileYMin;
		tileXMin = (int) (e.getX() / lvl.getLargeurTile());
	    tileXMax = (int) ((e.getX() + e.getWidth() ) / lvl.getLargeurTile());
	    tileYMin = (int) ((e.getY() - 1) / lvl.getHauteurTile());
	    if(tileXMin < 0)
			tileXMin = 0;
	    if(tileXMax > lvl.getLargeurNiveau() - 1)
	    	tileXMax = lvl.getLargeurNiveau() - 1;
	    
    	for(int i = tileXMin; i < tileXMax; i++)
        {
        	//C'est un mur, il y a donc collision
            if (lvl.getTileSet().getCorrespondanceTile().get(lvl.getTabNiveau()[tileYMin][i].getIndex()).isMur()){
            	System.out.println("Colission haut");
                return true;
            }
        }
	    
	    return false;
		
	}
	
	public static boolean isEntityCollidingWithGround(BasicEntity e){
		LevelDrol lvl = e.getEngineManager().getCurrentLevelUsed();
		
		if((e.getY() + e.getHeight()) > lvl.getHauteurNiveau()*lvl.getHauteurTile())
			return true;
		
		int tileXMin, tileXMax, tileYMax;
		tileXMin = (int) (e.getX() / lvl.getLargeurTile());
	    tileXMax = (int) ((e.getX() + e.getWidth() ) / lvl.getLargeurTile());
	    tileYMax = (int) ((e.getY() + e.getHeight() + 1) / lvl.getHauteurTile());
	    if(tileXMin < 0)
			tileXMin = 0;
	    if(tileXMax > lvl.getLargeurNiveau() - 1)
	    	tileXMax = lvl.getLargeurNiveau() - 1;
	    	    
    	for(int i = tileXMin; i <= tileXMax; i++)
        {
        	//C'est un mur, il y a donc collision
            if (lvl.getTileSet().getCorrespondanceTile().get(lvl.getTabNiveau()[tileYMax][i].getIndex()).isMur()){
            	//System.out.println("Colission bas");
                return true;
            }
        }
	    
	    return false;
	}
	
	public static boolean isEntityCollidingWithLeftOrRight(BasicEntity e){
		LevelDrol lvl = e.getEngineManager().getCurrentLevelUsed();
		
		if(e.getX() < 0 || (e.getX() + e.getWidth()) > lvl.getLargeurNiveau()*lvl.getLargeurTile())
			return true;
		
		int tileXMin, tileXMax, tileYMin, tileYMax;
		tileXMin = (int) ((e.getX() - 1) / lvl.getLargeurTile());
	    tileYMin = (int) (e.getY() / lvl.getHauteurTile());
	    tileXMax = (int) ((e.getX() + e.getWidth()) / lvl.getLargeurTile());
	    if(tileXMin < 1)
			tileXMin = 1;
	    if(tileXMax > lvl.getLargeurNiveau() - 1)
	    	tileXMax = lvl.getLargeurNiveau() - 1;
	    tileYMax = (int) ((e.getY() + e.getHeight() ) / lvl.getHauteurTile());
	    
        for(int j= tileYMin; j < tileYMax; j++)
        {
        	//C'est un mur, il y a donc collision
            if (lvl.getTileSet().getCorrespondanceTile().get(lvl.getTabNiveau()[j][tileXMin].getIndex()).isMur()){
            	System.out.println("Colission gauche" + j );
                return true;
            }
            if (lvl.getTileSet().getCorrespondanceTile().get(lvl.getTabNiveau()[j][tileXMax].getIndex()).isMur()){
            	System.out.println("Colission droite");
                return true;
            }
        }
	    
	    return false;
		
	}

}
