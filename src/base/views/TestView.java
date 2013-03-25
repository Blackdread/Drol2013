package base.views;

import java.io.File;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import base.engine.EngineManager;
import base.engine.Game;
import base.engine.Message;
import base.engine.MessageKey;
import base.engine.entities.HeroEntity;
import base.engine.levels.LevelDrol;
import base.tile.TilePropriety;
import base.tile.TileSet;
import base.utils.ResourceManager;
import base.engine.LogicEngine;


public class TestView extends View{
	
	private TileSet t;
	private LevelDrol lvl;
	ArrayList<TilePropriety> tp = new ArrayList<TilePropriety>(0);
	HeroEntity hero;
	EngineManager engineManager;
	
	public void initResources()
	{
		engineManager = EngineManager.getInstance();
		tp.add(new TilePropriety(0, false, "fodfnd"));
		tp.add(new TilePropriety(1, false, "fond"));
		tp.add(new TilePropriety(2, false, "fodnd"));
		tp.add(new TilePropriety(3, false, "fonssd"));
		
		
		t = new TileSet(ResourceManager.getSpriteSheet("sprite"), tp);
		
		lvl = new LevelDrol(new File("levels/lvl_0.lvl"), t);
		lvl.loadLevel();
		((LogicEngine)engineManager.getTabEngine()[1]).setLvl(lvl);
		hero = new HeroEntity(5, 500);
		hero.setLocation(64, 32);
		hero.setWidth(32);
		hero.setHeight(32);
		
		while(!lvl.isLoadOver())
		{
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		lvl.getArrayEntite().put(hero.getId(), hero);
		lvl.getTabNiveau()[2][0].ajouterEntite(hero);
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame sbgame)
	{
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {
		if(lvl.isLoadOver())
			lvl.generateLevelGraphic().flush();
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException 
	{
		if(Keyboard.isKeyDown(Input.KEY_RIGHT) || Keyboard.isKeyDown(Input.KEY_D))
		{
			Message m = new Message();
			m.instruction = MessageKey.I_MOVE_ENTITY;
			m.i_data.put(MessageKey.P_ID, hero.getId());
			m.i_data.put(MessageKey.P_X, (int)hero.getX()+1);
			m.i_data.put(MessageKey.P_Y, (int)hero.getY());
			
			engineManager.getTabEngine()[1].receiveMessage(m);
		}else if(Keyboard.isKeyDown(Input.KEY_LEFT) || Keyboard.isKeyDown(Input.KEY_A))
		{
			Message m = new Message();
			m.instruction = MessageKey.I_MOVE_ENTITY;
			m.i_data.put(MessageKey.P_ID, hero.getId());
			m.i_data.put(MessageKey.P_X, (int)hero.getX()-1);
			m.i_data.put(MessageKey.P_Y, (int)hero.getY());
			
			engineManager.getTabEngine()[1].receiveMessage(m);
		}
		else if(Keyboard.isKeyDown(Input.KEY_LEFT))
		{
			Message m = new Message();
			m.instruction = MessageKey.I_MOVE_ENTITY;
			m.i_data.put(MessageKey.P_ID, hero.getId());
			m.i_data.put(MessageKey.P_X, (int)hero.getX()-1);
			m.i_data.put(MessageKey.P_Y, (int)hero.getY());
			
			engineManager.getTabEngine()[1].receiveMessage(m);
		}
		else if(Keyboard.isKeyDown(Input.KEY_UP))
		{
			Message m = new Message();
			m.instruction = MessageKey.I_MOVE_ENTITY;
			m.i_data.put(MessageKey.P_ID, hero.getId());
			m.i_data.put(MessageKey.P_X, (int)hero.getX());
			m.i_data.put(MessageKey.P_Y, (int)hero.getY()-1);
			
			engineManager.getTabEngine()[1].receiveMessage(m);
		}
		else if(Keyboard.isKeyDown(Input.KEY_DOWN))
		{
			Message m = new Message();
			m.instruction = MessageKey.I_MOVE_ENTITY;
			m.i_data.put(MessageKey.P_ID, hero.getId());
			m.i_data.put(MessageKey.P_X, (int)hero.getX());
			m.i_data.put(MessageKey.P_Y, (int)hero.getY()+1);
			
			engineManager.getTabEngine()[1].receiveMessage(m);
		}
		
		engineManager.update();
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		
		switch(key){
		/*
		case Input.KEY_RIGHT:
			lvl.getScroll().setxScroll((lvl.getScroll().getxScroll()+3));
			break;*/
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
