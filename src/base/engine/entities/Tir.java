package base.engine.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

public class Tir extends MoveableEntity {
	
	public Tir(int maxLife, Vector2f vitesse)
	{
		super("tir", maxLife);
		this.vitesse = vitesse;
		shape = new Rectangle(0,0,10,10);
	}

	public Tir(String name, int maxLife) {
		super(name, maxLife);
		// TODO Auto-generated constructor stub
		shape = new Rectangle(0,0,10,10);
	}

	@Override
	public void render(Graphics g, int xx, int yy) {
		// TODO Auto-generated method stub
		g.setColor(Color.cyan);
		g.fillRect(x, y, this.getWidth(), this.getHeight());
		System.out.println("tir "+x+" "+y);
	}

	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub
		//TODO: Deplacer le tir
	}

	@Override
	public void onCollision(ICollidableObject collideWith) {
		// TODO Auto-generated method stub
		
	}
}
