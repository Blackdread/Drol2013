package base.engine.logics;

import base.engine.CollisionManager;
import base.engine.EngineManager;
import base.engine.Message;
import base.engine.MessageKey;
import base.engine.entities.BasicEntity;
import base.engine.entities.HeroEntity;
import base.engine.entities.ICollidableObject;
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
			//On vÈrifie qu'il n'y a pas de collision
			if(!CollisionManager.getInstance().testerCollision(x, y, e)){
				
				//Enlever l'entitÈ des tiles avant le dÈplacement
				enleverEntiteDesTiles(e);
				
				//On dÈplace l'entitÈ
				e.setLocation((float)(x + e.getX()), (float)(y + e.getY()));
				
				//On replace l'entitÈ dans les tiles
				ajouterEntiteDansTiles(e);
				
				//Si l'entitÈ est le hÈro, il faut mettre ‡ jour le scroll
				if(e instanceof HeroEntity)
					mettreAJourScroll(e);
			}
			else{
				deplacerEntityX(x,e);
				deplacerEntityY(y,e);
				System.out.println("Collision");
				if(e instanceof ICollidableObject)
					((ICollidableObject)e).onCollision(null);	// TODO
			}
		}else{
			System.err.println("Fonction deplacer Entity : EntitÈ non trouve, e = null");
		}
	}
	
	private static void deplacerEntityX(int x,BasicEntity e){
		if(!CollisionManager.getInstance().testerCollision(x, 0, e)){
			
			//Enlever l'entitÈ des tiles avant le dÈplacement
			enleverEntiteDesTiles(e);
			
			//On dÈplace l'entitÈ
			e.setLocation((float)(x + e.getX()), e.getY());
			
			//On replace l'entitÈ dans les tiles
			ajouterEntiteDansTiles(e);
			if(e instanceof HeroEntity)
				mettreAJourScroll(e);
		}
		else{
			if(x != 0)
				deplacerEntityX(x/2,e);
		}
	}
	private static void deplacerEntityY(int y,BasicEntity e){
		if(!CollisionManager.getInstance().testerCollision(0, y, e)){
			
			//Enlever l'entitÈ des tiles avant le dÈplacement
			enleverEntiteDesTiles(e);
			
			//On dÈplace l'entitÈ
			e.setLocation(e.getX(), e.getY() + y);
			
			//On replace l'entitÈ dans les tiles
			ajouterEntiteDansTiles(e);
			if(e instanceof HeroEntity)
				mettreAJourScroll(e);
		}
		else{
			if(y != 0){
				deplacerEntityY(y/2,e);
			}
				
		}
	}
	
	private Deplacement(){
		
	}
	
	/**
	 * Enleve l'entite des tiles où elle se trouve
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
	 * Ajoute l'entite dans les tiles qu'il faut (partout où l'entite touche la tile)
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
	 * Si le scroll sort de l'Ècran on met ‡ 0 ou max sinon on centre sur le hÈro
	 * @param e entite sur laquelle centre le scroll
	 */
	public static void mettreAJourScroll(final BasicEntity e){
		LevelDrol lvl = (LevelDrol) Level.getCurrentLevelUsed();
		
		lvl.getScroll().mettreAJourScroll(e);
	}
}
