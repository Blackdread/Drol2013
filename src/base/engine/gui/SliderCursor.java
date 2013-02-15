/**
 * 
 */
package base.engine.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

/**
 * @author Yoann CAPLAIN
 * @since 17 10 2012
 */
public class SliderCursor extends MouseOverArea {

	/**
	 * @param container
	 * @param image
	 * @param shape
	 */
	public SliderCursor(GUIContext container, Image image, Shape shape) {
		super(container, image, shape);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param container
	 * @param image
	 * @param x
	 * @param y
	 */
	public SliderCursor(GUIContext container, Image image, int x, int y) {
		super(container, image, x, y);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param container
	 * @param image
	 * @param x
	 * @param y
	 * @param listener
	 */
	public SliderCursor(GUIContext container, Image image, int x, int y,
			ComponentListener listener) {
		super(container, image, x, y, listener);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param container
	 * @param image
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public SliderCursor(GUIContext container, Image image, int x, int y,
			int width, int height) {
		super(container, image, x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param container
	 * @param image
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param listener
	 */
	public SliderCursor(GUIContext container, Image image, int x, int y,
			int width, int height, ComponentListener listener) {
		super(container, image, x, y, width, height, listener);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void render(GUIContext container, Graphics g){
		super.render(container, g);
		
	}
	
	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy){
		 super.mouseDragged(oldx, oldy, newx, newy);
		 
	}
	
	

}
