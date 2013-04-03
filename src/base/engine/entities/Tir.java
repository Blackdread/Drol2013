package base.engine.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Tir extends MoveableEntity {

	public Tir(String name, int type, int maxLife) {
		super(name, type, maxLife);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		g.setColor(Color.green);
		g.fillRect(x, y, this.getWidth(), this.getHeight());
		
	}

}
