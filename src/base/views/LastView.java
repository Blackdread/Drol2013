package base.views;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import base.engine.Game;
import base.utils.ResourceManager;
import base.utils.Timer;

/**
 * 
 * @author Yoann CAPLAIN
 * @since 09 10 2012
 */
public class LastView extends View{

	private static final int WAIT_TIME_BEFORE_NEXTR = 4000;	// 7000
	
	private Image background;
	private Timer timer;
	private boolean ready;
	
	@Override
	public void initResources() {
		ready = false;
		timer = new Timer(WAIT_TIME_BEFORE_NEXTR);	
		background = ResourceManager.getImage("main_menu_view_background").getScaledCopy(container.getWidth(), container.getHeight());
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbGame, Graphics g) throws SlickException {
		super.render(container, game, g);
		g.drawImage(background, 0, 0);
		g.setColor(Color.red);
		if(ready)
			g.drawString("Press a key or click", container.getWidth() / 2 - 90, container.getHeight() / 2 + 40);
	}
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {
		super.update(container, sbGame, delta);
		timer.update(delta);
		if (timer.isTimeComplete()) {
			timer.resetTime();
			ready=true;
		}
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		if(ready)
			container.exit();
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		if(ready)
			container.exit();
	}
	
	@Override
	public int getID() {
		return Game.LAST_VIEW_ID;
	}

}
