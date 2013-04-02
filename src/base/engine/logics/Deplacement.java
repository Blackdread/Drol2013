package base.engine.logics;

import base.engine.CollisionManager;
import base.engine.entities.BasicEntity;
import base.engine.entities.HeroEntity;
import base.engine.levels.LevelDrol;

public class Deplacement {
	
	private LevelDrol lvl;
	private CollisionManager c_manager;
	
	public Deplacement(LevelDrol lvl, CollisionManager c)
	{
		this.lvl = lvl;
		c_manager = c;
	}
	
	public void deplacerEntity(int x, int y, int id)
	{
		BasicEntity e = lvl.getArrayEntite().get(id);
		
		if(e != null)
		{
			int largeur = e.getWidth();
			int hauteur = e.getHeight();
			int ex = (int) e.getX();
			int ey = (int) e.getY();
			
			
			//On vérifie qu'il n'y a pas de collision
			if(!c_manager.testerCollision(x, y, e))
			{
				System.out.println(ex + " " + ey);
				
				//Enlever l'entité des tiles avant le déplacement
				for(int i = ex/lvl.getLargeurTile(); i <= (ex + largeur)/lvl.getLargeurTile(); i++)
				{
					for(int j = ey/lvl.getHauteurTile(); j <= (ey + hauteur)/lvl.getHauteurTile(); j++)
					{
						lvl.getTabNiveau()[j][i].enleverEntite(id);
					}
				}
				
				//On déplace l'entité
				e.setLocation((float)(x + e.getX()), (float)(y + e.getY()));
				lvl.getArrayEntite().put(id, e);
				
				//On replace l'entité dans les tiles
				for(int i = (int) ((x + e.getX())/lvl.getLargeurTile()); i <= ((x + e.getX()) + largeur)/lvl.getLargeurTile(); i++)
				{
					for(int j = (int) ((y + e.getY())/lvl.getHauteurTile()); j <= ((y + e.getY()) + hauteur)/lvl.getHauteurTile(); j++)
					{
						System.out.println(i + " " + j);
						lvl.getTabNiveau()[j][i].ajouterEntite(e);
					}
				}
				
				//Si l'entité est le héro, il faut mettre à jour le scroll
				if(e instanceof HeroEntity)
				{
					//Si le scroll sort de l'écran on met à 0 ou max sinon on centre sur le héro
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
		}
		else
		{
			System.out.println("Fonction deplacer Entity : Entité non trouve, e = null");
		}
	}

	public LevelDrol getLvl() {
		return lvl;
	}

	public void setLvl(LevelDrol lvl) {
		this.lvl = lvl;
	}
}
