package base.engine.entities;

import org.newdawn.slick.Graphics;

/**
 * Represent a game entity.
 * 
 * @author Yoann CAPLAIN
 * 
 */
public interface IEntity {

	public void render(Graphics g, int x, int y);
	
	//public void render(GameContainer container, Graphics g) throws SlickException;

	//public void update(int delta) throws SlickException;

	public float getX();

	public float getY();
	
	public void setLocation(float x, float y);

	public int getWidth();

	public int getHeight();
	
	public int getId();

}
