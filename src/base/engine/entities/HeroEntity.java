package base.engine.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class HeroEntity extends MoveableEntity {
	
	/*
	 * 0/ BAS
	 * 1/ HAUT
	 * 2/ GAUCHE
	 * 3/ DROITE
	 */
	
	int direction;

	public HeroEntity(String name, int type, int maxLife) {
		super(name, type, maxLife);
		direction = 0;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.fillRect(x, y, this.getWidth(), this.getHeight());
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}



}
