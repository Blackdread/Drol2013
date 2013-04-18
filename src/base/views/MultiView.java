package base.views;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import base.engine.Game;

/**
 * 
 * @author Yoann CAPLAIN
 *
 */
public class MultiView extends View {

	@Override
	public void initResources() {

	}


	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {	
		
		
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
		
		
	}
	
	@Override
	public int getID() {
		return Game.MULTI_VIEW_ID;
	}

}
