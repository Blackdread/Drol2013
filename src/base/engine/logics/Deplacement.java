package base.engine.logics;

import java.util.HashMap;
import java.util.Map.Entry;

import base.engine.CollisionManager;
import base.engine.EngineManager;
import base.engine.Message;
import base.engine.MessageKey;
import base.engine.entities.BasicEntity;
import base.engine.entities.MoveableEntity;
import base.engine.entities.HeroEntity;
import base.engine.entities.ICollidableObject;
import base.engine.entities.Tir;
import base.engine.entities.others.triggers.Trigger;
import base.engine.entities.others.triggers.TriggerObjectInZone;
import base.engine.levels.Level;
import base.engine.levels.LevelDrol;

/**
 * 
 * @author Yoann CAPLAIN
 * @author Nicolas DUPIN
 */
public class Deplacement {
	
	public static void deplacerEntity(int x, int y, int id)
	{
		LevelDrol lvl = (LevelDrol) Level.getCurrentLevelUsed();
		
		BasicEntity e = lvl.getArrayEntite().get(id);
		
		
		
		if(e != null){
			/* V2
			 * 
			 */
			
			/*
			 * Si le déplacement est trop rapide on le découpe en deux. Cela evite de traverser des tiles
			 * Algo recursif à mettre dans le dossier !
			 */
			
			if(x >= lvl.getLargeurTile() || y >= lvl.getHauteurTile())
			{
				deplacerEntity(x/2, y/2, id);
				deplacerEntity(x-x/2, y-y/2, id);
			}
			else{
				/*
				 * Le déplacement n'est pas trop grand on déplace selon les y puis selons les x
				 */
				gererCollisionEntity(x, y, e);
				
				deplacerEntityY(y,e);
				deplacerEntityX(x,e);
			}
		}else{
			System.err.println("Fonction deplacer Entity : Entité non trouve, e = null");
		}
	}
	
	private static void gererCollisionEntity(int x, int y, BasicEntity e)
	{
		HashMap<Integer, BasicEntity> hm = CollisionManager.testerCollisionEntites(x, y, e);
		
		if(!hm.isEmpty())
		{
			for(Entry<Integer, BasicEntity> entry : hm.entrySet()) {
			    BasicEntity b = entry.getValue();
			    
			    if(b != e)
			    {
			    	if(b instanceof Trigger){
	        			 ((TriggerObjectInZone)b).addAnEntityToActON(e);
	        			 System.out.println("ajouter dans Trigger");
	        		 }
	        		 else
	        		 {
	        			 if(b instanceof ICollidableObject && e instanceof ICollidableObject)
	        				 ((ICollidableObject)b).onCollision((ICollidableObject)e);
	        		 }
			    }
			}
		}
		
	}
	
	private static void deplacerEntityX(int x,BasicEntity e){
		
		/*
		 * Si l'on n'est pas en collision on déplace
		 */
		if(!CollisionManager.testerCollision(x, 0, e)){
			
			//Enlever l'entité des tiles avant le déplacement
			enleverEntiteDesTiles(e);
			
			//On déplace l'entité
			e.setLocation((float)(x + e.getX()), e.getY());
			
			//On replace l'entité dans les tiles
			ajouterEntiteDansTiles(e);
			
			if(e instanceof HeroEntity)
				mettreAJourScroll(e);
		}
		else if(x != 0)
		{
			/*
			 * On affine le deplacement afin que l'on colle au mur
			 * Possibilité d'optimisation
			 */
			deplacerEntityX(x/2,e);
			if(e instanceof ICollidableObject)
				((ICollidableObject)e).onCollision(null);
		}
	}
	private static void deplacerEntityY(int y,BasicEntity e){
		
		/*
		 * Si l'on n'est pas en collision on déplace
		 */
		if(!CollisionManager.testerCollision(0, y, e)){
			
			//Enlever l'entité des tiles avant le déplacement
			enleverEntiteDesTiles(e);
			
			//On déplace l'entité
			e.setLocation(e.getX(), e.getY() + y);
			
			//On replace l'entité dans les tiles
			ajouterEntiteDansTiles(e);
			if(e instanceof HeroEntity)
				mettreAJourScroll(e);
		}
		else if(y != 0)
		{
			//On affine le deplacement afin que l'on colle au mur
			deplacerEntityY(y/2,e);

			if(e instanceof ICollidableObject)
				((ICollidableObject)e).onCollision(null);
		}
	}
	
	private Deplacement(){
		
	}
	
	/**
	 * Enleve l'entite des tiles o� elle se trouve
	 * @param e entite a enlever des tiles qui la contiennent
	 */
	public static void enleverEntiteDesTiles(final BasicEntity e){
		LevelDrol lvl = (LevelDrol) Level.getCurrentLevelUsed();
		
		int larg = lvl.getLargeurTile();
		int haut = lvl.getHauteurTile();
		int x = (int)e.getX();
		int y = (int)e.getY();
		
		for(int j = y/haut ; j <= (y + e.getHeight())/haut ; j++)
			for(int i = x/larg ; i <= (x + e.getWidth())/larg ; i++){
				lvl.getTabNiveau()[j][i].enleverEntite(e.getId());
				//System.out.println("enlever ("+j+","+i+") "+e.getId());
			}
		//System.out.println("fin enlever");
	}
	
	/**
	 * Ajoute l'entite dans les tiles qu'il faut (partout o� l'entite touche la tile)
	 * @param e entite a ajouter
	 */
	public static void ajouterEntiteDansTiles(final BasicEntity e){
		LevelDrol lvl = (LevelDrol) Level.getCurrentLevelUsed();
		
		int larg = lvl.getLargeurTile();
		int haut = lvl.getHauteurTile();
		int x = (int)e.getX();
		int y = (int)e.getY();
		
		for(int j = y/haut ; j <= (y + e.getHeight())/haut ; j++)
			for(int i = x/larg ; i <= (x + e.getWidth())/larg ; i++){
				lvl.getTabNiveau()[j][i].ajouterEntite(e);
				//System.out.println("ajouter ("+j+","+i+") "+e.getId());
			}
		//System.out.println("fin ajouter");
	}
	
	/**
	 * Si le scroll sort de l'écran on met à 0 ou max sinon on centre sur le héro
	 * @param e entite sur laquelle centre le scroll
	 */
	public static void mettreAJourScroll(final BasicEntity e){
		LevelDrol lvl = (LevelDrol) Level.getCurrentLevelUsed();
		
		lvl.getScroll().mettreAJourScroll(e);
	}
}
