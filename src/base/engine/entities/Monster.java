package base.engine.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Monster extends MoveableEntity {

	public Monster(String name, int maxLife) {
		super(name, maxLife);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g, int x, int y) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		
	}

	
	public void onCollision(BasicEntity a){
		
	}

}
