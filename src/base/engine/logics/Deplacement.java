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
		int largeur = e.getWidth();
		int hauteur = e.getHeight();
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		
		
		//On vérifie qu'il n'y a pas de collision
		if(!c_manager.testerCollision(x, y, e))
		{
			//Enlever l'entité des tiles avant le déplacement
			for(int i = ex/lvl.getLargeurTile(); i <= (ex + largeur)/lvl.getLargeurTile(); i++)
			{
				for(int j = ey/lvl.getHauteurTile(); j < (ey + hauteur)/lvl.getHauteurTile(); j++)
					lvl.getTabNiveau()[i][j].enleverEntite(id);
			}
			
			//On déplace l'entité
			e.setLocation((float) x, (float) y);
			lvl.getArrayEntite().put(id, e);
			
			//On replace l'entité dans les tiles
			for(int i = x/lvl.getLargeurTile(); i <= (x + largeur)/lvl.getLargeurTile(); i++)
			{
				for(int j = y/lvl.getHauteurTile(); j < (y + hauteur)/lvl.getHauteurTile(); j++)
				{
					lvl.getTabNiveau()[i][j].ajouterEntite(e);
					for(int k = 0; k < lvl.getTabNiveau()[i][j].getEntiteProche().size(); k++)
						System.out.println(lvl.getTabNiveau()[i][j].getEntiteProche().get(k).getX() +" "+i+" "+j);
				}
			}
			
			//Si l'entité est le héro, il faut mettre à jour le scoll
			if(e instanceof HeroEntity)
			{
				//Si le scroll ne sort pas de l'écran
				if((x-(lvl.getScroll().getWidth()/2)) >= 0){
				System.out.println((x-(lvl.getScroll().getWidth()/2)));
				if((x-(lvl.getScroll().getWidth()/2)) > 0)
					lvl.getScroll().setxScroll((x-(lvl.getScroll().getWidth()/2)));
				}else
					lvl.getScroll().setxScroll(0);
				/*
				if( (x + (lvl.getScroll().getWidth()/2) ) < (lvl.getLargeurNiveau()*lvl.getLargeurTile()))
					lvl.getScroll().setxScroll(x+(lvl.getScroll().getWidth()/2));
				else
					lvl.getScroll().setxScroll(lvl.getLargeurNiveau()*lvl.getLargeurTile()-lvl.getScroll().getWidth());	
			*/
				
			}
		}		
	}

	public LevelDrol getLvl() {
		return lvl;
	}

	public void setLvl(LevelDrol lvl) {
		this.lvl = lvl;
	}
}
