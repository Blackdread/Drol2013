package base.engine.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class HeroEntity extends MoveableEntity {
	

	public HeroEntity(String name, int maxLife) {
		super(name, maxLife);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		g.setColor(Color.red);
		g.fillRect(x, y, this.getWidth(), this.getHeight());
	}
	
	@Override
	public void update(int delta) {
		super.update(delta);
		
	}

	@Override
	public void onCollision(ICollidableObject collideWith) {
		// TODO Auto-generated method stub
		
	}

}
