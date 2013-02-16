package base.views;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import base.engine.Game;
import base.engine.gui.transition.WarpTransition;
import base.utils.ResourceManager;



/**
 * The main menu of the game. Severals sub menus are linked here like:
 * 
 * menu Options menu Credits menu ...
 * 
 * @author Yoann CAPLAIN
 * 
 */
public class MainMenuView extends View {

	private Image background;
	MouseOverArea butJouer, butEditeur, butOption, butQuitter, butCredits;

	@Override
	public void initResources() {
		background = ResourceManager.getImage("main_menu_view_background").getScaledCopy(container.getWidth(), container.getHeight());
		
		int x = container.getWidth() / 2 - 75;
		int y = container.getHeight() / 2 - 100;
		
		butJouer = new MouseOverArea(container, ResourceManager.getImage("MenuJouer"), x, y, 150, 30);
		butJouer.setMouseOverImage(ResourceManager.getImage("MenuJouerOver"));
		
		butEditeur = new MouseOverArea(container, ResourceManager.getImage("MenuJouer"), x, y+60, 150, 30);
		butEditeur.setMouseOverImage(ResourceManager.getImage("MenuJouerOver"));
		
		butOption = new MouseOverArea(container, ResourceManager.getImage("MenuOption"), x, y+120, 150, 30);
		butOption.setMouseOverImage(ResourceManager.getImage("MenuOptionOver"));
		
		butQuitter = new MouseOverArea(container, ResourceManager.getImage("MenuQuitter"), x, y+180, 150, 30);
		butQuitter.setMouseOverImage(ResourceManager.getImage("MenuQuitterOver"));
		
		butCredits = new MouseOverArea(container, ResourceManager.getImage("MenuCredits"), x, y+240, 150, 30);
		butCredits.setMouseOverImage(ResourceManager.getImage("MenuCreditsOver"));
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {	
		g.drawImage(background, 0, 0);
		butJouer.render(container, g);
		butEditeur.render(container, g);
		butOption.render(container, g);
		butQuitter.render(container, g);
		butCredits.render(container, g);
		g.setColor(Color.white);
		//font.drawString(container.getWidth()-font.getWidth(Game.VERSION)-5, container.getHeight()-font.getHeight(Game.VERSION)-5, Game.VERSION, Color.cyan);
		g.drawString(Game.VERSION, 5, container.getHeight() - 20);
		super.render(container, sbgame, g);
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		switch(key){
		case Input.KEY_ESCAPE:
			gotoLastView();
			break;
		}
	}
	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		if(butJouer.isMouseOver())
			gotoJouer();
		else if(butEditeur.isMouseOver())
			gotoEditeur();
		else if(butOption.isMouseOver())
			gotoOption();
		else if(butCredits.isMouseOver())
			gotoCredits();
		else if(butQuitter.isMouseOver())
			gotoLastView();
	}

	private void gotoJouer() {
		container.setMouseGrabbed(false);
		game.enterState(Game.LEVELS_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}
	private void gotoEditeur() {
		container.setMouseGrabbed(false);
		game.enterState(Game.EDITEUR_VIEW_ID, null, new WarpTransition());
	}
	private void gotoOption() {
		container.setMouseGrabbed(false);
		game.enterState(Game.OPTIONS_VIEW_ID, new WarpTransition(), null);
	}
	private void gotoCredits() {
		container.setMouseGrabbed(false);
		game.enterState(Game.CREDITS_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}
	private void gotoLastView() {
		container.setMouseGrabbed(false);
		game.enterState(Game.LAST_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}

	@Override
	public int getID() {
		return Game.MAIN_MENU_VIEW_ID;
	}

}
