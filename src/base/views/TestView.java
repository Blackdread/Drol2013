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
import base.engine.Player;
import base.engine.entities.BasicEntity;
import base.engine.entities.HeroEntity;
import base.engine.entities.Zombi;
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
	
	private Player player;
	
	public void initResources(){
		
		tp.add(new TilePropriety(0, false, "fodfnd"));
		tp.add(new TilePropriety(1, false, "fond"));
		tp.add(new TilePropriety(2, true, "fodnd"));
		tp.add(new TilePropriety(3, false, "fonssd"));
		
		
		t = new TileSet(ResourceManager.getSpriteSheet("sprite"), tp);
		
		lvl = new LevelDrol(new File("levels/lvl_0.lvl"), t, Game.getEngineManager());
		lvl.loadLevel();
		engineManager.setCurrentLevelUsed(lvl);
		hero = new HeroEntity("bla", engineManager, 500);
		hero.setLocation(70, 70);
		
		Zombi z = new Zombi("zombi", engineManager, 10);
		z.setLocation(140, 40);
		
		while(!lvl.isLoadOver())
		{
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {e.printStackTrace();}
		}
		TriggerTeleport trGauche = new TriggerTeleport(engineManager,"teleport", lvl.getLargeurTile()+2,lvl.getHauteurTile()+2,5,lvl.getHauteurTile()*(lvl.getHauteurNiveau()-2));
		TriggerTeleport trTeleportDroite = new TriggerTeleport(engineManager,"teleport2", lvl.getLargeurTile()*(lvl.getLargeurNiveau()-1)-5,lvl.getHauteurTile()+2,5,lvl.getHauteurTile()*(lvl.getHauteurNiveau()-2));
		trGauche.setRemoteDestination("infotargetDroite");	
		trTeleportDroite.setRemoteDestination("infotargetGauche");
		
		
		InfoTarget inf = new InfoTarget(engineManager,"infotargetMilieuHaut", 700, 40);
		
		InfoTarget infGauche[] = new InfoTarget[6];
		for(int i=0;i<6;i++){
			infGauche[i] = new InfoTarget(engineManager,"infotargetGauche", lvl.getLargeurTile()*2, (lvl.getHauteurTile()+5)+(lvl.getHauteurTile()*4*i));
			engineManager.getInfoManager().addEntity(infGauche[i]);
			//Deplacement.deplacerEntity(engineManager,0, 0, infGauche[i].getId());
			Deplacement.ajouterEntiteDansTiles(infGauche[i]);
		}
		
		InfoTarget infDroite[] = new InfoTarget[6];
		for(int i=0;i<6;i++){
			infDroite[i] = new InfoTarget(engineManager,"infotargetDroite", lvl.getLargeurTile()*lvl.getLargeurNiveau()-lvl.getLargeurTile()*3, (lvl.getHauteurTile()+5)+(lvl.getHauteurTile()*4*i));
			engineManager.getInfoManager().addEntity(infDroite[i]);
			//Deplacement.deplacerEntity(engineManager,0, 0, infDroite[i].getId());
			Deplacement.ajouterEntiteDansTiles(infDroite[i]);
		}
		
		//InfoTarget inf = new InfoTarget(engineManager,"infotarget", 200, 36);
		FilterActivatorName fil = new FilterActivatorName(engineManager,"filtername",false,"zombi");
		
		trGauche.setFilterEntityThatActivate(fil);	trTeleportDroite.setFilterEntityThatActivate(fil);
		engineManager.getInfoManager().addEntity(inf);
		engineManager.getFilterManager().addEntity(fil);
		//lvl.addEntity(tr);
		engineManager.addEntity(trGauche);
		engineManager.addEntity(trTeleportDroite);
		lvl.addEntity(inf);
		//Deplacement.deplacerEntity(engineManager,0, 0, tr.getId());
		Deplacement.ajouterEntiteDansTiles(trGauche);	Deplacement.ajouterEntiteDansTiles(trTeleportDroite);
		Deplacement.deplacerEntity(engineManager,0, 0, inf.getId());
		lvl.addEntity(hero);
		lvl.addEntity(z);
		Deplacement.deplacerEntity(engineManager,0, 0, hero.getId());
		Deplacement.deplacerEntity(engineManager,0, 0, z.getId());
		engineManager.getIA().addEntity(hero);
		engineManager.getIA().addEntity(z);
		
		System.out.println("hero : " + hero);
		
		player = new Player(engineManager, "pseudoSolo", hero.getId());
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame sbgame)
	{
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {
		lvl.getScroll().mettreAJourScroll(hero);
		//if(lvl.isLoadOver())
			lvl.generateLevelGraphic(g, 200, 100);
			
		super.render(container, sbgame, g);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException 
	{	
		player.update(delta);
		
		engineManager.update(delta);
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		player.keyPressed(key, c);
		/*
		Message m = new Message();
		switch(key){
			case Input.KEY_SPACE:
				//hero.jump();
				m.instruction = MessageKey.I_JUMP;
				m.i_data.put(MessageKey.P_ID, hero.getId());
				m.engine = EngineManager.LOGIC_ENGINE;
				
				engineManager.receiveMessage(m);
				break;
			case Input.KEY_RIGHT:
				m.instruction = MessageKey.I_START_ENTITY_MOVE;
				m.i_data.put(MessageKey.P_ID, hero.getId());
				m.i_data.put(MessageKey.P_DIRECTION, BasicEntity.DROITE);
				m.b_data.put(MessageKey.P_BOOLEAN, true);//On mets en déplacement
				m.engine = EngineManager.LOGIC_ENGINE;
		
				engineManager.receiveMessage(m);
				break;
			case Input.KEY_LEFT:
				m.instruction = MessageKey.I_START_ENTITY_MOVE;
				m.i_data.put(MessageKey.P_ID, hero.getId());
				m.i_data.put(MessageKey.P_DIRECTION, BasicEntity.GAUCHE);
				m.b_data.put(MessageKey.P_BOOLEAN, true);//On mets en déplacement
				m.engine = EngineManager.LOGIC_ENGINE;
				
				engineManager.receiveMessage(m);
			break;
		}
		//*/
	}
	@Override
	public void keyReleased(int key, char c) {
		super.keyReleased(key, c);
		player.keyReleased(key, c);
		/*
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
			m.b_data.put(MessageKey.P_BOOLEAN, false);//On arrête le déplacement
			m.engine = EngineManager.LOGIC_ENGINE;
			
			engineManager.receiveMessage(m);
			break;
		case Input.KEY_LEFT:
			Message m2 = new Message();
			m2.instruction = MessageKey.I_START_ENTITY_MOVE;
			m2.i_data.put(MessageKey.P_ID, hero.getId());
			m2.i_data.put(MessageKey.P_DIRECTION, BasicEntity.DROITE);
			m2.b_data.put(MessageKey.P_CHANGE_DIRECTION, false);
			m2.b_data.put(MessageKey.P_BOOLEAN, false);//On arrête le déplacement
			m2.engine = EngineManager.LOGIC_ENGINE;
			
			engineManager.receiveMessage(m2);
		break;
		}
		//*/
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
	}

	@Override
	public int getID() {
		return Game.TEST_STATE_ID;
	}
}
