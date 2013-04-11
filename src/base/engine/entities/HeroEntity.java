package base.engine.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public class HeroEntity extends PlayableEntity {
	
	protected Weapon weapon;
	
	public HeroEntity(String name, int maxLife) {
		super(name, maxLife);
		// TODO Auto-generated constructor stub
		shape = new Rectangle(0,0,40,40);
		weapon = new Weapon("TirLinear", 500);
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
		super.onCollision(collideWith);
	}

}
