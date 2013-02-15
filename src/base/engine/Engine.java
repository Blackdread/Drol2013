package base.engine;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import base.engine.layers.entities.units.Player;
import base.engine.levels.Level;
import base.views.View;

/**
 * The entry point to all game mechanics.
 * 
 * This class regroup collision, rendering, network, etc and link every
 * module to make the game working.
 * 
 * The method initGame must be called to initialize the engine to play.
 * 
 * @author Yoann CAPLAIN
 * 
 */
public class Engine extends View {

	private int xPressed, yPressed;
	private int xScrollDecal;
	private int yScrollDecal;
	
	private Level level;
	private Player player;
	
	@Override
	public void initResources() {
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {
		
		super.render(container, sbgame, g);
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		
	}
	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		
	}
	
	public Player getPlayer(){
		return this.player;
	}
	public Level getLevel(){
		return this.level;
	}

	@Override
	public int getID() {
		return Game.ENGINE_VIEW_ID;
	}
}
