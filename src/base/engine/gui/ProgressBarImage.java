package base.engine.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.gui.GUIContext;


/**
 * background et barInterne doivent etre de la meme hauteur. 
 * Il faut donc que l'image de la bar gere la transparencesur le haut et le bas.
 * Les bars sont colees entre elles et par rapport au background
 * 
 * @author Yoann CAPLAIN
 * @since 26 10 2012
 */
public class ProgressBarImage extends ProgressBar {

	private Image background;
	private Image barInterne;
	
	public ProgressBarImage(Image back, Image bar) {
		background = back;
		barInterne = bar;
	}

	/**
	 * background et barInterne doivent etre de la meme hauteur. 
	 * Il faut donc que l'image de la bar gere la transparencesur le haut et le bas.
	 * Les bars sont colees entre elles et par rapport au background
	 */
	@Override
	public void render(GUIContext container, Graphics g) {
		g.drawImage(background, x, y);
		for(int i=0;i<value;i++){
			g.drawImage(barInterne, x + barInterne.getWidth()*i, y);
		}
	}
	@Override
	public void renderAndValue(GUIContext container, Graphics g) {
		this.render(container, g);
		g.drawString(""+value, barInterne.getWidth()*MAX_VALUE/2-container.getDefaultFont().getWidth(""+value)/2, y-container.getDefaultFont().getHeight(""+value) - DECALAGE_RENDER_VALUE);
	}
	
	@Override
	public int getWidht() {
		// TODO Auto-generated method stub
		return background.getWidth();
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return background.getHeight();
	}
}
