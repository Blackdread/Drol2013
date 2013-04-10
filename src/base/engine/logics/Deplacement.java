package base.engine.logics;

import base.engine.CollisionManager;
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
		
		if(e != null)
		{
			int largeur = e.getWidth();
			int hauteur = e.getHeight();
			int ex = (int) e.getX();
			int ey = (int) e.getY();
			
			
			//On vÈrifie qu'il n'y a pas de collision
			if(!CollisionManager.getInstance().testerCollision(x, y, e))
			{
				//System.out.println(ex + " " + ey);
				
				//Enlever l'entitÈ des tiles avant le dÈplacement
				for(int i = ex/lvl.getLargeurTile(); i < (ex + largeur)/lvl.getLargeurTile(); i++)
				{
					for(int j = ey/lvl.getHauteurTile(); j < (ey + hauteur)/lvl.getHauteurTile(); j++)
					{
						lvl.getTabNiveau()[j][i].enleverEntite(id);
					}
				}
				
				//On dÈplace l'entitÈ
				e.setLocation((float)(x + e.getX()), (float)(y + e.getY()));
				lvl.getArrayEntite().put(id, e);
				
				//On replace l'entitÈ dans les tiles
				for(int i = (int) ((x + e.getX())/lvl.getLargeurTile()); i < ((x + e.getX()) + largeur)/lvl.getLargeurTile(); i++)
				{
					for(int j = (int) ((y + e.getY())/lvl.getHauteurTile()); j < ((y + e.getY()) + hauteur)/lvl.getHauteurTile(); j++)
					{
						//System.out.println(i + " " + j);
						lvl.getTabNiveau()[j][i].ajouterEntite(e);
						//for(int k = 0; k < lvl.getTabNiveau()[j][i].getEntiteProche().size(); k++)
						//if(lvl.getTabNiveau()[j][i].getEntiteProche().get(i) instanceof Trigger)
						//{
							
						//}
							
					}
				}
				
				//Si l'entitÈ est le hÈro, il faut mettre ‡ jour le scroll
				if(e instanceof HeroEntity)
				{
					//Si le scroll sort de l'Ècran on met ‡ 0 ou max sinon on centre sur le hÈro
					if((x + e.getX()-(lvl.getScroll().getWidth()/2)) < 0)
						lvl.getScroll().setxScroll(0);
					else if((x + e.getX() + (lvl.getScroll().getWidth()/2)) > (lvl.getLargeurNiveau()*lvl.getLargeurTile()))
						lvl.getScroll().setxScroll(lvl.getLargeurNiveau()*lvl.getLargeurTile()-lvl.getScroll().getWidth());
					else
						lvl.getScroll().setxScroll((int) ((x + e.getX()-(lvl.getScroll().getWidth()/2))));	
				}
			}
			else{
				System.out.println("Collision");
				if(e instanceof ICollidableObject)
					((ICollidableObject)e).onCollision(null);	// TODO
			}
		}else{
			System.out.println("Fonction deplacer Entity : EntitÈ non trouve, e = null");
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
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		
		for(int i = ex/lvl.getLargeurTile(); i < (ex + e.getWidth())/lvl.getLargeurTile(); i++)
		{
			for(int j = ey/lvl.getHauteurTile(); j < (ey + e.getHeight())/lvl.getHauteurTile(); j++)
			{
				lvl.getTabNiveau()[j][i].enleverEntite(e.getId());
			}
		}
	}
	
	/**
	 * Ajoute l'entite dans les tiles qu'il faut (partout où l'entite touche la tile)
	 * @param e entite a ajouter
	 * @param x 
	 * @param y 
	 */
	public static void ajouterEntiteDansTiles(final BasicEntity e, final int x, final int y){
		LevelDrol lvl = (LevelDrol) Level.getCurrentLevelUsed();
		
		for(int i = (int) ((x + e.getX())/lvl.getLargeurTile()); i < ((x + e.getX()) + e.getWidth())/lvl.getLargeurTile(); i++)
		{
			for(int j = (int) ((y + e.getY())/lvl.getHauteurTile()); j < ((y + e.getY()) + e.getHeight())/lvl.getHauteurTile(); j++)
			{
				lvl.getTabNiveau()[j][i].ajouterEntite(e);
			}
		}
	}
	
	/**
	 * Si le scroll sort de l'Ècran on met ‡ 0 ou max sinon on centre sur le hÈro
	 * @param e entite sur laquelle centre le scroll
	 * @param x c quoi ?
	 */
	public static void mettreAJourScroll(final BasicEntity e, final int x){
		LevelDrol lvl = (LevelDrol) Level.getCurrentLevelUsed();
		
		if((x + e.getX()-(lvl.getScroll().getWidth()/2)) < 0)
			lvl.getScroll().setxScroll(0);
		else if((x + e.getX() + (lvl.getScroll().getWidth()/2)) > (lvl.getLargeurNiveau()*lvl.getLargeurTile()))
			lvl.getScroll().setxScroll(lvl.getLargeurNiveau()*lvl.getLargeurTile()-lvl.getScroll().getWidth());
		else
			lvl.getScroll().setxScroll((int) ((x + e.getX()-(lvl.getScroll().getWidth()/2))));	
	}
}
