package base.views;

import java.io.File;
import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import base.engine.EngineManager;
import base.engine.Game;
import base.engine.LogicEngine;
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
import base.utils.ResourceManager;
import base.engine.NetworkEngine;

public class ServerView extends View {

	private TileSet t;
	private LevelDrol lvl;
	ArrayList<TilePropriety> tp = new ArrayList<TilePropriety>(0);
	HeroEntity hero;

	@Override
	public void initResources() {
		// TODO Auto-generated method stub
		tp.add(new TilePropriety(0, false, "fodfnd"));
		tp.add(new TilePropriety(1, false, "fond"));
		tp.add(new TilePropriety(2, true, "fodnd"));
		tp.add(new TilePropriety(3, false, "fonssd"));
		
		
		t = new TileSet(ResourceManager.getSpriteSheet("sprite"), tp);
		
		lvl = new LevelDrol(new File("levels/lvl_0.lvl"), t);
		lvl.loadLevel();
		engineManager.setCurrentLevelUsed(lvl);
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
		
		((NetworkEngine)EngineManager.getInstance().getTabEngine()[2]).setServeur(true);
		
		//On démarre le serveur
		
	}

	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException 
	{
		engineManager.update(delta);
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return Game.SERVER_VIEW_ID;
	}

}
