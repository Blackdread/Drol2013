package base.engine.logics;

import base.engine.CollisionManager;
import base.engine.entities.BasicEntity;
import base.engine.entities.HeroEntity;
import base.engine.levels.Level;
import base.engine.levels.LevelDrol;

/**
 * 
 * @author Yoann CAPLAIN
 * @author Nicolas DUPIN
 */
public class Deplacement {

	//private static CollisionManager c_manager;
	//private static LevelDrol lvl;
	
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
			
			
			//On v�rifie qu'il n'y a pas de collision
			//if(!c_manager.testerCollision(x, y, e))
			if(!CollisionManager.getInstance().testerCollision(x, y, e))
			{
				//System.out.println(ex + " " + ey);
				
				//Enlever l'entit� des tiles avant le d�placement
				for(int i = ex/lvl.getLargeurTile(); i < (ex + largeur)/lvl.getLargeurTile(); i++)
				{
					for(int j = ey/lvl.getHauteurTile(); j < (ey + hauteur)/lvl.getHauteurTile(); j++)
					{
						lvl.getTabNiveau()[j][i].enleverEntite(id);
					}
				}
				
				//On d�place l'entit�
				e.setLocation((float)(x + e.getX()), (float)(y + e.getY()));
				lvl.getArrayEntite().put(id, e);
				
				//On replace l'entit� dans les tiles
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
				
				//Si l'entit� est le h�ro, il faut mettre � jour le scroll
				if(e instanceof HeroEntity)
				{
					//Si le scroll sort de l'�cran on met � 0 ou max sinon on centre sur le h�ro
					if((x + e.getX()-(lvl.getScroll().getWidth()/2)) < 0)
						lvl.getScroll().setxScroll(0);
					else if((x + e.getX() + (lvl.getScroll().getWidth()/2)) > (lvl.getLargeurNiveau()*lvl.getLargeurTile()))
						lvl.getScroll().setxScroll(lvl.getLargeurNiveau()*lvl.getLargeurTile()-lvl.getScroll().getWidth());
					else
						lvl.getScroll().setxScroll((int) ((x + e.getX()-(lvl.getScroll().getWidth()/2))));	
				}
			}
			else
				System.out.println("Collision");
		}else{
			System.out.println("Fonction deplacer Entity : Entit� non trouve, e = null");
		}
	}
	
	private Deplacement(){
		
	}
}
