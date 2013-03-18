package base.views;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

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
		
		
		t = new TileSet(ResourceManager.getSpriteSheet("turret"), tp);
		
		lvl = new LevelDrol(new File("levels/lvl_0.lvl"), t);
		lvl.loadLevel();
	}
	
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {
		if(lvl.isLoadOver())
			lvl.generateLevelGraphic(500, 500).flush();
	}
	
	
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException 
	{
		
	}
	
	public void keyPressed(int key, char c) {
	}
	
	public void mousePressed(int button, int x, int y) {
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return 1;
	}
}
