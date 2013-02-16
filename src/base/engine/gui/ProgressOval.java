package base.engine.gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.GUIContext;

public class ProgressOval extends ProgressBar {
	
	private int width, height;
	
	//private boolean milieuVide;
	//private int milieuWidth, milieuHeight
	
	public ProgressOval(int widht, int height) {
		this.width = widht;
		this.height = height;
	}

	public void setWidth(int w){
		this.width=w;
	}
	public void setHeight(int h){
		this.height=h;
	}
	public void setSize(int w,int h){
		this.width=w;
		this.height=h;
	}
	
	@Override
	public int getWidht() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}
	
	/**
	 * La couleur doit etre mise avant l'appel de ce render
	 */
	@Override
	public void render(GUIContext container, Graphics g) {
		g.fillArc(x, y, width, height, 0, value*3.6f);
	}
	
	/**
	 * C'est bugger, la flemme de le faire pour le moment
	 * @param _g
	 */
	@Deprecated
	private void colorMulty(Graphics _g){
		Graphics gFog = new Graphics(width+20,height+20);
		Rectangle rect = new Rectangle(0, 0, width+20, height+20);
		
		Color couleur = new Color(40,40,40,0);		// Changer le 4eme int pour plus ou moins noir (c'est le fog)
    	Color couleur2 = new Color(20,20,20,255);	// Vision mais pas attaque
    	gFog.setColor(couleur);
        gFog.fill(rect);
        
        gFog.setColor(couleur2);
        gFog.fillArc(x, y, width, height, 0, value*3.6f);
        
        _g.setDrawMode(Graphics.MODE_COLOR_MULTIPLY);   
        //_g.drawImage(fog, 0, 0);
        _g.fillArc(x, y, width, height, 0, 20*3.6f);
        _g.setDrawMode(Graphics.MODE_NORMAL);
        gFog.clear();
        gFog.resetFont();
        gFog.resetTransform();
	}
	
	/**
	 * La couleur doit etre mise avant l'appel de ce render
	 */
	@Override
	public void renderAndValue(GUIContext container, Graphics g) {
		this.render(container, g);
		g.drawString(""+value, width*MAX_VALUE/2-container.getDefaultFont().getWidth(""+value)/2, y-container.getDefaultFont().getHeight(""+value) - DECALAGE_RENDER_VALUE-height/2);
	}

}
