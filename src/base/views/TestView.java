package base.views;

import java.io.File;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import base.engine.Game;
import base.engine.levels.LevelDrol;
import base.tile.TilePropriety;
import base.tile.TileSet;
import base.utils.ResourceManager;


public class TestView extends View{
	
	private TileSet t;
	private LevelDrol lvl;
	ArrayList<TilePropriety> tp = new ArrayList<TilePropriety>(0);
	
	public void initResources()
	{
		tp.add(new TilePropriety(0, true, "mur"));
		tp.add(new TilePropriety(0, false, "fond"));
		
		
		t = new TileSet(ResourceManager.getSpriteSheet("sprite"), tp);
		
		lvl = new LevelDrol(new File("levels/lvl_0.lvl"), t);
		lvl.loadLevel();
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {
		if(lvl.isLoadOver())
			lvl.generateLevelGraphic(500, 500).flush();
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException 
	{
		
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		switch(key){
		case Input.KEY_RIGHT:
			lvl.setxScroll(lvl.getxScroll()+3);
			break;
		}
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
	}

	@Override
	public int getID() {
		return Game.TEST_STATE_ID;
	}
}
