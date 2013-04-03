package base.engine.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Tir extends MoveableEntity {

	public Tir(String name, int maxLife) {
		super(name, maxLife);
		// TODO Auto-generated constructor stub
		width = 10;
		height = 10;
	}

	@Override
	public void render(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		g.setColor(Color.cyan);
		g.fillRect(x, y, this.getWidth(), this.getHeight());
		
	}

}