package base.tile;

import base.engine.entities.BasicEntity;
import base.engine.levels.Level;
import base.engine.levels.LevelDrol;

public class Scroll {
	private int xScroll, yScroll;
	private int width, height;

	public Scroll() {
		xScroll = 0;
		yScroll = 0;
		width = 1000;
		height = 500;
	}
	
	public Scroll(int xScroll, int yScroll, int width, int height) {
		this.xScroll = xScroll;
		this.yScroll = yScroll;
		this.width = width;
		this.height = height;
	}
	
	/**
	 * Si le scroll sort de l'ecran on met 0 ou max sinon on centre sur l'entite
	 * @param e entite sur laquelle centre le scroll
	 */
	public void mettreAJourScroll(final BasicEntity e){
		LevelDrol lvl = (LevelDrol) Level.getCurrentLevelUsed();
		
		if((e.getX()-(width/2)) < 0)
			xScroll = 0;
		else if((e.getX() + (width/2)) > (lvl.getLargeurNiveau()*lvl.getLargeurTile()))
			xScroll = lvl.getLargeurNiveau()*lvl.getLargeurTile()-width;
		else
			xScroll = (int) (e.getX()-(width/2));
		
		if((e.getY()-height/2) < 0)
			yScroll = 0;
		else if((e.getY() + height/2) > (lvl.getHauteurNiveau()*lvl.getHauteurTile()))
			yScroll = lvl.getHauteurNiveau()*lvl.getHauteurTile()-height;
		else
			yScroll = (int) (e.getY()-(height/2));
		
	}

	public int getxScroll() {
		return xScroll;
	}

	public void setxScroll(int xScroll) {
		this.xScroll = xScroll;
	}

	public int getyScroll() {
		return yScroll;
	}

	public void setyScroll(int yScroll) {
		this.yScroll = yScroll;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
