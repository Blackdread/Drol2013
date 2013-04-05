package base.engine.entities;

import org.newdawn.slick.Graphics;

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
		super.update(delta);
		
	}

	@Override
	public void onCollision(ICollidableObject collideWith){
		
	}


}
