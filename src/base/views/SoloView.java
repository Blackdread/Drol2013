package base.views;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import base.engine.Game;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public class SoloView extends View {

	@Override
	public void initResources() {
		

	}

	
	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {	
		
		g.drawString("OUI Touche moi (temporaire ^^)", 300, 300);
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		switch(key){
		case Input.KEY_ESCAPE:
			gotoPreviousView();
			break;
		}
	}
	@Override
	public void mousePressed(int button, int x, int y) {
		if(x >= 300 && x < 500 && y >= 300 && y <= 320)
			startCampaign();
		
	}
	
	private void startCampaign(){
		container.setMouseGrabbed(false);
		((View)game.getState(Game.TEST_STATE_ID)).initResources();	// TODO temporaire
		game.enterState(Game.TEST_STATE_ID, new FadeOutTransition(), new FadeInTransition());
	}
	
	@Override
	public int getID() {
		return Game.SOLO_VIEW_ID;
	}

}
