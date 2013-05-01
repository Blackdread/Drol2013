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

import base.engine.EngineManager;
import base.engine.Game;
import base.engine.Message;
import base.engine.MessageKey;
import base.engine.levels.LevelDrol;
import base.engine.levels.LevelManager;
import base.utils.Configuration;
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
	private MouseOverArea butJouer, butSolo, butMulti, butOption, butQuitter, butCredits;
	
	private boolean wasOverJouer = false;
	private boolean doOnce = false;

	@Override
	public void initResources() {
		background = ResourceManager.getImage("background_main_menu_view").getScaledCopy(container.getWidth(), container.getHeight());

		Image tmp = ResourceManager.getImage("MenuJouer");
		
		int larg = tmp.getWidth();
		int haut = tmp.getHeight();
		
		int x = container.getWidth() / 2 - larg/2;
		int y = container.getHeight() / 2 - haut/2 * 4;
		
		butJouer = new MouseOverArea(container, tmp, x, y, larg, haut);
		butJouer.setMouseOverImage(ResourceManager.getImage("MenuJouerOver"));
		butJouer.setMouseDownSound(ResourceManager.getSound("butClick"));
		
		butSolo = new MouseOverArea(container, ResourceManager.getImage("MenuSolo"), x + tmp.getWidth(), y,  ResourceManager.getImage("MenuSolo").getWidth(),  ResourceManager.getImage("MenuSolo").getHeight());
		butSolo.setMouseOverImage(ResourceManager.getImage("MenuSoloOver"));
		butSolo.setMouseDownSound(ResourceManager.getSound("butClick"));
		
		butMulti = new MouseOverArea(container, ResourceManager.getImage("MenuMulti"), x + tmp.getWidth() + ResourceManager.getImage("MenuSolo").getWidth(), y, ResourceManager.getImage("MenuMulti").getWidth(), ResourceManager.getImage("MenuMulti").getHeight());
		butMulti.setMouseOverImage(ResourceManager.getImage("MenuMultiOver"));
		butMulti.setMouseDownSound(ResourceManager.getSound("butClick"));
		
		butOption = new MouseOverArea(container, ResourceManager.getImage("MenuOption"), x, y+haut, larg, haut);
		butOption.setMouseOverImage(ResourceManager.getImage("MenuOptionOver"));
		butOption.setMouseDownSound(ResourceManager.getSound("butClick"));
		
		butQuitter = new MouseOverArea(container, ResourceManager.getImage("MenuQuitter"), x, y+haut*2-25, larg, haut);
		butQuitter.setMouseOverImage(ResourceManager.getImage("MenuQuitterOver"));
		butQuitter.setMouseDownSound(ResourceManager.getSound("butClick"));
		
		butCredits = new MouseOverArea(container, ResourceManager.getImage("MenuCredits"), x, y+haut*3-50, larg, haut);
		butCredits.setMouseOverImage(ResourceManager.getImage("MenuCreditsOver"));
		butCredits.setMouseDownSound(ResourceManager.getSound("butClick"));
		
		if(Configuration.isMusicOn()){
			Message m = new Message();
			m.instruction = MessageKey.I_PLAY_MUSIC;
			// TODO donner le nom de la musique a jouer
			m.s_data.put(MessageKey.P_NAME, "tron");
			
			m.engine = EngineManager.SOUND_ENGINE;
			
			engineManager.receiveMessage(m);
		}
		if(!doOnce){
			LevelManager.getInstance(engineManager).addLevels("levels");
			doOnce = true;
		}
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {
		super.update(container, sbGame, delta);
		
		engineManager.getSoundEngine().processMessage();
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {	
		g.drawImage(background, 0, 0);
		butJouer.render(container, g);
		if(wasOverJouer){
			butSolo.render(container, g);
			butMulti.render(container, g);
		}
		butOption.render(container, g);
		butQuitter.render(container, g);
		butCredits.render(container, g);
		g.setColor(Color.white);
		//font.drawString(container.getWidth()-font.getWidth(Game.VERSION)-5, container.getHeight()-font.getHeight(Game.VERSION)-5, Game.VERSION, Color.cyan);
		g.drawString(Game.VERSION, 5, container.getHeight() - 20);
		super.render(container, sbgame, g);
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy){
		if(wasOverJouer){
			if(!butSolo.isMouseOver() && !butMulti.isMouseOver())
				wasOverJouer = false;
		}
		if(butJouer.isMouseOver())
			wasOverJouer = true;
		
		/*
		if(butJouer.isMouseOver() || butSolo.isMouseOver() || butMulti.isMouseOver() || butOption.isMouseOver() || butQuitter.isMouseOver() || butCredits.isMouseOver() && !ResourceManager.getSound("butOver").playing()){
			Message m = new Message();
			m.instruction = MessageKey.I_PLAY_SOUND;
			m.s_data.put(MessageKey.P_NAME, "butOver");
			m.engine = EngineManager.SOUND_ENGINE;
			
			engineManager.receiveMessage(m);
		}//*/
		
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
			gotoJouerSolo();
		else if(butOption.isMouseOver())
			gotoOption();
		else if(butCredits.isMouseOver())
			gotoCredits();
		else if(butQuitter.isMouseOver())
			gotoLastView();
		else if(wasOverJouer){
			if(butSolo.isMouseOver())
				gotoJouerSolo();
			if(butMulti.isMouseOver())
				gotoJouerMulti();
		}
	}

	private void gotoJouerSolo() {
		container.setMouseGrabbed(false);
		game.enterState(Game.SOLO_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}
	private void gotoJouerMulti() {
		container.setMouseGrabbed(false);
		game.enterState(Game.MULTI_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}
	private void gotoOption() {
		container.setMouseGrabbed(false);
		game.enterState(Game.OPTIONS_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}
	private void gotoCredits() {
		container.setMouseGrabbed(false);
		game.enterState(Game.CREDITS_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}
	
	protected void gotoLastView() {
		container.setMouseGrabbed(false);
		game.enterState(Game.LAST_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}

	@Override
	public int getID() {
		return Game.MAIN_MENU_VIEW_ID;
	}

}
