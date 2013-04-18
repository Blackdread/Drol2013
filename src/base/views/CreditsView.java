package base.views;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import base.engine.Game;
import base.utils.ResourceManager;


/**
 * Menu associated to the credits.
 * 
 * @author Yoann CAPLAIN
 * 
 */

public class CreditsView extends View {

	private Image background;
	MouseOverArea butQuitter;
	
	@Override
	public void initResources() {
		background = ResourceManager.getImage("background_credits_view").getScaledCopy(container.getWidth(), container.getHeight());
		
		butQuitter = new MouseOverArea(container, ResourceManager.getImage("MenuQuitterOver"), container.getWidth()/10, container.getHeight()-container.getHeight()/10 - 50, ResourceManager.getImage("MenuQuitterOver").getWidth(), ResourceManager.getImage("MenuQuitterOver").getHeight());
		butQuitter.setMouseOverImage(ResourceManager.getImage("MenuQuitter"));
	}


	@Override
	public int getID() {
		return Game.CREDITS_VIEW_ID;
	}


	@Override
	public void render(GameContainer container, StateBasedGame sbGame, Graphics g) throws SlickException {
		super.render(container, game, g);
		g.drawImage(background, 0, 0);
		
		butQuitter.render(container, g);
	}
	
	@Override
	public void mouseReleased(int button, int x, int y) {
		super.mouseReleased(button, x, y);
		if(butQuitter.isMouseOver())
			goToMenu();
	}

	private void goToMenu() {
		container.setMouseGrabbed(false);
		game.enterState(Game.MAIN_MENU_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}

}
