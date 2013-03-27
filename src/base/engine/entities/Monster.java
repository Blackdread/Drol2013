package base.engine.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Monster extends MoveableEntity {

	public Monster(String name, int type, int maxLife) {
		super(name, type, maxLife);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g, int x, int y) {
		// TODO Auto-generated method stub

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
	
	public void onCollision(BasicEntity a){
		
	}

}
