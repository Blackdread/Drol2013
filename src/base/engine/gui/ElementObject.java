package base.engine.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public class ElementObject extends Elements {

	private Object object;
	
	public ElementObject(GUIContext container, Image image, Shape shape) {
		super(container, image, shape);
		// TODO Auto-generated constructor stub
	}

	public ElementObject(GUIContext container, Image image, int x, int y) {
		super(container, image, x, y);
		// TODO Auto-generated constructor stub
	}

	public ElementObject(GUIContext container, Image image, int x, int y,
			ComponentListener listener) {
		super(container, image, x, y, listener);
		// TODO Auto-generated constructor stub
	}

	public ElementObject(GUIContext container, Image image, int x, int y,
			int width, int height) {
		super(container, image, x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	public ElementObject(GUIContext container, Image image, int x, int y,
			int width, int height, ComponentListener listener) {
		super(container, image, x, y, width, height, listener);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isElementUsed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String toString() {
		return object.toString();
	}

	
	@Override
	public void render(GUIContext container, Graphics g){
		super.render(container, g);
		g.drawString(""+object.toString(), super.getX()+25 +decalageX, super.getY() + super.getHeight()/4 +decalageY);
	}
	@Override
	public void render(GUIContext container, Graphics g, int x, int y) {
		super.render(container, g);
		g.drawString(""+object.toString(), x+25 +decalageX, y + super.getHeight()/4 +decalageY);
	}

	@Override
	public void renderString(GUIContext container, Graphics g, int x, int y) {
		g.drawString(""+object.toString(), x+25 +decalageX, y + super.getHeight()/4 + 6 +decalageY);
	}

}
