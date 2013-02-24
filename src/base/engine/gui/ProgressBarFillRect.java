package base.engine.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.GUIContext;

public class ProgressBarFillRect extends ProgressBar{
	
	private float widthRect, heightRect;

	public ProgressBarFillRect(float w, float h) {
		this.widthRect=w;
		this.heightRect=h;
	}

	public void setWidth(float w){
		this.widthRect=w;
	}
	public void setHeight(float h){
		this.heightRect=h;
	}
	public void setSize(float w,float h){
		this.widthRect=w;
		this.heightRect=h;
	}
	
	@Override
	public int getWidth() {
		return (int)(value*widthRect);
	}

	@Override
	public int getHeight() {
		return (int)heightRect;
	}

	/**
	 * La couleur doit etre mise avant l'appel de ce render
	 */
	@Override
	public void render(GUIContext container, Graphics g) {
		for(int i=0;i<value;i++){
			g.fillRect(x + widthRect*i, y, widthRect, heightRect);
		}
	}
	
	/**
	 * La couleur doit etre mise avant l'appel de ce render
	 */
	@Override
	public void renderAndValue(GUIContext container, Graphics g) {
		this.render(container, g);
		g.drawString(""+value, widthRect*MAX_VALUE/2-container.getDefaultFont().getWidth(""+value)/2, y-container.getDefaultFont().getHeight(""+value) - DECALAGE_RENDER_VALUE);
	}

}
