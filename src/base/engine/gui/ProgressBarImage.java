package base.engine.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;


/**
 * 
 * @author Yoann CAPLAIN
 * @since 26 10 2012
 */
public class ProgressBarImage extends ProgressBar {

	private Image background;
	private Image barInterne;
	
	/**
	 * Decalage sur les x de la bar interne
	 */
	private int decalageBarX;
	/**
	 * Decalage sur les y de la bar interne
	 */
	private int decalageBarY;
	
	public ProgressBarImage(Image back, Image bar) {
		background = back;
		barInterne = bar;
	}

	@Override
	public void render(GUIContext container, Graphics g) {
		g.drawImage(background, x, y);
		for(int i=0;i<value;i++){
			g.drawImage(barInterne, x + barInterne.getWidth()*i + decalageBarX, y + decalageBarY);
		}
	}
	@Override
	public void renderAndValue(GUIContext container, Graphics g) {
		this.render(container, g);
		g.drawString(""+value, barInterne.getWidth()*MAX_VALUE/2-container.getDefaultFont().getWidth(""+value)/2, y-container.getDefaultFont().getHeight(""+value) - DECALAGE_RENDER_VALUE);
	}
	
	/**
	 * width of background image
	 */
	@Override
	public int getWidth() {
		return background.getWidth();
	}

	/**
	 * height of background image
	 */
	@Override
	public int getHeight() {
		return background.getHeight();
	}

	public int getDecalageBarX() {
		return decalageBarX;
	}

	public int getDecalageBarY() {
		return decalageBarY;
	}

	public void setDecalageBarX(int decalageBarX) {
		this.decalageBarX = decalageBarX;
	}

	public void setDecalageBarY(int decalageBarY) {
		this.decalageBarY = decalageBarY;
	}
}
