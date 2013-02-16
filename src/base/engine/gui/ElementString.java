package base.engine.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;


public class ElementString extends Elements {

	private String chaine;
	
	public ElementString(GUIContext container, Image image, int x, int y,
			ComponentListener listener, String a) {
		super(container, image, x, y, listener);
		// TODO Auto-generated constructor stub
		chaine = a;
	}

	public ElementString(GUIContext container, Image image, int x, int y,
			int width, int height, ComponentListener listener, String a) {
		super(container, image, x, y, width, height, listener);
		// TODO Auto-generated constructor stub
		chaine = a;
	}

	public ElementString(GUIContext container, Image image, int x, int y,
			int width, int height, String a) {
		super(container, image, x, y, width, height);
		// TODO Auto-generated constructor stub
		chaine = a;
	}

	public ElementString(GUIContext container, Image image, int x, int y, String a) {
		super(container, image, x, y);
		// TODO Auto-generated constructor stub
		chaine = a;
	}

	public ElementString(GUIContext container, Image image, Shape shape, String a) {
		super(container, image, shape);
		// TODO Auto-generated constructor stub
		chaine = a;
	}

	@Override
	public boolean isElementUsed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void render(GUIContext container, Graphics g){
		super.render(container, g);
		g.drawString(""+chaine, super.getX()+25+decalageX, super.getY() + super.getHeight()/4 +decalageY);
	}
	@Override
	public void render(GUIContext container, Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		super.render(container, g);
		g.drawString(""+chaine, x+decalageX, y + super.getHeight()/4 +decalageY);
	}

	@Override
	public void renderString(GUIContext container, Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		g.drawString(""+chaine, x+decalageX, y + super.getHeight()/4 + 6 +decalageY);
	}

	@Override
	public String toString() {
		return chaine;
	}

}
