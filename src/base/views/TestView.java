package base.views;

import java.io.File;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import base.engine.EngineManager;
import base.engine.Game;
import base.engine.Message;
import base.engine.MessageKey;
import base.engine.entities.BasicEntity;
import base.engine.entities.HeroEntity;
import base.engine.entities.others.FilterManager;
import base.engine.entities.others.InfoManager;
import base.engine.entities.others.filters.FilterActivatorName;
import base.engine.entities.others.info.InfoTarget;
import base.engine.entities.others.triggers.TriggerTeleport;
import base.engine.levels.LevelDrol;
import base.engine.logics.Deplacement;
import base.engine.logics.IA;
import base.tile.TilePropriety;
import base.tile.TileSet;
import base.utils.Configuration;
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
		tp.add(new TilePropriety(2, true, "fodnd"));
		tp.add(new TilePropriety(3, false, "fonssd"));
		
		
		t = new TileSet(ResourceManager.getSpriteSheet("sprite"), tp);
		
		lvl = new LevelDrol(new File("levels/lvl_0.lvl"), t);
		lvl.loadLevel();
		((LogicEngine)engineManager.getTabEngine()[1]).setLvl(lvl);
		hero = new HeroEntity("bla", 500);
		hero.setLocation(70, 70);
		
		while(!lvl.isLoadOver())
		{
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		TriggerTeleport tr = new TriggerTeleport("teleport",200,36,40,40);
		InfoTarget inf = new InfoTarget("infotarget", 200, 250);
		FilterActivatorName fil = new FilterActivatorName("filtername",false,"bla");
		tr.setRemoteDestination("infotarget");
		tr.setFilterEntityThatActivate(fil);
		InfoManager.getInstance().addEntity(inf);
		FilterManager.getInstance().addEntity(fil);
		lvl.addEntity(tr);
		lvl.addEntity(inf);
		Deplacement.deplacerEntity(0, 0, tr.getId());
		Deplacement.deplacerEntity(0, 0, inf.getId());
		lvl.addEntity(hero);
		Deplacement.deplacerEntity(0, 0, hero.getId());
		IA.getInstance().addEntity(hero);
		
		System.out.println("hero : " + hero);
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame sbgame)
	{
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {
		if(lvl.isLoadOver())
			lvl.generateLevelGraphic(g, 200, 100);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException 
	{	
		if(Keyboard.isKeyDown(Input.KEY_LSHIFT))
		{
			Message m = new Message();
			m.instruction = MessageKey.I_SHOOT;
			
			if(hero.getDirection() == BasicEntity.HAUT)
			{
				//haut
				//TODO: Gérer direction, vecteur du tir, update du tir
				m.i_data.put(MessageKey.P_X, (int) (hero.getX() + hero.getHeight()/2));
				m.i_data.put(MessageKey.P_Y, (int) (hero.getY() - 5));
				m.i_data.put(MessageKey.P_VITESSE_X, 0);
				m.i_data.put(MessageKey.P_VITESSE_Y, -5);
				
			}
			else if(hero.getDirection() == BasicEntity.BAS)
			{
				//BAS
				m.i_data.put(MessageKey.P_X, (int) hero.getX() + hero.getHeight()/2);
				m.i_data.put(MessageKey.P_Y, (int) (hero.getY() + hero.getHeight() + 1));
				m.i_data.put(MessageKey.P_VITESSE_X, 0);
				m.i_data.put(MessageKey.P_VITESSE_Y, 5);
			}
			else if(hero.getDirection() == BasicEntity.GAUCHE)
			{
				//GAUCHE
				m.i_data.put(MessageKey.P_X, (int) (hero.getX() - 5));
				m.i_data.put(MessageKey.P_Y, (int) (hero.getY() + hero.getWidth()/2));
				m.i_data.put(MessageKey.P_VITESSE_X, -5);
				m.i_data.put(MessageKey.P_VITESSE_Y, 0);
				
			}
			else if(hero.getDirection() == BasicEntity.DROITE)
			{
				//DROITE
				m.i_data.put(MessageKey.P_X, (int) (hero.getX() + hero.getWidth() + 5));
				m.i_data.put(MessageKey.P_Y, (int) (hero.getY() + hero.getWidth()/2));
				m.i_data.put(MessageKey.P_VITESSE_X, 5);
				m.i_data.put(MessageKey.P_VITESSE_Y, 0);
			}
			
			m.o_data.put(MessageKey.P_ENTITY, hero);
			
			engineManager.getTabEngine()[1].receiveMessage(m);
			
			
		}
		
		engineManager.update(delta);
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		Message m = new Message();
		switch(key){
			case Input.KEY_SPACE:
				//hero.jump();
				m.instruction = MessageKey.I_JUMP;
				m.i_data.put(MessageKey.P_ID, hero.getId());
				m.engine = EngineManager.LOGIC_ENGINE;
				
				EngineManager.getInstance().receiveMessage(m);
				break;
			case Input.KEY_RIGHT:
				m.instruction = MessageKey.I_START_ENTITY_MOVE;
				m.i_data.put(MessageKey.P_ID, hero.getId());
				m.i_data.put(MessageKey.P_DIRECTION, BasicEntity.DROITE);
				m.engine = EngineManager.LOGIC_ENGINE;
				
				engineManager.receiveMessage(m);
				break;
			case Input.KEY_LEFT:
				m.instruction = MessageKey.I_START_ENTITY_MOVE;
				m.i_data.put(MessageKey.P_ID, hero.getId());
				m.i_data.put(MessageKey.P_DIRECTION, BasicEntity.GAUCHE);
				m.engine = EngineManager.LOGIC_ENGINE;
				
				engineManager.receiveMessage(m);
			break;
		}
		
	}
	@Override
	public void keyReleased(int key, char c) {
		Message m = new Message();
		switch(key){
		case Input.KEY_SPACE:
			hero.jump();
			break;
		case Input.KEY_RIGHT:
			
			m.instruction = MessageKey.I_START_ENTITY_MOVE;
			m.i_data.put(MessageKey.P_ID, hero.getId());
			m.i_data.put(MessageKey.P_DIRECTION, BasicEntity.GAUCHE);
			m.b_data.put(MessageKey.P_CHANGE_DIRECTION, false);
			m.engine = EngineManager.LOGIC_ENGINE;
			
			engineManager.receiveMessage(m);
			break;
		case Input.KEY_LEFT:
			Message m2 = new Message();
			m2.instruction = MessageKey.I_START_ENTITY_MOVE;
			m2.i_data.put(MessageKey.P_ID, hero.getId());
			m2.i_data.put(MessageKey.P_DIRECTION, BasicEntity.DROITE);
			m2.b_data.put(MessageKey.P_CHANGE_DIRECTION, false);
			m2.engine = EngineManager.LOGIC_ENGINE;
			
			engineManager.receiveMessage(m2);
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
